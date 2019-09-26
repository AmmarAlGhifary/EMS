package com.blogspot.yourfavoritekaisar.ems.network;

import com.blogspot.yourfavoritekaisar.ems.NewsInet.Pengumuman.VIew.PengumumanResponse;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;

public interface ApiInterface {

    @FormUrlEncoded
    @GET("GetNewsCategory.php")
    Call<PengumumanResponse> getCategoryNes();




}
