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
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
        String json = objectMapper.writeValueAsString(expected);
        ShoppingDTO shoppingDTO = new ShoppingDTO();
        when(shoppingService.createShopping(any(Shopping.class))).thenReturn(shoppingDTO);
        mvc.perform(post("/api/shop")
                .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(expected));
    }

    @Test
    public void getAllShops() throws Exception {
        ShoppingDTO expected = new ShoppingDTO();
        List<ShoppingDTO> shoppingDTO = new ArrayList<>();
        shoppingDTO.add(expected);
        mvc.perform(get("/api/shop"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$[0].name").value("bob"));
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
