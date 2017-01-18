package com.brandtechnosolutions.petbaazar;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class TakeAdInfoActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView petTypeTextView;
    ArrayAdapter spinnerAdapterPetType;
    Spinner spinnerPetType;
    EditText otherTypeEditText, otherBreedEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_ad_info);
        petTypeTextView = (TextView) findViewById(R.id.textView10);
        otherTypeEditText = (EditText) findViewById(R.id.other_pet_edit_text_id);
        otherBreedEditText = (EditText) findViewById(R.id.other_pet_edit_text_id);
        spinnerPetType = (Spinner) findViewById(R.id.type_spinner);
        ArrayAdapter spinnerAdapter = ArrayAdapter.createFromResource(
                this, R.array.pets, android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = (Spinner) findViewById(R.id.pet_spinner);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(this);
    }

    void onOk(View view) {
        FragmentManager manager = getSupportFragmentManager();
        SellerOptionFragment dialogFragment = new SellerOptionFragment();
        dialogFragment.show(manager, "Alert");
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String tempString = adapterView.getItemAtPosition(i).toString();
        loadSpinner(tempString);
//        Toast.makeText(this,"selected: "+tempString,Toast.LENGTH_LONG).show();
    }

    void loadSpinner(String spinnerString) {
        switch (spinnerString) {
            case "Cats":
                findViewById(R.id.relative_spinner_id).setVisibility(View.VISIBLE);
                petTypeTextView.setText("Select Cat Breeds");
                petTypeTextView.setVisibility(View.VISIBLE);
                spinnerPetType.setVisibility(View.VISIBLE);
                spinnerAdapterPetType = ArrayAdapter.createFromResource(
                        this, R.array.Cats, android.R.layout.simple_spinner_dropdown_item);
                spinnerPetType.setAdapter(spinnerAdapterPetType);
                break;
            case "Dogs":
                findViewById(R.id.relative_spinner_id).setVisibility(View.VISIBLE);
                petTypeTextView.setText("Select Dog Breeds");
                petTypeTextView.setVisibility(View.VISIBLE);
                spinnerPetType.setVisibility(View.VISIBLE);
                spinnerAdapterPetType = ArrayAdapter.createFromResource(
                        this, R.array.Dogs, android.R.layout.simple_spinner_dropdown_item);
                spinnerPetType.setAdapter(spinnerAdapterPetType);
                break;
            case "Fishes":
                findViewById(R.id.relative_spinner_id).setVisibility(View.VISIBLE);
                petTypeTextView.setText("Select Fish Breeds");
                petTypeTextView.setVisibility(View.VISIBLE);
                spinnerPetType.setVisibility(View.VISIBLE);
                spinnerAdapterPetType = ArrayAdapter.createFromResource(
                        this, R.array.Fishes, android.R.layout.simple_spinner_dropdown_item);
                spinnerPetType.setAdapter(spinnerAdapterPetType);
                break;
            case "Parrots":
                findViewById(R.id.relative_spinner_id).setVisibility(View.VISIBLE);
                petTypeTextView.setText("Select Parrot Breeds");
                petTypeTextView.setVisibility(View.VISIBLE);
                spinnerPetType.setVisibility(View.VISIBLE);
                spinnerAdapterPetType = ArrayAdapter.createFromResource(
                        this, R.array.Parrots, android.R.layout.simple_spinner_dropdown_item);
                spinnerPetType.setAdapter(spinnerAdapterPetType);
                break;
            case "Pigeons":
                findViewById(R.id.relative_spinner_id).setVisibility(View.VISIBLE);
                petTypeTextView.setText("Select Pigeon Breeds");
                petTypeTextView.setVisibility(View.VISIBLE);
                spinnerPetType.setVisibility(View.VISIBLE);
                spinnerAdapterPetType = ArrayAdapter.createFromResource(
                        this, R.array.Pigeons, android.R.layout.simple_spinner_dropdown_item);
                spinnerPetType.setAdapter(spinnerAdapterPetType);
                break;
            case "Other":
                otherTypeEditText.setVisibility(View.VISIBLE);
                otherBreedEditText.setVisibility(View.VISIBLE);
            default:
                petTypeTextView.setVisibility(View.GONE);
                spinnerPetType.setVisibility(View.GONE);
                Toast.makeText(this, "default selected: " + spinnerString, Toast.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}