package cn.edu.cwnu.studentmanage.web.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.filechooser.FileSystemView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import cn.edu.cwnu.studentmanage.common.tools.DateUtils;
import cn.edu.cwnu.studentmanage.domain.Login;
import cn.edu.cwnu.studentmanage.domain.common.Message;
import cn.edu.cwnu.studentmanage.web.CustomDateEditor;



@Controller
//@RequestMapping(value = "/jdbc")
public class BackAndRecoverController {
	private static final Logger LOGGER = LoggerFactory.getLogger(BackAndRecoverController.class);
	
	@InitBinder
	public void initBinder(WebDataBinder binder, WebRequest request) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(true));
	}
	
	
    /**
     * 列表展示
     * @return
     */
    @RequestMapping(value = "/jdbc", method = { RequestMethod.GET, RequestMethod.POST })
    public String initUpload() throws Exception {
        return "studentDatabase/database";
    }
    
	
	private String hostIP="127.0.0.1";
	private String userName="root";
	private String password="123456";
	private String dataBasename="student-manage";
	
	@RequestMapping(value="/jdbc/backup",method = {RequestMethod.GET})
	public @ResponseBody Message backup(HttpServletResponse response,Model view){
		String sqlName = "backup_"+DateUtils.format(new Date(),"yyyy-MM-dd")+".sql";//sql文件名称
		String desktopPath = FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath();
		desktopPath =desktopPath.replace("\\\\", "\\\\\\\\");
		 try {  
	            if (exportDatabaseTool(hostIP, userName, password, desktopPath, sqlName, dataBasename)) {  
	                System.out.println("数据库成功备份！！！");  
	            } else {  
	                System.out.println("数据库备份失败！！！");  
	            }  
	        } catch (InterruptedException e) {  
	            LOGGER.error("失败:" + e.getMessage(), e);
	            return Message.create("fail", e.getMessage()); 
	        }  
		return Message.create("ok", "备份成功");
	}
	
	
	/** 
     * Java代码实现MySQL数据库导出 
     *  
     * @author kkliu 
     * @param hostIP MySQL数据库所在服务器地址IP 
     * @param userName 进入数据库所需要的用户名 
     * @param password 进入数据库所需要的密码 
     * @param savePath 数据库导出文件保存路径 
     * @param fileName 数据库导出文件文件名 
     * @param databaseName 要导出的数据库名 
     * @return 返回true表示导出成功，否则返回false。 
     */  
    public static boolean exportDatabaseTool(String hostIP, String userName, String password, String savePath, String fileName, String databaseName) throws InterruptedException {  
        File saveFile = new File(savePath);  
        if (!saveFile.exists()) {// 如果目录不存在  
            saveFile.mkdirs();// 创建文件夹  
        }  
        if(!savePath.endsWith(File.separator)){  
            savePath = savePath + File.separator;  
        }  
          
        PrintWriter printWriter = null;  
        BufferedReader bufferedReader = null;  
        try {  
            printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(savePath + fileName), "utf8"));  
            Process process = Runtime.getRuntime().exec(" mysqldump -h" + hostIP + " -u" + userName + " -p" + password + " --set-charset=UTF8 " + databaseName);  
            InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream(), "utf8");  
            bufferedReader = new BufferedReader(inputStreamReader);  
            String line;  
            while((line = bufferedReader.readLine())!= null){  
                printWriter.println(line);  
            }  
            printWriter.flush();  
            if(process.waitFor() == 0){//0 表示线程正常终止。  
                return true;  
            }  
        }catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                if (bufferedReader != null) {  
                    bufferedReader.close();  
                }  
                if (printWriter != null) {  
                    printWriter.close();  
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        return false;  
    } 
	
	
    @RequestMapping(value = "/jdbc/recover", method = { RequestMethod.POST })
    public @ResponseBody Message recover(String type, @RequestParam(value = "filename") MultipartFile uploadFile) throws Exception {
		try{
    	Runtime runtime = Runtime.getRuntime();
		Process process = runtime.exec("mysql -h" + hostIP + " -u" + userName + " -p" + password +" --set-charset=UTF8 " + dataBasename);
		OutputStream outputStream = process.getOutputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(uploadFile.getInputStream()));
		String str = null;
		StringBuffer sb = new StringBuffer();
		while((str = br.readLine()) != null){
			sb.append(str+"\r\n");
		}
		str = sb.toString();
		System.out.println(str);
		OutputStreamWriter writer = new OutputStreamWriter(outputStream,"utf-8");
		writer.write(str);
		writer.flush();
		outputStream.close();
		br.close();
		writer.close();
		return Message.create("ok", "还原成功");
		}catch(Exception e){
            LOGGER.error("失败:" + e.getMessage(), e);
            return Message.create("fail", e.getMessage());
			
		}
	}	
	
	
	
	
	
}
