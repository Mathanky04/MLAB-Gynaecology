package kuppam.gynacology_v3;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.text.SimpleDateFormat;
import java.util.Date;

import static kuppam.gynacology_v3.choice.checker1;
import static kuppam.gynacology_v3.choice.checker2;
import static kuppam.gynacology_v3.doctor_login.doc_Name;
import static kuppam.gynacology_v3.doctor_login.doe;
import static kuppam.gynacology_v3.updateChoice.retrieve;
import static kuppam.gynacology_v3.updateChoice.selected;
import static kuppam.gynacology_v3.updateChoice.tables;

public class generalinfo extends AppCompatActivity {

    public static String id;
    //public static String docName = "VasGP";
    //public static String doe = "12-01-16";

    Calendar calender;
    SimpleDateFormat simpledateformat;
    String Date;
    String format;
    JSONObject ob;
    String[] str;
    String pid = "";
    private EditText e1, e21,e22,e23, e3, e4, e5, e6, e71,e72,e73, e8, e9;
    SQLiteDatabase database;
    String add,dat;
    String table_query = "patient_id TEXT NOT NULL," +
            " name TEXT NOT NULL," +
            " date_of_examination TEXT NOT NULL," +
            " age TEXT NOT NULL," +
            " wife_of TEXT NOT NULL," +
            " occupation TEXT DEFAULT \"Not Specified\"," +
            "address TEXT DEFAULT \"Not Specified\"," +
            " mobile_number TEXT NOT NULL," +
            " landline_number TEXT DEFAULT \"Not Specified\"," +
            "update_status TEXT DEFAULT \"No\"," +
            "timestamp TEXT NOT NULL, " +
            "primary key(patient_id)";
    String table_doctor="doctor_name TEXT NOT NULL," +
            " patient_id TEXT NOT NULL," +
            " date_of_examination1 TEXT NOT NULL," +
            " update_status TEXT DEFAULT \"No\"," +
            "timestamp TEXT NOT NULL," +
            "primary key(doctor_name,patient_id),  foreign key(patient_id) references general_information(patient_id)";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generalinfo);
        e1 = (EditText) findViewById(R.id.name_box);
        e21 = (EditText) findViewById(R.id.date_day_box);
        e22 = (EditText) findViewById(R.id.date_month_box);
        e23 = (EditText) findViewById(R.id.date_year_box);
        e3 = (EditText) findViewById(R.id.reg_box);
        e4 = (EditText) findViewById(R.id.age_box);
        e5 = (EditText) findViewById(R.id.wife_box);
        e6 = (EditText) findViewById(R.id.occupation);
        e71 = (EditText) findViewById(R.id.address_box_1);
        e72 = (EditText) findViewById(R.id.address_box_2);
        e73 = (EditText) findViewById(R.id.address_box_3);
        e8 = (EditText) findViewById(R.id.mobile_box);
        e9 = (EditText) findViewById(R.id.landline_box);
        //opening db
        database = openOrCreateDatabase("gynaecology", Context.MODE_PRIVATE, null);

        //creating table if doesn't exist
        database.execSQL("CREATE TABLE IF NOT EXISTS general_information(" + table_query + ")");
        database.execSQL("CREATE TABLE IF NOT EXISTS doctor_table(" + table_doctor + ")");
        TextView DisplayDay = (TextView) findViewById(R.id.date_day_box);
        TextView DisplayMonth = (TextView) findViewById(R.id.date_month_box);
        TextView DisplayYear = (TextView) findViewById(R.id.date_year_box);
        calender = Calendar.getInstance();
        simpledateformat = new SimpleDateFormat("dd-MM-yyyy");
        Date = simpledateformat.format(calender.getTime());
        String d_arr[]=Date.split("-");
        DisplayDay.setText(d_arr[0]);
        DisplayMonth.setText(d_arr[1]);
        DisplayYear.setText(d_arr[2]);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
        String format = simpleDateFormat.format(new Date());
        String id= format.substring(0,2)+format.substring(3,5)+format.substring(8,10)+format.substring(11,13)+format.substring(14,16)+format.substring(17,19);
        e3.setText(id);
        if(retrieve) {
            if (selected == "server") {
                try {
                    Bundle b = getIntent().getExtras();
                    System.out.println("Entered");
                    //JSONArray arr = new JSONArray(new String(responseBody));
                    ob = new JSONObject(b.getString("JObj"));
                    System.out.println(ob);
                    System.out.println("Retreived::" + "Here");
                    JSONObject obj = (JSONObject) ob.get("general_information");
                    pid = (String) obj.get("C0");
                    System.out.println(pid);
                    onDisplay();
                    Toast.makeText(getApplicationContext(), "Retreived!", Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                cleanDB(pid);
            } else {
                Bundle b = getIntent().getExtras();
                pid = b.getString("pid");
                String selectQuery = "SELECT * FROM general_information WHERE patient_id ='" + pid + "';";
                Cursor cursor = database.rawQuery(selectQuery, null);
                if (cursor.moveToFirst()) {//cursor.moveToNext();
                    int totalColumn = cursor.getColumnCount();
                    do {
                        str = new String[totalColumn];
                        for (int i = 0; i < totalColumn; i++) {
                            str[i] = cursor.getString(i);
                        }
                        onDisplay2();
                        //System.out.println(cursor.getString(0));
                        String deleteQuery = "DELETE FROM general_information where patient_id = " + "'" + pid + "'";
                        //String deleteQuery1 = "DELETE FROM doctor_table where patient_id = " + "'" + pid + "'";
                        Log.d("query", deleteQuery);
                        database.execSQL(deleteQuery);
                        //database.execSQL(deleteQuery1);
                    } while (cursor.moveToNext());
                }
            }
        }
    }

    @Override
    public void onBackPressed() { }
    public void onProceed(View view) {
        if (ValidationSuccess()) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
            format = simpleDateFormat.format(new Date());
            dat=e21.getText().toString().trim()+"/"+e22.getText().toString().trim()+"/"+e23.getText().toString().trim();
            add=e71.getText().toString()+"\n"+e72.getText().toString()+"\n"+e73.getText().toString();
            String insert_query ="'"+e3.getText().toString().trim()+"',"+
                    "'"+e1.getText().toString().trim()+"',"+
                    "'"+dat+"',"+
                    "'"+e4.getText().toString().trim()+"',"+
                    "'"+e5.getText().toString().trim()+"',"+
                    "'"+e6.getText().toString().trim()+"',"+
                    "'"+add+"',"+
                    "'"+e8.getText().toString().trim()+"',"+
                    "'"+e9.getText().toString().trim()+"',"+
                    "'"+"No"+"',"+
                    "'"+format.trim()+"'";
            String insert_doctor="'"+e3.getText().toString().trim()+"','"+doc_Name+"','"+doe+"','"+"No"+"',"+
                    "'"+format.trim()+"'";
            System.out.println("InsertQuery:"+insert_query);
            System.out.println("InsertQuery:"+insert_doctor);
            //inserting into database
            database.execSQL("INSERT INTO general_information VALUES (" + insert_query + ")");
            if(!checker1)
                database.execSQL("INSERT INTO doctor_table VALUES (" + insert_doctor + ")");
            id = e3.getText().toString().trim();
            Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_LONG).show();
            if(checker1)
            {
                if(selected == "server")
                {
                    Intent intent=new Intent(getApplicationContext(),obstetricinfo.class);
                    //obstetric
                    intent.putExtra("type","obs");
                    intent.putExtra("JObj1",ob.toString());
                    System.out.println("SENT::");
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(getApplicationContext(), obstetricinfo.class);
                    intent.putExtra("pid",pid);
                    startActivity(intent);
                }
            }
            else if(checker2)
            {
                if(selected == "server")
                {
                    Intent intent=new Intent(getApplicationContext(),gynaec_complaints_obstetric_history.class);
                    //obstetric
                    intent.putExtra("type","obs");
                    intent.putExtra("JObj1",ob.toString());
                    System.out.println("SENT::");
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(getApplicationContext(), gynaec_complaints_obstetric_history.class);
                    intent.putExtra("pid",pid);
                    startActivity(intent);
                }
            }

        } else {
            Toast.makeText(getApplicationContext(), "Please check the details", Toast.LENGTH_LONG).show();
        }
        //database.close();
    }

    private boolean ValidationSuccess() {

        if (e1.getText().toString().equalsIgnoreCase("")) {
            e1.setError("Please enter name");
            return false;
        }

        if (e71.getText().toString().equalsIgnoreCase("")) {
            e71.setError("Please enter address");
            return false;
        }
        if (e72.getText().toString().equalsIgnoreCase("")) {
            e72.setError("Please enter address");
            return false;
        }
        if (e73.getText().toString().equalsIgnoreCase("")) {
            e73.setError("Please enter address");
            return false;
        }

        if (e3.getText().toString().equalsIgnoreCase("")) {
            e3.setError("Please enter registration number");
            return false;
        }

        if (e4.getText().toString().equalsIgnoreCase("")) {
            e4.setError("Please enter age");
            return false;
        }

        if (Integer.parseInt(e4.getText().toString()) > 200) {
            e4.setError("Exceeds limit! Please enter a valid age");
            return false;
        }

        if (e5.getText().toString().equalsIgnoreCase("")) {
            e5.setError("Please enter name");
            return false;
        }

        if (e8.getText().toString().equalsIgnoreCase("")) {
            e8.setError("Please enter mobile number");
            return false;
        }

        if (e8.getText().toString().length() != 10) {
            e8.setError("Please enter a valid 10 digit mobile number");
            return false;
        }

        return true;
    }
    public void onDisplay()
    {
        try {
            JSONObject obj1 = (JSONObject) ob.get("general_information");
            e3.setText(obj1.getString("C0"));
            e1.setText(obj1.getString("C1"));
            String[] x = obj1.getString("C2").split("/");
            e21.setText(x[0]);
            e22.setText(x[1]);
            e23.setText(x[2]);
            e4.setText(obj1.getString("C3"));
            e5.setText(obj1.getString("C4"));
            e6.setText(obj1.getString("C5"));
            String[] y = obj1.getString("C6").split("\n");
            e71.setText(y[0]);
            e72.setText(y[1]);
            e73.setText(y[2]);
            e8.setText(obj1.getString("C7"));
            e9.setText(obj1.getString("C8"));
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void onDisplay2()
    {
        e3.setText(str[0]);
        e1.setText(str[1]);
        String[] x = str[2].split("/");
        e21.setText(x[0]);
        e22.setText(x[1]);
        e23.setText(x[2]);
        e4.setText(str[3]);
        e5.setText(str[4]);
        e6.setText(str[5]);
        String[] y = str[6].split("\n");
        e71.setText(y[0]);
        e72.setText(y[1]);
        e73.setText(y[2]);
        e8.setText(str[7]);
        e9.setText(str[8]);
    }
    public void cleanDB(String pid) {
        String selectQuery = "SELECT name FROM sqlite_master WHERE type='table';";
        Cursor cursor = database.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        cursor.moveToNext();
        do {
            //System.out.println(cursor.getString(0));
            String deleteQuery = "DELETE FROM " + cursor.getString(0) + " WHERE patient_id = " + "'" + pid + "'";
            Log.d("query", deleteQuery);
            database.execSQL(deleteQuery);
        }while(cursor.moveToNext());
    }//database.close();
}

