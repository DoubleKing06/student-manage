package cn.edu.cwnu.studentmanage.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.edu.cwnu.studentmanage.dao.base.BaseDao;
import cn.edu.cwnu.studentmanage.domain.StudentBasicInfo;
import cn.edu.cwnu.studentmanage.domain.StudentChengji;
import cn.edu.cwnu.studentmanage.domain.StudentPingjiang;
import cn.edu.cwnu.studentmanage.domain.StudentXueye;
import cn.edu.cwnu.studentmanage.domain.StudentZizhu;
import cn.edu.cwnu.studentmanage.service.AnalysisExcelService;
import cn.edu.cwnu.studentmanage.service.StudentBasicInfoService;
import cn.edu.cwnu.studentmanage.service.StudentChengjiService;
import cn.edu.cwnu.studentmanage.service.StudentPingjiangService;
import cn.edu.cwnu.studentmanage.service.StudentXueyeService;
import cn.edu.cwnu.studentmanage.service.StudentZizhuService;
import cn.edu.cwnu.studentmanage.service.base.BaseServiceImpl;


@Service("AnalysisExcelService")

public class AnalysisExcelServiceImpl extends BaseServiceImpl<StudentBasicInfo,Integer> implements AnalysisExcelService {
	@Resource private StudentBasicInfoService studentBasicInfoService;
	@Resource private StudentChengjiService studentChengjiService;
	@Resource private StudentXueyeService studentXueyeService;
	@Resource private StudentZizhuService studentZizhuService;
	@Resource private StudentPingjiangService studentPingjiangService;
	
	
	/** 
     * 读取excel单元格的内容并针对其type进行不同的处理, 
     * 其中就包含  读取excel表格中日期格式的cell 
     * @param cell 
     * @return 
     */
    public static String readCellSecondMethod(Cell cell) {
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
	
	/**
	 * 解析学生基本信息表
	 * 
	 */
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
            for(int cellNum = 0; cellNum <= row.getLastCellNum()-1; cellNum++) {  
                HSSFCell cell = row.getCell(cellNum);  
                if (cell == null) {  
                    continue;  
                }
                String temp =readCellSecondMethod(cell);
                if (0 == cellNum && StringUtils.isEmpty(temp)) {
                	break;
                }
                switch (cellNum){
	                case 0:
	                	sbi.setXiaoqu(temp);
	                	break;
	                case 1:
	                	sbi.setXueyuan(temp);
	                	break;
	                case 2:
	                	sbi.setZhuanye(temp);
	                	break;
	                case 3:
	                	sbi.setLuqupici(temp);
	                	break;
	                case 4:
	                	sbi.setNianji(temp);
	                	break;
	                case 5:
	                	sbi.setBanji(temp);
	                	break;
	                case 6:
	                	sbi.setXuehao(temp);
	                	break;
	                case 7:
	                	sbi.setName(temp);
	                	break;
	                case 8:
	                	sbi.setSex(temp);
	                	break;
	                case 9:
	                	sbi.setMinzu(temp);
	                	break;
	                case 10:
	                	sbi.setZhengzhi(temp);
	                	break;
	                case 11:
	                	sbi.setIdNumber(temp);
	                	break;
	                case 12:
	                	sbi.setGongyu(temp);
	                	break;
	                case 13:
	                	sbi.setQinshihao(temp);
	                	break;
	                case 14:
	                	sbi.setJiguan(temp);
	                	break;
	                case 15:
	                	sbi.setAddress(temp);
	                	break;
	                case 16:
	                	sbi.setPhone(temp);
	                	break;
	                case 17:
	                	sbi.setQq(temp);
	                	break;
	                case 18:
	                	sbi.setJiazhang1(temp);
	                	break;
	                case 19:
	                	sbi.setJiazhang1Phone(temp);
	                	break;
	                case 20:
	                	sbi.setJiazhang2(temp);
	                	break;
	                case 21:
	                	sbi.setJiazhang2Phone(temp);
	                	break;
                }  
                
					sbi.setUpdateTime(new Date());
            }  
            //System.out.println(sbi.toString());

           //下面判断数据库中是否存在此学生
            StudentBasicInfo studentTemp =new StudentBasicInfo(); 
            studentTemp.setXuehao(sbi.getXuehao());
            studentTemp.setName(sbi.getName());
            List<StudentBasicInfo> list =studentBasicInfoService.selectEntryList(studentTemp);
            if(list.isEmpty()){
            	studentBasicInfoService.insertEntry(sbi);
            }else{
            	studentBasicInfoService.deleteByCondtion(list.get(0));
            	studentBasicInfoService.insertEntry(sbi);
            }
            
        }  
        stream.close();
		return null;
	}
	
	/**
	 * 解析学生成绩表
	 * @param stream 文件对象
	 * @return list<List> 不存在的学号,与姓名不匹配的学号
	 * @throws IOException
	 */
	@Override
	public List analysisStudentChengji(InputStream stream) throws IOException {
		StudentChengji chengji;
		//记录姓名与学号和基本信息表不匹配的学号
		List<String> notMatch =new ArrayList<String>();
		//记录导入时，excel中学号在主表中不存在的学号
		List<String> dontExist =new ArrayList<String>();
		// TODO Auto-generated method stub
		POIFSFileSystem fs = new POIFSFileSystem(stream);  
        HSSFWorkbook wb = new HSSFWorkbook(fs);  
      //获取excel表的第一个sheet  
        HSSFSheet sheet = wb.getSheetAt(0);  
        if (sheet == null) {  
            return null;  
        }  
        //遍历该sheet的行  
        Iterator<Row> iter = sheet.iterator();
        if (iter.hasNext()) iter.next();
        while (iter.hasNext()) {  
        	
            Row row = iter.next();  
            if (row == null) {  
                continue;  
            }  
            chengji=new StudentChengji();
            
            String xueHaoTemp=""; //excel中的学号
            String nameTemp="";//excel中的姓名
            
            //再遍历改行的所有列  
            for(int cellNum = 0; cellNum <= row.getLastCellNum()-1; cellNum++) { 
//            	System.out.println(rowNum +":"+cellNum);
                Cell cell = row.getCell(cellNum);  
                if (cell == null) {  
                    continue;  
                }
                String temp =readCellSecondMethod(cell);
                if (0 == cellNum && StringUtils.isEmpty(temp)) {
                	break;
                }
                switch (cellNum){
                	case 0:
                		xueHaoTemp =temp;
                		break;                
                	case 1:
                		nameTemp=temp;
                		break;                
	                case 2:
	                	chengji.setXueqi(temp);
	                	break;
	                case 3:
	                	chengji.setZhuanyePaiming(Integer.valueOf(temp));
	                	break;
	                case 4:
	                	chengji.setZonghePaiming(Integer.valueOf(temp));
	                	break;
	                case 5:
	                	chengji.setBukaokemu(Integer.valueOf(temp));
	                	break;
                }  
                chengji.setUpdateTime(new Date());
            }  
            //System.out.println(chengji.toString());

           //下面判断数据库中是否存在此学生相同学期的成绩信息
            StudentBasicInfo studentTemp =new StudentBasicInfo(); 
            if(xueHaoTemp.equals("") && nameTemp.equals("")){
            	continue; 
            }
        	studentTemp.setXuehao(xueHaoTemp);
        	studentTemp.setName(nameTemp);
            
            List<StudentBasicInfo> list =studentBasicInfoService.selectEntryList(studentTemp);
            //如果list为空则说明学号不存在
            if(list.isEmpty()){
            	dontExist.add(xueHaoTemp);
            	continue;
            }
            //如果excel中的姓名与基本信息表中不一致，则记录，继续循环
            if(!nameTemp.equals(list.get(0).getName())){
            	notMatch.add(xueHaoTemp);
            	continue;
            }
            //下面通过学生的
            chengji.setStudentId(list.get(0).getId());
            StudentChengji studentChengjiTemp =new StudentChengji();
            studentChengjiTemp.setStudentId(chengji.getStudentId());
            studentChengjiTemp.setXueqi(chengji.getXueqi());
            
            List<StudentChengji> chengjiTemp =studentChengjiService.selectEntryList(studentChengjiTemp);
            if(chengjiTemp.isEmpty()){
            	//学期成绩表中不存在excel中的学期成绩，则直接插入数据
            	studentChengjiService.insertEntry(chengji);
            }else{
            	//如果数据库中存在此学生excel中学期
            	studentChengjiService.deleteByCondtion(studentChengjiTemp);
            	studentChengjiService.insertEntry(chengji);
            }
        }  
        stream.close(); 
        List list =new ArrayList<>();
        list.add(notMatch);
        list.add(dontExist);
		return list;
	}	

	
	/**
	 * 解析学生学业状况表
	 * @param stream 文件对象
	 * @return list<List> 不存在的学号,与姓名不匹配的学号
	 * @throws IOException
	 */
	@Override
	public List analysisStudentXueye(InputStream stream) throws IOException {
		StudentXueye xueye;
		//记录姓名与学号和基本信息表不匹配的学号
		List<String> notMatch =new ArrayList<String>();
		//记录导入时，excel中学号在主表中不存在的学号
		List<String> dontExist =new ArrayList<String>();
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
            xueye=new StudentXueye();
            
            String xueHaoTemp=""; //excel中的学号
            String nameTemp="";//excel中的姓名
            
            //再遍历改行的所有列  
            for(int cellNum = 0; cellNum <= row.getLastCellNum()-1; cellNum++) {  
                HSSFCell cell = row.getCell(cellNum);  
                if (cell == null) {  
                    continue;  
                }
                String temp =readCellSecondMethod(cell);
                if (0 == cellNum && StringUtils.isEmpty(temp)) {
                	break;
                }
                switch (cellNum){
                	case 0:
                		xueHaoTemp =temp;
                		break;                
                	case 1:
                		nameTemp=temp;
                		break;                
	                case 2:
	                	xueye.setCet4(Float.valueOf(temp));
	                	break;
	                case 3:
	                	xueye.setSanbizi(temp);
	                	break;
	                case 4:
	                	xueye.setPutonghua(temp);
	                	break;
                }  
                xueye.setUpdateTime(new Date());
            }  
            System.out.println(xueye.toString());

           //下面判断数据库中是否存在此学生相同学期的成绩信息
            StudentBasicInfo studentTemp =new StudentBasicInfo(); 
            if(xueHaoTemp.equals("") && nameTemp.equals("")){
            	continue; 
            }
        	studentTemp.setXuehao(xueHaoTemp);
        	studentTemp.setName(nameTemp);
            
            List<StudentBasicInfo> list =studentBasicInfoService.selectEntryList(studentTemp);
            //如果list为空则说明学号不存在
            if(list.isEmpty()){
            	dontExist.add(xueHaoTemp);
            	continue;
            }
            //如果excel中的姓名与基本信息表中不一致，则记录，继续循环
            if(!nameTemp.equals(list.get(0).getName())){
            	notMatch.add(xueHaoTemp);
            	continue;
            }
            //下面通过学生的
            xueye.setStudentId(list.get(0).getId());
            StudentXueye studentXueyeTemp =new StudentXueye();
            studentXueyeTemp.setStudentId(xueye.getStudentId());
            
            List<StudentXueye> xueyeTemp =studentXueyeService.selectEntryList(studentXueyeTemp);
            if(xueyeTemp.isEmpty()){
            	//学期成绩表中不存在excel中的学期成绩，则直接插入数据
            	studentXueyeService.insertEntry(xueye);
            }else{
            	//如果数据库中存在此学生excel中学期
            	studentXueyeService.deleteByCondtion(studentXueyeTemp);
            	studentXueyeService.insertEntry(xueye);
            }
        }  
        stream.close();
        List list =new ArrayList<>();
        list.add(notMatch);
        list.add(dontExist);
		return list;
		
		
		
		
		
		
		
		
		
	}


	/**
	 * 解析学生资助情况表
	 * @param stream 文件对象
	 * @return list<List> 不存在的学号,与姓名不匹配的学号
	 * @throws IOException
	 */
	@Override
	public List analysisStudentZizhu(InputStream stream) throws IOException {
		StudentZizhu zizhu;
		//记录姓名与学号和基本信息表不匹配的学号
		List<String> notMatch =new ArrayList<String>();
		//记录导入时，excel中学号在主表中不存在的学号
		List<String> dontExist =new ArrayList<String>();
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
			zizhu=new StudentZizhu();
			
			String xueHaoTemp=""; //excel中的学号
			String nameTemp="";//excel中的姓名
			
			//再遍历改行的所有列  
			for(int cellNum = 0; cellNum <= row.getLastCellNum()-1; cellNum++) {  
				HSSFCell cell = row.getCell(cellNum);  
				if (cell == null) {  
					continue;  
				}
				String temp =readCellSecondMethod(cell);
                if (0 == cellNum && StringUtils.isEmpty(temp)) {
                	break;
                }
				switch (cellNum){
				case 0:
					xueHaoTemp =temp;
					break;                
				case 1:
					nameTemp=temp;
					break;                
				case 2:
					zizhu.setXueqi(temp);
					break;
				case 3:
					zizhu.setGjjxj(temp);;
					break;
				case 4:
					zizhu.setGjlzjxj(temp);
					break;
				case 5:
					zizhu.setGjzxj(temp);
					break;
				case 6:
					zizhu.setOther(temp);
					break;					
				}  
				zizhu.setUpdateTime(new Date());
			}  
			System.out.println(zizhu.toString());
			
			//下面判断数据库中是否存在此学生相同学期的成绩信息
			StudentBasicInfo studentTemp =new StudentBasicInfo(); 
			if(xueHaoTemp.equals("") && nameTemp.equals("")){
				continue; 
			}
			studentTemp.setXuehao(xueHaoTemp);
			studentTemp.setName(nameTemp);
			
			List<StudentBasicInfo> list =studentBasicInfoService.selectEntryList(studentTemp);
			//如果list为空则说明学号不存在
			if(list.isEmpty()){
				dontExist.add(xueHaoTemp);
				continue;
			}
			//如果excel中的姓名与基本信息表中不一致，则记录，继续循环
			if(!nameTemp.equals(list.get(0).getName())){
				notMatch.add(xueHaoTemp);
				continue;
			}
			//下面通过学生的
			zizhu.setStudentId(list.get(0).getId());
			StudentZizhu studentZizhuTemp =new StudentZizhu();
			studentZizhuTemp.setStudentId(zizhu.getStudentId());
			studentZizhuTemp.setXueqi(zizhu.getXueqi());
			
			List<StudentZizhu> zizhuTemp =studentZizhuService.selectEntryList(studentZizhuTemp);
			if(zizhuTemp.isEmpty()){
				//学期成绩表中不存在excel中的学期成绩，则直接插入数据
				studentZizhuService.insertEntry(zizhu);
			}else{
				//如果数据库中存在此学生excel中学期
				studentZizhuService.deleteByCondtion(studentZizhuTemp);
				studentZizhuService.insertEntry(zizhu);
			}
		}  
		stream.close(); 
        List list =new ArrayList<>();
        list.add(notMatch);
        list.add(dontExist);
		
		return list;
	}
	
	/**
	 * 解析评优评奖表
	 * @param stream 文件对象
	 * @return list<List> 不存在的学号,与姓名不匹配的学号
	 * @throws IOException	 
	 */
	@Override
	public List analysisStudentPingjiang(InputStream stream) throws IOException {
		StudentPingjiang pingjiang;
		//记录姓名与学号和基本信息表不匹配的学号
		List<String> notMatch =new ArrayList<String>();
		//记录导入时，excel中学号在主表中不存在的学号
		List<String> dontExist =new ArrayList<String>();
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
			pingjiang=new StudentPingjiang();
			
			String xueHaoTemp=""; //excel中的学号
			String nameTemp="";//excel中的姓名
			
			//再遍历改行的所有列  
			for(int cellNum = 0; cellNum <= row.getLastCellNum()-1; cellNum++) {  
				HSSFCell cell = row.getCell(cellNum);  
				if (cell == null) {  
					continue;  
				}
				String temp =readCellSecondMethod(cell);
                if (0 == cellNum && StringUtils.isEmpty(temp)) {
                	break;
                }
				switch (cellNum){
				case 0:
					xueHaoTemp =temp;
					break;                
				case 1:
					nameTemp=temp;
					break;                
				case 2:
					pingjiang.setXueqi(temp);
					break;
				case 3:
					pingjiang.setJiangxuejin(temp);
					break;
				case 4:
					pingjiang.setDanxiangjiangxuejin(temp);
					break;
				case 5:
					pingjiang.setXueyou(temp);
					break;
				case 6:
					pingjiang.setTuanyou(temp);
					break;	
				case 7:
					pingjiang.setYxdxbys(temp);
					break;						
				case 8:
					pingjiang.setDxxx(temp);
					break;						
				}  
				pingjiang.setUpdateTime(new Date());
			}  
			System.out.println(pingjiang.toString());
			
			//下面判断数据库中是否存在此学生相同学期的成绩信息
			StudentBasicInfo studentTemp =new StudentBasicInfo(); 
			if(xueHaoTemp.equals("") && nameTemp.equals("")){
				continue; 
			}
			studentTemp.setXuehao(xueHaoTemp);
			studentTemp.setName(nameTemp);
			
			List<StudentBasicInfo> list =studentBasicInfoService.selectEntryList(studentTemp);
			//如果list为空则说明学号不存在
			if(list.isEmpty()){
				dontExist.add(xueHaoTemp);
				continue;
			}
			//如果excel中的姓名与基本信息表中不一致，则记录，继续循环
			if(!nameTemp.equals(list.get(0).getName())){
				notMatch.add(xueHaoTemp);
				continue;
			}
			//下面通过学生的
			pingjiang.setStudentId(list.get(0).getId());;
			StudentPingjiang studentPingjiangTemp =new StudentPingjiang();
			studentPingjiangTemp.setStudentId(list.get(0).getId());
			studentPingjiangTemp.setXueqi(pingjiang.getXueqi());
			
			List<StudentPingjiang> pingjiangTemp =studentPingjiangService.selectEntryList(studentPingjiangTemp);
			if(pingjiangTemp.isEmpty()){
				//学期成绩表中不存在excel中的学期成绩，则直接插入数据
				studentPingjiangService.insertEntry(pingjiang);
			}else{
				//如果数据库中存在此学生excel中学期
				studentPingjiangService.deleteByCondtion(studentPingjiangTemp);
				studentPingjiangService.insertEntry(pingjiang);
			}
		}  
		stream.close();
        List list =new ArrayList<>();
        list.add(notMatch);
        list.add(dontExist);
		return list;
		
		
	}
    



    @Override
    public BaseDao<StudentBasicInfo, Integer> getDao() {
        // TODO Auto-generated method stub
        return null;
    }



}
