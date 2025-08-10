package two.level.cache.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.time.Duration;

@Configuration
@EnableCaching
public class CacheConfig {

    @Value("${spring.cache.redis.time-to-live}")
    private short ttl;

    @Bean
    public CaffeineCacheManager caffeineCacheManager() {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        caffeineCacheManager.setCaffeine(
            com.github.benmanes.caffeine.cache.Caffeine.newBuilder()
                .expireAfterWrite(Duration.ofSeconds(5))
                .maximumSize(500)
                .recordStats()
                .removalListener((key, value, cause) ->
                        System.out.println("Caffeine remove: key=" + key + ", cause=" + cause))
        );
        return caffeineCacheManager;
    }

    @Bean
    public RedisCacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration cacheConfig = RedisCacheConfiguration.defaultCacheConfig()
            .entryTtl(Duration.ofSeconds(25))
            .disableCachingNullValues();
        return RedisCacheManager.builder(redisConnectionFactory)
            .cacheDefaults(cacheConfig)
            .build();
    }

    @Bean
    @Primary
    public CacheManager twoLevelCacheManager(CaffeineCacheManager caffeineCacheManager,
                                             RedisCacheManager redisCacheManager) {
        return new CustomCacheManager(caffeineCacheManager, redisCacheManager);
    }

}
