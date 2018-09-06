package kuppam.gynacology_v3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.*;


import java.text.SimpleDateFormat;
import java.util.Calendar;

public class doctor_login extends AppCompatActivity {
    Button btn;
    EditText e1,e21,e22,e23;
    public static String doc_Name;
    public static String doe;
    Calendar calender;
    SimpleDateFormat simpledateformat;
    String Date;

    doctor_account session;
   // AlertDialogManager alert = new AlertDialogManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_login);

        session = new doctor_account(getApplicationContext());

        e1=(EditText)findViewById(R.id.doctor_name_box);
        e21=(EditText)findViewById(R.id.doctor_date_day_box);
        e22=(EditText)findViewById(R.id.doctor_date_month_box);
        e23=(EditText)findViewById(R.id.doctor_date_year_box);
        btn=(Button)findViewById(R.id.button3);
        TextView DisplayDay = (TextView) findViewById(R.id.doctor_date_day_box);
        TextView DisplayMonth = (TextView) findViewById(R.id.doctor_date_month_box);
        TextView DisplayYear = (TextView) findViewById(R.id.doctor_date_year_box);
        calender = Calendar.getInstance();
        simpledateformat = new SimpleDateFormat("dd-MM-yyyy");
        Date = simpledateformat.format(calender.getTime());
        String d_arr[]=Date.split("-");
        DisplayDay.setText(d_arr[0]);
        DisplayMonth.setText(d_arr[1]);
        DisplayYear.setText(d_arr[2]);
        onbtn();
        //doc_Name=e1.getText().toString().trim();
        //doe=e21.getText().toString().trim()+"/"+e22.getText().toString().trim()+"/"+e23.getText().toString().trim();

    }

    @Override
    public void onBackPressed() { }

    public void onbtn()
    {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doc_Name=e1.getText().toString();
                doe=e21.getText().toString().trim()+"/"+e22.getText().toString().trim()+"/"+e23.getText().toString().trim();
                if(doc_Name.trim().length() > 0){
                        session.createLoginSession(doc_Name);

                        // Staring MainActivity
                        Intent i = new Intent(getApplicationContext(), choice.class);
                        startActivity(i);
                       // finish();

                    }else{
                        // username / password doesn't match
                        //alert.showAlertDialog(doctor_login.this, "Login failed..", "Username/Password is incorrect", false);
                    }
                }
        });
    }
}
