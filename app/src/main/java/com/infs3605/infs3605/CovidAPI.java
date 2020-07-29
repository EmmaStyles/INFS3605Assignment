package com.infs3605.infs3605;

import com.infs3605.infs3605.APIEntities.RecordsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CovidAPI {

    @GET("datastore_search?resource_id=21304414-1ff1-4243-a5d2-f52778048b29&limit=10000&sort=notification_date desc")
    Call<RecordsResponse> getRecords();

}