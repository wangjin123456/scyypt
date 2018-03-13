package com.scyypt.service;

import java.util.Map;

public interface ICERequestService {

	
	public String doGetRequest(Map<String ,String > param, String name);
	
	public String doPostRequest(Map<String, String> param, String name);
	
	public String doPostRequestByAuthV1(Map<String, String> param, String name);
	
	
	public String doUploadFileRequest(Map<String, String> paramMap, String name);
	
}
