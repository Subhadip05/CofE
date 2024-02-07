package com.example.cofe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class OrderViewActivity extends AppCompatActivity {

    TextView orderView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_view);

        orderView = findViewById(R.id.orderSummary);

        //getting the message from MainActivity to OrderViewActivity
        String oMsg = getIntent().getStringExtra("msg");

        orderView.setText(String.valueOf(oMsg));

    }
}