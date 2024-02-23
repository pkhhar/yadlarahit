package com.example.test3.ui.addItem;
import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.example.test3.R;
import com.example.test3.databinding.ActivityAddItemBinding;

import java.io.File;
import java.io.IOException;

public class addItemActivity extends AppCompatActivity {


    private  static  final  int CAMERA_PERMISSION_CODE =1;
    ActivityAddItemBinding addItemBinding;
    ActivityResultLauncher<Uri> takePictureLauncher;
    Uri imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addItemBinding = ActivityAddItemBinding.inflate(getLayoutInflater());
        setContentView(addItemBinding.getRoot());
        imageUri = createUri();
        registerPictureLuncher();

        addItemBinding.btnTakePicture.setOnClickListener(view -> {
            checkCameraPermissionAndOpenCamera();
        });
    }
    private Uri createUri() {
        File imageFile = new File(getApplicationContext().getFilesDir(), "my_photo.jpg");
        return FileProvider.getUriForFile(
                getApplicationContext(),
                "com.example.test3.fileProvider",
                imageFile
        );

    }
    private  void registerPictureLuncher() {
        takePictureLauncher= registerForActivityResult(
                new ActivityResultContracts.TakePicture(),
                new ActivityResultCallback<Boolean>() {
                    @Override
                    public void onActivityResult(Boolean result) {
                        try {
                            if(result)
                            {
                                addItemBinding.uploadPicture.setImageURI(null);
                                addItemBinding.uploadPicture.setImageURI(imageUri);
                            }
                        }catch (Exception exception)
                        {
                                exception.getStackTrace();

                        }
                    }
                }
        );
    }

    private void checkCameraPermissionAndOpenCamera()
    {
        if(ActivityCompat.checkSelfPermission(addItemActivity.this,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(addItemActivity.this,
                    new String[]{Manifest.permission.CAMERA},CAMERA_PERMISSION_CODE);

        }else {

        takePictureLauncher.launch(imageUri);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == CAMERA_PERMISSION_CODE)
        {
            if(grantResults.length> 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                takePictureLauncher.launch(imageUri);


            }else {
                Toast.makeText(this, "camera permission denied , please allow permission  to take a picture.", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
