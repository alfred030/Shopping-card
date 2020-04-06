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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ShoppingControllerTest {
    @Autowired
    MockMvc mvc;

    @MockBean
    ShoppingService shoppingService;
    ObjectMapper objectMapper = new ObjectMapper();


    @Test
    void createShopping() throws Exception {

        // Setup
        Shopping expected = new Shopping();
        String json = objectMapper.writeValueAsString(expected);
        ShoppingDTO shop = new ShoppingDTO();

        when(shoppingService.createShopping(any(Shopping.class))).thenReturn(shop);


        // Exercise

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

        // Setup
        ShoppingDTO expected = new ShoppingDTO();
        when(shoppingService.getShoppingById(anyLong())).thenReturn(expected);


        // Exercise
        mvc.perform(get("/api/shop/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(expected));
    }

    @Test
    public void updateShopper() throws Exception {

        // Setup
        Shopping expected = new Shopping();
        expected.setShopperId(1L);
        String json = objectMapper.writeValueAsString(expected);
        when(shoppingService.updateShopperById(anyLong(), any(Shopping.class))).thenReturn(expected);

        // Exercise and Assert
        mvc.perform(put("/api/shop/name/1").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.shopperId").value(expected.getShopperId()));
    }
}


