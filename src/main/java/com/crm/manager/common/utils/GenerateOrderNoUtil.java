package com.crm.manager.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * 生成商户订单号
 * 
 * @author fan
 * @version 1.0, 2017-12-7
 * @since 1.0
 */
public class GenerateOrderNoUtil {

	/**
	 * 生成商户订单号
	 *
	 * @return
	 */
	public static String generate(){
		SimpleDateFormat formatter = new SimpleDateFormat("yyMMdd");
		String date=formatter.format(new Date());
		int first = new Random(10).nextInt(8) + 1;
//        int hashCodeV = UUID.randomUUID().toString().hashCode();
//        if (hashCodeV < 0) {
//            hashCodeV = -hashCodeV;
//        }
		long uuid = UUID.randomUUID().getMostSignificantBits();
		String code = "01";//服务器代码
		String biz ="02";//业务类型
        String ordeNo=first + code+biz+String.format("%019d", Math.abs(uuid));
		return date+ordeNo;
	}
	
	public static void main(String[]args){
		System.out.println(generate());
	}
}
