package com.crm.manager.common.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class GenratorRedPackerUtil {
	
	/**
	 * 拆分红包算法
	 * @param maxNumber
	 * @param maxMoney
	 * @return
	 */
	private static List<Integer> genratorRedPacker(int maxNumber, int maxMoney){
		if(maxMoney<0 || maxNumber <0)
		return null;
		
		List<Integer> redPacketList = new ArrayList<Integer>();
		//最小获得微米数
		int min = 3;
		//最大微米，根据目前人数和总数取一个平均值
		int max;
		int i = 1;
		int money;
		while (i < maxNumber) {
			max = maxMoney -min * (maxNumber - i);
			int k = (int) ((maxNumber - i) / 2);
			if (maxNumber - i <= 2) {
				k = maxNumber -i;
			}
			max = max / k;
			money = (int) (min * 100 + Math.random() * (max * 100 - min * 100 +1));
			money = money /100;
			maxMoney = maxMoney - money;
			redPacketList.add(money);
			System.out.println("第" + i + "个红包产生，值为[" + money + "]微米,剩下：<" + maxMoney + ">微米");
			i++;
			if (i == maxNumber) {
				redPacketList.add(maxMoney);
				System.out.println("第" + i + "个红包产生，值为[" + maxMoney + "]微米,剩下：0微米");
			}
		}
		return redPacketList;
	}
	
	
	static void hb(double total,int num,double min){

		for(int i=1;i<num;i++){
			double safe_total=(total-(num-i)*min)/(num-i);
			double money=Math.random()*(safe_total-min)+min;
			BigDecimal money_bd=new BigDecimal(money);
			money=money_bd.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue(); 
			total=total-money;
			BigDecimal total_bd=new BigDecimal(total);
			total=total_bd.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
			System.out.println("第"+i+"个红包："+money+",余额为:"+total+"元");
		}
		System.out.println("第"+num+"个红包："+total+",余额为:0元");

	}

	public static void main(String[] args) {
		//GenratorRedPackerUtil.genratorRedPacker(10000, 500000);
		GenratorRedPackerUtil.hb(10, 6, 1);
	}

}
