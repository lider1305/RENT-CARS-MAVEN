package by.pvt.VO;

/**
 * Класс формирует объект клиента для показа на веб части админской области
 */
public class ClientDTO {
    private String password;
    private String name;
    private String surName;
    private String phone;
    private String email;
    private long passportId;
    private String passport;
    private long passportIssueDate;
    private long passportEndDate;

    public ClientDTO(String password, String name, String surName, String phone, String email,  String passport, long passportIssueDate, long passportEndDate) {
        this.password = password;
        this.name = name;
        this.surName = surName;
        this.phone = phone;
        this.email = email;
        this.passport = passport;
        this.passportIssueDate = passportIssueDate;
        this.passportEndDate = passportEndDate;
    }

    public ClientDTO() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPassportId() {
        return passportId;
    }

    public void setPassportId(long passportId) {
        this.passportId = passportId;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public long getPassportIssueDate() {
        return passportIssueDate;
    }

    public void setPassportIssueDate(long passportIssueDate) {
        this.passportIssueDate = passportIssueDate;
    }

    public long getPassportEndDate() {
        return passportEndDate;
    }

    public void setPassportEndDate(long passportEndDate) {
        this.passportEndDate = passportEndDate;
    }

    @Override
    public String toString() {
        return "ClientDTO{" +
                "password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surName='" + surName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", passportId=" + passportId +
                ", passport='" + passport + '\'' +
                ", passportIssueDate=" + passportIssueDate +
                ", passportEndDate=" + passportEndDate +
                '}';
    }
}
