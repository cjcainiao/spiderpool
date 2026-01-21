package com.spiderpool.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 * 用户返回
 * @TableName user
 */
@Data
public class UserVO {
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户名
     */
    private String username;


    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;


    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 性别 1男 0女 2未知
     */
    private Integer gender;

    /**
     * 出生日期
     */
    private Date birthday;

    /**
     * 最后登录时间
     */
    private Date lastLoginTime;

    /**
     * 最后登录ip
     */
    private String lastLoginIp;

    /**
     * 用户状态 1正常 0禁用
     */
    private Integer userStatus;

    /**
     *  token
     */
    private String token;


    /**
     * 角色信息
     */
    private Map<String,Integer> roles;
}