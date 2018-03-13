package com.scyypt.service;

import java.net.Socket;
import java.util.Date;
import java.util.List;

public interface ItemFollowUpService {
 
	public String addData(String userId, String accountUuid, String name, String iconPath, Date date, Integer type, String itemId, String stage, String fileName, String data);
	
	public String getDataList(String itemId, String stage, Integer index);
	
	public void doVisit(String key, Socket socket);
	
	public void doExit(String key, Socket socket);
	
	public void doClose(String key, Socket socket);
	
	public List<Socket> getSocketGroup(String key);
	
	public void removeSocket(String key, Socket socket);
	
	public void removeSocket(String key);
	
}
