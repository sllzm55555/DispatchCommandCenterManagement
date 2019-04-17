package com.lovo.util;

import com.lovo.entity.ResourceEntity;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 读取excel文件
 * 返回资源对象集合
 */
public class ResourceUtil {

    public List<ResourceEntity> importXLS(){
        ArrayList<ResourceEntity> list = new ArrayList<>();
             try {
             //1、获取文件输入流
//             InputStream inputStream = new FileInputStream("/Users/Shared/区域数据.xls");
                 InputStream inputStream = new FileInputStream("/staticResources/resource/resource.xls");
             //2、获取Excel工作簿对象
                     HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
             //3、得到Excel工作表对象
                     HSSFSheet sheetAt = workbook.getSheetAt(0);
                     //4、循环读取表格数据
              for (Row row : sheetAt) {
                 //首行（即表头）不读取
                           if (row.getRowNum() == 0) {
                                     continue;
                           }
                             //读取当前行中单元格数据，索引从0开始
                             String rid = row.getCell(0).getStringCellValue();
                             String area = row.getCell(1).getStringCellValue();
                             String rtype = row.getCell(2).getStringCellValue();
                             String rname = row.getCell(3).getStringCellValue();
                             int pnumber = Integer.parseInt(row.getCell(4).getStringCellValue());
                             int cnumber = Integer.parseInt(row.getCell(5).getStringCellValue());
                             ResourceEntity resourceEntity = new ResourceEntity();
                             resourceEntity.setRid(rid);
                             resourceEntity.setArea(area);
                             resourceEntity.setRtype(rtype);
                             resourceEntity.setRname(rname);
                             resourceEntity.setPnumber(pnumber);
                             resourceEntity.setCnumber(cnumber);
                         list.add(resourceEntity);
                        }
             //5、关闭流
                    workbook.close();
             } catch (IOException e) {
                  e.printStackTrace();
                }
        return list;
    }
}
