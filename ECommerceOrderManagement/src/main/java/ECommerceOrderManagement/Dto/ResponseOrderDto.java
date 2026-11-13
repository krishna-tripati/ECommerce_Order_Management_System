package ECommerceOrderManagement.Dto;

import java.util.List;

public class ResponseOrderDto {
    private int orderId;
    private int custId;
    private String custName;
    private String orderStatus;

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    private List<ResponseProductDto> products;

    private double totalAmount;

    public ResponseOrderDto() {
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<ResponseProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ResponseProductDto> products) {
        this.products = products;
    }

}
