package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.Toast;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bumptech.glide.Glide;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class formulaire extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;

   /* EditText nom, prix, quantite, description;
    ImageView imageURI;
    Button confirmer, importImageButton;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulaire);

        db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "app-database")
                .build();

        nom = findViewById(R.id.nom);
        prix = findViewById(R.id.prix);
        quantite = findViewById(R.id.quantite);
        description = findViewById(R.id.designation);
        imageURI = findViewById(R.id.imageView);
        confirmer = findViewById(R.id.confirm);
        importImageButton = findViewById(R.id.importImageButton);

        confirmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a Remboursement object with data from EditText fields
                remboursement remboursement = new remboursement();
                remboursement.nom = nom.getText().toString();
                remboursement.prix = Integer.parseInt(prix.getText().toString());
                remboursement.quantite = Integer.parseInt(quantite.getText().toString());
                remboursement.description = description.getText().toString();
                remboursement.etat = "en attente";

                // Insert remboursement into database
                new InsertRemboursementTask().execute(remboursement);
            }
        });

        importImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an intent to open the image gallery
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            // Load the selected image into the ImageView
            Glide.with(this).load(imageUri).into(imageURI);
        }
    }

    private class InsertRemboursementTask extends AsyncTask<remboursement, Void, Void> {
        @Override
        protected Void doInBackground(remboursement... remboursements) {
            db.remboursementDAO().insertRemboursement(remboursements[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(formulaire.this, "Remboursement added successfully!", Toast.LENGTH_SHORT).show();
        }
    }*/
 /* importImageButton;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulaire);

        importImageButton = findViewById(R.id.importImageButton);
        imageView = findViewById(R.id.imageView);

        importImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            try {
                // Récupérer l'image à partir de l'URI
                InputStream inputStream = getContentResolver().openInputStream(imageUri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                // Afficher l'image dans l'ImageView
                imageView.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }*/

    EditText nom, prix, quantite, description;
    ImageView imageURI;
    Button confirmer, importImageButton;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulaire);

        db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "app-database")
                .build();

        nom = findViewById(R.id.nom);
        prix = findViewById(R.id.prix);
        quantite = findViewById(R.id.quantite);
        description = findViewById(R.id.designation);
        imageURI = findViewById(R.id.imageView);
        confirmer = findViewById(R.id.confirm);
        importImageButton = findViewById(R.id.importImageButton);

        confirmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a Remboursement object with data from EditText fields
                remboursement remboursement = new remboursement();
                remboursement.nom = nom.getText().toString();
                remboursement.prix = Integer.parseInt(prix.getText().toString());
                remboursement.quantite = Integer.parseInt(quantite.getText().toString());
                remboursement.description = description.getText().toString();
                remboursement.etat = "en attente";

                // Insert remboursement into database
                new InsertRemboursementTask().execute(remboursement);
            }
        });

        importImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();

            // Load the selected image into the ImageView using Glide
            Glide.with(this).load(imageUri).into(imageURI);

            // You can also handle the bitmap directly if needed
            // Bitmap bitmap = getBitmapFromUri(imageUri);
            // imageView.setImageBitmap(bitmap);
        }
    }

    // If you need to get a Bitmap from the selected URI
    private Bitmap getBitmapFromUri(Uri uri) {
        try {
            InputStream inputStream = getContentResolver().openInputStream(uri);
            return BitmapFactory.decodeStream(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private class InsertRemboursementTask extends AsyncTask<remboursement, Void, Void> {
        @Override
        protected Void doInBackground(remboursement... remboursements) {
            db.remboursementDAO().insertRemboursement(remboursements[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(formulaire.this, "Remboursement added successfully!", Toast.LENGTH_SHORT).show();
        }
    }
}
