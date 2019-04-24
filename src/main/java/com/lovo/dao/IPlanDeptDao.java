/**
 * 预案和单位中间表Dao层
 * 高升
 */
package com.lovo.dao;

import com.lovo.entity.PlanDeptEntity;
import com.lovo.entity.PlanEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IPlanDeptDao extends CrudRepository<PlanDeptEntity, String> {
    /**
     * 根据id对预案进行删除
     *
     * @param planid
     */
    @Modifying
    @Query(value = " DELETE p.*,pd.*" +
            " FROM" +
            " t_plan_dept pd" +
            " INNER JOIN t_plan p ON p.plan_id = pd.plan_id" +
            " WHERE" +
            " p.plan_id = ?1", nativeQuery = true)
    public Integer deleteplanbyid(String planid);


    /**
     * 根据时间类型和事件等级匹配预案
     * @return
     */
    @Query(value = "SELECT " +
            " * " +
            " FROM" +
            " t_plan_dept pd" +
            " INNER JOIN t_plan p ON pd.plan_id = p.plan_id" +
            " INNER JOIN t_dept d ON pd.dept_id = d.dept_id" +
            " WHERE p.plan_id=?1 ",nativeQuery = true)
    public List<PlanDeptEntity> getPlanDeptEntitiesByPlan(String planId);

    /**
     * 查询详情
     * @param planid
     * @return
     */
    @Query(value = "SELECT " +
            " distinct pd.* " +
            " FROM" +
            " t_plan_dept pd" +
            " INNER JOIN t_plan p ON pd.plan_id = p.plan_id" +
            " INNER JOIN t_dept d ON pd.dept_id = d.dept_id" +
            " WHERE p.plan_id=?1 ",nativeQuery = true)
    public List<PlanDeptEntity> findPlanEntityByPlanid(String planid);

    /**
     * 修改中间表
     * @param pdid
     * @param personnum
     * @param planresource
     * @return
     */
    @Modifying
    @Query(value = "UPDATE t_plan_dept pd" +
        " SET" +
        " pd.plan_person_num = ?2," +
        " pd.plan_resource = ?3" +
        " WHERE" +
        " pd.pd_id = ?1 " ,nativeQuery = true)
    public Integer updatePlanDeptEntitiesByPlanId(String pdid,String personnum,String planresource);
}
