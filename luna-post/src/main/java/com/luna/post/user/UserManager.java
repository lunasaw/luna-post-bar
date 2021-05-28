package com.luna.post.user;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.luna.post.dto.ShowUserDTO;
import com.luna.post.entity.Register;
import com.luna.post.entity.User;
import com.luna.post.mapper.RegisterMapper;
import com.luna.post.mapper.UserMapper;
import com.luna.post.utils.DO2DTOUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author luna
 * 2021/5/28
 */
@Service
public class UserManager {

    @Resource
    private UserMapper     userMapper;

    @Resource
    private RegisterMapper registerMapper;

    public PageInfo<ShowUserDTO> listPageByEntity(int page, int size, User user) {
        PageHelper.startPage(page, size);
        List<User> users = userMapper.listByEntity(user);
        PageInfo<ShowUserDTO> pageInfo = new PageInfo(users);
        List<ShowUserDTO> collect = users.stream().map(userTemp -> {
            Register byEntity = registerMapper.getByEntity(new Register(userTemp.getId()));
            return DO2DTOUtil.user2ShowUserDTO(userTemp, byEntity);
        }).collect(Collectors.toList());
        pageInfo.setList(collect);
        return pageInfo;
    }
}
