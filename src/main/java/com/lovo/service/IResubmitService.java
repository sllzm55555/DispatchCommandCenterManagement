package com.lovo.service;

import com.lovo.dto.ResubmitDto;
import com.lovo.entity.ResourceEntity;
import com.lovo.entity.ResubmitEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 续报的service
 * @author 阿枫
 * @date 2019-04-19
 */
public interface IResubmitService {

    /**
     * 得到一个事件对应的续报，按分页显示
     * @param eventEntityId 事件的Id
     * @param pageNo 当前的页码
     * @param pageSize 每页显示的条数，使用StringUtil里面的静态属性
     * @return 返回集合
     */
    public List<ResubmitDto> findResourceEntitiesByEventEntityId(String eventEntityId, int pageNo, int pageSize,int eventPeriod);


    /**
     * 得到一个事件对应的所有续报
     * @param eventEntityId 续报对应的事件Id
     * @return 返回事件对应的所有续报的总页数
     */
    public int getAllResourNumber(String eventEntityId,int eventPeriod,String reperiod);


    /**
     * 点击未处理事件，进行处理完成之后，把之前的续报状态全部改成已处理
     * 1，表示未处理，2，表示已经处理
     * @param eventId 事件的Id
     */
    public int  changeResubmitPeriod(String eventId);


    /**
     * 得到一个事件某个阶段对应的所有续报
     * @param eventId 事件id
     * @param eventPeriod 事件的阶段
     * @param reperiod 续报的处理阶段
     * @return 返回集合
     */
    public List<ResubmitDto> findAllResubmitListByIdAndPeriod(String eventId,int eventPeriod,String reperiod);

    /**
     * 通过事件Id,和续报的进度 得到最新的续报
     * @param eventId 事件的Id

     * @return 单个续报
     */
    public ResubmitDto getHotNewsResubmit(String eventId,int eventPeriod,String reperiod);

    /**
     * 添加续报
     * @param r 续报实体
     * @return 成功返回实体，失败返回null;
     */
    public ResubmitEntity saveResubmit(ResubmitEntity r);
}
