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
    public List<AreaEntity> findAllArea(String area, int pageNum) {
        //设置每页显示数量
        int pageSize = 4;
        pageNum = pageSize * (pageNum - 1);
        //把  查询条件  当前页数  每页显示数量   传参
        return areaDao.findAllArea(area,pageNum,pageSize);
    }

    @Override
    public AreaEntity savaAreaEntity(AreaEntity areaEntity) {
        return areaDao.save(areaEntity);
    }

    @Override
    public int findcount(String area) {
        //通过查询条件  查询出符合条件list
        List<AreaEntity> list = areaDao.findcount(area);
        //集合的总个数
        double allUserSize = list.size();
        int pageSize = 4;
        //向上取整   强行转换成int  总页数
        allUserSize = Math.ceil(allUserSize / pageSize);
        return (int) allUserSize;
    }

    @Override
    public AreaEntity findByAreaName(String areaName) {
        return areaDao.findByAreaName(areaName);
    }
}
