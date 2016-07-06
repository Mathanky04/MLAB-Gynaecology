package com.example.mathanky.gynocare2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class form2 extends AppCompatActivity {

    private static TextView btnNext2;
    private static TextView btnPrev2;
    EditText e1,e2,e3,e4;
    DBHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form2);
        myDb=new DBHelper(this);
        e1=(EditText)findViewById(R.id.amenorrhea_month_box);
        e2=(EditText)findViewById(R.id.amenorrhea_day_box);
        e3=(EditText)findViewById(R.id.gestation_box);
        e4=(EditText)findViewById(R.id.complaints_oi);
        btnNext2=(TextView)findViewById(R.id.next_page2);
        onBtnNext2();
        onBtnPrev2();
    }

    public void onBtnNext2()
    {

        btnNext2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id1=getIntent().getExtras().getString("regNo");
                boolean isInserted= myDb.insertData2(id1,e1.getText().toString(),e2.getText().toString(),e3.getText().toString(),e4.getText().toString());
                if(isInserted)
                {
                    Toast.makeText(form2.this,"Saved", Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(form2.this,"Not Saved",Toast.LENGTH_LONG).show();
                Intent intent = new Intent("com.example.mathanky.gynocare2.form3");
                intent.putExtra("regNo",id1);
                startActivity(intent);
            }
        });
    }

    public void onBtnPrev2()
    {
        btnPrev2=(TextView)findViewById(R.id.previous_page2);
        btnPrev2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.example.mathanky.gynocare2.form1");
                startActivity(intent);
            }
        });
    }
}
