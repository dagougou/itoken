package com.it.itoken.web.admin.service.fallback;

import com.google.common.collect.Lists;
import com.it.itoken.common.constants.HttpStatusConstants;
import com.it.itoken.common.dto.BaseResult;
import com.it.itoken.common.hystrix.Fallback;
import com.it.itoken.common.util.MapperUtils;
import com.it.itoken.web.admin.service.AdminService;
import org.springframework.stereotype.Component;

/**
 * @author wjh
 * @create 2019-09-27 21:47
 */
@Component
public class AdminServiceFallback implements AdminService {
}
