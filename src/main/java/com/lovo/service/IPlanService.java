/**
 * 预案主页接口
 * 高升
 */
package com.lovo.service;

import com.lovo.dto.DeptDto;
import com.lovo.entity.PlanEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPlanService {
    /**
     * 查询所有信息
     * @param plangrade 事件等级
     * @param planType 事件类型
     * @return
     */
    public List<PlanEntity> findAllPlanEntity(String plangrade, String planType ,Integer pageNum);


    /**
     * 查询最大页数
     * @param plangrade
     * @param planType
     * @return
     */
    public Integer findcount(String plangrade, String planType);

    /**
     * 添加预案
     * @param planEntity
     * @return
     */
    public PlanEntity savePlan(PlanEntity planEntity);

    /**
     * 根据名字进行非空判断查询
     * @param PlanName
     * @return
     */
    public PlanEntity findPlanEntityByPlanName(String PlanName);

    /**
     * 修改预案表
     * @param planid
     * @param level
     * @param desc
     * @return
     */
    public Integer updatePlanByPlanId(String planid,String level,String desc);

    /**
     *  通过预案id，得到所有的需要的单位的资源
     * @param planId 预案id
     * @return 返回符合要求的Dto集合
     */
    public List<DeptDto> getAllDept(String planId);

    public List<String> findAllEventIdByEnevTypeAndEnevLeve(String eventType,String eventLevel);

    public PlanEntity findByPlanId(String planId);

    public List<DeptDto> getAllByLeveLAndType(String eventlevel,String eventType);
}
