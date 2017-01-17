package com.brandtechnosolutions.petbaazar;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class BuyerSellerActivity extends AppCompatActivity implements OpenCameraFromDialog {
    private final String TAG = "mycam";
    private File imageFile;
    private SellerFragment sellerFragment;
    private BuyerFragment buyerFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_seller);
// attach sellerfragment
        sellerFragment = new SellerFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.RLSeller, sellerFragment, "sellerFragment");
// attach buyerfragment
        buyerFragment = new BuyerFragment();
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.RLBuyer, buyerFragment, "buyerFragment");
        transaction.commit();
    }

    void onBuy(View view) {
//        Toast.makeText(this, "clicked", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, WebActivity.class);
        startActivity(intent);
    }

    void onSell(View view) {
        FragmentManager manager = getSupportFragmentManager();

        SellerOptionFragment dialogFragment = new SellerOptionFragment();
        dialogFragment.show(manager, "Alert");
    }

    //after capturing action finishes, this method will be called
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {      //resultCode = OK or Cancel
        Log.e(TAG, "onActivityResult start");
        if (requestCode == 0) {
            Log.e(TAG, "requestCode == 0");
            switch (resultCode) {
                case Activity.RESULT_OK:
                    Log.e(TAG, "Activity.RESULT_OK");
                    if (imageFile.exists()) {       //boolean
                        Log.e(TAG, "imageFile.exists()");
                        String imagePath = imageFile.getAbsolutePath();      //file path access
                        Log.e(TAG, "imagePath taken");
                        Bitmap bitImage = BitmapFactory.decodeFile(imagePath);       //convert path to bitmap code
                        Log.e(TAG, "Bitmap gen");
                        attachShowPictureFragment(bitImage);
//                        image.setImageBitmap(bitImage);        //set bitmap code to imageview (just like setImageResources())
                        Toast.makeText(this, "The Image is stored in the directory: " +
                                imagePath, Toast.LENGTH_LONG).show();
                    } else {
                        Log.e(TAG, "else");
                        Toast.makeText(this, "Oops...There is a Problem When Taking Picture!"
                                , Toast.LENGTH_LONG).show();
                    }

                case Activity.RESULT_CANCELED:
                    break;
                default:
                    break;
            }
        }
    }

    void attachShowPictureFragment(Bitmap bitImage) {
        ShowPictureFragment showPictureFragment = new ShowPictureFragment();
        showPictureFragment.showPicture(bitImage);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.remove(getSupportFragmentManager().findFragmentByTag("sellerFragment"));
        transaction.remove(getSupportFragmentManager().findFragmentByTag("buyerFragment"));
        transaction.add(R.id.activity_buyer_seller, showPictureFragment, "showPictureFragment");
//        transaction.commit();
//        transaction.add(R.id.RLSeller, sellerFragment, "sellerFragment");
    }
    public void openCamera() throws IOException {
        //create File(destination directory...parent, file name with type...child)

        imageFile = new File(Environment.getExternalStorageDirectory(), "MyCache");
//        imageFile = new File(
//                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)          //Environment gives access to phone and getESD(_) gives access to external memory and DIRECTORY_DCIM gives DCIM directory path
//                , "test.jpg");
        Uri uri = Uri.fromFile(imageFile);                            //to convert from file to Uri
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);  //MediaStore gives access to media(like camera) and ACTION_IMAGE_CAPTURE is the action we want to do by using intent
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);           //EXTRA_VIDEO_QUALITY = 1 means HD quality, =0 means Low Quality
        Log.e(TAG, "before startActivityForResult");
        startActivityForResult(intent, 0);                            // 0 is the requestCode for identification
        Log.e(TAG, "after startActivityForResult");
    }
}
