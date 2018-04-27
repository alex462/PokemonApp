package com.example.alexandrareinhart.pokemonapp;

import com.google.gson.annotations.SerializedName;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitPokemonApiCalls {

    @GET("{pokemon}")
    Call<PokemonName> getPokemon(@Path("pokemon") String pokemon);

    Class PokemonName {

        @SerializedName("summary")

                private String summary;
                public String getSummary() {

                    return summary;
        }
    }
}
