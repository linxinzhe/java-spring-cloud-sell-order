package top.linxz.java.spring.cloud.sell.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.linxz.java.spring.cloud.sell.order.dataobject.OrderMaster;

public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {
}
