package com.jingtong.zhgj.ops.log.service;


import com.jingtong.zhgj.ops.log.config.LogConfig;
import com.jingtong.zhgj.ops.log.enums.LogActEnum;
import com.jingtong.zhgj.ops.log.enums.LogTypeEnum;
import com.jingtong.zhgj.ops.log.enums.Operand;
import com.jingtong.zhgj.ops.log.enums.SubLogTypeEnum;
import com.jingtong.zhgj.ops.log.handle.BaseExtendedTypeHandler;
import com.jingtong.zhgj.ops.log.model.LogArgActorModel;
import com.jingtong.zhgj.ops.log.task.LogObjectTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
public class LogClient {


    private ExecutorService executor =
            new ThreadPoolExecutor(5,9,60, TimeUnit.SECONDS, new LinkedBlockingQueue<>(100) );

    @Autowired( required = false )
    private BaseExtendedTypeHandler baseExtendedTypeHandler;

    @Resource
    private LogService logService;


    /**
     *
     * @param argActorModel 传入的用户参数，为一个封装对象
     * @param operand 操作对象类型，参数为枚举类型，用来告知操作的对象所属类型
     * @param logTypeEnum 日志类型，告知
     * @param subLogTypeEnum 操作行为的子类型，枚举类型，通过该子类型来设置操作类型
     * @param logActEnum 操作对象类型系统划分，枚举类型
     * @param oldObject 被操作的之前的对象
     * @param newObject 操作之后的对象
     */
    public void logObject (LogArgActorModel argActorModel,
                           Operand operand, LogTypeEnum logTypeEnum, SubLogTypeEnum subLogTypeEnum,
                           LogActEnum logActEnum, Object oldObject, Object newObject ) {


        try {
            LogObjectTask logObjectTask = new LogObjectTask( argActorModel,operand,logTypeEnum,subLogTypeEnum, logActEnum,oldObject,newObject,baseExtendedTypeHandler,logService );

            executor.execute( logObjectTask );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
