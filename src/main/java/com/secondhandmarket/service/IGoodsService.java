package com.secondhandmarket.service;

import com.secondhandmarket.pojo.Goods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.secondhandmarket.pojo.GoodsInforms;
import com.secondhandmarket.pojo.User;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author bruce
 * @since 2021-11-01
 */
public interface IGoodsService extends IService<Goods> {

    void deleteBatch(String[] goodsIds);

    void addGoods(Goods goods, String imgUrl, User loginUser);

    void updateGoods(Goods goods, String imgUrl);

    void addInformGoods(GoodsInforms informs);

    List<GoodsInforms> getInformGoods(Integer goodsId);

    void updateStatus(Integer goodsId, Integer status);

}
