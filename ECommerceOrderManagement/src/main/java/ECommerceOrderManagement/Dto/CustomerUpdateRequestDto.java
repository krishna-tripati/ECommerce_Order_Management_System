package ECommerceOrderManagement.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public class CustomerUpdateRequestDto {
    private String address;
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Enter a valid 10 digit mobile Number")
    private String mobileNo;
    @Email(message = "only Email format accepted")
    private String Email;

    public CustomerUpdateRequestDto() {
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
