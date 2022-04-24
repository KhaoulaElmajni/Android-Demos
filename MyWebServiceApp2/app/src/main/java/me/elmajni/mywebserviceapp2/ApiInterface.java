package me.elmajni.mywebserviceapp2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("/tasks")
    Call<List<Task>>getTasks();

    @POST("tasks")
    Call<Task> createTask(@Body Task task);

    @PUT("tasks/{id}")
    Call<Task> putTask(@Path("id") int id, @Body Task task);

    @PATCH("tasks/{id}")
    Call<Task> patchTask(@Path("id") int id, @Body Task task);

    @DELETE("tasks/{id}")
    Call<Task> deleteTask(@Path("id") int id);
}
