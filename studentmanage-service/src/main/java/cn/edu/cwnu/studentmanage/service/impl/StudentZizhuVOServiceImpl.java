/*
 * Copyright (c) 2017 cwnu.edu.cn All rights reserved.
 * 本软件源代码版权归----所有,未经许可不得任意复制与传播.
 */
package cn.edu.cwnu.studentmanage.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.cwnu.studentmanage.dao.StudentZizhuDaoVO;
import cn.edu.cwnu.studentmanage.dao.base.BaseDao;
import cn.edu.cwnu.studentmanage.domain.StudentZizhuVO;
import cn.edu.cwnu.studentmanage.service.StudentZizhuVOService;
import cn.edu.cwnu.studentmanage.service.base.BaseServiceImpl;

/**
 * StudentZizhuService 实现类
 * @author kkliu
 * @since 2017-11-17
 */
@Service("studentZizhuVOService")
public class StudentZizhuVOServiceImpl extends BaseServiceImpl<StudentZizhuVO,Integer> implements StudentZizhuVOService {
	
	@Resource private StudentZizhuDaoVO studentZizhuDaoVO;
	
	public BaseDao<StudentZizhuVO,Integer> getDao() {
		return studentZizhuDaoVO;
	}
	
	@Transactional(rollbackFor=Exception.class)
	public int insertEntryCreateId(StudentZizhuVO studentZizhuVO) {
		return super.insertEntryCreateId(studentZizhuVO);
	}

	/**
	 * 根据查询条件导出excel
	 */
	@Override
	public HSSFWorkbook studentZizhuExport(StudentZizhuVO studentZizhuVO) throws IOException {
		List<StudentZizhuVO> list = studentZizhuDaoVO.selectEntryList(studentZizhuVO);
		String headers[]={"学号","姓名","班级","学期","国家奖学金","国家励志奖学金","国家助学金","其他社会资助"}; 
/*		HSSFWorkbook  wb=new HSSFWorkbook();
        HSSFSheet sheet=wb.createSheet("学生资助信息");
        //在sheet中创建一行  
        HSSFRow row=sheet.createRow(0);  
        //在该行写入各种类型的数据 
        for(int i=0;i<headers.length;i++){
        	row.createCell(i).setCellValue(headers[i]);  
        }*/
      //excel模板路径    
        File fi= new File(new File(Thread.currentThread().getContextClassLoader().getResource("").getPath()).getParent() + "/template/资助情况表-导出.xls");    
        POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(fi));    
        //读取excel模板    
        HSSFWorkbook wb1 = new HSSFWorkbook(fs);
        //读取了模板内所有sheet内容    
        HSSFSheet sheet1 = wb1.getSheetAt(0);     
        for(int x=0;x<list.size();x++){
            //在sheet中创建一行  
            HSSFRow rowTemp=sheet1.createRow(x+1);  
            //在该行写入各种类型的数据 
            for(int i=0;i<headers.length;i++){
            	switch(i){
            		case 0:
            			rowTemp.createCell(i).setCellValue(list.get(x).getXuehao());
            			break;
            		case 1:
            			rowTemp.createCell(i).setCellValue(list.get(x).getName());
            			break;
            		case 2:
            			rowTemp.createCell(i).setCellValue(list.get(x).getBanji());
            			break;
            		case 3:
            			rowTemp.createCell(i).setCellValue(list.get(x).getXueqi());
            			break;
            		case 4:
            			rowTemp.createCell(i).setCellValue(list.get(x).getGjjxj());
            			break;
            		case 5:
            			rowTemp.createCell(i).setCellValue(list.get(x).getGjlzjxj());
            			break;
            		case 6:
            			rowTemp.createCell(i).setCellValue(list.get(x).getGjzxj());
            			break;
            		case 7:
            			rowTemp.createCell(i).setCellValue(list.get(x).getOther());
            			break;
            	}
            }
        }
		// TODO Auto-generated method stub
		return wb1;
	}
}