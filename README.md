# hiot-snkey
This project is builded with IDEA.

1. Learn about SSM framework, Spring, Spring MVC, Mybatis
2. Learn about token and redis database，we use redis database to save token. In the project, you need to import Spring Data Redis framework to operate the redis database.
3. learn about authorization manage.

## SSM Framework

To use ssm framework, you need to include following libraries in your project.

    
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
    
    
      
    