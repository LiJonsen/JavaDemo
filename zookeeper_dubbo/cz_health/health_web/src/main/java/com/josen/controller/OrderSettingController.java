package com.josen.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.josen.entity.MessageConstant;
import com.josen.entity.Result;
import com.josen.pojo.OrderSetting;
import com.josen.service.OrderSettingService;
import com.josen.utils.DateUtils;
import com.josen.utils.POIUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.*;

/**
 * @ClassName OrderSettingController
 * @Description 处理预约设置请求
 * @Author Josen
 * @Create 2020/9/9 16:39
 */
@RestController
@RequestMapping("/order_setting")
public class OrderSettingController {
    @Reference
    private OrderSettingService orderSettingService;
    /**
     * 处理上传Excel文件，读取文件添加/更新预约数据
     * @param excelFile
     * @return
     */
    @RequestMapping(value = "/upload_excel",method = RequestMethod.POST)
    public Result handlerUploadExcel(@RequestParam("excelFile") MultipartFile excelFile){
        Result result = new Result(false, MessageConstant.UPLOAD_FAIL, "");

        try {
            // 读取excel数据
            List<String[]> list = POIUtils.readExcel(excelFile);
            if(list.size()>0){
                List<OrderSetting> orderSettings = new ArrayList<>();
                // 数据整合
                for (String[] item : list) {
                    OrderSetting order = new OrderSetting();
                    order.setOrderDate(DateUtils.parseString2Date(item[0]));
                    order.setNumber(Integer.parseInt(item[1]));
                    orderSettings.add(order);
                }

                orderSettingService.addOrUpdateOrderSetting(orderSettings);
                result.setFlag(true);
                result.setMessage(MessageConstant.REQ_SUCCESS);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }
    }

    /**
     * 查询指定月第一天-最后一天预约列表
     * @param
     * @return
     */
    @RequestMapping(value = "/queryOrderSettingByDate",method = RequestMethod.POST)
    public Result handlerOrderSettingByDate(@RequestBody Map message){
        String startDate = (String)message.get("startDate");
        Result result = new Result(false, MessageConstant.REQ_PARAM_ERROR, "");
        try {
            List<Map> datas = orderSettingService.queryOrderSettingByDate(startDate);
            result.setFlag(true);
            result.setMessage(MessageConstant.REQ_SUCCESS);
            result.setData(datas);
            return result;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return result;
        }
    }
}
