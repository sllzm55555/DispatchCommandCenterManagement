package com.lovo.dao;

import com.lovo.entity.AreaEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IAreaDao extends CrudRepository<AreaEntity,String> {

    /**
     * 主页表格查询
     * @param area
     * @param index
     * @return
     */

    @Query(value = "select * from t_area a where if(:area is not null,a.area_name like CONCAT('%',:area,'%'),1=1)" +
            " limit :index,:pagesize ", nativeQuery = true)
    public List<AreaEntity> findAllArea(@Param("area") String area, @Param("index")int index ,@Param("pagesize")int pagesize);

    /**
     * 查询最大页数
     * @param area
     * @return
     */
    @Query(value = "select * from t_area a where if(:area is not null,a.area_name like CONCAT('%',:area,'%'),1=1)", nativeQuery = true)
    public List<AreaEntity> findcount(@Param("area") String area);

    /**
     * 通过名字找区域
     * @param areaName
     * @return
     */
    public AreaEntity findByAreaName(String areaName);


}
