package com.lyl.batch.model;

/**
 * Date:2018/12/18 0018 下午 2:23
 *
 * @author :liyunlong
 * @Description:
 * @UpdateDate: 2018/12/18 0018 下午 2:23
 */

public class User {

    private int id;

    private String password;

    private String phone;

    private String username;

    public User(int id, String password, String phone, String username) {
        this.id = id;
        this.password = password;
        this.phone = phone;
        this.username = username;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
