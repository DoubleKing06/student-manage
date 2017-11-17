/**
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package cn.edu.cwnu.studentmanage.web.controller;

import cn.edu.cwnu.studentmanage.service.AnalysisExcelService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author YuBo
 * @version $Id: StudentInfoUploadController.java, v0.1 2017/11/17 23:37 YuBo Exp $$
 */
@Controller
public class StudentInfoUploadController {

    @Resource
    private AnalysisExcelService analysisExcelService;

    /**
     * 列表展示
     * @return
     */
    @RequestMapping(value = "/initUpload", method = { RequestMethod.GET, RequestMethod.POST })
    public String initUpload() throws Exception {
        return "studentUpload/upload";
    }

    /**
     * 列表展示
     * @return
     */
    @RequestMapping(value = "/upload", method = { RequestMethod.GET, RequestMethod.POST })
    public void upload(String type, @RequestParam(value = "filename") MultipartFile uploadFile) throws Exception {
        if ("BASIC_INFO".equals(type)) {
            analysisExcelService.analysisStudentBasicInfo(uploadFile.getInputStream());
        } else {
            //Other
        }
    }
}