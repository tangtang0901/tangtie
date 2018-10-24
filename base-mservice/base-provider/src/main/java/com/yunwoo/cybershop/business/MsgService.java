package com.yunwoo.cybershop.business;

import com.yunwoo.cybershop.constant.MsgTemplate;
import com.yunwoo.cybershop.dto.MsgConfigDTO;
import com.yunwoo.cybershop.dto.MsgTemplateDTO;

import java.util.Map;

/**
 * @company yunwoo
 * @created by huanghaizhou
 * @date 2018/4/12 下午1:37
 */
public interface MsgService {
    /**
     * 发送短信
     * @param phonenumber 手机号
     * @param msgTemplate 短信模板
     * @param params 参数
     * @return
     */
    boolean sendMsg(String phonenumber, MsgTemplate msgTemplate,Map<String,String> params);

    /**
     * 获取激活的短信配置
     * @return
     */
    MsgConfigDTO getActiveMsgConfig();
    /**
     * 根据内部编码获取短信模板
     * @return
     */
    MsgTemplateDTO getByInternalCode(Integer internalCode);
}
