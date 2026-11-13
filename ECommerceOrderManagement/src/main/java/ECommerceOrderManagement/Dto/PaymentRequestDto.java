package ECommerceOrderManagement.Dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public class PaymentRequestDto {

    @Positive (message = "Number must be greater than zero")
     private int orderId;
    @Pattern(regexp = "UPI|CARD|NET_BANKING|COD", message = "Invalid Payment Method")
     private String payMethod;

    public PaymentRequestDto() {
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }
}
