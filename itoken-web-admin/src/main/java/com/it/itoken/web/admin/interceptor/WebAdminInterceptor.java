package com.it.itoken.web.admin.interceptor;

import com.it.itoken.common.domain.TbSysUser;
import com.it.itoken.common.util.CookieUtils;
import com.it.itoken.common.util.MapperUtils;
import com.it.itoken.web.admin.service.RedisService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.*;

/**
 * @author wjh
 * @create 2019-10-02 17:16
 */
public class WebAdminInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = CookieUtils.getCookieValue(request, "token");
        //token为空一定没有登录
        if (StringUtils.isBlank(token)) {
            response.sendRedirect("http://localhost:8503/login?url=http://localhost:8601");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HttpSession session = request.getSession();
        TbSysUser tbSysUser = (TbSysUser) session.getAttribute("tbSysUser");

        //已经登录状态
        if (tbSysUser != null) {
            if (modelAndView != null) {
                modelAndView.addObject("tbSysUser", tbSysUser);
            }
        }

        //未登录
        else {
            String token = CookieUtils.getCookieValue(request, "token");
            if (StringUtils.isNotBlank(token)) {
                String loginCode = redisService.get(token);
                if (StringUtils.isNotBlank(loginCode)) {
                    String json = redisService.get(loginCode);
                    if (StringUtils.isNotBlank(json)) {
                        //已登录状态,创建局部会话
                        tbSysUser = MapperUtils.json2pojo(json, TbSysUser.class);
                        if (modelAndView != null) {
                            modelAndView.addObject("tbSysUser", tbSysUser);
                        }
                        request.getSession().setAttribute("tbSysUser", tbSysUser);
                    }
                }
            }
        }
        //二次确认
        if (tbSysUser == null) {
            response.sendRedirect("http://localhost:8503/login?url=http://localhost:8601");
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
