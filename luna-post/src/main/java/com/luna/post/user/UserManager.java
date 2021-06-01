package com.luna.post.user;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.luna.common.dto.constant.ResultCode;
import com.luna.common.encrypt.HashTools;
import com.luna.post.config.LoginInterceptor;
import com.luna.post.dto.ShowUserDTO;
import com.luna.post.entity.Register;
import com.luna.post.entity.User;
import com.luna.post.entity.UserException;
import com.luna.post.mapper.RegisterMapper;
import com.luna.post.mapper.UserMapper;
import com.luna.post.tools.UserTools;
import com.luna.post.utils.DO2DTOUtil;
import com.luna.post.utils.FileUploadUtils;
import com.luna.redis.util.RedisHashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
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

    @Autowired
    private UserTools      userTools;
    /**
     * Nginx 文件服务器
     */
    @Value("${luna.nginx.path}")
    private String         path = "http://127.0.0.1:8081";

    @Value("${luna.file.path}")
    private String         filePath;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean uploadImg(String sessionKey, MultipartFile file) {
        User user = userTools.getUser(sessionKey);
        try {
            // 上传并返回新文件名称
            String fileName = FileUploadUtils.upload(filePath, file);
            String url = path + fileName;
            Register byEntity = registerMapper.getByEntity(new Register(user.getId()));
            byEntity.setPhoto(url);
            return registerMapper.update(byEntity) == 1;
        } catch (IOException e) {
            throw new UserException(ResultCode.PARAMETER_INVALID, "上传失败");
        }
    }

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

    public int update(ShowUserDTO showUserDTO) {
        User byId = userMapper.getById(showUserDTO.getId());
        byId.setName(showUserDTO.getName());
        byId.setPassword(HashTools.md5(HashTools.md5(showUserDTO.getPassword())));
        byId.setAdmin(showUserDTO.getAdmin());
        Register byEntity = registerMapper.getByEntity(new Register(showUserDTO.getId()));

        if (byEntity == null) {
            byEntity = new Register(showUserDTO.getId());
            byEntity.setSex(showUserDTO.getSex());
            byEntity.setAge(showUserDTO.getAge());
            byEntity.setEmail(showUserDTO.getEmail());
            registerMapper.insert(byEntity);
        }

        byEntity.setSex(showUserDTO.getSex());
        byEntity.setAge(showUserDTO.getAge());
        byEntity.setEmail(showUserDTO.getEmail());
        registerMapper.update(byEntity);
        return userMapper.update(byId);
    }

    public int updateOwner(String sessionKey, ShowUserDTO showUserDTO) {
        User user = userTools.getUser(sessionKey);

        User byId = userMapper.getById(user.getId());
        Register byEntity = registerMapper.getByEntity(new Register(user.getId()));
        byEntity.setEmail(showUserDTO.getEmail());
        byEntity.setAge(showUserDTO.getAge());
        byEntity.setSex(showUserDTO.getSex());
        registerMapper.update(byEntity);
        byId.setName(showUserDTO.getName());

        return userMapper.update(byId);
    }

    public ShowUserDTO sysUserInfo(String sessionKey) {
        User user = userTools.getUser(sessionKey);

        Register byEntity = registerMapper.getByEntity(new Register(user.getId()));
        return DO2DTOUtil.user2ShowUserDTO(user, byEntity);
    }
}
