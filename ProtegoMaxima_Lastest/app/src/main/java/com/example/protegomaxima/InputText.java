package com.example.protegomaxima;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class InputText extends AppCompatActivity {
    EditText editText;
    Button button;
    ImageView fklogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_text);
        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);
        fklogo = findViewById(R.id.fklogo);

        Glide.with(this)
                .load("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAOEAAADhCAMAAAAJbSJIAAAAZlBMVEVKbqj///5BaKWyv9afsM48ZaPS2efAyt4+ZqRHbKdGa6fm6vE6Y6NRdKvQ1+VqhrXu8fV2j7qaq8qvvNXx9PenttFgfrCLnsK9yNzJ0uKFmr9Ncand4uxwirdlgbP5+vsuXZ98lLxz4kyMAAADCUlEQVR4nO3d6W7iMBhGYWLqNg5bKBRoy1Lu/yaH0bSdkQhJ8CTN+1nn/EVIeWRCFjswmo3SLnUfEREREREREREREdGPFEJwzvnPpt7nee5cEYowCkNv2/8XLrLRZrl9WO3mn73uxqvV8/Ztudi8h8urlgu522x3+3V2q9N6P38feiuju/AO48lN3HeHYugtjStMZ6uymWdWWEwP81a8S0uL3zb++NjWZ3IMXRi392XZwpzQL9vtf19trH1K/ctdviyb2RKG/PVOYGZriUFwLQ6AloUh3A/MTJ3TFPv7gZmls+/798FLJ0NC/xABzE7OjLA4xACzdT70hrct5BHfMqaE+b1H+s9KM5fA73FAO0K/S10YO4RmhG4VK5zYEAZ3+25TQ4/ToTe+VcVbLNCK0Mecr1kShlH0hzSb2xAuo4FGhK7d+cxpcl35YuKszTffPDw9bY/B++nv/L+5oTe+TaFovLs2DlMXzFwmXRVmTcCziZ3tdo1Xhs/GgY3H+0lu9/P5J/fcMIQmvk3qarr4NXVHtDJXPxNTmjji1dYgtHHmWVuD8Cn5MdwlLxwnL3wwf7BAiNBACBHqhxChfggR6ocQoX4IEeqXJyUM/rqPeuHLR8V7/iY2pxEWjxXVz+KX86r3fLfQIrqoNaS1ic3b9CDcJi88az0z04PwkPx+KPZkVw/CY/JjKDZD3INQ7JmZHoRiT5R0L1R7oqR74VpsFXT3wlJsHUMPwuTHcJ/8GKqttuleqLYKunvha/L7odpqm+6FK60L4B6Eamv1exAmP4Zit2l6EL5pXeIjjEjsVlsPQrEbUT0I1X6AJxyfKqp/7KmsestX46FFV4W827knsWPFjdKaP6wq/TlghAj1Q4hQP4QI9UOIUD+ECPVDiFA/hAj1Q4hQP4QI9UOIUD+ECPVDiFA/hAj1Q4hQP4QI9Ut/9SVChPohRKgfQoT6IUSoH0KE+iFEqB9ChPohRKgfQoT6IbQvLM61Pyuv9hs7MRW1fw2QAJCIiIiIiIiIiIiI6EcT+5vBzpv9ArTQOB6T9LEBAAAAAElFTkSuQmCC")
                .into(fklogo);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String spell = editText.getText().toString();

                Intent intent = new Intent(InputText.this, OutputText.class);
                intent.putExtra("spell", spell);
                startActivity(intent);
            }
        });

        fklogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InputText.this, OutputSite.class);
                startActivity(intent);
            }
        });
    }
}