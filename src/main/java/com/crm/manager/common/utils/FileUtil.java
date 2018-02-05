package com.crm.manager.common.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.jadira.usertype.spi.utils.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);
	
	/**
     * 上传文件
     *
     *@param file
     *			要上传的文件  
     * @param filePath
     *          要上传的路径
     * @param filePath
     * 			要上传的文件名
     */
	public static void uploadFile(byte[] file, String filePath, String fileName){ 
        if(StringUtils.isEmpty(fileName)){
        	return;
        }
		File targetFile = new File(filePath);  
        if(!targetFile.exists()){    
            targetFile.mkdirs();    
        }       
        FileOutputStream out = null;
		try {
			out = new FileOutputStream(filePath+fileName);
			out.write(file);
			out.flush();
		} catch (Exception e) {
			logger.error("图片上传异常！", e);
		} finally {
			if(out != null){
				try {
					out.close();
				} catch (IOException e) {
					//不需要处理
				}
			}
		}
    }
	
	/**
     * 删除单个文件
     *
     * @param fileName
     *            要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static void deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
        	file.delete();
        }
    }
    
    /**
     * url转文件路径
     * @param urlStr
     * @return
     */
    public static String  urlToFilePath(String urlStr){
    	String filePath = "";
    	if(StringUtils.isEmpty(urlStr)){
    		return filePath;
    	}
    	URL url = null;
    	try {
    		url = new URL(urlStr);
    		filePath = new File(url.getFile()).getAbsolutePath()+System.getProperty("file.separator");
    	} catch (MalformedURLException e) {
    		logger.error("URL转File异常", e);
    	}
    	return filePath;
    }
    
    /**
     * 重命名文件
     * @param prefix 前缀
     * @param fileName 文件名
     * @return 重命名后的文件名
     */
    public static String renameFileName(String  prefix, String fileName) {
    	// 当前时间字符串
        String formatDate = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()); 
        //随机数
        int random = new Random().nextInt(10000);
        // 文件后缀
        String extension = fileName.substring(fileName.lastIndexOf(".")); 
        if(StringUtils.isNotEmpty(prefix)){
        	return prefix + formatDate + random + extension;
        }
        return formatDate + random + extension;
    }

}
