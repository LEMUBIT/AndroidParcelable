package com.lemuel.lemubit.androidparcelable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(v -> {
            Model model = new Model();
            model.mUserName = "David";
            model.mAge = 34;

            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("data", model);
            startActivity(intent);
        });


    }
}
