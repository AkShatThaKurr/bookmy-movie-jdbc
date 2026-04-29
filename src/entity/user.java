package entity;

public class user {
    private int userId;
    private String name;
    private String phone;
    private String mail;

    public user(int userId, String name, String phone, String mail) {
        this.userId = userId;
        this.name = name;
        this.phone = phone;
        this.mail = mail;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
