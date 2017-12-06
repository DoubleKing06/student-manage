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

import cn.edu.cwnu.studentmanage.dao.StudentPingjiangDaoVO;
import cn.edu.cwnu.studentmanage.dao.base.BaseDao;
import cn.edu.cwnu.studentmanage.domain.StudentPingjiangVO;
import cn.edu.cwnu.studentmanage.service.StudentPingjiangVOService;
import cn.edu.cwnu.studentmanage.service.base.BaseServiceImpl;

/**
 * StudentPingjiangService 实现类
 * @author kkliu
 * @since 2017-11-17
 */
@Service("studentPingjiangVOService")
public class StudentPingjiangVOServiceImpl extends BaseServiceImpl<StudentPingjiangVO,Integer> implements StudentPingjiangVOService {
	
	@Resource private StudentPingjiangDaoVO studentPingjiangDaoVO;
	
	public BaseDao<StudentPingjiangVO,Integer> getDao() {
		return studentPingjiangDaoVO;
	}
	
	@Transactional(rollbackFor=Exception.class)
	public int insertEntryCreateId(StudentPingjiangVO studentPingjiangVO) {
		return super.insertEntryCreateId(studentPingjiangVO);
	}

	
	/**
	 * 通过查询条件导出评奖信息
	 */
	@Override
	public HSSFWorkbook studentPingjiangExport(StudentPingjiangVO studentPingjiangVO) throws IOException {
		List<StudentPingjiangVO> list = studentPingjiangDaoVO.selectEntryList(studentPingjiangVO);
/*		HSSFWorkbook  wb=new HSSFWorkbook();
        HSSFSheet sheet=wb.createSheet("学生资助信息");
        //在sheet中创建一行  
        HSSFRow row=sheet.createRow(0);  
        //在该行写入各种类型的数据 
        for(int i=0;i<headers.length;i++){
        	row.createCell(i).setCellValue(headers[i]);  
        }*/
		String headers[]={"学号","姓名","学期","奖学金","单项奖学金","学优","团优","优秀大学毕业生","党校学习"}; 
      //excel模板路径    
        File fi= new File(new File(Thread.currentThread().getContextClassLoader().getResource("").getPath()).getParent() + "/template/评优评奖情况-导出.xls");    
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
            			rowTemp.createCell(i).setCellValue(list.get(x).getXueqi());
            			break;
            		case 3:
            			rowTemp.createCell(i).setCellValue(list.get(x).getJiangxuejin());
            			break;
            		case 4:
            			rowTemp.createCell(i).setCellValue(list.get(x).getDanxiangjiangxuejin());
            			break;
            		case 5:
            			rowTemp.createCell(i).setCellValue(list.get(x).getXueyou());
            			break;
            		case 6:
            			rowTemp.createCell(i).setCellValue(list.get(x).getTuanyou());
            			break;
            		case 7:
            			rowTemp.createCell(i).setCellValue(list.get(x).getYxdxbys());
            			break;
            		case 8:
            			rowTemp.createCell(i).setCellValue(list.get(x).getDxxx());
            			break;
            	}
            }
        }
		// TODO Auto-generated method stub
		return wb1;
	}
	
	
}