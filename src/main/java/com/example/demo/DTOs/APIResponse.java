package com.example.demo.DTOs;

public class APIResponse<T> {
    private int status;
    private String messege;
    private T data; 

    public APIResponse(int status, String messege, T data){
        this.status = status;
        this.messege = messege;
        this.data = data;
    }

   
    public APIResponse() {
    }


    public int getStatus(){
        return status;
    }

    public String getMessege(){
        return messege;
    }

    public T getData(){
        return data;
    }

    public void setStatus(int status){
        this.status = status;
    }

    public void setMessege(String messege){
        this.messege = messege;

    }

    public void setData(T data){
        this.data = data;
    }
}
