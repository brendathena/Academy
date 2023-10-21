package com.example.c12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class Quiz extends AppCompatActivity {

    TextView answer;
    TextView reaction;

    Button button;

    ImageView imageView;

    private void gif(String url){
        Glide
                .with(this)
                .asGif()
                .load(url)
                .into(imageView);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        answer = findViewById(R.id.answer);
        reaction = findViewById(R.id.reaction);
        button = findViewById(R.id.button);
        imageView = findViewById(R.id.imageView);

        Intent intent = getIntent();
        String school = intent.getStringExtra("school");

        if (TextUtils.isEmpty(school) || school.trim().isEmpty()){
            Toast.makeText(this, "Don't go breaking my code. You couldn't if you've tried.", Toast.LENGTH_LONG).show();
        } else if (school.equalsIgnoreCase("Slytherin")) {
            answer.setText(school);
            reaction.setText("Greatness inspires envy, envy engenders spite, spite spawns lies.");
            button.setText("Home");
            gif("https://media4.giphy.com/media/KzvN3LrGoLtPG/200w.gif?cid=6c09b952xl5fz37vvwp6je5ft93aybby2yccuga5mvu9ebjl&ep=v1_gifs_search&rid=200w.gif&ct=g");
        } else if (school.equalsIgnoreCase("Gryffindor") || school.equalsIgnoreCase("Ravenclaw") || school.equalsIgnoreCase("HufflePuff")) {
            answer.setText(school);
            reaction.setText("No.\nYou don't want me as your enemy.");
            gif("https://media0.giphy.com/media/iHnYEDaV1RSg0/200w.gif?cid=6c09b952cmj0ziocywv4pg0iiavs6e1ik38ylcpry0hwmvck&ep=v1_gifs_search&rid=200w.gif&ct=g");
        } else {
            answer.setText(school);
            reaction.setText("Give it a real try!\nI will not have you behaving like a babbling bumbling band of baboons.");
            gif("https://media.tenor.com/LsJy3RzIXuIAAAAM/harry-potter-maggie-smith.gif");
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Quiz.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}