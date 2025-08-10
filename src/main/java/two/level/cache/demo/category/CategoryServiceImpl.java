package two.level.cache.demo.category;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryEntity findById(UUID id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public List<CategoryEntity> findAll() {
        return categoryRepository.findAllByOrderByName();
    }

    @Override
    @Cacheable(value = "categoryEntitiesFindAllRedis", cacheManager = "redisCacheManager")
    public List<CategoryEntity> findAllCacheRedis() {
        return categoryRepository.findAllByOrderByName();
    }

    @Override
    @Cacheable(value = "categoryEntitiesFindAllTwoLevel", cacheManager = "twoLevelCacheManager")
    public List<CategoryEntity> findAllCacheTwoLevel() {
        log.debug("findAllCacheTwoLevel request to db");
        return categoryRepository.findAllByOrderByName();
    }

}
