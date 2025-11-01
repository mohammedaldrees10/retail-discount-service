package com.example.retaildiscount.unitTest;

import com.example.retaildiscount.dto.request.CustomerOrderRequest;
import com.example.retaildiscount.dto.response.BillResponse;
import com.example.retaildiscount.mapper.CustomerOrderMapper;
import com.example.retaildiscount.model.*;
import com.example.retaildiscount.repository.UserRepository;
import com.example.retaildiscount.service.DiscountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class DiscountServiceEdgeCasesTest {

    private DiscountService discountService;
    private UserRepository userRepository;
    private CustomerOrderMapper customerOrderMapper;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        customerOrderMapper = Mockito.mock(CustomerOrderMapper.class);
        discountService = new DiscountService(userRepository, customerOrderMapper);
    }

    @Test
    void groceryExcludedFromPercentageDiscount() {
        UUID userId = UUID.randomUUID();
        User user = new User(userId, "U1", UserType.EMPLOYEE, LocalDate.now().minusYears(1));
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        Item item1 = new Item(UUID.randomUUID(), "Milk", 200.0, ItemType.GROCERY);
        Item item2 = new Item(UUID.randomUUID(), "Laptop", 100.0, ItemType.OTHER);
        CustomerOrder order = new CustomerOrder(UUID.randomUUID(), userId, List.of(item1, item2), LocalDate.now());

        CustomerOrderRequest request = CustomerOrderRequest.builder().build();
        when(customerOrderMapper.map(request)).thenReturn(order);

        BillResponse bill = discountService.calculateBill(request);

        assertEquals(260.0, bill.netPayableAmount(), 0.001);
    }

    @Test
    void flatDiscountMultipleHundreds() {
        UUID userId = UUID.randomUUID();
        User user = new User(userId, "U2", UserType.CUSTOMER, LocalDate.now().minusYears(3));
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        Item item = new Item(UUID.randomUUID(), "Wardrobe", 990.0, ItemType.OTHER);
        CustomerOrder order = new CustomerOrder(UUID.randomUUID(), userId, List.of(item), LocalDate.now());

        CustomerOrderRequest request = CustomerOrderRequest.builder().build();
        when(customerOrderMapper.map(request)).thenReturn(order);

        BillResponse bill = discountService.calculateBill(request);

        assertEquals(895.5, bill.netPayableAmount(), 0.001);
    }

    @Test
    void noPercentageWhenNoUserTypeMatches() {
        UUID userId = UUID.randomUUID();
        User user = new User(userId, "U3", null, LocalDate.now());
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        Item item = new Item(UUID.randomUUID(), "Book", 150.0, ItemType.OTHER);
        CustomerOrder order = new CustomerOrder(UUID.randomUUID(), userId, List.of(item), LocalDate.now());

        CustomerOrderRequest request = CustomerOrderRequest.builder().build();
        when(customerOrderMapper.map(request)).thenReturn(order);

        BillResponse bill = discountService.calculateBill(request);

        assertEquals(145.0, bill.netPayableAmount(), 0.001);
    }
}
