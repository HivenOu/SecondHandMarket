package com.secondhandmarket.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.secondhandmarket.mapper.AdminMapper;
import com.secondhandmarket.pojo.Admin;
import com.secondhandmarket.service.AdminService;
import org.jboss.logging.Cause;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper,Admin> implements AdminService {

    @Autowired
    AdminMapper adminMapper;

    @Override
    public Admin adminLogin(Admin admin) {
        return adminMapper.adminLogin(admin);
    }

}
