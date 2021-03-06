package com.lovo.service;

import com.lovo.entity.ResourceEntity;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.InputStream;
import java.util.List;

public interface IResourceService {

    /**
     * 查询所有资源
     * @return
     */
    public List<ResourceEntity> findAllResource();
    /**
     * 根据区域外键查询所有相应资源
     * @param id 区域外键
     * @return
     */
    public List<ResourceEntity> findAllResourceByID(String id);

    /**
     * 保存资源list对象
     * @param list
     */
    public void saveResourceList(List<ResourceEntity> list);


    /**

     * @param in

     * @param fileName

     * 处理上传的excel文件

     *

     * */
    public  List<List<Object>> getresourceListByExcel(InputStream in, String fileName) throws Exception;

    /**
     * 判断excel文件的格式
     * @param inStr
     * @param fileName
     * @return
     */
    public Workbook getWorkbook(InputStream inStr, String fileName) throws Exception;


    /**
     * 分页模糊查询
     * @param pageNum 当前页数
     * @param resourceType 查询条件
     * @return
     */
    public List<ResourceEntity> findAll(int pageNum,String resourceType,String areaid);

    /**
     * 总页数
     * @param resourceType 事件实体
     * @return
     */
    public int pageAll(String resourceType,String areaid);

    /**
     * 通过单位类型（110，120，119）和地区id找到所有资源
     * @param type
     * @param areaId
     * @return
     */
    public List<ResourceEntity> findAllByTypeAndAreaId(String type,String areaId);

    ResourceEntity findByUrl(String url);

}
