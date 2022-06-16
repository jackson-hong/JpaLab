package com.lab.community.common.type;

import com.lab.community.common.code.ResultCode;
import com.lab.community.common.exception.LabException;
import org.springframework.util.ObjectUtils;

public enum YnType {

    Y,
    N;

    public static YnType fromBoolean(Boolean expression){
        if(ObjectUtils.isEmpty(expression)) throw new LabException(ResultCode.RESULT_5001);
        return expression ? Y : N;
    }

    public static YnType from(String Yn) {
        if(ObjectUtils.isEmpty(Yn)) throw new LabException(ResultCode.RESULT_5001);
        if("Y".equalsIgnoreCase(Yn)) return Y;
        if("N".equalsIgnoreCase(Yn)) return N;
        throw new LabException(ResultCode.RESULT_5001);
    }

}
