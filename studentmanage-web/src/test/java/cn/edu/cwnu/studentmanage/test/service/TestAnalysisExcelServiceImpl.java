/**
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package cn.edu.cwnu.studentmanage.test.service;

import cn.edu.cwnu.studentmanage.service.AnalysisExcelService;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author YuBo
 * @version $Id: TestAnalysisExcelServiceImpl.java, v0.1 2017/11/15 22:27 YuBo Exp $$
 */
public class TestAnalysisExcelServiceImpl extends BasicTest {

    @Test
    public void testAnalysisStudentBasicInfo() throws IOException {
        AnalysisExcelService service = ctx.getBean(AnalysisExcelService.class);
        //读取一个excel表的内容
        InputStream stream = new FileInputStream("E:/个人/需求/学生基本情况表.xls");
        service.analysisStudentBasicInfo(stream);
    }
}