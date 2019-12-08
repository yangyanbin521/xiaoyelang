package com.xiaoyelang.mapper;

import com.xiaoyelang.model.Auth;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName AuthMapper
 * @Author 杨彦斌
 * @Date 2019/9/21 10:08
 */
@Mapper
public interface AuthMapper {

    List<Auth> selectAuthsByUserId(Integer userId);
}
