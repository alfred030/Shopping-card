package com.galvanize.controller;

import com.galvanize.dto.ShoppingDTO;
import com.galvanize.entity.Shopping;
import com.galvanize.service.ShoppingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/shop")
public class ShoppingController {
    ShoppingService shoppingService;

    public ShoppingController(ShoppingService shoppingService) {

        this.shoppingService = shoppingService;
    }

    @PostMapping
    public ResponseEntity<ShoppingDTO> createItem(@RequestBody Shopping shopping){
        return ResponseEntity.ok(shoppingService.createShopping(shopping));
    }

    @GetMapping
    public List<Shopping> getAllShops(){
        return shoppingService.getAllShops();
    }

//    @GetMapping
//    public List<Movie> getAllMovies(){
//        return movieService.getAllMovies();
//    }

   @GetMapping("/{id}")
    public ShoppingDTO getShoppingById(@PathVariable long id){
        return shoppingService.getShoppingById(id);
    }

//    @PutMapping("/name/{Id}")
//    public ShoppingDTO updateShopperById(@PathVariable long Id, @RequestBody Shopping shopping){
//        return shoppingService.updateShopperById(Id, shopping);
//    }

//    @PutMapping("/rating/{movieId}")
//    public Movie updateMovieWithStarRating(@PathVariable long movieId, @RequestBody Movie movie){
//        return movieService.updateMovieWithStarRating(movieId, movie);
//    }

//    @DeleteMapping("/{id}")
//    public BooleanSupplier updateShopper(@PathVariable long id){
//        return shoppingService.deleteByShopperId(id);
//    }


//    @DeleteMapping("/{id}")
//    public boolean updateMovie(@PathVariable long id){
//        return movieService.deleteById(id);
//    }
}
