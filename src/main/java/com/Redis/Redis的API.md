# Redis之API

推荐 ：https://docs.spring.io/spring-data/redis/docs/2.4.3/reference/html/#reference 

## 建立链接（copy from spring.io）

###  Configuring the Lettuce Connector

[Lettuce](https://github.com/lettuce-io/lettuce-core) is a [Netty](https://netty.io/)-based open-source connector supported by Spring Data Redis through the `org.springframework.data.redis.connection.lettuce` package.

Add the following to the pom.xml files `dependencies` element:

```xml
<dependencies>

  <!-- other dependency elements omitted -->

  <dependency>
    <groupId>io.lettuce</groupId>
    <artifactId>lettuce-core</artifactId>
    <version>6.0.2.RELEASE</version>
  </dependency>

</dependencies>
```

The following example shows how to create a new Lettuce connection factory:

```java
@Configuration
class AppConfig {

  @Bean
  public LettuceConnectionFactory redisConnectionFactory() {

    return new LettuceConnectionFactory(new RedisStandaloneConfiguration("server", 6379));
  }
}

```

There are also a few Lettuce-specific connection parameters that can be tweaked. By default, all `LettuceConnection` instances created by the `LettuceConnectionFactory` share the same thread-safe native connection for all non-blocking and non-transactional operations. To use a dedicated connection each time, set `shareNativeConnection` to `false`. `LettuceConnectionFactory` can also be configured to use a `LettucePool` for pooling blocking and transactional connections or all connections if `shareNativeConnection` is set to `false`.

Lettuce integrates with Netty’s [native transports](https://netty.io/wiki/native-transports.html), letting you use Unix domain sockets to communicate with Redis. Make sure to include the appropriate native transport dependencies that match your runtime environment. The following example shows how to create a Lettuce Connection factory for a Unix domain socket at `/var/run/redis.sock`:

```java
@Configuration
class AppConfig {

  @Bean
  public LettuceConnectionFactory redisConnectionFactory() {

    return new LettuceConnectionFactory(new RedisSocketConfiguration("/var/run/redis.sock"));
  }
}
```

### Configuring the Jedis Connector

[Jedis](https://github.com/xetorthio/jedis) is a community-driven connector supported by the Spring Data Redis module through the `org.springframework.data.redis.connection.jedis` package.

Add the following to the pom.xml files `dependencies` element:

```xml
<dependencies>

  <!-- other dependency elements omitted -->

  <dependency>
    <groupId>redis.clients</groupId>
    <artifactId>jedis</artifactId>
    <version>3.3.0</version>
  </dependency>

</dependencies>
```

In its simplest form, the Jedis configuration looks as follow:

```java
@Configuration
class AppConfig {

  @Bean
  public JedisConnectionFactory redisConnectionFactory() {
    return new JedisConnectionFactory();
  }
}
```

For production use, however, you might want to tweak settings such as the host or password, as shown in the following example:

```java
@Configuration
class RedisConfiguration {

  @Bean
  public JedisConnectionFactory redisConnectionFactory() {

    RedisStandaloneConfiguration config = new RedisStandaloneConfiguration("server", 6379);
    return new JedisConnectionFactory(config);
  }
}
```

### Write to Master, Read from Replica

The Redis Master/Replica setup — without automatic failover (for automatic failover see: [Sentinel](https://docs.spring.io/spring-data/redis/docs/2.4.3/reference/html/#redis:sentinel)) — not only allows data to be safely stored at more nodes. It also allows, by using [Lettuce](https://docs.spring.io/spring-data/redis/docs/2.4.3/reference/html/#redis:connectors:lettuce), reading data from replicas while pushing writes to the master. You can set the read/write strategy to be used by using `LettuceClientConfiguration`, as shown in the following example:

```java
@Configuration
class WriteToMasterReadFromReplicaConfiguration {

  @Bean
  public LettuceConnectionFactory redisConnectionFactory() {

    LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
      .readFrom(SLAVE_PREFERRED)
      .build();

    RedisStandaloneConfiguration serverConfig = new RedisStandaloneConfiguration("server", 6379);

    return new LettuceConnectionFactory(serverConfig, clientConfig);
  }
}
```

### Redis Sentinel Support

For dealing with high-availability Redis, Spring Data Redis has support for [Redis Sentinel](https://redis.io/topics/sentinel), using `RedisSentinelConfiguration`, as shown in the following example:

```java
/**
 * Jedis
 */
@Bean
public RedisConnectionFactory jedisConnectionFactory() {
  RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration()
  .master("mymaster")
  .sentinel("127.0.0.1", 26379)
  .sentinel("127.0.0.1", 26380);
  return new JedisConnectionFactory(sentinelConfig);
}

/**
 * Lettuce
 */
@Bean
public RedisConnectionFactory lettuceConnectionFactory() {
  RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration()
  .master("mymaster")
  .sentinel("127.0.0.1", 26379)
  .sentinel("127.0.0.1", 26380);
  return new LettuceConnectionFactory(sentinelConfig);
}
```

---

## 代码实现

新建包springboot

引入依赖

配置文件给出地址端口等设置

new 出链接

```
在redis-cli端，可以使用
config get *   # 来获取所有配置，其中有一项，是禁止外部访问
protected-mode no #默认为yes，需要设置成no


```

### redis中hash实现

### redis自定义template实现