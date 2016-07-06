package com.example.mathanky.gynocare2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class form1 extends AppCompatActivity {

    private static TextView btnNext1;
    private static TextView btnPrev1;
    EditText e1,e2,e3,e4,e5,e6,e7,e8;
    DBHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form1);
        myDb=new DBHelper(this);
        e1=(EditText)findViewById(R.id.name_box);
        e2=(EditText)findViewById(R.id.date_box);
        e3=(EditText)findViewById(R.id.reg_box);
        e4=(EditText)findViewById(R.id.age_box);
        e5=(EditText)findViewById(R.id.wife_box);
        e6=(EditText)findViewById(R.id.address_box);
        e7=(EditText)findViewById(R.id.mobile_box);
        e8=(EditText)findViewById(R.id.landline_box);
        btnNext1=(TextView)findViewById(R.id.next_page1);
        onButton();
        onBtnPrev1();
    }
    public void onButton()
    {
        btnNext1=(TextView) findViewById(R.id.next_page1);
        btnNext1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ValidationSuccess()){
                    onBtnNext1();


                }
            }
        });
    }

    private boolean ValidationSuccess(){

        boolean check=true;
        if (e1.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(getApplicationContext(),"please enter name",Toast.LENGTH_SHORT).show();
            e1.setError("Please enter name");
            check=false;
        }

        if (e3.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(getApplicationContext(),"please enter registration number",Toast.LENGTH_SHORT).show();
            e3.setError("Please enter registration number");

            check=false;
        }
        if (e5.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(getApplicationContext(),"please enter name",Toast.LENGTH_SHORT).show();
            e5.setError("Please enter name");

            check=false;
        }

        if (e7.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(getApplicationContext(),"please enter mobile number",Toast.LENGTH_SHORT).show();
            e7.setError("Please enter mobile number");

            check=false;
        }



        return check;


    }
    public void onBtnNext1()
    {

        btnNext1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted=myDb.insertData1(e1.getText().toString(),e2.getText().toString(),e3.getText().toString(),e4.getText().toString(),e5.getText().toString(),e6.getText().toString(),e7.getText().toString(),e8.getText().toString());
                if(isInserted)
                {
                    Toast.makeText(form1.this,"Saved", Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(form1.this,"Not Saved",Toast.LENGTH_LONG).show();
                Intent intent = new Intent("com.example.mathanky.gynocare2.form2");
                intent.putExtra("regNo",e3.getText().toString());
                startActivity(intent);
            }
        });
    }
    public void onBtnPrev1()
    {
        btnPrev1=(TextView)findViewById(R.id.previous_page1);
        btnPrev1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.example.mathanky.gynocare2.MainActivity");
                startActivity(intent);
            }
        });
    }
}
