package com.brokendroidproductions.favebeer;


import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Rory on 31/07/2015.
 */
public interface BeerApi {

    @GET("/v2/beer/random?hasLabels=y&key=989281d855a6205b3dc1889b171d8931&format=json")
    public void getRandomBeer(Callback<Beer> response);

}
