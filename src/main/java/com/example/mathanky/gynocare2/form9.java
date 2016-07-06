package com.example.mathanky.gynocare2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class form9 extends AppCompatActivity {

    private static TextView btnPrev9;
    Button sub;
    EditText e1,e2,e3,e4;
    RadioButton v1,v2,h1,h2,hb1,hb2;
    DBHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form9);
        myDb=new DBHelper(this);
        v1=(RadioButton)findViewById(R.id.reactive);
        v2=(RadioButton)findViewById(R.id.non_reactive);
        h1=(RadioButton)findViewById(R.id.HIV_yes);
        h2=(RadioButton)findViewById(R.id.HIV_no);
        hb1=(RadioButton)findViewById(R.id.positive);
        hb2=(RadioButton)findViewById(R.id.negative);
        e1=(EditText)findViewById(R.id.hb_box);
        e2=(EditText)findViewById(R.id.blood_group);
        e3=(EditText)findViewById(R.id.urine_routine);
        e4=(EditText)findViewById(R.id.usg_box);
        sub=(Button)findViewById(R.id.submit1);
        onSubmit();
        onBtnPrev9();
    }

    public void onBtnPrev9()
    {
        btnPrev9=(TextView)findViewById(R.id.previous_page9);
        btnPrev9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.example.mathanky.gynocare2.form8");
                startActivity(intent);
            }
        });
    }

    public void onSubmit()
    {

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id1=getIntent().getExtras().getString("regNo");
                String v="";
                String hiv="";
                String hbs="";
                if(v1.isChecked())
                    v="Reactive";
                else if(v2.isChecked())
                    v="Non Reactive";
                if(h1.isChecked())
                    hiv="Yes";
                else if(h2.isChecked())
                    hiv="No";
                if(hb1.isChecked())
                    hbs="Positive";
                else if(hb2.isChecked())
                    hbs="Negative";
                boolean isInserted=myDb.insertData14(id1,e1.getText().toString(),e2.getText().toString(),e3.getText().toString(),v,hiv,hbs,e4.getText().toString());
                if(isInserted)
                {
                    Toast.makeText(form9.this,"Saved", Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(form9.this,"Not Saved",Toast.LENGTH_LONG).show();
                Intent intent = new Intent("com.example.mathanky.gynocare2.ThankYou");
                startActivity(intent);
            }
        });
    }
}
