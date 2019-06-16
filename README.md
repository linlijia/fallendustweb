# 准备服务器
1. 操作系统版本：centos7
2. 安装jdk1.8+
```
## java运行环境
### open-jdk
yum install java-11-openjdk.x86_64
### java_home
export JAVA_HOME=/usr/lib/jvm/jre-openjdk
export CLASSPATH=.:$JAVA_HOME/jre/lib/rt.jar:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
export PATH=$JAVA_HOME/bin:${PATH}

```

3. 安装mysql
安装步骤略
* 创建数据库
```
CREATE DATABASE IF NOT EXISTS dust  DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

```
* 初始化数据库
登录数据库，使用source 命令导入原始数据
```
source dust/db/dust.sql
```
执行升级sql，以此按顺序执行日期.sql


4. 安装redis
安装步骤略，注意为了安全起见，建议配上redis密码


# 安装dust服务
## 修改配置文件
配置文件在：src/main/resources/ 目录
配置文件都有注释，按照提示修改即可

## 编译
```
sh  control.sh build 会自动编译打包到当前目录中的dist目录。
进入dist目录会有control.sh 和dust.jar 这两个文件。
```

## 启动
sh control.sh start 即可启动程序，同时stop 和restart操作 
启动后会在相同目录创建一个app.log用于记录日志


# 安装前端
1. 安装nginx
```
yum install nginx
编辑 /etc/nginx/nginx.conf
添加
  server {
    listen       8888;
    index index.html index.html;
    root  /opt/www/dist;
    #access_log  /var/log/vhost2.com.log;
  }

 mkdir -p /opt/www/ 
 
 service nginx restart
```
2. 修改配置
* 如果需要启用代理，需要修改config中的相关配置
* 后端地址配置在src/static/config中

3. 编译 
```
npm install -g cnpm --registry=https://registry.npm.taobao.org
cnpm install
npm run build
```

4. 发布
将dist目录拷贝到/opt/www/ （nginx中配置了静态文件目录为 /opt/www/dist）


# 安装iot—gateway
## 修改配置文件
文件目录：src/main/resources/application.properties。根据注释是修改即可

## 编译打包
 mvn clean install
 编译后会在target 目录生成 iot-gateway-0.0.1-SNAPSHOT.jar
 
## 启动
nohup java -jar iot-gateway-0.0.1-SNAPSHOT.jar > iot-gateway.log 2>&1 &

# 备份
## 数据库备
```
mysqldump --result-file=/tmp/dust.sql dust  --ignore-table=dust.dust_device_status_history  --add-drop-trigger  --create-options --user=root -p --host=47.104.4.223 --port=3306
```
## redis
无状态服务，缓存数据不需要备份
## 应用程序
不需要备份，重新安装即可
## 文件
图片，历史报表等数据需要定期备份，建议将/data/dust-files文件夹远程备份，可以自己写脚本也可以使用rsyslog等工具。
# 升级
1. 备份数据库(注意dust_device_status_history这个表，历史数据量较大，且目前没有特别应用，建议不备份此表)
```
mysqldump --result-file=/tmp/dust.sql dust  --ignore-table=dust.dust_device_status_history  --add-drop-trigger  --create-options --user=root -p --host=47.104.4.223 --port=3306
```
2. 升级
替换应用并重启
3. 回滚
如果升级发现新版本有问题，停服，恢复数据库，回滚上一版本程序

# 高可用方案
1. 前面需要一个负载均衡服务，可以使用公有云的elb或者自己搭建haproxy+keepalived实现负载均衡加探活
2. 有了负载均衡，数据库可以配成主从
3. 后端服务都是无状态的，可以无限水平扩展
4. 上传的文件建议使用共享存储，可以自己搭建nfs，建议这么做比较简单。也可以使用付费的共有云对象存储。
