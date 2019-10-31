package com.it.itoken.service.sso.service;

import com.it.itoken.common.domain.TbSysUser;

/**
 * 登录
 * @author wjh
 * @create 2019-09-28 21:13
 */
public interface LoginService {
    /**
     * 登录方法
     * @param loginCode
     * @param plantPassword
     * @return
     */
    TbSysUser login(String loginCode, String plantPassword);
}
