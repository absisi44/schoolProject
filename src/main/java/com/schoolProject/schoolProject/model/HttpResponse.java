package com.schoolProject.schoolProject.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.util.Date;

public class HttpResponse {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyy hh:mm:ss",timezone = "IST")
    private Date timeStamp;
    private int httpStatusode;
    private HttpStatus httpStatus;
    private String reson;
    private String message;

    public HttpResponse(){}

    public HttpResponse(int httpStatusode, HttpStatus httpStatus, String reson, String message) {
        super();
        this.timeStamp=new Date();
        this.httpStatusode = httpStatusode;
        this.httpStatus = httpStatus;
        this.reson = reson;
        this.message = message;
    }


    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getHttpStatusode() {
        return httpStatusode;
    }

    public void setHttpStatusode(int httpStatusode) {
        this.httpStatusode = httpStatusode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getReson() {
        return reson;
    }

    public void setReson(String reson) {
        this.reson = reson;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
