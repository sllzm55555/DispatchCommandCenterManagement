package com.lovo.dao;

import com.lovo.entity.ResourceEntity;
import com.lovo.entity.SendResourceEntity;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IResourceDao extends CrudRepository<ResourceEntity,String> {


    @Query("select r from ResourceEntity r where r.areaEntity.areaId=?1")
    public List<ResourceEntity> findAllByAreaID(String id);

    /**
     * 模糊查询
     * @param rType  资源类型
     * @param pageSize 每页的条数
     * @param index 起始页
     * @return
     */
    @Query(value = "select * from t_resource r LEFT JOIN t_area a on a.area_id=r.area_id where "
            +"if(:rType is not null ,r.dept_type like CONCAT('%',:rType,'%'),1=1) and if(:areaid is not null ,r.area_id = :areaid"+",1=1)"
            +" limit :index,:pageSize",nativeQuery = true)
    List<ResourceEntity> findAll(@Param("rType") String rType,@Param("index")int index,@Param("pageSize")int pageSize,@Param("areaid")String areaid);

    /**
     * 根据条件查询资源的总条数
     * @param rType 资源类型
     * @return
     */
    @Query(value = "select * from t_resource r  LEFT JOIN t_area a on a.area_id=r.area_id where "
            +"if(:rType is not null ,r.dept_type like CONCAT('%',:rType,'%'),1=1) and if(:areaid is not null ,r.area_id = :areaid,1=1)",
            nativeQuery = true)
    List<ResourceEntity> findAll(@Param("rType") String rType,@Param("areaid")String areaid);

    @Query(value = "SELECT * from t_resource r WHERE r.dept_type=?1 and r.area_id=?2",nativeQuery = true)
    public List<ResourceEntity> findAllByTypeAndAreaId(String type,String areaId);

}
