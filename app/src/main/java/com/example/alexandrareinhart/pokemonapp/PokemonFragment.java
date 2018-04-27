package com.example.alexandrareinhart.pokemonapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.alexandrareinhart.pokemonapp.MainActivity.POKEMON_NAME;

public class PokemonFragment extends Fragment {

    private String baseUrl = "http://pokeapi.co/api/v2/pokemon/";
    private Retrofit retrofit;
    private RetrofitPokemonApiCalls retrofitPokemonApiCalls;


    @BindView(R.id.pokemon_name_textView)
    protected TextView pokemonNameTextView;

    @BindView(R.id.pokemon_imageView)
    protected ImageView pokemonPictureImageView;

    @BindView(R.id.pokemon_summary_textView)
    protected TextView pokemonSummary;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pokemon, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        String pokemonName = getArguments().getString(POKEMON_NAME);
        buildRetrofit();
        makeApiCall(pokemonName);
    }

    private void makeApiCall(final String pokemonName) {

        RetrofitPokemonApiCalls.getPokemon(pokemonName).enqueue(new Callback<RetrofitPokemonApiCalls.PokemonName>() {
            @Override
                public void onResponse
                (Call < RetrofitPokemonApiCalls.PokemonName > call, Response < RetrofitPokemonApiCalls.PokemonName> response) {
                if (response.isSuccessful()) {
                    pokemonNameTextView.setText(POKEMON_NAME);
                    //TODO - set picture
                    pokemonSummary.setText(response.body().getSummary());
                } else {
                    Toast.makeText(getContext(), "YOU MISSED! TRY A DIFFERENT POKEMON.", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<RetrofitPokemonApiCalls.PokemonName> call, Throwable t) {
                t.printStackTrace();
            }
        }
    }

    public static PokemonFragment newInstance() {
        
        Bundle args = new Bundle();
        
        PokemonFragment fragment = new PokemonFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void buildRetrofit() {

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitPokemonApiCalls = retrofit.create(RetrofitPokemonApiCalls.class);
    }
}
