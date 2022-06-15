package com.zut.service_parking.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zut.service_parking.entity.User;
import com.zut.service_parking.mapper.UserMapper;
import com.zut.service_parking.service.UserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-03-07
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
