package com.batch.roomwordssample;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.batch.roomwordssample.MainActivity.EXTRA_DATA_ID;
import static com.batch.roomwordssample.MainActivity.EXTRA_DATA_UPDATE_WORD;

public class NewWordActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY_IMAGE = "com.example.android.roomwordssample.REPLY_IMAGE";
    public static final String EXTRA_REPLY = "com.example.android.roomwordssample.REPLY";
    public static final String EXTRA_REPLY_id = "com.example.android.roomwordssample.REPLY_ID";
    private EditText mEditWordView;
    private Uri imageUri;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);
        mEditWordView = findViewById(R.id.edit_word);
        int id = -1;
        final Bundle extras = getIntent().getExtras();

        if(extras != null){
            String word = extras.getString(EXTRA_DATA_UPDATE_WORD,"");
            if(!word.isEmpty()){
                mEditWordView.setText(word);
                mEditWordView.setSelection(word.length());
                mEditWordView.requestFocus();
            }
        }

        final CircleImageView picButton = findViewById(R.id.register_photo);
        picButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 0);
            }
        });
        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditWordView.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String word = mEditWordView.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY, word);
                    replyIntent.putExtra(EXTRA_REPLY_IMAGE,imageUri.toString());
                    if(extras != null && extras.containsKey(EXTRA_DATA_ID)){
                        int id = extras.getInt(EXTRA_DATA_ID);
                        if(id != -1){
                            replyIntent.putExtra(EXTRA_REPLY_id, id);
                        }
                    }
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0 && resultCode == Activity.RESULT_OK && data != null){
           try{
               Toast.makeText(this, "MADE IT HERE", Toast.LENGTH_SHORT).show();
               imageUri = data.getData();
               Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
               CircleImageView myView = findViewById(R.id.register_photo);
               myView.setImageBitmap(bitmap);
               //myView.setAlpha(0f);
           }catch(IOException e){
               e.printStackTrace();
           }

        }

    }
}