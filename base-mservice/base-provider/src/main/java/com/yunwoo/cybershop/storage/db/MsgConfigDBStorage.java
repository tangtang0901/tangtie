package com.yunwoo.cybershop.storage.db;

import com.yunwoo.cybershop.db.BaseStorage;
import com.yunwoo.cybershop.storage.po.MsgConfigPO;
import com.yunwoo.cybershop.storage.po.MsgTemplatePO;

/**
 * 短信配置仓储接口
 */
public interface MsgConfigDBStorage extends BaseStorage<MsgConfigPO> {
    MsgConfigPO getByInternalCode(Integer internalCode);
}
