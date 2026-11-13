package ECommerceOrderManagement.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class OrderItemRequestDto {
    @Positive(message = "Project Id must be positive")
    private int ProductId;
    @Positive(message = "Quantity must be greater than zero")
    private int quantity;

    public OrderItemRequestDto() {
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
