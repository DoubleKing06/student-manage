package cn.edu.cwnu.studentmanage.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPRow;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import cn.edu.cwnu.studentmanage.dao.base.BaseDao;
import cn.edu.cwnu.studentmanage.domain.StudentBasicInfo;
import cn.edu.cwnu.studentmanage.domain.StudentChengji;
import cn.edu.cwnu.studentmanage.domain.StudentPingjiang;
import cn.edu.cwnu.studentmanage.domain.StudentQitahuojiang;
import cn.edu.cwnu.studentmanage.domain.StudentXueye;
import cn.edu.cwnu.studentmanage.domain.StudentZizhu;
import cn.edu.cwnu.studentmanage.domain.vo.StudentGrowupInfoVO;
import cn.edu.cwnu.studentmanage.domain.vo.XueQiInfo;
import cn.edu.cwnu.studentmanage.service.GetStudentAllInfoService;
import cn.edu.cwnu.studentmanage.service.StudentBasicInfoService;
import cn.edu.cwnu.studentmanage.service.StudentChengjiService;
import cn.edu.cwnu.studentmanage.service.StudentPingjiangService;
import cn.edu.cwnu.studentmanage.service.StudentQitahuojiangService;
import cn.edu.cwnu.studentmanage.service.StudentXueyeService;
import cn.edu.cwnu.studentmanage.service.StudentZizhuService;
import cn.edu.cwnu.studentmanage.service.base.BaseServiceImpl;

@Service("getStudentAllInfoService")
public class GetStudentAllInfoServiceImpl extends BaseServiceImpl<StudentBasicInfo,Integer> implements GetStudentAllInfoService {
	@Resource private StudentBasicInfoService studentBasicInfoService;
	@Resource private StudentChengjiService studentChengjiService;
	@Resource private StudentXueyeService studentXueyeService;
	@Resource private StudentZizhuService studentZizhuService;
	@Resource private StudentPingjiangService studentPingjiangService;
	@Resource private StudentQitahuojiangService studentQitahuojiangService;
	
	@Override
	public StudentGrowupInfoVO getStudentAllInfo(Long student_id) throws Exception {
		StudentGrowupInfoVO sg = new StudentGrowupInfoVO();
		
		
		/**
		 * 学生基本信息
		 */
		StudentBasicInfo studentBasicInfo = getStudentBasicInfo(Integer.valueOf(student_id.toString()));
		if(null == studentBasicInfo){
			throw new Exception("无此学生");
		}
		sg.setXuehao(studentBasicInfo.getXuehao());
		sg.setName(studentBasicInfo.getName());
		sg.setZhuanye(studentBasicInfo.getZhuanye());
		sg.setBanji(studentBasicInfo.getBanji());
		
		/**
		 * 设置年级总人数
		 */
		StudentBasicInfo studentTemp =new StudentBasicInfo();
		studentTemp.setNianji(studentBasicInfo.getNianji());
		Integer nianjiCount = studentBasicInfoService.selectEntryListCount(studentTemp);
		sg.setNianjiCount(nianjiCount.toString());
		
		/**
		 * 设置班级总人数
		 */
		studentTemp.setBanji(studentBasicInfo.getBanji());
		Integer banjiCount = studentBasicInfoService.selectEntryListCount(studentTemp);
		sg.setBanjiCount(banjiCount.toString());
		
		
		/**
		 * 学业信息
		 */
		List<StudentXueye> StudentXueye =getStudentXueye(student_id);
		if(!StudentXueye.isEmpty()){
			sg.setCet4(StudentXueye.get(0).getCet4());
			sg.setPutonghua(StudentXueye.get(0).getPutonghua());
			sg.setSanbizi(StudentXueye.get(0).getSanbizi());
		}
		/**
		 * 学生成绩信息
		 */
		Map<String,XueQiInfo> xueQiInfoMap =new HashMap<String,XueQiInfo>();
		List<StudentChengji> chengjiList = getStudentChengji(student_id);
		if(!chengjiList.isEmpty()){
			for(int i=0;i<chengjiList.size();i++){
				StudentChengji it =chengjiList.get(i);
				XueQiInfo xueQiInfo;
				if(sg.getXueQiInfo()==null || sg.getXueQiInfo().get(it.getXueqi()) == null){
					xueQiInfo =new XueQiInfo();
				}else{
					xueQiInfo = (XueQiInfo) sg.getXueQiInfo().get(it.getXueqi());
				}
					xueQiInfo.setZhuanyePaiming(it.getZhuanyePaiming());
					xueQiInfo.setZonghePaiming(it.getZonghePaiming());
					xueQiInfo.setBukaokemu(it.getBukaokemu());
					xueQiInfoMap.put(it.getXueqi(), xueQiInfo);
			}
			sg.setXueQiInfo(xueQiInfoMap);
		}
		
		/**
		 * 学生评奖信息
		 */
		List<StudentPingjiang> pjList = getStudentPingjiang(student_id);
		if(!pjList.isEmpty()){
			Iterator<StudentPingjiang> pj =pjList.iterator();
			while(pj.hasNext()){
				StudentPingjiang pjTemp =pj.next();
				XueQiInfo xqInfo;
				if(xueQiInfoMap.containsKey(pjTemp.getXueqi())){
					xqInfo =xueQiInfoMap.get(pjTemp.getXueqi());
				}else{
					xqInfo =new XueQiInfo();
				}
				xqInfo.setJiangxuejin(pjTemp.getJiangxuejin());
				xqInfo.setDanxiangjiangxuejin(pjTemp.getDanxiangjiangxuejin());
				xqInfo.setXueyou(pjTemp.getXueyou());
				xqInfo.setTuanyou(pjTemp.getTuanyou());
				xqInfo.setYxdxbys(pjTemp.getYxdxbys());
				if(pjTemp.getDxxx()!=null && !"".equals(pjTemp.getDxxx())){
					sg.setDxxx(pjTemp.getDxxx());
				}
				xueQiInfoMap.put(pjTemp.getXueqi(), xqInfo);
			}
			sg.setXueQiInfo(xueQiInfoMap);

			
		}
		
		
		
		/**
		 * 学生资助信息
		 */
		List<StudentZizhu> ziZhuList = getStudentZizhu(student_id);
		if(!ziZhuList.isEmpty()){
			Iterator<StudentZizhu> zz =ziZhuList.iterator();
			while(zz.hasNext()){
				StudentZizhu zzTemp =zz.next();
				XueQiInfo xqInfo;
				if(xueQiInfoMap.containsKey(zzTemp.getXueqi())){
					xqInfo =xueQiInfoMap.get(zzTemp.getXueqi());
				}else{
					xqInfo =new XueQiInfo();
				}
				xqInfo.setGjjxj(zzTemp.getGjjxj());
				xqInfo.setGjlzjxj(zzTemp.getGjlzjxj());
				xqInfo.setGjzxj(zzTemp.getGjzxj());
				xqInfo.setOther(zzTemp.getOther());
				xueQiInfoMap.put(zzTemp.getXueqi(), xqInfo);
			}
			sg.setXueQiInfo(xueQiInfoMap);
		}
		
		
		/**
		 * 其他获奖信息
		 */
		List<StudentQitahuojiang> qthjList=getStudentQitahuojiang(student_id);
		if(!qthjList.isEmpty()){
			Iterator<StudentQitahuojiang> qt =qthjList.iterator();
			while(qt.hasNext()){
				StudentQitahuojiang qthj =qt.next();
				XueQiInfo xqInfo;
				if(xueQiInfoMap.containsKey(qthj.getXueqi())){
					xqInfo =xueQiInfoMap.get(qthj.getXueqi());
				}else{
					xqInfo =new XueQiInfo();
				}
				if(xqInfo.getOtherhuojianginfo() == null){
					List<String> otherhuojianginfoList = new ArrayList<String>();
					otherhuojianginfoList.add(qthj.getOtherhuojianginfo());
					xqInfo.setOtherhuojianginfo(otherhuojianginfoList);
				}else{
					xqInfo.getOtherhuojianginfo().add(qthj.getOtherhuojianginfo());
				}
				xueQiInfoMap.put(qthj.getXueqi(), xqInfo);
			}
			sg.setXueQiInfo(xueQiInfoMap);
			
		}
		// TODO Auto-generated method stub
		return sg;
	}

	
	
	
	@Override
	public BaseDao<StudentBasicInfo, Integer> getDao() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 根据student_id查询学生基本信息
	 * @param student_id
	 * @return
	 */
	public StudentBasicInfo getStudentBasicInfo(Integer student_id){
		return studentBasicInfoService.selectEntry(student_id);
	}
	
	/**
	 * 根据学生id查询学生学业信息
	 * @param student_id
	 * @return
	 */
	public List<StudentXueye> getStudentXueye(Long student_id){
		StudentXueye studentXueye =new StudentXueye();
		studentXueye.setStudentId(student_id);
		return studentXueyeService.selectEntryList(studentXueye);
	}
	
	/**
	 * 根据学生ID查询学生成绩信息
	 * @param student_id
	 * @return
	 */
	public List<StudentChengji> getStudentChengji(Long student_id){
		
		StudentChengji studentChengji =new StudentChengji();
		studentChengji.setStudentId(student_id);
		return studentChengjiService.selectEntryList(studentChengji);
	}
	
	/**
	 * 根据学生ID查询学生p评奖信息
	 * @param student_id
	 * @return
	 */
	public List<StudentPingjiang> getStudentPingjiang(Long student_id){
		StudentPingjiang studentPingjiang =new StudentPingjiang();
		studentPingjiang.setStudentId(student_id);
		return studentPingjiangService.selectEntryList(studentPingjiang);
	}
	
	/**
	 * 根据学生ID查询学生资助信息
	 * @param student_id
	 * @return
	 */
	public List<StudentZizhu> getStudentZizhu(Long student_id){
		
		StudentZizhu studentZizhu =new StudentZizhu();
		studentZizhu.setStudentId(student_id);
		return studentZizhuService.selectEntryList(studentZizhu);
	}
	
	/**
	 * 根据学生ID查询学生资助信息
	 * @param student_id
	 * @return
	 */
	public List<StudentQitahuojiang> getStudentQitahuojiang(Long student_id){
		
		
		StudentQitahuojiang studentQitahuojiang =new StudentQitahuojiang();
		studentQitahuojiang.setStudentId(student_id);
		return studentQitahuojiangService.selectEntryList(studentQitahuojiang);
	}




	@Override
	public StudentGrowupInfoVO getStudentAllInfoVO(Long student_id) throws IOException, Exception {
		StudentGrowupInfoVO studentGrowupInfoVO = getStudentAllInfo(student_id);
		
		/**
		 * 开始拼接数据
		 */
		Map<String,XueQiInfo> xueQiInfoMap = (Map<String, XueQiInfo>) studentGrowupInfoVO.getXueQiInfo();
		Set<String> itMap = xueQiInfoMap.keySet();
		Map<String,String> mapTemp =new HashMap<String,String>();
		for (String key : itMap) {
			StringBuffer sBuffer = new StringBuffer("	");
			XueQiInfo xq = xueQiInfoMap.get(key);
			/**
			 * 成绩相关
			 */
			//专业排名
			if(xq.getZhuanyePaiming()!=null){
				sBuffer.append("专业排名："+xq.getZhuanyePaiming()+"/"+studentGrowupInfoVO.getBanjiCount()+"，");
			}
			//综合排名
			if(xq.getZonghePaiming()!=null){
				sBuffer.append("综合排名："+xq.getZonghePaiming()+"/"+studentGrowupInfoVO.getNianjiCount()+"，");
			}
			//补考科目
			if(xq.getBukaokemu()!=null){
				sBuffer.append("补考科目:"+xq.getBukaokemu()+"，");
			}
			/**
			 * 资助相关
			 */
			//国家奖学金
			if(xq.getGjjxj()!=null){
				sBuffer.append(xq.getGjjxj()+"，");
			}
			//国家励志奖学金
			if(xq.getGjlzjxj()!=null){
				sBuffer.append(xq.getGjlzjxj()+"，");
			}
			//国家助学金
			if(xq.getGjzxj()!=null){
				sBuffer.append(xq.getGjzxj()+"，");
			}
			
			/**
			 * 评奖相关
			 */
			//奖学金
			if(xq.getJiangxuejin()!=null){
				sBuffer.append(xq.getJiangxuejin()+"，");
			}
			//单项奖学金
			if(xq.getDanxiangjiangxuejin()!=null){
				sBuffer.append(xq.getDanxiangjiangxuejin()+"，");
			}
			//学优
			if(xq.getXueyou()!=null){
				sBuffer.append(xq.getXueyou()+"，");
			}
			//团优
			if(xq.getTuanyou()!=null){
				sBuffer.append(xq.getTuanyou()+"，");
			}
			//优秀大学毕业生
			if(xq.getYxdxbys()!=null){
				sBuffer.append(xq.getYxdxbys()+"，");
			}
			
			if(xq.getOtherhuojianginfo()!=null){
				List<String> qthj =xq.getOtherhuojianginfo();
				for(int i=0;i<qthj.size();i++){
					String bd =(i==qthj.size()-1)?"。":"，";
					sBuffer.append(qthj.get(i)+bd);
				}
			}
			sBuffer = sBuffer.deleteCharAt(sBuffer.length()-1).append("。");
			String sBufferTemp = sBuffer.toString().replace("无，", "").replace("无", "").replace("，。", "。");
			mapTemp.put(key, sBufferTemp);
		}
		for(int i=1;i<=8;i++){
			if(!mapTemp.containsKey(String.valueOf(i))){
				mapTemp.put(String.valueOf(i), "无");
			}
		}
		studentGrowupInfoVO.setXueQiInfo(mapTemp);
		
		// TODO Auto-generated method stub
		return studentGrowupInfoVO;
	}




	@Override
	public ByteArrayOutputStream getPdfOfStudentAllInfo(Long student_id) throws IOException, Exception {
		// TODO Auto-generated method stub
		StudentGrowupInfoVO studentGrowupInfoVO  = getStudentAllInfoVO(student_id);
		//添加中文字体
		BaseFont bfChinese=BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		
		//创建文件
        Document document = new Document(PageSize.A4,40, 40, 60, 20);
//        document.setPageSize(PageSize.A4);//设置A4
        //建立一个书写器
//        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("E:/test.pdf"));
        
        Font titleFont = new Font(bfChinese, 20, Font.BOLD);  
        Font cellTitleFont = new Font(bfChinese, 11, Font.BOLD);  
        Font cellContentFont = new Font(bfChinese, 9, Font.NORMAL);  
          
        ByteArrayOutputStream baos = new ByteArrayOutputStream();  
        PdfWriter.getInstance(document, baos);  
        //打开文件
        document.open();
        
        Paragraph title =new Paragraph("学生成长记录表",titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingBefore(20);          
        //添加
        document.add(title);
        
        
        
        int size = 40;  
        int contentSize = 40; 
     
        // 8列的表.
        PdfPTable table = new PdfPTable(8); 
        table.setWidthPercentage(100); // 宽度100%填充
        table.setSpacingBefore(10f); // 前间距
        table.setSpacingAfter(10f); // 后间距

        List<PdfPRow> listRow = table.getRows();
        //设置列宽
        float[] columnWidths = { 2f, 2f, 2f,2f, 2f, 2f,2f, 2f };
        table.setWidthPercentage(100);  
        table.setSpacingBefore(10);  
        table.setWidths(columnWidths);
        
        //行1
        PdfPCell cellsFirst[]= new PdfPCell[8];
        PdfPRow rowFirst = new PdfPRow(cellsFirst);
        //姓名
        cellsFirst[0] = new PdfPCell(new Paragraph("姓名",cellTitleFont));//单元格内容
//        cellsFirst[0].setBorderColor(BaseColor.BLUE);//边框验证
//        cellsFirst[0].setPaddingCENTER(20);//左填充20
        cellsFirst[0].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居左
        cellsFirst[0].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
        cellsFirst[0].setColspan(1);  
        cellsFirst[0].setFixedHeight(size);
        //姓名value
        cellsFirst[1] = new PdfPCell(new Paragraph(studentGrowupInfoVO.getName(),cellContentFont));//单元格内容
        cellsFirst[1].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居左
        cellsFirst[1].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
        cellsFirst[1].setColspan(1);  
        cellsFirst[1].setFixedHeight(contentSize);
        //学号
        cellsFirst[2] = new PdfPCell(new Paragraph("学号",cellTitleFont));//单元格内容
        cellsFirst[2].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居左
        cellsFirst[2].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
        cellsFirst[2].setColspan(1);  
        cellsFirst[2].setFixedHeight(size);
        //学号value
        cellsFirst[3] = new PdfPCell(new Paragraph(studentGrowupInfoVO.getXuehao(),cellContentFont));//单元格内容
        cellsFirst[3].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居左
        cellsFirst[3].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
        cellsFirst[3].setColspan(1);  
        cellsFirst[3].setFixedHeight(contentSize);
        //专业
        cellsFirst[4] = new PdfPCell(new Paragraph("专业",cellTitleFont));//单元格内容
        cellsFirst[4].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居左
        cellsFirst[4].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
        cellsFirst[4].setColspan(1);  
        cellsFirst[4].setFixedHeight(size);
        //专业value
        cellsFirst[5] = new PdfPCell(new Paragraph(studentGrowupInfoVO.getZhuanye(),cellContentFont));//单元格内容
        cellsFirst[5].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居左
        cellsFirst[5].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
        cellsFirst[5].setColspan(1);  
        cellsFirst[5].setFixedHeight(contentSize);
        //班级
        cellsFirst[6] = new PdfPCell(new Paragraph("班级",cellTitleFont));//单元格内容
        cellsFirst[6].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居左
        cellsFirst[6].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
        cellsFirst[6].setColspan(1);  
        cellsFirst[6].setFixedHeight(size);
        //班级value
        cellsFirst[7] = new PdfPCell(new Paragraph(studentGrowupInfoVO.getBanji(),cellContentFont));//单元格内容
        cellsFirst[7].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居左
        cellsFirst[7].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
        cellsFirst[7].setColspan(1);  
        cellsFirst[7].setFixedHeight(contentSize);
        //把第一行添加到集合
        listRow.add(rowFirst);
        
        //行2
        PdfPCell cellsSec[]= new PdfPCell[8];
        PdfPRow rowSec = new PdfPRow(cellsSec);
        //英语四级
        cellsSec[0] = new PdfPCell(new Paragraph("英语四级",cellTitleFont));//单元格内容
//        cellsSec[0].setBorderColor(BaseColor.BLUE);//边框验证
//        cellsSec[0].setPaddingCENTER(20);//左填充20
        cellsSec[0].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居左
        cellsSec[0].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
        cellsSec[0].setColspan(1);  
        cellsSec[0].setFixedHeight(size);
        //英语四级value
        cellsSec[1] = new PdfPCell(new Paragraph(String.valueOf(studentGrowupInfoVO.getCet4()),cellContentFont));//单元格内容
        cellsSec[1].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居左
        cellsSec[1].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
        cellsSec[1].setColspan(1);  
        cellsSec[1].setFixedHeight(contentSize);
        //普通话
        cellsSec[2] = new PdfPCell(new Paragraph("普通话",cellTitleFont));//单元格内容
        cellsSec[2].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居左
        cellsSec[2].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
        cellsSec[2].setColspan(1);  
        cellsSec[2].setFixedHeight(size);
        //普通话value
        cellsSec[3] = new PdfPCell(new Paragraph(studentGrowupInfoVO.getPutonghua(),cellContentFont));//单元格内容
        cellsSec[3].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居左
        cellsSec[3].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
        cellsSec[3].setColspan(1);  
        cellsSec[3].setFixedHeight(contentSize);
        //三笔字
        cellsSec[4] = new PdfPCell(new Paragraph("三笔字",cellTitleFont));//单元格内容
        cellsSec[4].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居左
        cellsSec[4].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
        cellsSec[4].setColspan(1);  
        cellsSec[4].setFixedHeight(size);
        //三笔字value
        cellsSec[5] = new PdfPCell(new Paragraph(studentGrowupInfoVO.getSanbizi(),cellContentFont));//单元格内容
        cellsSec[5].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居左
        cellsSec[5].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
        cellsSec[5].setColspan(1);  
        cellsSec[5].setFixedHeight(contentSize);
        //党校学习
        cellsSec[6] = new PdfPCell(new Paragraph("党校学习",cellTitleFont));//单元格内容
        cellsSec[6].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居左
        cellsSec[6].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
        cellsSec[6].setColspan(1);  
        cellsSec[6].setFixedHeight(size);
        //党校学习value
        cellsSec[7] = new PdfPCell(new Paragraph(studentGrowupInfoVO.getDxxx(),cellContentFont));//单元格内容
        cellsSec[7].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居左
        cellsSec[7].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
        cellsSec[7].setColspan(1);  
        cellsSec[7].setFixedHeight(contentSize);
        //把第二行添加到集合
        listRow.add(rowSec);
        
        
        for(int i=0;i<8;i++){
            //行3
            PdfPCell cells[]= new PdfPCell[8];

            String temp="";
            switch(i){
	            case 0:
	            	temp = "一";
	            	break;
	            case 1:
	            	temp = "二";
	            	break;
	            case 2:
	            	temp = "三";
	            	break;
	            case 3:
	            	temp = "四";
	            	break;
	            case 4:
	            	temp = "五";
	            	break;
	            case 5:
	            	temp = "六";
	            	break;
	            case 6:
	            	temp = "七";
	            	break;
	            case 7:
	            	temp = "八";
	            	break;
            }
            //学期
            cells[0] = new PdfPCell(new Paragraph("第"+temp+"学期",cellTitleFont));//单元格内容
//            cells1[0].setBorderColor(BaseColor.BLUE);//边框验证
//            cells1[0].setPaddingLeft(20);//左填充20
            cells[0].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居左
            cells[0].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
            cells[0].setColspan(1);
            cells[0].setFixedHeight(40);
            
            
            
            //学期value
            cells[1] = new PdfPCell(new Paragraph("	 "+(String) studentGrowupInfoVO.getXueQiInfo().get(String.valueOf(i+1)),cellContentFont));//单元格内容
            cells[1].setHorizontalAlignment(Element.ALIGN_LEFT);//水平居左
            cells[1].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
            cells[1].setColspan(7);
            cells[1].setFixedHeight(40);
            PdfPRow row = new PdfPRow(cells);
            listRow.add(row);
        }
        
        
        
        
        
        //把表格添加到文件中
        document.add(table);
        
        //关闭文档
        document.close();
        //关闭书写器
//        writer.close();
		
		
		return baos;
	}
	
	
	
	

}
