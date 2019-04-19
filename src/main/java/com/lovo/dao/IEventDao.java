package com.lovo.dao;

import com.lovo.entity.EventEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IEventDao extends CrudRepository<EventEntity,String> {


    @Query(value = "",nativeQuery = true)
    public List<EventEntity> getEventEntityList(Pageable pageable);
}
