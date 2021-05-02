
This POC requires REDIS Server to run on localhost:6379
Use the following command to run redis.

```
docker run -p 6379:6379 -it --name some-redis -d redis
```

## Redis Serialization

Refer to `com.chinthakad.common.session.GenericSessionDataJdkRedisSerializer` on a custom `RedisSerializer` that is
 capable to avoiding deserialization failures on `@SessionScope` beans that may be used in our micro-services.