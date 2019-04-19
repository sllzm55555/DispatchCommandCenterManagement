package com.lovo.service.impl;

import com.lovo.dao.IPlanDeptDao;
import com.lovo.entity.PlanDeptEntity;
import com.lovo.service.IPlanDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("planDeptService")
public class PlanDeptService implements IPlanDeptService {
    @Autowired
    private IPlanDeptDao planDeptDao;

    @Override
    public PlanDeptEntity savaPlanDept(PlanDeptEntity planDeptEntity) {
        return planDeptDao.save(planDeptEntity);
    }
}
