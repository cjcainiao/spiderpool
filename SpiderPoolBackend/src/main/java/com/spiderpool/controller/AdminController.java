package com.spiderpool.controller;


import com.spiderpool.common.utils.PageResultUtils;
import com.spiderpool.common.utils.ResponseResult;
import com.spiderpool.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * 管理员控制层
 */

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    /**
     * 获取用户分页列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @PostMapping("/getUserPageList")
    public ResponseResult<PageResultUtils> getUserPageList(int pageNum, int pageSize){
        return userService.getUserPageList(pageNum,pageSize);
    }
}
