### 某智能监控系统

#### demo
http://47.103.61.148/#/login
账号：admin
密码：123

#### 项目目录说明
- back: 后端项目
- front: 前端项目
- db: 数据库初始化 
	- init.sql  表定义
	- test.sql 测试数据插入

#### 权限说明
- 用户类型：管理员/普通用户
- 实现方式：前端页面鉴权（关键字 permission: true） + 后端接口鉴权（关键字：AuthLevel）


#### 启动说明
- 后端：
	1. 安装jdk（1.8， 推荐11or12）、maven、lombok
	2. 进入back目录，通过maven安装依赖
	3. 通过BackApplication.java主方法启动服务
	4. 详细文档请见/back/back.md

- 前端：
	1. 安装node、npm
	2. 进入front目录，执行npm i 进行依赖安装
	3. 通过执行npm run dev 启动服务 启动后会输出访问地址
	4. 通过执行npm run build 打生产包
	5. 详细文档请见/front/front.md


#### 部署说明
- 后端：
	1. 打包完成后直接 java -jar supervision-0.0.1-SNAPSHOT.jar
	2. 服务器上通过systemctl启动项目
	于/usr/lib/systemd/system 创建 supervision.service 内容如下
	```
	[Unit]
	Description=supervision
	After=syslog.target

	[Service]
	ExecStart=/u01/jdk/jdk-12.0.2/bin/java -jar -Xms2G -Xmx4G -	XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/u01/dump/  ${jar包地址}
	
	[Install]
	WantedBy=multi-user.target
	```
	执行 systemctl start supervision 启动
- 前端：
	1. 安装nginx
	2. 修改nginx.conf 
	```
	    server {
        listen       80;
        listen       [::]:80;
        server_name  _;
        root         {前端项目路径}/dist;

        # Load configuration files for the default server block.
        include /etc/nginx/default.d/*.conf;

        location / {
	    			index index.html index.htm;
        }

    }
	```

    ​      

