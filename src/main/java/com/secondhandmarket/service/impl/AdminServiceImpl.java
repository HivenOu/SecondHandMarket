package com.secondhandmarket.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.secondhandmarket.mapper.AdminMapper;
import com.secondhandmarket.mapper.AnnouncementMapper;
import com.secondhandmarket.pojo.Admin;
import com.secondhandmarket.pojo.Announcement;
import com.secondhandmarket.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper,Admin> implements AdminService {

    @Resource
    AdminMapper adminMapper;
    @Resource
    private AnnouncementMapper announcementMapper;

    @Override
    public Admin adminLogin(Admin admin) {
        return adminMapper.adminLogin(admin);
    }

    @Override
    public Announcement getAnnouncement() {
        return announcementMapper.selectOne(new QueryWrapper<>());
    }

    @Override
    public void updateAnnouncement(String name,String content) {
        UpdateWrapper<Announcement> announcementUpdateWrapper = new UpdateWrapper<>();
        announcementMapper.update(null,
                announcementUpdateWrapper.set("todayRecommend".equals(name),"today_recommend",content)
                        .set("violationInfo".equals(name),"violation_info",content).set("almanac".equals(name),"almanac",content));
    }

}
