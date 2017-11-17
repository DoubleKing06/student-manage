package cn.edu.cwnu.studentmanage.test.service;

/**
* BBD Service Inc
* All Rights Reserved @2017
*/

import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @author YuBo
 * @version $Id: BasicTest.java, v0.1 2017/11/15 22:30 YuBo Exp $$
 */
public class BasicTest {

    protected ApplicationContext ctx;

    @Before
    public void init() {
        ctx = new FileSystemXmlApplicationContext("classpath:spring-config-test.xml");
    }
}