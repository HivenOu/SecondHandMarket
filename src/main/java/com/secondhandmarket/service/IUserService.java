package com.secondhandmarket.service;

import com.secondhandmarket.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author bruce
 * @since 2021-10-29
 */
public interface IUserService extends IService<User> {

    int deleteBatch(String[] userIds);

    int register(User user);

}
