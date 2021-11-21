package com.example.proyectofinal;

import static com.example.proyectofinal.R.layout.activity_create_post;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class createPost extends AppCompatActivity {

    EditText postTitle;
    EditText postDescription;
    Button addPics,uploadPost;
    DBHelper DB;
    ImageView previewImage;


    private static int RESULT_LOAD_IMAGE = 1;
    int SELECT_PICTURE = 200;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_create_post);

        postTitle = (EditText) findViewById(R.id.postTitle);
        postDescription = (EditText) findViewById(R.id.postDescripcion);
        addPics = (Button) findViewById(R.id.addPics);
        uploadPost = (Button) findViewById(R.id.uploadPost);
        DB = new DBHelper(this);


        addPics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageChooser();
            }
        });

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    previewImage.setImageURI(selectedImageUri);
                }
            }
        }
    }
    void imageChooser() {

        // create an instance of the
        // intent of the type image
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        // pass the constant to compare it
        // with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }

}