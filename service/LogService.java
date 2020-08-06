package com.jingtong.zhgj.ops.log.service;

import com.jingtong.zhgj.framework.page.Page;
import com.jingtong.zhgj.ops.log.model.BaseOperationModel;
import com.jingtong.zhgj.ops.log.vo.LogQueryCriteriaVo;
import com.jingtong.zhgj.ops.log.vo.LogVo;

import java.util.List;


public interface LogService {

    /**
     * 日志实体入库
     * 返回类型待定
     */

    public Integer saveLog( BaseOperationModel baseOperationModel );


    /**
     * 分页查询
     * @return
     */
    public Page<LogVo> page( LogQueryCriteriaVo logQueryCriteriaVo );

}
