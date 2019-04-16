package com.lovo.dao;

import com.lovo.entity.ResourceEntity;
import org.springframework.data.repository.CrudRepository;

public interface IResourceDao extends CrudRepository<ResourceEntity,String> {



}
