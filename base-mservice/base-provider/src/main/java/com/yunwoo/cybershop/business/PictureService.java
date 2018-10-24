package com.yunwoo.cybershop.business;

import com.yunwoo.cybershop.dto.PictureDTO;

/**
 * 图片服务层接口
 * @author ALI
 *         at 2017/4/4 0004 16:22
 */
public interface PictureService {

	PictureDTO get(int pictureId);
	PictureDTO getByUrl(String url);
	Integer save(PictureDTO pictureDTO);
}
