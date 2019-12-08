package com.xiaoyelang.mapper;

import com.xiaoyelang.model.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName RoleMapper
 * @Author 杨彦斌
 * @Date 2019/9/21 9:52
 */
@Mapper
public interface RoleMapper {
    List<Role> selectRolesByUserId(@Param("userid") Integer userId);
}
