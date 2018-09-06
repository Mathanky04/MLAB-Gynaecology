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

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import static kuppam.gynacology_v3.generalinfo.id;
import static kuppam.gynacology_v3.updateChoice.retrieve;
import static kuppam.gynacology_v3.updateChoice.selected;

public class gynaec_systemic_examination extends AppCompatActivity {
    Button btnNext2;
    EditText e,e1,e2,e3,e4,e5,e6, e7, e8, e9,e10,e11,e12;
    RadioGroup rg1, rg2, rg3;
    RadioButton selectedRadioButton,selectedRadioButton1, sr1,sr2, sr3,sr4;
    JSONObject ob;
    String[] str;
    String pid = "";
    //String id="1234";
    SQLiteDatabase database;
    String table_query = "patient_id TEXT ," +
            "gynaec_cvs TEXT ," +
            "gynaec_rs TEXT ," +
            "gynaec_cns TEXT ," +
            "gynaec_inspection TEXT ," +
            "gynaec_palpation TEXT ," +
            "gynaec_percussion TEXT ," +
            "gynaec_auscultation TEXT ," +
            "gynaec_local_examination TEXT ," +
            "gynaec_speculum_examination TEXT ," +
            "gynaec_bimanual_examination TEXT ," +
            "gynaec_rectal_examination TEXT ," +
            "gynaec_provisional_diagnosis TEXT ," +
            "update_status TEXT DEFAULT \"No\"," +
            "timestamp TEXT ," +
            "primary key(patient_id)," +
            "foreign key(patient_id) references general_information(patient_id)";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gynaec_systemic_examination);

        e1 = (EditText)findViewById(R.id.g_CVS_box) ;
        e2 = (EditText)findViewById(R.id.g_RS_box) ;
        e3 = (EditText)findViewById(R.id.g_CNS_box) ;
        e4=(EditText)findViewById(R.id.g_inspection);
        e5=(EditText)findViewById(R.id.g_palpitation);
        e6=(EditText)findViewById(R.id.g_percussion);
        e7=(EditText)findViewById(R.id.g_ascultation);
        e8=(EditText)findViewById(R.id.g_local_examination);
        e9=(EditText)findViewById(R.id.g_speculum_examination);
        e10=(EditText)findViewById(R.id.g_bimanual_examination);
        e11=(EditText)findViewById(R.id.g_rectal_examination);
        e12=(EditText)findViewById(R.id.g_provisional_diagnosis);
        rg2 = (RadioGroup)findViewById(R.id.g_RS);
        sr2 = (RadioButton)findViewById(R.id.g_RS_abnormal);
        sr1 = (RadioButton)findViewById(R.id.g_RS_normal);
        rg3 = (RadioGroup)findViewById(R.id.g_CNS);
        sr3 = (RadioButton)findViewById(R.id.g_CNS_abnormal);
        sr4 = (RadioButton)findViewById(R.id.g_CNS_normal);
        rg1=(RadioGroup)findViewById(R.id.g_CVS);
        selectedRadioButton = (RadioButton)findViewById(R.id.g_CVS_abnormal);
        selectedRadioButton1 = (RadioButton)findViewById(R.id.g_CVS_normal);
        e1.setVisibility(View.GONE);
        e2.setVisibility(View.GONE);
        e3.setVisibility(View.GONE);
        //opening db
        database = openOrCreateDatabase("gynaecology", Context.MODE_PRIVATE, null);

        //creating table if doesn't exist
        //database.execSQL("DROP TABLE systemic_examination");
        database.execSQL("CREATE TABLE IF NOT EXISTS gynaec_systemic_examination (" + table_query + ")");
        if(retrieve) {
            if (selected == "server") {
                try {
                    Bundle b = getIntent().getExtras();
                    System.out.println("Entered");
                    System.out.println(b.getString("JObj6"));
                    //JSONArray arr = new JSONArray(new String(responseBody));
                    ob = new JSONObject(b.getString("JObj6"));
                    System.out.println(ob);
                    System.out.println("Retreived::" + "Here");
                    JSONObject obj = (JSONObject) ob.get("gynaec_systemic_examination");
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
                String selectQuery = "SELECT * FROM gynaec_systemic_examination WHERE patient_id ='" + pid + "';";
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
                        String deleteQuery = "DELETE FROM gynaec_systemic_examination where patient_id = " + "'" + pid + "'";
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
            String cvs="Normal",rs="Normal",cns="Normal";
            if(selectedRadioButton.isChecked())
                cvs=e1.getText().toString().trim();
            if(sr2.isChecked())
                rs=e2.getText().toString().trim();
            if(sr3.isChecked())
                cns=e3.getText().toString().trim();
            String insert_query ="'" + id.toString().trim() + "'," +
                    "'" + cvs + "'," +
                    "'" + rs + "'," +
                    "'" + cns + "'," +
                    "'"+e4.getText().toString().trim()+"',"+
                    "'"+e5.getText().toString().trim()+"',"+
                    "'"+e6.getText().toString().trim()+"',"+
                    "'"+e7.getText().toString().trim()+"',"+
                    "'"+e8.getText().toString().trim()+"',"+
                    "'"+e9.getText().toString().trim()+"',"+
                    "'"+e10.getText().toString().trim()+"',"+
                    "'"+e11.getText().toString().trim()+"',"+
                    "'"+e12.getText().toString().trim()+"',"+
                    "'" + "No" + "'," +
                    "'" + format.toString().trim() + "'";
            System.out.println("InsertQuery:" + insert_query);
            //inserting into database
            database.execSQL("INSERT INTO gynaec_systemic_examination VALUES (" + insert_query + ")");
            Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_LONG).show();
            if(selected == "server")
            {
                Intent intent=new Intent(getApplicationContext(),gynaec_investigations.class);
                intent.putExtra("type","obs");
                intent.putExtra("JObj7",ob.toString());
                startActivity(intent);
            }
            else {
                Intent intent = new Intent(getApplicationContext(), gynaec_investigations.class);
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

        if(selectedRadioButton.isChecked())
        {
            e1 = (EditText)findViewById(R.id.g_CVS_box) ;
            if (e1.getText().toString().equalsIgnoreCase("")){
                e1.setError("Please enter a value");
                check=false;
            }
        }

        if(sr2.isChecked())
        {
            e2 = (EditText)findViewById(R.id.g_RS_box) ;
            if (e2.getText().toString().equalsIgnoreCase("")){
                e2.setError("Please enter a value");
                check=false;
            }
        }

        if(sr3.isChecked())
        {
            e3 = (EditText)findViewById(R.id.g_CNS_box) ;
            if (e3.getText().toString().equalsIgnoreCase("")){
                e3.setError("Please enter a value");
                check=false;
            }
        }

        e = (EditText)findViewById(R.id.g_inspection) ;
        if (e.getText().toString().equalsIgnoreCase("")){
            e.setError("Please enter a value");
            check=false;
        }

        e = (EditText)findViewById(R.id.g_palpitation) ;
        if (e.getText().toString().equalsIgnoreCase("")){
            e.setError("Please enter a value");
            check=false;
        }

        e = (EditText)findViewById(R.id.g_ascultation) ;
        if (e.getText().toString().equalsIgnoreCase("")){
            e.setError("Please enter a value");
            check=false;
        }

        e = (EditText)findViewById(R.id.g_percussion) ;
        if (e.getText().toString().equalsIgnoreCase("")){
            e.setError("Please enter a value");
            check=false;
        }

        e = (EditText)findViewById(R.id.g_speculum_examination) ;
        if (e.getText().toString().equalsIgnoreCase("")){
            e.setError("Please enter a value");
            check=false;
        }

        e = (EditText)findViewById(R.id.g_local_examination) ;
        if (e.getText().toString().equalsIgnoreCase("")){
            e.setError("Please enter a value");
            check=false;
        }

        e = (EditText)findViewById(R.id.g_bimanual_examination) ;
        if (e.getText().toString().equalsIgnoreCase("")){
            e.setError("Please enter a value");
            check=false;
        }

        e = (EditText)findViewById(R.id.g_rectal_examination) ;
        if (e.getText().toString().equalsIgnoreCase("")){
            e.setError("Please enter a value");
            check=false;
        }

        e = (EditText)findViewById(R.id.g_provisional_diagnosis) ;
        if (e.getText().toString().equalsIgnoreCase("")){
            e.setError("Please enter a value");
            check=false;
        }


        return check;
    }
    public void click(View view) {
        e1 = (EditText) findViewById(R.id.g_CVS_box);
        e1.setVisibility(View.VISIBLE);
    }

    public void click1(View view) {
        e1 = (EditText) findViewById(R.id.g_CVS_box);
        e1.setVisibility(View.GONE);
    }

    public void click2(View view) {
        e1 = (EditText) findViewById(R.id.g_RS_box);
        e1.setVisibility(View.VISIBLE);
    }

    public void click3(View view) {
        e1 = (EditText) findViewById(R.id.g_RS_box);
        e1.setVisibility(View.GONE);
    }

    public void click4(View view) {
        e1 = (EditText) findViewById(R.id.g_CNS_box);
        e1.setVisibility(View.VISIBLE);
    }

    public void click5(View view) {
        e1 = (EditText) findViewById(R.id.g_CNS_box);
        e1.setVisibility(View.GONE);
    }

    public void onDisplay()
    {
        try {
            JSONObject obj1 = (JSONObject) ob.get("gynaec_systemic_examination");
            if(obj1.getString("C1").equals(new String("Normal")))
            {
                selectedRadioButton1.setChecked(true);
            }
            else
            {
                selectedRadioButton.setChecked(true);
                e1.setText(obj1.getString("C1"));
                e1.setVisibility(View.VISIBLE);
            }
            if(obj1.getString("C2").equals(new String("Normal")))
            {
                sr1.setChecked(true);
            }
            else
            {
                sr2.setChecked(true);
                e2.setText(obj1.getString("C2"));
                e2.setVisibility(View.VISIBLE);
            }
            if(obj1.getString("C3").equals(new String("Normal")))
            {
                sr4.setChecked(true);
            }
            else
            {
                sr3.setChecked(true);
                e3.setText(obj1.getString("C3"));
                e3.setVisibility(View.VISIBLE);
            }
            e4.setText(obj1.getString("C4"));
            e5.setText(obj1.getString("C5"));
            e6.setText(obj1.getString("C6"));
            e7.setText(obj1.getString("C7"));
            e8.setText(obj1.getString("C8"));
            e9.setText(obj1.getString("C9"));
            e10.setText(obj1.getString("C10"));
            e11.setText(obj1.getString("C11"));
            e12.setText(obj1.getString("C12"));
        } catch (JSONException e13) {
            e13.printStackTrace();
        }
    }

    public void onDisplay2()
    {
        if(str[1].equals(new String("Normal")))
        {
            selectedRadioButton1.setChecked(true);
        }
        else
        {
            selectedRadioButton.setChecked(true);
            e1.setText(str[1]);
            e1.setVisibility(View.VISIBLE);
        }
        if(str[2].equals(new String("Normal")))
        {
            sr1.setChecked(true);
        }
        else
        {
            sr2.setChecked(true);
            e2.setText(str[2]);
            e2.setVisibility(View.VISIBLE);
        }
        if(str[3].equals(new String("Normal")))
        {
            sr4.setChecked(true);
        }
        else
        {
            sr3.setChecked(true);
            e3.setText(str[3]);
            e3.setVisibility(View.VISIBLE);
        }
        e4.setText(str[4]);
        e5.setText(str[5]);
        e6.setText(str[6]);
        e7.setText(str[7]);
        e8.setText(str[8]);
        e9.setText(str[9]);
        e10.setText(str[10]);
        e11.setText(str[11]);
        e12.setText(str[12]);
    }

}
