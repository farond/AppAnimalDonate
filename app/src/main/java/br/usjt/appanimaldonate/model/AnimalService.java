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

    @GET("/api/33b321ad522b4c2ebc11a3b549dad679")
    Call<List<Animal>> getAllAnimais();


    @POST("/api/33b321ad522b4c2ebc11a3b549dad679")
    Call<ResponseBody> salvarAnimal(
            @Body
                    Animal animal);

    @PUT("/api/33b321ad522b4c2ebc11a3b549dad679/animal/{id}")
    Call<ResponseBody> alterarAnimal(
            @Path("id") String id,
            @Body AnimalPut animalPut);

    @DELETE("/api/33b321ad522b4c2ebc11a3b549dad679/animal/{id}")
    Call<ResponseBody> deletarAnimal(
            @Path("id") String id);

}
