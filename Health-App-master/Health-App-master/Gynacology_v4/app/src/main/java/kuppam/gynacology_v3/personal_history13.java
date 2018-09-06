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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import static kuppam.gynacology_v3.generalinfo.id;
import static kuppam.gynacology_v3.updateChoice.retrieve;
import static kuppam.gynacology_v3.updateChoice.selected;

public class personal_history13 extends AppCompatActivity {
    EditText e1, e2, e3, e4, e5, e6, e7, e8, e9;
    RadioGroup rg1, rg2, rg3, rg4, rg5, rg6;
    RadioButton selectedRadioButton, d1, d2, s1, s2,a1,a2;
    SQLiteDatabase database;
    JSONObject ob;
    String[] str;
    String pid = "";
    String table_query = "patient_id TEXT ," +
            "gynaec_diet TEXT ," +
            "gynaec_appetite TEXT ," +
            "gynaec_sleep TEXT ," +
            "gynaec_micturition TEXT ," +
            "gynaec_bowel TEXT ," +
            "gynaec_habits TEXT ," +
            "gynaec_allergy_history TEXT ," +
            "update_status TEXT DEFAULT \"No\"," +
            "timestamp TEXT ,primary key(patient_id)," +
            "foreign key(patient_id) references general_information(patient_id)";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_history13);
        e1 = (EditText) findViewById(R.id.g_allergy_box);
        e1.setVisibility(View.GONE);
        e2 = (EditText) findViewById(R.id.g_appetite_box);
        e3 = (EditText) findViewById(R.id.g_micturition_box);
        e4 = (EditText) findViewById(R.id.g_bowel_box);
        e5 = (EditText) findViewById(R.id.g_habits_box);
        selectedRadioButton = (RadioButton)findViewById(R.id.g_allergy_yes);
        d1 = (RadioButton) findViewById(R.id.g_diet_veg);
        d2 = (RadioButton) findViewById(R.id.g_diet_mixed);
        s1 = (RadioButton) findViewById(R.id.g_sleep_normal);
        s2 = (RadioButton) findViewById(R.id.g_sleep_disturbed);
        rg2 = (RadioGroup) findViewById(R.id.g_sleep);
        rg3 = (RadioGroup) findViewById(R.id.g_allergy);
        //a1 = (RadioButton) findViewById(R.id.g_allergy_yes);
        a2 = (RadioButton) findViewById(R.id.g_allergy_no);
        rg1 = (RadioGroup) findViewById(R.id.g_diet);
        //opening db
        database = openOrCreateDatabase("gynaecology", Context.MODE_PRIVATE, null);

        //creating table if doesn't exist
        database.execSQL("CREATE TABLE IF NOT EXISTS gynaec_personal_history (" + table_query + ")");
        if(retrieve) {
            if (selected == "server") {
                try {
                    Bundle b = getIntent().getExtras();
                    System.out.println("Entered");
                    System.out.println(b.getString("JObj4"));
                    //JSONArray arr = new JSONArray(new String(responseBody));
                    ob = new JSONObject(b.getString("JObj4"));
                    System.out.println(ob);
                    System.out.println("Retreived::" + "Here");
                    JSONObject obj = (JSONObject) ob.get("gynaec_personal_history");
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
                String selectQuery = "SELECT * FROM gynaec_personal_history WHERE patient_id ='" + pid + "';";
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
                        String deleteQuery = "DELETE FROM gynaec_personal_history where patient_id = " + "'" + pid + "'";
                        Log.d("query", deleteQuery);
                        database.execSQL(deleteQuery);
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
            String format = simpleDateFormat.format(new Date());
            String diet = "", slp = "", algr = "No";
            if(d1.isChecked())
                diet="Veg";
            else if(d2.isChecked())
                diet="Mixed";
            if(s1.isChecked())
                slp="Normal";
            else if(s2.isChecked())
                slp="Disturbed";
            if(selectedRadioButton.isChecked())
                algr=e1.getText().toString();
            else
                algr="No";
            String insert_query = "'" + id.toString().trim() + "'," +
                    "'" + diet + "'," +
                    "'" + slp + "'," +
                    "'" + e2.getText().toString().trim() + "'," +
                    "'" + e3.getText().toString().trim() + "'," +
                    "'" + e4.getText().toString().trim() + "'," +
                    "'" + e5.getText().toString().trim() + "'," +
                    "'" + algr + "'," +
                    "'" + "No" + "'," +
                    "'" + format.toString().trim() + "'";
            System.out.println("InsertQuery:" + insert_query);
            //inserting into database
            database.execSQL("INSERT INTO gynaec_personal_history VALUES (" + insert_query + ")");
            Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_LONG).show();
            if(selected == "server")
            {
                Intent intent=new Intent(getApplicationContext(),gynaec_general_physical_examination.class);
                intent.putExtra("type","obs");
                intent.putExtra("JObj5",ob.toString());
                startActivity(intent);
            }
            else {
                Intent intent = new Intent(getApplicationContext(), gynaec_general_physical_examination.class);
                intent.putExtra("pid",pid);
                startActivity(intent);
            }

        } else {
            Toast.makeText(getApplicationContext(), "Please check the details", Toast.LENGTH_LONG).show();
        }
        database.close();

    }

    private boolean ValidationSuccess() {

        boolean check = true;
        StringBuilder errMsg = new StringBuilder("");

        if (e2.getText().toString().equalsIgnoreCase("")) {
            e2.setError("Please enter a value");
            check = false;
        }

        if (e3.getText().toString().equalsIgnoreCase("")) {
            e3.setError("Please enter a value");
            check = false;
        }

        if (e4.getText().toString().equalsIgnoreCase("")) {
            e4.setError("Please enter a value");
            check = false;
        }

        if (rg1.getCheckedRadioButtonId() == -1) {
            errMsg.append("Please select on option\n");
            check = false;
        }

        if (rg2.getCheckedRadioButtonId() == -1) {
            errMsg.append("Please select on option\n");
            check = false;
        }

        if (rg3.getCheckedRadioButtonId() == -1) {
            errMsg.append("Please select on option\n");
            check = false;
        }

        if (selectedRadioButton.isChecked()) {
            //e1 = (EditText) findViewById(R.id.g_allergy_box);
            if (e1.getText().toString().equalsIgnoreCase("")) {
                e1.setError("Please enter a value");
                check = false;
            }
        }


        return check;
    }

    public void click(View view)
    {
        e1.setVisibility(View.VISIBLE);
    }
    public void click1(View view)
    {
        e1.setVisibility(View.GONE);
    }
    public void onDisplay()
    {
        try {
            JSONObject obj1 = (JSONObject) ob.get("gynaec_personal_history");
            if(obj1.getString("C1").equals(new String("Veg")))
            {
                d1.setChecked(true);
            }
            else
            {
                d2.setChecked(true);
            }
            if(obj1.getString("C2").equals("Normal"))
            {
                s1.setChecked(true);
            }
            else
            {
                s2.setChecked(true);
            }
            e2.setText(obj1.getString("C3"));
            e3.setText(obj1.getString("C4"));
            e4.setText(obj1.getString("C5"));
            e5.setText(obj1.getString("C6"));
            if(obj1.getString("C7").equals(new String("No")))
            {
                a2.setChecked(true);
            }
            else
            {
                selectedRadioButton.setChecked(true);
                e1.setText(obj1.getString("C7"));
                e1.setVisibility(View.VISIBLE);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onDisplay2()
    {
        if(str[1].equals(new String("Veg")))
        {
            d1.setChecked(true);
        }
        else
        {
            d2.setChecked(true);
        }
        if(str[2].equals("Normal"))
        {
            s1.setChecked(true);
        }
        else
        {
            s2.setChecked(true);
        }
        e2.setText(str[3]);
        e3.setText(str[4]);
        e4.setText(str[5]);
        e5.setText(str[6]);
        if(str[7].equals(new String("No")))
        {
            a2.setChecked(true);
        }
        else
        {
            selectedRadioButton.setChecked(true);
            e1.setText(str[7]);
            e1.setVisibility(View.VISIBLE);
        }
    }
}
