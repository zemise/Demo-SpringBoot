## Profile-小结

1. profile是用来完成不同环境下，动态配置切换功能的

2. profile配置方式
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

### Condition小结

- 自定义条件
    * 自定义条件类：自定义实现Condition接口，重写matches方法，在matches方法中进行逻辑判断，返回Boolean值，matches方法两个参数：
        - context: 上下文对象，可以获得属性值，获取类加载器，获取BeanFactory等
        - metadata: 元数据对象，用于获得注解属性
    * 判断条件: 在初始化Bean时，使用@Conditional(条件类.class)注解

- SpringBoot提供的常用条件注解
    * ConditionalProperties: 判断配置文件中是否有对应属性和值才初始化Bean
    * ConditionalOnClass: 判断环境中是否有对应的字节码文件才初始化Bean
    * ConditionalOnMissingBean: 判断环境中没有对应的Bean才初始化Bean

# SpringBoot自动配置

## 切换内置Web服务器

SpringBoot的Web环境默认使用类tomcat作为内置服务器，其实SpringBoot提供了4种内置服务器供我们选择，可以很方便的切换
其实现原理和上文的Condition一致，实际操作就只用改动坐标即可，如：

```xml

<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
        <exclusions>
            <!--排除tomcat依赖-->
            <exclusion>
                <artifactId>spring-boot-starter-tomcat</artifactId>
                <groupId>org.springframework.boot</groupId>
            </exclusion>
        </exclusions>
    </dependency>

    <!--引入jetty依赖-->
    <dependency>
        <artifactId>spring-boot-starter-jetty</artifactId>
        <groupId>org.springframework.boot</groupId>
    </dependency>
</dependencies>
```

## @Enable* 注解

SpringBoot中提供了很多Enable开头的注解，这些注解都用于动态启用某些功能的。其底层原理是使用@Import注解导入一些配置类，实现Bean的动态加载。

### @Import注解

@Enable* 底层是依赖于@Import注解来导入一些类，使用@Import导入的类会被Spring加载到IOC容器中 ，而@Import提供了4种用法：

- 导入Bean
- 导入配置类
- 导入ImportSelector实现类，一般用于加载配置文件种的类
- 导入ImportBeanDefinitionRegister实现类

@EnableAutoConfiguration 注解

- @EnableAutoConfiguration注解内部使用@Import(AutoConfigurationImportSelector.class)来加载配置类。
- 配置文件位置：META-INF/spring.factories,该配置文件定义了大量的配置类，当SpringBoot应用启动时，会自动加载这些配置类，初始化Bean

### 尝试自定义一个Spring boot的自动配置

`需求：自定义redis-start。要求当导入redis坐标的时候，SpringBoot自动创建redis的Bean`
实现步骤：

1. 创建redis-spring-boot-autoconfigure模块
2. 创建redis-spring-boot-stater模块，依赖redis-srping-boot-autoconfigure模块
3. 在redis-spring-boot-autoconfigure模块中初始化Jedis的Bean，并定义META-INF/spring.factories文件

# SpringBoot监听机制

Java监听机制
SpringBoot的监听机制，其实是对Java提供的事件监听机制的封装
java的事件监听机制定义了以下几个角色：
1. 事件：Event，继承java.util.EventObject类的对象
2. 事件源：Source，任意对象的Object
3. 监听器：Listener，实现java.util.EventListener接口的对象

## SpringBoot监听机制
SpringBoot在项目启动时，会对几个监听器进行回调，我们可以实现这些监听接口，在项目启动完成一些操作
ApplicationContextInitializer、SpringApplicationRunListener、CommandLineRunner、ApplicationRunner