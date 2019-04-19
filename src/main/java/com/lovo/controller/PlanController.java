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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
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
        //添加预案
        PlanEntity planEntity = new PlanEntity();
        planEntity.setPlanName(planname);
        planEntity.setEnevType(planttpe);
        planEntity.setEnevLeve(planeven);
        planEntity.setPlanDecs(planDecs);
        planService.savaPlan(planEntity);
        //添加单位和中间表
        for (int i = 0; i<planselects.length; i++) {
            //添加单位
            DeptEntity deptEntity = new DeptEntity();
            deptEntity.setDeptName(planselects[i]);
            deptService.savaDeptEntity(deptEntity);
            //添加中间表
            PlanDeptEntity planDeptEntity = new PlanDeptEntity();
            planDeptEntity.setPlanEntity(planEntity);
            planDeptEntity.setDeptEntity(deptEntity);
            planDeptEntity.setResource(vehicles[i]);
            planDeptEntity.setPersonNum(presonNums[i]);
            planDeptService.savaPlanDept(planDeptEntity);

        }

        ModelAndView mv = new ModelAndView("plan");
        return mv;
    }
}
