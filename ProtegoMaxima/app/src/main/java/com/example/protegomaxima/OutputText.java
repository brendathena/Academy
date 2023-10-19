package com.example.protegomaxima;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class OutputText extends AppCompatActivity {

    TextView textView;
    TextView reaction;

    ImageView imageView;

    private void gif(String url){
        Glide.with(this)
                .asGif()
                .load(url)
                .into(imageView);
        imageView.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output_text);
        textView = findViewById(R.id.textView);
        imageView = findViewById(R.id.imageView);
        reaction = findViewById(R.id.reaction);

        //TODO: Style the app.
        //TODO: Create a try again button and the exception if the value passed is null.

        Intent intent = getIntent();
        String spell = intent.getStringExtra("spell");

        if (TextUtils.isEmpty(spell) || spell.trim().isEmpty()) {
            Toast.makeText(this, "Don't go breaking my code. You couldn't if you've tried.", Toast.LENGTH_SHORT).show();
        } else if (spell.equalsIgnoreCase("Protego Máxima") || spell.equalsIgnoreCase("Protego Maxima")) {
                textView.setText(spell);
                reaction.setText("CHARGE!!!");
                gif("https://dhjhkxawhe8q4.cloudfront.net/catholic-university-america-press-wp/wp-content/uploads/2021/11/08212921/that-spell.gif");
            } else if (spell.equalsIgnoreCase("Protego")) {
                textView.setText(spell);
                reaction.setText("You're almost there but not quite yet");
                gif("https://media.tenor.com/OjyMYoJZ0LEAAAAC/minerva-mcgonagall.gif");
            } else {
                textView.setText(spell);
                reaction.setText("i will not have you behaving like a babbling bumbling band of baboons");
                gif("https://media.tenor.com/LsJy3RzIXuIAAAAM/harry-potter-maggie-smith.gif");
            }
    }
}