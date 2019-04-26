/**
 * 预案主页controller
 * 高升
 */
package com.lovo.controller;

import com.lovo.entity.DeptEntity;
import com.lovo.entity.PlanDeptEntity;
import com.lovo.entity.PlanEntity;
import com.lovo.entity.PageBean;
import com.lovo.service.IDeptService;
import com.lovo.service.IPlanDeptService;
import com.lovo.service.IPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
public class PlanController {
    @Autowired
    private IPlanService planService;
    @Autowired
    private IDeptService deptService;
    @Autowired
    private IPlanDeptService planDeptService;

    /**
     * 显示主页表格和查询
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("findAllplan")
    public PageBean findAllplan(Integer currpage, String plangrader, String plantype) throws IOException {
        //页面集合
        List<PlanEntity> planEntities = planService.findAllPlanEntity(plangrader, plantype, currpage);
        //最大页数
        Integer totalPate = planService.findcount(plangrader, plantype);

        PageBean bean = new PageBean();
        bean.setTableBeans(planEntities);
        bean.setCurrPate(currpage);
        bean.setTotalPate(totalPate);
        return bean;
    }

    /**
     * 添加预案
     *
     * @param planname
     * @param planttpe
     * @param planeven
     * @param planDecs
     * @param presonNum
     * @param vehicle
     * @param planselect
     * @return
     */
    @RequestMapping("savaplan")
    public ModelAndView savaPlanController(String planname, String planttpe, String planeven, String planDecs,
                                           String presonNum, String vehicle, String planselect) {
        //去掉结尾逗号
        presonNum = presonNum.substring(0, presonNum.length() - 1);
        vehicle = vehicle.substring(0, vehicle.length() - 1);
        planselect = planselect.substring(0, planselect.length() - 1);
        //通过逗号切割字符串
        String[] presonNums = presonNum.split(",");//人数
        String[] vehicles = vehicle.split(",");//资源数
        String[] planselects = planselect.split(",");//单位名
        //判断数据长度相等
        if(planselects.length==vehicles.length&&vehicles.length==presonNums.length){

            //添加预案
            PlanEntity planEntity = new PlanEntity();
            planEntity.setPlanName(planname);
            planEntity.setEnevType(planttpe);
            planEntity.setEnevLeve(planeven);
            planEntity.setPlanDecs(planDecs);
            planService.savePlan(planEntity);
            //添加单位和中间表
            for (int i = 0; i < planselects.length; i++) {
                //添加单位
           /* DeptEntity deptEntity = new DeptEntity();
            deptEntity.setDeptName(planselects[i]);
            deptService.savaDeptEntity(deptEntity);*/
                DeptEntity deptEntity = deptService.findDeptEntity(planselects[i]);
                //添加中间表
                PlanDeptEntity planDeptEntity = new PlanDeptEntity();
                planDeptEntity.setPlanEntity(planEntity);
                planDeptEntity.setDeptEntity(deptEntity);
                planDeptEntity.setResource(vehicles[i]);
                planDeptEntity.setPersonNum(presonNums[i]);
                planDeptService.savePlanDept(planDeptEntity);

            }

        }


        ModelAndView mv = new ModelAndView("plan");
        return mv;
    }

    /**
     * 判断预案名字是否重复
     *
     * @param PlanName
     * @return
     */
    @RequestMapping("isPlanName")
    public void isPlanName(HttpServletResponse response, String PlanName) throws IOException {
        PlanEntity planEntity = planService.findPlanEntityByPlanName(PlanName);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        if (planEntity != null) {
            out.print("预案名重复");
        } else {
            out.print("预案名可使用");
        }

    }
    /**
     * 判断预案的类型和级别是否有相同
     *
     * @param
     * @return
     */
    @RequestMapping("isPlantype")
    public void isPlanevenandeventype(HttpServletResponse response, String planevenl,String plantype)
            throws IOException {
        int size = planDeptService.getPlanDeptEntitiesByPlan(plantype,planevenl).size();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        if (size >0) {
            out.print("已有类型和等级相同预案");
        }
    }

    /**
     * 删除预案
     *
     * @param planid
     * @return
     */
    @RequestMapping("{planid}/DeleteServlet")
    public ModelAndView deletePlan(@PathVariable("planid") String planid) {
        ModelAndView mv = new ModelAndView("plan");
        planDeptService.deletePlanbyid(planid);

        RedirectView tv = new RedirectView("/gotoPlan");
        mv.setView(tv);
        return mv;
    }

    /**
     * 查看详情
     *
     * @param planid
     * @return
     */
   /* @RequestMapping("{planid}/findPlanByPlanId")*/
    @RequestMapping("findPlanByPlanId")
    public ModelAndView findPlanByPlanId(/*@PathVariable("planid")*/ String planid) {
        ModelAndView mv = new ModelAndView("findplan");

        List<PlanDeptEntity> planDepts = planDeptService.getPlanDeptByPlanId(planid);
        mv.addObject("planDepts", planDepts);

        return mv;
    }

    /**
     * 跳转到修改页面并给输入框获取值
     *
     * @param planid
     * @return
     */
  /*  @RequestMapping("{planid}/updatafindPlanByPlanId")*/
   @RequestMapping("updatafindPlanByPlanId")
    public ModelAndView updatafindPlanByPlanId(/*@PathVariable("planid")*/ String planid) {
        ModelAndView mv = new ModelAndView("updataPlan");

        List<PlanDeptEntity> planDepts = planDeptService.getPlanDeptByPlanId(planid);
        mv.addObject("planDepts", planDepts);
        return mv;
    }

    /**
     * 修改预案
     *
     * @param planeven  预案等级
     * @param personNum 单位人数
     * @param resource  资源数
     * @param planDecs  详情
     * @return
     */
    @RequestMapping("updataplan")
    public ModelAndView updataPlan(String planeven, String personNum, String resource, String planDecs,
                                   String planDeptId, String planId) {
        ModelAndView mv = new ModelAndView("plan");
        personNum = personNum.substring(0, personNum.length() - 1);
        resource = resource.substring(0, resource.length() - 1);
        planDeptId = planDeptId.substring(0, planDeptId.length() - 1);

        String[] presonNums = personNum.split(",");//人数
        String[] resources = resource.split(",");//资源数
        String[] planDeptIds = planDeptId.split(",");//中间表id
        if(planDeptIds.length==resources.length&&resources.length==presonNums.length) {
            //修改预案表
            planService.updatePlanByPlanId(planId, planeven, planDecs);
            //修改中间表
            for (int i = 0; i < planDeptIds.length - 1; i++) {
                System.out.println(planDeptIds[i]);
                System.out.println(presonNums[i]);
                System.out.println(resources[i]);
                planDeptService.updatePlanDeptEntitiesByPlanId(planDeptIds[i], presonNums[i], resources[i]);
            }
        }
        return mv;
    }
}
