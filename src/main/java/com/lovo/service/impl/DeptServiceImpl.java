package com.lovo.service.impl;

import com.lovo.dao.IDeptDao;
import com.lovo.entity.DeptEntity;
import com.lovo.service.IDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("deptService")
public class DeptServiceImpl implements IDeptService {
    @Autowired
    private IDeptDao deptDao;

    @Override
    public DeptEntity savaDeptEntity(DeptEntity deptEntity) {
        return deptDao.save(deptEntity);
    }

    @Override
    public DeptEntity findDeptEntity(String id) {
        return deptDao.findOne(id);
    }


}
