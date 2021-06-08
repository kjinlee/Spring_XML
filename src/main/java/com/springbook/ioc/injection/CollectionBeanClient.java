package com.springbook.ioc.injection;

import java.util.Properties;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class CollectionBeanClient {
	public static void main(String[] args) {
		AbstractApplicationContext factory = new GenericXmlApplicationContext("applicationContext.xml");

		CollectionBean bean = (CollectionBean) factory.getBean("collectionBean");
		Properties addressList = bean.getAddressList();
		
		// properties 는 value와 key 값에 대한 자료형을 확정하지 않았기 때문에
		// toString()을 통해 형변환 해주었다.
		String address = addressList.get("이경진").toString();
		String address2 = addressList.get("비트캠프").toString();
		
		System.out.println("이경진 주소 :"+address);
		System.out.println("비트캠프 주소 :"+address2);
		
		factory.close();
	}
}
