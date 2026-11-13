package ECommerceOrderManagement.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class ProductRequestDto {
    @NotBlank (message = "Not blank")
    private String productName;
    @NotBlank(message = "Category is required")
    private String category;
    @Positive (message = " Price must be greater than zero")
    private double price;
    @Positive (message = "Quantity must be greater than zero")
    private int quantity;

    public ProductRequestDto() {
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
