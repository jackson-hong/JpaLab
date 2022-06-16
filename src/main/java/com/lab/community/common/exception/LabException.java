package com.lab.community.common.exception;

import com.lab.community.common.code.ResultCode;
import lombok.Getter;

@Getter
public class LabException extends RuntimeException{

    private final ResultCode resultCode;
    private final String description;

    public LabException(ResultCode resultCode) {
        this(resultCode, resultCode.getResultMessage());
    }

    public LabException(ResultCode resultCode, String message){
        super(message);
        this.resultCode = resultCode;
        this.description = message;
    }
}
