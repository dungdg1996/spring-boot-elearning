package com.myclass.elearning.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {
    private String type = "info";
    private String content;

    public Message type(String type) {
        this.type = type;
        return this;
    }

    public Message content(String content) {
        this.content = content;
        return this;
    }
}
