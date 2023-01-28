package com.secondhandmarket.service.impl;

import com.secondhandmarket.pojo.Purse;
import com.secondhandmarket.pojo.User;
import com.secondhandmarket.mapper.UserMapper;
import com.secondhandmarket.service.IPurseService;
import com.secondhandmarket.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author bruce
 * @since 2021-10-29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    IPurseService purseService;

    @Override
    @Transactional // 事务
    public int deleteBatch(String[] userIds) {
        for (String userId : userIds) {
            User user = getById(userId);
            user.setIsdel(1);//删除
            updateById(user);
        }
        return 1;
    }

    /**
     * 事务操作
     * @param user
     * @return
     */
    @Override
    @Transactional
    public int register(User user) {
        //新增User到数据库
        this.save(user);
        //新增钱包信息
        Purse purse=new Purse();
        purse.setUserId(user.getId());
        purse.setBalance(0.0F);
        //purse.setRecharge(0.0F);
        //purse.setState(0);
        //purse.setWithdrawals(0.0F);
        purseService.save(purse);
        return 1;
    }
}
