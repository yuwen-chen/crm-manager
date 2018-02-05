package com.crm.manager.common.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;



public class MD5Utils {
	/**
	 * 对字符串进行Md5加密
	 * 
	 * @param input 原文
	 * @return md5后的密文
	 */
	public static String md5(String input) {
		byte[] code = null;
        try {
            code = MessageDigest.getInstance("md5").digest(input.getBytes());
        } catch (NoSuchAlgorithmException e) {
            code = input.getBytes();
        }
        BigInteger bi = new BigInteger(code);
        return bi.abs().toString(32).toUpperCase();
	}
	
	/**
	 * 对字符串进行Md5加密
	 * 
	 * @param input 原文
	 * @param salt 随机数
	 * @return string
	 */
	public static String generatePasswordMD5(String input, String salt) {
		if(StringUtils.isEmpty(salt)) {
			salt = "";
		}
		return md5(salt + md5(input));
	}
	
	
	/**
	 * 参数加密
	 * @param plainText参数
	 * @return
	 */
	public static String encryption(String plainText) {
		String re_md5 = new String();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}

			re_md5 = buf.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return re_md5;

	}
	
	
	public static int getInvestmentAmount(BigDecimal totalAmount){
		BigDecimal minAmount = BigDecimal.valueOf(1); 
		BigDecimal maxAmount = BigDecimal.valueOf(9999); 
		BigDecimal multipleNum = BigDecimal.valueOf(10000); 
		if(totalAmount.compareTo(minAmount) >= 0 && totalAmount.compareTo(maxAmount) <= 0){
			return 1;
		} else if(totalAmount.compareTo(maxAmount) >0 ){
			int multiple =totalAmount.divide(multipleNum).intValue();
			BigDecimal remainder =totalAmount.subtract(multipleNum.multiply(new BigDecimal(multiple)));
			if (multiple == 0){
				return 1;
			}
			if((remainder.compareTo(minAmount) >= 0 && remainder.compareTo(maxAmount) <= 0)){
				return multiple + 1;
			} else {
				return multiple;
			}
		}
		return 0;
	}

	
	public static void main(String[] args) {
		/*System.out.println(md5("111111"));;
		
		System.out.println(generatePasswordMD5("123456","111111"));
		
		String fileName = "/rqbmall-manager/images/goodsImg201801161057446044.jpg";
		String tmpImg = fileName.substring(fileName.lastIndexOf("/")+1);
		System.out.println("tmpImg========"+tmpImg);
		
		System.out.println(MD5Utils.encryption("12345"));*/
		/*List<String> list = new ArrayList<String>();
		 for(char a='G';a<='Z';a++){  
			 list.add(String.valueOf(a));
         }  
		 for(String a :list){
			 System.out.println(a);
		 }*/
		BigDecimal totalAmount = BigDecimal.valueOf(100001.999); 
		System.out.println(getInvestmentAmount(totalAmount));
		
		String str = String.format("%04d", 5);
		System.out.println(str);
	}
	
}
