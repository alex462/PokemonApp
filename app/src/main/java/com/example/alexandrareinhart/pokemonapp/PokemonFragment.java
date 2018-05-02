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

import com.bumptech.glide.Glide;

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
    String pokemonName;


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

    public static PokemonFragment newInstance() {

        Bundle args = new Bundle();

        PokemonFragment fragment = new PokemonFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();

        String pokemonName = getArguments().getString(POKEMON_NAME);
        buildRetrofit();
        makeApiCalls(pokemonName);
    }

    private void makeApiCalls(final String pokemonName) {

        retrofitPokemonApiCalls.getPokemonInfo(pokemonName).enqueue(new Callback<RetrofitPokemonApiCalls.PokemonInfo>() {
            @Override
                public void onResponse
                (Call < RetrofitPokemonApiCalls.PokemonInfo > call, Response < RetrofitPokemonApiCalls.PokemonInfo> response) {
                if (response.isSuccessful()) {
                    pokemonNameTextView.setText(response.body().getName());
                    Glide.with(getContext()).load(response.body().getSprites().getDefaultImage()).into(pokemonPictureImageView);

//                    makeSecondApiCall(response.body().getEffect());
                } else {
                    Toast.makeText(getContext(), "YOU MISSED! TRY A DIFFERENT POKEMON.", Toast.LENGTH_LONG).show();
//                    getSupportFragmentManager().beginTransaction().remove(R.id.fragment_holder, container).commit();
                }
            }
            @Override
            public void onFailure(Call<RetrofitPokemonApiCalls.PokemonInfo> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void makeSecondApiCall(int id) {

//        retrofitPokemonApiCalls.getPokemonAbilities()
    }


    private void buildRetrofit() {

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitPokemonApiCalls = retrofit.create(RetrofitPokemonApiCalls.class);
    }
}
