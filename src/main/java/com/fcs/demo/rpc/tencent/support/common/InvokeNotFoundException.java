package com.fcs.demo.rpc.tencent.support.common;

/**
 * 用于封装UnCheckException
 * 
 * @author wuqq
 *
 */
public class InvokeNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -684314835123125276L;

	
    public InvokeNotFoundException(String message) {
    	super(message);
    }

    public InvokeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvokeNotFoundException(Throwable cause) {
        super(cause);
    }
}
