package cn.edu.cwnu.studentmanage.service.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.junit.Test;
import org.springframework.stereotype.Service;


import cn.edu.cwnu.studentmanage.dao.base.BaseDao;
import cn.edu.cwnu.studentmanage.domain.StudentBasicInfo;
import cn.edu.cwnu.studentmanage.service.AnalysisExcelService;
import cn.edu.cwnu.studentmanage.service.StudentBasicInfoService;
import cn.edu.cwnu.studentmanage.service.base.BaseServiceImpl;

@Service("AnalysisExcelService")
public class AnalysisExcelServiceImpl extends BaseServiceImpl<StudentBasicInfo,Integer> implements AnalysisExcelService {
	@Resource private StudentBasicInfoService studentBasicInfoService;
	
	
	
	
	@Override
	public StudentBasicInfo analysisStudentBasicInfo(InputStream stream) throws IOException {
		StudentBasicInfo sbi;
		// TODO Auto-generated method stub
		POIFSFileSystem fs = new POIFSFileSystem(stream);  
        HSSFWorkbook wb = new HSSFWorkbook(fs);  
      //获取excel表的第一个sheet  
        HSSFSheet sheet = wb.getSheetAt(0);  
        if (sheet == null) {  
            return null;  
        }  
  
        //遍历该sheet的行  
        for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {  
            HSSFRow row = sheet.getRow(rowNum);  
            if (row == null) {  
                continue;  
            }  
            sbi=new StudentBasicInfo();
            //再遍历改行的所有列  
            for(int cellNum = 0; cellNum <= row.getLastCellNum(); cellNum++) {  
                HSSFCell cell = row.getCell(cellNum);  
                if (cell == null) {  
                    continue;  
                }
                String temp =readCellSecondMethod(cell);
                switch (cellNum){
	                case 0:
	                	sbi.setXiaoqu(temp);
	                case 1:
	                	sbi.setXueyuan(temp);
	                case 2:
	                	sbi.setZhuanye(temp);
	                case 3:
	                	sbi.setLuqupici(temp);
	                case 4:
	                	sbi.setNianji(temp);
	                case 5:
	                	sbi.setJclass(temp);
	                case 6:
	                	sbi.setXuehao(temp);
	                case 7:
	                	sbi.setName(temp);
	                case 8:
	                	sbi.setSex(temp);
	                case 9:
	                	sbi.setMinzu(temp);
	                case 10:
	                	sbi.setZhengzhi(temp);
	                case 11:
	                	sbi.setIdNumber(temp);
	                case 12:
	                	sbi.setGongyu(temp);
	                case 13:
	                	sbi.setQinshihao(temp);
	                case 14:
	                	sbi.setJiguan(temp);
	                case 15:
	                	sbi.setAddress(temp);
	                case 16:
	                	sbi.setPhone(temp);
	                case 17:
	                	sbi.setQq(temp);
	                case 18:
	                	sbi.setJiazhang1(temp);
	                case 19:
	                	sbi.setJiazhang1Phone(temp);
	                case 20:
	                	sbi.setJiazhang2(temp);
	                case 21:
	                	sbi.setJiazhang2Phone(temp);	
                }  
                
					sbi.setUpdateTime(new Date());
            }  
            System.out.println(sbi.toString());

           //下面判断数据库中是否存在此学生
            
            
            
            
            studentBasicInfoService.insertEntry(sbi);
        }  
        
        stream.close();  
		return null;
	}

	
	
	/**第二种方法 
     * 读取excel单元格的内容并针对其type进行不同的处理, 
     * 其中就包含  读取excel表格中日期格式的cell 
     * @param cell 
     * @return 
     */  
    public static String readCellSecondMethod(HSSFCell cell) {  
        //DecimalFormat df = new DecimalFormat("#");  
        if (cell == null) {  
            return "";  
        }  
        switch (cell.getCellType()) {  
          
            //数字  
            case HSSFCell.CELL_TYPE_NUMERIC:  
                  
                //日期格式的处理  
                if (HSSFDateUtil.isCellDateFormatted(cell)) {  
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
                    return sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue())).toString();  
                }  
                
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);  
                return cell.getStringCellValue();  
                //return df.format(cell.getNumericCellValue());  
                  
            //字符串  
            case HSSFCell.CELL_TYPE_STRING:  
                return cell.getStringCellValue();  
              
            //公式  
            case HSSFCell.CELL_TYPE_FORMULA:  
                return cell.getCellFormula();  
                  
            //空白  
            case HSSFCell.CELL_TYPE_BLANK:  
                return "";  
              
            //布尔取值  
            case HSSFCell.CELL_TYPE_BOOLEAN:  
                return cell.getBooleanCellValue() + "";  
              
            //错误类型  
            case HSSFCell.CELL_TYPE_ERROR:  
                return cell.getErrorCellValue() + "";  
        }  
          
        return "";  
    }  
	
    
    @Test
    public void test() throws IOException{
    	//读取一个excel表的内容  
        InputStream stream = new FileInputStream("C:\\Users\\DoubleKing\\Desktop\\west\\学生基本情况表.xls");
        analysisStudentBasicInfo(stream);
    	
    	
    }



	@Override
	public BaseDao<StudentBasicInfo, Integer> getDao() {
		// TODO Auto-generated method stub
		return null;
	}


	
}
