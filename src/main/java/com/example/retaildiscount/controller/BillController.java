package com.example.retaildiscount.controller;

import com.example.retaildiscount.dto.request.CustomerOrderRequest;
import com.example.retaildiscount.dto.response.BillResponse;
import com.example.retaildiscount.service.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bill")
@RequiredArgsConstructor
public class BillController {

    private final DiscountService discountService;

    @PostMapping("/calculate")
    public BillResponse calculate(@RequestBody CustomerOrderRequest request) {
        return discountService.calculateBill(request);
    }
}
