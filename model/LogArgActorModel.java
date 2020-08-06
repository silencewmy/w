package com.jingtong.zhgj.ops.log.model;

import com.jingtong.zhgj.framework.security.SysUser;
import com.jingtong.zhgj.framework.security.service.TokenService;
import com.jingtong.zhgj.framework.utils.IpUtils;
import com.jingtong.zhgj.framework.utils.ServletUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public class LogArgActorModel {



    @ApiModelProperty("登陆的ip地址")
    private String ipAddress;

    @ApiModelProperty("日志备注")
    private String remark;

    @ApiModelProperty("登录的用户")
    private SysUser loginUser;


    public LogArgActorModel( String remark, TokenService tokenService ) {
        this.remark = remark;
        this.ipAddress = IpUtils.getIpAddr( ServletUtils.getRequest() );
        this.loginUser = tokenService.getLoginUser(ServletUtils.getRequest()).getSysUser();
    }

}
