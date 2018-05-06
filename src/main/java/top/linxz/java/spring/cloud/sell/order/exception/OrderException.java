package top.linxz.java.spring.cloud.sell.order.exception;

import top.linxz.java.spring.cloud.sell.order.enums.ResultEnum;

public class OrderException extends RuntimeException {
    private Integer code;

    public OrderException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public OrderException(ResultEnum resultEnum) {
        this(resultEnum.getCode(), resultEnum.getMessage());
    }
}
