package com.example.hugy.test36.base;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class HttpServiceFactory {
    private volatile static OkHttpClient okHttpClient;
    private volatile static Retrofit retrofit;

    private static final int DEFAULT_CACHE_SIZE = 1024 * 1024 * 20;//默认缓存大小20M
    private static final int DEFAULT_MAX_AGE = 60 * 60;// 默认缓存时间单位
    private static final int DEFAULT_MAX_STALE_ONLINE = DEFAULT_MAX_AGE * 24;// 默认在线缓存时间
    private static final int DEFAULT_MAX_STALE_OFFLINE = DEFAULT_MAX_AGE * 24 * 7;// 默认离线缓存时间
    private static final int DEFAULT_WRITE_TIME = 60;
    private static final int DEFAULT = 60;

    /**
     * 获取retrofit实例
     *
     * @param baseUrl baseURL
     * @return retrofit实例
     */
    public static Retrofit getRetrofit(String baseUrl) {
        if (retrofit == null) {
            synchronized (Retrofit.class) {
                if (retrofit == null) {
                    retrofit = new Retrofit.Builder()
                            .client(getOkHttpClient())
                            .baseUrl(baseUrl)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .build();
                }
            }
        }
        return retrofit;
    }

    /**
     * 处理Json不规范带来的问题
     */
    private static Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    /**
     * 创建Service
     *
     * @param baseUrl      base url
     * @param serviceClazz 定义的服务类（通常是接口）
     * @param <T>          泛形，对应与定义的服务类
     * @return 定义的服务类
     */
    public static <T> T createService(String baseUrl, Class<T> serviceClazz) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(getOkHttpClient())
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(serviceClazz);
    }

    /**
     * 获取OkHttpClient实例
     *
     * @return OkHttpClient实例
     */
    private static OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            synchronized (OkHttpClient.class) {// 同步访问
                if (okHttpClient == null) {
                    okHttpClient = new OkHttpClient.Builder()
                            .writeTimeout(DEFAULT, TimeUnit.SECONDS)
                            .readTimeout(DEFAULT, TimeUnit.SECONDS)
                            .connectTimeout(DEFAULT, TimeUnit.SECONDS)
                            .build();
                }
            }
        }
        return okHttpClient;
    }
}
