package com.lovo.service;


import com.lovo.entity.AreaEntity;

import java.util.List;

public interface IAreaService {


    /**不带条件查询所有区域
     *
     * @return
     */
    public List<AreaEntity> findAllArea();
    /**
     *添加区域
     * @return
     */
    public AreaEntity saveArea(AreaEntity areaEntity);
    /**
     * 添加区域list对象
     * @param list
     */
    public void saveAreaList(List<AreaEntity> list);
    /**
     *删除区域
     * @return
     */
    public void deletAreaByID(String id);
    /**
     *修改区域
     * @return
     */
    public AreaEntity updateAreaByID(AreaEntity areaEntity);







    /**
     * 查询所有信息
     * @param area 区域名称
     * @return
     */
    public List<AreaEntity> findAllArea(String area,int pageNum);

    /**
     * 添加区域
     * @param areaEntity
     * @return
     */
    public AreaEntity savaAreaEntity(AreaEntity areaEntity);

    /**
     * 查询最大页数
     * @param area
     * @return
     */
    public int findcount(String area);




}
