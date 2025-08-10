package two.level.cache.demo.category;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping( "category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("{id}")
    public ResponseEntity<CategoryEntity> get(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(categoryService.findById(id));
    }

    @GetMapping("find-all")
    public ResponseEntity<List<CategoryEntity>> findAll() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @GetMapping("find-all-cache-redis")
    public ResponseEntity<List<CategoryEntity>> findAllCacheRedis() {
        return ResponseEntity.ok(categoryService.findAllCacheRedis());
    }

    @GetMapping("find-all-cache-two-level")
    public ResponseEntity<List<CategoryEntity>> findAllCacheTwoLevel() {
        return ResponseEntity.ok(categoryService.findAllCacheTwoLevel());
    }

}
