package com.example.mathanky.gynocare2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class form8 extends AppCompatActivity {

    private static TextView btnNext8;
    private static TextView btnPrev8;
    DBHelper myDb;
    EditText e1,e2,e3,e4,e5,e6,e7,e8,e9,e10,e11,e12;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form8);
        myDb=new DBHelper(this);
        e1=(EditText)findViewById(R.id.CVS);
        e2=(EditText)findViewById(R.id.RS);
        e3=(EditText)findViewById(R.id.PA);
        e4=(EditText)findViewById(R.id.uterus);
        e5=(EditText)findViewById(R.id.SFH);
        e6=(EditText)findViewById(R.id.lie);
        e7=(EditText)findViewById(R.id.AG);
        e8=(EditText)findViewById(R.id.presentation);
        e9=(EditText)findViewById(R.id.EFW);
        e10=(EditText)findViewById(R.id.liquor);
        e11=(EditText)findViewById(R.id.FHS);
        e12=(EditText)findViewById(R.id.previous_scar);
        btnNext8=(TextView)findViewById(R.id.next_page8);
        onBtnNext8();
        onBtnPrev8();
    }
    public void onBtnNext8()
    {

        btnNext8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id1=getIntent().getExtras().getString("regNo");
                boolean isInserted=myDb.insertData13(id1,e1.getText().toString(),e2.getText().toString(),e3.getText().toString(),e4.getText().toString(),e5.getText().toString(),e6.getText().toString(),e7.getText().toString(),e8.getText().toString(),e9.getText().toString(),e10.getText().toString(),e11.getText().toString(),e12.getText().toString());
                if(isInserted)
                {
                    Toast.makeText(form8.this,"Saved", Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(form8.this,"Not Saved",Toast.LENGTH_LONG).show();
                Intent intent = new Intent("com.example.mathanky.gynocare2.form9");
                intent.putExtra("regNo",id1);
                startActivity(intent);
            }
        });
    }

    public void onBtnPrev8()
    {
        btnPrev8=(TextView)findViewById(R.id.previous_page8);
        btnPrev8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.example.mathanky.gynocare2.form7");
                startActivity(intent);
            }
        });
    }
}
