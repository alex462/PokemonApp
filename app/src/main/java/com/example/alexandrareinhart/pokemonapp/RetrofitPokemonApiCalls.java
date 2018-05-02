package com.example.alexandrareinhart.pokemonapp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitPokemonApiCalls {

    @GET("pokemon/{name}")
    Call<PokemonInfo> getPokemonInfo(@Path("name") String pokemonName);

    @GET("type/{name}")
    Call<PokemonType> getPokemonType(@Path("name") String pokemonType);

    @GET("ability/{name}")
    Call<PokemonAbilities> getPokemonAbilities(@Path("name") int pokemonId);

    class PokemonInfo {

        @SerializedName("name")
        private String name;
        @SerializedName("id")
        private int id;
        @SerializedName("sprites")
        private Sprites sprites;

        public String getName() {
            return name;
        }

        public int getId() {
            return id;
        }

        public Sprites getSprites() {
            return sprites;
        }

        class Sprites {
            @SerializedName("front_default")
            private String defaultImage;

            public String getDefaultImage() {
                return defaultImage;
            }
        }
    }

    class PokemonType {

        @SerializedName("name")
        private String type;

        public String getType() {
            return type;
        }
    }

    //return "effect" within effect_entries
    class PokemonAbilities {

        @SerializedName("effect_entries")
        private List<PokemonEffects> pokemonEffectsList;

        public List<PokemonEffects> getPokemonEffectsList() {
            return pokemonEffectsList;
        }

        class PokemonEffects {
            @SerializedName("effect")
            private String effect;

            public String getEffect() {
                return effect;
            }
        }
    }
}
