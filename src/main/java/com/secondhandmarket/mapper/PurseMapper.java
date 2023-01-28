package com.secondhandmarket.mapper;

import com.secondhandmarket.pojo.Purse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author bruce
 * @since 2021-11-03
 */
public interface PurseMapper extends BaseMapper<Purse> {


    int update(Purse p);
}
