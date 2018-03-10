package com.example.hugy.test36;

import android.nfc.Tag;
import android.util.Log;

import com.example.hugy.test36.common.http.IHttpClient;
import com.example.hugy.test36.common.http.IRequest;
import com.example.hugy.test36.common.http.impl.OkHttpClientImpl;

import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    public String test(String par ,String pp,short ss){

        return "";
    }
    class TTT{
        private String ret;
        private String msg;

        public String getRet() {
            return ret;
        }

        public String getMsg() {
            return msg;
        }

        public void setRet(String ret) {
            this.ret = ret;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

    public interface ITestHTTp {
        @GET("admin/test/test")
        Call<TTT> listRepos();
    }
    @Test
    public void test1111() throws IOException {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.31.138:8088/")
//                .baseUrl("http://www.baidu.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        ITestHTTp iTestHTTp = retrofit.create(ITestHTTp.class);
        Call<TTT> call = iTestHTTp.listRepos();
        Log.e("d", "strat: ");
        System.out.println("ssss");
//        Response<TTT> execute = call.execute();
//        System.out.println(execute.body().getRet());
//        System.out.println(execute.body().getMsg());
//        System.out.println("www");


        call.enqueue(new Callback<TTT>() {
            @Override
            public void onResponse(Call<TTT> call, Response<TTT> response) {
                System.out.println(response.body().getMsg());
            }

            @Override
            public void onFailure(Call<TTT> call, Throwable t) {
                System.out.println("222wee");
            }
        });
        System.out.print("wwwwwwwwwwwwww");

    }


    }

