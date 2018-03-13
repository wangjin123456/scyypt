package com.scyypt.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.scyypt.service.ItemFollowUpService;



// no.1 注册身份，使用itemId 这样可能进行分组广播回包信息   client
/**
{
	    "code": "0",
	    "type": "1",
	    "message": "visit",
	    "content": {
	        "itemId": "1"
	    }
	}

*/
//no.1 注册身份，使用itemId 这样可能进行分组广播回包信息 server
/**
{
	    "code": "0",
	    "type": "1",
	    "message": "success",
	    "content": {
	        "itemId": "1"
	    }
	}
*/

//no.2 离开访问  client
/**
{
	    "code": "0",
	    "type": "1",
	    "message": "exit",
	    "content": {
	        "itemId": "1"
	    }
	}

*/

//no.2 离开访问 server
/**
{
	    "code": "0",
	    "type": "1",
	    "message": "success",
	    "content": {
	        "itemId": "1"
	    }
	}
*/

//no.3 关闭连接  client
/**
{
	    "code": "0",
	    "type": "1",
	    "message": "close",
	    "content": {
	        "itemId": "1"
	    }
	}
*/

//no.3 关闭连接 server
/**
{
	    "code": "0",
	    "type": "1",
	    "message": "success",
	    "content": {
	        "itemId": "1"
	    }
	}
*/

// no.4.0 保存一条信息 client
/**
 {
	    "code": "0",
	    "type": "1",
	    "message": "saveData",
	    "content": {
	        "itemId": "1",
	        "userId": "1",
	        "accountUuid" : "akasdfklasfdkljasfjasfdkljafsd",
	        "type": "1",
	        "date" : "2018-01-21 10:30:30",
	        "message": "aaklsfakl;sfad;kldsafk;lasf;klafk;lasf"
	    }
	}

 */

// no.4.0 保存一条纯文本信息 server
/**
{
	    "code": "0",
	    "type": "1",
	    "message": "success",
	    "content": {
	        "itemId": "1",
	        "userId": "1",
	        "accountUuid" : "akasdfklasfdkljasfjasfdkljafsd",
	        "iconPath":"http://avatar.ice.colourlife.com/avatar?uid=oa_Name",
	        "type": "1",
	        "date" : "2018-01-21 10:30:30",
	        "message": "aaklsfakl;sfad;kldsafk;lasf;klafk;lasf"
	    }
	}
*/

// no.4.1 保存一条非文本信息 如果图片、文件只传路径即可以 client
/**
{
	    "code": "0",
	    "type": "1",
	    "message": "saveData",
	    "content": {
	        "itemId": "1",
	        "userId": "1",
	        "accountUuid" : "akasdfklasfdkljasfjasfdkljafsd",
	        "type": "2",
	        "date" : "2018-01-21 10:30:30",
	        "message": "c:\xxxx\xxxx\xxxx.xxx"
	    }
	}

*/

//no.4.1 保存一条非文本信息 如果图片、文件只传路径即可以 server
/**
{
	    "code": "0",
	    "type": "1",
	    "message": "success",
	    "content": {
	        "itemId": "1",
	        "userId": "1",
	        "accountUuid" : "akasdfklasfdkljasfjasfdkljafsd",
	        "iconPath":"http://avatar.ice.colourlife.com/avatar?uid=oa_Name",
	        "type": "2",
	        "date" : "2018-01-21 10:30:30",
	        "message": "htt://xxx.xxxx.xxx.xxx/xxxx/xxxx/xxxx.xxx"
	    }
	}
*/

//no.5 返回数据列表 client
/**
{
	    "code": "0",
	    "type": "1",
	    "message": "getDataList",
	    "content": {
	        "itemId": "1",					// 不能为空
	        "stage":"2"						// 项目跟进记录阶段
	        "accountUuid":"xxxxxxxxxxxxxxx", // 当前用户accountUuid
	        "date" : "",						// 可以为空 
	        "search":""						// 可以为空
	    }
	}
*/

//no.5 返回数据列表 server
/**
{
	    "code": "0",
	    "type": "1",
	    "message": "success",
	    "content": [{
	        "itemId": "1",
	        "userId": "1",
	        "accountUuid" : "akasdfklasfdkljasfjasfdkljafsd1",
	        "iconPath":"http://avatar.ice.colourlife.com/avatar?uid=oa_Name",
	        "type": "2",
	        "date" : "2018-01-21 10:30:30",
	        "message": "c:\xxxx\xxxx\xxxx.xxx"
	    },
	    {
	        "itemId": "1",
	        "userId": "2",
	        "accountUuid" : "akasdfklasfdkljasfjasfdkljafsd2",
	        "iconPath":"http://avatar.ice.colourlife.com/avatar?uid=oa_Name",
	        "type": "2",
	        "date" : "2018-01-21 10:30:30",
	        "message": "c:\xxxx\xxxx\xxxx.xxx"
	    },
	    {
	        "itemId": "1",
	        "userId": "3",
	        "accountUuid" : "akasdfklasfdkljasfjasfdkljafsd3",
	        "iconPath":"http://avatar.ice.colourlife.com/avatar?uid=oa_Name",
	        "type": "2",
	        "date" : "2018-01-21 10:30:30",
	        "message": "c:\xxxx\xxxx\xxxx.xxx"
	    }
	    
	    ]
	}
*/


class Working implements Runnable {

	private static Logger logger = Logger.getLogger(Working.class.getName());
	
	private Socket socket = null;
	
	private static ItemFollowUpService itemFollowUpService;
	
	public Working() {
		
	}
	
	public Working(Socket socket) {
		
		this.setSocket(socket);
		
		logger.info("当前socket:" + socket.toString() + "进行执行任务状态!");
	}
	
	public void setSocket(Socket socket) {
		
		this.socket = socket;
	}
	
	public Socket getSocket() {
		
		return socket;
	}

	static public void setItemFollowUpService(ItemFollowUpService itemFollowUpService){
    	
		Working.itemFollowUpService = itemFollowUpService;
	}
	

	public void run() {
		
		this.revcData();
		
	}
	


//	
//	private void revcData() {
//		
//		InputStream inputStream = null;
//		
//		String message = "";
//		 
//		 try {
//			 
//			 inputStream = this.socket.getInputStream();
//			
//			 int max = 0;
//			 
//			 byte [] first = new byte[1];
//			 
//			 while(true) {
//				 
//				 inputStream.read(first, 0, 1);
//				 
//				 try {
//					 
//					 Thread.sleep(200);
//					 
//				 }catch(Exception e) {
//					 
//					 e.printStackTrace();
//				 }
//				 
//				 max = inputStream.available();
//				 
//		      	 logger.info("inputStream.available() 数据包大小:" + max);
//	      	 	
//				 if(max == 0) {
//
//					 continue;
//				 }
//				 
//				 byte[] buffer = new byte[max + 1];
//				 
//				 System.arraycopy(first, 0, buffer,0, 1);
//				 
//				 inputStream.read(buffer, 1, max);
//				 
//			 	 // 获取 FIN
//				 //获取到第一个字节的数值  
//          	 	 int fin = (buffer[0] & 0x80) >> 7;
//          	 	 
//          	 	 int rsv1 = buffer[0] & 0x20;
//          	 	 int rsv2 = buffer[0] & 0x10;
//          	 	 int rsv3 = buffer[0] & 0x08;
//          	 	 
//          	 	 int opCode = buffer[0] & 0x0F;
//          	 	 
//          	 	 int mask = (buffer[1] & 0x80) >> 7;
//          	 	 
//          	 	 int payloadSize = buffer[1] & 0x7F;
//          	 
//          	 	 int length = payloadSize;
//          	 	 
//          	 	 int index = 0;
//          	 	 if(length <= 125) {
//          	 		 index = 2;
//          	 	 }
//          	 	 
//          	 	 if (payloadSize == 126) {
//	                   
//	                   int t1 = buffer[2] & 0xFF;
//	                   int t2 = buffer[3] & 0xFF;
//	                   int t3 = t1 << 8;
//	                   length = (t3 | t2);
//	                   
//  	                 logger.info("payloadSize 126 数据包大小:" + length);
//  	                 
//  	                   index = 4;
//          	 	 }
//          	 	 
//          	 	 if(payloadSize == 127) {
//          	 		 
//	                long t1 = buffer[2] & 0xFFFF;
//	                long t2 = buffer[3] & 0xFFFF;
//	                long t3 = buffer[4] & 0xFFFFFFFF;
//	                long t4 = buffer[5] & 0xFFFFFFFF;
//	                long t5 = buffer[6] & 0xFFFFFFFF;
//	                long t6 = buffer[7] & 0xFFFFFFFF;
//	                long t7 = buffer[8] & 0xFFFFFFFF;
//	                long t8 = buffer[9] & 0xFFFFFFFF;
//	                
//	                index = 10;
//          	 	 }
//          	 	 
//          	 	 byte[] maskKey = new byte[4];
//          	 	 
//          	 	 // 获取掩码
//          	 	 System.arraycopy(buffer, index, maskKey, 0, 4);
//          	 	 
//          	 	 index += 4;
//
//          	 	ByteBuffer byteBuffer = ByteBuffer.allocate(length + 30);
//          	 	
//          	 	int a = max - index;
//          	 	
//          	 	 for(int i = 0; i <=  a; i ++) {
//          	 		 
//          	 		byte data = buffer[i + index];
//          	 		
//          	 		data = (byte) (data ^ maskKey[i % 4]);
//          	 		
//          	 		byteBuffer.put(data);
//          	 		 
//          	 	 }
//          	 	 
//          	 	byteBuffer.flip();
//              
//	              byte [] data = byteBuffer.array();
//	              
//	              message = new String(data);
//	              
//	              logger.info(message);
//	              
//	              boolean isClose = this.doWork(message);
//	              if(isClose == true) {
//	             	 	break;
//	              }
//	          	 	
//          	 
//				 max = 0;
//			 }
//			 
//		 } catch(IOException e) {
//			 
//			 e.printStackTrace();
//			 
//			 String error = e.getLocalizedMessage();
//			 
//			 logger.info("读取客户数据产生异常，详细信息为:" + error);
//			 
//		 } finally {
//			 
////			 try {
////	
////				 this.socket.isInputShutdown();
////				 
////			 } catch(Exception e) {
////				 
////				 e.printStackTrace();
////			 }
//		 }
//	}
	

	
	
	private void revcData() {
		
		InputStream inputStream = null;
		
		String message = "";
		 
		 try {
			 
			  boolean isClosed = this.socket.isClosed();
			  if(isClosed == true) {
			
				  logger.info("revcData 任务中 this.socket.isClosed() == true, 取读任务不能继续进行");
				  
				  return;
			  }
			  
			 inputStream = this.socket.getInputStream();
			 if(inputStream == null) {
				 
				 logger.info("revcData 任务中 this.socket.getInputStream() == null, 取读任务不能继续进行");
				 
				 return;
			 }
			 
			 
			 //接收数据  
             byte[] first = new byte[1];

             while(true) {
            	 
                	 	//这里会阻塞
                 int read = inputStream.read(first, 0, 1);
                  
                 
         	 	int max = inputStream.available();
         	 	// logger.info("数据包大小:" + max);
        	 	
                 //读取第一个字节是否有值,开始接收数据
                 if(read > 0){
                 
                	 	//让byte和十六进制做与运算（二进制也就是11111111
                     //获取到第一个字节的数值  
                	 	int b = first[0] & 0xFF;

                	 	//1为字符数据，8为关闭socket（只要低四位的值判断
                     byte opCode = (byte) (b & 0x0F);
                    
                     if(opCode == 8){
                    	 
                         this.socket.getOutputStream().close();
                         
                         logger.info("revcData->while(true) opCode == 8 执行行了  this.socket.getOutputStream().close(); 当前socket:" + this.socket.toString() +" 取读任务不能继续进行 从循环break!");
                         
                         break;
                     }
                     
                     b = inputStream.read();
                     
                     //只能描述127
                     int payloadLength = b & 0x7F;
                     if (payloadLength == 126) {
                         byte[] extended = new byte[2];
                         inputStream.read(extended, 0, 2);

  	                   int t1 = extended[0] & 0xFF;
  	                   int t2 = extended[1] & 0xFF;
  	                   
  	                   int t3 = t1 << 8;
  	                 
  	                   payloadLength = (t3 | t2);
  	                   
    	                 logger.info("payloadSize 126 数据包大小:" + payloadLength);

                     } else if (payloadLength == 127) {
                         byte[] extended = new byte[8];  
                         inputStream.read(extended, 0, 8);
                         int shift = 0;
                         payloadLength = 0;
                         for (int i = extended.length - 1; i >= 0; i--) {
                             payloadLength = payloadLength + ((extended[i] & 0xFF) << shift);
                             shift += 8;
                         }
                         
                         logger.info("payloadSize 127 数据包大小:" + payloadLength);
                     }
                     //掩码  
                     byte[] mask = new byte[4];
                     inputStream.read(mask, 0, 4);
                     
                     int readThisFragment = 1;
                     
                     ByteBuffer byteBuf = ByteBuffer.allocate(payloadLength + 30);
                     
                     while(payloadLength > 0){  
                          int masked = inputStream.read();
                          masked = masked ^ (mask[(int) ((readThisFragment - 1) % 4)] & 0xFF);  
                          byteBuf.put((byte) masked);  
                          payloadLength--;  
                          readThisFragment++;
                     }  
                     
                     byteBuf.flip();
                     
                     byte [] data = byteBuf.array();
                     
                     message = new String(data);
                     
                     logger.info(message);
                     
                     boolean isClose = this.doWork(message);
                     if(isClose == true) {
                    	 	break;
                     }
                 }
             }

		 } catch(IOException e) {
			 
			 e.printStackTrace();
			 
			 String error = e.getLocalizedMessage();
			 
			 logger.info("读取客户数据产生异常，详细信息为:" + error);
			 
		 } finally {
			 
//			 try {
//	
//				 this.socket.isInputShutdown();
//				 
//			 } catch(Exception e) {
//				 
//				 e.printStackTrace();
//			 }
		 }
	}
	

	
	private boolean doWork(String data) {
		
		boolean isClose = false;
		
		if(data == null || data.trim().equals("")) {
			
			logger.info("doWork->执行任务的数据为空，不能继续执行...");
			
			isClose = true;
			
			return isClose;
		}
		
		data = data.trim();
		
		logger.info(data);
		
		JSONObject jsonObject = JSONObject.parseObject(data);
		
		boolean ok = Global.isICESuccessed(jsonObject);
		if(ok) {
		
			JSONObject object = Global.getIceProtocolObject(data);
			
			JSONObject msgObject = object.getJSONObject("message");

			String message = msgObject.getString("info");
			
			switch(message) {
			
				case Global.INTERFACE_NAME_VISIT: {
					
					String content = object.getString("content");
					
					String itemId = content;
					
					logger.info("INTERFACE_NAME_VISIT  访问id:" + itemId + " 用于socket:" + this.socket.toString() +" 广播标识!");
					
					this.itemFollowUpService.doVisit(itemId, this.socket);
					
					String resultJson = Global.getProtocol(itemId);
					
					this.sendData(resultJson);
				}
				
				break;
				
				
			
				case Global.INTERFACE_NAME_EXIT: {
					
					String content = object.getString("content");
					
					String itemId = content;
					
					this.itemFollowUpService.doExit(itemId, this.socket);
					
					String resultJson = Global.getProtocol(itemId);
					
					this.sendData(resultJson);
				}
				
				break;
				

				
				case Global.INTERFACE_NAME_CLOSE: {
					
					String content = object.getString("content");
					
					String itemId = content;
					
					this.itemFollowUpService.doClose(itemId, this.socket);
					
//					try {
//						
//						this.socket.close();
//					}
//					catch(Exception e) {
//						
//						e.printStackTrace();
//					}
					
					isClose = true;
					
				}
				
				break;
				
				
				
				
				case Global.INTERFACE_NAME_SAVE_DATA: {
					
					JSONObject content = object.getJSONObject("content");
					Integer type =  content.getInteger(Global.TYPE);
					String itemId = content.getString(Global.ITEM_ID);
					String userId = content.getString(Global.USER_ID);
					String accountUuid = content.getString(Global.ACCOUNT_UUID);
					String stage = content.getString(Global.STAGE);
					Date date = content.getDate(Global.DATE);
					String fileName = content.getString(Global.FILE_NAME);
					String info = content.getString(Global.MESSAGE_KEY);
					
					String user = Global.getUserName(accountUuid);
					String name = Global.getName(accountUuid);
					
					String iconPath = Global.getUserIcon(user);
					
					String jsonString = this.itemFollowUpService.addData(userId, accountUuid, name, iconPath, date, type, itemId, stage, fileName, info);
					
					logger.info("ItemFollowupThread->itemFollowUpService->addData:" + jsonString);
					
					this.castData(itemId, jsonString);
				}
				
				break;
				
				
				
				case Global.INTERFACE_NAME_GET_DATA_LIST : {
					
					JSONObject content = object.getJSONObject("content");
					
					String itemId = content.getString(Global.ITEM_ID);
					String accountUuid = content.getString(Global.ACCOUNT_UUID);
					String stage = content.getString(Global.STAGE);
					Integer index = content.getInteger("pageIndex");
					
					String resultString = this.itemFollowUpService.getDataList(itemId, stage, index);
					
					this.sendData(this.socket, resultString);
				}
				
				break;
				
				
				default: {
					
				}
			}
		}
		
		return isClose;
	}
	
	// 当前socket回包
	private boolean sendData(String data) {
		
		 return this.sendData(this.socket, data);
	}
	
	
	// 广播发送
	private void castData(String key, String data) {
	
		logger.info("广播消息 Key: " + key +" 内容: " + data);
		
		List<Socket> list = this.itemFollowUpService.getSocketGroup(key);
		
		if(list != null) {
			
			for(Socket socket : list) {
				
				if(socket != null && !socket.isClosed()) {
					
					logger.info("广播消息 Socket:" + socket + "Data:" + data);
					
					this.sendData(socket, data);
				}
			}
		} else {
			
			logger.info("广播消息 Key: " + key +" 对应的socket没有找到，不能执行广播操作!");
		}
	}
	
	private boolean sendData(Socket socket, String data) {
		
		boolean ok = true;
		
		OutputStream out = null;
		 
		 try {
			 
			out = socket.getOutputStream();
			 
			 byte[] buffer = data.getBytes();
			 
			 int len = buffer.length;
			 
			 ByteBuffer byteBuf = ByteBuffer.allocate(len);
			 
			 byteBuf.put(buffer);
			 
            int first = 0x00;
            
            first = first + 0x80;  
            first = first + 0x1;
            
            out.write(first);  
            
            if (byteBuf.limit() < 126) { 
            	
                out.write(byteBuf.limit());

            } else if (byteBuf.limit() < 65536) {
            	
	            out.write(126);  
	            out.write(byteBuf.limit() >>> 8);  
	            out.write(byteBuf.limit() & 0xFF);  
            
            } else {  
            
	            	// Will never be more than 2^31-1  
	            out.write(127);
	            out.write(0);
	            out.write(0);
	            out.write(0);
	            out.write(0);
	            out.write(byteBuf.limit() >>> 24);
	            out.write(byteBuf.limit() >>> 16); 
	            out.write(byteBuf.limit() >>> 8);
	            out.write(byteBuf.limit() & 0xFF);
            }
            
            out.write(byteBuf.array(), 0, byteBuf.limit());
            
            out.flush();

		 } catch(IOException e) {
			 
			 ok = false;
			 
			 e.printStackTrace();
			 
			 String error = e.getLocalizedMessage();
			 
			 logger.info("读取客户数据产生异常，详细信息为:" + error);
			 
		 } finally {
			 
//			 try {
//				 socket.shutdownOutput();
//				 
//			 } catch(IOException e) {
//				 
//				 e.printStackTrace();
//				 
//				 String error = "Socket 关闭输出流产生异常，详细为: " + e.getLocalizedMessage();
//				 
//				 logger.info(error);
//			 }
		 }
		
		return ok;
	}
}


@Component
public class ItemFollowupThread extends Thread {

	private static Logger logger = Logger.getLogger(ItemFollowupThread.class.getName());
	
	private ExecutorService executorService = null;
	
	// 私有静态成员变量，存储唯一实例
	private static ItemFollowupThread itemFollowupThread = new ItemFollowupThread();
    
	private boolean isRun = false;
	
	
	private ServerSocket serverSocket = null;
	
	 // 私有构造函数
		private ItemFollowupThread(){
			
			logger.info("跟进Websocket服务线程(ItemFollowupThread) 执行获取线程池对象...");
			
			if(this.executorService == null) {
				
				this.executorService = Global.getPool("ItemFollowupThread");
			}
		}
		
	    // 公有静态成员方法，返回唯一实例
	    public static ItemFollowupThread getInstance(){

	    		return itemFollowupThread;
	    }
	    
		@Autowired
		public void setItemFollowupService(ItemFollowUpService itemFollowUpService) {
			
			Working.setItemFollowUpService(itemFollowUpService);
		}
		
		
		
		private boolean readHandsProtocol(Socket newConnection) {
			
			InputStream inputStream = null;

			String headerInfo = "";
			
			boolean isOk = true;
			
			try {
				
				inputStream = newConnection.getInputStream();
				 
				byte[] buffer = new byte[1024];

				int len = inputStream.read(buffer, 0, 1024);
	        
		        byte[] temp = new byte[len];
		        
		        System.arraycopy(buffer, 0, temp, 0, len);  
		
		        headerInfo = new String(temp);
			       
			    logger.info("=========================WebSocket握手头====================");
				logger.info(headerInfo);
				logger.info("=========================输出结束====================");
				
				String secWebsocketValue = "";
				
				String ls[] = headerInfo.split("\r\n");
				
				for(String line : ls) {
					
					String a[] = line.split(":");
					
					if(a.length == 2) {
						String key = a[0];
						String value = a[1];
						if(key.equals("Sec-WebSocket-Key")) {
							
							secWebsocketValue = value;
						}
					}
				}
				
				secWebsocketValue = secWebsocketValue.trim();
				logger.info("secWebsocketValue:" + secWebsocketValue);
				
				if(secWebsocketValue == null || secWebsocketValue.equals("")) {
					
					logger.info("Sec-WebSocket-Key 取值为空，不能继续执行服务器端握手协议...");
					
					isOk = false;
				}
				
				OutputStream socketOut = newConnection.getOutputStream();
				
				PrintWriter printWriter =  new PrintWriter(socketOut, true);
				
			   secWebsocketValue += "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";  
		        //通过SHA-1算法进行更新  
		        MessageDigest md = MessageDigest.getInstance("SHA-1");
		        
		        md.update(secWebsocketValue.getBytes("utf-8"), 0, secWebsocketValue.length());
		        byte[] sha1Hash = md.digest();
		        
		        //进行Base64加密  
		        sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();    
		        secWebsocketValue = encoder.encode(sha1Hash);    
		        
		        //服务器端返回输出内容  
		        printWriter.println("HTTP/1.1 101 Switching Protocols");  
		        printWriter.println("Upgrade: websocket");  
		        printWriter.println("Connection: Upgrade");  
		        printWriter.println("Sec-WebSocket-Accept: " + secWebsocketValue);  
		        printWriter.println();  
		        printWriter.flush();
		        
				logger.info("执行服务器端握手协议完成...");
				
			}catch(Exception e) {
				
				isOk = false;
				e.printStackTrace();
				
				String error = "读取与写回握手协议生产异常，详细信息为:" + e.getLocalizedMessage();
				
				logger.info(error);
			}
		
			return isOk;
		}

	    
	    private void doSocket() {
	    	
	    		try {
	    			
	    			this.serverSocket = new ServerSocket(Global.PORT);
	    			
	    			if(this.serverSocket != null) {
	    			
	    				logger.info("接受客户端连接的socket创建成功!");
	    			}
	    		
	    		} catch(Exception e) {
	    			
	    			e.printStackTrace();

	    			String message = e.getLocalizedMessage();
	    			logger.info("创建跟进服务器Socket产生异常，详细信息:" + message);
	    		}
	    		
	    		while(this.isRun) {
	    			
	    			 String ip = Global.getServerIp();

	    			 int port = this.serverSocket.getLocalPort();
	    			 	    			  
	    			logger.info("Websocket 服务器进入等待客户端连接线程，IP:" + ip + ":" + port + " 接受连接中...!");
	    			
	    			try {
	    				
		    				Socket newConnection = this.serverSocket.accept();
		    				
		    				if(newConnection != null) {
		    					
		    					logger.info("客户端连接的socket成功,详细信息:" + newConnection.toString());
		    				}
	    			
		    				// 握手操作
		    				boolean ok = this.readHandsProtocol(newConnection);
		    				
		    				if(ok == true) {
		    				
		    					logger.info("Websocket 服务器主线完成一次接受客户端连接请求!");
			    				
			    				this.executorService.execute(new Working(newConnection));
		    				}
		    				
	    			} catch(Exception e) {
	    				
	    				String message = e.getLocalizedMessage();
	    				
	    				logger.info("跟进服务器等待客户端连接产生异常，详细信息：" + message);
	    				
	    				e.printStackTrace();
	    				
	    				  if (serverSocket != null) {  
	    		                
	    					  try {  
	    		                		serverSocket.close();
	    		                
	    		                } catch (IOException ex) {
	    		                	
	    		                    ex.printStackTrace();
	    		                }  
	    		           }
	    			}
	    		}
	    		
	    }
	    
	    public void runStart() {
	    	
	    		this.isRun = true;
	    		
	    		this.start();
	    }
	    
	    public void runStop() {
	    		
	    		this.isRun = false;
	    }
	    
		public void run() {
			
			logger.info("Websocket 服务器主线开始执行...");
			
			this.doSocket();
		}
}
