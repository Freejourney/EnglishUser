package com.example.englishuser;
/*
 * 文件名：RequsetInterface
 * 作者：created by admin on 2019 五月
 * 描述：
 *
 */


import retrofit2.Call;
import retrofit2.http.GET;

public interface RequsetInterface {

    @GET("?api-key=d92fd1f9-e11d-4498-b38d-709581ee11df")
    Call<NewsModel> getNewsCall();
}
