package com.lovo.service;

import com.lovo.entity.EventEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public interface IDealWithEdService {

    /**
     * 处理完成事件分页
     * @param pageNum 页数
     * @return
     */
    List<EventEntity> showDealWithEdEventList(int pageNum);

    /**
     * 查询所有的页数
     * @return
     */
    int getPageAll(int pageNum);


    /**
     * 分页模糊查询
     * @param pageNum 当前页数
     * @param event 查询条件
     * @return
     */
    List<EventEntity> findAll(int pageNum,EventEntity event);

    /**
     * 总页数
     * @param pageNum 当前页数
     * @param event 事件实体
     * @return
     */
    int pageAll(int pageNum,EventEntity event);
}
