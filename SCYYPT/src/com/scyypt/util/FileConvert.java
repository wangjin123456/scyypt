package com.scyypt.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;

import org.apache.log4j.Logger;

public class FileConvert {
	private static Logger logger = Logger.getLogger(ImageConvert.class.getName());
	 /**
	   * byte数组到本地文件
	   * @param data 文件的Byte字节数组
	   * @param path 保存文件的本地路径
	   * @return 1:失败  0:成功
	   */
	  public static int  byte2File(byte[] data,String path){
	    if(data.length<3||path.equals("")) return 1;
	    try{
	    FileOutputStream output = new FileOutputStream(new File(path));
	    output.write(data, 0, data.length);
	    output.close();
	    logger.info("Make Picture success,Please find file in " + path);
	    return 0;
	    } catch(Exception ex) {
	    	logger.info("Exception: " + ex);
	      ex.printStackTrace();
	      return 1;
	    }
	  }
	  /**
	   * 本地文件到byte数组
	   * @param path 文件的本地路径
	   * @return 文件的字节数组
	   */
	  public static byte[] file2byte(String path){
	    byte[] data = null;
	    FileInputStream input = null;
	    try {
	      input = new FileInputStream(new File(path));
	      ByteArrayOutputStream output = new ByteArrayOutputStream();
	      byte[] buf = new byte[1024];
	      int numBytesRead = 0;
	      while ((numBytesRead = input.read(buf)) != -1) {
	      output.write(buf, 0, numBytesRead);
	      }
	      data = output.toByteArray();
	      output.close();
	      input.close();
	    }
	    catch (FileNotFoundException ex1) {
	      ex1.printStackTrace();
	    }
	    catch (IOException ex1) {
	      ex1.printStackTrace();
	    }
	    return data;
	  }
}
