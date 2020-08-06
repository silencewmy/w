package com.jingtong.zhgj.ops.log.controller;

import com.jingtong.zhgj.framework.page.Page;
import com.jingtong.zhgj.ops.log.service.LogService;
import com.jingtong.zhgj.ops.log.vo.LogQueryCriteriaVo;
import com.jingtong.zhgj.ops.log.vo.LogVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class LogController {

    @Resource
    LogService logService;

    /**
     * todo:接口访问url,dao层没有接收tenantId进行判断
     * 分页查询
     * @return
     */
    public Page<LogVo> page( LogQueryCriteriaVo logQueryCriteriaVo ){
        return logService.page( logQueryCriteriaVo );
    }
}
