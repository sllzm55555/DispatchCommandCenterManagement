package com.lovo.controller;

import com.lovo.entity.AreaEntity;
import com.lovo.entity.PageBean;
import com.lovo.entity.ResourceEntity;
import com.lovo.service.IAreaService;
import com.lovo.service.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

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

    //通过controller 跳转页面  否者读取不到页面样式等

    /**
     * 跳转区域页面
     * @return
     */
    @RequestMapping("goToArea")
    public ModelAndView goToArea(){
        //进入主页面
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
     * 导入区域excel文件
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="uploadarea",method= RequestMethod.POST)
    @ResponseBody
    public   ModelAndView  uploadAreaExcel(HttpServletRequest request) throws Exception {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        InputStream inputStream =null;
        List<List<Object>> list = null;
        MultipartFile file = multipartRequest.getFile("filename");
        if(file.isEmpty()){
            String massge = "添加失败,文件不能为空";
            ModelAndView mv=new ModelAndView("area");
            mv.addObject("massge",massge);
            return mv;
        }
        inputStream = file.getInputStream();
        list = resourceService.getresourceListByExcel(inputStream,file.getOriginalFilename());
        inputStream.close();
        //代码健壮性  这里验证操作
        //通过mv.addObject  键值对   发送相对应的信息到页面   页面通过   ${键}  获取到值
        List<Object> objectList = list.get(1);
        if(objectList.size()!=2){
            String massge = "添加失败，excel文件格式不正确";
            ModelAndView mv=new ModelAndView("area");
            mv.addObject("massge",massge);
            return mv;
        }else {
            //连接数据库部分  循环遍历出值  添加到实体对象中  调用业务层  添加到数据库
            List<AreaEntity> arealist = new ArrayList<AreaEntity>();
            for (int i = 0; i < list.size(); i++) {
                AreaEntity areaEntity = new AreaEntity();
                List<Object> lo = list.get(i);
                areaEntity.setAreaName(lo.get(1).toString());
                arealist.add(areaEntity);
            }
            areaService.saveAreaList(arealist);
            String massge = "添加成功";
            ModelAndView mv=new ModelAndView("area");
            mv.addObject("massge",massge);
            return mv;
        }
    }

    /**
     * 导入资源excel文件
     *  @param areaid 传进来的区域id
     * @param request 请求
     * @return
     * @throws Exception
     */
    @RequestMapping(value="uploadresource",method= RequestMethod.POST)
    @ResponseBody
    public   ModelAndView  uploadResourceExcel(HttpServletRequest request,String areaid) throws Exception {

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        InputStream inputStream =null;

        List<List<Object>> list = null;

        MultipartFile file = multipartRequest.getFile("filename");

        if(file.isEmpty()){

            String massge = "添加失败,文件不能为空";
            ModelAndView mv=new ModelAndView("resource");
            mv.addObject("massge",massge);
            mv.addObject("areaid",areaid);
            return mv;
        }
        inputStream = file.getInputStream();
        list = resourceService.getresourceListByExcel(inputStream,file.getOriginalFilename());
        inputStream.close();
        List<Object> objectList = list.get(1);
        if(objectList.size()!=6){
            String massge = "添加失败，excel文件格式不正确";
            ModelAndView mv=new ModelAndView("resource");
            mv.addObject("massge",massge);
            mv.addObject("areaid",areaid);
            return mv;
        }else {
            //连接数据库部分
            List<ResourceEntity> resourcelist = new ArrayList<ResourceEntity>();
            for (int i = 0; i < list.size(); i++) {
                ResourceEntity resourceEntity = new ResourceEntity();
                List<Object> lo = list.get(i);
                resourceEntity.setRtype(lo.get(1).toString());
                resourceEntity.setRname(lo.get(2).toString());
                resourceEntity.setPnumber((int)Double.parseDouble(lo.get(3).toString()));
                resourceEntity.setCnumber((int)Double.parseDouble(lo.get(4).toString()));
                resourceEntity.setUrl(lo.get(5).toString());
                AreaEntity areaEntity = new AreaEntity();
                //把传进来的区域id设置给  资源对象
                areaEntity.setAreaId(areaid);
                resourceEntity.setAreaEntity(areaEntity);
                resourcelist.add(resourceEntity);
            }
            resourceService.saveResourceList(resourcelist);

            String massge = "添加成功";
            ModelAndView mv=new ModelAndView("resource");
            mv.addObject("massge",massge);
            mv.addObject("areaid",areaid);
            return mv;
        }
    }

    /**
     * 显示区域主页表格和查询
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("findAllArea")
    public PageBean findAllArea(int pageNum, String area) throws IOException {

        //静茹主界面  通过ajax 预加载 进入 controller 并传参相关参数： 当前页数  查询条件
        //页面集合
        List<AreaEntity> areaEntities = areaService.findAllArea(area, pageNum);
        //最大页数
        Integer totalPate= areaService.findcount(area);
        //通过工具类  pageBean  传参  返回 pageBean 通过ajax  动态打印到页面
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
    public PageBean changePage(int pageNum,String areaid,String resourceType,HttpServletRequest request){

        //这里和第一个页面不同  这里是通过区域进入  相关区域的资源  所以需要  区域id来查询 资源
        //通过预加载  获取相关参数： 当前页数，区域id ，查询条件
        //区域页面  通过<a  ?id=...>标签  进入controller findAllResourceByID  通过隐藏表单  获取区域id  保存区域id

        //通过区域id  和查询条件  得到 集合和总条数
        int pageAll = resourceService.pageAll(resourceType,areaid);
        List<ResourceEntity> list = resourceService.findAll(pageNum,resourceType,areaid);
        //添加到工具  PageBean里面  返还到页面 通过ajax  打印出来
        PageBean<ResourceEntity> pageBean = new PageBean<>();
        pageBean.setCurrPate(pageNum);
        pageBean.setTableBeans(list);
        pageBean.setTotalPate(pageAll);
        return pageBean;
    }
    /**
     * 查看详情
     *
     * @param id
     * @return
     */
    @RequestMapping("findAllResourceByID")
    public ModelAndView findPlanByPlanId(String id) {
        ModelAndView modelAndView = new ModelAndView("resource");
        //通过addObject 键值对  保存区域id  转发到资源页面  表单中
        modelAndView.addObject("areaid",id);
        return modelAndView;
    }


    /**
     * 删除区域
     *
     * @param areaid
     * @return
     */
    @RequestMapping("{areaid}/deletAreaByID")
    public ModelAndView deletAreaByID(@PathVariable("areaid") String areaid) {
        ModelAndView mv = new ModelAndView("plan");
        areaService.deletAreaByID(areaid);
        RedirectView tv = new RedirectView("/goToArea");
        mv.setView(tv);
        return mv;
    }
    /**
     * 添加区域
     *
     * @param areaName
     * @return
     */
    @RequestMapping("addArea")
    public ModelAndView addArea(String areaName) {
        if(null!=areaName&&!"".equals(areaName)){
            List<AreaEntity> list = new ArrayList<AreaEntity>();
            list = areaService.findAllArea();
            for (AreaEntity a: list
                 ) {
                if(areaName.equals(a.getAreaName())){

                    String massge = "添加失败，已经存在相同区域名称";
                    ModelAndView mv=new ModelAndView("area");
                    mv.addObject("massge",massge);
                    return mv;
                }
            }

            AreaEntity areaEntity = new AreaEntity();
            areaEntity.setAreaName(areaName);
            areaService.saveArea(areaEntity);
        }
        String massge = "添加成功";
        ModelAndView mv=new ModelAndView("area");
        mv.addObject("massge",massge);
        return mv;
    }

}
