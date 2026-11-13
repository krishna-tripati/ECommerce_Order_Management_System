package ECommerceOrderManagement.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class CustomerRequestDto {
    @NotBlank(message = "please provide customer Name")
    private String custName;
    @NotBlank(message = "Address is required")
    private String address;
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Enter a valid 10 digit mobile Number")
    private String mobileNo;
    @Email (message = "only Email format accepted")
    private String Email;

    public CustomerRequestDto() {
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
