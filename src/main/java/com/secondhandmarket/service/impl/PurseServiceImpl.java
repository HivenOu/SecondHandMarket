package com.secondhandmarket.service.impl;

import com.secondhandmarket.pojo.Purse;
import com.secondhandmarket.mapper.PurseMapper;
import com.secondhandmarket.service.IPurseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author bruce
 * @since 2021-11-03
 */
@Service
public class PurseServiceImpl extends ServiceImpl<PurseMapper, Purse> implements IPurseService {

    @Resource
    PurseMapper purseMapper;

    @Override
    public int update(Purse p) {
        return purseMapper.update(p);
    }
}
