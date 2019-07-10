# Redis

## 使用synchronized

- 是一种解决办法
- 无法做到细粒度控制
- 只适合单点系统

## Redis命令

### SETNX

SET if Not eXist：

将`key`设置值为`value`，如果`key`不存在，这种情况下等同[SET](http://redis.cn/commands/set.html)命令。 当`key`存在时，什么也不做。

```shell
redis> SETNX mykey "Hello"
(integer) 1
redis> SETNX mykey "World"
(integer) 0
redis> GET mykey
"Hello"
redis> 
```

### GETSET

Get-Set：

自动将key对应到value并且返回原来key对应的value。

如果key存在但是对应的value不是字符串，就返回错误。

```shell
redis> INCR mycounter
(integer) 1
redis> GETSET mycounter "0"
"1"
redis> GET mycounter
"0"
redis> 
```

## Redis实现分布式锁

- 支持分布式
- 可以更细粒度的控制
- 多台机器上多个进程对`一个数据`进行操作的`互斥`
- 单线程

## Redis缓存

必须实例化

结合业务场景，避免滥用

系统先读取缓存，缓存没有再读取数据库，然后再把数据存入缓存。

- @Enablecaching

  放在启动类中，开启缓存

- @Cacheable

  设置缓存，cacheName，key

  只有第一次执行，之后读取数据，不调用方法，只读取缓存

- @CachePut

  更新缓存，每次都执行

- @CacheEvict

  清除缓存