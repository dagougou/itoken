package com.it.itoken.service.sso.controller;

import com.it.itoken.common.domain.TbSysUser;
import com.it.itoken.common.util.CookieUtils;
import com.it.itoken.common.util.MapperUtils;
import com.it.itoken.service.sso.service.LoginService;
import com.it.itoken.service.sso.service.consumer.RedisService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author wjh
 * @create 2019-09-28 21:36
 */
@Controller
public class LoginController {

    @Autowired
    private RedisService redisService;

    @Autowired
    private LoginService loginService;

    /**
     * 跳转登录页面
     *
     * @return
     */
    @GetMapping("/login")
    public String login(@RequestParam(required = false) String url, HttpServletRequest request, Model model) {
        String token = CookieUtils.getCookieValue(request, "token");
        //token不为空 可能已经登录
        if (StringUtils.isNotBlank(token)) {
            String loginCode = redisService.get(token);
            if (StringUtils.isNotBlank(loginCode)) {
                String json = redisService.get(loginCode);
                if (StringUtils.isNotBlank(json)) {
                    try {
                        TbSysUser tbSysUser = MapperUtils.json2pojo(json, TbSysUser.class);
                        //已经登录
                        if (tbSysUser != null) {
                            if (StringUtils.isNotBlank(url)) {
                                return "redirect:" + url;
                            }
                        }
                        //将登录信息传到登录页
                        model.addAttribute("tbSysUser", tbSysUser);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        if (StringUtils.isNotBlank(url)) {
            model.addAttribute("url", url);
        }

        return "login";
    }

    /**
     * 登录业务
     *
     * @param loginCode
     * @param password
     * @return
     */
    @PostMapping("/login")
    public String login(
            @RequestParam(required = true) String loginCode,
            @RequestParam(required = true) String password,
            @RequestParam(required = false) String url,
            HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        TbSysUser tbSysUser = loginService.login(loginCode, password);

        //登录失败
        if (tbSysUser == null) {
            redirectAttributes.addFlashAttribute("message", "用户名或密码错误，请重新输入");
        }

        //登录成功
        else {
            String token = UUID.randomUUID().toString();
            //将token放入缓冲
            String result = redisService.put(token, loginCode, 60 * 60 * 24);
            if (StringUtils.isNotBlank(result) && "ok".equals(result)) {
                CookieUtils.setCookie(request, response, "token", token, 60 * 60 * 24);
                if (StringUtils.isNotBlank(url)) {
                    return "redirect:" + url;
                }
            }

            //熔断处理
            else {
                redirectAttributes.addFlashAttribute("message", "服务器异常，请稍后再试");
            }
        }

        return "redirect:/login";
    }

    /**
     * 登出
     * @param request
     * @param response
     * @param url
     * @param model
     * @return
     */
    @GetMapping("logout")
    public String logout(HttpServletRequest request,HttpServletResponse response,@RequestParam(required = false) String url,Model model){
        CookieUtils.deleteCookie(request,response,"token");
        return login(url,request,model);
    }
}
