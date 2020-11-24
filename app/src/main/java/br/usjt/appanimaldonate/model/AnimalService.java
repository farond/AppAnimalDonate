package br.usjt.appanimaldonate.model;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AnimalService {

    @GET("/api/2829e58641a3416a9ef606e64bfe2c96/animal")
    Call<List<Animal>> getAllAnimais();


    @POST("/api/2829e58641a3416a9ef606e64bfe2c96/animal")
    Call<ResponseBody> salvarAnimal(
            @Body
                    Animal animal);

    @PUT("/api/2829e58641a3416a9ef606e64bfe2c96/animal/{id}")
    Call<ResponseBody> alterarAnimal(
            @Path("id") String id,
            @Body AnimalPut animalPut);

    @DELETE("/api/2829e58641a3416a9ef606e64bfe2c96/animal/{id}")
    Call<ResponseBody> deletarAnimal(
            @Path("id") String id);

}
