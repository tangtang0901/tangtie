package com.yunwoo.cybershop.business;

import com.yunwoo.cybershop.dto.MenuDTO;

import java.util.List;

/**
 * 菜单业务层
 * @author Fox
 *
 */
public interface MenuService {

	List<MenuDTO> getAllMenu();
}
