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

- OneToMany

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

- ManyToOne
  当插入"多""的数据的时候，使用多对一的关联关系更加合理
  只有几个注意点，如下

```
@Test
@Transactional(readOnly = true)
void testFind(){
    CustomerM customerM = new CustomerM();
    customerM.setId(1L);
    // 只能通过ID查询，下方的名字设置，其实并没有影响
    customerM.setName("xxxx");

    List<Message> messages = repository.findByCustomer(customerM);
    // 这里隐式调用ToString方法
    System.out.println(messages);

    // 如果 @ManyToOne(cascade = CascadeType.PERSIST)设置如此，就只会删除message那张表内容
    repository.deleteAll(messages);
}
```

- ManyToMany

```java

@Entity
@Table(name = "a_employee")
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // 单向 多对多
    /**
     * 中间表需要通过@JoinTable来维护 （不设置也会自动生成）
     * name 指定中间表的名称
     * JoinColums 设置本表的外键名称
     * inverseJoinColumns 设置关联表的外键名称
     */
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "a_employee_role_relation",
            joinColumns = {@JoinColumn(name = "em_id")},
            inverseJoinColumns = {@JoinColumn(name = "r_id")})
    private List<Role> roles;
}
```

```java

@SpringBootTest
public class ManyToManyTest {
    @Resource
    EmployeeRepository employeeRepository;

    @Resource
    RoleRepository roleRepository;

    @Test
    @Transactional
    @Commit
    // 此处很重要，不加这条注释，可能不会入库
    // 插入
    /**
     * 1. 如果保存的关联数据，希望使用已有的，就需要从数据库中查出来
     *    否则提示游离状态，不能持久化
     *
     * 2. 如果一个业务方法有多个持久话操作，记得加上@Transactional
     *    否则不能公用一个session
     *
     * 3. 在单元测试中用到了@Transactional，如果有增删改的操作，一定要加@commit
     *    因为单元测试中，一般不会考虑修改数据库，认为事务方法为@Transactional，只是测试
     *    所以需要单独加上@commit
     */
    void test() {
        ArrayList<Role> list = new ArrayList<>();
//        list.add(new Role("管理员"));
//        list.add(new Role("普通职工"));
//        list.add(new Role("主任"));
//        list.add(new Role("超级管理员"));

        list.add(roleRepository.findByName("管理员"));
        list.add(roleRepository.findByName("超级管理员"));


//        list.add(roleRepository.findById(1L).get());
//        list.add(roleRepository.findById(2L).get());

        Employee employee = new Employee();
        employee.setName("赵六");
        employee.setRoles(list);

        employeeRepository.save(employee);
    }


    @Test
    @Transactional(readOnly = true)
    void testFind() {

        // System.out.println(employeeRepository.findById(1L));
        Employee user = employeeRepository.findByName("赵大");
        System.out.println(user);
        System.out.println(user.getRoles());
    }

    // 删除

    /**
     * 注意点：
     * 多对多其实不适合删除
     * 因为经常出现可能除了和当前这端关联，还会关联另一端
     * 此时删除就会出现：DataIntegrityViolationException
     *   要删除，就要保证没有额外其他另一端数据关联
     */
    @Test
    @Transactional
    @Commit
    void testDelete() {
        Optional<Employee> employee = employeeRepository.findById(1L);
        employeeRepository.delete(employee.get());
    }

    // update
    @Test
    void testUpdate() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("张三");

        employeeRepository.save(employee);
    }
}

```

**设置了懒加载后需添加@Transactional？为什么懒加载需要配置事务？**

- 当通过repository调用完查询方法后，session就会立即关闭，一旦session关闭就不能查询
- 加了事务后，就能让session直到事务关闭才会关闭

**乐观锁**
hibernate
主要作用：防止并发修改

```
private @Version Long version;
```

简单来说就是加一个@Version和属性

**审计**

```java

@Entity
@Table(name = "a_employee")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "a_employee_role_relation",
            joinColumns = {@JoinColumn(name = "em_id")},
            inverseJoinColumns = {@JoinColumn(name = "r_id")})
    private List<Role> roles;

    // 乐观锁
    private @Version Long version;

    // 审计相关
    @CreatedBy
    String createdBy;

    @LastModifiedBy
    String modifiedBy;

    /**
     * 实体创建时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    protected Date dateCreated = new Date();

    /**
     * 实体修改时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    protected Date dateModified = new Date();
}

```

```java

@Component
@EnableJpaAuditing
public class JpaConfig {

    // AuditorAware 返回当前用户
    @Bean
    public AuditorAware<String> auditorAware() {
        return new AuditorAware() {
            @Override
            public Optional getCurrentAuditor() {
                // 当前用户 session 或redis等等
                // 此处测试，写死了
                return Optional.of("zemise");
            }
        };
    }
}
```

题外话：

1. @Data 等于以下四个注解

```
@Getter // 所有属性的get方法
@Setter // 所有属性的set方法
@RequiredArgsConstructor // 生成所欲必须属性(加final)的构造方法，如果没有final就是无参构造方法
@EqualAndHashCode
```

2. 用springboot jpa往服务器mysql插入数据时，对应时间不匹配的解决办法
    - 对应mysql可手动设置，如：
   ```mysql
    SET GLOBAL time_zone = '+8:00';
    ```     
    - jpa相关配置也可手动设置，如 serverTimezone=Asia/Shanghai

  ```
spring:
  datasource:
      driver-class-name: com.mysql.cj.jdbc.Driver
      url: jdbc:mysql://cellcraft.store:5506/secruity_test?
          serverTimezone=Asia/Shanghai&
          useUnicode=true&
          characterEncoding=utf-8&
          useSSL=true
  ```

### SpringBoot Log

关于日志，默认使用的是logbook

```xml
<!--其实spring-boot-starter其中包含了 spring-boot-starter-logging-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-logging</artifactId>
</dependency>
```

对于不太详细的配置，application.yml配置文件，可以应付以下

```yaml
# 日志配置
logging:
  #  file:
    # max-history: 30
    # max-size: 10MB
    # path: /var/log
  #    name: ./logs/spring_log.log
  pattern:
    # %d{HH:mm:ss.SSS}：日志输出时间。
    # %thread：输出日志的进程名，这在Web应用以及异步任务处理中很有用。
    # %-5level：日志级别，使用5个字符靠左对齐。
    # %logger-：日志输出者的名称。%msg：日志消息。
    # %n：平台的换行符。
    console: "%d{yyyy-MM-dd-HH:mm:ss} [%thread] %-5level %logger- %msg%n"
    file: "%d{yyyy-MM-dd-HH:mm} [%thread] %-5level %logger- %msg%n"
  level:
    # 指定整个项目的日志级别为WARN
    # root: warn
    # 也可对某个包指定单独的日志级别
    io.github.zemise.configuration: warn
```

更细致化的配置可以在resources文件夹下配置logback-spring.xml文件

1. 可选，application.yml文件指定，因为是默认，此步骤可以省略，如果不用logbook可以指定更改

```yaml
   logging:
   config: classpath:logback-spring.xml
```

2. 配置logback-spring.xml，以下达到的效果是，以相对路径产生一个日志文件夹根据日期分类，分别记录了error、info、warn级别的日志

```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
    <!-- 关闭logback的状态输出-->
    <statusListener class="ch.qos.logback.core.status.NopStatusListener" />
    <property name="LOG_CONTEXT_NAME" value="log"/>
    <!--定义日志文件的存储地址 勿在 LogBack 的配置中使用相对路径-->
    <property name="LOG_HOME" value="logs/${LOG_CONTEXT_NAME}"/>
    <!-- 定义日志上下文的名称 -->
    <contextName>${LOG_CONTEXT_NAME}</contextName>
    <!-- 控制台输出 -->
    <!--<appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
      <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
        &lt;!&ndash;格式化输出：%d表示日期，%thread表示线程名，%-5level：级别从左显示5个字符宽度%msg：日志消息，%n是换行符&ndash;&gt;
        <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %highlight(%-5level) %cyan(%logger{50}:%L) - %msg%n</pattern>
        <charset>utf-8</charset>
      </encoder>
      <filter class="ch.qos.logback.classic.filter.ThresholdFilter">
        <level>INFO</level>
      </filter>
    </appender>-->


    <!-- 彩色日志依赖的渲染类 -->
    <conversionRule conversionWord="clr" converterClass="org.springframework.boot.logging.logback.ColorConverter"/>
    <conversionRule conversionWord="wex"
                    converterClass="org.springframework.boot.logging.logback.WhitespaceThrowableProxyConverter"/>
    <conversionRule conversionWord="wEx"
                    converterClass="org.springframework.boot.logging.logback.ExtendedWhitespaceThrowableProxyConverter"/>
    <!-- 彩色日志格式 -->
    <property name="CONSOLE_LOG_PATTERN"
              value="${CONSOLE_LOG_PATTERN:-%clr(%d{yyyy-MM-dd HH:mm:ss.SSS}){faint} %clr(${LOG_LEVEL_PATTERN:-%5p}) %clr(${PID:- }){magenta} %clr(---){faint} %clr([%15.15t]){faint} %clr(%-40.40logger{39}){cyan} %clr(:){faint} %m%n${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}}"/>

    <!--1. 输出到控制台-->
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <!--此日志appender是为开发使用，只配置最底级别，控制台输出的日志级别是大于或等于此级别的日志信息-->
        <filter class="ch.qos.logback.classic.filter.ThresholdFilter">
            <level>INFO</level>
        </filter>
        <encoder>
            <Pattern>${CONSOLE_LOG_PATTERN}</Pattern>
            <!-- 设置字符集 -->
            <charset>UTF-8</charset>
        </encoder>
    </appender>

    <!--info日志统一输出到这里-->
    <appender name="file.info" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <Prudent>true</Prudent>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <!--日志文件输出的文件名，按小时生成-->
            <FileNamePattern>${LOG_HOME}/%d{yyyy-MM-dd}/info/info.%d{yyyy-MM-dd-HH}.%i.log</FileNamePattern>
            <!--日志文件保留天数-->
            <MaxHistory>30</MaxHistory>
            <timeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
                <!-- 除按日志记录之外，还配置了日志文件不能超过10M(默认)，若超过10M，日志文件会以索引0开始， -->
                <maxFileSize>10MB</maxFileSize>
            </timeBasedFileNamingAndTriggeringPolicy>
        </rollingPolicy>
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <!--格式化输出：%d表示日期，%thread表示线程名，%-5level：级别从左显示5个字符宽度 %method 方法名  %L 行数 %msg：日志消息，%n是换行符-->
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{56}.%method:%L - %msg%n</pattern>
            <charset>utf-8</charset>
        </encoder>
        <!-- 此日志文件只记录info级别的 -->
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <level>INFO</level>
            <onMatch>ACCEPT</onMatch>
            <onMismatch>DENY</onMismatch>
        </filter>
    </appender>


    <!--错误日志统一输出到这里-->
    <appender name="file.error" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <Prudent>true</Prudent>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <!--日志文件输出的文件名，按天生成-->
            <FileNamePattern>${LOG_HOME}/%d{yyyy-MM-dd}/error/error.%d{yyyy-MM-dd}.%i.log</FileNamePattern>
            <!--日志文件保留天数-->
            <MaxHistory>30</MaxHistory>
            <timeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
                <!-- 除按日志记录之外，还配置了日志文件不能超过10M(默认)，若超过10M，日志文件会以索引0开始， -->
                <maxFileSize>10MB</maxFileSize>
            </timeBasedFileNamingAndTriggeringPolicy>
        </rollingPolicy>
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <!--格式化输出：%d表示日期，%thread表示线程名，%-5level：级别从左显示5个字符宽度 %method 方法名  %L 行数 %msg：日志消息，%n是换行符-->
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{56}.%method:%L - %msg%n</pattern>
            <charset>utf-8</charset>
        </encoder>
        <!-- 此日志文件只记录error级别的 -->
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <level>ERROR</level>
            <onMatch>ACCEPT</onMatch>
            <onMismatch>DENY</onMismatch>
        </filter>
    </appender>

    <!--warn日志统一输出到这里-->
    <appender name="file.warn" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <Prudent>true</Prudent>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <FileNamePattern>${LOG_HOME}/%d{yyyy-MM-dd}/warn/warn.%d{yyyy-MM-dd}.%i.log</FileNamePattern>
            <!--日志文件保留天数-->
            <MaxHistory>30</MaxHistory>
            <timeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
                <!-- 除按日志记录之外，还配置了日志文件不能超过10M(默认)，若超过10M，日志文件会以索引0开始， -->
                <maxFileSize>10MB</maxFileSize>
            </timeBasedFileNamingAndTriggeringPolicy>
        </rollingPolicy>
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <!--格式化输出：%d表示日期，%thread表示线程名，%-5level：级别从左显示5个字符宽度 %method 方法名  %L 行数 %msg：日志消息，%n是换行符-->
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{56}.%method:%L - %msg%n</pattern>
            <charset>utf-8</charset>
        </encoder>
        <!-- 此日志文件只记录warn级别的 -->
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <level>WARN</level>
            <onMatch>ACCEPT</onMatch>
            <onMismatch>DENY</onMismatch>
        </filter>
    </appender>


    <!--  日志输出级别 -->
    <root level="DEBUG">
        <appender-ref ref="STDOUT"/>
        <appender-ref ref="file.error"/>
        <appender-ref ref="file.info"/>
        <appender-ref ref="file.warn"/>
    </root>

</configuration>
```