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

public class tri2_investigation extends AppCompatActivity {

    SQLiteDatabase database;
    EditText e2;
    EditText second_other_box, second_hb, rbs;
    RadioGroup rg1, rg2, rg3, rg4, rg5, rg6, rg7, rg8;
    RadioButton selectedRadioButton,b1,b2,b3,b4,b5,b6,b7,b8,a1,a2,s1,s2,m1,m2,v1,v2,h1,h2,hbs1,hbs2,selectedRadioButton1;
    CheckBox quickening, bleeding_piv, leak_piv, pih, tt, iron,calc, second_other;
    JSONObject ob;
    String[] str;
    String[] str1;
    String pid = "";
    String bgrt="",
            q="No",
            bp="No",
            lp="No",
            p="No",
            t="No",
            i="No",
            c="No",
            so="No",
            sc="",
            trimesterNo="Trimester-2",
            a="",
            s="",
            m="",
            vdrl="",
            hiv="",
            hbsag="";

    String table_query1 = "patient_id TEXT NOT NULL," +
            "quickening TEXT DEFAULT \"No\"," +
            " history_of_bleeding_piv TEXT DEFAULT \"No\"," +
            "leak_piv1 TEXT DEFAULT \"No\"," +
            "pih_history1 TEXT DEFAULT \"No\"," +
            "tt TEXT DEFAULT \"No\"," +
            " iron_tablets1 TEXT DEFAULT \"No\"," +
            " calcium_tablets1 TEXT DEFAULT \"No\"," +
            " others2 TEXT DEFAULT \"No\"," +
            "anomaly_scan TEXT NOT NULL," +
            "update_status TEXT DEFAULT \"No\"," +
            "timestamp TEXT NOT NULL," +
            "primary key(patient_id), foreign key(patient_id) references general_information(patient_id)";
    String table_query2 = "patient_id TEXT NOT NULL," +
            "trimester_number TEXT NOT NULL," +
            " hb TEXT NOT NULL, " +
            "blood_grouping_rh_typing TEXT NOT NULL, " +
            "albumin TEXT NOT NULL," +
            " sugar TEXT NOT NULL," +
            " microscopy TEXT NOT NULL," +
            " vdrl TEXT NOT NULL," +
            " hiv_status TEXT NOT NULL," +
            " hbsag TEXT NOT NULL," +
            " rbs TEXT NOT NULL," +
            " others4 TEXT DEFAULT \"No\"," +
            " update_status TEXT DEFAULT \"No\"," +
            "timestamp TEXT NOT NULL," +
            "primary key(patient_id, trimester_number), foreign key(patient_id) references general_information(patient_id)";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tri2_investigation);
        e2 = (EditText)findViewById(R.id.second_trimester_others);
        quickening = (CheckBox) findViewById(R.id.quickening);
        bleeding_piv = (CheckBox) findViewById(R.id.bleeding_piv);
        leak_piv = (CheckBox) findViewById(R.id.leak_piv);
        pih = (CheckBox) findViewById(R.id.pih);
        calc = (CheckBox)findViewById(R.id.calcium);
        tt = (CheckBox) findViewById(R.id.tt);
        iron = (CheckBox) findViewById(R.id.iron);
        selectedRadioButton1 = (RadioButton)findViewById(R.id.second_trimester_complaints_no);
        second_other = (CheckBox) findViewById(R.id.second_other);
        second_other_box = (EditText)findViewById(R.id.second_other_box);

        second_other_box.setVisibility(View.GONE);
        quickening.setVisibility(View.GONE);
        bleeding_piv.setVisibility(View.GONE);
        leak_piv.setVisibility(View.GONE);
        pih.setVisibility(View.GONE);
        tt.setVisibility(View.GONE);
        calc.setVisibility(View.GONE);
        iron.setVisibility(View.GONE);
        second_other.setVisibility(View.GONE);

        second_hb = (EditText)findViewById(R.id.second_hb) ;
        rg2 = (RadioGroup)findViewById(R.id.second_albumin);
        rg3 = (RadioGroup)findViewById(R.id.second_sugar);
        rg4 = (RadioGroup)findViewById(R.id.second_microscopy);
        rg5 = (RadioGroup)findViewById(R.id.second_vdrl);
        rg6 = (RadioGroup)findViewById(R.id.second_hiv);
        rg7 = (RadioGroup)findViewById(R.id.second_hbsag);
        rg8 =  (RadioGroup)findViewById(R.id.second_blood_group);
        rbs = (EditText)findViewById(R.id.second_rbs);
        rg1=(RadioGroup)findViewById(R.id.second_trimester_complaints);
        selectedRadioButton = (RadioButton)findViewById(R.id.second_trimester_complaints_yes);
        b1 = (RadioButton)findViewById(R.id.second_blood_group_A_plus);
        b2 = (RadioButton)findViewById(R.id.second_blood_group_A_minus);
        b3 = (RadioButton)findViewById(R.id.second_blood_group_B_plus);
        b4 = (RadioButton)findViewById(R.id.second_blood_group_B_minus);
        b5 = (RadioButton)findViewById(R.id.second_blood_group_O_plus);
        b6 = (RadioButton)findViewById(R.id.second_blood_group_O_minus);
        b7 = (RadioButton)findViewById(R.id.second_blood_group_AB_plus);
        b8 = (RadioButton)findViewById(R.id.second_blood_group_AB_minus);
        e2 = (EditText)findViewById(R.id.second_trimester_others);
        quickening = (CheckBox) findViewById(R.id.quickening);
        bleeding_piv = (CheckBox) findViewById(R.id.bleeding_piv);
        leak_piv = (CheckBox) findViewById(R.id.leak_piv);
        pih = (CheckBox) findViewById(R.id.pih);
        calc = (CheckBox)findViewById(R.id.calcium);
        tt = (CheckBox) findViewById(R.id.tt);
        iron = (CheckBox) findViewById(R.id.iron);
        second_other = (CheckBox) findViewById(R.id.second_other);
        second_other_box = (EditText) findViewById(R.id.second_other_box);
        a1=(RadioButton)findViewById(R.id.second_albumin_normal);
        a2=(RadioButton)findViewById(R.id.second_albumin_abnormal);
        s1=(RadioButton)findViewById(R.id.second_sugar_normal);
        s2=(RadioButton)findViewById(R.id.second_sugar_abnormal);
        m1=(RadioButton)findViewById(R.id.second_microscopy_normal);
        m2=(RadioButton)findViewById(R.id.second_microscopy_abnormal);
        v1=(RadioButton)findViewById(R.id.second_vdrl_reactive);
        v2=(RadioButton)findViewById(R.id.second_vdrl_non_reactive);
        h1=(RadioButton)findViewById(R.id.second_hiv_reactive);
        h2=(RadioButton)findViewById(R.id.second_hiv_non_reactive);
        hbs1=(RadioButton)findViewById(R.id.second_hbsag_reactive);
        hbs2=(RadioButton)findViewById(R.id.second_hbsag_non_reactive);
        rbs=(EditText)findViewById(R.id.second_rbs);
        // Is the button now checked?
        //opening db
        database = openOrCreateDatabase("gynaecology", Context.MODE_PRIVATE, null);

        //creating table if doesn't exist
        database.execSQL("CREATE TABLE IF NOT EXISTS second_trimester(" + table_query1 + ")");
        database.execSQL("CREATE TABLE IF NOT EXISTS investigations(" + table_query2 + ")");
        if(retrieve) {
            String selectQuery2 = "SELECT * FROM second_trimester WHERE patient_id ='" + pid + "';";
            Cursor c = database.rawQuery(selectQuery2, null); //check for table existence
            if (c.moveToFirst()) {
                if (selected == "server") {
                    try {
                        Bundle b = getIntent().getExtras();
                        System.out.println("Entered");
                        //JSONArray arr = new JSONArray(new String(responseBody));
                        ob = new JSONObject(b.getString("JObj2"));
                        System.out.println(ob);
                        System.out.println("Retreived::" + "Here");
                        JSONObject obj = (JSONObject) ob.get("second_trimester");
                        pid = (String) obj.get("C0");
                        System.out.println(pid);
                        onDisplay();
                        Toast.makeText(getApplicationContext(), "Retreived!", Toast.LENGTH_LONG).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    //System.out.println("Yo em here");
                    Bundle b = getIntent().getExtras();
                    pid = b.getString("pid");
                    String selectQuery = "SELECT * FROM second_trimester WHERE patient_id ='" + pid + "';";
                    String selectQuery1 = "SELECT * FROM investigations WHERE patient_id ='" + pid + "';";
                    Cursor cursor = database.rawQuery(selectQuery, null);
                    Cursor c1 = database.rawQuery(selectQuery1, null);
                    if (cursor.moveToFirst()) {
                        int totalColumn = cursor.getColumnCount();
                        do {
                            str = new String[totalColumn];
                            for (int i = 0; i < totalColumn; i++) {
                                str[i] = cursor.getString(i);
                            }
                        } while (cursor.moveToNext());
                    }
                    if (c1.moveToFirst()) {
                        int totalColumn2 = c1.getColumnCount();
                        do {
                            str1 = new String[totalColumn2];
                            for (int i = 0; i < totalColumn2; i++) {
                                str1[i] = c1.getString(i);
                            }
                        } while (c1.moveToNext());
                    }
                    onDisplay2();
                    String deleteQuery = "DELETE FROM second_trimester WHERE patient_id = " + "'" + pid + "'";
                    Log.d("query", deleteQuery);
                    database.execSQL(deleteQuery);
                    String deleteQuery1 = "DELETE FROM investigations WHERE patient_id = " + "'" + pid + "'";
                    Log.d("query", deleteQuery1);
                    database.execSQL(deleteQuery1);
                }
            }
        }
    }
    @Override
    public void onBackPressed() {
    }

    public void onProceed(View view){
        if(ValidationSuccess()){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
            String format = simpleDateFormat.format(new Date());
            if(selectedRadioButton.isChecked()) {
                if (quickening.isChecked()) {
                    q = "Yes";
                } else {
                    q = "No";
                }
                if (bleeding_piv.isChecked()) {
                    bp = "Yes";
                } else {
                    bp = "No";
                }
                if (leak_piv.isChecked()) {
                    lp = "Yes";
                } else {
                    lp = "No";
                }
                if (pih.isChecked()) {
                    p = "Yes";
                } else {
                    p = "No";
                }
                if (tt.isChecked()) {
                    t = "Yes";
                } else {
                    t = "No";
                }
                if (iron.isChecked()) {
                    i = "Yes";
                } else {
                    i = "No";
                }
                if (calc.isChecked()) {
                    c = "Yes";
                } else {
                    c = "No";
                }
                if (second_other.isChecked()) {
                    so = second_other_box.getText().toString();
                } else {
                    so = "No";
                }
            }
            String insert_query1 = "'" + id.toString().trim() + "'," +
                    "'" + q + "'," +
                    "'" + bp + "'," +
                    "'" + lp + "'," +
                    "'" + p + "'," +
                    "'" + t + "'," +
                    "'" + i + "'," +
                    "'" + c + "'," +
                    "'" + so + "'," +
                    "'" + sc +"',"+
                    "'" + "No" + "'," +
                    "'" + format.trim() + "'";
            String insert_query2 = "'" + id.toString().trim() + "'," +
                    "'" + trimesterNo + "'," +
                    "'" + second_hb.getText().toString().trim() + "'," +
                    "'" + bgrt + "'," +
                    "'" + a + "'," +
                    "'" + s + "'," +
                    "'" + m + "'," +
                    "'" + vdrl + "'," +
                    "'" + hiv + "'," +
                    "'" + hbsag + "'," +
                    "'" + rbs.getText().toString().trim() + "'," +
                    "'" + e2.getText().toString().trim() + "'," +
                    "'" + "No" + "'," +
                    "'" + format.trim() + "'";
            System.out.println("InsertQuery:" + insert_query1);
            System.out.println("InsertQuery:" + insert_query2);
            //inserting into database
            database.execSQL("INSERT INTO second_trimester VALUES (" + insert_query1 + ")");
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
        database.close();
    }

    private boolean ValidationSuccess(){

        boolean check=true;
        StringBuilder errMsg = new StringBuilder("");

        if (second_hb.getText().toString().equalsIgnoreCase("")){
            second_hb.setError("Please enter a value");
            check=false;
        }

        if (Integer.parseInt(second_hb.getText().toString()) > 100) {
            second_hb.setError("Exceeds limit! Please enter a valid percentage");
            return false;
        }

        if (rbs.getText().toString().equalsIgnoreCase("")){
            rbs.setError("Please enter a value");
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
            if (!(quickening.isChecked() || bleeding_piv.isChecked() || leak_piv.isChecked() || pih.isChecked() || tt.isChecked() || iron.isChecked()||calc.isChecked() || second_other.isChecked())){
                quickening.setError("Please select an option");
                check=false;
            }


            else if(second_other.isChecked()&& second_other_box.getText().toString().equalsIgnoreCase("")){
                second_other.setError("Please enter a value");
                check=false;
            }

        }
        return check;
    }

    public void onclickAssign(View view)
    {
        if(b1.isChecked())
            bgrt="A+";
        else if(b2.isChecked())
            bgrt="A-";
        else if(b3.isChecked())
            bgrt="B+";
        else if(b4.isChecked())
            bgrt="B-";
        else if(b5.isChecked())
            bgrt="O+";
        else if(b6.isChecked())
            bgrt="O-";
        else if(b7.isChecked())
            bgrt="AB+";
        else if(b8.isChecked())
            bgrt="AB-";
    }

    public void click(View view)
    {
        quickening.setVisibility(View.VISIBLE);
        bleeding_piv.setVisibility(View.VISIBLE);
        leak_piv.setVisibility(View.VISIBLE);
        pih.setVisibility(View.VISIBLE);
        tt.setVisibility(View.VISIBLE);
        calc.setVisibility(View.VISIBLE);
        iron.setVisibility(View.VISIBLE);
        second_other.setVisibility(View.VISIBLE);

    }

    public void click3(View view)
    {
        second_other_box = (EditText)findViewById(R.id.second_other_box);
        second_other_box.setVisibility(View.VISIBLE);

    }
    public void click1(View view)
    {
        second_other_box.setVisibility(View.GONE);
        quickening.setVisibility(View.GONE);
        bleeding_piv.setVisibility(View.GONE);
        leak_piv.setVisibility(View.GONE);
        pih.setVisibility(View.GONE);
        tt.setVisibility(View.GONE);
        calc.setVisibility(View.GONE);
        iron.setVisibility(View.GONE);
        second_other.setVisibility(View.GONE);

    }

    public void onclickAssign3(View view)
    {
        if(a1.isChecked())
            a="Normal";
        if(s1.isChecked())
            s="Normal";
        if(m1.isChecked())
            m="Normal";
        if(v1.isChecked())
            vdrl="Reactive";
        if(h1.isChecked())
            hiv="Reactive";
        if(hbs1.isChecked())
            hbsag="Reactive";
    }

    public void onDisplay() {
        try {
            JSONObject obj1 = (JSONObject) ob.get("second_trimester");
            if ((obj1.getString("C1").equals(new String("No")) && obj1.getString("C2").equals(new String("No")) &&
                    obj1.getString("C3").equals(new String("No")) && obj1.getString("C4").equals(new String("No")) &&
                    obj1.getString("C5").equals(new String("No")) && obj1.getString("C6").equals(new String("No")) &&
                    obj1.getString("C7").equals(new String("No")) && obj1.getString("C8").equals(new String("No")))) {
                selectedRadioButton1.setChecked(true);
                System.out.println("Here in none");
            } else
            {
                selectedRadioButton.setChecked(true);
                quickening.setVisibility(View.VISIBLE);
                if(!(obj1.getString("C1").equals(new String("No"))))
                {
                    quickening.setChecked(true);
                    q = "Yes";
                }
                bleeding_piv.setVisibility(View.VISIBLE);
                if(!(obj1.getString("C2").equals(new String("No"))))
                {
                    bleeding_piv.setChecked(true);
                    bp = "Yes";
                }
                leak_piv.setVisibility(View.VISIBLE);
                if(!(obj1.getString("C3").equals(new String("No"))))
                {
                    leak_piv.setChecked(true);
                    lp = "Yes";
                }
                pih.setVisibility(View.VISIBLE);
                if(!(obj1.getString("C4").equals(new String("No"))))
                {
                    pih.setChecked(true);
                    p = "Yes";
                }
                tt.setVisibility(View.VISIBLE);
                if(!(obj1.getString("C5").equals(new String("No"))))
                {
                    tt.setChecked(true);
                    t = "Yes";
                }
                iron.setVisibility(View.VISIBLE);
                if(!(obj1.getString("C6").equals(new String("No"))))
                {
                    iron.setChecked(true);
                    i = "Yes";
                }
                calc.setVisibility(View.VISIBLE);
                if(!(obj1.getString("C7").equals(new String("No"))))
                {
                    calc.setChecked(true);
                    c = "Yes";
                }
                second_other.setVisibility(View.VISIBLE);
                if(!(obj1.getString("C8").equals(new String("No"))))
                {
                    second_other.setChecked(true);
                    second_other_box.setVisibility(View.VISIBLE);
                    second_other_box.setText(obj1.getString("C8"));
                }
            }
            JSONObject obj2 = (JSONObject) ob.get("investigations");
            if (obj2.getString("C1").equals(new String("Trimester-2"))) {
                second_hb.setText(obj2.getString("C2"));
                switch (obj2.getString("C3")) {
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
                if (obj2.getString("C4").equals(new String("Normal"))) {
                    a1.setChecked(true);
                    a = "Normal";
                } else {
                    a2.setChecked(true);
                    a = "Abnormal";
                }
                if (obj2.getString("C5").equals(new String("Normal"))) {
                    s1.setChecked(true);
                    s = "Normal";
                } else {
                    s2.setChecked(true);
                    s = "Abnormal";
                }
                if (obj2.getString("C6").equals(new String("Normal"))) {
                    m1.setChecked(true);
                    m = "Normal";
                } else {
                    m2.setChecked(true);
                    m = "Normal";
                }
                if (obj2.getString("C7").equals(new String("Reactive"))) {
                    v1.setChecked(true);
                    vdrl = "Reactive";
                } else {
                    v2.setChecked(true);
                    vdrl = "Non Reactive";
                }
                if (obj2.getString("C8").equals(new String("Reactive"))) {
                    h1.setChecked(true);
                    hiv = "Reactive";
                } else {
                    h2.setChecked(true);
                    hiv = "Non Reactive";
                }
                if (obj2.getString("C9").equals(new String("Reactive"))) {
                    hbs1.setChecked(true);
                    hbsag = "Reactive";
                } else {
                    hbs2.setChecked(true);
                    hbsag = "Non Reactive";
                }
                rbs.setText(obj2.getString("C10"));
                e2.setText(obj2.getString("C11"));
            }
        } catch (JSONException e) {
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
                quickening.setVisibility(View.VISIBLE);
                if(!(str[1].equals(new String("No"))))
                {
                    quickening.setChecked(true);
                    q = "Yes";
                }
                bleeding_piv.setVisibility(View.VISIBLE);
                if(!(str[2].equals(new String("No"))))
                {
                    bleeding_piv.setChecked(true);
                    bp = "Yes";
                }
                leak_piv.setVisibility(View.VISIBLE);
                if(!(str[3].equals(new String("No"))))
                {
                    leak_piv.setChecked(true);
                    lp = "Yes";
                }
                pih.setVisibility(View.VISIBLE);
                if(!(str[4].equals(new String("No"))))
                {
                    pih.setChecked(true);
                    p = "Yes";
                }
                tt.setVisibility(View.VISIBLE);
                if(!(str[5].equals(new String("No"))))
                {
                    tt.setChecked(true);
                    t = "Yes";
                }
                iron.setVisibility(View.VISIBLE);
                if(!(str[6].equals(new String("No"))))
                {
                    iron.setChecked(true);
                    i = "Yes";
                }
                calc.setVisibility(View.VISIBLE);
                if(!(str[7].equals(new String("No"))))
                {
                    calc.setChecked(true);
                    c = "Yes";
                }

                second_other.setVisibility(View.VISIBLE);
                if(!(str[8].equals(new String("No"))))
                {
                    second_other.setChecked(true);
                    second_other_box.setVisibility(View.VISIBLE);
                    second_other_box.setText(str[8]);
                }
            }
            if(str1[1].equals(new String("Trimester-2")))
            {
                second_hb.setText(str1[2]);
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
                    a = "Abnormal";
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
                e2.setText(str1[11]);
            }

        }

    }