/**
 * 预案主页controller
 * 高升
 */
package com.lovo.controller;

import com.lovo.entity.PlanEntity;
import com.lovo.entity.pageBean;
import com.lovo.service.IPlanService;
import com.netflix.client.http.HttpResponse;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class PlanController {
    @Autowired
    private IPlanService planService;

    /**
     * 显示主页表格和查询
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("findAllplan")
    public pageBean findAllplan(Integer currpage, String plangrader, String plantype) throws IOException {
        //页面集合
        List<PlanEntity> planEntities = planService.findAllPlanEntity(plangrader, plantype, currpage);
        //最大页数
       Integer totalPate= planService.findcount(plangrader,plantype);

        pageBean bean = new pageBean();
        bean.setTableBeans(planEntities);
        bean.setCurrPate(currpage);
        bean.setTotalPate(totalPate);
        return bean;
    }

}
