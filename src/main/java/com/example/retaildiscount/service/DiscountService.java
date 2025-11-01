package com.example.retaildiscount.service;

import com.example.retaildiscount.dto.request.CustomerOrderRequest;
import com.example.retaildiscount.dto.response.BillResponse;
import com.example.retaildiscount.mapper.CustomerOrderMapper;
import com.example.retaildiscount.model.*;
import com.example.retaildiscount.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DiscountService {

    private final UserRepository userRepository;
    private final CustomerOrderMapper customerOrderMapper;

    public BillResponse calculateBill(CustomerOrderRequest request) {
        CustomerOrder order = customerOrderMapper.map(request);

        User user = userRepository.findById(order.getUserId())
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        double total = order.getItems().stream()
                .mapToDouble(Item::getPrice)
                .sum();

        double nonGroceryTotal = order.getItems().stream()
                .filter(item -> item.getItemType() != ItemType.GROCERY)
                .mapToDouble(Item::getPrice)
                .sum();

        double percentageDiscount = getPercentageDiscount(user);

        double discountedTotal = nonGroceryTotal * (1 - percentageDiscount)
                + (total - nonGroceryTotal);

        double flatDiscount = ((int) (discountedTotal / 100)) * 5;
        double netPayable = discountedTotal - flatDiscount;

        return new BillResponse(
                UUID.randomUUID(),
                order.getId(),
                total,
                total - netPayable,
                netPayable
        );
    }

    private double getPercentageDiscount(User user) {
        if (user.getUserType() == UserType.EMPLOYEE) return 0.30;
        if (user.getUserType() == UserType.AFFILIATE) return 0.10;
        if (user.getUserType() == UserType.CUSTOMER &&
                user.getRegisteredDate().isBefore(LocalDate.now().minusYears(2))) {
            return 0.05;
        }
        return 0.0;
    }
}
