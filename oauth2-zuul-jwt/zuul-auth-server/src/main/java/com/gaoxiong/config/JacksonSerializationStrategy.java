package com.gaoxiong.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.security.oauth2.provider.token.store.redis.StandardStringSerializationStrategy;

/**
 * @author gaoxiong
 * @ClassName JacksonSerializationStrategy
 * @Description TODO
 * @date 2019/7/12 10:09
 */
@Configuration
public class JacksonSerializationStrategy extends StandardStringSerializationStrategy {
    private static final Jackson2JsonRedisSerializer OBJECT_SERIALIZER = new Jackson2JsonRedisSerializer<Object>(Object.class);
    @Override
    protected <T> T deserializeInternal ( byte[] bytes, Class<T> clazz ) {
        return (T) OBJECT_SERIALIZER.deserialize(bytes);
    }


    @Override
    protected byte[] serializeInternal ( Object object ) {
      return   OBJECT_SERIALIZER.serialize(object);
    }

}
