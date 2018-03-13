package com.scyypt.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.net.util.Base64;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.scyypt.bean.ItemNotesBean;
import com.scyypt.dao.ItemFollowUpDao;
import com.scyypt.dao.ItemNotesDao;
import com.scyypt.dao.ItemUserNotesDao;
import com.scyypt.entity.ItemFollowUpEntity;
import com.scyypt.entity.ItemNotesEntity;
import com.scyypt.entity.ItemUserNotesEntity;
import com.scyypt.service.ICEInterfaceService;
import com.scyypt.service.ItemFollowUpService;
import com.scyypt.util.Global;

@Service("itemFollowUpService")
public class ItemFollowUpServiceImpl implements ItemFollowUpService {


	@Autowired
	ItemFollowUpDao itemFollowUpDao;
	
	@Autowired
	ItemNotesDao itemNotesDao;
	
	@Autowired
	ItemUserNotesDao itemUserNotesDao;
	
	@Autowired
	ICEInterfaceService iceInterfaceService;
	
	private static Logger logger = Logger.getLogger(ItemFollowUpService.class.getName());

	
	private MultiValueMap<String, Socket> socketGroupMap = new LinkedMultiValueMap<String, Socket>();
	
 
	
	private String downloadFile(String accountUuid, String fileId) {
		
		String resultString = this.iceInterfaceService.downloadFile(accountUuid, fileId);
		
		String filePath = "";
		
		if(Global.isICESuccessed(resultString)) {
			
			filePath = Global.getICEContent(resultString);
		}
		
		return filePath;
	}

	@Override
	public String addData(String userId, String accountUuid, String name, String iconPath, Date date, Integer type, String itemId, String stage, String fileName, String data) {
		
		// 先保存项目跟进资源 文本、图片、文件
		ItemNotesEntity itemNotesEntity = new ItemNotesEntity();
		itemNotesEntity.setType(type);
		itemNotesEntity.setCreateTime(date);
		
		String message = "";

		if(type == Global.MESSAGE) {
			
			itemNotesEntity.setMessage(data);
			
			message = data;
			
		} else {
		
			try { 
				
				final Base64 base64 = new Base64();
				
				String timeName = "";
				
				int find = data.lastIndexOf(Global.BASE64_JPEG_DATA);
				if(find != -1) {
					
					type = Global.FILE_TYPE_PICTURE;
					
					data = data.replace(Global.BASE64_JPEG_DATA, "");
				}
				
				find = data.lastIndexOf(Global.BASE64_PDF_DATA);
				if(find != -1) {
					
					type = Global.FILE_TYPE_PDF;

					data = data.replace(Global.BASE64_PDF_DATA ,"");
				}
						
				byte[] datas = base64.decode(data.getBytes());
				
				int len = datas.length;
				
				for(int i=0; i< len; ++i) {
					
					if(datas[i] < 0) { //调整异常数据 

						datas[i] += 256; 
					}
				} 
						
				if(datas != null) {
				
					String dir = System.getProperty("user.dir");
					logger.info("当前目录:" + dir);
					
					// 取得文件后缀
					String fileExtend = "";

					if(fileName != null && !fileName.trim().equals("")) {
						
						fileExtend = fileName.substring(fileName.lastIndexOf(".") + 1);
					}
					
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmSSS");

					timeName  = dateFormat.format(new Date());
					
					timeName = dir + "/" +  timeName + "." + fileExtend;
					
					File file = new File(timeName);
				
					FileOutputStream fileOutputStream = new FileOutputStream(file);
					fileOutputStream.write(datas, 0, datas.length);
					fileOutputStream.flush();
					fileOutputStream.close();
				}

				String resultJson = this.iceInterfaceService.uploadFile(accountUuid, timeName);
				if(Global.isICESuccessed(resultJson)) {
					
					File file = new File(timeName);
					if(file.exists()) {
						
						file.deleteOnExit();
					}
					
					String fileId = Global.getICEContent(resultJson);

					itemNotesEntity.setPath(fileName);
					itemNotesEntity.setFileId(Integer.parseInt(fileId));
				
					message = this.downloadFile(accountUuid, fileId);
				}
				
			}catch(Exception e) {
				
				e.printStackTrace();
			}
		}
			
		int result = itemNotesDao.add(itemNotesEntity);
		
		int noteId = itemNotesEntity.getId();
		
		ItemFollowUpEntity itemFollowUpEntity = new ItemFollowUpEntity();
		
		itemFollowUpEntity.setItemId(Integer.parseInt(itemId));
		itemFollowUpEntity.setUserId(Integer.parseInt(userId));
		itemFollowUpEntity.setAccountUuid(accountUuid);
		itemFollowUpEntity.setStage(Integer.parseInt(stage));
		
		result = itemFollowUpDao.add(itemFollowUpEntity);
	
		logger.info("result:" + result);
		 
		ItemUserNotesEntity itemUserNotesEntity = new ItemUserNotesEntity();
		
		itemUserNotesEntity.setAccountUuid(accountUuid);
		itemUserNotesEntity.setId(Integer.parseInt(userId));
		itemUserNotesEntity.setNoteId(noteId);
		itemUserNotesEntity.setItemId(Integer.parseInt(itemId));
		
		
		result = itemUserNotesDao.add(itemUserNotesEntity);
	
		JSONObject object = new JSONObject();
		
		object.put("userId", userId);
		object.put("accountUuid", accountUuid);
		object.put("name", name);
		object.put("iconPath", iconPath);
		object.put("date", date);
		object.put("type", String.valueOf(type));
		object.put("itemId", itemId);
		object.put("stage", stage);
		object.put("message", message);
		object.put("fileName", fileName);
		
		String resultJson = Global.getWebSocketProtocol(Global.ICE_OK, Global.INTERFACE_NAME_SAVE_DATA, object);
		
		return resultJson;
	}
	

 
	
	
	
	@Override
	public String getDataList(String itemId, String stage, Integer index) {
	
		if(index == null) {
			index = 1;
		}
		
		int pageSize = 10;
		
		int start = (index - 1) * 10 + 1;
		
		List<ItemNotesBean> contentList = this.itemFollowUpDao.getDataListByStage(itemId, stage, start, pageSize);
		
		String temp = JSON.toJSONString(contentList);
		
		logger.info("List<ItemNotesBean> contentList=>JSONString:" + temp);
		
		JSONArray content = JSONArray.parseArray(temp);
		
		String resultJson = Global.getProtocol(Global.ICE_OK, "success", false, content);
		
		logger.info(resultJson);
		
		return resultJson;
	}
	 
	
	@Override
	public void doVisit(String key, Socket socket) {
		
		this.socketGroupMap.add(key, socket);
	}
	

	@Override
	public void doExit(String key, Socket socket) {
		
		this.socketGroupMap.remove(key, socket);
	}
	

	@Override
	public void doClose(String key, Socket socket) {
		
		this.socketGroupMap.remove(key, socket);
	}
	
	
	@Override
	public List<Socket> getSocketGroup(String key) {
		
		return this.socketGroupMap.get(key);
	}
	
	@Override
	public void removeSocket(String key, Socket socket) {
		
		this.socketGroupMap.remove(key, socket);
		
	}
	
	@Override
	public void removeSocket(String key) {
		
		this.socketGroupMap.remove(key);
	}
}
