package com.lab.community.controller;


import com.lab.community.common.code.ResultCode;
import com.lab.community.controller.payload.ErrorPayload;
import lombok.Getter;
import org.springframework.boot.web.server.ErrorPage;

@Getter
public class ResponseData<T> extends ResponseBase {

    private final T resultData;

    public ResponseData(ResultCode resultCode) {
        this(resultCode, null);
    }

    public ResponseData(ResultCode resultCode, T resultData) {
        super(resultCode);
        this.resultData = resultData;
    }

    public static <T> ResponseData<T> success(T resultData) {
        return new ResponseData<T>(ResultCode.RESULT_0000, resultData);
    }

    public static ResponseData<String> success(){
        return new ResponseData<>(ResultCode.RESULT_0000);
    }

    public static ResponseData<ErrorPayload> error(ErrorPayload errorPayload){;
        return new ResponseData<>(ResultCode.RESULT_9999, errorPayload);
    }

    public static ResponseData<String> error(ResultCode resultCode){
        return new ResponseData<>(resultCode);
    }

    public static <T> ResponseData<T> error(ResultCode resultCode, T errorPayload){
        return new ResponseData<T>(resultCode, errorPayload);
    }

}
