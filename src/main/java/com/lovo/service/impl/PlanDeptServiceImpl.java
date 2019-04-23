package com.lovo.service.impl;

import com.lovo.dao.IPlanDao;
import com.lovo.dao.IPlanDeptDao;
import com.lovo.entity.PlanDeptEntity;
import com.lovo.entity.PlanEntity;
import com.lovo.service.IPlanDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("planDeptService")
public class PlanDeptServiceImpl implements IPlanDeptService {
    @Autowired
    private IPlanDao planDao;
    @Autowired
    private IPlanDeptDao planDeptDao;

    @Override
    public PlanDeptEntity savaPlanDept(PlanDeptEntity planDeptEntity) {
        return planDeptDao.save(planDeptEntity);
    }

    @Override
    @Transactional
    public void deleteplanbyid(String planid) {
        planDeptDao.deleteplanbyid(planid);
    }

    @Override
    public List<PlanDeptEntity> getPlanDeptEntitiesByPlan(String EvenType, String EvenLive) {
        String id = "";
        List<String> ids = planDao.getPlanIdBytypeandlevel(EvenType, EvenLive);
        if (ids.size() > 0) {
            id = ids.get(0);
        }
        return planDeptDao.getPlanDeptEntitiesByPlan(id);
    }

    @Override
    public List<PlanDeptEntity> getPlanDeptByPlanId(String PlanId) {
        return planDeptDao.findPlanEntityByPlanid(PlanId);
    }

    @Override
    @Transactional
    public Integer updatePlanDeptEntitiesByPlanId(String pdid, String personnum, String planresource) {
        return planDeptDao.updatePlanDeptEntitiesByPlanId(pdid,personnum,planresource);
    }
}
