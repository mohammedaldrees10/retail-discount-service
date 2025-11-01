package com.example.retaildiscount.unitTest;

import com.example.retaildiscount.controller.BillController;
import com.example.retaildiscount.dto.request.CustomerOrderRequest;
import com.example.retaildiscount.dto.response.BillResponse;
import com.example.retaildiscount.model.Item;
import com.example.retaildiscount.model.ItemType;
import com.example.retaildiscount.service.DiscountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BillControllerTest {

    private MockMvc mockMvc;
    private DiscountService discountService;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        discountService = Mockito.mock(DiscountService.class);
        BillController controller = new BillController(discountService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void calculateEndpoint_returnsOkAndBillResponse() throws Exception {
        UUID userId = UUID.randomUUID();
        CustomerOrderRequest request = new CustomerOrderRequest(
                userId,
                List.of(
                        new Item(UUID.randomUUID(), "Laptop", 200.0, ItemType.OTHER),
                        new Item(UUID.randomUUID(), "Milk", 100.0, ItemType.GROCERY)
                )
        );

        BillResponse expected = new BillResponse(
                UUID.randomUUID(),
                UUID.randomUUID(),
                300.0,
                70.0,
                230.0
        );

        when(discountService.calculateBill(any(CustomerOrderRequest.class))).thenReturn(expected);

        mockMvc.perform(post("/api/bill/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expected)));
    }
}
