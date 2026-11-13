package ECommerceOrderManagement.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class ProductUpdateRequestDto {

    @NotBlank(message = "Not blank")
    private String productName;
    @Positive(message = " Price must be greater than zero")
    private double price;
    @Positive (message = " Quantity must be greater than zero")
    private int quantity;


    public ProductUpdateRequestDto() {
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
