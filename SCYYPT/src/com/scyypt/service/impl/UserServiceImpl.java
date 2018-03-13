package com.scyypt.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.scyypt.dao.UserDao;
import com.scyypt.entity.Authority;
import com.scyypt.entity.Operation;
import com.scyypt.entity.Role;
import com.scyypt.entity.UserEntity;
import com.scyypt.service.AuthorityService;
import com.scyypt.service.OperationService;
import com.scyypt.service.RoleService;
import com.scyypt.service.UserService;

/**
 * 用户信息服务实现
 * 
 * @Description:
 * @author ChengChuanPing
 * @Time 2018年1月17日下午2:27:34
 */
@Service("UserService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleService roleService;

	@Autowired
	private AuthorityService authorityService;
	
	@Autowired
	private OperationService operationService;

	@Override
	public Object queryUserinfo(UserEntity userEntity) {

		List<UserEntity> user = userDao.queryUserinfo(userEntity);

		String userjson = JSON.toJSONString(user);
		
		String role_id = null;
		
		JSONArray userList = JSONArray.parseArray(userjson);
		
		for (int i = 0; i < userList.size(); i++) {

			JSONObject jsonObj = JSONObject.parseObject(userList.get(i).toString());
			role_id = jsonObj.get("role_id").toString();
		}

		String roleName = null;
		
		String authorityId = null;
		
		List<Role> roleItem = roleService.queryRole(role_id);
		
		String roleJson = JSON.toJSONString(roleItem);
		
		JSONArray roleList = JSONArray.parseArray(roleJson);
		
		for (int i = 0; i < roleList.size(); i++) {

			JSONObject jsonObj = JSONObject.parseObject(roleList.get(i).toString());
			// role_id = jsonObj.get("role_id").toString();
			roleName = jsonObj.get("role_name").toString();
			authorityId = jsonObj.get("authority_id").toString();
		}

		List<Authority> authoritys = authorityService.findAuthority(authorityId);
		
		List<String> menuId = new ArrayList<>();
		
		List<String> menuName = new ArrayList<>();
		
		List<Object> operation = new ArrayList<>();
		
		String authorityJson = JSON.toJSONString(authoritys);
		
		JSONArray authorityList = JSONArray.parseArray(authorityJson);
		
		for (int i = 0; i < authorityList.size(); i++) {

			JSONObject jsonObj = JSONObject.parseObject(authorityList.get(i).toString());
			String authority_id=jsonObj.get("authority_id").toString();
			menuId.add(authority_id);
			menuName .add(jsonObj.get("operate").toString());
			
			List<Operation> operations=operationService.queryOperation(role_id, authority_id);
			
			operation.add(operations);
			//String operationJson = JSON.toJSONString(operations);
			
			//JSONArray operationList = JSONArray.parseArray(operationJson);
			
			//for (int j = 0; j < operationList.size(); j++) {
				
				//JSONObject operationObj = JSONObject.parseObject(operationList.get(j).toString());
				//operationObj.getString("operationId");
				//operationObj.getString("operationName");
			//}
		}
		
		JSONArray array = new JSONArray();
		JSONObject obj;
		
		for (int i = 0; i < menuId.size(); i++) {
			
			obj = new JSONObject();
			obj.put("authority_id", menuId.get(i));
			obj.put("operate", menuName.get(i));
			obj.put("status", true);
			obj.put("operation", operation.get(i));
			array.add(obj);
			
		}
		
		JSONObject jsonobj = new JSONObject();
		
		jsonobj.put("role_name", roleName);
		
		jsonobj.put("authority", array);
		
		return jsonobj;
		
		
		
		
		///////////////////////////////////////////////////////////////////////////////////////////
		/*String role_name = "";
		JSONObject jsonobj = new JSONObject();
		List<String> operate = new ArrayList<>();
		List<String> authority_id = new ArrayList<>();
		List<Object> status = new ArrayList<>();
		JSONArray array = new JSONArray();
		JSONObject obj;
		List<UserEntity> list = userDao.queryUserinfo(userEntity);
		// String jsonResult = Global.getProtocol(list);
		String json = JSON.toJSONString(list);
		// String str=null;
		// str=json.substring(1, json.length());
		// str=json.substring(0,json.length()-1);
		// json=json.replace("[", "");
		// json=json.replace("]", "");
		JSONArray jsonList = JSONArray.parseArray(json);
		for (int i = 0; i < jsonList.size(); i++) {

			JSONObject o = JSONObject.parseObject(jsonList.get(i).toString());
			JSONObject role = JSONObject.parseObject(o.get("role").toString());
			// JSONObject authority =
			// JSONObject.parseObject(o.get("authority").toString());
			JSONArray authority = JSONArray.parseArray(o.get("authority").toString());
			for (int j = 0; j < authority.size(); j++) {
				JSONObject ad = JSONObject.parseObject(authority.get(j).toString());
				authority_id.add(ad.get("authority_id").toString());
				operate.add(ad.get("operate").toString());
			}
			JSONArray uRAProvider = JSONArray.parseArray(o.get("uRAProvider").toString());
			// JSONObject uRAProvider =
			// JSONObject.parseObject(o.get("uRAProvider").toString());
			for (int k = 0; k < uRAProvider.size(); k++) {
				JSONObject ao = JSONObject.parseObject(uRAProvider.get(k).toString());
				String judge = ao.get("status").toString();
				boolean judge_boo;
				if (judge.equals("true")) {
					judge_boo = true;
				} else {
					judge_boo = false;
				}
				status.add(judge_boo);
			}
			role_name = role.get("role_name").toString();
			// operate.add(authority.get("operate").toString());
		}

		for (int i = 0; i < operate.size(); i++) {
			obj = new JSONObject();
			obj.put("authority_id", authority_id.get(i));
			obj.put("operate", operate.get(i));
			obj.put("status", status.get(i));
			array.add(obj);
		}
		jsonobj.put("role_name", role_name);
		jsonobj.put("authority", array);
		return jsonobj;*/
	}

	@Override
	public Integer addUsers(UserEntity userEntity) {
		return userDao.addUsers(userEntity);
	}

	@Override
	public List<UserEntity> showUsers(String orgUuid) {
		return userDao.showUsers(orgUuid);
	}

	@Override
	public List<UserEntity> searchUser(UserEntity userEntity) {
		return userDao.searchUser(userEntity);
	}

	@Override
	public Integer updateUser(UserEntity userEntity) {
		return userDao.updateUser(userEntity);
	}

}
