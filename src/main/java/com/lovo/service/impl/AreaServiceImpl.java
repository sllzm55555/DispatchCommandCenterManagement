package com.lovo.service.impl;

import com.lovo.dao.IAreaDao;
import com.lovo.entity.AreaEntity;
import com.lovo.service.IAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service(value="areaService")
public class AreaServiceImpl implements IAreaService {

    @Autowired
    private IAreaDao areaDao;

    @Override
    public List<AreaEntity> findAllArea() {
        return (List<AreaEntity>) areaDao.findAll();
    }

    @Override
    public AreaEntity saveArea(AreaEntity areaEntity) {
        return areaDao.save(areaEntity);
    }

    @Override
    public void saveAreaList(List<AreaEntity> list) {
        areaDao.save(list);
    }

    @Override
    public void deletAreaByID(String id) {
         areaDao.delete(id);
    }

    @Override
    public AreaEntity updateAreaByID(AreaEntity areaEntity) {
        return areaDao.save(areaEntity);
    }








    @Override
    public List<AreaEntity> findAllArea(String area, Integer pageNum ,int pagesize) {
        int state = (pageNum - 1) * 5;
        return areaDao.findAllArea(area,state,pagesize);
    }

    @Override
    public AreaEntity savaAreaEntity(AreaEntity areaEntity) {
        return areaDao.save(areaEntity);
    }

    @Override
    public Integer findcount(String area) {
        Integer totalcount = areaDao.findcount(area);
        Integer finishIndex = 3;
        int tapage = (totalcount + finishIndex - 1) / finishIndex;
        return tapage;
    }
}
