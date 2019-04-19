package com.lovo.service.impl;

import com.lovo.dao.IPlanDao;
import com.lovo.entity.PlanEntity;
import com.lovo.service.IPlanService;
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
        int state = (pageNum - 1) * 5;
        return planDao.findAllPlanEntity(plangrade, planType, state);

    }

    @Override
    public PlanEntity savaPlanEntity(PlanEntity planEntity) {
        return planDao.save(planEntity);
    }

    @Override
    public Integer findcount(String plangrade, String planType) {
        Integer totalcount = planDao.findcount(plangrade, planType);
        Integer finishIndex = 5;
        int tapage = (totalcount + finishIndex - 1) / finishIndex;
        return tapage;
    }

}