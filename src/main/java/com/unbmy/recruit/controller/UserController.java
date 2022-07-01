package com.unbmy.recruit.controller;


import com.unbmy.recruit.pojo.*;
import com.unbmy.recruit.service.*;
import com.unbmy.recruit.vo.NoticeVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
    @Resource
    private IBillService billService;
    @Resource
    private IUserService userService;
    @Resource
    private IRequestService requestService;
    @Resource
    private IHousingService housingService;

    private int randomCode = (int) ((Math.random()*9+1)*100000);

    @RequestMapping("/index")
    public ModelAndView index(HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        Account account = (Account) session.getAttribute("account");
        List<Notice> latestNoticeList = noticeService.getLatestNotice();
        modelAndView.addObject("latestNoticeList", latestNoticeList);
        List<Bill> latestBillList = billService.getLatestBill(account.getId());
        modelAndView.addObject("latestBillList", latestBillList);
        modelAndView.setViewName("/user/index");
        return modelAndView;
    }

    @RequestMapping("/all-notice")
    public ModelAndView noticeAll(){
        ModelAndView modelAndView = new ModelAndView();
        List<Notice> allNoticeList = noticeService.getAllNotice();
        modelAndView.addObject("allNoticeList", allNoticeList);
        modelAndView.setViewName("/user/all-notice");
        return modelAndView;
    }

    @RequestMapping("/all-notice/{current}")
    public ModelAndView noticeQuery(@PathVariable int current){
        ModelAndView modelAndView = new ModelAndView();
        NoticeVo noticeVo = noticeService.queryNotice(current);
        modelAndView.addObject("noticeVo", noticeVo);
        modelAndView.setViewName("/user/all-notice");
        return modelAndView;
    }

    @RequestMapping("/my-housing")
    public ModelAndView myHousing(HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        Account account = (Account) session.getAttribute("account");
        Housing housing = housingService.getHousingById(account.getId());
        modelAndView.addObject("housing", housing);
        modelAndView.setViewName("/user/my-housing");
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
                                                @RequestParam MultipartFile file,
                                                HttpSession session) throws IOException {
        Account account = (Account) session.getAttribute("account");
        ModelAndView modelAndView = new ModelAndView();
        if (file.isEmpty()){
            maintenanceService.addMaintenance(topic, place, description, null, account.getId());
        } else {
            String originalName = file.getOriginalFilename();
            String suffix = originalName.substring(originalName.lastIndexOf("."));
            if (".png".equals(suffix)){
                String filename = UUID.randomUUID() + suffix;
                System.out.println(filename);
                String path = "D:\\upload";
                File newFile = new File(path, filename);
                File parentFile = newFile.getParentFile();
                if (!parentFile.exists()){
                    parentFile.mkdir();
                }
                file.transferTo(newFile);
                maintenanceService.addMaintenance(topic, place, description, filename, account.getId());
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

    @RequestMapping("/bill-completed")
    public ModelAndView billCompleted(HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        Account account = (Account) session.getAttribute("account");
        List<Bill> completedBill = billService.getCompletedBill(account.getId());
        modelAndView.addObject("completedBill", completedBill);
        modelAndView.setViewName("/user/bill-completed");
        return modelAndView;
    }

    @RequestMapping("/bill-unfinished")
    public ModelAndView billUnfinished(HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        Account account = (Account) session.getAttribute("account");
        List<Bill> unfinishedBill = billService.getUnfinishedBill(account.getId());
        modelAndView.addObject("unfinishedBill", unfinishedBill);
        modelAndView.setViewName("/user/bill-unfinished");
        return modelAndView;
    }

    @RequestMapping("/bill-cost/{id}")
    public ModelAndView billCost(HttpServletResponse response,
                                 @PathVariable long id) throws IOException {
        billService.billCost(id);
        response.sendRedirect("/user/bill-unfinished");
        return new ModelAndView("/user/bill-unfinished");
    }

    @RequestMapping("/modify-email")
    public ModelAndView modifyEmailHtml(){
        return new ModelAndView("/user/modify-email");
    }

    @RequestMapping("/modify-password")
    public ModelAndView modifyPasswordHtml(){
        return new ModelAndView("/user/modify-password");
    }

}
