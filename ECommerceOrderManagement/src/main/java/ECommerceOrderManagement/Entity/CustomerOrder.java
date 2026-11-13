package ECommerceOrderManagement.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class CustomerOrder {

       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
      private int orderId;
       @ManyToOne
       @JoinColumn(name = "custId")
      private Customer customer;

      private String date;
      private double totalAmount;
      private String orderStatus;

       @OneToMany(mappedBy = "customerOrder")
      private List<OrderItem> orderItems;

      public CustomerOrder() {
      }

      public int getOrderId() {
            return orderId;
      }

      public void setOrderId(int orderId) {
            this.orderId = orderId;
      }

      public Customer getCustomer() {
            return customer;
      }

      public void setCustomer(Customer customer) {
            this.customer = customer;
      }

      public String getDate() {
            return date;
      }

      public void setDate(String date) {
            this.date = date;
      }

      public String getOrderStatus() {
            return orderStatus;
      }

      public void setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
      }

      public double getTotalAmount() {
            return totalAmount;
      }

      public void setTotalAmount(double totalAmount) {
            this.totalAmount = totalAmount;
      }

      public List<OrderItem> getOrderItems() {
            return orderItems;
      }

      public void setOrderItems(List<OrderItem> orderItems) {
            this.orderItems = orderItems;
      }
}
