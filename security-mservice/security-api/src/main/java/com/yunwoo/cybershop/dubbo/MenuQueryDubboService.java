package com.yunwoo.cybershop.dubbo;

import com.yunwoo.cybershop.dto.MenuDTO;
import com.yunwoo.cybershop.dto.MenuDTO;

import java.util.List;

public interface MenuQueryDubboService {
    List<MenuDTO> getAllMenu();
}
