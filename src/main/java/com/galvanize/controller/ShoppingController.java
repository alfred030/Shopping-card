package com.galvanize.controller;

import com.galvanize.dto.ShoppingDTO;
import com.galvanize.entity.Shopping;
import com.galvanize.service.ShoppingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public List<Shopping> getAllShops(){
        return shoppingService.getAllShops();
    }

//   @GetMapping("/{id}")
//    public ShoppingDTO getShoppingById(@PathVariable long id){
//        return shoppingService.getShoppingById(id);
//    }

//    @DeleteMapping("/{id}")
//    public ShoppingDTO updateShopper(@PathVariable long id){
//
//        return shoppingService.deleteShopperById(id);
//    }
//
//    @PutMapping("/name/{Id}")
//    public ShoppingDTO updateShopperById(@PathVariable long Id, @RequestBody Shopping shopping){
//        return shoppingService.updateShopperById(Id, shopping);
//    }
}
