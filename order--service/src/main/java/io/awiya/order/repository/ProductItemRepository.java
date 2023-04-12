package io.awiya.order.repository;


import io.awiya.order.entities.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource
@Repository
public interface ProductItemRepository extends JpaRepository<ProductItem, Long> {
}
