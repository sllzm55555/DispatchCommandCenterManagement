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
    int getPageAll();
}
