package two.level.cache.demo.category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    CategoryEntity findById(UUID id);
    List<CategoryEntity> findAll();
    List<CategoryEntity> findAllCacheRedis();
    List<CategoryEntity> findAllCacheTwoLevel();

}
