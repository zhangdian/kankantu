kankantu
========
####2012-12-26 V0.0.1.0
ChangeLog:

*  添加sina weibo api到工程之中;
* 完成在maven工程下，配置sina weibo api环境;

####2012-12-27 V0.0.1.1
ChangeLog:

* 添加获取code、token接口;
* 添加简陋的登陆和后台页面;
* 添加转发指定userid的n条微博的接口;

Bug:

* 把程序部署到vps后，无法执行openUrl()函数，也就是无法执行授权;

功能点：
* 缺少filter功能;

####2012-12-28 V0.0.1.2
ChangeLog:

* 修复无法执行openurl()的bug;

功能点：

* 不直接转发微博，将微博保存到redis中;
* 开一个后台进程，读redis中的数据，然后转发;
