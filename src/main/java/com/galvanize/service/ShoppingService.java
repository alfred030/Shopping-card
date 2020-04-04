package com.galvanize.service;

import com.galvanize.dto.ShoppingDTO;
import com.galvanize.entity.Shopping;
import com.galvanize.repository.ShoppingRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ShoppingService {
    ShoppingRepo shoppingRepo;

    ModelMapper mapper = new ModelMapper();

    public ShoppingService(ShoppingRepo shoppingRepo){
        this.shoppingRepo = shoppingRepo;
    }

    public ShoppingDTO createShopping(Shopping shopping) {
        return mapper.map(shoppingRepo.save(shopping), ShoppingDTO.class );
    }
}
