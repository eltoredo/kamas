package com.example.kotlinproject

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;

class OKHttpRequest(client: OkHttpClient) {

    private val client = OkHttpClient()

    fun GET(url: String, callback: Callback): Call {
        val request = Request.Builder()
                .url(url)
                .build();

        val call = client.newCall(request);
        call.enqueue(callback);
        return call;
    }

    companion object {
        val JSON = MediaType.parse("application/json; charset=utf-8")
    }
}