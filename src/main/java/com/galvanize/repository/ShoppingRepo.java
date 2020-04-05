package com.galvanize.repository;

import com.galvanize.entity.Shopping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingRepo extends JpaRepository<Shopping, Long> {
    boolean deleteById(long Id);
}
