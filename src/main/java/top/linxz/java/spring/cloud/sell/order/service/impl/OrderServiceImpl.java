package top.linxz.java.spring.cloud.sell.order.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import top.linxz.java.spring.cloud.sell.order.dataobject.OrderMaster;
import top.linxz.java.spring.cloud.sell.order.dto.OrderDTO;
import top.linxz.java.spring.cloud.sell.order.enums.OrderStatusEnum;
import top.linxz.java.spring.cloud.sell.order.enums.PayStatusEnum;
import top.linxz.java.spring.cloud.sell.order.repository.OrderDetailRepository;
import top.linxz.java.spring.cloud.sell.order.repository.OrderMasterRepository;
import top.linxz.java.spring.cloud.sell.order.service.OrderService;
import top.linxz.java.spring.cloud.sell.order.utils.KeyUtil;

import java.math.BigDecimal;

public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;


    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        //TODO 查询商品信息(商品服务)

        //计算总价

        //TODO 扣库存(商品服务)

        //订单入库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(KeyUtil.genUniqueKey());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(new BigDecimal(5));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);

        return orderDTO;
    }
}
