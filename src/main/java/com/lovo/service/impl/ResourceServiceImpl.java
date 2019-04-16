package com.lovo.service.impl;

import com.lovo.dao.IResourceDao;
import com.lovo.entity.ResourceEntity;
import com.lovo.service.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service(value="resourceService")
public class ResourceServiceImpl implements IResourceService {
    @Autowired
    private IResourceDao resourceDao;


    @Override
    public List<ResourceEntity> findAllResource() {
        return (List<ResourceEntity>) resourceDao.findAll();
    }
}
