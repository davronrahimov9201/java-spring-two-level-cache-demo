package two.level.cache.demo.category;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping( "category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

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
