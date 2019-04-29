package com.lovo.service.impl;

import com.lovo.dao.IResourceDao;
import com.lovo.entity.ResourceEntity;
import com.lovo.service.IResourceService;
import com.lovo.util.StringUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


@Service(value="resourceService")
public class ResourceServiceImpl implements IResourceService {
    private final static String excel2003 =".xls";
    private final static String excel2010 =".xlsx";
    @Autowired
    private IResourceDao resourceDao;


    @Override
    public List<ResourceEntity> findAllResource() {
        return (List<ResourceEntity>) resourceDao.findAll();
    }

    @Override
    public List<ResourceEntity> findAllResourceByID(String id) {
        return resourceDao.findAllByAreaID(id);
    }

    @Override
    public void saveResourceList(List<ResourceEntity> list) {
        resourceDao.save(list);
    }

    @Override
    public  List<List<Object>> getresourceListByExcel(InputStream in, String fileName) throws Exception{

        List<List<Object>> list = null;

        //创建Excel工作薄

        Workbook work = this.getWorkbook(in,fileName);

        if(null == work){

            throw new Exception("创建Excel工作薄为空！");

        }

        Sheet sheet = null;

        Row row = null;

        Cell cell = null;



        list = new ArrayList<List<Object>>();

        for (int i = 0; i < work.getNumberOfSheets(); i++) {

            sheet = work.getSheetAt(i);

            if(sheet==null){continue;}



            for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {

                row = sheet.getRow(j);

                if(row==null||row.getFirstCellNum()==j){continue;}



                List<Object> li = new ArrayList<Object>();

                for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {

                    cell = row.getCell(y);

                    li.add(cell);

                }

                list.add(li);

            }

        }

        work.close();

        return list;

    }

    @Override
    public  Workbook getWorkbook(InputStream inStr,String fileName) throws Exception{

        Workbook wb = null;

        String fileType = fileName.substring(fileName.lastIndexOf("."));

        if(excel2003.equals(fileType)){

            wb = new HSSFWorkbook(inStr);

        }else if(excel2010.equals(fileType)){

            wb = new XSSFWorkbook(inStr);

        }else{

            throw new Exception("解析的文件格式有误！");

        }

        return wb;

    }




    @Override
    public List<ResourceEntity> findAll(int pageNum, String resourceType,String areaid) {
        int pageSize = 4;
        pageNum = pageSize * (pageNum - 1);
        return resourceDao.findAll(resourceType,pageNum,pageSize,areaid);
    }

    @Override
    public int pageAll( String resourceType,String areaid) {
        int pageSize = 4;
        List<ResourceEntity> list = resourceDao.findAll(resourceType,areaid);

        double allUserSize = list.size();


        allUserSize = Math.ceil(allUserSize / pageSize);

        return (int) allUserSize;

    }

    @Override
    public List<ResourceEntity> findAllByTypeAndAreaId(String type, String areaId) {
        return resourceDao.findAllByTypeAndAreaId(type, areaId);
    }

    @Override
    public ResourceEntity findByUrl(String url) {
        return resourceDao.findByUrl(url);
    }


}
