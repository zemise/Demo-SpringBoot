## Profile-小结

1. profile是用来完成不同环境下，动态配置切换功能的

3. profile配置方式
    - 多profile文件方式：提供多个配置文件，每一个代表一种环境
        - application-dev.properties/yml
        - application-test.properties/yml
        - application-pro.properties/yml
    - YML多文档方式

3. profile激活方式
   - 配置文件：在配置文件中配置：spring.profiles.active=dev
   - 虚拟机参数：在VM options指定：-Dspring.profiles.active=dev
   - 命令行参数：java -jar xxx.jar --spring.profiles.active=dev

## SpringBoot配置
### 内部配置加载顺序
springboot程序启动时，会从以下位置加载配置文件
1. 当前项目的/config目录下
2. 当前项目的根目录
3. classpath的/config目录下（resource文件夹下的文件通常就打包在classpath）
4. classpath的根目录
如果这四个目录都有配置文件，实际上是都会加载。只是加载顺序为上文的排列顺序，高优先级配置的属性会生效