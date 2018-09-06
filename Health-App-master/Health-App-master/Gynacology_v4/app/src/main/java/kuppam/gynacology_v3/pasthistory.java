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

public class pasthistory extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5,e6, e7, e8, e9,e10;
    RadioGroup rg1, rg2, rg3, rg4, rg5,rg6,rg7,rg8, rg9;
    RadioButton selectedRadioButton,selectedRadioButton1, sr2,sr21, sr3,sr31, sr4,sr41, sr5,sr51, sr6,sr61, sr7,sr71, sr8,sr81, sr9,sr91;
    SQLiteDatabase database;
    JSONObject ob;
    String[] str;
    String pid = "";
    String table_query = "patient_id TEXT ," +
            "gynaec_diabetes1 TEXT ,"+
            "gynaec_hypertension1 TEXT ," +
            "gynaec_tb1 TEXT ," +
            "gynaec_epilepsy TEXT ," +
            "gynaec_cardiac_disease TEXT ," +
            "gynaec_renal_disease TEXT ,"+
            "gynaec_veneral_disease TEXT ," +
            "gynaec_blood_transfusion TEXT , " +
            "gynaec_past_surgeries TEXT ," +
            "gynaec_others5 TEXT ," +
            "update_status TEXT DEFAULT \"No\"," +
            "timestamp TEXT ,primary key(patient_id)," +
            "foreign key(patient_id) references general_information(patient_id)";

    @Override
    public void onBackPressed() { }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasthistory);
        e1 = (EditText)findViewById(R.id.g_diabetes_box) ;
        e2 = (EditText)findViewById(R.id.g_hypertension_box) ;
        e3 = (EditText)findViewById(R.id.g_TB_box) ;
        e4 = (EditText)findViewById(R.id.g_epilepsy_box) ;
        e5 = (EditText)findViewById(R.id.g_cardiac_box) ;
        e6 = (EditText)findViewById(R.id.g_renal_box) ;
        e7 = (EditText)findViewById(R.id.g_veneral_box) ;
        e8 = (EditText)findViewById(R.id.g_blood_box) ;
        e9 = (EditText)findViewById(R.id.g_past_surgery_box) ;
        e10=(EditText)findViewById(R.id.g_complaints_oi);
        rg2 = (RadioGroup)findViewById(R.id.g_hypertension);
        sr2 = (RadioButton)findViewById(R.id.g_hypertension_yes);
        sr21 = (RadioButton)findViewById(R.id.g_hypertension_no);
        rg3 = (RadioGroup)findViewById(R.id.g_TB);
        sr3 = (RadioButton)findViewById(R.id.g_TB_yes);
        sr31 = (RadioButton)findViewById(R.id.g_TB_no);
        rg4 = (RadioGroup)findViewById(R.id.g_epilepsy);
        sr4 = (RadioButton)findViewById(R.id.g_epi_yes);
        sr41 = (RadioButton)findViewById(R.id.g_epi_no);
        rg5 = (RadioGroup)findViewById(R.id.g_cardiac);
        sr5 = (RadioButton)findViewById(R.id.g_cardiac_yes);
        sr51 = (RadioButton)findViewById(R.id.g_cardiac_no);
        rg6 = (RadioGroup)findViewById(R.id.g_renal);
        sr6 = (RadioButton)findViewById(R.id.g_renal_yes);
        sr61 = (RadioButton)findViewById(R.id.g_renal_no);
        rg7 = (RadioGroup)findViewById(R.id.g_veneral);
        sr7 = (RadioButton)findViewById(R.id.g_veneral_yes);
        sr71 = (RadioButton)findViewById(R.id.g_veneral_no);
        rg8 = (RadioGroup)findViewById(R.id.g_blood);
        sr8 = (RadioButton)findViewById(R.id.g_blood_yes);
        sr81 = (RadioButton)findViewById(R.id.g_blood_no);
        rg9 = (RadioGroup)findViewById(R.id.g_past_surgery);
        sr9 = (RadioButton)findViewById(R.id.g_past_surgery_yes);
        sr91 = (RadioButton)findViewById(R.id.g_past_surgery_no);
        rg1=(RadioGroup)findViewById(R.id.g_diabetes);
        selectedRadioButton = (RadioButton)findViewById(R.id.g_diabetes_yes);
        selectedRadioButton1 = (RadioButton)findViewById(R.id.g_diabetes_no);
        e1.setVisibility(View.GONE);
        e2.setVisibility(View.GONE);
        e3.setVisibility(View.GONE);
        e4.setVisibility(View.GONE);
        e5.setVisibility(View.GONE);
        e6.setVisibility(View.GONE);
        e7.setVisibility(View.GONE);
        e8.setVisibility(View.GONE);
        e9.setVisibility(View.GONE);
        //opening db
        database = openOrCreateDatabase("gynaecology", Context.MODE_PRIVATE, null);

        //creating table if doesn't exist
        database.execSQL("CREATE TABLE IF NOT EXISTS gynaec_past_history (" + table_query + ")");
        if(retrieve) {
            if (selected == "server") {
                try {
                    Bundle b = getIntent().getExtras();
                    System.out.println("Entered");
                    System.out.println(b.getString("JObj2"));
                    //JSONArray arr = new JSONArray(new String(responseBody));
                    ob = new JSONObject(b.getString("JObj2"));
                    System.out.println(ob);
                    System.out.println("Retreived::" + "Here");
                    JSONObject obj = (JSONObject) ob.get("gynaec_past_history");
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
                String selectQuery = "SELECT * FROM gynaec_past_history WHERE patient_id ='" + pid + "';";
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
                        String deleteQuery = "DELETE FROM gynaec_past_history where patient_id = " + "'" + pid + "'";
                        Log.d("query", deleteQuery);
                        database.execSQL(deleteQuery);
                    } while (cursor.moveToNext());
                }
            }
        }

    }
    public void onProceed(View view) {
        if (ValidationSuccess()) {
            String dia = "No", hyp = "No", tb1 = "No",epp1 ="Negative",crd="Negative",rnl="Negative",vnd="No",blt="No",psts="No";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
            String format = simpleDateFormat.format(new Date());
            if(selectedRadioButton.isChecked())
                dia=e1.getText().toString();
            if(sr2.isChecked())
                hyp=e2.getText().toString();
            if(sr3.isChecked())
                tb1=e3.getText().toString();
            if(sr4.isChecked())
                epp1=e4.getText().toString();
            if(sr5.isChecked())
                crd=e5.getText().toString();
            if(sr6.isChecked())
                rnl=e6.getText().toString();
            if(sr7.isChecked())
                vnd=e7.getText().toString();
            if(sr8.isChecked())
                blt=e8.getText().toString();
            if(sr9.isChecked())
                psts=e9.getText().toString();
            String insert_query = "'" + id.toString().trim() + "'," +
                    "'" + dia + "'," +
                    "'" + hyp + "'," +
                    "'" + tb1 + "'," +
                    "'" + epp1+ "'," +
                    "'" + crd + "'," +
                    "'" + rnl + "'," +
                    "'" + vnd + "'," +
                    "'" + blt + "'," +
                    "'" + psts + "'," +
                    "'" + e10.getText().toString().trim() + "'," +
                    "'" + "No" + "'," +
                    "'" + format.toString().trim() + "'";
            System.out.println("InsertQuery:" + insert_query);
            //inserting into database
            database.execSQL("INSERT INTO gynaec_past_history VALUES (" + insert_query + ")");
            Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_LONG).show();
            if(selected == "server")
            {
                Intent intent=new Intent(getApplicationContext(),gynaec_family_history.class);
                intent.putExtra("type","obs");
                intent.putExtra("JObj3",ob.toString());
                startActivity(intent);
            }
            else {
                Intent intent = new Intent(getApplicationContext(), gynaec_family_history.class);
                intent.putExtra("pid",pid);
                startActivity(intent);
            }


        } else {
            Toast.makeText(getApplicationContext(), "Please check the details", Toast.LENGTH_LONG).show();
        }


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

        if (rg5.getCheckedRadioButtonId() == -1)
        {
            errMsg.append("Please select on option\n");
            check=false;
        }

        if (rg6.getCheckedRadioButtonId() == -1)
        {
            errMsg.append("Please select on option\n");
            check=false;
        }

        if (rg7.getCheckedRadioButtonId() == -1)
        {
            errMsg.append("Please select on option\n");
            check=false;
        }

        if (rg8.getCheckedRadioButtonId() == -1)
        {
            errMsg.append("Please select on option\n");
            check=false;
        }

        if (rg9.getCheckedRadioButtonId() == -1)
        {
            errMsg.append("Please select on option\n");
            check=false;
        }

        if(selectedRadioButton.isChecked())
        {
            e1 = (EditText)findViewById(R.id.g_diabetes_box) ;
            if (e1.getText().toString().equalsIgnoreCase("")){
                e1.setError("Please enter a value");
                check=false;
            }
        }

        if(sr2.isChecked())
        {
            e2 = (EditText)findViewById(R.id.g_hypertension_box) ;
            if (e2.getText().toString().equalsIgnoreCase("")){
                e2.setError("Please enter a value");
                check=false;
            }
        }

        if(sr3.isChecked())
        {
            e3 = (EditText)findViewById(R.id.g_TB_box) ;
            if (e3.getText().toString().equalsIgnoreCase("")){
                e3.setError("Please enter a value");
                check=false;
            }
        }

        if(sr4.isChecked())
        {
            e4 = (EditText)findViewById(R.id.g_epilepsy_box) ;
            if (e4.getText().toString().equalsIgnoreCase("")){
                e4.setError("Please enter a value");
                check=false;
            }
        }

        if(sr5.isChecked())
        {
            e5 = (EditText)findViewById(R.id.g_cardiac_box) ;
            if (e5.getText().toString().equalsIgnoreCase("")){
                e5.setError("Please enter a value");
                check=false;
            }
        }

        if(sr6.isChecked())
        {
            e6 = (EditText)findViewById(R.id.g_renal_box) ;
            if (e6.getText().toString().equalsIgnoreCase("")){
                e6.setError("Please enter a value");
                check=false;
            }
        }

        if(sr7.isChecked())
        {
            e5 = (EditText)findViewById(R.id.g_veneral_box) ;
            if (e5.getText().toString().equalsIgnoreCase("")){
                e5.setError("Please enter a value");
                check=false;
            }
        }

        if(sr8.isChecked())
        {
            e6 = (EditText)findViewById(R.id.g_blood_box) ;
            if (e6.getText().toString().equalsIgnoreCase("")){
                e6.setError("Please enter a value");
                check=false;
            }
        }
        if(sr9.isChecked())
        {
            e5 = (EditText)findViewById(R.id.g_past_surgery_box) ;
            if (e5.getText().toString().equalsIgnoreCase("")){
                e5.setError("Please enter a value");
                check=false;
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
    public void click2(View view)
    {
        e2.setVisibility(View.VISIBLE);
    }
    public void click3(View view)
    {
        e2.setVisibility(View.GONE);
    }
    public void click4(View view)
    {
        e3.setVisibility(View.VISIBLE);
    }
    public void click5(View view)
    {
        e3.setVisibility(View.GONE);
    }
    public void click6(View view)
    {
        e4.setVisibility(View.VISIBLE);
    }
    public void click7(View view)
    {
        e4.setVisibility(View.GONE);
    }
    public void click8(View view)
    {
        e5.setVisibility(View.VISIBLE);
    }
    public void click9(View view)
    {
        e5.setVisibility(View.GONE);
    }
    public void click10(View view)
    {
        e6.setVisibility(View.VISIBLE);
    }
    public void click11(View view)
    {
        e6.setVisibility(View.GONE);
    }
    public void click12(View view)
    {
        e7.setVisibility(View.VISIBLE);
    }
    public void click13(View view)
    {
        e7.setVisibility(View.GONE);
    }
    public void click14(View view)
    {
        e8.setVisibility(View.VISIBLE);
    }
    public void click15(View view)
    {
        e8.setVisibility(View.GONE);
    }
    public void click16(View view)
    {
        e9.setVisibility(View.VISIBLE);
    }
    public void click17(View view)
    {
        e9.setVisibility(View.GONE);
    }

    public void onDisplay()
    {
        try {
            JSONObject obj1 = (JSONObject) ob.get("gynaec_past_history");
            if(obj1.getString("C1").equals(new String("No")))
            {
                selectedRadioButton1.setChecked(true);
            }
            else
            {
                selectedRadioButton.setChecked(true);
                e1.setText(obj1.getString("C1"));
                e1.setVisibility(View.VISIBLE);
            }
            if(obj1.getString("C2").equals(new String("No")))
            {
                sr21.setChecked(true);
            }
            else
            {
                sr2.setChecked(true);
                e2.setText(obj1.getString("C2"));
                e2.setVisibility(View.VISIBLE);
            }
            if(obj1.getString("C3").equals(new String("No")))
            {
                sr31.setChecked(true);
            }
            else
            {
                sr3.setChecked(true);
                e3.setText(obj1.getString("C3"));
                e3.setVisibility(View.VISIBLE);
            }
            if(obj1.getString("C4").equals(new String("Negative")))
            {
                sr41.setChecked(true);
            }
            else
            {
                sr4.setChecked(true);
                e4.setText(obj1.getString("C4"));
                e4.setVisibility(View.VISIBLE);
            }
            if(obj1.getString("C5").equals(new String("Negative")))
            {
                sr51.setChecked(true);
            }
            else
            {
                sr5.setChecked(true);
                e5.setText(obj1.getString("C5"));
                e5.setVisibility(View.VISIBLE);
            }
            if(obj1.getString("C6").equals(new String("Negative")))
            {
                sr61.setChecked(true);
            }
            else
            {
                sr6.setChecked(true);
                e6.setText(obj1.getString("C6"));
                e6.setVisibility(View.VISIBLE);
            }
            if(obj1.getString("C7").equals(new String("No")))
            {
                sr71.setChecked(true);
            }
            else
            {
                sr7.setChecked(true);
                e7.setText(obj1.getString("C7"));
                e7.setVisibility(View.VISIBLE);
            }
            if(obj1.getString("C8").equals(new String("No")))
            {
                sr81.setChecked(true);
            }
            else
            {
                sr8.setChecked(true);
                e8.setText(obj1.getString("C8"));
                e8.setVisibility(View.VISIBLE);
            }
            if(obj1.getString("C9").equals(new String("No")))
            {
                sr91.setChecked(true);
            }
            else
            {
                sr9.setChecked(true);
                e9.setText(obj1.getString("C9"));
                e9.setVisibility(View.VISIBLE);
            }
            e10.setText(obj1.getString("C10"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onDisplay2()
    {
            if(str[1].equals(new String("No")))
            {
                selectedRadioButton1.setChecked(true);
            }
            else
            {
                selectedRadioButton.setChecked(true);
                e1.setText(str[1]);
                e1.setVisibility(View.VISIBLE);
            }
            if(str[2].equals(new String("No")))
            {
                sr21.setChecked(true);
            }
            else
            {
                sr2.setChecked(true);
                e2.setText(str[2]);
                e2.setVisibility(View.VISIBLE);
            }
            if(str[3].equals(new String("No")))
            {
                sr31.setChecked(true);
            }
            else
            {
                sr3.setChecked(true);
                e3.setText(str[3]);
                e3.setVisibility(View.VISIBLE);
            }
            if(str[4].equals(new String("Negative")))
            {
                sr41.setChecked(true);
            }
            else
            {
                sr4.setChecked(true);
                e4.setText(str[4]);
                e4.setVisibility(View.VISIBLE);
            }
            if(str[5].equals(new String("Negative")))
            {
                sr51.setChecked(true);
            }
            else
            {
                sr5.setChecked(true);
                e5.setText(str[5]);
                e5.setVisibility(View.VISIBLE);
            }
            if(str[6].equals(new String("Negative")))
            {
                sr61.setChecked(true);
            }
            else
            {
                sr6.setChecked(true);
                e6.setText(str[6]);
                e6.setVisibility(View.VISIBLE);
            }
            if(str[7].equals(new String("No")))
            {
                sr71.setChecked(true);
            }
            else
            {
                sr7.setChecked(true);
                e7.setText(str[7]);
                e7.setVisibility(View.VISIBLE);
            }
            if(str[8].equals(new String("No")))
            {
                sr81.setChecked(true);
            }
            else
            {
                sr8.setChecked(true);
                e8.setText(str[8]);
                e8.setVisibility(View.VISIBLE);
            }
            if(str[9].equals(new String("No")))
            {
                sr91.setChecked(true);
            }
            else
            {
                sr9.setChecked(true);
                e9.setText(str[9]);
                e9.setVisibility(View.VISIBLE);
            }
        e10.setText(str[10]);
    }

}
