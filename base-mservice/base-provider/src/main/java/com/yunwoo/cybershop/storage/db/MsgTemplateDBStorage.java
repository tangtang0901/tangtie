package com.yunwoo.cybershop.storage.db;

import com.yunwoo.cybershop.db.BaseStorage;
import com.yunwoo.cybershop.storage.po.MsgTemplatePO;
import com.yunwoo.cybershop.storage.po.PicturePO;

/**
 * 短信模板仓储接口
 * @author ALI
 *         at 2017/4/4 0004 0:41
 */
public interface MsgTemplateDBStorage extends BaseStorage<MsgTemplatePO> {
    MsgTemplatePO getByInternalCode(Integer internalCode);
}
