package ECommerceOrderManagement.Dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.util.List;

public class OrderRequestDto {
    @Positive(message = "Customer ID must be positive")
    private int custId;
    @NotEmpty(message = "Order must be contain at least one product")
    @Valid
    private List<OrderItemRequestDto> orderItems;

    public OrderRequestDto() {
    }

    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    public List<OrderItemRequestDto> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemRequestDto> orderItems) {
        this.orderItems = orderItems;
    }
}
