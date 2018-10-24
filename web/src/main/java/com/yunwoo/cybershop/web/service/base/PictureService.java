package com.yunwoo.cybershop.web.service.base;

import com.yunwoo.cybershop.dto.PictureDTO;

/**
 * @company yunwoo
 * @created by huanghaizhou
 * @date 2018/4/10 下午6:25
 */
public interface PictureService {
    PictureDTO getByUrl(String url);
    int save(PictureDTO pictureDTO);
}
