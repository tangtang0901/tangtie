package com.yunwoo.cybershop.business.impl;

import com.yunwoo.cybershop.business.MenuService;
import com.yunwoo.cybershop.dto.MenuDTO;
import com.yunwoo.cybershop.storage.db.MenuDBStorage;
import com.yunwoo.cybershop.storage.po.MenuPO;
import com.yunwoo.cybershop.utils.BeanConverter;
import com.yunwoo.cybershop.utils.CollectionUtils;
import com.yunwoo.cybershop.storage.db.MenuDBStorage;
import com.yunwoo.cybershop.storage.po.MenuPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
	public static final String MENU_CACHE_NAME = "menu";
	public static final String MENU_CACHE_KEY = "'menu#'";
	@Autowired
	private MenuDBStorage menuDBStorage;
	
	@Override
	public List<MenuDTO> getAllMenu() {
		return getByPId(0);
	}
	
	private List<MenuDTO> getByPId(int pId){
		List<MenuDTO> result = new ArrayList<MenuDTO>();
		List<MenuPO> menus = menuDBStorage.getByPId(pId);
		if(CollectionUtils.isNotEmpty(menus)){
			for (MenuPO menuPO : menus) {
				MenuDTO dto = BeanConverter.to(menuPO,MenuDTO.class);
				result.add(dto);
				List<MenuDTO> cMenus = getByPId(menuPO.getId());
				if(CollectionUtils.isNotEmpty(cMenus)){
					dto.setChildren(cMenus);
				}
			}
		}
		System.out.println(result);
		return result;

	}

}
