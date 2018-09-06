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
import android.widget.TextView;
import android.widget.Toast;
import android.widget.DatePicker;
import java.text.SimpleDateFormat;
import java.util.Date;


import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import static kuppam.gynacology_v3.generalinfo.id;
import static kuppam.gynacology_v3.updateChoice.retrieve;
import static kuppam.gynacology_v3.updateChoice.selected;

public class obstetricinfo extends AppCompatActivity {

    EditText e1, e2, e3,gestation;
    TextView edd1, edd2, edd3,lmp1,lmp2,lmp3,corrected1,corrected2,corrected3;
    Button submitbutton;
    DatePicker datepick;
    RadioGroup rg1;
    RadioButton selectedRadioButton;
    android.widget.Button submit;
    android.widget.DatePicker simpleDatePicker;
    android.widget.DatePicker simpleDatePicker3;
    TextView t1,t2,t3,t4,d1,d2,d3,d4,d5,d6;
    SQLiteDatabase database;
    JSONObject ob;
    String[] str;
    String str1[];
    String pid = "";
    String table_query = "patient_id TEXT NOT NULL, " +
            "history_of_amenorrhea_months TEXT NOT NULL," +
            "history_of_amenorrhea_days TEXT NOT NULL," +
            "lmp TEXT NOT NULL," +
            " edd TEXT NOT NULL," +
            " gestational_age TEXT NOT NULL," +
            " corrected_edd TEXT NOT NULL," +
            " any_complaints1 TEXT DEFAULT \"Not Specified\"," +
            "update_status TEXT DEFAULT \"No\"," +
            "timestamp TEXT NOT NULL," +
            " primary key(patient_id), foreign key(patient_id) references general_information(patient_id)";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obstetricinfo);
        e1 = (EditText) findViewById(R.id.amenorrhea_month_box);
        e2 = (EditText) findViewById(R.id.amenorrhea_day_box);
        e3 = (EditText) findViewById(R.id.obstetric_complaints);
        rg1 = (RadioGroup) findViewById(R.id.G_lmp);
        selectedRadioButton = (RadioButton) findViewById(R.id.G_lmp_yes);
        corrected1 = (TextView)findViewById(R.id.correctededd_date_day_box);
        corrected2 = (TextView) findViewById(R.id.correctededd_date_month_box);
        corrected3 = (TextView) findViewById(R.id.correctededd_date_year_box);
        lmp1 = (TextView) findViewById(R.id.lmp_date_day_box);
        lmp2 = (TextView) findViewById(R.id.lmp_date_month_box);
        lmp3 = (TextView) findViewById(R.id.lmp_date_year_box);
        edd1= (TextView) findViewById(R.id.edd_date_day_box);
        edd2= (TextView) findViewById(R.id.edd_date_month_box);
        edd3= (TextView) findViewById(R.id.edd_date_year_box);
	datepick=(DatePicker)findViewById(R.id.simpleDatePicker);
        submitbutton=(Button)findViewById(R.id.submitButton);
        gestation = (EditText) findViewById(R.id.gestation);
        d1=(TextView)findViewById(R.id.dash1);
        d2=(TextView)findViewById(R.id.dash2);
        d3=(TextView)findViewById(R.id.dash3);
        d4=(TextView)findViewById(R.id.dash4);
        d5=(TextView)findViewById(R.id.dash5);
        d6=(TextView)findViewById(R.id.dash6);
        t1=(TextView)findViewById(R.id.lmp_text);
        t2=(TextView)findViewById(R.id.edd_text);
        t3=(TextView)findViewById(R.id.gest_text);
        t4=(TextView)findViewById(R.id.weeks_text);
        lmp1.setVisibility(View.GONE);
        lmp2.setVisibility(View.GONE);
        lmp3.setVisibility(View.GONE);
        edd1.setVisibility(View.GONE);
        edd2.setVisibility(View.GONE);
        edd3.setVisibility(View.GONE);
	datepick.setVisibility(View.GONE);
        submitbutton.setVisibility(View.GONE);
        d1.setVisibility(View.GONE);
        d2.setVisibility(View.GONE);
        d3.setVisibility(View.GONE);
        d4.setVisibility(View.GONE);
        //d5.setVisibility(View.GONE);
        //d6.setVisibility(View.GONE);
        gestation.setVisibility(View.GONE);
        t1.setVisibility(View.GONE);
        t2.setVisibility(View.GONE);
        t3.setVisibility(View.GONE);
        t4.setVisibility(View.GONE);
        //opening db
        database = openOrCreateDatabase("gynaecology", Context.MODE_PRIVATE, null);

        //creating table if doesn't exist
        database.execSQL("CREATE TABLE IF NOT EXISTS obstetric_information(" + table_query + ")");
        if(retrieve) {
            if (selected == "server") {
                try {
                    Bundle b = getIntent().getExtras();
                    System.out.println("Entered");
                    System.out.println(b.getString("JObj1"));
                    //JSONArray arr = new JSONArray(new String(responseBody));
                    ob = new JSONObject(b.getString("JObj1"));
                    System.out.println(ob);
                    System.out.println("Retreived::" + "Here");
                    JSONObject obj = (JSONObject) ob.get("obstetric_information");
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
                String selectQuery = "SELECT * FROM obstetric_information WHERE patient_id ='" + pid + "';";
                Cursor cursor = database.rawQuery(selectQuery, null);
                if (cursor.moveToFirst()) {//cursor.moveToNext();
                    int totalColumn = cursor.getColumnCount();
                    do {
                        str = new String[totalColumn];
                        for (int i = 0; i < totalColumn; i++) {
                            str[i] = cursor.getString(i);
                            //System.out.println(str[i]);
                        }
                        onDisplay2();
                        //System.out.println(cursor.getString(0));
                        String deleteQuery = "DELETE FROM obstetric_information where patient_id = " + "'" + pid + "'";
                        Log.d("query", deleteQuery);
                        database.execSQL(deleteQuery);
                    } while (cursor.moveToNext());
                }
            }
        }
    }

    @Override
    public void onBackPressed() { }

    public void settext(View view)
    {
        simpleDatePicker = (DatePicker) findViewById(R.id.simpleDatePicker);

        edd1=(TextView)findViewById(R.id.edd_date_day_box);
        edd1.setText(""+simpleDatePicker.getDayOfMonth());

        edd2=(TextView)findViewById(R.id.edd_date_month_box);
        edd2.setText(""+(simpleDatePicker.getMonth()+1));

        edd3=(TextView)findViewById(R.id.edd_date_year_box);
        edd3.setText(""+simpleDatePicker.getYear());


        Integer day =  simpleDatePicker.getDayOfMonth();
        Integer month =(simpleDatePicker.getMonth() + 1);
        Integer year = simpleDatePicker.getYear();

        Integer newyear;
        Integer newmonth;
        Integer newday;

        if(month==1)
        {
            newyear=year;
            newmonth=10;
            newday=day+7;
        }
        else if(month==2)
        {
            newyear=year;
            newmonth=11;
            newday=day+7;
        }
        else if(month==3)
        {
            newyear=year;
            newmonth=12;
            newday=day+7;
        }

        else
        {
            newyear=year+1;
            newmonth=month-3;
            newday=day+7;

        }

        lmp1=(TextView)findViewById(R.id.lmp_date_day_box);
        lmp1.setText(""+newday);
        lmp2=(TextView)findViewById(R.id.lmp_date_month_box);
        lmp2.setText(""+newmonth);
        lmp3=(TextView)findViewById(R.id.lmp_date_year_box);
        lmp3.setText(""+newyear);



    }

    public void settext3(View view)
    {
        simpleDatePicker3 = (DatePicker) findViewById(R.id.simpleDatePicker3);

        corrected1=(TextView)findViewById(R.id.correctededd_date_day_box);
        corrected1.setText(""+simpleDatePicker3.getDayOfMonth());

        corrected2=(TextView)findViewById(R.id.correctededd_date_month_box);
        corrected2.setText(""+(simpleDatePicker3.getMonth()+1));

        corrected3=(TextView)findViewById(R.id.correctededd_date_year_box);
        corrected3.setText(""+simpleDatePicker3.getYear());



    }







    public void onProceed(View view) {
        if (ValidationSuccess()) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
            String format = simpleDateFormat.format(new Date());
            String l="Not Specified",e="Not Specified", g="Not Specified",c="Not-Specified";
            if(selectedRadioButton.isChecked())
            {
                l=lmp1.getText().toString().trim()+"/"+lmp2.getText().toString().trim()+"/"+lmp3.getText().toString().trim();
                e=edd1.getText().toString().trim()+"/"+edd2.getText().toString().trim()+"/"+edd3.getText().toString().trim();
                g=gestation.getText().toString().trim();
            }
            c=corrected1.getText().toString().trim()+"/"+corrected2.getText().toString().trim()+"/"+corrected3.getText().toString().trim();
            String insert_query = "'" + id.toString().trim() + "'," +
                    "'" + e1.getText().toString().trim() + "'," +
                    "'" + e2.getText().toString().trim() + "'," +
                    "'" + l + "'," +
                    "'" + e + "'," +
                    "'" + g + "'," +
                    "'" + c + "'," +
                    "'" +  e3.getText().toString().trim() + "'," +
                    "'" + "No" + "'," +
                    "'" + format.toString().trim() + "'";
            System.out.println("InsertQuery:" + insert_query);
            //inserting into database
            database.execSQL("INSERT INTO obstetric_information VALUES (" + insert_query + ")");
            Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_LONG).show();
            if(selected == "server")
            {
                Intent intent=new Intent(getApplicationContext(),trimester_choice.class);
                intent.putExtra("type","obs");
                intent.putExtra("JObj2",ob.toString());
                startActivity(intent);
            }
            else {
                Intent intent = new Intent(getApplicationContext(), trimester_choice.class);
                intent.putExtra("pid",pid);
                startActivity(intent);
            }

        } else {
            Toast.makeText(getApplicationContext(), "Please check the details", Toast.LENGTH_LONG).show();
        }
    }

    private boolean ValidationSuccess() {

        boolean check = true;
        StringBuilder errMsg = new StringBuilder("");

        e1 = (EditText) findViewById(R.id.amenorrhea_month_box);
        e2 = (EditText) findViewById(R.id.amenorrhea_day_box);
        rg1 = (RadioGroup) findViewById(R.id.G_lmp);
        selectedRadioButton = (RadioButton) findViewById(R.id.G_lmp_yes);
        corrected1 = (TextView) findViewById(R.id.correctededd_date_day_box);
        corrected2 = (TextView) findViewById(R.id.correctededd_date_month_box);
        corrected3 = (TextView) findViewById(R.id.correctededd_date_year_box);

        if (e1.getText().toString().equalsIgnoreCase("")) {
            e1.setError("Please enter a value");
            check = false;
        }

        if (e2.getText().toString().equalsIgnoreCase("")) {
            e2.setError("Please enter a value");
            check = false;
        }

        if (Integer.parseInt(e1.getText().toString()) > 10) {
            e1.setError("Exceeds limit!");
            return false;
        }

        if (Integer.parseInt(e2.getText().toString()) > 31) {
            e2.setError("Exceeds limit!");
            return false;
        }

        if (rg1.getCheckedRadioButtonId() == -1) {
            errMsg.append("Please select on option\n");
            check = false;
        }

        if (selectedRadioButton.isChecked()) {
            lmp1 = (TextView) findViewById(R.id.lmp_date_day_box);
            lmp2 = (TextView) findViewById(R.id.lmp_date_month_box);
            lmp3 = (TextView) findViewById(R.id.lmp_date_year_box);
            edd1 = (TextView) findViewById(R.id.edd_date_day_box);
            edd2 = (TextView) findViewById(R.id.edd_date_month_box);
            edd3 = (TextView) findViewById(R.id.edd_date_year_box);

            if (lmp1.getText().toString().equalsIgnoreCase("")) {
                lmp1.setError("Please enter LMP Day");
                check = false;
            }

            if (lmp2.getText().toString().equalsIgnoreCase("")) {
                lmp2.setError("Please enter LMP Month");
                check = false;
            }

            if (lmp3.getText().toString().equalsIgnoreCase("")) {
                lmp3.setError("Please enter LMP Year");
                check = false;
            }

            if (edd1.getText().toString().equalsIgnoreCase("")) {
                edd1.setError("Please enter EDD Day");
                check = false;
            }
            if (edd2.getText().toString().equalsIgnoreCase("")) {
                edd2.setError("Please enter EDD Month");
                check = false;
            }
            if (edd3.getText().toString().equalsIgnoreCase("")) {
                edd3.setError("Please enter EDD Year");
                check = false;
            }
        }
        if (corrected1.getText().toString().equalsIgnoreCase("")) {
            corrected1.setError("Please enter the corrected EDD");
            check = false;
        }
        if (corrected2.getText().toString().equalsIgnoreCase("")) {
            corrected2.setError("Please enter the corrected EDD");
            check = false;
        }
        if (corrected3.getText().toString().equalsIgnoreCase("")) {
            corrected3.setError("Please enter the corrected EDD");
            check = false;
        }
        return check;
    }

    public void click(View view) {
        lmp1.setVisibility(View.VISIBLE);
        lmp2.setVisibility(View.VISIBLE);
        lmp3.setVisibility(View.VISIBLE);
        edd1.setVisibility(View.VISIBLE);
        edd2.setVisibility(View.VISIBLE);
        edd3.setVisibility(View.VISIBLE);
	datepick.setVisibility(View.VISIBLE);
        submitbutton.setVisibility(View.VISIBLE);
        d1.setVisibility(View.VISIBLE);
        d2.setVisibility(View.VISIBLE);
        d3.setVisibility(View.VISIBLE);
        d4.setVisibility(View.VISIBLE);
        //d5.setVisibility(View.VISIBLE);
        //d6.setVisibility(View.VISIBLE);
        gestation.setVisibility(View.VISIBLE);
        t1.setVisibility(View.VISIBLE);
        t2.setVisibility(View.VISIBLE);
        t3.setVisibility(View.VISIBLE);
        t4.setVisibility(View.VISIBLE);

    }

    public void click1(View view) {
        lmp1.setVisibility(View.GONE);
        lmp2.setVisibility(View.GONE);
        lmp3.setVisibility(View.GONE);
        edd1.setVisibility(View.GONE);
        edd2.setVisibility(View.GONE);
        edd3.setVisibility(View.GONE);
	datepick.setVisibility(View.GONE);
        submitbutton.setVisibility(View.GONE);
        gestation.setVisibility(View.GONE);
        t1.setVisibility(View.GONE);
        t2.setVisibility(View.GONE);
        t3.setVisibility(View.GONE);
        t4.setVisibility(View.GONE);
        d1.setVisibility(View.GONE);
        d2.setVisibility(View.GONE);
        d3.setVisibility(View.GONE);
        d4.setVisibility(View.GONE);
    }

    public void onDisplay()
    {
        try {
            JSONObject obj1 = (JSONObject) ob.get("obstetric_information");
            //e3.setText(obj1.getString("C0"));
            e1.setText(obj1.getString("C1"));
            e2.setText(obj1.getString("C2"));
            e3.setText(obj1.getString("C7"));
            System.out.println(obj1.getString("C3"));
            if(obj1.getString("C3").equals(new String("Not Specified")))
            {
                System.out.println("Yes I am Here");
                rg1.check(R.id.G_lmp_no);
                //selectedRadioButton.setChecked(true);
            }
            else
            {
                System.out.println("No I am Here");
                rg1.check(R.id.G_lmp_yes);
                lmp1.setVisibility(View.VISIBLE);
                lmp2.setVisibility(View.VISIBLE);
                lmp3.setVisibility(View.VISIBLE);
                edd1.setVisibility(View.VISIBLE);
                edd2.setVisibility(View.VISIBLE);
                edd3.setVisibility(View.VISIBLE);
                d1.setVisibility(View.VISIBLE);
                d2.setVisibility(View.VISIBLE);
                d3.setVisibility(View.VISIBLE);
                d4.setVisibility(View.VISIBLE);
                //d5.setVisibility(View.VISIBLE);
                //d6.setVisibility(View.VISIBLE);
                gestation.setVisibility(View.VISIBLE);
                t1.setVisibility(View.VISIBLE);
                t2.setVisibility(View.VISIBLE);
                t3.setVisibility(View.VISIBLE);
                t4.setVisibility(View.VISIBLE);
                //selectedRadioButton.setChecked(true);
                String[] x = obj1.getString("C3").split("/");
                System.out.println(obj1.getString("C3"));
                lmp3.setText(x[2]);
                lmp2.setText(x[1]);
                lmp1.setText(x[0]);
                String[] y = obj1.getString("C4").split("/");
                edd3.setText(y[2]);
                edd2.setText(y[1]);
                edd1.setText(y[0]);
                gestation.setText(obj1.getString("C5"));
            }
            String[] z = obj1.getString("C6").split("/");
            corrected3.setText(z[2]);
            corrected2.setText(z[1]);
            corrected1.setText(z[0]);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void onDisplay2()
    {
        e1.setText(str[1]);
        e2.setText(str[2]);
        e3.setText(str[7]);

        if( str[3].equals(new String("Not Specified")))
        {
            selectedRadioButton = (RadioButton) findViewById(R.id.G_lmp_no);
            selectedRadioButton.setChecked(true);
        }
        else
        {
            System.out.println(str[3]);
            selectedRadioButton = (RadioButton) findViewById(R.id.G_lmp_yes);
            selectedRadioButton.setChecked(true);
            lmp1.setVisibility(View.VISIBLE);
            lmp2.setVisibility(View.VISIBLE);
            lmp3.setVisibility(View.VISIBLE);
            edd1.setVisibility(View.VISIBLE);
            edd2.setVisibility(View.VISIBLE);
            edd3.setVisibility(View.VISIBLE);
            d1.setVisibility(View.VISIBLE);
            d2.setVisibility(View.VISIBLE);
            d3.setVisibility(View.VISIBLE);
            d4.setVisibility(View.VISIBLE);
            //d5.setVisibility(View.VISIBLE);
            //d6.setVisibility(View.VISIBLE);
            gestation.setVisibility(View.VISIBLE);
            t1.setVisibility(View.VISIBLE);
            t2.setVisibility(View.VISIBLE);
            t3.setVisibility(View.VISIBLE);
            t4.setVisibility(View.VISIBLE);
            String[] x = str[3].split("/");
            lmp3.setText(x[2]);
            lmp2.setText(x[1]);
            lmp1.setText(x[0]);
            String[] y = str[4].split("/");
            edd3.setText(y[2]);
            edd2.setText(y[1]);
            edd1.setText(y[0]);
            gestation.setText(str[5]);
            System.out.println("Watch::"+str[6]);
        }
        String[] z = str[6].split("/");
        corrected3.setText(z[2]);
        corrected2.setText(z[1]);
        corrected1.setText(z[0]);

    }
   /* public void cleanDB(String pid) {
        String selectQuery = "SELECT name FROM sqlite_master WHERE type='table' AND name='obstetric_information';";
        Cursor cursor = database.rawQuery(selectQuery, null);
        if(cursor.moveToFirst())
        {
        do {
            //System.out.println(cursor.getString(0));
            String deleteQuery = "DELETE FROM " + cursor.getString(0) + " WHERE patient_id = " + "'" + pid + "'";
            Log.d("query", deleteQuery);
            database.execSQL(deleteQuery);
            }while(cursor.moveToNext());
        }
    }//database.close();
*/
}
