package com.xiaoyelang.service.impl;


import com.xiaoyelang.common.exception.base.BaseException;
import com.xiaoyelang.mapper.UserMapper;
import com.xiaoyelang.model.User;
import com.xiaoyelang.service.UserService;
import com.xiaoyelang.vo.NormDataHaedExcelVo;
import com.xiaoyelang.vo.UserRoleVo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @ClassName UserServiceImpl
 * @Author 杨彦斌
 * @Date 2019/9/19 11:42
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    private final static String XLS = "xls";
    private final static String XLSX = "xlsx";

    /**
     * 查询所有用户
     * @return
     */
    @Override
    public List<User> findUser() {

        return userMapper.findUser();
    }

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    @Override
    public User findUserByLoginName(String username) {

        return  userMapper.findUserByLoginName(username);
    }

    /**
     * 注冊
     * @param user
     */
    @Override
    public void register(User user) {
        String password = user.getPassword();
        String username = user.getUsername();


        //查看库中是否有该用户
        User user2 = userMapper.findUserByLoginName(username);
        if (user2!=null){
            throw new BaseException("用户已存在");
        }

        // 将用户名作为盐值
        ByteSource salt = ByteSource.Util.bytes(username);
        /*
         * MD5加密：
         * 使用SimpleHash类对原始密码进行加密。
         * 第一个参数代表使用MD5方式加密
         * 第二个参数为原始密码
         * 第三个参数为盐值，即用户名
         * 第四个参数为加密次数
         * 最后用toHex()方法将加密后的密码转成String
         * */
        String newPs = new SimpleHash("MD5", password, salt, 1024).toHex();
        user.setPassword(newPs);
        user.setSalt(salt.toHex());
        userMapper.register(user);
    }

    @Transactional
    public void testTransactiona() {
        this.testTransactionb();
        this.testTransactionc();
    }

    @Override
    public void testTransactionb() {
        String username ="小明";
        userMapper.testTransactionb(username);
    }

    @Override
    public void testTransactionc() {
        String role ="天使";
        userMapper.testTransactionc(role);
        throw  new BaseException("baocuole啊");
    }

    @Override
    public void importExcel(Map map){
        List<NormDataHaedExcelVo> normDataHaedList = (List<NormDataHaedExcelVo>)map.get("list01");
        List normDatalist = (List) map.get("list02");

      for (int i = 0; i < normDataHaedList.size(); i++) {
            NormDataHaedExcelVo normDataHaedExcelVo = normDataHaedList.get(i);
             userMapper.addNormDataHead(normDataHaedExcelVo);

            StringBuffer norm = (StringBuffer) normDatalist.get(i);
            norm.deleteCharAt(0);
            String[] split = norm.toString().split(",");
            //Integer tyear = normDataHaedExcelVo.getTyear();
            userMapper.addNormData(split,normDataHaedExcelVo.getId());
        }
    }

    @Override
    public  List<UserRoleVo> exportExcel(){
       List<UserRoleVo>  userRoleVos = userMapper.exportExcel();
       return  userRoleVos;
    }


}
