package com.secondhandmarket.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.secondhandmarket.pojo.Admin;
import com.secondhandmarket.pojo.Announcement;

public interface AdminService extends IService<Admin> {

    Admin adminLogin(Admin admin);

    Announcement getAnnouncement();

    void updateAnnouncement(String name,String content);

}
