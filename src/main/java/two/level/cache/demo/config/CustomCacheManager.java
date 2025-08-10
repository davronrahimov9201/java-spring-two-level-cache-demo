package two.level.cache.demo.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.Cache;

import java.util.Collection;

public class CustomCacheManager implements CacheManager {

    private final CacheManager firstLevelCacheManager;
    private final CacheManager secondLevelCacheManager;

    public CustomCacheManager(CacheManager firstLevelCacheManager, CacheManager secondLevelCacheManager) {
        this.firstLevelCacheManager = firstLevelCacheManager;
        this.secondLevelCacheManager = secondLevelCacheManager;
    }

    @Override
    public Cache getCache(String name) {
        Cache firstCache = firstLevelCacheManager.getCache(name);
        Cache secondCache = secondLevelCacheManager.getCache(name);
        return new CustomCache(firstCache, secondCache);
    }

    @Override
    public Collection<String> getCacheNames() {
        return firstLevelCacheManager.getCacheNames();
    }
}
