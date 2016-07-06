package com.example.mathanky.gynocare2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ThankYou extends AppCompatActivity {

    DBHelper myDB;
    Button btnHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thankyou);
        myDB=new DBHelper(this);
        btnHome=(Button)findViewById(R.id.button2);
        onBtnHome();
    }

    public void onBtnHome()
    {
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent("com.example.mathanky.gynocare2.MainActivity");
                startActivity(intent);
            }
        });
    }
}
