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

public class gynaec_family_history extends AppCompatActivity {
    EditText d_box,h_box,t_box,m_box,others;
    RadioGroup rg1, rg2, rg3, rg4;
    RadioButton sr1,sr2,sr3,sr4;
    RadioButton d1,h1,t1,m1;
    SQLiteDatabase database;
    //String id="1234";
    JSONObject ob;
    String[] str;
    String pid = "";
    String table_query = "patient_id TEXT ," +
            "gynaec_diabetes2 TEXT ," +
            "gynaec_hypertension2 TEXT ," +
            "gynaec_tb2 TEXT ," +
            "gynaec_malignancy TEXT ," +
            "gynaec_others6 TEXT ," +
            "update_status TEXT DEFAULT \"No\"," +
            "timestamp TEXT ," +
            "primary key(patient_id)," +
            "foreign key(patient_id) references general_information(patient_id)";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gynaec_family_history);
        d_box= (EditText)findViewById(R.id.g_diabetes_family_box) ;
        h_box= (EditText)findViewById(R.id.g_hyper_family_box) ;
        t_box= (EditText)findViewById(R.id.g_family_tb_box) ;
        m_box = (EditText)findViewById(R.id.g_malignacy_box) ;
        others= (EditText)findViewById(R.id.g_complaints_oi_2) ;
        d1=(RadioButton) findViewById(R.id.g_dia_family_yes);
        h1=(RadioButton)findViewById(R.id.g_hyper_family_yes);
        t1=(RadioButton)findViewById(R.id.g_family_tb_yes);
        m1=(RadioButton)findViewById(R.id.g_malignacy_yes);
        rg2 = (RadioGroup)findViewById(R.id.g_hyper_family);
        rg3 = (RadioGroup)findViewById(R.id.g_family_tb);
        rg4 = (RadioGroup)findViewById(R.id.g_malignacy);
        rg1=(RadioGroup)findViewById(R.id.g_diabetes_family);
        sr1 = (RadioButton)findViewById(R.id.g_dia_family_no);
        sr2 = (RadioButton)findViewById(R.id.g_hyper_family_no);
        sr3 = (RadioButton)findViewById(R.id.g_family_tb_no);
        sr4 = (RadioButton)findViewById(R.id.g_malignacy_no);
        // = (RadioButton)findViewById(R.id.g_dia_family_no);
        d_box.setVisibility(View.GONE);
        h_box.setVisibility(View.GONE);
        t_box.setVisibility(View.GONE);
        m_box.setVisibility(View.GONE);
        //opening db
        database = openOrCreateDatabase("gynaecology", Context.MODE_PRIVATE, null);

        //creating table if doesn't exist
        database.execSQL("CREATE TABLE IF NOT EXISTS gynaec_family_history (" + table_query + ")");
        if(retrieve) {
            if (selected == "server") {
                try {
                    Bundle b = getIntent().getExtras();
                    System.out.println("Entered");
                    System.out.println(b.getString("JObj3"));
                    //JSONArray arr = new JSONArray(new String(responseBody));
                    ob = new JSONObject(b.getString("JObj3"));
                    System.out.println(ob);
                    System.out.println("Retreived::" + "Here");
                    JSONObject obj = (JSONObject) ob.get("gynaec_family_history");
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
                String selectQuery = "SELECT * FROM gynaec_family_history WHERE patient_id ='" + pid + "';";
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
                        String deleteQuery = "DELETE FROM gynaec_family_history where patient_id = " + "'" + pid + "'";
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
            String diaf= "Negative", hypf = "Negative", tb2 = "Negative",mlg ="Negative";
            if(d1.isChecked())
                diaf=d_box.getText().toString().trim();
            if(h1.isChecked())
                hypf=h_box.getText().toString().trim();
            if(t1.isChecked())
                tb2=t_box.getText().toString().trim();
            if(m1.isChecked())
                mlg=m_box.getText().toString().trim();
            String insert_query = "'" + id.toString().trim() + "'," +
                    "'" + diaf + "'," +
                    "'" + hypf + "'," +
                    "'" + tb2 + "'," +
                    "'" + mlg + "'," +
                    "'" + others.getText().toString().trim() + "'," +
                    "'" + "No" + "'," +
                    "'" + format.toString().trim() + "'";
            System.out.println("InsertQuery:" + insert_query);
            //inserting into database
            database.execSQL("INSERT INTO gynaec_family_history VALUES (" + insert_query + ")");
            Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_LONG).show();
            if(selected == "server")
            {
                Intent intent=new Intent(getApplicationContext(),personal_history13.class);
                intent.putExtra("type","obs");
                intent.putExtra("JObj4",ob.toString());
                startActivity(intent);
            }
            else {
                Intent intent = new Intent(getApplicationContext(), personal_history13.class);
                intent.putExtra("pid",pid);
                startActivity(intent);
            }

        } else {
            Toast.makeText(getApplicationContext(), "Please check the details", Toast.LENGTH_LONG).show();
        }
        database.close();


    }
    private boolean ValidationSuccess(){

        boolean check=true;
        StringBuilder errMsg = new StringBuilder("");
        if (rg1.getCheckedRadioButtonId() == -1)
        {
            errMsg.append("Please select on option\n");
            check=false;
        }

        if (rg2.getCheckedRadioButtonId() == -1)
        {
            errMsg.append("Please select on option\n");
            check=false;
        }

        if (rg3.getCheckedRadioButtonId() == -1)
        {
            errMsg.append("Please select on option\n");
            check=false;
        }

        if (rg4.getCheckedRadioButtonId() == -1)
        {
            errMsg.append("Please select on option\n");
            check=false;
        }

        if(d1.isChecked())
        {
            if (d_box.getText().toString().equalsIgnoreCase("")){
                d_box.setError("Please enter a value");
                check=false;
            }
        }

        if(h1.isChecked())
        {
            if (h_box.getText().toString().equalsIgnoreCase("")){
                h_box.setError("Please enter a value");
                check=false;
            }
        }

        if(t1.isChecked())
        {
            if (t_box.getText().toString().equalsIgnoreCase("")){
                t_box.setError("Please enter a value");
                check=false;
            }
        }

        if(m1.isChecked())
        {
            if (m_box.getText().toString().equalsIgnoreCase("")){
                m_box.setError("Please enter a value");
                check=false;
            }
        }
        if(others.getText().toString().equalsIgnoreCase("")){
            others.setError("Please enter a value");
            check=false;
        }

        return check;
    }
    public void click(View view)
    {
        d_box.setVisibility(View.VISIBLE);

    }
    public void click1(View view)
    {
        d_box.setVisibility(View.GONE);

    }
    public void click2(View view)
    {
        h_box.setVisibility(View.VISIBLE);

    }
    public void click3(View view)
    {
        h_box.setVisibility(View.GONE);

    }
    public void click4(View view)
    {
        t_box.setVisibility(View.VISIBLE);

    }
    public void click5(View view)
    {
        t_box.setVisibility(View.GONE);

    }
    public void click6(View view)
    {
        m_box.setVisibility(View.VISIBLE);

    }
    public void click7(View view)
    {
        m_box.setVisibility(View.GONE);

    }

    public void onDisplay()
    {

        try {
            JSONObject obj1 = (JSONObject) ob.get("gynaec_family_history");
            if(obj1.getString("C1").equals(new String("Negative")))
            {
                sr1.setChecked(true);
            }
            else
            {
                d1.setChecked(true);
                d_box.setText(obj1.getString("C1"));
                d_box.setVisibility(View.VISIBLE);
            }
            if(obj1.getString("C2").equals(new String("Negative")))
            {
                sr2.setChecked(true);
            }
            else
            {
                h1.setChecked(true);
                h_box.setText(obj1.getString("C2"));
                h_box.setVisibility(View.VISIBLE);
            }
            if(obj1.getString("C3").equals(new String("Negative")))
            {
                sr3.setChecked(true);
            }
            else
            {
                t1.setChecked(true);
                t_box.setText(obj1.getString("C3"));
                t_box.setVisibility(View.VISIBLE);
            }
            if(obj1.getString("C4").equals(new String("Negative")))
            {
                sr4.setChecked(true);
            }
            else
            {
                m1.setChecked(true);
                m_box.setText(obj1.getString("C4"));
                m_box.setVisibility(View.VISIBLE);
            }
            others.setText(obj1.getString("C5"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void onDisplay2()
    {
        if(str[1].equals(new String("Negative")))
        {
            sr1.setChecked(true);
        }
        else
        {
            d1.setChecked(true);
            d_box.setText(str[1]);
            d_box.setVisibility(View.VISIBLE);
        }
        if(str[2].equals(new String("Negative")))
        {
            sr2.setChecked(true);
        }
        else
        {
            h1.setChecked(true);
            h_box.setText(str[2]);
            h_box.setVisibility(View.VISIBLE);
        }
        if(str[3].equals(new String("Negative")))
        {
            sr3.setChecked(true);
        }
        else
        {
            t1.setChecked(true);
            t_box.setText(str[3]);
            t_box.setVisibility(View.VISIBLE);
        }
        if(str[4].equals(new String("Negative")))
        {
            sr4.setChecked(true);
        }
        else
        {
            m1.setChecked(true);
            m_box.setText(str[4]);
            m_box.setVisibility(View.VISIBLE);
        }
        others.setText(str[5]);
    }

}
