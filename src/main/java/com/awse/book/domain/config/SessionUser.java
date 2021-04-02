package com.awse.book.domain.config;

import java.io.Serializable;

import com.awse.book.domain.user.User;

import lombok.Getter;

@Getter
public class SessionUser implements Serializable {
    
    private String name, email , picture;

    public SessionUser(User user) {
        this.name = user.getName() ;
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
