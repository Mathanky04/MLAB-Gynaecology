package com.example.mathanky.gynocare2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class form6 extends AppCompatActivity {

    private static TextView btnNext6;
    private static TextView btnPrev6;
    RadioButton r1,r2;
    EditText e1,e2,e3,e4,e5,e6,e7,e8,e9,e10;
    DBHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form6);
        myDb=new DBHelper(this);
        r1=(RadioButton)findViewById(R.id.regular);
        r2=(RadioButton)findViewById(R.id.irregular);
        e1=(EditText)findViewById(R.id.LMP);
        e2=(EditText)findViewById(R.id.EDD);
        e3=(EditText)findViewById(R.id.contraceptive_history);
        e4=(EditText)findViewById(R.id.past_history);
        e5=(EditText)findViewById(R.id.family_history);
        e6=(EditText)findViewById(R.id.diet);
        e7=(EditText)findViewById(R.id.sleep);
        e8=(EditText)findViewById(R.id.addictive_habits);
        e9=(EditText)findViewById(R.id.allergies_known);
        e10=(EditText)findViewById(R.id.allergies_unknown);
        btnNext6=(TextView)findViewById(R.id.next_page6);
        onBtnNext6();
        onBtnPrev6();
    }
    public void onBtnNext6()
    {

        btnNext6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String reg="";
                if(r1.isChecked())
                    reg="Regular";
                else if(r2.isChecked())
                    reg="Irregular";
                String id1=getIntent().getExtras().getString("regNo");
                boolean isInserted1=myDb.insertData9(id1,reg,e1.getText().toString(),e2.getText().toString());
                boolean isInserted2=myDb.insertData10(id1,e3.getText().toString(),e4.getText().toString(),e5.getText().toString());
                boolean isInserted3=myDb.insertData11(id1,e6.getText().toString(),e7.getText().toString(),e8.getText().toString(),e9.getText().toString(),e10.getText().toString());
                if(isInserted1 && isInserted2 && isInserted3)
                {
                    Toast.makeText(form6.this,"Saved", Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(form6.this,"Not Saved",Toast.LENGTH_LONG).show();
                Intent intent = new Intent("com.example.mathanky.gynocare2.form7");
                intent.putExtra("regNo",id1);
                startActivity(intent);
            }
        });
    }

    public void onBtnPrev6()
    {
        btnPrev6=(TextView)findViewById(R.id.previous_page6);
        btnPrev6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.example.mathanky.gynocare2.form5");
                startActivity(intent);
            }
        });
    }
}
