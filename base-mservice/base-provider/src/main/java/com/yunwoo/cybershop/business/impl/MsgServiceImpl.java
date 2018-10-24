package com.yunwoo.cybershop.business.impl;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.yunwoo.cybershop.business.MsgService;
import com.yunwoo.cybershop.constant.MsgTemplate;
import com.yunwoo.cybershop.dto.MsgConfigDTO;
import com.yunwoo.cybershop.dto.MsgTemplateDTO;
import com.yunwoo.cybershop.storage.db.MsgConfigDBStorage;
import com.yunwoo.cybershop.storage.db.MsgLogDBStorage;
import com.yunwoo.cybershop.storage.db.MsgTemplateDBStorage;
import com.yunwoo.cybershop.storage.po.MsgConfigPO;
import com.yunwoo.cybershop.storage.po.MsgLogPO;
import com.yunwoo.cybershop.storage.po.MsgTemplatePO;
import com.yunwoo.cybershop.util.AliMsgUtil;
import com.yunwoo.cybershop.utils.BeanConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @company yunwoo
 * @created by huanghaizhou
 * @date 2018/4/12 下午1:37
 */
@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MsgServiceImpl implements MsgService{
    private static final String OK = "OK";

    public static final String CACHE_NAME = "msg";
    public static final String CACHE_KEY = "'msg#'";
    @Autowired
    private MsgConfigDBStorage msgConfigDBStorage;
    @Autowired
    private MsgTemplateDBStorage msgTemplateDBStorage;
    @Autowired
    private MsgLogDBStorage msgLogDBStorage;
    /**
     * 自己本身，只有这样才能在调用自己的方法时候走缓存注解
     */
    @Autowired
    private MsgServiceImpl msgService;
    /**
     * 激活的短信配置内部编码
     */
    @Value("${msg.config.activeInternalCode}")
    private Integer MSG_CONFIG_ACTIVE_INTERNALCODE;
    @Override
    public boolean sendMsg(String phonenumber, MsgTemplate msgTemplate, Map<String, String> params) {
        MsgConfigDTO activeMsgConfig = msgService.getActiveMsgConfig();
        if(activeMsgConfig != null && activeMsgConfig.getIsEnable()){
            MsgTemplateDTO templateDTO = msgService.getByInternalCode(msgTemplate.getInternalCode());
            if(templateDTO != null){
                try {
                    SendSmsResponse sendSmsResponse = AliMsgUtil.sendSms(activeMsgConfig.getAccessKeyId(), activeMsgConfig.getAccessKeySecret(), phonenumber, activeMsgConfig.getSignName(), templateDTO.getCode(), params, "");
                    //插入日志
                    if(OK.equals(sendSmsResponse.getCode())){
                        MsgLogPO log = new MsgLogPO();
                        log.setMsgConfig(activeMsgConfig.getId());
                        log.setMsgTemplate(templateDTO.getId());
                        log.setPhonenumber(phonenumber);
                        log.setParams(JSON.toJSONString(params));
                        log.setBizId(sendSmsResponse.getBizId());
                        log.setCode(sendSmsResponse.getCode());
                        log.setMessage(sendSmsResponse.getMessage());
                        log.setCreateTime(new Date());
                        msgLogDBStorage.add(log);
                        return true;
                    }
                } catch (ClientException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }
        return false;
    }

    @Cacheable(value = CACHE_NAME, key = CACHE_KEY + "+'config_default'", unless = "#result==null")
    @Override
    public MsgConfigDTO getActiveMsgConfig() {
        MsgConfigDTO msgConfigDTO = null;
        MsgConfigPO msgConfigPO = msgConfigDBStorage.getByInternalCode(MSG_CONFIG_ACTIVE_INTERNALCODE);
        if(msgConfigPO != null){
            msgConfigDTO = BeanConverter.to(msgConfigPO,MsgConfigDTO.class);
        }
        return msgConfigDTO;
    }

    @Cacheable(value = CACHE_NAME, key = CACHE_KEY + "+'template_'+#internalCode.toString()", unless = "#result==null")
    @Override
    public MsgTemplateDTO getByInternalCode(Integer internalCode) {
        MsgTemplateDTO msgTemplateDTO = null;
        MsgTemplatePO msgTemplatePO = msgTemplateDBStorage.getByInternalCode(internalCode);
        if(msgTemplatePO != null){
            msgTemplateDTO = BeanConverter.to(msgTemplatePO,MsgTemplateDTO.class);
        }
        return msgTemplateDTO;
    }

}
