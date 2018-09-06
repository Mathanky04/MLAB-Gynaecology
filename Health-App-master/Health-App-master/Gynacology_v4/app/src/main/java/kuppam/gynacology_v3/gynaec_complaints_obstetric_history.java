package kuppam.gynacology_v3;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import static kuppam.gynacology_v3.generalinfo.id;
import static kuppam.gynacology_v3.updateChoice.retrieve;
import static kuppam.gynacology_v3.updateChoice.selected;

public class gynaec_complaints_obstetric_history extends AppCompatActivity {

    SQLiteDatabase database;
    String table_gynaec_complaints="patient_id TEXT ,gynaec_chief_complaints TEXT , gynaec_lmp TEXT ,gynaec_history_of_present_illness TEXT , gynaec_menstrual_history TEXT ,update_status TEXT DEFAULT \"No\", timestamp TEXT ,primary key(patient_id), foreign key(patient_id) references general_information(patient_id)";
    String table_gynaec_obstetric_history="patient_id TEXT ,gynaec_married_life TEXT , gynaec_p TEXT , gynnaec_a TEXT , gynaec_l TEXT ,update_status TEXT DEFAULT \"No\", timestamp TEXT , primary key(patient_id), foreign key(patient_id) references general_information(patient_id)";
    EditText chief_complaints,hist_illness,mens_reg,mens_irreg,married_life,p,a,l;
    TextView l1,l2,l3;
    RadioButton m1,m2; String m;
    RadioGroup rg1;
    Button btnNext;
    android.widget.Button submit;
    android.widget.DatePicker simpleDatePicker;
    JSONObject ob;
    String[] str;
    String str1[];
    String pid = "";
    @Override
    public void onBackPressed() { }



public void settext(View view)
    {
        simpleDatePicker = (DatePicker) findViewById(R.id.simpleDatePicker2);

        l1=(TextView)findViewById(R.id.g_lmp_day_box);
        l1.setText(""+simpleDatePicker.getDayOfMonth());

        l2=(TextView)findViewById(R.id.g_lmp_month_box);
        l2.setText(""+simpleDatePicker.getMonth()+1);

        l3=(TextView)findViewById(R.id.g_lmp_year_box);
        l3.setText(""+simpleDatePicker.getYear());



    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gynaec_complaints_obstetric_history);
        database = openOrCreateDatabase("gynaecology", Context.MODE_PRIVATE, null);
        database.execSQL("CREATE TABLE IF NOT EXISTS gynaec_complaints(" + table_gynaec_complaints + ")");
        database.execSQL("CREATE TABLE IF NOT EXISTS gynaec_obstetric_history(" + table_gynaec_obstetric_history + ")");
        chief_complaints=(EditText)findViewById(R.id.g_chief_complaints);
        l1=(TextView)findViewById(R.id.g_lmp_day_box);
        l2=(TextView)findViewById(R.id.g_lmp_month_box);
        l3=(TextView)findViewById(R.id.g_lmp_year_box);
        hist_illness=(EditText)findViewById(R.id.g_hist_of_pres_illness_box);
        mens_reg=(EditText)findViewById(R.id.g_menstrual_history_regular_box);
        mens_irreg=(EditText)findViewById(R.id.g_menstrual_history_irregular_box);
        married_life=(EditText)findViewById(R.id.g_married_life);
        p=(EditText)findViewById(R.id.g_P_box);
        a=(EditText)findViewById(R.id.g_A_box);
        l=(EditText)findViewById(R.id.g_L_box);
        m1=(RadioButton)findViewById(R.id.g_menstrual_history_regular);
        m2=(RadioButton)findViewById(R.id.g_menstrual_history_irregular);
        rg1=(RadioGroup)findViewById(R.id.g_menstrual_history);
        mens_reg.setVisibility(View.GONE);
        mens_irreg.setVisibility(View.GONE);
        btnNext=(Button)findViewById(R.id.next_page10);
        //onButton();
        if(retrieve) {
            if (selected == "server") {
                try {
                    Bundle b = getIntent().getExtras();
                    System.out.println("Entered");
                    System.out.println(b.getString("JObj1"));
                    ob = new JSONObject(b.getString("JObj1"));
                    System.out.println(ob);
                    System.out.println("Retreived::" + "Here");
                    JSONObject obj = (JSONObject) ob.get("gynaec_complaints");
                    pid = (String) obj.get("C0");
                    System.out.println(pid);
                    onDisplay();
                    Toast.makeText(getApplicationContext(), "Retreived!", Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Yo em here");
                Bundle b = getIntent().getExtras();
                pid = b.getString("pid");
                String selectQuery = "SELECT * FROM gynaec_complaints WHERE patient_id ='" + pid + "';";
                String selectQuery1 = "SELECT * FROM gynaec_obstetric_history WHERE patient_id ='" + pid + "';";
                Cursor cursor = database.rawQuery(selectQuery, null);
                Cursor c = database.rawQuery(selectQuery1, null);
                if (cursor.moveToFirst()) {//cursor.moveToNext();
                    int totalColumn = cursor.getColumnCount();
                    do {
                        str = new String[totalColumn];
                        for (int i = 0; i < totalColumn; i++) {
                            str[i] = cursor.getString(i);
                        }
                        //System.out.println(cursor.getString(0));
                    } while (cursor.moveToNext());
                }
                if (c.moveToFirst()) {//cursor.moveToNext();
                    int totalColumn = c.getColumnCount();
                    do {
                        str1 = new String[totalColumn];
                        for (int i = 0; i < totalColumn; i++) {
                            str1[i] = c.getString(i);
                            //System.out.println(str[i]);
                        }
                        onDisplay2();
                        String deleteQuery = "DELETE FROM gynaec_complaints where patient_id = " + "'" + pid + "'";
                        Log.d("query", deleteQuery);
                        database.execSQL(deleteQuery);
                        String deleteQuery1 = "DELETE FROM gynaec_obstetric_history where patient_id = " + "'" + pid + "'";
                        Log.d("query", deleteQuery1);
                        database.execSQL(deleteQuery1);
                    } while (cursor.moveToNext());
                }
            }
        }
    }

    public void onButton(View view)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
        final String format = simpleDateFormat.format(new Date());
                if(ValidationSuccess())
                {
                    String m ="";
                    String lmp="";
                    lmp=l1.getText().toString().trim()+"/"+l2.getText().toString().trim()+"/"+l3.getText().toString().trim();
                    if(m1.isChecked())
                        m ="Regular-"+mens_reg.getText().toString();
                    else if(m2.isChecked())
                        m ="Irregular-"+mens_irreg.getText().toString();
                    String insert_gynaec_complaints="'"+id.toString().trim()+"','"+
                            chief_complaints.getText().toString().trim()+"','"+
                            lmp+"','"+
                            hist_illness.getText().toString().trim()+"','"+
                            m+"','"+
                            "No"+"','"+
                            format.toString().trim()+"'";
                    String insert_gynaec_obstetric_history="'"+id.toString().trim()+"','"+
                            married_life.getText().toString().trim()+"','"+
                            p.getText().toString().trim()+"','"+
                            a.getText().toString()+"','"+
                            l.getText().toString()+"','"+
                            "No"+"','"+
                            format.toString().trim()+"'";
                    System.out.println("InsertQuery:" + insert_gynaec_complaints);
                    System.out.println("InsertQuery:" + insert_gynaec_obstetric_history);
                    //inserting into database
                    database.execSQL("INSERT INTO gynaec_complaints VALUES (" + insert_gynaec_complaints + ")");
                    database.execSQL("INSERT INTO gynaec_obstetric_history VALUES (" + insert_gynaec_obstetric_history+" )");
                    Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_LONG).show();
                    if(selected == "server")
                    {
                        Intent intent=new Intent(getApplicationContext(),pasthistory.class);
                        intent.putExtra("JObj2",ob.toString());
                        System.out.println("SENT::");
                        startActivity(intent);
                    }
                    else {
                        Intent intent = new Intent(getApplicationContext(), pasthistory.class);
                        intent.putExtra("pid",pid);
                        startActivity(intent);
                    }

                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Please check the details", Toast.LENGTH_LONG).show();
                }
    }

    private boolean ValidationSuccess(){

        boolean check=true;
        StringBuilder errMsg = new StringBuilder("");
        if (chief_complaints.getText().toString().equalsIgnoreCase("")){
            chief_complaints.setError("Please enter a value");
            check=false;
        }

        if (l1.getText().toString().equalsIgnoreCase("")){
            l1.setError("Please enter a value");
            check=false;
        }

        if (l2.getText().toString().equalsIgnoreCase("")){
            l2.setError("Please enter a value");
            check=false;
        }

        if (l3.getText().toString().equalsIgnoreCase("")){
            l3.setError("Please enter a value");
            check=false;
        }

        if (hist_illness.getText().toString().equalsIgnoreCase("")){
            hist_illness.setError("Please enter a value");
            check=false;
        }

        if (married_life.getText().toString().equalsIgnoreCase("")){
            married_life.setError("Please enter a value");
            check=false;
        }

        if (p.getText().toString().equalsIgnoreCase("")){
            p.setError("Please enter a value");
            check=false;
        }

        if (a.getText().toString().equalsIgnoreCase("")){
            a.setError("Please enter a value");
            check=false;
        }

        if (l.getText().toString().equalsIgnoreCase("")){
            l.setError("Please enter a value");
            check=false;
        }


        if (rg1.getCheckedRadioButtonId() == -1)
        {
            errMsg.append("Please select on option\n");
            check=false;
        }

        if(m1.isChecked())
        {
            if (mens_reg.getText().toString().equalsIgnoreCase("")){
                mens_reg.setError("Please enter a value");
                check=false;
            }
        }

        if(m2.isChecked())
        {
            if (mens_irreg.getText().toString().equalsIgnoreCase("")){
                mens_irreg.setError("Please enter a value");
                check=false;
            }
        }

        return check;
    }

    public void click(View view)
    {
        mens_reg.setVisibility(View.VISIBLE);
        mens_irreg.setVisibility(View.GONE);
    }

    public void click1(View view)
    {
        mens_reg.setVisibility(View.GONE);
        mens_irreg.setVisibility(View.VISIBLE);
    }

    public void onDisplay()
    {
        try {
            JSONObject obj1 = (JSONObject) ob.get("gynaec_complaints");
            JSONObject obj2 = (JSONObject) ob.get("gynaec_obstetric_history");
            chief_complaints.setText(obj1.getString("C1"));
            System.out.println(obj1.getString("C2"));
            String[] x = obj1.getString("C2").split("/");
            l1.setText(x[0]);
            l2.setText(x[1]);
            l3.setText(x[2]);
            hist_illness.setText(obj1.getString("C3"));
            String[] y =obj1.getString("C4").split("-");
            if(y[0].equals(new String("Regular")))
            {
                m1.setChecked(true);
                mens_reg.setText(obj1.getString("C4"));
                mens_reg.setVisibility(View.VISIBLE);
            }
            else
            {
                m2.setChecked(true);
                mens_irreg.setText(obj1.getString("C4"));
                mens_irreg.setVisibility(View.VISIBLE);
            }
            married_life.setText(obj2.getString("C1"));
            p.setText(obj2.getString("C2"));
            a.setText(obj2.getString("C3"));
            l.setText(obj2.getString("C4"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onDisplay2()
    {
            chief_complaints.setText(str[1]);
            String[] x = str[2].split("/");
            l1.setText(x[0]);
            l2.setText(x[1]);
            l3.setText(x[2]);
            hist_illness.setText(str[3]);
            String[] y =str[4].split("-");
            if(y[0].equals(new String("Regular")))
            {
                m1.setChecked(true);
                mens_reg.setText(str[4]);
                mens_reg.setVisibility(View.VISIBLE);
            }
            else
            {
                m2.setChecked(true);
                mens_irreg.setText(str[4]);
                mens_irreg.setVisibility(View.VISIBLE);
            }
            married_life.setText(str1[1]);
            p.setText(str1[2]);
            a.setText(str1[3]);
            l.setText(str1[4]);
    }
}
