package com.xiaoyelang.mapper;


import com.xiaoyelang.model.User;
import com.xiaoyelang.vo.NormDataHaedExcelVo;
import com.xiaoyelang.vo.UserRoleVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @ClassName UserMapper
 * @Author 杨彦斌
 * @Date 2019/9/19 11:44
 */
@Mapper
public interface UserMapper {

    List<User> findUser();

    User findUserByLoginName(@Param("username") String username);

    void register(User user);


    //测试事务
    void testTransactionb(@Param("username")String username);

    void testTransactionc(@Param("role")String role);

    void addUser(@Param("users") User users);

    void addRole(@Param("nomrs") String[] split,@Param("id") int i);

    List<UserRoleVo> exportExcel();

    void addNormDataHead(@Param("normDataHaed")NormDataHaedExcelVo normDataHaedExcelVo);

    void addNormData(@Param("nomrs") String[] split, @Param("tid")Integer id);


}
