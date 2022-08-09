package com.example.onlineretailers.network_okhttp;

import android.os.Handler;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpEngine {

    //加上volatile使得HttpEngine的变化对各线程可见
    private static volatile HttpEngine httpEngineInstances;
    private final OkHttpClient okHttpClient;
    private final Handler mHandler;


    /**
     * DCL模式创建单例
     */
    public static HttpEngine getHttpEngineInstances() {
        if (httpEngineInstances == null) {
            synchronized (HttpEngine.class) {
                if (httpEngineInstances == null) {
                    httpEngineInstances = new HttpEngine();
                }
            }
        }
        return httpEngineInstances;
    }

    /**
     * 初始化一些变量
     */
    private HttpEngine() {
        /*File sdCache = context.getExternalCacheDir();//内部存储的地址
        int cacheSize = 10 * 1024 * 1024;//开辟的内存空间大小*/

        okHttpClient = new OkHttpClient().newBuilder() //创建okHttpClient实例并初始化一些设置（建造者模式）
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();

        mHandler = new Handler();
    }

    /**
     * 异步GET请求
     * @param url 网络地址
     * @param callback 回调引用
     */
    public void getAsynchronous(String url, ResultCallback callback) {
        final Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = okHttpClient.newCall(request);

        dealResult(call, callback);

    }


    /**
     * 处理返回数据
     * @param call call请求
     * @param callback 回调引用
     */
    private void dealResult(Call call, final ResultCallback callback) {
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                sendSuccessCallback(Objects.requireNonNull(response.body()).string(),
                        callback);//发送成功的回调
            }
        });
    }

    /**
     * 通过回调返回数据
     * @param str 返回的数据
     * @param callback 回调引用
     */
    private void sendSuccessCallback(final String str, final ResultCallback callback) {
        mHandler.post(() -> callback.onResponse(str));
    }

    private void sendFailureCallback(ResultCallback callback) {
        mHandler.post(callback::onError);
    }
}
