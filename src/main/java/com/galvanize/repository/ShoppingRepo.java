package com.galvanize.repository;

import com.galvanize.entity.Shopping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingRepo extends JpaRepository<Shopping, Long> {
}
