package com.lovo.controller;

import com.lovo.entity.ResourceEntity;
import com.lovo.service.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LiTest {

    @Autowired
    private IResourceService resourceService;

    @RequestMapping(value="upload",method= RequestMethod.POST)
    @ResponseBody
    public   String  uploadExcel(HttpServletRequest request) throws Exception {

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        InputStream inputStream =null;

        List<List<Object>> list = null;

        MultipartFile file = multipartRequest.getFile("filename");

        if(file.isEmpty()){

            return "文件不能为空";

        }

        inputStream = file.getInputStream();

        list = resourceService.getresourceListByExcel(inputStream,file.getOriginalFilename());

        inputStream.close();

//连接数据库部分
        List<ResourceEntity> resourcelist = new ArrayList<ResourceEntity>();
        for (int i = 0; i < list.size(); i++) {
            ResourceEntity resourceEntity = new ResourceEntity();
            List<Object> lo = list.get(i);
            resourceEntity.setArea(lo.get(1).toString());
            resourceEntity.setRtype(lo.get(2).toString());
            resourceEntity.setRname(lo.get(3).toString());
            resourceEntity.setPnumber((int)Double.parseDouble(lo.get(4).toString()));
            resourceEntity.setCnumber((int)Double.parseDouble(lo.get(5).toString()));


            resourcelist.add(resourceEntity);


        }
        resourceService.saveResourceList(resourcelist);

        return "上传成功";

    }




    @RequestMapping("gotoResource")
    public ModelAndView gotoResource(){
        List<ResourceEntity> AllList =resourceService.findAllResource();
        ModelAndView mv=new ModelAndView();
        mv.addObject("resourceList",AllList);
        mv.setViewName("resource");
        return mv;
    }
}
