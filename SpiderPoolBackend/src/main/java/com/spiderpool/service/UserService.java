package com.spiderpool.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.spiderpool.common.utils.PageResultUtils;
import com.spiderpool.common.utils.ResponseResult;
import com.spiderpool.entity.dto.UserDTO;
import com.spiderpool.entity.pojo.User;
import com.spiderpool.entity.vo.UserVO;

/**
 * 用户表(User)表服务接口
 */
public interface UserService extends IService<User> {


    /**
     * 用户登录
     * @param user
     * @return
     */
    ResponseResult<UserVO> loginByUsername(User user);

    /**
     * 用户名注册
     * @param userDTO
     * @return
     */
    ResponseResult<Boolean> registerByUsername(UserDTO userDTO);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    ResponseResult<Boolean> updateUserById(User user);


    /**
     * 登出
     * @param userId
     * @return
     */
    ResponseResult<Void> logout(Long userId);


    /**
     * 获取用户列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    ResponseResult<PageResultUtils> getUserPageList(int pageNum, int pageSize);
}
