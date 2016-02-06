package com.demo.util;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
/*
 * RunWith是junit4的注解 意思是此类由这个注解的参数的类来主导单元测试 参数类型是Class
 * SpringJUnit4ClassRunner.class是SpringTest中的一个类 作用类似驱动 
 * 意思是由Spring Test来主导本次单元测试
 */
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class) 
public class BaseTest 
	extends AbstractTransactionalJUnit4SpringContextTests {

}
