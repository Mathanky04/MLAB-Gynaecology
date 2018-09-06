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

public class tri3_investigation extends AppCompatActivity {

    SQLiteDatabase database;
    JSONObject ob;
    String[] str;
    String[] str1;
    String pid = "";
    EditText e1, e2, e4;
    EditText third_other_box, third_hb, third_rbs;
    RadioGroup rg1, rg2, rg3, rg4, rg5, rg6, rg7, rg8;
    RadioButton selectedRadioButton,selectedRadioButton1, b1, b2, b3, b4, b5, b6, b7, b8, a1, a2, s1, s2, m1, m2, v1, v2, h1, h2, hbs1,hbs2;
    CheckBox abdomen, leak_piv, bleed_piv, foetal, third_pih, third_iron, third_calcium, third_other;
    String pain="No", leak="No", bleed="No", pi="No", calcium="No", iront="No", foet="No", to="No", trimesterNo = "Trimester-3", bgrt="",
            a = "Abnormal", s="Abnormal", m="Abnormal", vdrl="Non Reactive", hiv="Non Reactive", hbsag="Non Reactive";

    String table_query1 = "patient_id TEXT NOT NULL," +
            "pain_in_abdomen TEXT DEFAULT \"No\"," +
            "leak_piv2 TEXT DEFAULT \"No\"," +
            "bleeding_piv TEXT DEFAULT \"No\"," +
            "foetal_movements TEXT DEFAULT \"No\"," +
            "pih_history2 TEXT DEFAULT \"No\"," +
            " iron_tablets2 TEXT DEFAULT \"No\"," +
            "calcium_tablets2 TEXT DEFAULT \"No\", " +
            "others3 TEXT DEFAULT \"No\"," +
            "growth_scan TEXT NOT NULL," +
            "update_status TEXT DEFAULT \"No\"," +
            "timestamp TEXT NOT NULL," +
            "primary key(patient_id), foreign key(patient_id) references general_information(patient_id)";
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
        setContentView(R.layout.activity_tri3_investigation);
        e2 = (EditText) findViewById(R.id.third_trimester_others);
        abdomen = (CheckBox) findViewById(R.id.abdomen);
        leak_piv = (CheckBox) findViewById(R.id.leak_piv);
        bleed_piv = (CheckBox) findViewById(R.id.third_bleeding_piv);
        third_pih = (CheckBox) findViewById(R.id.third_pih);
        foetal = (CheckBox) findViewById(R.id.foetal);
        third_iron = (CheckBox) findViewById(R.id.third_iron);
        third_calcium = (CheckBox) findViewById(R.id.third_calcium);

        third_other = (CheckBox) findViewById(R.id.third_other);
        third_other_box = (EditText) findViewById(R.id.third_other_box);

        third_other_box.setVisibility(View.GONE);
        abdomen.setVisibility(View.GONE);
        leak_piv.setVisibility(View.GONE);
        bleed_piv.setVisibility(View.GONE);
        third_pih.setVisibility(View.GONE);
        foetal.setVisibility(View.GONE);
        third_iron.setVisibility(View.GONE);
        third_other.setVisibility(View.GONE);
        third_calcium.setVisibility(View.GONE);
        third_hb = (EditText) findViewById(R.id.third_hb);
        rg2 = (RadioGroup) findViewById(R.id.third_albumin);
        rg3 = (RadioGroup) findViewById(R.id.third_sugar);
        rg4 = (RadioGroup) findViewById(R.id.third_microscopy);
        rg5 = (RadioGroup) findViewById(R.id.third_vdrl);
        rg6 = (RadioGroup) findViewById(R.id.third_hiv);
        rg7 = (RadioGroup) findViewById(R.id.third_hbsag);
        rg8 = (RadioGroup) findViewById(R.id.third_blood_group);
        third_rbs = (EditText) findViewById(R.id.third_rbs);
        a1 = (RadioButton) findViewById(R.id.third_albumin_normal);
        a2 = (RadioButton) findViewById(R.id.third_albumin_abnormal);
        s1 = (RadioButton) findViewById(R.id.third_sugar_normal);
        s2 = (RadioButton) findViewById(R.id.third_sugar_abnormal);
        m1 = (RadioButton) findViewById(R.id.third_microscopy_normal);
        m2 = (RadioButton) findViewById(R.id.third_microscopy_abnormal);
        v1 = (RadioButton) findViewById(R.id.third_vdrl_reactive);
        v2 = (RadioButton) findViewById(R.id.third_vdrl_non_reactive);
        h1 = (RadioButton) findViewById(R.id.third_hiv_reactive);
        h2 = (RadioButton) findViewById(R.id.third_hiv_non_reactive);
        hbs1 = (RadioButton) findViewById(R.id.third_hbsag_reactive);
        hbs2 = (RadioButton) findViewById(R.id.third_hbsag_non_reactive);
        third_rbs = (EditText) findViewById(R.id.third_rbs);
        rg1 = (RadioGroup) findViewById(R.id.third_trimester_complaints);
        selectedRadioButton = (RadioButton) findViewById(R.id.third_trimester_complaints_yes);
        selectedRadioButton1 = (RadioButton)findViewById(R.id.second_trimester_complaints_no);
        //opening db
        database = openOrCreateDatabase("gynaecology", Context.MODE_PRIVATE, null);

        //creating table if doesn't exist
        database.execSQL("CREATE TABLE IF NOT EXISTS third_trimester(" + table_query1 + ")");
        database.execSQL("CREATE TABLE IF NOT EXISTS investigations(" + table_query2 + ")");
        if(retrieve) {
            String selectQuery2 = "SELECT * FROM third_trimester WHERE patient_id ='" + pid + "';";
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
                        JSONObject obj = (JSONObject) ob.get("third_trimester");
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
                    String selectQuery = "SELECT * FROM third_trimester WHERE patient_id ='" + pid + "';";
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
                    if (c1.moveToFirst()) {//cursor.moveToNext();
                        int totalColumn2 = c1.getColumnCount();
                        do {
                            str1 = new String[totalColumn2];
                            for (int i = 0; i < totalColumn2; i++) {
                                str1[i] = c1.getString(i);
                                //System.out.println(str[i]);
                            }
                        } while (c1.moveToNext());
                    }
                    onDisplay2();
                    //System.out.println(cursor.getString(0));
                    String deleteQuery = "DELETE FROM third_trimester WHERE patient_id = " + "'" + pid + "'";
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
    public void onBackPressed() { }

    public void onProceed(View view) {
        onclickAssign2();
        if (ValidationSuccess()) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
            String format = simpleDateFormat.format(new Date());
            String insert_query1 = "'" + id.toString().trim() + "'," +
                    "'" + pain + "'," +
                    "'" + leak + "'," +
                    "'" + bleed + "'," +
                    "'" + foet + "'," +
                    "'" + pi + "'," +
                    "'" + iront + "'," +
                    "'" + calcium + "'," +
                    "'" + to + "'," +
                    "'" + "" + "'," +
                    "'" + "No" + "'," +
                    "'" + format.trim() + "'";
            String insert_query2 = "'" + id.toString().trim() + "'," +
                    "'" + trimesterNo + "'," +
                    "'" + third_hb.getText().toString().trim() + "'," +
                    "'" + bgrt + "'," +
                    "'" + a + "'," +
                    "'" + s + "'," +
                    "'" + s + "'," +
                    "'" + m + "'," +
                    "'" + vdrl + "'," +
                    "'" + hiv + "'," +
                    "'" + third_rbs.getText().toString().trim() + "'," +
                    "'" + e2.getText().toString().trim() + "'," +
                    "'" + "No" + "'," +
                    "'" + format.trim() + "'";
            System.out.println("InsertQuery:" + insert_query1);
            System.out.println("InsertQuery:" + insert_query2);
            //inserting into database
            database.execSQL("INSERT INTO third_trimester VALUES (" + insert_query1 + ")");
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
        } else {
            Toast.makeText(getApplicationContext(), "Please check the details", Toast.LENGTH_LONG).show();
        }
    }

    private boolean ValidationSuccess() {

        boolean check = true;
        StringBuilder errMsg = new StringBuilder("");

        if (third_hb.getText().toString().equalsIgnoreCase("")) {
            third_hb.setError("Please enter a value");
            check = false;
        }

        if (Integer.parseInt(third_hb.getText().toString()) > 100) {
            third_hb.setError("Exceeds limit! Please enter a valid percentage");
            return false;
        }

        if (third_rbs.getText().toString().equalsIgnoreCase("")) {
            third_hb.setError("Please enter a value");
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

        if (rg4.getCheckedRadioButtonId() == -1) {
            errMsg.append("Please select on option\n");
            check = false;
        }

        if (rg5.getCheckedRadioButtonId() == -1) {
            errMsg.append("Please select on option\n");
            check = false;
        }

        if (rg6.getCheckedRadioButtonId() == -1) {
            errMsg.append("Please select on option\n");
            check = false;
        }

        if (rg7.getCheckedRadioButtonId() == -1) {
            errMsg.append("Please select on option\n");
            check = false;
        }

        if (rg8.getCheckedRadioButtonId() == -1) {
            errMsg.append("Please select on option\n");
            check = false;
        }

        if (selectedRadioButton.isChecked()) {
            third_other = (CheckBox) findViewById(R.id.third_other);
            third_other_box = (EditText) findViewById(R.id.third_other_box);

            if (!(abdomen.isChecked() || leak_piv.isChecked() || bleed_piv.isChecked() || third_pih.isChecked() || foetal.isChecked() || third_iron.isChecked() || third_other.isChecked() || third_calcium.isChecked())) {
                abdomen.setError("Please select an option");
                check = false;
            } else if (third_other.isChecked() && third_other_box.getText().toString().equalsIgnoreCase("")) {
                third_other.setError("Please enter a value");
                check = false;
            }
        }
        return check;
    }

    public void onclickAssign(View view) {
        b1 = (RadioButton) findViewById(R.id.third_blood_group_A_plus);
        b2 = (RadioButton) findViewById(R.id.third_blood_group_A_minus);
        b3 = (RadioButton) findViewById(R.id.third_blood_group_B_plus);
        b4 = (RadioButton) findViewById(R.id.third_blood_group_B_minus);
        b5 = (RadioButton) findViewById(R.id.third_blood_group_O_plus);
        b6 = (RadioButton) findViewById(R.id.third_blood_group_O_minus);
        b7 = (RadioButton) findViewById(R.id.third_blood_group_AB_plus);
        b8 = (RadioButton) findViewById(R.id.third_blood_group_AB_minus);
        // Is the button now checked?
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

    public void onclickAssign2() {
        e2 = (EditText) findViewById(R.id.third_trimester_others);
        abdomen = (CheckBox) findViewById(R.id.abdomen);
        bleed_piv = (CheckBox) findViewById(R.id.third_bleeding_piv);
        leak_piv = (CheckBox) findViewById(R.id.leak_piv);
        third_pih = (CheckBox) findViewById(R.id.third_pih);
        third_calcium = (CheckBox) findViewById(R.id.third_calcium);
        third_iron = (CheckBox) findViewById(R.id.third_iron);
        third_other = (CheckBox) findViewById(R.id.third_other);
        third_other_box = (EditText) findViewById(R.id.third_other_box);
        if (selectedRadioButton.isChecked()) {
            if (abdomen.isChecked()) {
                pain = "Yes";
            }
            else
            {
                pain="No";
            }
            if (leak_piv.isChecked()) {
                leak = "Yes";
            }
            else
            {
                leak="No";
            }
            if (bleed_piv.isChecked()) {
                bleed = "Yes";
            }
            if (third_pih.isChecked()) {
                pi = "Yes";

            }
            else
            {
                pi="No";
            }
            if (third_iron.isChecked()) {
                iront = "Yes";
            }
            else
            {
                iront="No";
            }
            if (foetal.isChecked()) {
                foet = "Yes";
            }
            else
            {
                foet="No";
            }
            if (third_calcium.isChecked()) {
                calcium = "Yes";
            }
            else
            {
                calcium="No";
            }
            if (third_other.isChecked()) {
                to = third_other_box.getText().toString();
            } else {
                to = "No";
            }
        }
    }

    public void click(View view) {
        abdomen.setVisibility(View.VISIBLE);
        leak_piv.setVisibility(View.VISIBLE);
        bleed_piv.setVisibility(View.VISIBLE);
        third_pih.setVisibility(View.VISIBLE);
        foetal.setVisibility(View.VISIBLE);
        third_iron.setVisibility(View.VISIBLE);
        third_other.setVisibility(View.VISIBLE);
        third_calcium.setVisibility(View.VISIBLE);
    }

    public void click1(View view) {
        third_other_box.setVisibility(View.GONE);
        abdomen.setVisibility(View.GONE);
        leak_piv.setVisibility(View.GONE);
        bleed_piv.setVisibility(View.GONE);
        third_pih.setVisibility(View.GONE);
        foetal.setVisibility(View.GONE);
        third_iron.setVisibility(View.GONE);
        third_other.setVisibility(View.GONE);
        third_calcium.setVisibility(View.GONE);
    }

    public void click3(View view) {
        third_other_box = (EditText) findViewById(R.id.third_other_box);
        third_other_box.setVisibility(View.VISIBLE);
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
            JSONObject obj1 = (JSONObject) ob.get("third_trimester");
            if ((obj1.getString("C1").equals(new String("No")) && obj1.getString("C2").equals(new String("No")) &&
                    obj1.getString("C3").equals(new String("No")) && obj1.getString("C4").equals(new String("No")) &&
                    obj1.getString("C5").equals(new String("No")) && obj1.getString("C6").equals(new String("No")) &&
                    obj1.getString("C7").equals(new String("No")) && obj1.getString("C8").equals(new String("No")))) {
                selectedRadioButton1.setChecked(true);
                //System.out.println("Here in none");
            } else
            {
                selectedRadioButton.setChecked(true);
                abdomen.setVisibility(View.VISIBLE);
                if(!(obj1.getString("C1").equals(new String("No"))))
                {
                    abdomen.setChecked(true);
                    pain = "Yes";
                }
                leak_piv.setVisibility(View.VISIBLE);
                if(!(obj1.getString("C2").equals(new String("No"))))
                {
                    leak_piv.setChecked(true);
                    leak = "Yes";
                }
                bleed_piv.setVisibility(View.VISIBLE);
                if(!(obj1.getString("C3").equals(new String("No"))))
                {
                    bleed_piv.setChecked(true);
                    bleed = "Yes";
                }
                foetal.setVisibility(View.VISIBLE);
                if(!(obj1.getString("C5").equals(new String("No"))))
                {
                    foetal.setChecked(true);
                    foet = "Yes";
                }
                third_pih.setVisibility(View.VISIBLE);
                if(!(obj1.getString("C4").equals(new String("No"))))
                {
                    third_pih.setChecked(true);
                    pi = "Yes";
                }
                third_iron.setVisibility(View.VISIBLE);
                if(!(obj1.getString("C6").equals(new String("No"))))
                {
                    third_iron.setChecked(true);
                    iront = "Yes";
                }
                third_calcium.setVisibility(View.VISIBLE);
                if(!(obj1.getString("C7").equals(new String("No"))))
                {
                    third_calcium.setChecked(true);
                    calcium = "Yes";
                }
                third_other.setVisibility(View.VISIBLE);
                if(!(obj1.getString("C8").equals(new String("No"))))
                {
                    third_other.setChecked(true);
                    third_other_box.setVisibility(View.VISIBLE);
                    third_other_box.setText(obj1.getString("C8"));
                }
            }
            JSONObject obj2 = (JSONObject) ob.get("investigations");
            if (obj2.getString("C1").equals(new String("Trimester-3"))) {
                third_hb.setText(obj2.getString("C2"));
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
                third_rbs.setText(obj2.getString("C10"));
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
            abdomen.setVisibility(View.VISIBLE);
            if(!(str[1].equals(new String("No"))))
            {
                abdomen.setChecked(true);
                pain = "Yes";
            }
            leak_piv.setVisibility(View.VISIBLE);
            if(!(str[2].equals(new String("No"))))
            {
                leak_piv.setChecked(true);
                leak = "Yes";
            }
            bleed_piv.setVisibility(View.VISIBLE);
            if(!(str[3].equals(new String("No"))))
            {
                bleed_piv.setChecked(true);
                bleed = "Yes";
            }
            third_pih.setVisibility(View.VISIBLE);
            if(!(str[4].equals(new String("No"))))
            {
                third_pih.setChecked(true);
                pi = "Yes";
            }
            foetal.setVisibility(View.VISIBLE);
            if(!(str[5].equals(new String("No"))))
            {
                foetal.setChecked(true);
                foet = "Yes";
            }
            third_iron.setVisibility(View.VISIBLE);
            if(!(str[6].equals(new String("No"))))
            {
                third_iron.setChecked(true);
                iront = "Yes";
            }
            third_calcium.setVisibility(View.VISIBLE);
            if(!(str[7].equals(new String("No"))))
            {
                third_calcium.setChecked(true);
                calcium = "Yes";
            }
            third_other.setVisibility(View.VISIBLE);
            if(!(str[8].equals(new String("No"))))
            {
                third_other.setChecked(true);
                third_other_box.setVisibility(View.VISIBLE);
                third_other_box.setText(str[8]);
            }
        }
        if(str1[1].equals(new String("Trimester-2")))
        {
            third_hb.setText(str1[2]);
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
            third_rbs.setText(str1[10]);
            e2.setText(str1[11]);
        }

    }

}
