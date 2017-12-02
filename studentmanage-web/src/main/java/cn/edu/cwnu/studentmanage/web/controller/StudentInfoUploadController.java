/**
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package cn.edu.cwnu.studentmanage.web.controller;

import cn.edu.cwnu.studentmanage.domain.StudentChengji;
import cn.edu.cwnu.studentmanage.domain.common.Message;
import cn.edu.cwnu.studentmanage.service.AnalysisExcelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author YuBo
 * @version $Id: StudentInfoUploadController.java, v0.1 2017/11/17 23:37 YuBo Exp $$
 */
@Controller
public class StudentInfoUploadController {
    private static final Logger  LOGGER = LoggerFactory.getLogger(StudentChengjiController.class);

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
     * @return
     */
    @RequestMapping(value = "/upload", method = { RequestMethod.POST })
    public @ResponseBody Message upload(String type, @RequestParam(value = "filename") MultipartFile uploadFile) throws Exception {
        try {
            if (uploadFile.getInputStream() == null) {

                throw new Exception("文件对象不能为空");
            }
            if ("BASIC_INFO".equals(type)) {
                analysisExcelService.analysisStudentBasicInfo(uploadFile.getInputStream());
            } else if ("CHENGJI".equals(type)) {
                List<StudentChengji> studentChengjiList = analysisExcelService.analysisStudentChengji(uploadFile.getInputStream());

            } else if ("PINGJIANG".equals(type)) {
                analysisExcelService.analysisStudentPingjiang(uploadFile.getInputStream());
            } else if ("XUEYE".equals(type)) {
                analysisExcelService.analysisStudentXueye(uploadFile.getInputStream());
            } else if ("ZIZHU".equals(type)) {
                analysisExcelService.analysisStudentZizhu(uploadFile.getInputStream());
            }
        } catch (Exception e) {
            LOGGER.error("失败:" + e.getMessage(), e);
            return Message.create("fail", e.getMessage());
        } finally {
        }

        return Message.success();
    }
}