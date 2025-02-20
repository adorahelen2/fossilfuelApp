package dcu.fossilfuel.api;

import java.util.List;

import dcu.fossilfuel.Guestbook;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @GET("guestbook/get")
    Call<List<Guestbook>> getGuestbookEntries();

    @POST("guestbook/post")
    Call<Guestbook> addGuestbookEntry(@Body Guestbook entry);
}
