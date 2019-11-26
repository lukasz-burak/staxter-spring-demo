package com.staxter.userrepository;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class UserLoginDto {

    @Valid
    @NotNull
    private String userName;

    @Valid
    @NotNull
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserLoginDto that = (UserLoginDto) o;

        return Objects.equals(userName, that.userName);
    }

    @Override
    public int hashCode() {
        return userName != null ? userName.hashCode() : 0;
    }
}
