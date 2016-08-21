package by.pvt.entity;

import java.util.Date;

/**
 * Класс для формирования объекта клиента
 */
public class Client {
    private long clientID;
    private String password;
    private String name;
    private String surName;
    private String phone;
    private String email;
    private long passportId;
    private String passport;
    private Date passportIssueDate;
    private Date passportEndDate;
    private String status;
    private String role;

    public Client() {
    }

    public Client(String password, String name, String surName, String phone, String email, String passport, Date passportIssueDate, Date passportEndDate) {
        this.password = password;
        this.name = name;
        this.surName = surName;
        this.phone = phone;
        this.email = email;
        this.passport = passport;
        this.passportIssueDate = passportIssueDate;
        this.passportEndDate = passportEndDate;
    }

    public Client(long clientID, String password, String name, String surName, String phone, String email, long passportId, String passport, Date passportIssueDate, Date passportEndDate, String status, String role) {
        this.clientID = clientID;
        this.password = password;
        this.name = name;
        this.surName = surName;
        this.phone = phone;
        this.email = email;
        this.passportId = passportId;
        this.passport = passport;
        this.passportIssueDate = passportIssueDate;
        this.passportEndDate = passportEndDate;
        this.status = status;
        this.role = role;
    }

    public long getClientID() {
        return clientID;
    }

    public void setClientID(long clientID) {
        this.clientID = clientID;
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

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public Date getPassportIssueDate() {
        return passportIssueDate;
    }

    public void setPassportIssueDate(Date passportIssueDate) {
        this.passportIssueDate = passportIssueDate;
    }

    public Date getPassportEndDate() {
        return passportEndDate;
    }

    public void setPassportEndDate(Date passportEndDate) {
        this.passportEndDate = passportEndDate;
    }

    public long getPassportId() {
        return passportId;
    }

    public void setPassportId(long passportId) {
        this.passportId = passportId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;

        Client client = (Client) o;

        if (clientID != client.clientID) return false;
        if (password != null ? !password.equals(client.password) : client.password != null) return false;
        if (name != null ? !name.equals(client.name) : client.name != null) return false;
        if (surName != null ? !surName.equals(client.surName) : client.surName != null) return false;
        if (phone != null ? !phone.equals(client.phone) : client.phone != null) return false;
        if (email != null ? !email.equals(client.email) : client.email != null) return false;
        if (passport != null ? !passport.equals(client.passport) : client.passport != null) return false;
        if (passportIssueDate != null ? !passportIssueDate.equals(client.passportIssueDate) : client.passportIssueDate != null)
            return false;
        if (passportEndDate != null ? !passportEndDate.equals(client.passportEndDate) : client.passportEndDate != null)
            return false;
        if (status != null ? !status.equals(client.status) : client.status != null) return false;
        return role != null ? role.equals(client.role) : client.role == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (clientID ^ (clientID >>> 32));
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surName != null ? surName.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (passport != null ? passport.hashCode() : 0);
        result = 31 * result + (passportIssueDate != null ? passportIssueDate.hashCode() : 0);
        result = 31 * result + (passportEndDate != null ? passportEndDate.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientID=" + clientID +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surName='" + surName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", passportId=" + passportId +
                ", passport='" + passport + '\'' +
                ", passportIssueDate=" + passportIssueDate +
                ", passportEndDate=" + passportEndDate +
                ", status='" + status + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
