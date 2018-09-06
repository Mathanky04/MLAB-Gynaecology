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

public class obs_systemic_examination extends AppCompatActivity {

    SQLiteDatabase database;
    String table_systemic_examination="patient_id TEXT ,cvs TEXT , rs TEXT , cns TEXT , uterine_size TEXT , presentation TEXT , fhs TEXT , liquor TEXT , previous_scar TEXT ,update_status TEXT DEFAULT \"No\", timestamp TEXT , primary key(patient_id), foreign key(patient_id) references general_information(patient_id)";
    EditText cvs_box, rs_box, cns_box, uterine_box,previous_scar_box;
    RadioButton cvs1,cvs2,rs1,rs2,cns1,cns2,p1,p2,p3,fhs1,fhs2,fhsp1,fhsp2,fhsp3,l1,l2,l3,ps1,ps2;
    RadioGroup rg1,rg2,rg3,rg4,rg5,rg6,rg7,rg11;
    Button btnNext;
    String cvs="Normal",rs="Normal",cns="Normal",fhs="Absent",prev_scar="Absent";
    String pres="",liq="";
    JSONObject ob;
    String[] str;
    String pid = "";

    @Override
    public void onBackPressed() { }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obs_systemic_examination);
        database = openOrCreateDatabase("gynaecology", Context.MODE_PRIVATE, null);
        database.execSQL("CREATE TABLE IF NOT EXISTS systemic_examination(" + table_systemic_examination + ")");
        cvs1=(RadioButton)findViewById(R.id.cvs_normal);
        cvs2=(RadioButton)findViewById(R.id.cvs_abnormal);
        rs1=(RadioButton)findViewById(R.id.rs_normal);
        rs2=(RadioButton)findViewById(R.id.rs_abnormal);
        cns1=(RadioButton)findViewById(R.id.cns_normal);
        cns2=(RadioButton)findViewById(R.id.cns_abnormal);
        p1=(RadioButton)findViewById(R.id.cephalic);
        p2=(RadioButton)findViewById(R.id.breech);
        p3=(RadioButton)findViewById(R.id.transverse);
        fhs1=(RadioButton)findViewById(R.id.fhs_plus);
        fhs2=(RadioButton)findViewById(R.id.fhs_minus);
        fhsp1=(RadioButton)findViewById(R.id.fhs_single);
        fhsp2=(RadioButton)findViewById(R.id.fhs_double);
        fhsp3=(RadioButton)findViewById(R.id.fhs_triple);
        l1=(RadioButton)findViewById(R.id.adequate);
        l2=(RadioButton)findViewById(R.id.reduced);
        l3=(RadioButton)findViewById(R.id.excess);
        ps1=(RadioButton)findViewById(R.id.previous_scar_present);
        ps2=(RadioButton)findViewById(R.id.previous_scar_absent);
        cvs_box=(EditText)findViewById(R.id.cvs_box);
        rs_box=(EditText)findViewById(R.id.rs_box);
        cns_box=(EditText)findViewById(R.id.cns_box);
        uterine_box=(EditText)findViewById(R.id.uterine_size);
        previous_scar_box=(EditText)findViewById(R.id.previous_scar_box);
        rg1=(RadioGroup)findViewById(R.id.cvs);
        rg2 = (RadioGroup)findViewById(R.id.rs);
        rg3 = (RadioGroup)findViewById(R.id.cns);
        rg4 = (RadioGroup)findViewById(R.id.presentation);
        rg5 = (RadioGroup)findViewById(R.id.fhs);
        rg6 = (RadioGroup)findViewById(R.id.liquor);
        rg7 = (RadioGroup)findViewById(R.id.previous_scar);
        rg11 = (RadioGroup)findViewById(R.id.fhs_positive);
        cns_box.setVisibility(View.GONE);
        cvs_box.setVisibility(View.GONE);
        rs_box.setVisibility(View.GONE);
        previous_scar_box.setVisibility(View.GONE);
        rg11.setVisibility(View.GONE);
        btnNext=(Button)findViewById(R.id.next_page8);
        if(retrieve) {
            if (selected == "server") {
                try {
                    Bundle b = getIntent().getExtras();
                    System.out.println("Entered");
                    //JSONArray arr = new JSONArray(new String(responseBody));
                    ob = new JSONObject(b.getString("JObj6"));
                    System.out.println(ob);
                    System.out.println("Retreived::" + "Here");
                    JSONObject obj = (JSONObject) ob.get("systemic_examination");
                    pid = (String) obj.get("C0");
                    System.out.println(pid);
                    onDisplay();
                    Toast.makeText(getApplicationContext(), "Retreived!", Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Bundle b = getIntent().getExtras();
                pid = b.getString("pid");
                String selectQuery = "SELECT * FROM systemic_examination WHERE patient_id ='" + pid + "';";
                Cursor cursor = database.rawQuery(selectQuery, null);
                if (cursor.moveToFirst()) {//cursor.moveToNext();
                    int totalColumn = cursor.getColumnCount();
                    do {
                        str = new String[totalColumn];
                        for (int i = 0; i < totalColumn; i++) {
                            str[i] = cursor.getString(i);
                        }
                    } while (cursor.moveToNext());
                }
                onDisplay2();
                //System.out.println(cursor.getString(0));
                String deleteQuery = "DELETE FROM systemic_examination where patient_id = " + "'" + pid + "'";
                Log.d("query", deleteQuery);
                database.execSQL(deleteQuery);
            }
        }
        //onButton();
    }
    private boolean ValidationSuccess(){

        boolean check=true;
        StringBuilder errMsg = new StringBuilder("");
        if (uterine_box.getText().toString().equalsIgnoreCase("")){
            uterine_box.setError("Please enter a value");
            check=false;
        }

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


        if(cvs2.isChecked())
        {
            if (cvs_box.getText().toString().equalsIgnoreCase("")){
                cvs_box.setError("Please enter a value");
                check=false;
            }
        }

        if(rs2.isChecked())
        {
            if (rs_box.getText().toString().equalsIgnoreCase("")){
                rs_box.setError("Please enter a value");
                check=false;
            }
        }

        if(cns2.isChecked())
        {
            if (cns_box.getText().toString().equalsIgnoreCase("")){
                cns_box.setError("Please enter a value");
                check=false;
            }
        }


        if(fhs1.isChecked())
        {
            if (rg11.getCheckedRadioButtonId() == -1)
            {
                errMsg.append("Please select on option\n");
                check=false;
            }
        }


        if(ps1.isChecked())
        {
            if (previous_scar_box.getText().toString().equalsIgnoreCase("")){
                previous_scar_box.setError("Please enter a value");
                check=false;
            }
        }
        return check;
    }

    public void onProceed(View view)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
        final String format = simpleDateFormat.format(new Date());
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ValidationSuccess())
                {
                    if(cvs2.isChecked())
                        cvs=cvs_box.getText().toString();
                    if(rs2.isChecked())
                        rs=rs_box.getText().toString();
                    if(cns2.isChecked())
                        cns=cns_box.getText().toString();
                    if(ps1.isChecked())
                        prev_scar=previous_scar_box.getText().toString();
                    if(p1.isChecked())
                        pres="Cephalic";
                    else if(p2.isChecked())
                        pres="Breech";
                    else if(p3.isChecked())
                        pres="Transverse";
                    if(l1.isChecked())
                        liq="Adequate";
                    else if(l2.isChecked())
                        liq="Reduced";
                    else if(l3.isChecked())
                        liq="Excess";
                    if(fhs1.isChecked())
                    {
                        if(fhsp1.isChecked())
                            fhs="+";
                        else if(fhsp2.isChecked())
                            fhs="++";
                        else if(fhsp3.isChecked())
                            fhs="+++";
                    }
                    String insert_systemic_examination="'"+id.toString().trim()+"','"+cvs.trim()+"','"+rs.trim()+"','"+cns.trim()+"','"+uterine_box.getText().toString().trim()+"','"+pres.trim()+"','"+fhs.trim()+"','"+liq.trim()+"','"+prev_scar.trim()+"',"+ "'" + "No" + "',"  + "'" + format.toString().trim() + "'";
                    System.out.println("InsertQuery:" + insert_systemic_examination);
                    //inserting into database
                    database.execSQL("INSERT INTO systemic_examination VALUES (" + insert_systemic_examination + ")");
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

                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Please check the details", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void click(View view)
    {
        cvs_box.setVisibility(View.VISIBLE);
    }

    public void click1(View view)
    {
        cvs_box.setVisibility(View.GONE);
    }

    public void click2(View view)
    {
        rs_box.setVisibility(View.VISIBLE);
    }

    public void click3(View view)
    {
        rs_box.setVisibility(View.GONE);
    }

    public void click4(View view)
    {
        cns_box.setVisibility(View.VISIBLE);
    }

    public void click5(View view)
    {
        cns_box.setVisibility(View.GONE);
    }

    public void click8(View view)
    {
        previous_scar_box.setVisibility(View.VISIBLE);
    }

    public void click9(View view)
    {
        previous_scar_box.setVisibility(View.GONE);
    }

    public void click6(View view)
    {
        rg11 = (RadioGroup)findViewById(R.id.fhs_positive);
        rg11.setVisibility(View.VISIBLE);
    }

    public void click7(View view)
    {
        rg11 = (RadioGroup)findViewById(R.id.fhs_positive);
        rg11.setVisibility(View.GONE);
    }

    public void onDisplay()
    {
        try {
            JSONObject obj1 = (JSONObject) ob.get("systemic_examination");
            if(obj1.getString("C1").equals(new String("Normal")))
            {
                cvs1.setChecked(true);
            }
            else
            {
                cvs2.setChecked(true);
                cvs_box.setText(obj1.getString("C1"));
                cvs_box.setVisibility(View.VISIBLE);
            }
            if(obj1.getString("C2").equals(new String("Normal")))
            {
                rs1.setChecked(true);
            }
            else
            {
                rs2.setChecked(true);
                rs_box.setVisibility(View.VISIBLE);
                rs_box.setText(obj1.getString("C2"));
            }
            if(obj1.getString("C3").equals(new String("Normal")))
            {
                cns1.setChecked(true);
            }
            else
            {
                cns2.setChecked(true);
                cns_box.setText(obj1.getString("C3"));
                cns_box.setVisibility(View.VISIBLE);
            }
            uterine_box.setText(obj1.getString("C4"));
            if(obj1.getString("C5").equals(new String("Cephalic")))
            {
                p1.setChecked(true);
            }
            else if(obj1.getString("C5").equals(new String("Breech")))
            {
                p2.setChecked(true);
            }
            else
            {
                p3.setChecked(true);
            }
            if(obj1.getString("C6").equals(new String("+")))
            {
                fhs1.setChecked(true);
                rg5.setVisibility(View.VISIBLE);
                fhsp1.setChecked(true);
            }
            else if(obj1.getString("C6").equals(new String("++")))
            {
                fhs1.setChecked(true);
                rg5.setVisibility(View.VISIBLE);
                fhsp2.setChecked(true);
            }
            else if(obj1.getString("C6").equals(new String("+++")))
            {
                fhs1.setChecked(true);
                rg5.setVisibility(View.VISIBLE);
                fhsp3.setChecked(true);
            }
            else
            {
                fhs2.setChecked(true);
            }
            if(obj1.getString("C7").equals(new String("Adequate")))
            {
                l1.setChecked(true);
            }
            else if(obj1.getString("C7").equals(new String("Reduced")))
            {
                l2.setChecked(true);
            }
            else
            {
                l3.setChecked(true);
            }
            if(obj1.getString("C8").equals(new String("Present")))
            {
                ps1.setChecked(true);
                previous_scar_box.setText(obj1.getString("C8"));
                previous_scar_box.setVisibility(View.VISIBLE);
            }
            else
            {
                ps2.setChecked(true);
            }

        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onDisplay2()
    {
            if(str[1].equals(new String("Normal")))
            {
                cvs1.setChecked(true);
            }
            else
            {
                cvs2.setChecked(true);
                cvs_box.setText(str[1]);
                cvs_box.setVisibility(View.VISIBLE);
            }
            if(str[2].equals(new String("Normal")))
            {
                rs1.setChecked(true);
            }
            else
            {
                rs2.setChecked(true);
                rs_box.setVisibility(View.VISIBLE);
                rs_box.setText(str[2]);
            }
            if(str[3].equals(new String("Normal")))
            {
                cns1.setChecked(true);
            }
            else
            {
                cns2.setChecked(true);
                cns_box.setText(str[3]);
                cns_box.setVisibility(View.VISIBLE);
            }
            uterine_box.setText(str[4]);
            if(str[5].equals(new String("Cephalic")))
            {
                p1.setChecked(true);
            }
            else if(str[5].equals(new String("Breech")))
            {
                p2.setChecked(true);
            }
            else
            {
                p3.setChecked(true);
            }
            if(str[6].equals(new String("+")))
            {
                fhs1.setChecked(true);
                rg5.setVisibility(View.VISIBLE);
                fhsp1.setChecked(true);
            }
            else if(str[6].equals(new String("++")))
            {
                fhs1.setChecked(true);
                rg5.setVisibility(View.VISIBLE);
                fhsp2.setChecked(true);
            }
            else if(str[6].equals(new String("+++")))
            {
                fhs1.setChecked(true);
                rg5.setVisibility(View.VISIBLE);
                fhsp3.setChecked(true);
            }
            else
            {
                fhs2.setChecked(true);
            }
            if(str[7].equals(new String("Adequate")))
            {
                l1.setChecked(true);
            }
            else if(str[7].equals(new String("Reduced")))
            {
                l2.setChecked(true);
            }
            else
            {
                l3.setChecked(true);
            }
            if(str[8].equals(new String("Present")))
            {
                ps1.setChecked(true);
                previous_scar_box.setText(str[8]);
                previous_scar_box.setVisibility(View.VISIBLE);
            }
            else
            {
                ps2.setChecked(true);
            }
    }

}
