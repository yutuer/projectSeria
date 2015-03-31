package com.pureland.common.error;

public class CoreException extends CommonException {

    /**
     *
     */
    private static final long serialVersionUID = -5752051928966254580L;

    public CoreException(int code, String message, Throwable throwable) {
        super(message, throwable);
        super.setCode(code);
    }

    public CoreException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public CoreException(int code, String message) {
        super(message);
        super.setCode(code);
    }

    public CoreException(String message, Object... params) {
        super(String.format(message + "\n", params));
    }

    public CoreException(Throwable arg0) {
        super(arg0);
    }


}
