package com.hyancy.eco_recicla_reto_1_grupo_7.data.models;

public class UserModel {
    private String name;
    private Integer age;
    private String email;
    private String password;

    public UserModel(String name, Integer age, String email, String password) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String createUserID(){
        RandomKey randomKeyGenerator = new RandomKey();
        return randomKeyGenerator.createRandomKey();
    }

}
