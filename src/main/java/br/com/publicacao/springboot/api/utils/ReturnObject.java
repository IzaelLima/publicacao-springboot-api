package br.com.publicacao.springboot.api.utils;

import java.io.Serializable;

public class ReturnObject implements Serializable {

    private Integer status;
    private Object result;
    private String message;

    public ReturnObject(Integer sucesso, Object result, String message) {
        this.status = sucesso;
        this.result = result;
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
