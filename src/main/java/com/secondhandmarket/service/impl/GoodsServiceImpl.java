package com.secondhandmarket.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.secondhandmarket.mapper.GoodsInformsMapper;
import com.secondhandmarket.mapper.GoodsMapper;
import com.secondhandmarket.pojo.Goods;
import com.secondhandmarket.pojo.GoodsInforms;
import com.secondhandmarket.pojo.Image;
import com.secondhandmarket.pojo.User;
import com.secondhandmarket.service.IGoodsService;
import com.secondhandmarket.service.IImageService;
import com.secondhandmarket.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author bruce
 * @since 2021-11-01
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

    @Autowired
    IImageService iImageService;

    @Resource
    private GoodsInformsMapper goodsInformsMapper;

    @Override
    @Transactional // 事务
    public void deleteBatch(String[] goodsIds) {
        for (String goodsId : goodsIds) {
            Goods goods = getById(goodsId);
            goods.setIsdel(1);//删除
            updateById(goods);
        }
    }

    @Transactional
    @Override
    public void addGoods(Goods goods, String imgUrl, User loginUser) {
        goods.setIsdel(0);
        goods.setPolishTime(DateUtils.nowTime());
        goods.setStartTime(DateUtils.nowTime());
        goods.setStatus(1);//上架
        goods.setUserId(loginUser.getId());
        this.save(goods);

        //新增图片表
        Image image=new Image();
        image.setGoodsId(goods.getId());
        image.setImgUrl(imgUrl);
        iImageService.save(image);
    }

    @Override
    @Transactional
    public void updateGoods(Goods goods, String imgUrl) {
        //更新商品
        this.updateById(goods);
        //更新图片
        Image image = iImageService.getOne(new QueryWrapper<Image>().eq("goods_id", goods.getId()));
        image.setImgUrl(imgUrl);
        iImageService.updateById(image);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void addInformGoods(GoodsInforms informs) {
        goodsInformsMapper.insert(informs);
        //在goods表中增加被举报次数的字段 此处更新字段
        Goods byId = getById(informs.getGoodsId());
        update(new UpdateWrapper<Goods>().set(true, "inform_num", byId.getInformNum() + 1).eq("id", byId.getId()));
    }

    @Override
    public List<GoodsInforms> getInformGoods(Integer goodsId) {
        return goodsInformsMapper.selectList(new QueryWrapper<GoodsInforms>().eq(true, "goods_id", goodsId));
    }

    @Override
    public void updateStatus(Integer goodsId, Integer status) {
        update(new UpdateWrapper<Goods>().set("status", status).eq("id", goodsId));
    }
}
