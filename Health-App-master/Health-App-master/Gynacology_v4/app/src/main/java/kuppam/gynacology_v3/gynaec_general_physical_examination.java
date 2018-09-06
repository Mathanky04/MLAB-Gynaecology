package kuppam.gynacology_v3;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import static kuppam.gynacology_v3.generalinfo.id;
import static kuppam.gynacology_v3.updateChoice.retrieve;
import static kuppam.gynacology_v3.updateChoice.selected;

public class gynaec_general_physical_examination extends AppCompatActivity {

    SQLiteDatabase database;
    String table_gynaec_general_physical_examination="patient_id TEXT , gynaec_height TEXT , gynaec_weight TEXT , gynaec_bmi TEXT , gynaec_temp TEXT , gynaec_pr TEXT , gynaec_bp TEXT , gynaec_thyroid TEXT , gynaec_breasts TEXT , gynaec_spine TEXT , gynaec_pallor TEXT ,  gynaec_icterus TEXT , gynaec_cyanosis TEXT , gynaec_clubbing TEXT , gynaec_lymphadenopathy TEXT , gynaec_edema TEXT ,update_status TEXT DEFAULT \"No\", timestamp TEXT ,primary key(patient_id), foreign key(patient_id) references general_information(patient_id)";
    EditText ht,wt,bmi,pul,bp,thyroid_box,breasts_box,spine_box,pallor_box,icterus_box,cyanosis_box, clubbing_box, lymph_box, edema_box;
    RadioButton t1,t2,b1,b2,s1,s2,p1,p2,i1,i2,c1,c2,cl1,cl2,l1,l2,e1,e2,temp1,temp2;
    JSONObject ob;
    String[] str;
    String pid = "";
    Button btnNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gynaec_general_physical_examination);
        database = openOrCreateDatabase("gynaecology", Context.MODE_PRIVATE, null);
        database.execSQL("CREATE TABLE IF NOT EXISTS gynaec_physical_examination(" + table_gynaec_general_physical_examination + ")");
        ht=(EditText)findViewById(R.id.g_height);
        wt=(EditText)findViewById(R.id.g_weight);
        bmi=(EditText)findViewById(R.id.g_BMI);
        pul=(EditText)findViewById(R.id.g_pulse);
        bp=(EditText)findViewById(R.id.g_BP);
        thyroid_box=(EditText)findViewById(R.id.g_thyroid_box);
        breasts_box=(EditText)findViewById(R.id.g_breasts_box);
        spine_box=(EditText)findViewById(R.id.g_spine_box);
        pallor_box=(EditText)findViewById(R.id.g_pallor_box);
        icterus_box=(EditText)findViewById(R.id.g_icterus_box);
        cyanosis_box=(EditText)findViewById(R.id.g_cyanosis_box);
        clubbing_box=(EditText)findViewById(R.id.g_clubbing_box);
        lymph_box=(EditText)findViewById(R.id.g_lymph_box);
        edema_box=(EditText)findViewById(R.id.g_edema_box);
        t1=(RadioButton)findViewById(R.id.g_thyroid_absent);
        t2=(RadioButton)findViewById(R.id.g_thyroid_present);
        b2=(RadioButton)findViewById(R.id.g_breasts_abnormal);
        b1=(RadioButton)findViewById(R.id.g_breasts_normal);
        s2=(RadioButton)findViewById(R.id.g_spine_abnormal);
        s1=(RadioButton)findViewById(R.id.g_spine_normal);
        p1=(RadioButton)findViewById(R.id.g_pallor_normal);
        p2=(RadioButton)findViewById(R.id.g_pallor_abnormal);
        i1=(RadioButton)findViewById(R.id.g_icterus_normal);
        i2=(RadioButton)findViewById(R.id.g_icterus_abnormal);
        c1=(RadioButton)findViewById(R.id.g_cyanosis_normal);
        c2=(RadioButton)findViewById(R.id.g_cyanosis_abnormal);
        cl1=(RadioButton)findViewById(R.id.g_clubbing_normal);
        cl2=(RadioButton)findViewById(R.id.g_clubbing_abnormal);
        l1=(RadioButton)findViewById(R.id.g_lymph_normal);
        l2=(RadioButton)findViewById(R.id.g_lymph_abnormal);
        e1=(RadioButton)findViewById(R.id.g_edema_normal);
        e2=(RadioButton)findViewById(R.id.g_edema_abnormal);
        temp1=(RadioButton)findViewById(R.id.g_temp_febrile);
        temp2=(RadioButton)findViewById(R.id.g_temp_afebrile);
        btnNext=(Button)findViewById(R.id.next_page14);
        thyroid_box.setVisibility(View.GONE);
        breasts_box.setVisibility(View.GONE);
        spine_box.setVisibility(View.GONE);
        pallor_box.setVisibility(View.GONE);
        icterus_box.setVisibility(View.GONE);
        cyanosis_box.setVisibility(View.GONE);
        clubbing_box.setVisibility(View.GONE);
        lymph_box.setVisibility(View.GONE);
        edema_box.setVisibility(View.GONE);
        if(retrieve) {
            if (selected == "server") {
                try {
                    Bundle b = getIntent().getExtras();
                    System.out.println("Entered");
                    System.out.println(b.getString("JObj5"));
                    //JSONArray arr = new JSONArray(new String(responseBody));
                    ob = new JSONObject(b.getString("JObj5"));
                    System.out.println(ob);
                    System.out.println("Retreived::" + "Here");
                    JSONObject obj = (JSONObject) ob.get("gynaec_physical_examination");
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
                String selectQuery = "SELECT * FROM gynaec_physical_examination WHERE patient_id ='" + pid + "';";
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
                        String deleteQuery = "DELETE FROM gynaec_physical_examination where patient_id = " + "'" + pid + "'";
                        Log.d("query", deleteQuery);
                        database.execSQL(deleteQuery);
                    } while (cursor.moveToNext());
                }
            }
        }

    }

    @Override
    public void onBackPressed() { }

    public void onButton(View view)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
        final String format = simpleDateFormat.format(new Date());
                if(ValidationSuccess())
                {
                    String temp="",t="Normal",b="Normal",s="Normal",pal="Absent",ict="Absent",cya="Absent",clu="Absent",lym="Absent",ede="Absent";
                    if(temp1.isChecked())
                        temp="Febrile";
                    else if(temp2.isChecked())
                        temp="Afebrile";
                    if(t1.isChecked())
                        t=thyroid_box.getText().toString();
                    if(b1.isChecked())
                        b=breasts_box.getText().toString();
                    if(s2.isChecked())
                        s=spine_box.getText().toString();
                    if(p1.isChecked())
                        pal=pallor_box.getText().toString();
                    if(i1.isChecked())
                        ict=icterus_box.getText().toString();
                    if(c1.isChecked())
                        cya=cyanosis_box.getText().toString();
                    if(cl1.isChecked())
                        clu=clubbing_box.getText().toString();
                    if(l1.isChecked())
                        lym=lymph_box.getText().toString();
                    if(e1.isChecked())
                        ede=edema_box.getText().toString();
                    String insert_gynaec_general_physical_examination="'"+id.toString().trim()+"','"+ht.getText().toString().trim()+"','"+wt.getText().toString().trim()+"','"+bmi.getText().toString().trim()+"','"+temp.trim()+"','"+pul.getText().toString().trim()+"','"+bp.getText().toString().trim()+"','"+t.trim()+"','"+b.trim()+"','"+s.trim()+"','"+pal.trim()+"','"+ict.trim()+"','"+cya.trim()+"','"+clu.trim()+"','"+lym.trim()+"','"+ede.trim()+"','"+"No"+"','"+format.toString().trim()+"'";
                    System.out.println("InsertQuery:" + insert_gynaec_general_physical_examination);
                    //inserting into database
                    database.execSQL("INSERT INTO gynaec_physical_examination VALUES (" + insert_gynaec_general_physical_examination + ")");
                    Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_LONG).show();
                    if(selected == "server")
                    {
                        Intent intent=new Intent(getApplicationContext(),gynaec_systemic_examination.class);
                        intent.putExtra("type","obs");
                        intent.putExtra("JObj6",ob.toString());
                        startActivity(intent);
                    }
                    else {
                        Intent intent = new Intent(getApplicationContext(), gynaec_systemic_examination.class);
                        intent.putExtra("pid",pid);
                        startActivity(intent);
                    }

                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Please check the details", Toast.LENGTH_LONG).show();
                }
                database.close();
    }
    private boolean ValidationSuccess()
    {

        boolean check=true;
        StringBuilder errMsg = new StringBuilder("");

        return check;
    }
    public void click(View view)
    {
        thyroid_box.setVisibility(View.GONE);
    }

    public void click1(View view)
    {
        thyroid_box.setVisibility(View.VISIBLE);
    }

    public void click2(View view)
    {
        breasts_box.setVisibility(View.GONE);
    }

    public void click3(View view)
    {
        breasts_box.setVisibility(View.VISIBLE);
    }

    public void click4(View view)
    {
        spine_box.setVisibility(View.GONE);
    }

    public void click5(View view)
    {
        spine_box.setVisibility(View.VISIBLE);
    }

    public void click6(View view)
    {
        pallor_box.setVisibility(View.GONE);
    }

    public void click7(View view)
    {
        pallor_box.setVisibility(View.VISIBLE);
    }

    public void click8(View view)
    {
        icterus_box.setVisibility(View.GONE);
    }

    public void click9(View view)
    {
        icterus_box.setVisibility(View.VISIBLE);
    }

    public void click11(View view)
    {
        cyanosis_box.setVisibility(View.GONE);
    }

    public void click10(View view)
    {
        cyanosis_box.setVisibility(View.VISIBLE);
    }

    public void click12(View view)
    {
        clubbing_box.setVisibility(View.GONE);
    }

    public void click13(View view)
    {
        clubbing_box.setVisibility(View.VISIBLE);
    }

    public void click14(View view)
    {
        lymph_box.setVisibility(View.GONE);
    }

    public void click15(View view)
    {
        lymph_box.setVisibility(View.VISIBLE);
    }

    public void click16(View view)
    {
        edema_box.setVisibility(View.GONE);
    }

    public void click17(View view)
    {
        edema_box.setVisibility(View.VISIBLE);
    }

    public void onDisplay()
    {

        try {
            JSONObject obj1 = (JSONObject) ob.get("gynaec_physical_examination");
            ht.setText(obj1.getString("C1"));
            wt.setText(obj1.getString("C2"));
            bmi.setText(obj1.getString("C3"));
            pul.setText(obj1.getString("C5"));
            bp.setText(obj1.getString("C6"));
            if(obj1.getString("C4").equals(new String("Febrile")))
            {
                temp1.setChecked(true);
            }
            else
            {
                temp2.setChecked(true);
            }
            if(obj1.getString("C7").equals(new String("Normal")))
            {
                t1.setChecked(true);
            }
            else
            {
                t2.setChecked(true);
                thyroid_box.setText(obj1.getString("C7"));
                thyroid_box.setVisibility(View.VISIBLE);
            }
            if(obj1.getString("C8").equals(new String("Normal")))
            {
                b1.setChecked(true);
            }
            else
            {
                b2.setChecked(true);
                breasts_box.setText(obj1.getString("C8"));
                breasts_box.setVisibility(View.VISIBLE);
            }
            if(obj1.getString("C9").equals(new String("Normal")))
            {
                s1.setChecked(true);
            }
            else
            {
                s2.setChecked(true);
                spine_box.setText(obj1.getString("C9"));
                spine_box.setVisibility(View.VISIBLE);
            }
            if(obj1.getString("C10").equals(new String("Absent")))
            {
                p2.setChecked(true);
            }
            else
            {
                p1.setChecked(true);
                pallor_box.setText(obj1.getString("C10"));
                pallor_box.setVisibility(View.VISIBLE);
            }
            if(obj1.getString("C11").equals(new String("Absent")))
            {
                i2.setChecked(true);
            }
            else
            {
                i1.setChecked(true);
                icterus_box.setText(obj1.getString("C11"));
                icterus_box.setVisibility(View.VISIBLE);
            }
            if(obj1.getString("C12").equals(new String("Absent")))
            {
                c2.setChecked(true);
            }
            else
            {
                c1.setChecked(true);
                cyanosis_box.setText(obj1.getString("C12"));
                cyanosis_box.setVisibility(View.VISIBLE);
            }
            if(obj1.getString("C13").equals(new String("Absent")))
            {
                cl2.setChecked(true);
            }
            else
            {
                cl1.setChecked(true);
                clubbing_box.setText(obj1.getString("C13"));
                clubbing_box.setVisibility(View.VISIBLE);
            }
            if(obj1.getString("C14").equals(new String("Absent")))
            {
                l2.setChecked(true);
            }
            else
            {
                l1.setChecked(true);
                lymph_box.setText(obj1.getString("C14"));
                lymph_box.setVisibility(View.VISIBLE);
            }
            if(obj1.getString("C15").equals(new String("Absent")))
            {
                e2.setChecked(true);
            }
            else
            {
                e1.setChecked(true);
                edema_box.setText(obj1.getString("C15"));
                edema_box.setVisibility(View.VISIBLE);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void onDisplay2()
    {
        ht.setText(str[1]);
        wt.setText(str[2]);
        bmi.setText(str[3]);
        pul.setText(str[5]);
        bp.setText(str[6]);
        if(str[4].equals(new String("Febrile")))
        {
            temp1.setChecked(true);
        }
        else
        {
            temp2.setChecked(true);
        }
        if(str[7].equals(new String("Normal")))
        {
            t1.setChecked(true);
        }
        else
        {
            t2.setChecked(true);
            thyroid_box.setText(str[7]);
            thyroid_box.setVisibility(View.VISIBLE);
        }
        if(str[8].equals(new String("Normal")))
        {
            b1.setChecked(true);
        }
        else
        {
            b2.setChecked(true);
            breasts_box.setText(str[8]);
            breasts_box.setVisibility(View.VISIBLE);
        }
        if(str[9].equals(new String("Normal")))
        {
            s1.setChecked(true);
        }
        else
        {
            s2.setChecked(true);
            spine_box.setText(str[9]);
            spine_box.setVisibility(View.VISIBLE);
        }
        if(str[10].equals(new String("Absent")))
        {
            p2.setChecked(true);
        }
        else
        {
            p1.setChecked(true);
            pallor_box.setText(str[10]);
            pallor_box.setVisibility(View.VISIBLE);
        }
        if(str[11].equals(new String("Absent")))
        {
            i2.setChecked(true);
        }
        else
        {
            i1.setChecked(true);
            icterus_box.setText(str[11]);
            icterus_box.setVisibility(View.VISIBLE);
        }
        if(str[12].equals(new String("Absent")))
        {
            c2.setChecked(true);
        }
        else
        {
            c1.setChecked(true);
            cyanosis_box.setText(str[12]);
            cyanosis_box.setVisibility(View.VISIBLE);
        }
        if(str[13].equals(new String("Absent")))
        {
            cl2.setChecked(true);
        }
        else
        {
            cl1.setChecked(true);
            clubbing_box.setText(str[13]);
            clubbing_box.setVisibility(View.VISIBLE);
        }
        if(str[14].equals(new String("Absent")))
        {
            l2.setChecked(true);
        }
        else
        {
            l1.setChecked(true);
            lymph_box.setText(str[14]);
            lymph_box.setVisibility(View.VISIBLE);
        }
        if(str[15].equals(new String("Absent")))
        {
            e2.setChecked(true);
        }
        else
        {
            e1.setChecked(true);
            edema_box.setText(str[15]);
            edema_box.setVisibility(View.VISIBLE);
        }
    }

}
