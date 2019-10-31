package com.it.itoken.common.web.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * churu
 *
 * @author wjh
 * @create 2019-10-02 16:41
 */
public class ConstantsInterceptor implements HandlerInterceptor {
    private static final String HOST_CDN = "http://192.168.182.130:81";

    private static final String TEMPLATE_ADMIN_LTE = "/adminlte/v2.4.18";


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            modelAndView.addObject("adminlte", HOST_CDN + "/" + TEMPLATE_ADMIN_LTE);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
