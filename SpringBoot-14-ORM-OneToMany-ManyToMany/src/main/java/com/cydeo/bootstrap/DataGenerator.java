package com.cydeo.bootstrap;

import com.cydeo.enums.Status;
import com.cydeo.model.*;
import com.cydeo.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
public class DataGenerator implements CommandLineRunner {

    private final PaymentRepository paymentRepository;
    private final MerchantRepository merchantRepository;
    private final CustomerRepository customerRepository;
    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;

    public DataGenerator(PaymentRepository paymentRepository, MerchantRepository merchantRepository, CustomerRepository customerRepository,
                         ItemRepository itemRepository, CartRepository cartRepository) {
        this.paymentRepository = paymentRepository;
        this.merchantRepository = merchantRepository;
        this.customerRepository = customerRepository;
        this.itemRepository = itemRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Payment payment1 = new Payment(LocalDate.of(2024,11, 9), new BigDecimal("10000"), Status.SUCCESS);
        PaymentDetail paymentDetail1 = new PaymentDetail(new BigDecimal("140000"), new BigDecimal("10000"), LocalDate.of(2024,11, 14));
        payment1.setPaymentDetail(paymentDetail1);

        Payment payment2 = new Payment(LocalDate.of(2024,10, 14), new BigDecimal("500000"), Status.FAILURE);
        PaymentDetail paymentDetail2 = new PaymentDetail(new BigDecimal("140000"), new BigDecimal("10000"), LocalDate.of(2024,10, 15));
        payment2.setPaymentDetail(paymentDetail2);

        //many to one
        Merchant merchant1 = new Merchant("AmazonMerchant", "A123", new BigDecimal("0.25"), new BigDecimal("3000"),2);
        payment1.setMerchant(merchant1);
        payment2.setMerchant(merchant1);

        merchantRepository.save(merchant1);

        Customer customer1 = new Customer("Javid","Mammadli","javid146","jmammadli@bclc.com","New Westminster");
        payment1.setCustomer(customer1);
        payment2.setCustomer(customer1);
        customerRepository.save(customer1);

        Item item1 = new Item("Beef", "B01");
        Item item2 = new Item("Tacos", "T01");
        Item item3 = new Item("Nachos", "N01");

        Cart cart1 = new Cart();
        Cart cart2 = new Cart();

        cart1.setItemList(Arrays.asList(item1, item2, item3));
        cart2.setItemList(Arrays.asList(item1, item2));

        itemRepository.saveAll(Arrays.asList(item1, item2, item3));
        cartRepository.saveAll(Arrays.asList(cart1, cart2));


        paymentRepository.save(payment1);
        paymentRepository.save(payment2);
    }
}
