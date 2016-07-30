package com.gintong.exception;

/**
 * Created by lenovo on 2016/7/15.
 */
public class HttpProcessException  extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = -2749168865492921426L;

    public HttpProcessException(Exception e){
        super(e);
    }

    /**
     * @param string
     */
    public HttpProcessException(String msg) {
        super(msg);
    }

    /**
     * @param message
     * @param e
     */
    public HttpProcessException(String message, Exception e) {
        super(message, e);
    }

}
