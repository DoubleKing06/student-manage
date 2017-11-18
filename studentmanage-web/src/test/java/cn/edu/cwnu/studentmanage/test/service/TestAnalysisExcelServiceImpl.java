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
/*        //读取一个excel表的内容
        InputStream stream = new FileInputStream("C:/Users/DoubleKing/Desktop/west/1/学生基本情况表.xls");
        service.analysisStudentBasicInfo(stream);*/
        
/*        //读取一个excel表的内容
        InputStream stream1 = new FileInputStream("C:/Users/DoubleKing/Desktop/west/1/评优评奖情况.xls");
        service.analysisStudentPingjiang(stream1);*/
        
/*        //读取一个excel表的内容
        InputStream stream2 = new FileInputStream("C:/Users/DoubleKing/Desktop/west/1/学期成绩表.xls");
        service.analysisStudentChengji(stream2);*/
        
/*        //读取一个excel表的内容
        InputStream stream3 = new FileInputStream("C:/Users/DoubleKing/Desktop/west/1/资助情况表.xls");
        service.analysisStudentZizhu(stream3);*/
        
        //读取一个excel表的内容
        InputStream stream4 = new FileInputStream("C:/Users/DoubleKing/Desktop/west/1/学业状况表.xls");
        service.analysisStudentXueye(stream4);
        
    }
}