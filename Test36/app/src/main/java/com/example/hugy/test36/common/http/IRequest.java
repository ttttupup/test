package com.example.hugy.test36.common.http;

import java.util.Map;


/**
 *
 */
public interface IRequest {
    public static final String POST = "POST";
    public static final String GET = "GET";

    /**
     * 指定请求方式
      */
    void setMethod(String method);

    /**
     * 指定请求头部
     * @param key
     * @param value
     */
    void setHeader(String key, String value);

    /**
     *  指定请求参数
     * @param key
     * @param value
     */
    void setBody(String key, String value);

    /**
     * 提供给执行库请求行URL
     * @return
     */
    String getUrl();

    /**
     * 提供给执行库请求头部
     * @return
     */
    Map<String, String> getHeader();
    /**
     * 提供给执行库请求参数
     * @return
     */
    Object getBody();


}
