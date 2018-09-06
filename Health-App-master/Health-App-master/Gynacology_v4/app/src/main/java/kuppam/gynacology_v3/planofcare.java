package kuppam.gynacology_v3;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import static kuppam.gynacology_v3.generalinfo.id;
import static kuppam.gynacology_v3.updateChoice.retrieve;
import static kuppam.gynacology_v3.updateChoice.selected;

public class planofcare extends AppCompatActivity {
    private EditText e1, e2;
    SQLiteDatabase database;
    JSONObject ob;
    String[] str;
    String pid = "";

    String table_query = "patient_id TEXT ," +
            "gynaec_plan_of_care TEXT ," +
            "gynaec_advice TEXT ," +
            "update_status TEXT DEFAULT \"No\"," +
            "timestamp TEXT ," +
            "primary key(patient_id)," +
            "foreign key(patient_id) references general_information(patient_id)";

    @Override
    public void onBackPressed() { }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planofcare);
        e1 = (EditText) findViewById(R.id.g_plan);
        e2 = (EditText) findViewById(R.id.g_advice);
        //opening db
        database = openOrCreateDatabase("gynaecology", Context.MODE_PRIVATE, null);

        //creating table if doesn't exist
        database.execSQL("CREATE TABLE IF NOT EXISTS gynaec_care(" + table_query + ")");
        if(retrieve) {
            if (selected == "server") {
                try {
                    Bundle b = getIntent().getExtras();
                    System.out.println("Entered");
                    System.out.println(b.getString("JObj8"));
                    //JSONArray arr = new JSONArray(new String(responseBody));
                    ob = new JSONObject(b.getString("JObj8"));
                    System.out.println(ob);
                    System.out.println("Retreived::" + "Here");
                    JSONObject obj = (JSONObject) ob.get("gynaec_care");
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
                String selectQuery = "SELECT * FROM gynaec_care WHERE patient_id ='" + pid + "';";
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
                        String deleteQuery = "DELETE FROM gynaec_care where patient_id = " + "'" + pid + "'";
                        Log.d("query", deleteQuery);
                        database.execSQL(deleteQuery);
                    } while (cursor.moveToNext());
                }
            }
        }
    }
    public void onProceed(View view) {
        if (ValidationSuccess()) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
            String format = simpleDateFormat.format(new Date());
            String insert_query ="'" + id.toString().trim() + "'," +
                    "'"+e1.getText().toString().trim()+"',"+
                    "'"+e2.getText().toString().trim()+"',"+
                    "'"+"No"+"',"+
                    "'"+format.toString().trim()+"'";
            System.out.println("InsertQuery:"+insert_query);
            //inserting into database
            database.execSQL("INSERT INTO gynaec_care VALUES (" + insert_query + ")");
            Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_LONG).show();
            if(retrieve)
            {
                Intent intent=new Intent(getApplicationContext(),updateServer.class);
                //intent.putExtra("type","obs");
                //intent.putExtra("JObj5",ob.toString());
                startActivity(intent);
            }
            else {
                Intent intent = new Intent(getApplicationContext(), thankyou.class);
                //intent.putExtra("pid",pid);
                startActivity(intent);
            }

        } else {
            Toast.makeText(getApplicationContext(), "Please check the details", Toast.LENGTH_LONG).show();
        }
    }
    private boolean ValidationSuccess(){

        boolean check=true;
        StringBuilder errMsg = new StringBuilder("");

        e1 = (EditText)findViewById(R.id.g_plan) ;
        if (e1.getText().toString().equalsIgnoreCase("")){
            e1.setError("Please enter a value");
            check=false;
        }
        e2 = (EditText)findViewById(R.id.g_advice) ;
        if (e2.getText().toString().equalsIgnoreCase("")){
            e2.setError("Please enter a value");
            check=false;
        }

        return check;
    }

    public void onDisplay()
    {
        try {
            JSONObject obj1 = (JSONObject) ob.get("gynaec_care");
            e1.setText(obj1.getString("C1"));
            e2.setText(obj1.getString("C2"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onDisplay2()
    {
            e1.setText(str[1]);
            e2.setText(str[2]);
    }

}
