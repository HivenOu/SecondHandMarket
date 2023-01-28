package com.secondhandmarket.service.impl;

import com.secondhandmarket.pojo.Comments;
import com.secondhandmarket.mapper.CommentsMapper;
import com.secondhandmarket.service.ICommentsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author bruce
 * @since 2021-11-02
 */
@Service
public class CommentsServiceImpl extends ServiceImpl<CommentsMapper, Comments> implements ICommentsService {

}
