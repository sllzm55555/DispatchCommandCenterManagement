package com.lovo.service;


import com.lovo.entity.DeptEntity;

public interface IDeptService {
    /**
     * 添加单位
     * @param deptEntity
     * @return
     */
    public DeptEntity savaDeptEntity(DeptEntity deptEntity);

    /**
     * 查询单位
     * @param id
     * @return
     */
    public DeptEntity finDeptEntity(String id);
}
