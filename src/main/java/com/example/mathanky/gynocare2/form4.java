package com.example.mathanky.gynocare2;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class form4 extends AppCompatActivity {

    private static TextView btnNext4;
    private static TextView btnPrev4;
    RadioButton fyes, fno,iyes,ino,dyes,dno;
    CheckBox dose1,dose2;
    DBHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form4);
        myDb=new DBHelper(this);
        fyes=(RadioButton)findViewById(R.id.folic_yes);
        fno=(RadioButton)findViewById(R.id.folic_no);
        iyes=(RadioButton)findViewById(R.id.iron_yes);
        ino=(RadioButton)findViewById(R.id.iron_no);
        dose1=(CheckBox)findViewById(R.id.tt_first);
        dose2=(CheckBox)findViewById(R.id.tt_second);
        dyes=(RadioButton)findViewById(R.id.drug_yes);
        dno=(RadioButton)findViewById(R.id.drug_no);
        btnNext4=(TextView)findViewById(R.id.next_page4);
        onBtnNext4();
        onBtnPrev4();

    }

    public void onBtnNext4()
    {
        btnNext4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id1=getIntent().getExtras().getString("regNo");

                String folic="";
                String iron="";
                String drug="";
                String check1="";
                String check2="";
                if(fyes.isChecked())
                    folic="Yes";
                else if(fno.isChecked())
                    folic="no";
                if(iyes.isChecked())
                    iron="Yes";
                else if(ino.isChecked())
                    iron="no";
                if(dyes.isChecked())
                    drug="Yes";
                else if(dno.isChecked())
                    drug="no";
                if(dose1.isChecked())
                    check1="Yes";
                else
                    check1="no";
                if(dose2.isChecked())
                    check2="Yes";
                else
                    check2="no";

                boolean isInserted=myDb.insertData6(id1,folic,iron,check1,check2,drug);
                if(isInserted)
                {
                    Toast.makeText(form4.this,"Saved", Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(form4.this,"Not Saved",Toast.LENGTH_LONG).show();
                Intent intent = new Intent("com.example.mathanky.gynocare2.form5");
                intent.putExtra("regNo",id1);
                startActivity(intent);
            }
        });
    }

    public void onBtnPrev4()
    {
        btnPrev4=(TextView)findViewById(R.id.previous_page4);
        btnPrev4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.example.mathanky.gynocare2.form3");
                startActivity(intent);
            }
        });
    }
}
