package com.lovo.service;

import com.lovo.entity.ResourceEntity;

import java.util.List;

public interface IResourceService {

    /**
     * 查询所有资源
     * @return
     */
    public List<ResourceEntity> findAllResource();

    /**
     * 保存资源list对象
     * @param list
     */
    public void saveResourceList(List<ResourceEntity> list);
}
