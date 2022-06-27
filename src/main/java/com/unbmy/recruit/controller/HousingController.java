package com.unbmy.recruit.controller;

import com.unbmy.recruit.pojo.Housing;
import com.unbmy.recruit.service.IHousingService;
import com.unbmy.recruit.service.IRequestService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author Unbmy
 */
@Controller
@RequestMapping("/housing")
public class HousingController {
    @Resource
    private IHousingService housingService;
    @Resource
    private IRequestService requestService;

    @RequestMapping("/housingDetail-en/{id}")
    public ModelAndView housingDetailEn(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView();
        Housing housing = housingService.getHousingByIdEn(id);
        modelAndView.addObject("housing", housing);
        modelAndView.setViewName("/housing/housingDetail-en");
        return modelAndView;
    }

    @RequestMapping(value = "/insert/{id}", method = RequestMethod.POST)
    public String addHousing(ModelAndView modelAndView,
                             @PathVariable Long id,
                             @RequestParam Long userId,
                             @RequestParam String topic,
                             @RequestParam String host,
                             @RequestParam Integer people,
                             @RequestParam MultipartFile image,
                             @RequestParam Double area,
                             HttpServletRequest request) throws IOException, ParseException {
        String dateStr = request.getParameter("date");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse(dateStr);
        Housing housing = new Housing();
        housing.setTopic(topic);
        housing.setHost(host);
        housing.setPeople(people);
        housing.setArea(area);
        housing.setBuyDate(date);
        if (image.isEmpty()){
            housingService.addHousing(housing);
            housingService.addHousingUser(userId, housing.getId());
            requestService.deleteRequest(id);
        } else {
            String originalName = image.getOriginalFilename();
            String suffix = originalName.substring(originalName.lastIndexOf("."));
            if (".jpg".equals(suffix)){
                String filename = UUID.randomUUID() + suffix;
                System.out.println(filename);
                String path = "D:\\Workspace\\Java\\Recruit\\src\\main\\resources\\static\\upload";
                File newFile = new File(path, filename);
                File parentFile = newFile.getParentFile();
                if (!parentFile.exists()){
                    parentFile.mkdir();
                }
                image.transferTo(newFile);
                housing.setImage(filename);
                housingService.addHousing(housing);
                housingService.addHousingUser(userId, housing.getId());
                requestService.deleteRequest(id);
            } else {
                modelAndView.addObject("upload_err_msg", "暂不支持上传该格式文件！");
                modelAndView.setViewName("/request/requestDetail-en");
                return "redirect:/request/requestDetail-en/" + id;
            }
        }
        return "redirect:/enterprise/all-housing";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public ModelAndView update(@PathVariable Long id,
                               @RequestParam String topic,
                               @RequestParam String host,
                               @RequestParam Integer people){
        Housing housing = new Housing(id, topic, host, people);
        housingService.updateHousing(housing);
        return new ModelAndView("redirect:/housing/housingDetail-en/{id}");
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable Long id){
        housingService.deleteHousing(id);
        return new ModelAndView("redirect:/enterprise/all-housing");
    }


}
