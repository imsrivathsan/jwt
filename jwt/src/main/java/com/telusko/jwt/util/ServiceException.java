package com.telusko.jwt.util;


import org.springframework.http.HttpStatus;

public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private HttpStatus status = HttpStatus.OK;

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public ServiceException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

}
