package top.linxz.java.spring.cloud.sell.order.converter;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import top.linxz.java.spring.cloud.sell.order.dataobject.OrderDetail;
import top.linxz.java.spring.cloud.sell.order.dto.OrderDTO;
import top.linxz.java.spring.cloud.sell.order.enums.ResultEnum;
import top.linxz.java.spring.cloud.sell.order.exception.OrderException;
import top.linxz.java.spring.cloud.sell.order.form.OrderForm;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class OrderFrom2OrderDTO {
    public static OrderDTO convert(OrderForm orderForm) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetailList = new ArrayList<>();
        Gson gson = new Gson();
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>() {
            }.getType());
        } catch (Exception e) {
            log.error("【json转换】错误,string={}", orderForm.getItems());
            throw new OrderException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }
}
