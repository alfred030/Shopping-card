package com.galvanize.service;

import com.galvanize.dto.ShoppingDTO;
import com.galvanize.entity.Activity;
import com.galvanize.entity.Expense;
import com.galvanize.entity.Shopping;
import com.galvanize.repository.ShoppingRepo;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
public class ShoppingServiceTest {
    @MockBean
    ShoppingRepo shoppingRepo;

    ModelMapper mapper = new ModelMapper();

    @Test
    public void createShopping(){
        ShoppingService shoppingService = new ShoppingService(shoppingRepo);
        Shopping input = new Shopping(Expense.EXPENSIVE, "Description", "Name", Activity.ACTIVE, 101);
        ShoppingDTO expected = new ShoppingDTO(input.getName(), input.getDescription(), input.getPrice());
        Shopping received = new Shopping(input.getExpense(), input.getDescription(), input.getName(), input.getActivity(), input.getPrice());
        received.setId(1L);
        when(shoppingRepo.save(any(Shopping.class))).thenReturn(received);
        assertEquals(expected, shoppingService.createShopping(input));
    }

    @Test
    public void getShopById(){
        ShoppingService shoppingService = new ShoppingService(shoppingRepo);
        Shopping expected = new Shopping(Expense.EXPENSIVE, "Description", "Name", Activity.ACTIVE, 101);
        expected.setId(1L);
        when(shoppingRepo.findById(anyLong())).thenReturn(Optional.of(expected));
        assertEquals(mapper.map(expected,ShoppingDTO.class),shoppingService.getShoppingById(expected.getId()));
    }
}