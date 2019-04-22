package com.lovo.controller;

import com.lovo.entity.AreaEntity;
import com.lovo.entity.PageBean;
import com.lovo.entity.ResourceEntity;
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
     * 跳转区域页面
     * @return
     */
    @RequestMapping("goToArea")
    public ModelAndView goToArea(){
        ModelAndView mv=new ModelAndView("area");
        return mv;
    }
    /**
     * 跳转资源页面
     * @return
     */
    @RequestMapping("goToResource")
    public ModelAndView goToResource(){
        ModelAndView mv=new ModelAndView("resource");
        return mv;
    }



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
     * 显示区域主页表格和查询
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("findAllArea")
    public PageBean findAllArea(int pageNum, String area) throws IOException {

        //页面集合
        List<AreaEntity> areaEntities = areaService.findAllArea(area, pageNum);
        //最大页数
        Integer totalPate= areaService.findcount(area);

        PageBean bean = new PageBean();
        bean.setTableBeans(areaEntities);
        bean.setCurrPate(pageNum);
        bean.setTotalPate(totalPate);
        return bean;
    }

    /**
     * 显示资源主页表格和查询
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("findAllResource")
    public PageBean changePage(int pageNum,String resourceType){
        List<ResourceEntity> list = resourceService.findAll(pageNum,resourceType);
        int pageAll = resourceService.pageAll(resourceType);
        PageBean<ResourceEntity> pageBean = new PageBean<>();
        pageBean.setCurrPate(pageNum);
        pageBean.setTableBeans(list);
        pageBean.setTotalPate(pageAll);
        return pageBean;
    }

}
