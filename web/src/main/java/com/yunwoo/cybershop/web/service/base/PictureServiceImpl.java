package com.yunwoo.cybershop.web.service.base;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yunwoo.cybershop.dto.PictureDTO;
import com.yunwoo.cybershop.dubbo.PictureQueryDubboService;
import org.springframework.stereotype.Service;

/**
 * @company yunwoo
 * @created by huanghaizhou
 * @date 2018/4/10 下午6:26
 */
@Service
public class PictureServiceImpl implements PictureService{
    @Reference(version = "1.0.0",check = true)
    private PictureQueryDubboService pictureQueryDubboService;
    @Override
    public PictureDTO getByUrl(String url) {
        return pictureQueryDubboService.getByUrl(url);
    }

    @Override
    public int save(PictureDTO pictureDTO) {
        return pictureQueryDubboService.save(pictureDTO);
    }
}
