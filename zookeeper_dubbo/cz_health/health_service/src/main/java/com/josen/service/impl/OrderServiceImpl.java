package com.josen.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.josen.entity.MessageConstant;
import com.josen.entity.OrderConstant;
import com.josen.entity.RedisConstant;
import com.josen.mapper.MemberMapper;
import com.josen.mapper.OrderMapper;
import com.josen.mapper.OrderSettingMapper;
import com.josen.pojo.Member;
import com.josen.pojo.Order;
import com.josen.pojo.OrderSetting;
import com.josen.pojo.SubmitOrder;
import com.josen.service.OrderService;
import com.josen.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName OrderServiceImpl
 * @Description 实现提交预约套餐业务逻辑
 * @Author Josen
 * @Create 2020/9/11 16:27
 */
@Service(interfaceClass = OrderService.class)
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private JedisPool jedisPool;
    @Autowired
    private OrderSettingMapper orderSettingMapper;
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Order addOrder(Order order) {
        orderMapper.addOrder(order);
        return order;
    }

    @Override
    public Map validateOrder(SubmitOrder submitOrder) throws ParseException {

        Map res = new HashMap();
        // 1. 校验短信验证码
        Jedis resource = jedisPool.getResource();
        String telephone = submitOrder.getTelephone();
        // 1.1 手机号码为空
        if(telephone == null || "".equals(telephone)){
            res.put("message", MessageConstant.REQ_PARAM_ERROR);
            return res;
        }
        // 1.2 校验验证码
        String code = resource.get(RedisConstant.SEND_CODE_TYPE_ORDER+telephone);
        if(code == null || !code.equals(submitOrder.getValidateCode())){
            res.put("message", MessageConstant.VALIDATECODE_ERROR);
            return res;
        }
        resource.close();

        // 2. 校验体检日期是否可预约
        Date orderDate = DateUtils.parseString2Date(submitOrder.getOrderDate());
        // 2.1 根据日期查询orderSetting
        OrderSetting os = new OrderSetting();
        os.setOrderDate(orderDate);
        OrderSetting res_os = orderSettingMapper.queryOrderSetting(os);
        if(res_os==null){
            // 2.2 当前日期不可预约
            res.put("message", MessageConstant.SELECTED_DATE_CANNOT_ORDER);
            return res;
        }
        // 3. 校验预约人数是否已满
        if(res_os.getReservations() >= res_os.getNumber()){
            // 3.1 预约人数已满
            res.put("message", MessageConstant.ORDER_FULL);
            return res;
        }

        // 4. 判断是否已注册会员
        Member member = memberMapper.queryMemberByPhone(telephone);

        // 4.1 未注册-自动再member表中注册会员
        if(member==null){
            // 4.2 快速注册会员（）
            member = new Member();
            member.setRegTime(new Date());
            member.setPhoneNumber(telephone);
            member.setIdCard(submitOrder.getIdCard());
            member.setSex(submitOrder.getSex().toString());
            member.setName(submitOrder.getName());
            memberMapper.insertMember(member);
        }else {
            // 5. 已注册-校验当前会员是否有重复预约(根据setmealId查询t_member)
            // Hint: 同一个Member可以有不同的setmealId预约，但不能有相同setmealId的预约
            Order order = orderMapper.queryOrderByMidAndSid(submitOrder.getSetmealId(), member.getId());
            if(order != null){
                // 5.1 重复预约-校验不通过
                res.put("message", MessageConstant.HAS_ORDERED);
                return res;
            }
        }

        // 6. 校验通过-拼接订单信息返回
        Order new_order = new Order();
        new_order.setOrderDate(orderDate);
        new_order.setMemberId(member.getId());
        new_order.setOrderStatus(OrderConstant.ORDERSTATUS_NO);
        new_order.setOrderType(OrderConstant.ORDERTYPE_WEIXIN);
        new_order.setSetmealId(submitOrder.getSetmealId());
        res.put("message",MessageConstant.VALIDATE_SUCCESS);
        res.put("order",new_order);
        return res;
    }

    @Override
    public Map getOrderDetailsById(Integer orderId) {
        return orderMapper.getOrderDetailsById(orderId);
    }
}
