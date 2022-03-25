package com.example.fptintern.Repositories;

import com.example.fptintern.Models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findCustomerByFullName(String fullName);

    @Query("SELECT c FROM Customer c WHERE c.fullName LIKE %?1%")
    List<Customer> searchCustomer(String keyword);

    @Modifying
    @Query(value = "SET FOREIGN_KEY_CHECKS=0;", nativeQuery = true)
    void setForeignKeyCheckOff ();

    @Modifying
    @Query(value = "SET FOREIGN_KEY_CHECKS=1;", nativeQuery = true)
    void setForeignKeyCheckOn ();

    @Modifying
    @Query(value = "DELETE\n" +
            "FROM c, o\n" +
            "USING customers c, orders o\n" +
            "WHERE c.customer_id = ?1\n" +
            "AND c.customer_id = o.customer_id;", nativeQuery = true)
    void deleteCustomerRelative(Long id);
}
