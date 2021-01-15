package com.job.offers.data.source.remote;

import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static final String BASE_URL = "https://staging.elafbds.com/api/";
    public static final String BASE_URL_IMAGE = "https://cap10.app/";
//    public static final String BASE_URL = "http://185.224.139.9:80/api/";
//    public static final String BASE_URL_IMAGE = "http://185.224.139.9:80/";

    private static final Retrofit.Builder builder = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create());

    private static final Retrofit retrofit = builder.build();

    public static <T> T buildServices(Class<T> type) {
        return retrofit.create(type);
    }

}