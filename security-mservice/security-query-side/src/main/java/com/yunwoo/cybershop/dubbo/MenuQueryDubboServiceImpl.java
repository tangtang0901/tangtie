package com.yunwoo.cybershop.dubbo;

import com.alibaba.dubbo.config.annotation.Service;
import com.yunwoo.cybershop.business.MenuService;
import com.yunwoo.cybershop.dto.MenuDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(version = "1.0.0",timeout = 20000)
public class MenuQueryDubboServiceImpl implements MenuQueryDubboService {
    @Autowired
    private MenuService menuService;

    @Override
    public List<MenuDTO> getAllMenu() {
        return menuService.getAllMenu();
    }
}
