package com.scyypt.service.impl;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.scyypt.service.ICERequestService;
import com.scyypt.util.Global;
import com.scyypt.util.Md5Util;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Service("ICERequestService")
public class ICERequestServiceImpl implements ICERequestService {

	private static Logger logger = Logger.getLogger(ICERequestService.class.getName());

	private static OkHttpClient client = new OkHttpClient();

	private URL url;
	
	private HttpURLConnection httpURLConnection;
	
	private String boundary = "--------httppost123";

    
	private Map<String, String> textParams = new HashMap<String, String>();
	private Map<String, File> fileparams = new HashMap<String, File>();
    
	private DataOutputStream dataOutputStream;
	private ByteArrayOutputStream byteArrayOutputStream;
    
	
	@Override
	public String doGetRequest(Map<String ,String > param, String name) {
		
		
		String apiURL = Global.getConfig(Global.ICE_SCYYPT_API_URL_KEY);
		String appId = Global.getConfig(Global.ICE_SCYYPT_APPID_KEY);
		
		String url = apiURL + name;
		
		// 封装 请求参数
		HashMap<String, String> paramMap = new HashMap<>();
		String ts = this.getTimestamp();
		
		paramMap.put("ts", ts);
		paramMap.put("sign", Md5Util.signMd5(ts));
		paramMap.put("appID", appId);
		
		paramMap.putAll(param);
				
		String jsonResult = this.sendGet(url, paramMap);
	
		return jsonResult;
	}
	
	
	@Override
	public String doPostRequest(Map<String, String> param, String name){
		
		String apiURL = Global.getConfig(Global.ICE_SCYYPT_API_URL_KEY);
		String appId = Global.getConfig(Global.ICE_SCYYPT_APPID_KEY);

		String url = apiURL + name;
		
		// 地址栏附加参数
		HashMap<String, String> baseParam = new HashMap<>();
		
		String ts = this.getTimestamp();
		baseParam.put("ts", ts);
		baseParam.put("sign", Md5Util.signMd5(ts));
		baseParam.put("appID", appId);
		
		// 发起post 获得数据数据
		String jsonResult = this.sendPost(url, baseParam, param);
		
		return jsonResult;
	}
	
	
	@Override
	public String doPostRequestByAuthV1(Map<String, String> param, String name) {
		
		String apiURL = Global.getConfig(Global.ICE_SCYYPT_API_URL_KEY);
		String appId = Global.getConfig(Global.ICE_SCYYPT_APPID_KEY);
		
		String token = Global.getConfig(Global.ICE_SCYYPT_TOKEN_KEY);
		
		
		String url = apiURL + name;
		
		
		
		// 地址栏附加参数
		HashMap<String, String> baseParam = new HashMap<>();
		
		String timestamp = this.getTimestamp();
		 
		
		baseParam.put("ts", timestamp);
		
		String sign = Md5Util.signMd5(appId, timestamp, token);
		
		baseParam.put("sign", sign);
		
		baseParam.put("appID", appId);
		
		
		
		// 发起post 获得数据数据
		String jsonResult = this.sendPost(url, baseParam, param);
		
		return jsonResult;
	}
	
	
	@Override
	public String doUploadFileRequest(Map<String, String> paramMap, String name) {
		
		String apiURL = Global.getConfig(Global.ICE_SCYYPT_API_URL_KEY);
		String appId = Global.getConfig(Global.ICE_SCYYPT_APPID_KEY);
		String appName = Global.getConfig(Global.ICE_SCYYPT_INFO_KEY);
		
		String ts = this.getTimestamp();
		String sign =  Md5Util.signMd5(ts);
		
		
		String token = paramMap.get("token").toString();
		String filePath = paramMap.get("filePath").toString();
		
		File file = new File(filePath);
		String fileName = "";
		String fileSize = "0";
		
		if(file != null)
		{
			fileName = file.getName();
			long size = file.length();
			
			fileSize = String.valueOf(size);
		}
		
		String account = paramMap.get("account").toString();
		String jsonResult = "";
		
		String param = "ts="+ts+"&sign=" + sign + "&appID=" + appId + "&access_token=" + token  + "&fileName=" + fileName + "&fileLength=" + fileSize + "&fileUploadAccount=" + account + "&fileUploadAppName=" + appName;

		logger.info("调用文件微服务参数串:" + param);
	
		String url = apiURL + name;
		
		url = url + "?" + param;
		
		try
		{
			
			this.setUrl(url);
			
			this.addFileParameter("file", file);
			
			byte[] bytes = this.send();
			
			jsonResult = new String(bytes, "UTF-8");
			
			logger.info("文件微服务对接返回信息:" + jsonResult);
		}
		catch (Exception  e) 
		{
			e.printStackTrace();
			
		}

		return jsonResult;
	}
	

	
	public String getTimestamp() {

		Date date = new Date();
		
		long unixTimestamp = date.getTime() / 1000;

		String timestamp = String.valueOf(unixTimestamp);
		
		logger.info("系统时间戳：" + timestamp);

		return timestamp;
	}
	
	
    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public String doGet(String url) {
        String result = "";
        BufferedReader in = null;
        try {

        	URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
			// Map<String, List<String>> map = connection.getHeaderFields();
			// // 遍历所有的响应头字段
			// for (String key : map.keySet()) {
			// System.out.println(key + "--->" + map.get(key));
			// }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
    
	
	
	/**
	 * 
	 * @Description
	 *
	 * @param url
	 *            请求地址 如有参数有 map封装 第二个参数
	 * @param paramMap
	 *            参数可为null
	 * @return
	 */
	public String sendGet(String url, Map<String, String> paramAddressBar) {

		String result = "";
		// 地址栏参数拼接
		String urlResult = paramJoin(url, paramAddressBar);
		logger.info(urlResult);
		Request request = new Request.Builder().url(urlResult).get().build();
		try {
			Response response = client.newCall(request).execute();
			if (response.isSuccessful()) {
				result = response.body().string();
			} else {
				logger.warn("Get 请求没有获取到数据");
			}
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("this Get 请求出错");
		} finally {
			client.newCall(request).cancel();
		}
		return result;

	}

	/**
	 * 
	 * @Description 发送Post请求
	 *
	 * @param url
	 *            地址
	 * @param paramAddressBar
	 *            待绑定的地址栏参数 如： ..?type=1&id=2
	 * @param paramMap
	 *            未在地址栏显示参数
	 * @return
	 */
	public String sendPost(String url, Map<String, String> paramAddressBar, Map<String, String> paramMap) {
		String result = "";
		String urlResult = paramJoin(url, paramAddressBar);
		logger.info("请求地址和参数："+urlResult);
		logger.info("参数："+paramMap);
		// 用于封装Form参数
		FormBody.Builder params = getFormParam(paramMap);
		Request request = new Request.Builder().url(urlResult).post(params.build()).build();
		try {
			Response response = client.newCall(request).execute();
			if (response.isSuccessful()) {
				result = response.body().string();
			} else {
				logger.warn("POST 请求没有返回数据");
			}
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("POST 请求出错");
		} finally {
			client.newCall(request).cancel();
		}
		return result;
	}

	/**
	 * 
	 * @Description 发送PUT请求
	 *
	 * @param url
	 *            地址
	 * @param paramAddressBar
	 *            待绑定的地址栏参数 如： ..?type=1&id=2
	 * @param paramMap
	 *            未在地址栏显示参数
	 * @return
	 */

	public String sendPut(String url, Map<String, String> paramAddressBar, Map<String, String> paramMap) {
		String result = "";
		String urlResult = paramJoin(url, paramAddressBar);
		// 用于封装Form参数
		FormBody.Builder params = getFormParam(paramMap);
		Request request = new Request.Builder().url(urlResult).put(params.build()).build();
		try {
			Response response = client.newCall(request).execute();
			if (response.isSuccessful()) {
				result = response.body().string();
			} else {
				logger.info("PUT 请求没有返回数据");
			}
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("PUT 请求出错");
		} finally {
			client.newCall(request).cancel();
		}
		return result;
	}

	/**
	 * 
	 * @Description 发送Delete请求
	 *
	 * @param url
	 *            地址
	 * @param paramMap
	 *            参数(可为空)
	 * @return
	 */
	public String sendDelete(String url, Map<String, String> paramMap) {
		String result = "";
		String urlResult = paramJoin(url, paramMap);
		logger.info("删除请求地址：" + urlResult);
		Request request = new Request.Builder().url(urlResult).delete().build();
		try {
			Response response = client.newCall(request).execute();
			if (response.isSuccessful()) {
				result = response.body().string();
			} else {
				logger.info("DELETE 请求没有获取到数据");
			}
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(" DELETE 请求出错");
		} finally {
			client.newCall(request).cancel();
		}
		return result;
	}

	/**
	 * 
	 * @Description
	 *
	 * @param url
	 *            请求地址
	 * @param paramMap
	 *            需要拼接在请求地址后面的参数： ..?name=1&password=2
	 * @return
	 */
	private String paramJoin(String url, Map<String, String> paramMap) {
		StringBuilder urlSB = new StringBuilder();
		// 判断是否 为空和有键值对
		if (paramMap != null && !paramMap.isEmpty()) {
			Set<String> keys = paramMap.keySet();
			urlSB.append(url);
			urlSB.append("?");
			// 判断是否是第一个参数，是否需要&连接参数
			Boolean first = true;
			for (String key : keys) {
				if (first) {
					urlSB.append(key);
					urlSB.append("=");
					urlSB.append(paramMap.get(key));
					first = false;
				} else {
					urlSB.append("&");
					urlSB.append(key);
					urlSB.append("=");
					urlSB.append(paramMap.get(key));
				}
			}
		} else {
			urlSB.append(url);
		}
		return urlSB.toString();

	}

	/**
	 * 
	 * @Description 用于创建 FormBody.Builder
	 *
	 * @param paramMap
	 * @return
	 */
	private FormBody.Builder getFormParam(Map<String, String> paramMap) {
		FormBody.Builder params = new FormBody.Builder();
		
		if (null != paramMap && !paramMap.isEmpty()) {
			Set<String> keys = paramMap.keySet();
			for (String key : keys) {
				if (!"".equals(paramMap.get(key)) && null != paramMap.get(key)) {
					params.add(key, paramMap.get(key));
				}
			}
		}
	
		return params;
	}
	
	

    //重新设置要请求的服务器地址，即上传文件的地址。
    public void setUrl(String url) throws Exception {
        this.url = new URL(url);
    }

    //增加一个普通字符串数据到form表单数据中
    public void addTextParameter(String name, String value) {
        this.textParams.put(name, value);
    }

    //增加一个文件到form表单数据中
    public void addFileParameter(String name, File value) {
        this.fileparams.put(name, value);
    }

    // 清空所有已添加的form表单数据
    public void clearAllParameters() {
        
    		this.textParams.clear();
        this.fileparams.clear();
    }

    // 发送数据到服务器，返回一个字节包含服务器的返回结果的数组
    public byte[] send() throws Exception {
    	
    	
	    this.httpURLConnection = (HttpURLConnection) this.url.openConnection();
	    
	    this.httpURLConnection.setDoOutput(true);
	        
	    this.httpURLConnection.setUseCaches(false);
	        
	    this.httpURLConnection.setConnectTimeout(10000 * 6); //连接超时为10秒
	        
	    this.httpURLConnection.setRequestMethod("POST");
	        
	    this.httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
	    
        
        try {
            
	        	this.httpURLConnection.connect();
	        	
	        this.dataOutputStream = new DataOutputStream(this.httpURLConnection.getOutputStream());
	        
	        writeFileParams();
	        
	        writeStringParams();
	        
	        paramsEnd();
	        
	        InputStream in = this.httpURLConnection.getInputStream();
	        
	        this.byteArrayOutputStream = new ByteArrayOutputStream();
	        
	        int b;
	        
	        while ((b = in.read()) != -1) {
	            this.byteArrayOutputStream.write(b);
	        }
	        
	        this.httpURLConnection.disconnect();
	        
	        this.dataOutputStream.close();
	        
	        } catch (Exception e) {
	        	
	           e.printStackTrace();
	           
	           String error = e.getLocalizedMessage();
	           
	           System.out.println(error);
	           
	           return error.getBytes();
	        }
	        
	        return this.byteArrayOutputStream.toByteArray();
    }


    //普通字符串数据
    private void writeStringParams() throws Exception {
    	
        Set<String> keySet = textParams.keySet();
    
        for (Iterator<String> it = keySet.iterator(); it.hasNext(); ) {
            String name = it.next();
            String value = textParams.get(name);
            this.dataOutputStream.writeBytes("--" + boundary + "\r\n");
            this.dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"" + name
                    + "\"\r\n");
            this.dataOutputStream.writeBytes("\r\n");
            this.dataOutputStream.writeBytes(encode(value) + "\r\n");
        }
        
    }

    //文件数据
    private void writeFileParams() throws Exception {
        Set<String> keySet = fileparams.keySet();
        for (Iterator<String> it = keySet.iterator(); it.hasNext(); ) {
            String name = it.next();
            File value = fileparams.get(name);
            this.dataOutputStream.writeBytes("--" + boundary + "\r\n");
            this.dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"" + name
                    + "\"; filename=\"" + encode(value.getName()) + "\"\r\n");
            this.dataOutputStream.writeBytes("Content-Type: " + getContentType(value) + "\r\n");
            this.dataOutputStream.writeBytes("\r\n");
            this.dataOutputStream.write(getBytes(value));
            this.dataOutputStream.writeBytes("\r\n");
        }
    }

    //获取文件的上传类型，图片格式为image/png,image/jpg等。非图片为application/octet-stream
    private String getContentType(File f) throws Exception {

//			return "application/octet-stream";  // 此行不再细分是否为图片，全部作为application/octet-stream 类型
        ImageInputStream imagein = ImageIO.createImageInputStream(f);
        if (imagein == null) {
            return "application/octet-stream";
        }
        Iterator<ImageReader> it = ImageIO.getImageReaders(imagein);
        if (!it.hasNext()) {
            imagein.close();
            return "application/octet-stream";
        }
        imagein.close();
        return "image/" + it.next().getFormatName().toLowerCase();//将FormatName返回的值转换成小写，默认为大写

    }

    //把文件转换成字节数组
    private byte[] getBytes(File f) throws Exception {
        FileInputStream in = null;
        ByteArrayOutputStream out = null;
        try {
            in = new FileInputStream(f);
            out = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = in.read(b)) != -1) {
                out.write(b, 0, n);
            }
            return out.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    //添加结尾数据
    private void paramsEnd() throws Exception {
        this.dataOutputStream.writeBytes("--" + boundary + "--" + "\r\n");
        this.dataOutputStream.writeBytes("\r\n");
    }

    // 对包含中文的字符串进行转码，此为UTF-8。服务器那边要进行一次解码
    private String encode(String value) throws Exception {
        return URLEncoder.encode(value, "UTF-8");
    }

}
