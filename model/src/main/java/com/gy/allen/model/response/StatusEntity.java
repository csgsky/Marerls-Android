package com.gy.allen.model.response;

import com.gy.allen.model.rest.constants.NetConstants;

public class StatusEntity {
    public int error_code;
    public String error_msg;

    public boolean isInvalidCode() {return error_code != NetConstants.ErrorCode.CODE_SUCCESS;}
}
