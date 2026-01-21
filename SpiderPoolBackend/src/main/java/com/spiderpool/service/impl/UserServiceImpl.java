package com.spiderpool.service.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spiderpool.common.constants.USER;
import com.spiderpool.common.utils.JwtUtils;
import com.spiderpool.common.utils.PageResultUtils;
import com.spiderpool.common.utils.PasswordEncryptUtils;
import com.spiderpool.common.utils.ResponseResult;
import com.spiderpool.entity.dto.UserDTO;
import com.spiderpool.entity.pojo.User;
import com.spiderpool.entity.vo.UserVO;
import com.spiderpool.exception.BusinessException;
import com.spiderpool.mapper.UserMapper;
import com.spiderpool.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 用户表(User)表服务实现类
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserMapper userMapper;


    /**
     * 用户名登录
     * @param user
     * @return
     */
    public ResponseResult<UserVO> loginByUsername(User user) {
        //1、校验参数
        if(Objects.isNull(user) || Objects.isNull(user.getUsername())|| Objects.isNull(user.getPassword())){
            throw new BusinessException(400,"参数校验失败!!!");
        }
        //2、查询用户信息
        User u = this.getOne(new LambdaQueryWrapper<>(User.class).eq(User::getUsername, user.getUsername()));
        if(Objects.isNull(u)){
            throw new BusinessException(400,"用户不存在!!!");
        }

        //3、判断密码是否正确
        if(!PasswordEncryptUtils.verify(user.getPassword(),u.getPassword())){
            throw new BusinessException(400,"用户名或密码错误!!!");
        }

        UserVO userVO = BeanUtil.copyProperties(u, UserVO.class);

        //4、生成token
        Map<String, Object> cliams = new HashMap<>();
        cliams.put("username",u.getUsername());
        cliams.put("userId",u.getUserId());
        userVO.setToken(jwtUtils.generateToken(cliams));

        //todo 查询用户的角色信息

        //5、缓存用户信息
        redisTemplate.opsForValue().set(USER.LOGIN_USER+u.getUserId(),userVO);

        //返回
        return ResponseResult.success("登录成功",userVO);
    }

    /**
     * 用户名注册
     * @param userDTO
     * @return
     */
    public ResponseResult<Boolean> registerByUsername(UserDTO userDTO) {
        //1、校验参数
        if(Objects.isNull(userDTO) || Objects.isNull(userDTO.getUsername())
                || Objects.isNull(userDTO.getPassword()) || Objects.isNull(userDTO.getConfirmPassword())){
            throw new BusinessException(400,"参数校验失败!!!");
        }

        if(!userDTO.getPassword().equals(userDTO.getConfirmPassword())){
            throw new BusinessException(400,"密码不一致!!!");
        }

        //2、判断用户名是否已存在
        if(this.count(new LambdaQueryWrapper<>(User.class).eq(User::getUsername, userDTO.getUsername())) > 0){
            throw new BusinessException(400,"用户名已存在!!!");
        }
        //3、密码加密
        userDTO.setPassword(PasswordEncryptUtils.encrypt(userDTO.getPassword()));

        //4、保存用户信息
        boolean save = this.save(BeanUtil.copyProperties(userDTO, User.class));

        if (!save){
            throw new BusinessException(400,"注册失败!!!");
        }
        return ResponseResult.success("注册成功",true);
    }

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    public ResponseResult<Boolean> updateUserById(User user) {
        //1、校验参数
        if(Objects.isNull(user) || Objects.isNull(user.getUserId())){
            throw new BusinessException(400,"参数校验失败!!!");
        }

        //2、查询用户信息
        User u = this.getById(user.getUserId());
        if(Objects.isNull(u)){
            throw new BusinessException(400,"用户不存在!!!");
        }

        //4、判断密码是否修改
        if(!Objects.isNull(user.getPassword())){
            user.setPassword(PasswordEncryptUtils.encrypt(user.getPassword()));
        }

        //3、修改用户信息
        //设置修改时间
        user.setUpdateTime(new DateTime());
        boolean b = this.updateById(user);
        if (!b){
            throw new BusinessException(400,"更新用户失败!!!");
        }
        return ResponseResult.success("更新用户成功",true);
    }

    /**
     * 注销
     * @param userId
     * @return
     */
    public ResponseResult<Void> logout(Long userId) {

        if(Objects.isNull(userId)){
            throw new BusinessException(400,"参数校验失败!!!");
        }
        //1、删除缓存
        Boolean delete = redisTemplate.delete(USER.LOGIN_USER + userId);

        return ResponseResult.success("注销成功",null);
    }

    /**
     * 管理员获取用户列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    public ResponseResult<PageResultUtils> getUserPageList(int pageNum, int pageSize) {

        Page<User> userPage = new Page<>(pageNum, pageSize);

        Page<User> page = userMapper.selectPage(userPage, null);

        PageResultUtils pageResultUtils = new PageResultUtils();
        pageResultUtils.setPageNum(page.getCurrent());
        pageResultUtils.setPageSize(page.getSize());
        pageResultUtils.setTotal(page.getTotal());
        pageResultUtils.setData(page.getRecords());

        return ResponseResult.success("获取用户列表成功",pageResultUtils);
    }
}




