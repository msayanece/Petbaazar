package com.brandtechnosolutions.petbaazar;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class TakeAdInfoActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView petTypeTextView, takePictureTextId;
    ArrayAdapter spinnerAdapterPetType;
    Spinner spinnerPetType;
    RelativeLayout relativeAfterBreedText, relativeAfterBreedSpinner;
    EditText otherTypeEditText, otherBreedEditText;
    ImageButton takeImageButton;
    ImageView pictureTaken;
    File imageFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_ad_info);
        pictureTaken = (ImageView) findViewById(R.id.picture_taken_id);
        takePictureTextId = (TextView) findViewById(R.id.take_picture_text_id);
        takeImageButton = (ImageButton) findViewById(R.id.imageView7);
        petTypeTextView = (TextView) findViewById(R.id.textView10);
        otherTypeEditText = (EditText) findViewById(R.id.other_pet_edit_text_id);
        otherBreedEditText = (EditText) findViewById(R.id.other_breed_edit_text_id);
        relativeAfterBreedSpinner = (RelativeLayout) findViewById(R.id.after_breed_spinner_relative_id);
        relativeAfterBreedText = (RelativeLayout) findViewById(R.id.relative_spinner_id);
        spinnerPetType = (Spinner) findViewById(R.id.type_spinner);
//        otherTypeEditText.setVisibility(View.GONE);
//        otherBreedEditText.setVisibility(View.GONE);
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
                otherTypeEditText.setVisibility(View.GONE);
                otherBreedEditText.setVisibility(View.GONE);
                relativeAfterBreedText.setVisibility(View.VISIBLE);
                relativeAfterBreedSpinner.setVisibility(View.GONE);
                petTypeTextView.setText("Select Cat Breeds");
                petTypeTextView.setVisibility(View.VISIBLE);
                spinnerPetType.setVisibility(View.VISIBLE);
                spinnerAdapterPetType = ArrayAdapter.createFromResource(
                        this, R.array.Cats, android.R.layout.simple_spinner_dropdown_item);
                spinnerPetType.setAdapter(spinnerAdapterPetType);
                break;
            case "Dogs":
                otherTypeEditText.setVisibility(View.GONE);
                otherBreedEditText.setVisibility(View.GONE);
                relativeAfterBreedText.setVisibility(View.VISIBLE);
                relativeAfterBreedSpinner.setVisibility(View.GONE);
                petTypeTextView.setText("Select Dog Breeds");
                petTypeTextView.setVisibility(View.VISIBLE);
                spinnerPetType.setVisibility(View.VISIBLE);
                spinnerAdapterPetType = ArrayAdapter.createFromResource(
                        this, R.array.Dogs, android.R.layout.simple_spinner_dropdown_item);
                spinnerPetType.setAdapter(spinnerAdapterPetType);
                break;
            case "Fishes":
                otherTypeEditText.setVisibility(View.GONE);
                otherBreedEditText.setVisibility(View.GONE);
                relativeAfterBreedSpinner.setVisibility(View.GONE);
                relativeAfterBreedText.setVisibility(View.VISIBLE);
                petTypeTextView.setText("Select Fish Breeds");
                petTypeTextView.setVisibility(View.VISIBLE);
                spinnerPetType.setVisibility(View.VISIBLE);
                spinnerAdapterPetType = ArrayAdapter.createFromResource(
                        this, R.array.Fishes, android.R.layout.simple_spinner_dropdown_item);
                spinnerPetType.setAdapter(spinnerAdapterPetType);
                break;
            case "Parrots":
                otherTypeEditText.setVisibility(View.GONE);
                otherBreedEditText.setVisibility(View.GONE);
                relativeAfterBreedText.setVisibility(View.VISIBLE);
                relativeAfterBreedSpinner.setVisibility(View.GONE);
                petTypeTextView.setText("Select Parrot Breeds");
                petTypeTextView.setVisibility(View.VISIBLE);
                spinnerPetType.setVisibility(View.VISIBLE);
                spinnerAdapterPetType = ArrayAdapter.createFromResource(
                        this, R.array.Parrots, android.R.layout.simple_spinner_dropdown_item);
                spinnerPetType.setAdapter(spinnerAdapterPetType);
                break;
            case "Pigeons":
                otherTypeEditText.setVisibility(View.GONE);
                otherBreedEditText.setVisibility(View.GONE);
                relativeAfterBreedSpinner.setVisibility(View.GONE);
                relativeAfterBreedText.setVisibility(View.VISIBLE);
                petTypeTextView.setText("Select Pigeon Breeds");
                petTypeTextView.setVisibility(View.VISIBLE);
                spinnerPetType.setVisibility(View.VISIBLE);
                spinnerAdapterPetType = ArrayAdapter.createFromResource(
                        this, R.array.Pigeons, android.R.layout.simple_spinner_dropdown_item);
                spinnerPetType.setAdapter(spinnerAdapterPetType);
                break;
            case "Other":
                relativeAfterBreedSpinner.setVisibility(View.GONE);
                relativeAfterBreedText.setVisibility(View.GONE);
                otherTypeEditText.setVisibility(View.VISIBLE);
                petTypeTextView.setVisibility(View.GONE);
                spinnerPetType.setVisibility(View.GONE);
                otherBreedEditText.setVisibility(View.VISIBLE);
                break;
            default:
                relativeAfterBreedSpinner.setVisibility(View.GONE);
                relativeAfterBreedText.setVisibility(View.GONE);
                otherTypeEditText.setVisibility(View.GONE);
                otherBreedEditText.setVisibility(View.GONE);
                petTypeTextView.setVisibility(View.GONE);
                spinnerPetType.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    //stackoverflowlink: http://stackoverflow.com/questions/15408240/take-photo-from-camera-in-fragment
    //after caturing action finishes, this method will be called
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {      //resultCode = OK or Cancel
        Toast.makeText(this, "onActivityResult called", Toast.LENGTH_LONG).show();
        switch (requestCode) {
            case 0:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        takeImageButton.setVisibility(View.GONE);
                        takePictureTextId.setVisibility(View.GONE);
                        Bitmap bitImage = (Bitmap) data.getExtras().get("data");
                        pictureTaken.setImageBitmap(bitImage);        //set bitmap code to imageview (just like setImageResources())
                        Toast.makeText(this, "picture captured: " + bitImage, Toast.LENGTH_LONG).show();
                        break;
                    case Activity.RESULT_CANCELED:
                        break;
                    default:
                        break;
                }
                break;
            case 1:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        takeImageButton.setVisibility(View.GONE);
                        takePictureTextId.setVisibility(View.GONE);
                        Bitmap bitImage = (Bitmap) data.getExtras().get("data");
                        pictureTaken.setImageBitmap(bitImage);        //set bitmap code to imageview (just like setImageResources())
                        Toast.makeText(this, "picture captured: " + bitImage, Toast.LENGTH_LONG).show();
                        break;
                    case Activity.RESULT_CANCELED:
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
    }

//    public String getImagePath(Context inContext, Bitmap inImage) {
//        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
//        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
//        return path;
//    }
//
//    public String getRealPathFromURI(Uri contentUri) {
//        Cursor cursor = null;
//        try {
//            String[] proj = { MediaStore.Images.Media.DATA };
//            cursor = this.getContentResolver().query(contentUri,  proj, null, null, null);
//            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//            cursor.moveToFirst();
//            return cursor.getString(column_index);
//        } finally {
//            if (cursor != null) {
//                cursor.close();
//            }
//        }
//    }
}