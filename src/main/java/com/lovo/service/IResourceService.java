package com.lovo.service;

import com.lovo.entity.ResourceEntity;

import java.util.List;

public interface IResourceService {

    /**
     * 查询所有资源
     * @return
     */
    public List<ResourceEntity> findAllResource();
}
