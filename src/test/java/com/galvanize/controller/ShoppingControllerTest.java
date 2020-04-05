package com.galvanize.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ShoppingControllerTest {
    @MockBean
    MockMvc mvc;

    @Autowired
    ShoppingService shoppingService;
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void createShopping() throws Exception {
        Shopping expected = new Shopping();
        ShoppingDTO shoppingDTO = new ShoppingDTO();
        when(shoppingService.createShopping(any(Shopping.class))).thenReturn(shoppingDTO);
        mvc.perform(post("/api/shop").content(objectMapper.writeValueAsString(Shopping.class)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Id").value(expected.getId()));

    }
}
