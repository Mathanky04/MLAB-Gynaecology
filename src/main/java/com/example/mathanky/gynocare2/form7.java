package com.example.mathanky.gynocare2;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class form7 extends AppCompatActivity {

    private static TextView btnNext7;
    private static TextView btnPrev7;
    RadioButton n,i;
    EditText e1,e2,e4,e5,e6,e7,e8,e9,e10,e11,e12,e13,e14,e15,e16;
    TextView e3;
    DBHelper myDb;
    int v1,v2;
    double res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form7);
        myDb=new DBHelper(this);
        n=(RadioButton)findViewById(R.id.normal);
        i=(RadioButton)findViewById(R.id.inverted);
        e1=(EditText)findViewById(R.id.height);
        e1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                v1=Integer.valueOf(e1.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        e2=(EditText)findViewById(R.id.weight);
        e3=(TextView)findViewById(R.id.BMI);
        e2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                v2=Integer.valueOf(e2.getText().toString());
                if(v1!=0)
                {
                    res=(v2*10000)/(v1*v1);
                    e3.setText(String.valueOf(res));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        e4=(EditText)findViewById(R.id.PR);
        e5=(EditText)findViewById(R.id.BP);
        e6=(EditText)findViewById(R.id.RR);
        e7=(EditText)findViewById(R.id.temp);
        e8=(EditText)findViewById(R.id.breasts);
        e9=(EditText)findViewById(R.id.thyroid);
        e10=(EditText)findViewById(R.id.spine);
        e11=(EditText)findViewById(R.id.pallor);
        e12=(EditText)findViewById(R.id.edema);
        e13=(EditText)findViewById(R.id.jaundice);
        e14=(EditText)findViewById(R.id.cyanosis);
        e15=(EditText)findViewById(R.id.clubbing);
        e16=(EditText)findViewById(R.id.lymphadenopathy);
        btnNext7=(TextView)findViewById(R.id.next_page7);
        onBtnPrev7();
        onBtnNext7();
    }

    public void onBtnNext7()
    {

        btnNext7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id1=getIntent().getExtras().getString("regNo");
                String nip="";
                if(n.isChecked())
                    nip="Normal";
                else if(i.isChecked())
                    nip="Inverted";
                boolean isInserted=myDb.insertData12(id1,e1.getText().toString(),e2.getText().toString(),e3.getText().toString(),e4.getText().toString(),e5.getText().toString(),e6.getText().toString(),e7.getText().toString(),e8.getText().toString(),nip,e9.getText().toString(),e10.getText().toString(),e11.getText().toString(),e12.getText().toString(),e13.getText().toString(),e14.getText().toString(),e15.getText().toString(),e16.getText().toString());
                if(isInserted)
                {
                    Toast.makeText(form7.this,"Saved", Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(form7.this,"Not Saved",Toast.LENGTH_LONG).show();
                Intent intent = new Intent("com.example.mathanky.gynocare2.form8");
                intent.putExtra("regNo",id1);
                startActivity(intent);
            }
        });
    }

    public void onBtnPrev7()
    {
        btnPrev7=(TextView)findViewById(R.id.previous_page7);
        btnPrev7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.example.mathanky.gynocare2.form6");
                startActivity(intent);
            }
        });
    }
}
