package com.example.mathanky.gynocare2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class form5 extends AppCompatActivity {

    private static TextView btnNext5;
    private static TextView btnPrev5;
    EditText e1,e2,e3,e4,e5,e01,e02,e03,e11,e12,e13,e21,e22,e23;
    TextView t00,t10,t20;
    DBHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form5);
        myDb=new DBHelper(this);
        btnNext5=(TextView)findViewById(R.id.next_page5);
        e1=(EditText)findViewById(R.id.G_box);
        e2=(EditText)findViewById(R.id.P_box);
        e3=(EditText)findViewById(R.id.A_box);
        e4=(EditText)findViewById(R.id.L_box);
        e5=(EditText)findViewById(R.id.D_box);
        t00=(TextView)findViewById(R.id.sl1);
        e01=(EditText)findViewById(R.id.mode1);
        e02=(EditText)findViewById(R.id.comp1);
        e03=(EditText)findViewById(R.id.outcome1);
        t10=(TextView)findViewById(R.id.sl2);
        e11=(EditText)findViewById(R.id.mode2);
        e12=(EditText)findViewById(R.id.comp2);
        e13=(EditText)findViewById(R.id.outcome2);
        t20=(TextView)findViewById(R.id.sl3);
        e21=(EditText)findViewById(R.id.mode3);
        e22=(EditText)findViewById(R.id.comp3);
        e23=(EditText)findViewById(R.id.outcome3);
        onBtnNext5();
        onBtnPrev5();
    }
    public void onBtnNext5()
    {
        btnNext5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id1=getIntent().getExtras().getString("regNo");
                boolean isInserted1=myDb.insertData7(id1,e1.getText().toString(),e2.getText().toString(),e3.getText().toString(),e4.getText().toString(),e5.getText().toString());
                boolean isInserted2=myDb.insertData8(id1,t00.getText().toString(),e01.getText().toString(),e02.getText().toString(),e03.getText().toString());
                boolean isInserted3=myDb.insertData8(id1,t10.getText().toString(),e11.getText().toString(),e12.getText().toString(),e13.getText().toString());
                boolean isInserted4=myDb.insertData8(id1,t20.getText().toString(),e21.getText().toString(),e22.getText().toString(),e23.getText().toString());
                if(isInserted1&&isInserted2&&isInserted3&&isInserted4)
                    Toast.makeText(form5.this,"Saved", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(form5.this,"Not Saved", Toast.LENGTH_LONG).show();
                Intent intent = new Intent("com.example.mathanky.gynocare2.form6");
                intent.putExtra("regNo",id1);
                startActivity(intent);
            }
        });
    }

    public void onBtnPrev5()
    {
        btnPrev5=(TextView)findViewById(R.id.previous_page5);
        btnPrev5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.example.mathanky.gynocare2.form4");
                startActivity(intent);
            }
        });
    }
}
