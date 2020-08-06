package com.jingtong.zhgj.ops.log.dal.dao;


import com.jingtong.zhgj.ops.log.dal.entity.LogEntity;
import com.jingtong.zhgj.ops.log.vo.LogQueryCriteriaVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LogDao {



    /**
     * 日志实体入库
     * 返回类型待定
     */

    Integer saveLog( @Param("logEntity") LogEntity logEntity );

    List<LogEntity> seleceList( @Param("logQuery") LogQueryCriteriaVo logQueryCriteriaVo, @Param("logType") Integer logType );
}
