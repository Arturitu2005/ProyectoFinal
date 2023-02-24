package com.example.myproyectofinal.API.PokeAPI;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import com.example.myproyectofinal.API.PokemonR;



public interface PokeapiService {
    @GET("Pokemon")
    Call<PokemonR> obtenerListaPokemon(@Query("limit") int limit, @Query("offset") int offset);
}
