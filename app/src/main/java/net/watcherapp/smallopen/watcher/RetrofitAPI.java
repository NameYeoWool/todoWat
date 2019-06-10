package net.watcherapp.smallopen.watcher;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitAPI {

    @GET("/room/info/{seatname}/")
    Call<PcInfoOfJson> getPcInfo(@Path("seatname") String seatname);

}