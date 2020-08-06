package com.jingtong.zhgj.ops.log.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class LogConfig {

    /**
     * wmyxiugai
     */
//    @Value(" ${zhgj.log.autoLogAttributes}")
    private static String autoLogAttributes = "true";


}
