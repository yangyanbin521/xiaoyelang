package com.xiaoyelang.service.impl;


import com.xiaoyelang.common.utils.StringUtils;
import com.xiaoyelang.mapper.RoleMapper;
import com.xiaoyelang.model.Role;
import com.xiaoyelang.service.RoleService;
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
public class RoleServiceImpl implements RoleService {
     @Autowired
     private RoleMapper roleMapper;

    @Override
    public Set<String> selectRoleKeys(Integer userId) {
        List<Role> perms = roleMapper.selectRolesByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (Role perm : perms)
        {
            if (StringUtils.isNotNull(perm))
            {
                permsSet.addAll(Arrays.asList(perm.getRoleName().trim().split(",")));
            }
        }
        return permsSet;
    }
}
