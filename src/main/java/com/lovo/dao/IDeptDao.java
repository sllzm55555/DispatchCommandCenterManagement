/**
 * 单位Dao层
 * 高升
 */
package com.lovo.dao;

import com.lovo.entity.DeptEntity;
import org.springframework.data.repository.CrudRepository;

public interface IDeptDao extends CrudRepository<DeptEntity,String> {

}
