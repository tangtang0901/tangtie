package com.yunwoo.cybershop.web.service.menu;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yunwoo.cybershop.dto.MenuDTO;
import com.yunwoo.cybershop.dubbo.MenuQueryDubboService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @company yunwoo
 * @created by huanghaizhou
 * @date 2018/4/5 下午7:53
 */
@Service
public class MenuServiceImpl implements MenuService{
    @Reference(version = "1.0.0",check = true)
    private MenuQueryDubboService menuQueryDubboService;
    @Override
    public List<MenuDTO> getAllMenu() {
        return menuQueryDubboService.getAllMenu();
    }
}
