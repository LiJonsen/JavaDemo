package com.josen.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.josen.entity.MessageConstant;
import com.josen.entity.Result;
import com.josen.pojo.Order;
import com.josen.pojo.OrderSetting;
import com.josen.pojo.SubmitOrder;
import com.josen.service.OrderService;
import com.josen.service.OrderSettingService;
import com.josen.utils.WebUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @ClassName MobileOrderController
 * @Description 处理移动端提交预约请求
 * @Author Josen
 * @Create 2020/9/11 16:28
 */
@RestController
@RequestMapping("/order")
public class MobileOrderController {
    @Reference
    private OrderService orderService;
    @Reference
    private OrderSettingService orderSettingService;
    /**
     * 提交预约套餐接口
     * @return
     */
    @RequestMapping("/addOrder")
    public Result handlerAddOrder(@RequestBody SubmitOrder submitOrder){
        try {
            // 1. 校验体检预约信息
            Map validate_res = orderService.validateOrder(submitOrder);
            Order order = (Order)validate_res.get("order");
            if (order == null) {
                // 校验不通过
                String message = (String)validate_res.get("message");
                return WebUtils.getFailResult(message);
            }

            // 2. 添加体检预约订单
            order = orderService.addOrder(order);

            // 3. 更新预约设置（ordersetting），预约人数+1
            orderSettingService.incrOrderSettingReservations(order.getOrderDate());
            // 4. 预约成功
            return WebUtils.getSuccessResult(MessageConstant.ORDER_SUCCESS,order);
        } catch (Exception e) {
            e.printStackTrace();
            return WebUtils.getFailResult();
        }
    }

    @RequestMapping("/details")
    public Result handlerGetDetails(@RequestBody Map body){
        try {
            Integer orderId = (Integer)body.get("orderId");
            Map data = orderService.getOrderDetailsById(orderId);
            return WebUtils.getSuccessResult(data);
        } catch (Exception e) {
            e.printStackTrace();
            return WebUtils.getFailResult();
        }
    }
}
