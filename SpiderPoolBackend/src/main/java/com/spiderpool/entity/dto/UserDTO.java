package com.spiderpool.entity.dto;

import lombok.Data;

import java.util.Date;

/**
 * 接收用户信息
 * @TableName user
 */

@Data
public class UserDTO {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 确认密码
     */
    private String confirmPassword;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 身份证号
     */
    private String idCard;

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

}