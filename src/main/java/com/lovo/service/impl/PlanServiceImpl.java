package com.lovo.service.impl;

import com.lovo.dao.IPlanDao;
import com.lovo.entity.PlanEntity;
import com.lovo.service.IPlanService;
import com.lovo.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "planServiceImpl")
public class PlanServiceImpl implements IPlanService {
    @Autowired
    private IPlanDao planDao;

    @Override
    public List<PlanEntity> findAllPlanEntity(String plangrade, String planType, Integer pageNum) {
        int state = (pageNum - 1) * StringUtil.PAGESIZE;
        return planDao.findAllPlanEntity(plangrade, planType, state);

    }



    @Override
    public Integer findcount(String plangrade, String planType) {
        Integer totalcount = planDao.findcount(plangrade, planType);
        Integer finishIndex = StringUtil.PAGESIZE;
        int tapage = (totalcount + finishIndex - 1) / finishIndex;
        return tapage;
    }

    @Override
    public PlanEntity savaPlan(PlanEntity planEntity) {
        return planDao.save(planEntity);
    }

}
