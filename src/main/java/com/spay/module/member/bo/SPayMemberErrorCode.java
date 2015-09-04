package com.spay.module.member.bo;

import com.spay.exception.ErrorCode;

public enum SPayMemberErrorCode implements ErrorCode {
	
	MEMBER_ADD_FAIL("M0001"), MEMBER_MODIFY_FAIL("M0002"), MEMBER_DELETE_FAIL("M0003"), MEMBER_READ_FAIL("M0004");

	private static final String ERROR_PREFIX = "error.member";
    private final String code;

    SPayMemberErrorCode(String code) {
        this.code = code;
    }
    
	@Override
	public String getCode() {
		return ERROR_PREFIX + code;
	}

}
