package com.yunwoo.cybershop.storage.db;

import com.yunwoo.cybershop.db.BaseStorage;
import com.yunwoo.cybershop.storage.po.PicturePO;

/**
 * 图片仓储接口
 * @author ALI
 *         at 2017/4/4 0004 0:41
 */
public interface PictureDBStorage extends BaseStorage<PicturePO> {
    PicturePO getByUrl(String url);
}
