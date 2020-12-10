package demo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import demo.domain.OrderInfo;
import demo.service.OrderInfoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping(value = "/api/orders")
public class OrderController {

    @Reference
    private OrderInfoService orderInfoService;

    @PostMapping(value = "")
    public String createOrder(Integer count, BigDecimal price){
        OrderInfo order = orderInfoService.createOrder(count, price);
        orderInfoService.makePayment(order);
        return "success";
    }
}
