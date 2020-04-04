package com.galvanize.service;

import com.galvanize.dto.ShoppingDTO;
import com.galvanize.entity.Activity;
import com.galvanize.entity.Expense;
import com.galvanize.entity.Shopping;
import com.galvanize.repository.ShoppingRepo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ShoppingServiceTest {
    @MockBean
    ShoppingRepo shoppingRepo;

    @Test
    public void createShopping(){
        ShoppingService shoppingService = new ShoppingService(shoppingRepo);
        Shopping shopping1 = new Shopping(Expense.EXPENSIVE, "Description", "Name", Activity.ACTIVE, 101);
        ShoppingDTO expected = new ShoppingDTO(shopping1.getName(), shopping1.getDescription(), shopping1.getPrice());
        Shopping received = new Shopping(shopping1.getExpense(), shopping1.getDescription(), shopping1.getName(), shopping1.getActivity(), shopping1.getPrice());
        received.setId(1L);
        when(shoppingRepo.save(any(Shopping.class))).thenReturn(received);
        assertEquals(expected, shoppingService.createShopping(shopping1));
    }
}
