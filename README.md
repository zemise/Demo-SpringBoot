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

## SpringBoot启动流程分析

em....这部分，似乎就看了个大概，实际没明白
可能...就学会了替换banner.txt

## SpringBoot监控

### SpringBoot监控概述

SpringBoot自带监控功能插件Actuator，可以帮助实现对程序内部运行情况监控，比如监控状况、Bean加载情况、配置属性、日志信息等

### SpringBoot监控使用

1. 引入依赖

```xml

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>

```

2. http://localhost:8080/actuator
   得到的是一些json，可以复制粘贴到json.cn网站去在线解析一下

`开启健康检查的完整信息
management.endpoint.health.show-details=always`

### SpringBoot Admin

- SpringBoot Admin是一个开源社区项目，用于管理和监控SpringBoot应用程序
- 有两个角色，客户端和服务端
- 应用程序作为SpringBoot Client向为SpringBoot Admin Server注册
- Springboot Admin Server的UI界面展示Springboot Admin Client的Actuator Endpoint上的一些监控信息

使用步骤:

- admin server:
    - 创建admin-server模块
    - 导入依赖坐标admin-starter-server
    - 在引导类启用监控功能@EnableAdminServer

- admin client
    - 创建admin-client模块
    - 导入依赖坐标admin-starter-client
    - 配置相关信息，server地址等
    - 启用server和client服务，访问server

# SpringBoot项目部署
略

# Spring Data JPA
**什么是JPA?与JDBC的区别**

SUN官方提出的**一种ORM规范**，O：Object R：Relative M：Mapping
JPA是规范，而Hibernate是JPA的实现
Spring Data JPA旨在改进数据访问层的实现以提升开发效率

- 相同处：
1. 都跟数据库操作有关，JPA 是JDBC的升华，升级版。
2. JDBC和JPA都是一组规范接口
3. 都是由SUN官方推出的
- 不同处：
1. JDBC是由各个关系型数据库实现的，JPA是由**ORM**框架实现
2. JDBC使用SQL语句和数据库通信，JPA用**面向对象**方式，通过ORM框架来生成SQL，进行操作。
3. JPA在JDBC之上的，JPA也要依赖JDBC才能操作数据库。

总的来说，类比下的话，用JPA相当与用了个翻译器，无需学习专门的外语语言

mybatis：小巧、方便？、高效、简单、直接、半自动
半自动的ORM框架
小巧：mybatis就是jdbc封装

hibernate：强大、方便、高效、复杂、绕弯子、全自动
全自动的ORM框架
强大：根据ORM映射生成不同SQL

**JPA的对象4种状态**
- 临时状态：又称为瞬时状态，刚创建出来，没有与entityManager发生关系，没有被持久化，不处于entityManager中的对象。
- 持久状态：与entityManager发生关系，已经被持久化，可以把持久化状态当做实实在在的数据库记录。
- 删除状态：执行remove方法，事物提交之前。
- 游离状态：游离状态就是提交到数据库后，事务commit后实体的状态，因为事务已经提交了，此时实体的属性任你如何改变，也不会同步到数据库，因为游离是没人管的孩子，不在持久化
上下文中

**多表关联操作**

- OneToOne
```java
@Entity
@Table(name = "tb_customer")
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "cust_address")
    private String address;

    // 单向关联一对一
    /**
     * cascade 设置关联操作
     *  All, 所有持久化操作
     *  PERSIST 只有插入才会执行关联操作
     *  MERGE 只有修改才会执行关联操作
     *  REMOVE 只有删除才会执行关联操作
     *
     * fetch 设置是否懒加载
     *  EAGER 立即加载（默认）
     *  LAZY 懒加载（直到用到对象才会进行查询，因为不是所有关联对象需要用到）
     *
     * orphanRemoval 关联移除（通常在修改的时候会用到）
     * 一旦关联的数据设置null，或修改为其他的关联数据，如果想删除关数据，就可以设置为true
     */
    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, orphanRemoval = true)
    // 设置外键的字段的ID
    @JoinColumn(name = "account_id")
    private Account account;
}
```

- OneToMore
```java
@Entity
@Table(name = "m_customer")
@Data
public class CustomerM {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;


    // 一对多
    // 一对多，默认把@OneToMany放在一的这一类，但其外键字段实际在生成在多的那张表
    /**
     * OneToMany fetch默认懒加载
     */
//    @OneToMany(cascade = CascadeType.PERSIST)
    @OneToMany(cascade = CascadeType.ALL)
    // 设置外键的字段的ID
    @JoinColumn(name = "customer_id")
    private List<Message> messages;
}
```

**设置了懒加载后需添加@Transactional？为什么懒加载需要配置事务？**
- 当通过repository调用完查询方法后，session就会立即关闭，一旦session关闭就不能查询
- 加了事务后，就能让session直到事务关闭才会关闭



题外话：
@Data 等于以下四个注解
```java
@Getter // 所有属性的get方法
@Setter // 所有属性的set方法
@RequiredArgsConstructor // 生成所欲必须属性(加final)的构造方法，如果没有final就是无参构造方法
@EqualAndHashCode
```
