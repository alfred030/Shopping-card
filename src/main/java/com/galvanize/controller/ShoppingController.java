package com.galvanize.controller;

import com.galvanize.service.ShoppingService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/shop")
public class ShoppingController {
    ShoppingService shoppingService;

    public ShoppingController(ShoppingService shoppingService){
        this.shoppingService = shoppingService;
    }

    @PostMapping
    public ShoppingDTO createShopping(@RequestBody Shopping shopping){
        return shoppingService.createShopping(shopping);
    }
}
