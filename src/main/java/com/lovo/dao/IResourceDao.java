package com.lovo.dao;

import com.lovo.entity.ResourceEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IResourceDao extends CrudRepository<ResourceEntity,String> {


    @Query("select r from ResourceEntity r where r.areaEntity.areaId=?1")
    public List<ResourceEntity> findAllByAreaID(String id);

}
