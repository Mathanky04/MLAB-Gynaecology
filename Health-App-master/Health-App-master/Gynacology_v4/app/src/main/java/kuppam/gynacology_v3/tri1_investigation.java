package kuppam.gynacology_v3;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
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

public class tri1_investigation extends AppCompatActivity {

    SQLiteDatabase database;
    JSONObject ob;
    String[] str;
    String[] str1;
    String pid = "";
    EditText e4;
    EditText drug_box, other_box, hb,rbs;
    RadioGroup rg1, rg2, rg3, rg4, rg5, rg6, rg7, rg8;
    RadioButton selectedRadioButton,b1,b2,b3,b4,b5,b6,b7,b8,a1,a2,s1,s2,m1,m2,v1,v2,h1,h2,hbs1,hbs2,selectedRadioButton1;
    CheckBox hyperemesis, micturition, PIV, radiation, fever, folic, drug, other;
    String h = "No",bm = "No",bpsp = "No",re = "No",fwr = "No",
            fai = "No",odi = "No",o1 = "No",sc = "";
    String trimesterNo="Trimester-1",bgrt="",a="",s="",m="",vdrl="",hiv="",hbsag="";

    String table_query1 = "patient_id TEXT NOT NULL," +
            " hyperemesis TEXT DEFAULT \"No\", " +
            " burning_micturition TEXT DEFAULT \"No\"," +
            " bleeding_piv_spotting_piv TEXT DEFAULT \"No\"," +
            " radiation_exposure TEXT DEFAULT \"No\"," +
            " fever_with_rash TEXT DEFAULT \"No\"," +
            " folic_acid_intake TEXT DEFAULT \"No\"," +
            " other_drug_intake TEXT DEFAULT \"No\"," +
            " others1 TEXT DEFAULT \"No\"," +
            " scan DEFAULT \"No\","+
            " update_status TEXT DEFAULT \"No\"," +
            " timestamp TEXT NOT NULL," +
            " primary key(patient_id)," +
            " foreign key(patient_id) references general_information(patient_id)";

    String table_query2 = "patient_id TEXT NOT NULL," +
            "trimester_number TEXT NOT NULL," +
            " hb TEXT NOT NULL," +
            " blood_grouping_rh_typing TEXT NOT NULL," +
            " albumin TEXT NOT NULL," +
            " sugar TEXT NOT NULL," +
            " microscopy TEXT NOT NULL," +
            " vdrl TEXT NOT NULL," +
            " hiv_status TEXT NOT NULL," +
            " hbsag TEXT NOT NULL," +
            " rbs TEXT NOT NULL," +
            " others4 TEXT DEFAULT \"No\"," +
            " update_status TEXT DEFAULT \"No\"," +
            " timestamp TEXT NOT NULL," +
            " primary key(patient_id, trimester_number)," +
            " foreign key(patient_id) references general_information(patient_id)";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tri1_investigation);

        hyperemesis = (CheckBox) findViewById(R.id.hyperemesis);
        micturition = (CheckBox) findViewById(R.id.micturition);
        PIV = (CheckBox) findViewById(R.id.PIV);
        radiation = (CheckBox) findViewById(R.id.radiation);
        fever = (CheckBox) findViewById(R.id.fever);
        folic = (CheckBox) findViewById(R.id.folic);

        drug = (CheckBox) findViewById(R.id.drug);
        drug_box = (EditText)findViewById(R.id.drug_box);

        other = (CheckBox) findViewById(R.id.other);
        other_box = (EditText)findViewById(R.id.other_box);
        drug_box.setVisibility(View.GONE);
        other_box.setVisibility(View.GONE);
        hyperemesis.setVisibility(View.GONE);
        micturition.setVisibility(View.GONE);
        PIV.setVisibility(View.GONE);
        drug_box.setVisibility(View.GONE);
        other_box.setVisibility(View.GONE);
        radiation.setVisibility(View.GONE);
        fever.setVisibility(View.GONE);
        folic.setVisibility(View.GONE);
        drug.setVisibility(View.GONE);
        other.setVisibility(View.GONE);
        selectedRadioButton = (RadioButton)findViewById(R.id.first_trimester_complaints_yes);
        selectedRadioButton1 = (RadioButton)findViewById(R.id.first_trimester_complaints_no);
        hb = (EditText)findViewById(R.id.hb) ;
        rg2 = (RadioGroup)findViewById(R.id.albumin);
        rg3 = (RadioGroup)findViewById(R.id.sugar);
        rg4 = (RadioGroup)findViewById(R.id.microscopy);
        rg5 = (RadioGroup)findViewById(R.id.vdrl);
        rg6 = (RadioGroup)findViewById(R.id.hiv);
        rg7 = (RadioGroup)findViewById(R.id.hbsag);
        rg8 = (RadioGroup)findViewById(R.id.first_blood_group);
        rbs = (EditText)findViewById(R.id.rbs);
        e4=(EditText)findViewById(R.id.first_trimester_others);
        b1 = (RadioButton)findViewById(R.id.first_blood_group_A_plus);
        b2 = (RadioButton)findViewById(R.id.first_blood_group_A_minus);
        b3 = (RadioButton)findViewById(R.id.first_blood_group_B_plus);
        b4 = (RadioButton)findViewById(R.id.first_blood_group_B_minus);
        b5 = (RadioButton)findViewById(R.id.first_blood_group_O_plus);
        b6 = (RadioButton)findViewById(R.id.first_blood_group_O_minus);
        b7 = (RadioButton)findViewById(R.id.first_blood_group_AB_plus);
        b8 = (RadioButton)findViewById(R.id.first_blood_group_AB_minus);
        a1=(RadioButton)findViewById(R.id.albumin_normal);
        a2=(RadioButton)findViewById(R.id.albumin_abnormal);
        s1=(RadioButton)findViewById(R.id.sugar_normal);
        s2=(RadioButton)findViewById(R.id.sugar_abnormal);
        m1=(RadioButton)findViewById(R.id.microscopy_normal);
        m2=(RadioButton)findViewById(R.id.microscopy_abnormal);
        v1=(RadioButton)findViewById(R.id.vdrl_reactive);
        v2=(RadioButton)findViewById(R.id.vdrl_non_reactive);
        h1=(RadioButton)findViewById(R.id.hiv_reactive);
        h2=(RadioButton)findViewById(R.id.hiv_non_reactive);
        hbs1=(RadioButton)findViewById(R.id.hbsag_reactive);
        hbs2=(RadioButton)findViewById(R.id.hbsag_non_reactive);
        rbs=(EditText)findViewById(R.id.rbs);
        //opening db
        database = openOrCreateDatabase("gynaecology", Context.MODE_PRIVATE, null);

        //creating table if doesn't exist
        database.execSQL("CREATE TABLE IF NOT EXISTS first_trimester(" + table_query1 + ")");
        database.execSQL("CREATE TABLE IF NOT EXISTS investigations(" + table_query2 + ")");
        if(retrieve) {
            if (selected == "server") {
                try {
                    Bundle b = getIntent().getExtras();
                    System.out.println("Entered");
                    //JSONArray arr = new JSONArray(new String(responseBody));
                    ob = new JSONObject(b.getString("JObj2"));
                    System.out.println(ob);
                    System.out.println("Retreived::" + "Here");
                    JSONObject obj = (JSONObject) ob.get("first_trimester");
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
                String selectQuery = "SELECT * FROM first_trimester WHERE patient_id ='" + pid + "';";
                String selectQuery1 = "SELECT * FROM investigations WHERE patient_id ='" + pid + "';";
                Cursor cursor = database.rawQuery(selectQuery, null);
                Cursor c = database.rawQuery(selectQuery1, null);
                if (cursor.moveToFirst()) {//cursor.moveToNext();
                    int totalColumn = cursor.getColumnCount();
                    do {
                        str = new String[totalColumn];
                        for (int i = 0; i < totalColumn; i++) {
                            str[i] = cursor.getString(i);
                        }
                    } while (cursor.moveToNext());
                }
                if (c.moveToFirst()) {
                    int totalColumn2 = c.getColumnCount();
                    do {
                        str1 = new String[totalColumn2];
                        for (int i = 0; i < totalColumn2; i++) {
                            str1[i] = c.getString(i);
                            System.out.println(c.getString(i));
                        }
                    } while (c.moveToNext());
                }
                onDisplay2();
                String deleteQuery = "DELETE FROM first_trimester WHERE patient_id = " + "'" + pid + "'";
                Log.d("query", deleteQuery);
                database.execSQL(deleteQuery);
                String deleteQuery1 = "DELETE FROM investigations WHERE patient_id = " + "'" + pid + "'";
                Log.d("query", deleteQuery1);
                database.execSQL(deleteQuery1);
            }
        }

    }

    @Override
    public void onBackPressed() { }

    public void onProceed(View view){
        onclickAssign2();
        if(ValidationSuccess()){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
            String format = simpleDateFormat.format(new Date());
            String insert_query1 = "'" + id.toString().trim() + "'," +
                    "'" + h + "'," +
                    "'" + bm + "'," +
                    "'" + bpsp + "'," +
                    "'" + re + "'," +
                    "'" + fwr + "'," +
                    "'" + fai + "'," +
                    "'" + odi + "'," +
                    "'" + o1 + "'," +
                    "'" + sc + "'," +
                    "'" + "No" + "'," +
                    "'" + format.trim() + "'";
            String insert_query2 = "'" + id.toString().trim() + "'," +
                    "'" + trimesterNo + "'," +
                    "'" + hb.getText().toString().trim() + "'," +
                    "'" + bgrt + "'," +
                    "'" + a + "'," +
                    "'" + s + "'," +
                    "'" + m + "'," +
                    "'" + vdrl + "'," +
                    "'" + hiv + "'," +
                    "'" + hbsag + "'," +
                    "'" + rbs.getText().toString().trim() + "'," +
                    "'" + e4.getText().toString().trim() + "'," +
                    "'" + "No" + "'," +
                    "'" + format.trim() + "'";
            System.out.println("InsertQuery:" + insert_query1);
            System.out.println("InsertQuery:" + insert_query2);
            //inserting into database
            database.execSQL("INSERT INTO first_trimester VALUES (" + insert_query1 + ")");
            database.execSQL("INSERT INTO investigations VALUES (" + insert_query2 + ")");
            Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_LONG).show();
            if(selected == "server")
            {
                Intent intent=new Intent(getApplicationContext(),obstetric_score_past_history.class);
                intent.putExtra("type","obs");
                intent.putExtra("JObj3",ob.toString());
                startActivity(intent);
            }
            else {
                Intent intent = new Intent(getApplicationContext(), obstetric_score_past_history.class);
                intent.putExtra("pid",pid);
                startActivity(intent);
            }
        }else{
            Toast.makeText(getApplicationContext(), "Please check the details", Toast.LENGTH_LONG).show();
        }
    }

    private boolean ValidationSuccess(){
        boolean check=true;
        StringBuilder errMsg = new StringBuilder("");

        hb = (EditText)findViewById(R.id.hb) ;
        rg2 = (RadioGroup)findViewById(R.id.albumin);
        rg3 = (RadioGroup)findViewById(R.id.sugar);
        rg4 = (RadioGroup)findViewById(R.id.microscopy);
        rg5 = (RadioGroup)findViewById(R.id.vdrl);
        rg6 = (RadioGroup)findViewById(R.id.hiv);
        rg7 = (RadioGroup)findViewById(R.id.hbsag);
        rg8 = (RadioGroup)findViewById(R.id.first_blood_group);
        rbs = (EditText)findViewById(R.id.rbs);

        rg1=(RadioGroup)findViewById(R.id.first_trimester_complaints);
        selectedRadioButton = (RadioButton)findViewById(R.id.first_trimester_complaints_yes);


        if (hb.getText().toString().equalsIgnoreCase("")){
            hb.setError("Please enter a value");
            check=false;
        }

        if (Integer.parseInt(hb.getText().toString()) > 100) {
            hb.setError("Exceeds limit! Please enter a valid percentage");
            return false;
        }

        if (rbs.getText().toString().equalsIgnoreCase("")){
            hb.setError("Please enter a value");
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

        if (rg8.getCheckedRadioButtonId() == -1)
        {
            errMsg.append("Please select on option\n");
            check=false;
        }

        if(selectedRadioButton.isChecked())
        {
            hyperemesis = (CheckBox) findViewById(R.id.hyperemesis);
            micturition = (CheckBox) findViewById(R.id.micturition);
            PIV = (CheckBox) findViewById(R.id.PIV);
            radiation = (CheckBox) findViewById(R.id.radiation);
            fever = (CheckBox) findViewById(R.id.fever);
            folic = (CheckBox) findViewById(R.id.folic);

            drug = (CheckBox) findViewById(R.id.drug);
            drug_box = (EditText)findViewById(R.id.drug_box);

            other = (CheckBox) findViewById(R.id.other);
            other_box = (EditText)findViewById(R.id.other_box);

            if (!(hyperemesis.isChecked() || micturition.isChecked() || PIV.isChecked() || radiation.isChecked() || fever.isChecked() || folic.isChecked() || drug.isChecked() || other.isChecked())){
                hyperemesis.setError("Please select an option");
                check=false;
            }

            else if(drug.isChecked()&& drug_box.getText().toString().equalsIgnoreCase("")){
                drug.setError("Please enter  a value");
                check=false;
            }

            else if(other.isChecked()&& other_box.getText().toString().equalsIgnoreCase("")){
                drug.setError("Please enter a value");
                check=false;
            }

        }
        return check;
    }

    public void click(View view)
    {
        hyperemesis = (CheckBox) findViewById(R.id.hyperemesis);
        micturition = (CheckBox) findViewById(R.id.micturition);
        PIV = (CheckBox) findViewById(R.id.PIV);
        radiation = (CheckBox) findViewById(R.id.radiation);
        fever = (CheckBox) findViewById(R.id.fever);
        folic = (CheckBox) findViewById(R.id.folic);
        drug = (CheckBox) findViewById(R.id.drug);
        other = (CheckBox) findViewById(R.id.other);
        hyperemesis.setVisibility(View.VISIBLE);
        micturition.setVisibility(View.VISIBLE);
        PIV.setVisibility(View.VISIBLE);
        radiation.setVisibility(View.VISIBLE);
        fever.setVisibility(View.VISIBLE);
        folic.setVisibility(View.VISIBLE);
        drug.setVisibility(View.VISIBLE);
        other.setVisibility(View.VISIBLE);

    }

    public void click2(View view)
    {
        drug_box = (EditText)findViewById(R.id.drug_box);
        drug_box.setVisibility(View.VISIBLE);

    }

    public void click3(View view)
    {
        other_box = (EditText)findViewById(R.id.other_box);
        other_box.setVisibility(View.VISIBLE);

    }
    public void click1(View view)
    {
        hyperemesis = (CheckBox) findViewById(R.id.hyperemesis);
        micturition = (CheckBox) findViewById(R.id.micturition);
        PIV = (CheckBox) findViewById(R.id.PIV);
        radiation = (CheckBox) findViewById(R.id.radiation);
        fever = (CheckBox) findViewById(R.id.fever);
        folic = (CheckBox) findViewById(R.id.folic);

        drug = (CheckBox) findViewById(R.id.drug);
        drug_box = (EditText)findViewById(R.id.drug_box);

        other = (CheckBox) findViewById(R.id.other);
        other_box = (EditText)findViewById(R.id.other_box);

        drug_box.setVisibility(View.GONE);
        other_box.setVisibility(View.GONE);

        hyperemesis.setVisibility(View.GONE);
        micturition.setVisibility(View.GONE);
        PIV.setVisibility(View.GONE);
        radiation.setVisibility(View.GONE);
        fever.setVisibility(View.GONE);
        folic.setVisibility(View.GONE);
        drug.setVisibility(View.GONE);
        other.setVisibility(View.GONE);

    }

    public void onclickAssign(View view)
    {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.first_blood_group_A_plus:
                if (checked)
                    bgrt="A+";
                break;
            case R.id.first_blood_group_A_minus:
                if (checked)
                    bgrt="A-";
                break;
            case R.id.first_blood_group_B_plus:
                if (checked)
                    bgrt="B+";
                break;
            case R.id.first_blood_group_B_minus:
                if (checked)
                    bgrt="B-";
                break;
            case R.id.first_blood_group_O_plus:
                if (checked)
                    bgrt="O+";
                break;
            case R.id.first_blood_group_O_minus:
                if (checked)
                    bgrt="O-";
                break;
            case R.id.first_blood_group_AB_plus:
                if (checked)
                    bgrt="AB+";
                break;
            case R.id.first_blood_group_AB_minus:
                if (checked)
                    bgrt="AB-";
                break;
        }
    }

    public void onclickAssign2()
    {
        hyperemesis = (CheckBox) findViewById(R.id.hyperemesis);
        micturition = (CheckBox) findViewById(R.id.micturition);
        PIV = (CheckBox) findViewById(R.id.PIV);
        radiation = (CheckBox) findViewById(R.id.radiation);
        fever = (CheckBox) findViewById(R.id.fever);
        folic = (CheckBox) findViewById(R.id.folic);
        drug = (CheckBox) findViewById(R.id.drug);
        drug_box = (EditText)findViewById(R.id.drug_box);
        other = (CheckBox) findViewById(R.id.other);
        if(selectedRadioButton.isChecked()) {
            if (hyperemesis.isChecked())
            {
                h = "Yes";
            }
            else
            {
                h="No";
            }
            if (micturition.isChecked()) {
                bm = "Yes";
            }
            else
            {
                bm="No";
            }
            if (PIV.isChecked()) {
                bpsp = "Yes";
            }
            else
            {
                bpsp="No";
            }
            if (radiation.isChecked()) {
                re = "Yes";
            }
            else
            {
                re="No";
            }
            if (fever.isChecked()) {
                fwr = "Yes";
            }
            else
            {
                fwr="No";
            }
            if (folic.isChecked()) {
                fai = "Yes";
            }
            else
            {
                fai="No";
            }
            if (drug.isChecked()) {
                odi = drug_box.getText().toString();
            }
            else
            {
                odi="No";
            }
            if (other.isChecked()) {
                o1 = other_box.getText().toString();
            }
            else {
                o1 = "No";
            }
        }
    }

    public void onclickAssign3(View view)
    {
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.albumin_normal:
                if(checked)
                {
                    a="Normal";
                }
                break;
            case R.id.sugar_normal:
                if(checked)
                {
                    s="Normal";
                }
                break;
            case R.id.microscopy_normal:
                if(checked)
                {
                    m="Normal";
                }
                break;
            case R.id.vdrl_reactive:
                if(checked)
                {
                    vdrl="Reactive";
                }
                break;
            case R.id.hiv_reactive:
                if(checked)
                {
                    hiv="Reactive";
                }
                break;
            case R.id.hbsag_reactive:
                if(checked)
                {
                    hbsag="Reactive";
                }
                break;
            case R.id.albumin_abnormal:
                if(checked)
                {
                    a="Abnormal";
                }
                break;
            case R.id.sugar_abnormal:
                if(checked)
                {
                    s="Abnormal";
                }
                break;
            case R.id.microscopy_abnormal:
                if(checked)
                {
                    m="Abnormal";
                }
                break;
            case R.id.vdrl_non_reactive:
                if(checked)
                {
                    vdrl="Non Reactive";
                }
                break;
            case R.id.hiv_non_reactive:
                if(checked)
                {
                    hiv="Non Reactive";
                }
                break;
            case R.id.hbsag_non_reactive:
                if(checked)
                {
                    hbsag="Non Reactive";
                }
                break;
        }
    }
    public void onDisplay()
    {
        try {
            JSONObject obj1 = (JSONObject) ob.get("first_trimester");
            if ((obj1.getString("C1").equals(new String("No")) && obj1.getString("C2").equals(new String("No")) &&
                    obj1.getString("C3").equals(new String("No")) && obj1.getString("C4").equals(new String("No")) &&
                    obj1.getString("C5").equals(new String("No")) && obj1.getString("C6").equals(new String("No")) &&
                    obj1.getString("C7").equals(new String("No")) && obj1.getString("C8").equals(new String("No"))))
            {
                selectedRadioButton1.setChecked(true);
                System.out.println("Here in none");
            }
            else
            {
                selectedRadioButton.setChecked(true);
                System.out.println("Here in some");
                hyperemesis.setVisibility(View.VISIBLE);
                if(!(obj1.getString("C1").equals(new String("No"))))
                {
                    hyperemesis.setChecked(true);
                    h = "Yes";
                }
                micturition.setVisibility(View.VISIBLE);
                if(!(obj1.getString("C2").equals(new String("No"))))
                {
                    micturition.setChecked(true);
                    bm = "Yes";
                }
                PIV.setVisibility(View.VISIBLE);
                if(!(obj1.getString("C3").equals(new String("No"))))
                {
                    PIV.setChecked(true);
                    bpsp = "Yes";
                }
                radiation.setVisibility(View.VISIBLE);
                if(!(obj1.getString("C4").equals(new String("No"))))
                {
                    radiation.setChecked(true);
                    re = "Yes";
                }
                fever.setVisibility(View.VISIBLE);
                if(!(obj1.getString("C5").equals(new String("No"))))
                {
                    fever.setChecked(true);
                    fwr = "Yes";
                }
                folic.setVisibility(View.VISIBLE);
                if(!(obj1.getString("C6").equals(new String("No"))))
                {
                    folic.setChecked(true);
                    fai = "Yes";
                }
                drug.setVisibility(View.VISIBLE);
                if(!(obj1.getString("C7").equals(new String("No"))))
                {
                    drug.setChecked(true);
                    drug_box.setVisibility(View.VISIBLE);
                    drug_box.setText(obj1.getString("C7"));
                }
                other.setVisibility(View.VISIBLE);
                if(!(obj1.getString("C8").equals(new String("No"))))
                {
                    other.setChecked(true);
                    other_box.setVisibility(View.VISIBLE);
                    other_box.setText(obj1.getString("C8"));
                }
            }
            JSONObject obj2 = (JSONObject) ob.get("investigations");
            if(obj2.getString("C1").equals(new String("Trimester-1")))
            {
                hb.setText(obj2.getString("C2"));
                switch(obj2.getString("C3")) {
                    case "A+":
                        b1.setChecked(true);
                        bgrt = "A+";
                        break;
                    case"A-":
                        b2.setChecked(true);
                        bgrt = "B-";
                        break;
                    case"B+":
                        b3.setChecked(true);
                        bgrt = "B+";
                        break;
                    case"B-":
                        b4.setChecked(true);
                        bgrt = "B-";
                        break;
                    case"O+":
                        b5.setChecked(true);
                        bgrt = "O+";
                        break;
                    case"O-":
                        b6.setChecked(true);
                        bgrt = "O-";
                        break;
                    case"AB+":
                        b7.setChecked(true);
                        bgrt = "AB+";
                        break;
                    case"AB-":
                        b8.setChecked(true);
                        bgrt = "AB-";
                        break;
                }
                if(obj2.getString("C4").equals(new String("Normal")))
                {
                    a1.setChecked(true);
                    a = "Normal";
                }
                else
                {
                    a2.setChecked(true);
                    a = "Abnormal";
                }
                if(obj2.getString("C5").equals(new String("Normal")))
                {
                    s1.setChecked(true);
                    s = "Normal";
                }
                else
                {
                    s2.setChecked(true);
                    s = "Abnormal";
                }
                if(obj2.getString("C6").equals(new String("Normal")))
                {
                    m1.setChecked(true);
                    m = "Normal";
                }
                else
                {
                    m2.setChecked(true);
                    m = "Abnormal";
                }
                if(obj2.getString("C7").equals(new String("Reactive")))
                {
                    v1.setChecked(true);
                    vdrl = "Reactive";
                }
                else
                {
                    v2.setChecked(true);
                    vdrl = "Non Reactive";
                }
                if(obj2.getString("C8").equals(new String("Reactive")))
                {
                    h1.setChecked(true);
                    hiv = "Reactive";
                }
                else
                {
                    h2.setChecked(true);
                    hiv = "Non Reactive";
                }
                if(obj2.getString("C9").equals(new String("Reactive")))
                {
                    hbs1.setChecked(true);
                    hbsag = "Reactive";
                }
                else
                {
                    hbs2.setChecked(true);
                    hbsag = "Non Reactive";
                }
                rbs.setText(obj2.getString("C10"));
                e4.setText(obj2.getString("C11"));
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void onDisplay2()
    {
        if ((str[1].equals(new String("No")) && str[2].equals(new String("No")) &&
                str[3].equals(new String("No")) && str[4].equals(new String("No")) &&
                str[5].equals(new String("No")) && str[6].equals(new String("No")) &&
                str[7].equals(new String("No")) && str[8].equals(new String("No"))))
        {
            selectedRadioButton1.setChecked(true);
        }
        else
        {
            selectedRadioButton.setChecked(true);
            hyperemesis.setVisibility(View.VISIBLE);
            if(!(str[1].equals(new String("No"))))
            {
                hyperemesis.setChecked(true);
                h = "Yes";
            }
            micturition.setVisibility(View.VISIBLE);
            if(!(str[2].equals(new String("No"))))
            {
                micturition.setChecked(true);
                bm = "Yes";
            }
            PIV.setVisibility(View.VISIBLE);
            if(!(str[3].equals(new String("No"))))
            {
                PIV.setChecked(true);
                 bpsp= "Yes";
            }
            radiation.setVisibility(View.VISIBLE);
            if(!(str[4].equals(new String("No"))))
            {
                radiation.setChecked(true);
                re = "Yes";
            }
            fever.setVisibility(View.VISIBLE);
            if(!(str[5].equals(new String("No"))))
            {
                fever.setChecked(true);
                fwr = "Yes";
            }
            folic.setVisibility(View.VISIBLE);
            if(!(str[6].equals(new String("No"))))
            {
                folic.setChecked(true);
                fai = "Yes";
            }
            drug.setVisibility(View.VISIBLE);
            if(!( str[7].equals(new String("No"))))
            {
                drug.setChecked(true);
                drug_box.setText(str[7]);
            }
            other.setVisibility(View.VISIBLE);
            if(!(str[8].equals(new String("No"))))
            {
                other.setChecked(true);
                other_box.setText(str[8]);
            }
        }
        if(str1[1].equals(new String("Trimester-1")))
        {
            hb.setText(str1[2]);
            System.out.println("Blood::"+str1[3]);
            switch(str1[3]) {
                case "A+":
                    b1.setChecked(true);
                    bgrt = "A+";
                    break;
                case"A-":
                    b2.setChecked(true);
                    bgrt = "B-";
                    break;
                case"B+":
                    b3.setChecked(true);
                    bgrt = "B+";
                    break;
                case"B-":
                    b4.setChecked(true);
                    bgrt = "B-";
                    break;
                case"O+":
                    b5.setChecked(true);
                    bgrt = "O+";
                    break;
                case"O-":
                    b6.setChecked(true);
                    bgrt = "O-";
                    break;
                case"AB+":
                    b7.setChecked(true);
                    bgrt = "AB+";
                    break;
                case"AB-":
                    b8.setChecked(true);
                    bgrt = "AB-";
                    break;
            }
            if(str1[4].equals(new String("Normal")))
            {
                a1.setChecked(true);
                a = "Normal";
            }
            else
            {
                a2.setChecked(true);
                a="Abnormal";
            }
            if(str1[5].equals(new String("Normal")))
            {
                s1.setChecked(true);
                s = "Normal";
            }
            else
            {
                s2.setChecked(true);
                s = "Abnormal";
            }
            if(str1[6].equals(new String("Normal")))
            {
                m1.setChecked(true);
                m = "Normal";
            }
            else
            {
                m2.setChecked(true);
                m = "Abnormal";
            }
            if(str1[7].equals(new String("Reactive")))
            {
                v1.setChecked(true);
                vdrl = "Reactive";
            }
            else
            {
                v2.setChecked(true);
                vdrl = "Non Reactive";
            }
            if(str1[8].equals(new String("Reactive")))
            {
                h1.setChecked(true);
                hiv = "Reactive";
            }
            else
            {
                h2.setChecked(true);
                hiv = "Non Reactive";
            }
            if(str1[9].equals(new String("Reactive")))
            {
                hbs1.setChecked(true);
                hbsag = "Reactive";
            }
            else
            {
                hbs2.setChecked(true);
                hbsag = "Non Reactive";
            }
            rbs.setText(str1[10]);
            e4.setText(str1[11]);
        }

    }

}
