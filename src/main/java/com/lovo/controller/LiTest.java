package com.lovo.controller;

import com.lovo.entity.AreaEntity;
import com.lovo.entity.ResourceEntity;
import com.lovo.entity.pageBean;
import com.lovo.service.IAreaService;
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
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LiTest {

    @Autowired
    private IResourceService resourceService;
    @Autowired
    private IAreaService areaService;

    /**
     * 导入资源excel文件
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="uploadresource",method= RequestMethod.POST)
    @ResponseBody
    public   String  uploadResourceExcel(HttpServletRequest request) throws Exception {

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
            resourceEntity.setRtype(lo.get(1).toString());
            resourceEntity.setRname(lo.get(2).toString());
            resourceEntity.setPnumber((int)Double.parseDouble(lo.get(3).toString()));
            resourceEntity.setCnumber((int)Double.parseDouble(lo.get(4).toString()));
            AreaEntity areaEntity = new AreaEntity();
            areaEntity.setAreaId(lo.get(5).toString());
            resourceEntity.setAreaEntity(areaEntity);

            resourcelist.add(resourceEntity);
        }
        resourceService.saveResourceList(resourcelist);

        return "上传成功";

    }


    /**
     * 导入区域excel文件
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="uploadarea",method= RequestMethod.POST)
    @ResponseBody
    public   String  uploadAreaExcel(HttpServletRequest request) throws Exception {

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
        List<AreaEntity> arealist = new ArrayList<AreaEntity>();
//        List<ResourceEntity> resourcelist = new ArrayList<ResourceEntity>();
        for (int i = 0; i < list.size(); i++) {
//            ResourceEntity resourceEntity = new ResourceEntity();
            AreaEntity areaEntity = new AreaEntity();
            List<Object> lo = list.get(i);
            areaEntity.setAreaName(lo.get(1).toString());
            arealist.add(areaEntity);
        }
//        resourceService.saveResourceList(resourcelist);
        areaService.saveAreaList(arealist);

        return "上传成功";

    }


    /**
     * 链接资源
     * @return
     */
    @RequestMapping("gotoResource")
    public ModelAndView gotoResource(){
        List<ResourceEntity> AllList =resourceService.findAllResourceByID("1");
        ModelAndView mv=new ModelAndView();
        mv.addObject("resourceList",AllList);
        mv.setViewName("resource");
        return mv;
    }

    /**
     * 链接区域
     * @return
     */
    @RequestMapping("gotoArea")
    public ModelAndView gotoArea(){
        List<AreaEntity> AllList =areaService.findAllArea();
        ModelAndView mv=new ModelAndView();
        mv.addObject("areaList",AllList);
        mv.setViewName("area");
        return mv;
    }

    /**
     * 显示主页表格和查询
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("findAllArea")
    public pageBean findAllplan(Integer currpage, String area) throws IOException {
        int pagesize =3;
        //页面集合
        List<AreaEntity> areaEntities = areaService.findAllArea(area, currpage,pagesize);
        //最大页数
        Integer totalPate= areaService.findcount(area);

        pageBean bean = new pageBean();
        bean.setTableBeans(areaEntities);
        bean.setCurrPate(currpage);
        bean.setTotalPate(totalPate);
        return bean;
    }
    /**
     * 跳转区域页面
     * @return
     */
    @RequestMapping("goToArea")
    public ModelAndView goToArea(){
        ModelAndView mv=new ModelAndView("area");
        return mv;
    }

}
