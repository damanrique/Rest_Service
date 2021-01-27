package co.edu.unipiloto.rest_service;


import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PostService {

    String API_ROUTE = "/posts/{id}";
    @GET(API_ROUTE)
    Call <Post> getPost(@Path("id") int id);








}
