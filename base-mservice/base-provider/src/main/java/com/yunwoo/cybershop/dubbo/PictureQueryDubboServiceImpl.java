package com.yunwoo.cybershop.dubbo;

import com.alibaba.dubbo.config.annotation.Service;
import com.yunwoo.cybershop.business.PictureService;
import com.yunwoo.cybershop.dto.PictureDTO;
import org.springframework.beans.factory.annotation.Autowired;

@Service(version = "1.0.0",timeout = 20000)
public class PictureQueryDubboServiceImpl implements PictureQueryDubboService {
    @Autowired
    private PictureService pictureService;
    @Override
    public PictureDTO get(int pictureId) {
        return pictureService.get(pictureId);
    }

    @Override
    public PictureDTO getByUrl(String url) {
        return pictureService.getByUrl(url);
    }

    @Override
    public int save(PictureDTO pictureDTO) {
        return pictureService.save(pictureDTO);
    }
}
