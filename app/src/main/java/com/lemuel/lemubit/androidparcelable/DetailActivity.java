/*You may notice some similarities between Parcelable and Serializable.
DO NOT attempt to persist Parcel data.
It is meant for high-performance transport and you could lose data by trying to persist it.*/

package com.lemuel.lemubit.androidparcelable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        textView = findViewById(R.id.textView);
        Model model = getIntent().getParcelableExtra("data");
        textView.setText(model.mUserName + " is " + model.mAge + "years old");
    }
}
