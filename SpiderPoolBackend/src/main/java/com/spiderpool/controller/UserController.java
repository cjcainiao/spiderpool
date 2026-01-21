package com.spiderpool.controller;


import com.spiderpool.common.utils.ResponseResult;
import com.spiderpool.entity.dto.UserDTO;
import com.spiderpool.entity.pojo.User;
import com.spiderpool.entity.vo.UserVO;
import com.spiderpool.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户控制层
 */
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    /**
     * 用户名登录
     * @param user
     * @return
     */
    @PostMapping("/loginByUsername")
    public ResponseResult<UserVO> loginByUsername(@RequestBody User user) {
        return userService.loginByUsername(user);
    }

    @GetMapping("/logout/{userId}")
    public ResponseResult<Void> logout(@PathVariable Long userId){
        return userService.logout(userId);
    }


    /**
     * 用户名注册
     * @param userDTO
     * @return
     */
    @PostMapping("/registerByUsername")
    public ResponseResult<Boolean> registerByUsername(@RequestBody UserDTO userDTO){
        return userService.registerByUsername(userDTO);
    }


    /**
     * 修改用户信息
     * @return
     */
    @PutMapping("/updateUser")
    public ResponseResult<Boolean> updateUserById(@RequestBody User user){
        return userService.updateUserById(user);
    }


}
