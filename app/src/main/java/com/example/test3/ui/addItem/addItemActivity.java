package com.example.test3.ui.addItem;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import com.example.test3.DB.FireBaseDataBase;
import com.example.test3.R;
import com.example.test3.databinding.ActivityAddItemBinding;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

// Define the main activity class for adding items
public class addItemActivity extends AppCompatActivity implements View.OnClickListener {

    // Declare UI elements and other variables
    private EditText name, price, length, width, height, color;
    private Spinner spinnerType;
    private ImageView imgView;
    private Button btnAddFurniture;
    private static final int CAMERA_PERMISSION_CODE = 1;
    ActivityAddItemBinding addItemBinding;
    ActivityResultLauncher<Intent> CameraResultLauncher;
    Uri imageUri;
    Bitmap photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Bind the layout to this activity
        addItemBinding = ActivityAddItemBinding.inflate(getLayoutInflater());
        setContentView(addItemBinding.getRoot());

        // Create a URI for storing the image
        imageUri = createUri();

        // Register the camera launcher
        registerPictureLuncher();

        // Initialize the UI elements by finding them by their IDs
        name = findViewById(R.id.namePutProduct);
        price = findViewById(R.id.pricePutProduct);
        length = findViewById(R.id.lengthPutProduct);
        width = findViewById(R.id.widthPutProduct);
        height = findViewById(R.id.heightPutProduct);
        color = findViewById(R.id.colorPutProduct);
        spinnerType = findViewById(R.id.spinnerPutProduct);
        btnAddFurniture = findViewById(R.id.addFurnutureButton);
        imgView = findViewById(R.id.uploadPicture);
        imgView.setTag("NoPic"); // Default tag indicating no picture is set

        // Set the click listener for the add button
        btnAddFurniture.setOnClickListener(this);

        // Create and set the adapter for the spinner with item categories
        List<String> lst = new LinkedList<>();
        lst.add("בחר קטגוריה");
        lst.add("חדר שינה");
        lst.add("שירותים");
        lst.add("מטבח");
        lst.add("סלון");
        lst.add("חצר");

        // Create an ArrayAdapter for the spinner (dropdown menu)
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, lst);

// Set the layout resource to use for the dropdown items
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// Attach the adapter to the spinner
        spinnerType.setAdapter(adapter);
        // Set the click listener for the take picture button
        addItemBinding.btnTakePicture.setOnClickListener(view -> {
            checkCameraPermissionAndOpenCamera();
        });

        // Initialize the Firebase database object

    }

    // Method to create a URI for storing the image
    private Uri createUri() {
        File imageFile = new File(getApplicationContext().getFilesDir(), "my_photo.jpg");
        return FileProvider.getUriForFile(
                getApplicationContext(),
                "com.example.test3.fileProvider",
                imageFile
        );
    }

    // Method to register the camera launcher for capturing images
    private void registerPictureLuncher() {
        CameraResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            // Get the captured image and display it in the ImageView
                            photo = (Bitmap) result.getData().getExtras().get("data");
                            imgView.setImageBitmap(photo);
                            imgView.setTag("Pic"); // Tag indicating a picture is set
                        }
                    }
                }
        );
    }

    // Method to check camera permission and open the camera if granted
    private void checkCameraPermissionAndOpenCamera() {
        if (ActivityCompat.checkSelfPermission(addItemActivity.this,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // Request camera permission if not already granted
            ActivityCompat.requestPermissions(addItemActivity.this,
                    new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);
        } else {
            // Launch the camera if permission is already granted
            CameraResultLauncher.launch(new Intent(MediaStore.ACTION_IMAGE_CAPTURE));
        }
    }

    // Handle the result of the permission request
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, launch the camera
                CameraResultLauncher.launch(new Intent(MediaStore.ACTION_IMAGE_CAPTURE));
            } else {
                // Permission denied, show a toast message
                Toast.makeText(this, "camera permission denied, please allow permission to take a picture.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Handle the click events for the buttons
    @Override
    public void onClick(View v) {
        if (btnAddFurniture == v) {
            // Validate the input fields before proceeding
            if (name.getText().toString().trim().equals("")) {
                Toast.makeText(this, "you need to write a name", Toast.LENGTH_SHORT).show();
                return;
            }
            if (price.getText().toString().trim().equals("")) {
                Toast.makeText(this, "you need to write a price", Toast.LENGTH_SHORT).show();
                return;
            }
            if (length.getText().toString().trim().equals("")) {
                Toast.makeText(this, "you need to write a length", Toast.LENGTH_SHORT).show();
                return;
            }
            if (width.getText().toString().trim().equals("")) {
                Toast.makeText(this, "you need to write a width", Toast.LENGTH_SHORT).show();
                return;
            }
            if (height.getText().toString().trim().equals("")) {
                Toast.makeText(this, "you need to write a height", Toast.LENGTH_SHORT).show();
                return;
            }
            if (color.getText().toString().trim().equals("")) {
                Toast.makeText(this, "you need to write a color", Toast.LENGTH_SHORT).show();
                return;
            }
            if (spinnerType.getSelectedItem().toString().trim().equals("בחר קטגוריה")) {
                Toast.makeText(this, "you need to choose a type", Toast.LENGTH_SHORT).show();
                return;
            }
            if (imgView.getTag().equals("NoPic")) {
                Toast.makeText(this, "Add pic", Toast.LENGTH_SHORT).show();
                return;
            }

            // Retrieve the current user's email from shared preferences
            SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
            String currentEmail = sharedPreferences.getString("email", "");
            String currentPhone = sharedPreferences.getString("phone","");
            // Add the furniture item to the Firebase database
            FireBaseDataBase fireBaseDataBase = new FireBaseDataBase();
            fireBaseDataBase.AddFurniture(currentPhone,currentEmail, name.getText().toString().trim(), price.getText().toString().trim(), length.getText().toString().trim(), width.getText().toString().trim(), height.getText().toString().trim(), color.getText().toString().trim(), spinnerType.getSelectedItem().toString().trim(), photo, this);
        }
    }
}
