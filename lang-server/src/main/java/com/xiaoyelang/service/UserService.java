package com.xiaoyelang.service;

import com.xiaoyelang.model.User;
import com.xiaoyelang.vo.UserRoleVo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @ClassName UserService
 * @Author 杨彦斌
 * @Date 2019/9/19 11:42
 *
 * 此接口进行对于用户的操作
 */
public interface UserService {
    List<User> findUser();

   User findUserByLoginName(String username);

    void register(User user);


    //测试
    void testTransactionb();

    void testTransactionc();

    void importExcel(Map map);

    List<UserRoleVo> exportExcel();
}
