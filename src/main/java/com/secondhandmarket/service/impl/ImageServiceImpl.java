package com.secondhandmarket.service.impl;

import com.secondhandmarket.pojo.Image;
import com.secondhandmarket.mapper.ImageMapper;
import com.secondhandmarket.service.IImageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author bruce
 * @since 2021-11-01
 */
@Service
public class ImageServiceImpl extends ServiceImpl<ImageMapper, Image> implements IImageService {

}
