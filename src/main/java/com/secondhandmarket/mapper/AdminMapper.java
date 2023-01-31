package com.secondhandmarket.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.secondhandmarket.pojo.Admin;

public interface AdminMapper extends BaseMapper<Admin> {
    Admin adminLogin(Admin admin);
}
