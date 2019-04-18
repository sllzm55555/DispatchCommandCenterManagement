package com.lovo.dao;

import com.lovo.entity.EventEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;

import java.util.List;

public interface DealWithEdDao extends CrudRepository<EventEntity,String> {

    @Query("select e from EventEntity e where e.eventPeriod = ?1")
    List<EventEntity> showDealWithEdEventList(int eventPeriod, Pageable pageable);


}
