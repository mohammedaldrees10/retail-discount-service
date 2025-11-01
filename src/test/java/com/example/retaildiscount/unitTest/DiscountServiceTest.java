package com.example.retaildiscount.unitTest;

import com.example.retaildiscount.dto.request.CustomerOrderRequest;
import com.example.retaildiscount.dto.response.BillResponse;
import com.example.retaildiscount.mapper.CustomerOrderMapper;
import com.example.retaildiscount.model.*;
import com.example.retaildiscount.repository.UserRepository;
import com.example.retaildiscount.service.DiscountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class DiscountServiceTest {

    private DiscountService discountService;
    private UserRepository userRepository;
    private CustomerOrderMapper customerOrderMapper;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        customerOrderMapper = Mappers.getMapper(CustomerOrderMapper.class);

        discountService = new DiscountService(userRepository, customerOrderMapper);
    }

    @Test
    void testEmployeeDiscount() {
        UUID userId = UUID.randomUUID();
        User user = new User(userId, "Alice", UserType.EMPLOYEE, LocalDate.now());
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        Item item1 = new Item(UUID.randomUUID(), "Laptop", 200.0, ItemType.OTHER);
        Item item2 = new Item(UUID.randomUUID(), "Milk", 100.0, ItemType.GROCERY);
        CustomerOrderRequest request = new CustomerOrderRequest(userId, List.of(item1, item2));

        BillResponse response = discountService.calculateBill(request);

        assertEquals(230.0, response.netPayableAmount(), 0.001);
    }

    @Test
    void testAffiliateDiscount() {
        UUID userId = UUID.randomUUID();
        User user = new User(userId, "Bob", UserType.AFFILIATE, LocalDate.now());
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        Item item = new Item(UUID.randomUUID(), "Chair", 400.0, ItemType.OTHER);
        CustomerOrderRequest request = new CustomerOrderRequest(userId, List.of(item));

        BillResponse response = discountService.calculateBill(request);

        assertEquals(345.0, response.netPayableAmount(), 0.001);
    }

    @Test
    void testCustomerOver2Years() {
        UUID userId = UUID.randomUUID();
        User user = new User(userId, "Charlie", UserType.CUSTOMER, LocalDate.now().minusYears(3));
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        Item item = new Item(UUID.randomUUID(), "Shirt", 200.0, ItemType.OTHER);
        CustomerOrderRequest request = new CustomerOrderRequest(userId, List.of(item));

        BillResponse response = discountService.calculateBill(request);

        assertEquals(185.0, response.netPayableAmount(), 0.001);
    }
}
