package com.lovo.dao;

import com.lovo.entity.PlanEntity;
import com.lovo.entity.ResourceEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IPlanDao extends CrudRepository<PlanEntity, String> {
  /*  @Query("select p from PlanEntity p where p.enevLeve=?1 and  p.enevType=?2 ")
    public List<PlanEntity> findAllPlanEntity(String plangrade, String planType, Pageable pageable);*/

    /**
     * 主页表格查询
     *
     * @param plangrade
     * @param planType
     * @param pageNum
     * @return
     */
    @Query(value = "select p.* from t_plan p where if(?1 !='',event_level=?1,1=1) and if(?2 !='' ,event_type=?2,1=1)" +
            " limit ?3,10 "
            , nativeQuery = true)
    public List<PlanEntity> findAllPlanEntity(String plangrade, String planType, Integer pageNum);

    /**
     * 查询最大页数
     *
     * @param plangrade
     * @param planType
     * @return
     */
    @Query(value = "SELECT COUNT(*) FROM t_plan where if(?1 !='',event_level=?1,1=1) and if(?2 !='' ,event_type=?2,1=1) ",
            nativeQuery = true)
    public Integer findcount(String plangrade, String planType);

    /**
     * 根据名字进行非空判断查询
     *
     * @param PlanName
     * @return
     */
    public PlanEntity findPlanEntityByPlanName(String PlanName);

    /*
     * 根据事件的类型和级别查询出事件id
     * @return
     */
    @Query(value = "SELECT p.plan_id FROM t_plan p where p.event_type=?1 and p.event_level=?2 ", nativeQuery = true)
    public List<String> getPlanIdBytypeandlevel(String evenType, String evenLive);

    /**
     * 对预案表进行修改
     * @return
     */
    @Modifying
    @Query(value = "UPDATE t_plan p" +
            " SET " +
            " p.event_level=?2," +
            " p.plan_desc=?3" +
            " WHERE p.plan_id=?1", nativeQuery = true)
    public Integer updataPlanByPlanId(String planid,String level,String desc);
}
