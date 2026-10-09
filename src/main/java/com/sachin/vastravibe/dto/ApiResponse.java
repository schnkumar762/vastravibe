package com.sachin.vastravibe.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

@JsonPropertyOrder({"status", "message", "data"})
public class ApiResponse<T> {

    private String status;
    private String message;
    private T data;
}