package com.brandtechnosolutions.petbaazar;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShowPictureFragment extends Fragment {

    Bitmap bitmapImage;
    public ShowPictureFragment() {
        // Required empty public constructor
    }

    void showPicture(Bitmap bitmapImage) {
        this.bitmapImage = bitmapImage;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_picture, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.show_image_id);
        imageView.setImageBitmap(bitmapImage);
        return view;
    }
}
