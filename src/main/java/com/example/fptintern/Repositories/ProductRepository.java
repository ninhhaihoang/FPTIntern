package com.example.fptintern.Repositories;

import com.example.fptintern.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE p.productName LIKE %?1%")
    List<Product> searchProducts(String keyword);

    @Modifying
    @Query(value = "SET FOREIGN_KEY_CHECKS=0;", nativeQuery = true)
    void setForeignKeyCheckOff ();

    @Modifying
    @Query(value = "SET FOREIGN_KEY_CHECKS=1;", nativeQuery = true)
    void setForeignKeyCheckOn ();

    @Modifying
    @Query(value = "DELETE\n" +
            "FROM p, o\n" +
            "USING products p, orders o\n" +
            "WHERE p.product_id = ?1\n" +
            "AND p.product_id = o.product_id;", nativeQuery = true)
    void deleteProductRelative(Long id);
}
