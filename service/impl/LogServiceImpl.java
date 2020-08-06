package com.jingtong.zhgj.ops.log.service.impl;

import com.github.pagehelper.PageInfo;
import com.jingtong.zhgj.framework.bean.IdName;
import com.jingtong.zhgj.framework.exception.ZhgjApiException;
import com.jingtong.zhgj.framework.page.Page;
import com.jingtong.zhgj.framework.page.Paging;

import com.jingtong.zhgj.ops.log.dal.dao.LogDao;
import com.jingtong.zhgj.ops.log.dal.entity.LogEntity;
import com.jingtong.zhgj.ops.log.enums.LogActEnum;
import com.jingtong.zhgj.ops.log.enums.LogTypeEnum;
import com.jingtong.zhgj.ops.log.enums.Operand;
import com.jingtong.zhgj.ops.log.enums.SubLogTypeEnum;
import com.jingtong.zhgj.ops.log.model.BaseOperationModel;
import com.jingtong.zhgj.ops.log.service.LogService;
import com.jingtong.zhgj.ops.log.vo.LogQueryCriteriaVo;
import com.jingtong.zhgj.ops.log.vo.LogVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LogServiceImpl implements LogService {

    @Resource
    private LogDao logDao;

    @Override
    public Integer saveLog( BaseOperationModel baseOperationModel ) {



        /**
         * 类型转换，String转为Integer
         */
        LogEntity logEntity = new LogEntity();
        BeanUtils.copyProperties( baseOperationModel, logEntity );

        logEntity.setLogActType( LogActEnum.nameGetType(baseOperationModel.getLogActType()) );
        logEntity.setLogType( LogTypeEnum.nameGetType( baseOperationModel.getLogType()) );
        logEntity.setSubLogType( SubLogTypeEnum.nameGetType( baseOperationModel.getSubLogType()) );
        logEntity.setOperand( Operand.nameGetType(baseOperationModel.getOperand())  );


        /**
         * todo:测试使用,待删除
         */
        {
            LogVo logVo = new LogVo();
            //属性值传递
            BeanUtils.copyProperties( baseOperationModel, logVo );
            System.out.println( logVo.toString() );
            System.out.println( logEntity.toString() );
        }

        /**
         * TODO：查询测试 待删除，这里仅仅测试了dao层，没有测试service层的分页封装
         */
        {
            LogQueryCriteriaVo logQueryCriteriaVo = new LogQueryCriteriaVo();
            logQueryCriteriaVo.setLogType( LogTypeEnum.WEB.getName() );
            logQueryCriteriaVo.setLineName("test");

            Integer logType = LogTypeEnum.nameGetType( logQueryCriteriaVo.getLogType() );

            List<LogEntity> list = logDao.seleceList(  logQueryCriteriaVo, logType );
            System.out.println( list.size() );
            System.out.println( list.toArray().toString()  );

            System.out.println( list.toString() );
        }


        return logDao.saveLog( logEntity );



    }

    @Paging
    @Override
    public Page<LogVo> page( LogQueryCriteriaVo logQueryCriteriaVo) {

        Integer logType = LogTypeEnum.nameGetType( logQueryCriteriaVo.getLogType() );

        List<LogEntity> list = logDao.seleceList(  logQueryCriteriaVo, logType );


        PageInfo<LogEntity> logEntityPageInfo = new PageInfo<>(list);

        //去除重复元素,该链表内的数据为日志的id
        List<Long> ids = list.stream().map( x -> x.getId() ).distinct().collect(Collectors.toList());

        //TODO:按照条件查询数据库没有数据，如何处理待增加
        if (ids.size() == 0) {
            throw new ZhgjApiException(400, "查无此站点,请检查输入的站点名称信息");
        }

        List<LogVo> logVos = new ArrayList<>( list.size() );

        for ( LogEntity logEntity : list ) {
            LogVo logVo = new LogVo();

            //两种实体的转换
            BeanUtils.copyProperties( logEntity, logVo );

            /**
             * 类型转换 Integer转String
             */
            logVo.setLogActType( LogActEnum.typeGetName(logEntity.getLogActType()) );
            logVo.setLogType( LogTypeEnum.typeGetName(logEntity.getLogType()) );
            logVo.setSubLogType( SubLogTypeEnum.typeGetName(logEntity.getSubLogType()) );
            logVo.setOperand( Operand.typeGetName(logEntity.getOperand()) );

            logVos.add( logVo );
        }

        Page<LogVo> logVoPage = new Page<>(logVos);
        logEntityPageInfo.setTotal( logEntityPageInfo.getTotal() );

        return logVoPage;

    }

}
