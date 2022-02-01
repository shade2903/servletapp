package com.haiduk.service;


import com.haiduk.converter.OrderMapper;
import com.haiduk.domain.Order;
import com.haiduk.domain.Product;
import com.haiduk.domain.User;
import com.haiduk.dto.OrderDto;
import com.haiduk.exception.UserNotFoundException;
import com.haiduk.repository.OrderRepository;
import com.haiduk.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
@Transactional
@Service
public class OrderService {

    private ProductRepository productRepository;
    private OrderRepository orderRepository;
    private OrderMapper orderMapper;

    @Autowired
    OrderService(ProductRepository productRepository, OrderRepository orderRepository,OrderMapper orderMapper) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    public void addProductToOrder(User user, String select) {
        List<Product> products;
        Order order;
        Product selectProduct = (productRepository.getById(Integer.parseInt(select)));
         if(user.getOrders().size() !=0){
            order = getCurrentOrder(user);
            products = order.getProductList();
            products.add(selectProduct);
            order.setTotalPrice(getTotalPrice(products));
            order.setProductList(products);
            orderRepository.updateOrder(order);
         }
    }

    public Double getTotalPrice(List<Product> totalPriceList) {
        Double totalPrice = 0.0;
        for (Product product : totalPriceList) {
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }
    public Order getOrder(User user){
        if(user != null){

            return user.getOrders().get(user.getOrders().size()-1);
        }

        throw new UserNotFoundException("User not found in database");
    }
    public List<Product> getAllOrderProduct(User user){

        return getCurrentOrder(user).getProductList();
    }
    public OrderDto getOrderDto(Order order){
        return orderMapper.toDto(order);
    }

    private Order getCurrentOrder(User user){
        return user.getOrders().get(user.getOrders().size() - 1);
    }


}
