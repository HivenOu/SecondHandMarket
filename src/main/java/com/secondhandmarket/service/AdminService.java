package com.secondhandmarket.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.secondhandmarket.pojo.Admin;

import java.util.List;

public interface AdminService extends IService<Admin> {

    Admin adminLogin(Admin admin);

}
