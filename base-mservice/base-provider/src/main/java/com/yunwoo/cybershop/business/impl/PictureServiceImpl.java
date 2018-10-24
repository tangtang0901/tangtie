package com.yunwoo.cybershop.business.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yunwoo.cybershop.business.PictureService;
import com.yunwoo.cybershop.constant.TableName;
import com.yunwoo.cybershop.dto.PictureDTO;
import com.yunwoo.cybershop.dubbo.PrimaryKeyDubboService;
import com.yunwoo.cybershop.storage.db.PictureDBStorage;
import com.yunwoo.cybershop.storage.po.PicturePO;
import com.yunwoo.cybershop.utils.BeanConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 图片服务层实现类
 * @author ALI
 *         at 2017/4/4 0004 16:22
 */
@Service
public class PictureServiceImpl implements PictureService {
	private static final Logger log = LoggerFactory.getLogger(PictureServiceImpl.class);

	@Autowired
	private PictureDBStorage pictureDBStorage;
	@Reference(version = "1.0.0",check = true)
	private PrimaryKeyDubboService primaryKeyDubboService;

	@Override
	public PictureDTO get(int pictureId) {
		return BeanConverter.to(pictureDBStorage.get(pictureId),PictureDTO.class);
	}

	@Override
	public PictureDTO getByUrl(String url) {
		return BeanConverter.to(pictureDBStorage.getByUrl(url),PictureDTO.class);
	}

	@Override
	public Integer save(PictureDTO pictureDTO) {
		int id = primaryKeyDubboService.generatePrimaryKey(TableName.PICTURE.name());
		pictureDTO.setId(id);
		boolean result = pictureDBStorage.add(BeanConverter.to(pictureDTO, PicturePO.class));
		if(result) {
			return id;
		}
		return null;
	}
}
