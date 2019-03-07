# hiot-snkey
This project is builded with IDEA.

- Learn about SSM framework, Spring, Spring MVC, Mybatis.
- Learn about token and redis database，we use redis database to save token. In the project, you need to import Spring Data Redis framework to operate the redis database.
- learn about authorization manage.
- Injection lables troduction.

## SSM Framework

To use ssm framework, you need to include following libraries in your project. Also we should make a config in the applicationContext.xml. I will provide the whole file content after.

    
        //SSM
        compile 'org.springframework:spring-aop:4.2.4.RELEASE'
        compile 'org.springframework:spring-context:4.2.4.RELEASE'
        compile 'org.springframework:spring-beans:4.2.4.RELEASE'
        compile 'org.springframework:spring-web:4.2.4.RELEASE'
        compile 'org.springframework:spring-webmvc:4.2.4.RELEASE'
        compile 'org.springframework:spring-tx:4.2.4.RELEASE'
        compile 'org.aspectj:aspectjweaver:1.8.6'
        compile 'org.mybatis:mybatis-spring:1.2.3'
        compile 'org.mybatis:mybatis:3.3.0'
        compile 'org.springframework:spring-jdbc:4.1.7.RELEASE'
        compile 'org.springframework:spring-test:4.0.5.RELEASE'
        compile 'org.mybatis:mybatis:3.3.0'
        
### Log Manage

To manage your project's log, we include these libraries

         //log
        compile 'log4j:log4j:1.2.17'
        compile group: 'org.slf4j', name: 'slf4j-log4j12', version: '1.7.21'

### Tomcat and MySql

Add tomcat and mysql support.

        //tomcat
        compile 'org.apache.tomcat:tomcat-servlet-api:8.0.24'
    
        //mysql
        compile 'mysql:mysql-connector-java:5.1.36'
        //导入dbcp的jar包，用来在applicationContext.xml中配置数据库
        compile 'commons-dbcp:commons-dbcp:1.2.2'

Then we need to new a file named 'jdbc.properties', its main contents as following

        
    driver=com.mysql.jdbc.Driver
    url=jdbc:mysql://127.0.0.1:3306/hiot-snkey
    username=root
    password=admin888

### JSON Support

    //json
        compile 'com.alibaba:fastjson:1.1.41'
        compile "net.sf.json-lib:json-lib:2.3:jdk15"
        compile 'org.codehaus.jackson:jackson-core-asl:1.9.12'
        compile group: 'org.codehaus.jackson', name: 'jackson-mapper-asl', version: '1.9.12'
        compile group: 'com.fasterxml.jackson.core', name: 'jackson-core', version: '2.8.3'
        compile group: 'com.fasterxml.jackson.core', name: 'jackson-databind', version: '2.8.3'

## Spring Data Redis

When we need to use redis in our project, there are six steps.

- step one: Install redis in your computer, you can download it in official network.
- step two: Start the redis server, we suggest you to set the server as daemon process and set a password in the configaration file.
- step three: Spring framework provides a library to operate redis in our project. The library is Spring Data Redis, we include them as following: 

        //spring-Data-Redis封装的TokenManager对Token进行基础操作
        compile group: 'org.springframework.data', name: 'spring-data-redis', version: '1.8.3.RELEASE'
        compile group: 'redis.clients', name: 'jedis', version: '2.9.0'
        
- step four: Config Redis connection information, we create a file name 'redis-config.properties' in resources directory.


        #redis的服务器地址
        redis.host=127.0.0.1
        #redis的服务端口
        redis.port=6379
        #密码
        redis.pass=root

- step five: Config the redis in 'applicationContext.xml' file, add following contents in '<beans>'
    
      <!--配置redis，缓存token-->
        <bean id="propertyConfigurerRedis" class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer">
            <property name="order" value="1"/>
            <property name="ignoreUnresolvablePlaceholders" value="true"/>
            <property name="locations">
                <list>
                    <value>classpath:redis-config.properties</value>
                </list>
            </property>
        </bean>
        <bean id="jedisPoolConfig" class="redis.clients.jedis.JedisPoolConfig">
            <property name="maxTotal" value="${redis.maxTotal}"/>
            <property name="maxIdle" value="${redis.maxIdle}"/>
            <property name="minIdle" value="${redis.minIdle}"/>
            <property name="maxWaitMillis" value="${redis.maxWaitMillis}"/>
            <property name="testOnBorrow" value="${redis.testOnBorrow}"/>
        </bean>
        <bean id="jedisConnectionFactory" class="org.springframework.data.redis.connection.jedis.JedisConnectionFactory">
            <property name="usePool" value="true"></property>
            <property name="hostName" value="${redis.host}"/>
            <property name="port" value="${redis.port}"/>
            <property name="password" value="${redis.pass}"/>
            <property name="timeout" value="${redis.timeout}"/>
            <property name="database" value="${redis.default.db}"/>
            <constructor-arg index="0" ref="jedisPoolConfig"/>
        </bean>
        <bean id="redisTemplate" class="org.springframework.data.redis.core.StringRedisTemplate">
            <property name="connectionFactory" ref="jedisConnectionFactory"/>
        </bean>


- step six: Then we can use Redis Manage Object 'StringRedisTemplate' to save data and get data from redis.
  
    
     redis.boundValueOps(UUId).set(tokenValue, Constant.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS); 
     redis.boundValueOps(model.getUUId()).expire(Constant.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
    

      
### applicationContext.xml 

    <?xml version="1.0" encoding="UTF-8"?>
    <beans
            xmlns="http://www.springframework.org/schema/beans"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xmlns:context="http://www.springframework.org/schema/context"
            xmlns:tx="http://www.springframework.org/schema/tx"
            xmlns:util="http://www.springframework.org/schema/util"
            xsi:schemaLocation="
            http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.2.xsd
            http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.2.xsd
            http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-3.2.xsd
            http://www.springframework.org/schema/util http://www.springframework.org/schema/util/spring-util-3.2.xsd">
    
        <!-- 开启组件扫描 -->
        <context:component-scan base-package="com.snkey.core"/>
    
        <!-- 配置视图解析器ViewResolver，负责将视图名解析成具体的视图技术，比如解析成html、jsp等 -->
        <bean id="viewResolver"
              class="org.springframework.web.servlet.view.InternalResourceViewResolver">
            <!-- 前缀属性 -->
            <property name="prefix" value="/"/>
            <!-- 后缀属性 -->
            <property name="suffix" value=""/>
        </bean>
    
        <!-- 配置数据库连接信息 -->
        <util:properties id="jdbc" location="classpath:jdbc.properties"/>
    
        <bean id="dbcp" class="org.apache.commons.dbcp.BasicDataSource">
            <property name="driverClassName" value="#{jdbc.driver}"/>
            <property name="url" value="#{jdbc.url}"/>
            <property name="username" value="#{jdbc.username}"/>
            <property name="password" value="#{jdbc.password}"/>
            <!--initialSize: 连接池初始值-->
            <property name="initialSize" value="#{jdbc.initialSize}"/>
            <!--maxIdle: 连接池最大空闲连接-->
            <property name="maxIdle" value="#{jdbc.maxIdle}"/>
            <!--minIdle: 连接池最小空闲连接-->
            <property name="minIdle" value="#{jdbc.minIdle}"/>
            <!--maxActive: 连接池最大连接数量-->
            <property name="maxActive" value="#{jdbc.maxActive}"/>
            <!--maxWait: 连接池最大连接数量-->
            <property name="maxWait" value="#{jdbc.maxWait}"/>
            <!--SQL查询,用来验证从连接池取出的连接-->
            <property name="validationQuery" value="#{jdbc.validationQuery}"/>
            <!--指明连接是否被空闲连接回收器(如果有)进行检验，如果检测失败，则连接将被从池中去除-->
            <property name="testWhileIdle" value="#{jdbc.testWhileIdle}"/>
            <!--在空闲连接回收器线程运行期间休眠的时间值,以毫秒为单位，一般比minEvictableIdleTimeMillis小-->
            <property name="timeBetweenEvictionRunsMillis" value="#{jdbc.timeBetweenEvictionRunsMillis}"/>
            <!--在每次空闲连接回收器线程(如果有)运行时检查的连接数量，最好和maxActive一致-->
            <property name="numTestsPerEvictionRun" value="#{jdbc.numTestsPerEvictionRun}"/>
            <!--连接池中连接，在时间段内一直空闲，被逐出连接池的时间(1000*60*60)，以毫秒为单位-->
            <property name="minEvictableIdleTimeMillis" value="#{jdbc.minEvictableIdleTimeMillis}"/>
        </bean>
    
        <!-- spring和MyBatis完美整合，不需要mybatis的配置映射文件 -->
        <bean id="ssf" class="org.mybatis.spring.SqlSessionFactoryBean">
            <!-- 数据源，注入连接信息 -->
            <property name="dataSource" ref="dbcp"/>
            <!-- 用于指定sql定义文件的位置(加classpath从src下找),自动扫描mapping.xml文件 -->
            <property name="mapperLocations" value="classpath*:sqlmapper/*.xml"/>
            <!-- 扫描 entity 包，这样在 mapper 中就可以使用简单类名，多个用 , 隔开 -->
            <property name="typeAliasesPackage" value="com.snkey.core.entity"/>
        </bean>
    
        <!-- 按指定包扫描dao接口，批量生成dao接口实现对象，id为接口名首字母小写，自动注入Dao实现类，无须手动实现 -->
        <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
            <property name="basePackage" value="com.snkey.core.dao"/>
            <property name="sqlSessionFactoryBeanName" value="ssf"/>
        </bean>
    
        <!--带有@Transactional标记的方法会调用transactionManager组件追加事务控制-->
        <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
            <property name="dataSource" ref="dbcp"/>
        </bean>
        <tx:annotation-driven transaction-manager="transactionManager"/>
    
        <!--配置redis，缓存token-->
        <bean id="propertyConfigurerRedis" class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer">
            <property name="order" value="1"/>
            <property name="ignoreUnresolvablePlaceholders" value="true"/>
            <property name="locations">
                <list>
                    <value>classpath:redis-config.properties</value>
                </list>
            </property>
        </bean>
        <bean id="jedisPoolConfig" class="redis.clients.jedis.JedisPoolConfig">
            <property name="maxTotal" value="${redis.maxTotal}"/>
            <property name="maxIdle" value="${redis.maxIdle}"/>
            <property name="minIdle" value="${redis.minIdle}"/>
            <property name="maxWaitMillis" value="${redis.maxWaitMillis}"/>
            <property name="testOnBorrow" value="${redis.testOnBorrow}"/>
        </bean>
        <bean id="jedisConnectionFactory" class="org.springframework.data.redis.connection.jedis.JedisConnectionFactory">
            <property name="usePool" value="true"></property>
            <property name="hostName" value="${redis.host}"/>
            <property name="port" value="${redis.port}"/>
            <property name="password" value="${redis.pass}"/>
            <property name="timeout" value="${redis.timeout}"/>
            <property name="database" value="${redis.default.db}"/>
            <constructor-arg index="0" ref="jedisPoolConfig"/>
        </bean>
        <bean id="redisTemplate" class="org.springframework.data.redis.core.StringRedisTemplate">
            <property name="connectionFactory" ref="jedisConnectionFactory"/>
        </bean>
    </beans>

## Injection Lables Troduction

I will troduction some usual injection lables in this block.

### @Repository

表明这是一个Dao层，供Mapper文件扫描，所有标注@Repository的类都会被扫描

### @Service

表明这是一个Service类，处理业务逻辑的

### @RestController 

表明该类为Controller类，@RestController和@Controller都可以，区别在于一个是Rest风格的

###  @Autowired

自动导入，如果我们需要在一个类中使用另一个类的对象，可以使用该标签进行注解，而无需去实例化

        @Autowired
        private IUserService userService;

