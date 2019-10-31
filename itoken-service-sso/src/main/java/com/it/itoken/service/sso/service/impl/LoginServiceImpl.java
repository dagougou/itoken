package com.it.itoken.service.sso.service.impl;

import com.it.itoken.common.domain.TbSysUser;
import com.it.itoken.common.util.MapperUtils;
import com.it.itoken.service.sso.mapper.TbSysUserMapper;
import com.it.itoken.service.sso.service.LoginService;
import com.it.itoken.service.sso.service.consumer.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import tk.mybatis.mapper.entity.Example;

/**
 * @author wjh
 * @create 2019-09-28 21:14
 */
@Slf4j
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private RedisService redisService;

    @Autowired
    private TbSysUserMapper tbSysUserMapper;

    @Override
    public TbSysUser login(String loginCode, String plantPassword) {
        TbSysUser tbSysUser = null;
        String json = redisService.get(loginCode);

        //redis缓存中没有数据,从数据库取数据
        if (json == null) {
            Example example = new Example(TbSysUser.class);
            example.createCriteria().andEqualTo("loginCode", loginCode);
            tbSysUser = tbSysUserMapper.selectOneByExample(example);
            String password = DigestUtils.md5DigestAsHex(plantPassword.getBytes());
            if (tbSysUser != null && tbSysUser.getPassword().equals(password)) {
                //将信息放入缓存,保存24小时
                try {
                    redisService.put(loginCode, MapperUtils.obj2json(tbSysUser), 86400);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return tbSysUser;
            } else {
                return null;
            }
        }
        //缓存中有数据
        else {
            try {
                tbSysUser = MapperUtils.json2pojo(json, TbSysUser.class);
            } catch (Exception e) {
                log.warn("触发熔断：{}", e.getMessage());
            }
        }
        return tbSysUser;
    }
}
