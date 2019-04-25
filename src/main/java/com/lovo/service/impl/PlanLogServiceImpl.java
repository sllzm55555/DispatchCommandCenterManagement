package com.lovo.service.impl;

import com.lovo.dao.IPlanLogDao;
import com.lovo.entity.PlanLogEntity;
import com.lovo.service.IPlanLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "planLogService")
public class PlanLogServiceImpl implements IPlanLogService {

    @Autowired
    private IPlanLogDao planLogDao;

    @Override
    public PlanLogEntity savePlanLog(String pLogId, String operator, String operateTime, Integer operateType, String planId) {
        PlanLogEntity planLogEntity = new PlanLogEntity();
        planLogEntity.setpLogId(pLogId);
        planLogEntity.setOperator(operator);
        planLogEntity.setOperateType(operateType);
        planLogEntity.setOperateTime(operateTime);
        planLogEntity.setPlanId(planId);

        return planLogDao.save(planLogEntity);
    }

    @Override
    public List<PlanLogEntity> showPlanLogByPage(Integer currpage, Integer pageSize, String planId, Integer operateType, String operator, String operateTime) {
        if (null==planId||"".equals(planId)){
            planId=null;
        }
        if (null==operator||"".equals(operator)){
            operator=null;
        }
        if (null==operateTime||"".equals(operateTime)){
            operateTime=null;
        }
        int index = (currpage - 1)* pageSize;
        List<PlanLogEntity> planLogList = planLogDao.showPlanLogListByPage(planId, operator, operateTime, operateType, index, pageSize);
        return planLogList;
    }

    @Override
    public Integer getCount(String planId, Integer operateType, String operator, String operateTime, Integer pageSize) {
        if (null==planId||"".equals(planId)){
            planId=null;
        }

        if (null==operator||"".equals(operator)){
            operator=null;
        }
        if (null==operateTime||"".equals(operateTime)){
            operateTime=null;
        }

        int count = planLogDao.getAllPlanLogCount(planId,operateType,operator,operateTime);
        int size = pageSize;
        int totalPage = (count + size - 1)/size;
        return totalPage;
    }
}
