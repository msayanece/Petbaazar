package com.brandtechnosolutions.petbaazar;


import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.File;


/**
 * A simple {@link Fragment} subclass.
 */
public class SellerOptionFragment extends DialogFragment {


    public SellerOptionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_seller_option, container, false);
    }

    @Nullable
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Button camera = null, gallery = null;
        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_seller_option, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()).setView(view);
        final Dialog dialog = builder.create();
        camera = (Button) view.findViewById(R.id.picture_id);
        gallery = (Button) view.findViewById(R.id.gallery_id);

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                OpenCameraFromDialog openCameraFromDialog = (OpenCameraFromDialog) getActivity();
//                try {
//                    openCameraFromDialog.openCamera();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                dialog.dismiss();
                //create File(destination directory...parent, file name with type...child)
                File imageFile = new File(
                        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)           //Environment gives access to phone and getESD(_) gives access to external memory and DIRECTORY_DCIM gives DCIM directory path
                        , "temp.jpg");
                Uri uri = Uri.fromFile(imageFile);                            //to convert from file to Uri
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);  //MediaStore gives access to media(like camera) and ACTION_IMAGE_CAPTURE is the action we want to do by using intent
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);           //EXTRA_VIDEO_QUALITY = 1 means HD quality, =0 means Low Quality
                getActivity().startActivityForResult(intent, 0);              // 0 is the requestCode for identification
            }
        });
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
            }
        });
        return dialog;
    }
}
