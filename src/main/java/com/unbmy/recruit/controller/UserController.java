package com.unbmy.recruit.controller;


import com.unbmy.recruit.pojo.Account;
import com.unbmy.recruit.pojo.Maintenance;
import com.unbmy.recruit.pojo.Notice;
import com.unbmy.recruit.service.IMaintenanceService;
import com.unbmy.recruit.service.INoticeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @author Unbmy
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private INoticeService noticeService;
    @Resource
    private IMaintenanceService maintenanceService;

    @RequestMapping("/index")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        List<Notice> latestNoticeList = noticeService.getLatestNotice();
        modelAndView.addObject("latestNoticeList", latestNoticeList);
        modelAndView.setViewName("/user/index");
        return modelAndView;
    }

    @RequestMapping("/all-notice")
    public ModelAndView noticeLatest(){
        ModelAndView modelAndView = new ModelAndView();
        List<Notice> allNoticeList = noticeService.getAllNotice();
        modelAndView.addObject("allNoticeList", allNoticeList);
        modelAndView.setViewName("/user/all-notice");
        return modelAndView;
    }

    @RequestMapping("/maintenance-upload")
    public ModelAndView maintenanceUploadHtml(){
        return new ModelAndView("/user/maintenance-upload");
    }

    @RequestMapping("/maintenance-upload/upload")
    public ModelAndView maintenanceUploadAction(@RequestParam String topic,
                                                @RequestParam String place,
                                                @RequestParam String description,
                                                @RequestParam MultipartFile file) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        if (file.isEmpty()){
            maintenanceService.addMaintenance(topic, place, description, null);
        } else {
            String originalName = file.getOriginalFilename();
            String suffix = originalName.substring(originalName.lastIndexOf("."));
            if (".png".equals(suffix)){
                String filename = UUID.randomUUID() + suffix;
                System.out.println(filename);
                String path = "D:\\Workspace\\Java\\Recruit\\src\\main\\resources\\static\\upload";
                File newFile = new File(path, filename);
                File parentFile = newFile.getParentFile();
                if (!parentFile.exists()){
                    parentFile.mkdir();
                }
                file.transferTo(newFile);
                maintenanceService.addMaintenance(topic, place, description, filename);
            } else {
                modelAndView.addObject("upload_err_msg", "暂不支持上传该格式文件！");
            }
        }
        modelAndView.setViewName("/user/maintenance-upload");
        return modelAndView;
    }

    @RequestMapping("/maintenance-finish")
    public ModelAndView maintenanceFinish(HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        Account account = (Account) session.getAttribute("account");
        List<Maintenance> completeMaintenance = maintenanceService.getCompleteMaintenance(account.getId());
        modelAndView.addObject("completeMaintenance", completeMaintenance);
        modelAndView.setViewName("/user/maintenance-finish");
        return modelAndView;
    }

    @RequestMapping("/maintenance-not-finish")
    public ModelAndView maintenanceNotFinish(HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        Account account = (Account) session.getAttribute("account");
        List<Maintenance> unhandledMaintenance = maintenanceService.getUnhandledMaintenance(account.getId());
        modelAndView.addObject("unhandledMaintenance", unhandledMaintenance);
        modelAndView.setViewName("/user/maintenance-not-finish");
        return modelAndView;
    }

}
