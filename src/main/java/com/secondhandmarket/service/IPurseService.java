package com.secondhandmarket.service;

import com.secondhandmarket.pojo.Purse;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author bruce
 * @since 2021-11-03
 */
public interface IPurseService extends IService<Purse> {

    int update(Purse p);
}
