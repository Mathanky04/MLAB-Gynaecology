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

public class form3 extends AppCompatActivity {

    String clicked="";
    private static TextView btnNext3;
    private static TextView btnPrev3;
    RadioButton s,i;
    EditText e1,e2,e3,e4;
    Button b1,b2,b3;
    DBHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form3);

        myDb=new DBHelper(this);
        btnNext3=(TextView)findViewById(R.id.next_page3);
        btnPrev3=(TextView)findViewById(R.id.previous_page3);
        s=(RadioButton)findViewById(R.id.spontaneous);
        i=(RadioButton)findViewById(R.id.induced);
        e1=(EditText)findViewById(R.id.ailments);
        e2=(EditText)findViewById(R.id.exposure);
        e3=(EditText)findViewById(R.id.history_1);
        e4=(EditText)findViewById(R.id.history_2);
        b1=(Button)findViewById(R.id.uploadscan_button);
        b2=(Button)findViewById(R.id.uploadscan2_button);
        b3=(Button)findViewById(R.id.uploadscan3_button);
        onBtnNext3();
        onBtnPrev3();
    }

    public void onBtnNext3()
    {
        btnNext3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id1=getIntent().getExtras().getString("regNo");
                String conception="";
                if(s.isChecked())
                    conception="Spontaneous";
                else if(i.isChecked())
                    conception="Induced";
                String isClickedb1=b1onclicklistener();
                String isClickedb2=b2onclicklistener();
                String isClickedb3=b3onclicklistener();
                boolean isInserted1=myDb.insertData3(id1,conception,e1.getText().toString(),e2.getText().toString(),isClickedb1);
                boolean isInserted2=myDb.insertData4(id1,e3.getText().toString(),isClickedb2);
                boolean isInserted3=myDb.insertData5(id1,e4.getText().toString(),isClickedb3);
                if(isInserted1 && isInserted2 && isInserted3)
                    Toast.makeText(form3.this,"Saved", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(form3.this,"Not Saved", Toast.LENGTH_LONG).show();
                Intent intent = new Intent("com.example.mathanky.gynocare2.form4");
                intent.putExtra("regNo",id1);
                startActivity(intent);
            }
        });
    }

    public String b1onclicklistener()
    {
        clicked="false";
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicked="true";
            }
        });
        return clicked;
    }

    public String b2onclicklistener()
    {
       clicked="false";
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicked="true";
            }
        });
        return clicked;
    }

    public String b3onclicklistener()
    {
        clicked="false";
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicked="true";
            }
        });
        return clicked;
    }

    public void onBtnPrev3()
    {
        btnPrev3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.example.mathanky.gynocare2.form2");
                startActivity(intent);
            }
        });
    }
}
