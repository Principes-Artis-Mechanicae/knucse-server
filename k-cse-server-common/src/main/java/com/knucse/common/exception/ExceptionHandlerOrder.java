package com.knucse.common.exception;

public interface ExceptionHandlerOrder {
	int BUSINESS_EXCEPTION_HANDLER = 1;
	int SECURITY_EXCEPTION_HANDLER = 2;
	int CUSTOM_EXCEPTION_HANDLER = 3;
	int GLOBAL_EXCEPTION_HANDLER = 4;
}
