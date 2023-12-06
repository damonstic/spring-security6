package com.example.springsecurity6.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HnResult {
    private Integer code;
    private String msg;
    private Object data;

    public static HnResult ok(String message) {
        return HnResult.builder().code(200).msg(message).build();
    }

    public static HnResult ok() {
        return HnResult.builder().code(200).msg("成功").build();
    }

    public static HnResult error(String message){
        return HnResult.builder().code(500).msg(message).build();
    }

    public static HnResult error(){
        return HnResult.builder().code(500).msg("失败").build();
    }

    public <T> HnResult setData(T data){
        this.data = data;
        return this;
    }
}