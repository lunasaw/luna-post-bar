# luna-post

luna-post 语音论坛

# Contributor

- Luna

# 代码规范

- 后端使用同一份代码格式化膜模板ali-code-style.xml，ecplise直接导入使用，idea使用Eclipse Code Formatter插件配置xml后使用。
- 前端代码使用vs插件的Beautify格式化，缩进使用TAB
- 后端代码非特殊情况准守P3C插件规范
- 注释要尽可能完整明晰，提交的代码必须要先格式化
- xml文件和前端一样，使用TAB缩进

# docker部署
```
docker stop luna-post 
docker rm luna-post
docker rmi luna-post

docker run -d \
    -p 8084:8084 \
    --name luna-post \
    --privileged=true \
    --restart always \
    -v /root/openresty/root/luna/post:/root/luna/post  \
    luna-post
```