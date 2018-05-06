package top.linxz.java.spring.cloud.sell.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.linxz.java.spring.cloud.sell.order.VO.ResultVO;
import top.linxz.java.spring.cloud.sell.order.converter.OrderFrom2OrderDTO;
import top.linxz.java.spring.cloud.sell.order.dto.OrderDTO;
import top.linxz.java.spring.cloud.sell.order.enums.ResultEnum;
import top.linxz.java.spring.cloud.sell.order.exception.OrderException;
import top.linxz.java.spring.cloud.sell.order.form.OrderForm;
import top.linxz.java.spring.cloud.sell.order.service.OrderService;
import top.linxz.java.spring.cloud.sell.order.utils.ResultVOUtil;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/create")
    public ResultVO<Map<String,String>> create(@Valid OrderForm orderForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数不正确，orderForm={}", orderForm);
            throw new OrderException(ResultEnum.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }

        //orderForm->orderDTO
        OrderDTO orderDTO = OrderFrom2OrderDTO.convert(orderForm);
        if (orderDTO.getOrderDetailList() == null || orderDTO.getOrderDetailList().size() == 0) {
            log.error("【创建订单】购物车信息为空");
            throw new OrderException(ResultEnum.CART_EMPTY);
        }

        OrderDTO result = orderService.create(orderDTO);
        Map<String, String> map = new HashMap<>();
        map.put("orderId", result.getOrderId());

        return ResultVOUtil.success(map);
    }

}
