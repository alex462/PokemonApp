package com.example.alexandrareinhart.pokemonapp;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    public static final String POKEMON_NAME = "pokemon_name";

    private PokemonFragment pokemonFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    public void onBackPressed() {
        if (pokemonFragment.isVisible()) {
            super.onBackPressed();
        }
    }

    @BindView(R.id.pokemon_name_input)
    protected TextInputEditText pokemonNameEditText;

    @OnClick(R.id.capture_button)
    protected void captureButton() {

        if(pokemonNameEditText.getText().toString().isEmpty()){
            Toast.makeText(this, "POKEMON NAME REQUIRED FOR SEARCH", Toast.LENGTH_LONG).show();
        } else {
            pokemonFragment = PokemonFragment.newInstance();
            Bundle bundle = new Bundle();
            bundle.putString(POKEMON_NAME, pokemonNameEditText.getText().toString());
            pokemonFragment.setArguments(bundle);

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, pokemonFragment).commit();
        }
    }
}
