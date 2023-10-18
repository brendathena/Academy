package com.example.protegomaxima;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InsertText extends AppCompatActivity {

    EditText editText;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_text);
            editText = findViewById(R.id.editText);
            button = findViewById(R.id.button);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String spell = editText.getText().toString();

                    Intent intent = new Intent(InsertText.this, DisplayResult.class);
                    intent.putExtra("spell", spell);
                    startActivity(intent);
                }
            });
    }
}