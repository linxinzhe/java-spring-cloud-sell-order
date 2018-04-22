package top.linxz.java.spring.cloud.sell.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.linxz.java.spring.cloud.sell.order.dataobject.OrderDetail;

public interface OrderDetailRepository  extends JpaRepository<OrderDetail,String> {
}
