package com.yunwoo.cybershop.dubbo;

import com.yunwoo.cybershop.dto.PictureDTO;

public interface PictureQueryDubboService {
    PictureDTO get(int pictureId);
    PictureDTO getByUrl(String url);
    int save(PictureDTO pictureDTO);
}
