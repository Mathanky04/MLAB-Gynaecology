package com.example.mathanky.gynocare2;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    DBHelper myDb;
    private static Button btnCreate;
    private static Button btnUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb=new DBHelper(this);
        onCreate1();
    }

    public void onCreate1()
    {
        btnCreate=(Button)findViewById(R.id.button);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.example.mathanky.gynocare2.form1");
                startActivity(intent);
            }
        });
    }
}
