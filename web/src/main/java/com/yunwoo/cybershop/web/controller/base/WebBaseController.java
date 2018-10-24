package com.yunwoo.cybershop.web.controller.base;

import com.yunwoo.cybershop.dto.MenuDTO;
import com.yunwoo.cybershop.web.service.menu.MenuService;
import httl.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
public class WebBaseController {
	
	private static List<MenuDTO> allMenus;
	private static Map<Integer,MenuDTO> allMenusMap;
	private static Map<String,MenuDTO> allMenusUrlMap;

	@Autowired
	private MenuService menuService;

	public List<MenuDTO> getAllMenus(){
		if(CollectionUtils.isNotEmpty(allMenus)) {
			return allMenus;
		}
		allMenus = menuService.getAllMenu();
		return allMenus;
	}
	
	@ModelAttribute("breadCrumb")
	public List<MenuDTO> getBreadCrumb(HttpServletRequest request){
		if(allMenus == null) {
			initMenu();
		}
		String uri = request.getRequestURI();
		MenuDTO menuDTO = allMenusUrlMap.get(uri);
		List<MenuDTO> menuList = null;
		if(menuDTO != null){
			menuList = new ArrayList<>();
			//递归加入父级
			getParentMenuAndSetToList(menuList, menuDTO);
			//把自己加入
			menuList.add(menuDTO);
		}
		
		return menuList;
	}

	@ModelAttribute("urlParamsStr")
	public String getRequestParamsForBreadcrumb(HttpServletRequest request){
		Map parameterMap = request.getParameterMap();
		Set set = parameterMap.entrySet();
		StringBuilder stringBuilder = new StringBuilder();
		Iterator iterator = set.iterator();
		String key = null;
		String excludeKey = "menuId";
		String and = "&";
		String eq = "=";
		while (iterator.hasNext()){
			Map.Entry entry = (Map.Entry)iterator.next();
			key = entry.getKey().toString();
			if(key.equals(excludeKey)) {
				continue;
			}
			stringBuilder.append(key+eq+((String[])entry.getValue())[0].toString()+and);

		}
		String lastBreadcrumb = stringBuilder.toString();
		lastBreadcrumb = and+lastBreadcrumb;
		lastBreadcrumb = lastBreadcrumb.substring(0,lastBreadcrumb.length()-1);
		if(lastBreadcrumb.equals(and)) {
			lastBreadcrumb = "";
		}
		return lastBreadcrumb;
	}

	private void initMenu(){
		getAllMenus();
		getAllMenusMap();
		getAllMenusUrlMap();
	}
	
	public void getParentMenuAndSetToList(List<MenuDTO> menuList,MenuDTO childMenu){
		MenuDTO pMenuDTO = getAllMenusMap().get(childMenu.getPId());
		if(pMenuDTO != null){
			getParentMenuAndSetToList(menuList, pMenuDTO);
			menuList.add(pMenuDTO);
		}
		
	}
	
	private Map<Integer,MenuDTO> getAllMenusMap(){
		if(CollectionUtils.isEmpty(allMenusMap)) {
			allMenusMap = new HashMap<>();
			getAllMenus();
			setToAllMenusMap(allMenus);
		}
		return allMenusMap;
	}
	private Map<String,MenuDTO> getAllMenusUrlMap(){
		if(CollectionUtils.isEmpty(allMenusUrlMap)) {
			allMenusUrlMap = new HashMap<>();
			getAllMenus();
			setToAllMenusUrlMap(allMenus);
		}
		return allMenusUrlMap;
	}

	private void setToAllMenusMap(List<MenuDTO> menus){
		for (MenuDTO menu : menus) {
			List<MenuDTO> children = menu.getChildren();
			if(CollectionUtils.isNotEmpty(children)){
				setToAllMenusMap(children);
			}
			allMenusMap.put(menu.getId(), menu);
		}
	}
	private void setToAllMenusUrlMap(List<MenuDTO> menus){
		for (MenuDTO menu : menus) {
			List<MenuDTO> children = menu.getChildren();
			if(CollectionUtils.isNotEmpty(children)){
				setToAllMenusUrlMap(children);
			}
			allMenusUrlMap.put(menu.getUrl(), menu);
		}
	}


}
