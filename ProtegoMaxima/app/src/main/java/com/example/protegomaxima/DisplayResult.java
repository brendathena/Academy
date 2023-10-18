package com.example.protegomaxima;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;

public class DisplayResult extends AppCompatActivity {

    TextView textView;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_result);

        textView = findViewById(R.id.textView);
        imageView = findViewById(R.id.imageView);

        Intent intent = getIntent();
        String spell = intent.getStringExtra("spell");
            if (spell.equals("Legilimens")) {
                textView.setText("Curiosity is not a sin, but we should exercise caution with our curiosity.");
                imageView.setVisibility(View.VISIBLE);
                Picasso.get()
                        .load("https://www.reddit.com/media?url=https%3A%2F%2Fi.redd.it%2Fcxyqab02bq281.png")
                        .into(imageView);
            } else if (spell != "Legilimens"){
                textView.setText(spell);
                Toast.makeText(this, "Lol, no. Just wrong.", Toast.LENGTH_LONG).show();
            }
    }
}
