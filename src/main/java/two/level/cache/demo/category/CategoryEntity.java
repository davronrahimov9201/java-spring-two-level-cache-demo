package two.level.cache.demo.category;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Formula;
import two.level.cache.demo.product.ProductEntity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "category")
@Data
@NamedEntityGraph(
    name = "CategoryEntity.products",
    attributeNodes = @NamedAttributeNode("products")
)
public class CategoryEntity implements Serializable {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private List<ProductEntity> products;


//
//    @Formula("(SELECT COUNT(1) FROM product WHERE product.category_id = id AND product.price >= 50)")
//    private Long expensiveProductsCount;
//
//    @Formula("(SELECT SUM(product.price) FROM product WHERE product.category_id = id)")
//    private BigDecimal totalProductsSum;
//
//    @Formula("(SELECT SUM(product.price) FROM product WHERE product.category_id = id AND product.price < 50)")
//    private BigDecimal cheapProductsSum;
//
//    @Formula("(SELECT SUM(product.price) FROM product WHERE product.category_id = id AND product.price >= 50)")
//    private BigDecimal expensiveProductsSum;





}
