package com.lovo.service;

import com.lovo.entity.PlanLogEntity;

import java.util.List;

/**
 * 预案日志接口
 *
 * 刘金林
 */
public interface IPlanLogService {
    /**
     * 保存预案操作日志
     * @param pLogId 预案日志id
     * @param operator 操作者
     * @param operateTime 操作时间
     * @param operateType 操作类型
     * @param planId  预案id
     * @return
     */
    public PlanLogEntity savePlanLog(String pLogId,String operator,String operateTime,Integer operateType,String planId);


    /**
     * 多条件分页查询，得到满足条件的日志
     * @param currpage 当前页数
     * @param pageSize 每页显示条数
     * @param  planId  预案id
     * @param operateType 操作类型
     * @param operator 操作者
     * @param operateTime 操作时间
     * @return
     */
    public List<PlanLogEntity> showPlanLogByPage(Integer currpage, Integer pageSize, String planId, Integer operateType, String operator, String operateTime);


    /**
     * 得到满足情况的总页数
     * @param planId 预案id
     * @param operateType
     * @param operator
     * @param operateTime
     * @param pageSize
     * @return
     */
    public Integer getCount(String planId, Integer operateType, String operator, String operateTime,Integer pageSize);

}
