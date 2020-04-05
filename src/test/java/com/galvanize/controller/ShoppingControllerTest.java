package com.galvanize.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.dto.ShoppingDTO;
import com.galvanize.entity.Shopping;
import com.galvanize.service.ShoppingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ShoppingControllerTest {
    @Autowired
    MockMvc mvc;
    ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    ShoppingService shoppingService;

    @Test
    void createShopping() throws Exception {
        Shopping expected = new Shopping();
        String json = objectMapper.writeValueAsString(expected);
        ShoppingDTO shop = new ShoppingDTO();

        when(shoppingService.createShopping(any(Shopping.class))).thenReturn(shop);

        mvc.perform(post("/api/shop").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(expected));
    }

    @Test
    public void getAllShops() throws Exception {
        Shopping expected = new Shopping();
        expected.setShopperId(1L);
        List<Shopping> shopping = new ArrayList<>();
        shopping.add(expected);
        when(shoppingService.getAllShops()).thenReturn(shopping);
        mvc.perform(get("/api/shop"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value(expected.getName()));
    }

    @Test
    public void getShopperById() throws Exception {
        ShoppingDTO expected = new ShoppingDTO();
        when(shoppingService.getShoppingById(anyLong())).thenReturn(expected);
        mvc.perform(get("/api/shop/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(expected));
    }

//    @Test
//    public void updateMovie() throws Exception {
//        Shopping expected = new Shopping();
//        expected.setId(1L);
//        String json = objectMapper.writeValueAsString(expected);
//        when(shoppingService.updateShopperById(anyLong(), any(Shopping.class))).thenReturn(expected);
//        mvc.perform(put("/api/shops/1").content(json).contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.Id").value(expected.getId()));
//    }

//    @Test
//    public void updateMovie() throws Exception {
//        Movie expected = new Movie();
//        expected.setMovieId(1L);
//        String json = objectMapper.writeValueAsString(expected);
//        when(movieService.updateMovieWithStarRating(anyLong(), any(Movie.class))).thenReturn(expected);
//        mvc.perform(put("/api/movies/rating/1").content(json).contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.movieId").value(expected.getMovieId()));
//    }

//    @Test
//    public void deleteShoppingById() throws Exception {
//        when(shoppingService.deleteByShopperId(anyLong())).thenReturn(true);
//        mvc.perform(delete("/api/shops/1"))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$").value(true));
//    }

//    @Test
//    public void deleteMovieById() throws Exception {
//        when(movieService.deleteById(anyLong())).thenReturn(true);
//        mvc.perform(delete("/api/movies/1"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$").value(true));
//    }
}
