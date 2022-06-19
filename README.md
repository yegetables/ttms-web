# ttms-web

剧院票务管理系统---软件工程课程设计

# 部署

环境要求：

- 安装 maven
- 安装 jdk11
- 安装 nodejs,npm
- 安装 mysql/mariadb
- 安装 redis
- (可选)安装 docker

## 编译安装

1. 修改前端发送请求的url和prot(`./front/src/api/index.js`) `axios.defaults.baseURL = 'http://127.0.0.1:80'`
2. 导入数据库,创建名为`ttms`的数据库,并导入数据库文件(`web/sqldata/ttms_localhost-2022_06_09_21_58_06-dump.sql`)
3. 修改后台配置(`src/main/resources/application.properties.bak`),将其重命名为`application.properties`
   - 修改数据库连接
     ```
     spring.datasource.url=jdbc:mysql://localhost:3306/ttms?useUnicode=true&characterEncoding=utf8&useSSL=false&allowPublicKeyRetrieval=true
     spring.datasource.username=root
     spring.datasource.password=123456
     ``` 
   - 修改redis连接
     ```
     spring.redis.password=
     spring.redis.host=127.0.0.1
     ```
   - 修改端口号`server.port=80`
   - (2选1)配置手机验证码或邮箱验证码发送,自行研究 `# authCode`相关或者 `# mail`相关
4. 确认可以正常使用或连接 maven,jdk11,docker(包括登陆,拉取镜像,正常使用),nodejs,npm,mysql/mariadb,redis.确认配置端口的防火墙放行.
5. 修改`web/Dockerfile`,修改`EXPOSE`端口号
6. 修改`web/server.sh`,修改`docker run -p `端口号
7. `chmod +x deploy.sh web/web.sh front/front.sh`
8. 在当前目录下执行：`./deploy.s