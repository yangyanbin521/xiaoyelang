package com.xiaoyelang.service.impl;


import com.xiaoyelang.common.utils.StringUtils;
import com.xiaoyelang.mapper.AuthMapper;
import com.xiaoyelang.model.Auth;
import com.xiaoyelang.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ClassName LoginServiceImpl
 * @Author 杨彦斌
 * @Date 2019/9/20 10:53
 */
@Service
public class AuthServiceImpl implements AuthService {
     @Autowired
     private AuthMapper authMapper;

     @Override
     public Set<String> selectPermsByUserId(Integer userId) {
          List<Auth> perms = authMapper.selectAuthsByUserId(userId);
          Set<String> permsSet = new HashSet<>();
          for (Auth perm : perms)
          {
               if (StringUtils.isNotNull(perm))
               {
                    permsSet.addAll(Arrays.asList(perm.getAuthName().trim().split(",")));
               }
          }
          return null;
     }
}
