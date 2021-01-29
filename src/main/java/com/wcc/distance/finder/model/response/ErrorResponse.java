package com.wcc.distance.finder.model.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Generic Error Response model class
 *
 * @author praveen kumar m
 * @version 1.0
 * @since 23-Jan-2021
 */
@Getter
@Setter
public class ErrorResponse {
    private int statusCode;
    private Date timestamp;
    private String message;
    private String description;

    public ErrorResponse(int statusCode, Date timestamp, String message, String description) {
        this.statusCode = statusCode;
        this.timestamp = timestamp;
        this.message = message;
        this.description = description;
    }


}
