package com.example.retaildiscount.integrationTest;

import com.example.retaildiscount.dto.request.CustomerOrderRequest;
import com.example.retaildiscount.dto.response.BillResponse;
import com.example.retaildiscount.model.*;
import com.example.retaildiscount.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Testcontainers
public class DiscountServiceIntegrationTest {

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.0");

    @DynamicPropertySource
    static void setMongoProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    private UUID employeeId;
    private UUID affiliateId;
    private UUID loyalCustomerId;
    private UUID notLoyalCustomerId;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();

        employeeId = UUID.randomUUID();
        affiliateId = UUID.randomUUID();
        loyalCustomerId = UUID.randomUUID();
        notLoyalCustomerId = UUID.randomUUID();

        userRepository.save(new User(employeeId, "Employee", UserType.EMPLOYEE, LocalDate.now()));
        userRepository.save(new User(affiliateId, "Affiliate", UserType.AFFILIATE, LocalDate.now()));
        userRepository.save(new User(loyalCustomerId, "Loyal", UserType.CUSTOMER, LocalDate.now().minusYears(3)));
        userRepository.save(new User(notLoyalCustomerId, "NotLoyal", UserType.CUSTOMER, LocalDate.now()));
    }

    private ResponseEntity<BillResponse> postBill(CustomerOrderRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth("admin", "admin123");

        HttpEntity<CustomerOrderRequest> entity = new HttpEntity<>(request, headers);

        return restTemplate.postForEntity("/api/bill/calculate", entity, BillResponse.class);
    }

    @Test
    void testEmployeeDiscount() {
        Item item1 = new Item(UUID.randomUUID(), "Laptop", 100.0, ItemType.OTHER);
        Item item2 = new Item(UUID.randomUUID(), "juice", 100.0, ItemType.GROCERY);
        List<Item> itemList= new ArrayList<>();
        itemList.add(item1);
        itemList.add(item2);
        CustomerOrderRequest request = new CustomerOrderRequest(employeeId, itemList);

        ResponseEntity<BillResponse> response = postBill(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(165.0, response.getBody().netPayableAmount(), 0.001);
    }

    @Test
    void testAffiliateDiscount() {
        Item item1 = new Item(UUID.randomUUID(), "Chair", 100.0, ItemType.OTHER);
        Item item2 = new Item(UUID.randomUUID(), "juice", 100.0, ItemType.GROCERY);
        List<Item> itemList= new ArrayList<>();
        itemList.add(item1);
        itemList.add(item2);
        CustomerOrderRequest request = new CustomerOrderRequest(affiliateId, itemList);

        ResponseEntity<BillResponse> response = postBill(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(185.0, response.getBody().netPayableAmount(), 0.001);
    }

    @Test
    void testCustomerOver2YearsDiscount() {
        Item item1 = new Item(UUID.randomUUID(), "Shirt", 100.0, ItemType.OTHER);
        Item item2 = new Item(UUID.randomUUID(), "juice", 100.0, ItemType.GROCERY);
        List<Item> itemList= new ArrayList<>();
        itemList.add(item1);
        itemList.add(item2);

        CustomerOrderRequest request = new CustomerOrderRequest(loyalCustomerId, itemList);

        ResponseEntity<BillResponse> response = postBill(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(190.0, response.getBody().netPayableAmount(), 0.001);
    }

    @Test
    void testCustomerUnder2YearsDiscount() {
        Item item1 = new Item(UUID.randomUUID(), "Shirt", 100.0, ItemType.OTHER);
        Item item2 = new Item(UUID.randomUUID(), "juice", 100.0, ItemType.GROCERY);
        List<Item> itemList= new ArrayList<>();
        itemList.add(item1);
        itemList.add(item2);

        CustomerOrderRequest request = new CustomerOrderRequest(notLoyalCustomerId, itemList);

        ResponseEntity<BillResponse> response = postBill(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(190.0, response.getBody().netPayableAmount(), 0.001);
    }
}
