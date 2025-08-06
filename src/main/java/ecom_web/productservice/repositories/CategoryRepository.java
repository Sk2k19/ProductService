package ecom_web.productservice.repositories;

import ecom_web.productservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Custom query methods can be added here if needed
    Optional<Category> findByName(String name);

    @Override
    Optional<Category> findById(Long categoryId);
    Category save(Category category);
}
