package com.yunwoo.cybershop.business.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yunwoo.cybershop.business.MemberService;
import com.yunwoo.cybershop.dto.MemberDTO;
import com.yunwoo.cybershop.dto.PictureDTO;
import com.yunwoo.cybershop.dubbo.PictureQueryDubboService;
import com.yunwoo.cybershop.model.Pagination;
import com.yunwoo.cybershop.model.PaginationResult;
import com.yunwoo.cybershop.storage.db.MemberDBStorage;
import com.yunwoo.cybershop.storage.po.MemberPO;
import com.yunwoo.cybershop.utils.BeanConverter;
import com.yunwoo.cybershop.utils.MD5Util;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MemberServiceImpl implements MemberService {
    public static final String CACHE_NAME = "member";
    public static final String CACHE_KEY = "'member#'";

    public static final String LOGIN_FAIL_CACHE_KEY = "loginFail#";
    public static final Long LOGIN_FAIL_CACHE_EXPIRE = 1L;

    public static final String CAPTCHA_CACHE_KEY = "captcha#";
    public static final Long CAPTCHA_FAIL_CACHE_EXPIRE = 1L;

    public static final String SESSION_CACHE_KEY = "session#";
    public static final Long SESSION_FAIL_CACHE_EXPIRE = 7L;

    public static final String REGISTER_CAPTCHA_CACHE_KEY = "register_captcha#";
    public static final Long REGISTER_CAPTCHA_FAIL_CACHE_EXPIRE = 1L;
    @Autowired
    private MemberDBStorage memberDBStorage;

    @Reference(version = "1.0.0",check = true)
    private PictureQueryDubboService pictureQueryDubboService;
    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 自己本身，只有这样才能在调用自己的方法时候走缓存注解
     */
    @Autowired
    private MemberServiceImpl memberService;

    @Override
    @Cacheable(value = CACHE_NAME, key = CACHE_KEY + "+#id.toString()", unless = "#result==null")
    public MemberDTO getById(int id) {
        MemberPO memberPO = memberDBStorage.get(id);
        if (memberPO != null) {
            MemberDTO memberDTO = BeanConverter.to(memberPO, MemberDTO.class);
            PictureDTO pictureDTO = pictureQueryDubboService.get(memberDTO.getAvatar());
            if(pictureDTO != null){
                memberDTO.setAvatarUrl(pictureDTO.getUrl());
            }
            return memberDTO;
        }
        return null;
    }

    @Override
    public boolean save(MemberDTO dto) {
        MemberPO po = new MemberPO();
        BeanUtils.copyProperties(dto, po);
        return memberDBStorage.add(po);
    }

    @Override
    public PaginationResult<MemberDTO> getByPagination(Pagination pagination) {
        PaginationResult<Integer> result = memberDBStorage.getByPagination(pagination);
        List<Integer> ids = result.getResult();
        List<MemberDTO> memberDTOS = new ArrayList<MemberDTO>();
        for (Integer id : ids) {
            if(id == 0) {
                continue;
            }
            memberDTOS.add(memberService.getById(id));
        }
        return new PaginationResult<MemberDTO>(pagination,result.getTotalNum(),memberDTOS);
    }

    @Override
    public MemberDTO getByUsername(String username) {
        MemberPO memberPO = memberDBStorage.getByUsername(username);
        if(memberPO != null){
            return BeanConverter.to(memberPO,MemberDTO.class);
        }
        return null;
    }

    @Override
    public MemberDTO getByPhonenum(String phonenum) {
        MemberPO memberPO = memberDBStorage.getByPhonenum(phonenum);
        if(memberPO != null){
            return BeanConverter.to(memberPO,MemberDTO.class);
        }
        return null;
    }
    @CacheEvict(value = CACHE_NAME, key = CACHE_KEY + "+#dto.id.toString()",condition = "#dto != null")
    @Override
    public boolean update(MemberDTO dto) {
        return memberDBStorage.update(BeanConverter.to(dto,MemberPO.class));
    }

    @Override
    public void addLoginFailCount(Integer memberId) {
        RedisAtomicLong counter = new RedisAtomicLong(generateLoginFailCountKey(memberId), redisTemplate.getConnectionFactory());
        counter.incrementAndGet();
        //一分钟后过期
        counter.expire(LOGIN_FAIL_CACHE_EXPIRE, TimeUnit.MINUTES);
    }

    @Override
    public Long getLoginFailCount(Integer memberId) {
        RedisAtomicLong counter = new RedisAtomicLong(generateLoginFailCountKey(memberId), redisTemplate.getConnectionFactory());
        return counter.get();
    }

    @Override
    public void delLoginFailCount(Integer memberId) {
        redisTemplate.delete(generateLoginFailCountKey(memberId));
    }

    @Override
    public void addCaptcha(String unique, String captcha) {
        redisTemplate.opsForValue().set(generateCaptchaKey(unique),captcha);
        //一分钟后过期
        redisTemplate.expire(generateCaptchaKey(unique),CAPTCHA_FAIL_CACHE_EXPIRE, TimeUnit.MINUTES);
    }

    @Override
    public String getCaptcha(String unique) {
        return (String) redisTemplate.opsForValue().get(generateCaptchaKey(unique));
    }

    @Override
    public String createSession(Integer memberId, String unique) {
        String session = MD5Util.MD5(unique + memberId + System.currentTimeMillis());
        redisTemplate.opsForValue().set(generateSessionKey(memberId),session);
        redisTemplate.expire(generateSessionKey(memberId),SESSION_FAIL_CACHE_EXPIRE, TimeUnit.DAYS);
        return session;
    }

    @Override
    public String getSession(Integer memberId) {
        return (String) redisTemplate.opsForValue().get(generateSessionKey(memberId));
    }

    @Override
    public void delCaptcha(String unique) {
        redisTemplate.delete(generateCaptchaKey(unique));
    }

    @Override
    public void addRegisterCode(String phonenumber, String captcha) {
        redisTemplate.opsForValue().set(generateRegisterCaptchaKey(phonenumber),captcha);
        //一分钟后过期
        redisTemplate.expire(generateRegisterCaptchaKey(phonenumber),REGISTER_CAPTCHA_FAIL_CACHE_EXPIRE, TimeUnit.MINUTES);
    }

    @Override
    public String getRegisterCode(String phonenumber) {
        return  (String)redisTemplate.opsForValue().get(generateRegisterCaptchaKey(phonenumber));
    }

    /**
     * 生成过期失效KEY
     * @param memberId
     * @return
     */
    private String generateLoginFailCountKey(Integer memberId){
        return LOGIN_FAIL_CACHE_KEY + memberId;
    }
    /**
     * 生成图片验证码失效KEY
     * @param unique
     * @return
     */
    private String generateCaptchaKey(String unique){
        return CAPTCHA_CACHE_KEY + unique;
    }
    /**
     * 生成会话KEY
     * @param memberId
     * @return
     */
    private String generateSessionKey(Integer memberId){
        return SESSION_CACHE_KEY + memberId;
    }
    /**
     * 生成注册验证码KEY
     * @param phonenumber
     * @return
     */
    private String generateRegisterCaptchaKey(String phonenumber){
        return REGISTER_CAPTCHA_CACHE_KEY + phonenumber;
    }
}

