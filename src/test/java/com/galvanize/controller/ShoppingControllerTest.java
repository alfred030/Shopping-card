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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ShoppingControllerTest {
    @Autowired
    MockMvc mvc;

    @MockBean
    ShoppingService shoppingService;
    ObjectMapper objectMapper = new ObjectMapper();

    // AML the main issue here was like 52 and lines 55
    @Test
    public void createShopping() throws Exception {
        Shopping expected = new Shopping();
        // make sure your object has a value for the parameter that you are asserting on at the end
        String json = objectMapper.writeValueAsString(expected);
        //expected.setName("Sample name");
        ShoppingDTO shoppingDTO = new ShoppingDTO();
        // don't need to use mock here
        when(shoppingService.createShopping(any(Shopping.class))).thenReturn(shoppingDTO);
        mvc.perform(post("/api/shop")
                // don't send in the Shopping.class
                //.content(objectMapper.writeValueAsString(Shopping.class))
                //Instead, send in an instance of the class variable shopping
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                // need acceptance type here as well
                //.accept(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isOk())
                // the DTO class doesn't have an Id field to assert on
                //.andExpect(jsonPath("$.Id").value(expected.getId()));

                // Instead assert on something the DTO has, like name
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(expected));
    }

    @Test
    public void getShoppingById() throws Exception {
        ShoppingDTO expected = new ShoppingDTO();
        List<ShoppingDTO> shoppingDTO = new ArrayList<>();
        shoppingDTO.add(expected);
        when(shoppingService.getAllShops()).thenReturn(shoppingDTO);
        mvc.perform(get("/api/shop"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").value(expected));
    }

//    @Test
//    public void getShopperById() throws Exception {
//        ShoppingDTO expected = new ShoppingDTO();
//        when(shoppingService.getShoppingById(anyLong())).thenReturn(expected);
//        mvc.perform(get("/api/shop/1"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$").value(expected));
//    }
//
//    @Test
//    public void deleteShoppingById() throws Exception {
//        when(shoppingService.(anyLong())).thenReturn(true);
//        mvc.perform(delete("/api/shops/1"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$").value(true));
//    }
//
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
}
