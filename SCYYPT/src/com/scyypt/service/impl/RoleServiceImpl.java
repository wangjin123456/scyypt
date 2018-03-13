package com.scyypt.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.scyypt.dao.RoleDao;
import com.scyypt.entity.Role;
import com.scyypt.entity.URAProvider;
import com.scyypt.service.RoleService;
import com.scyypt.service.URAProviderService;

/**
 * 角色业务实现类
 * 
 * @Description:
 *
 * @Author 程传平
 *
 * @Time 2018-01-22 14:21
 *
 */
@Service("RoleService")
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private URAProviderService uRAProviderService;

	@Override
	public List<Role> getRole() {
		return roleDao.getRole();
	}

	@Override
	public int addRole(Role role) {
		return roleDao.addRole(role);
	}

	@Override
	public int updateRole(Role role) {
		return roleDao.updateRole(role);
	}

	@Override
	public int delRole(String role_id) {
		return roleDao.delRole(role_id);
	}

	@Override
	public List<Role> queryRole(String role_id) {
		return roleDao.queryRole(role_id);
	}

	@Override
	public Integer setRoleAuthority(String roles) {

		JSONObject json = JSONObject.parseObject(roles);

		String role_id = json.get("role_id").toString();
		List<String> menu_id = new ArrayList<>();
		List<String> operationIds = new ArrayList<>();
		JSONArray menus = json.getJSONArray("menu");

		for (int i = 0; i < menus.size(); i++) {

			JSONObject menu = menus.getJSONObject(i);
			menu_id.add(menu.getString("menu_id"));
			JSONArray oparations = menu.getJSONArray("operation");

			for (int j = 0; j < oparations.size(); j++) {

				JSONObject oparation = oparations.getJSONObject(j);
				operationIds.add(oparation.getString("operationId"));

			}
			String operationId = "";

			for (int k = 0; k < operationIds.size(); k++) {

				if (operationId.equals("")) {

					operationId = operationIds.get(k);

				} else {

					operationId = operationId + "," + operationIds.get(k);
				}
			}
			URAProvider uRAProvider = new URAProvider();

			uRAProvider.setRole_id(Integer.parseInt(role_id));
			uRAProvider.setAuthority_id(Integer.parseInt(menu.getString("menu_id")));
			uRAProvider.setOperationId(operationId);
			uRAProviderService.updateAuthority(uRAProvider);
			operationId = "";
			operationIds.clear();
		}

		String menuId = "";

		for (int i = 0; i < menu_id.size(); i++) {

			if (menuId.equals("")) {

				menuId = menu_id.get(i);

			} else {

				menuId = menuId + "," + menu_id.get(i);
			}
		}
		Role role = new Role();
		role.setRole_id(Integer.parseInt(role_id));
		role.setAuthority_id(menuId);
		return roleDao.setRoleAuthority(role);
	}

}
