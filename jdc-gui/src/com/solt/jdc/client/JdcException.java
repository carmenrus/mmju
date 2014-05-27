package com.solt.jdc.client;

public class JdcException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private boolean alert;

	public JdcException(boolean alert, String message) {
		super(message);
		this.alert = alert;
	}

	public JdcException(boolean alert, String message, Throwable cause) {
		super(message, cause);
		this.alert = alert;
	}

	public JdcException(boolean alert, String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.alert = alert;
	}

	public boolean isAlert() {
		return alert;
	}

	public void setAlert(boolean alert) {
		this.alert = alert;
	}
}
