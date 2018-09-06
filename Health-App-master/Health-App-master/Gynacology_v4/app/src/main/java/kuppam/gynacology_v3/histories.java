package kuppam.gynacology_v3;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionProvider;
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

public class histories extends AppCompatActivity {

    SQLiteDatabase database;
    String table_history="patient_id TEXT ,menstrual_history TEXT , contraceptive_history TEXT , past_history TEXT , family_history TEXT ,update_status TEXT DEFAULT \"No\",timestamp TEXT ,primary key(patient_id), foreign key(patient_id) references general_information(patient_id)";
    String table_personal_history="patient_id TEXT ,diet TEXT , sleep TEXT , addictive_habits TEXT , allergies_known TEXT ,update_status TEXT DEFAULT \"No\",timestamp TEXT , primary key(patient_id), foreign key(patient_id) references general_information(patient_id)";
    EditText m_box, c_box,p_box, f_box,addictive_box, allergies_box;
    RadioButton m1,m2,c1,c2,p1,p2,f1,f2,d1,d2,s1,s2,ad1,ad2,al1,al2;
    RadioGroup rg1, rg2, rg3, rg4, rg5,rg6,rg7,rg8;
    Button btnNext;
    JSONObject ob;
    String[] str;
    String str1[];
    String pid = "";
    String mens="Regular",cons="Insignificant",past="Insignificant",fam="Insignificant",addict="No",aller="No";
    String d="",s="";
    @Override
    public void onBackPressed() { }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_histories);
        database = openOrCreateDatabase("gynaecology", Context.MODE_PRIVATE, null);
        database.execSQL("CREATE TABLE IF NOT EXISTS history_table(" + table_history + ")");
        database.execSQL("CREATE TABLE IF NOT EXISTS personal_history(" + table_personal_history + ")");
        m1=(RadioButton)findViewById(R.id.menstrual_history_regular);
        m2=(RadioButton)findViewById(R.id.menstrual_history_irregular);
        c1=(RadioButton)findViewById(R.id.contraceptive_history_yes);
        c2=(RadioButton)findViewById(R.id.contraceptive_history_no);
        p1=(RadioButton)findViewById(R.id.past_history_significant);
        p2=(RadioButton)findViewById(R.id.past_history_insignificant);
        f1=(RadioButton)findViewById(R.id.family_history_significant);
        f2=(RadioButton)findViewById(R.id.family_history_insignificant);
        d1=(RadioButton)findViewById(R.id.diet_veg);
        d2=(RadioButton)findViewById(R.id.diet_mixed);
        s1=(RadioButton)findViewById(R.id.sleep_normal);
        s2=(RadioButton)findViewById(R.id.sleep_disturbed);
        ad1=(RadioButton)findViewById(R.id.addictive_habits_yes);
        ad2=(RadioButton)findViewById(R.id.addictive_habits_no);
        al1=(RadioButton)findViewById(R.id.allergies_known_yes);
        al2=(RadioButton)findViewById(R.id.allergies_known_no);
        m_box=(EditText)findViewById(R.id.menstrual_history_box);
        c_box = (EditText)findViewById(R.id.contraceptive_history_box) ;
        p_box = (EditText)findViewById(R.id.past_history_box) ;
        f_box = (EditText)findViewById(R.id.family_history_box) ;
        addictive_box = (EditText)findViewById(R.id.addictive_habits_box) ;
        allergies_box = (EditText)findViewById(R.id.allergies_known_box) ;
        rg1=(RadioGroup)findViewById(R.id.menstrual_history);
        rg2 = (RadioGroup)findViewById(R.id.contraceptive_history);
        rg3 = (RadioGroup)findViewById(R.id.past_history);
        rg4 = (RadioGroup)findViewById(R.id.family_history);
        rg5 = (RadioGroup)findViewById(R.id.addictive_habits);
        rg6 = (RadioGroup)findViewById(R.id.allergies_known);
        btnNext=(Button)findViewById(R.id.next_page6);
        m_box.setVisibility(View.GONE);
        c_box.setVisibility(View.GONE);
        p_box.setVisibility(View.GONE);
        f_box.setVisibility(View.GONE);
        addictive_box.setVisibility(View.GONE);
        allergies_box.setVisibility(View.GONE);
        //onButton();
        if(retrieve) {
            if (selected == "server") {
                try {
                    Bundle b = getIntent().getExtras();
                    System.out.println("Entered");
                    System.out.println(b.getString("JObj4"));
                    ob = new JSONObject(b.getString("JObj4"));
                    System.out.println(ob);
                    System.out.println("Retreived::" + "Here");
                    JSONObject obj = (JSONObject) ob.get("history_table");
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
                String selectQuery = "SELECT * FROM history_table WHERE patient_id ='" + pid + "';";
                String selectQuery1 = "SELECT * FROM personal_history WHERE patient_id ='" + pid + "';";
                Cursor cursor = database.rawQuery(selectQuery, null);
                Cursor c = database.rawQuery(selectQuery1, null);
                if (cursor.moveToFirst()) {//cursor.moveToNext();
                    int totalColumn = cursor.getColumnCount();
                    do {
                        str = new String[totalColumn];
                        for (int i = 0; i < totalColumn; i++) {
                            str[i] = cursor.getString(i);
                        }
                        //System.out.println(cursor.getString(0));
                    } while (cursor.moveToNext());
                }
                if (c.moveToFirst()) {//cursor.moveToNext();
                    int totalColumn = c.getColumnCount();
                    do {
                        str1 = new String[totalColumn];
                        for (int i = 0; i < totalColumn; i++) {
                            str1[i] = c.getString(i);
                            //System.out.println(str[i]);
                        }
                        onDisplay2();
                        String deleteQuery = "DELETE FROM history_table where patient_id = " + "'" + pid + "'";
                        Log.d("query", deleteQuery);
                        database.execSQL(deleteQuery);
                        String deleteQuery1 = "DELETE FROM personal_history where patient_id = " + "'" + pid + "'";
                        Log.d("query", deleteQuery1);
                        database.execSQL(deleteQuery1);
                    } while (cursor.moveToNext());
                }
            }
        }
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
                    if(m2.isChecked())
                        mens=m_box.getText().toString();
                    if(c1.isChecked())
                        cons=c_box.getText().toString();
                    if(p1.isChecked())
                        past=p_box.getText().toString();
                    if(f1.isChecked())
                        fam=f_box.getText().toString();
                    if(d1.isChecked())
                        d="Veg";
                    else if(d2.isChecked())
                        d="Mixed";
                    if(s1.isChecked())
                        s="Normal";
                    else if(s2.isChecked())
                        s="Disturbed";
                    if(ad1.isChecked())
                        addict=addictive_box.getText().toString();
                    if(al1.isChecked())
                        aller=allergies_box.getText().toString();
                    String insert_history_table ="'"+id.toString().trim()+"','"+mens.trim()+"','"+cons.trim()+"','"+past.trim()+"','"+fam.trim()+ "','" + "No" + "','" +format.toString().trim()+"'";
                    String insert_personal_history="'"+id.toString().trim()+"','"+d.trim()+"','"+s.trim()+"','"+addict.trim()+"','"+aller.trim()+ "','" + "No" + "','" +format.toString().trim()+"'";

                    System.out.println("InsertQuery:" + insert_history_table);
                    System.out.println("InsertQuery:" + insert_personal_history);
                    //inserting into database
                    database.execSQL("INSERT INTO history_table VALUES (" + insert_history_table + ")");
                    database.execSQL("INSERT INTO personal_history VALUES (" +insert_personal_history+" )");
                    Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_LONG).show();
                    if(selected == "server")
                    {
                        Intent intent=new Intent(getApplicationContext(),Generalphysicalexamination.class);
                        intent.putExtra("type","obs");
                        intent.putExtra("JObj5",ob.toString());
                        startActivity(intent);
                    }
                    else {
                        Intent intent = new Intent(getApplicationContext(), Generalphysicalexamination.class);
                        intent.putExtra("pid",pid);
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




        if(m2.isChecked())
        {
            if (m_box.getText().toString().equalsIgnoreCase("")){
                m_box.setError("Please enter a value");
                check=false;
            }
        }

        if(c1.isChecked())
        {
            if (c_box.getText().toString().equalsIgnoreCase("")){
                c_box.setError("Please enter a value");
                check=false;
            }
        }

        if(p1.isChecked())
        {
            if (p_box.getText().toString().equalsIgnoreCase("")){
                p_box.setError("Please enter a value");
                check=false;
            }
        }

        if(f1.isChecked())
        {
            if (f_box.getText().toString().equalsIgnoreCase("")){
                f_box.setError("Please enter a value");
                check=false;
            }
        }

        if(ad1.isChecked())
        {
            if (addictive_box.getText().toString().equalsIgnoreCase("")){
                addictive_box.setError("Please enter a value");
                check=false;
            }
        }

        if(al1.isChecked())
        {
            if (allergies_box.getText().toString().equalsIgnoreCase("")){
                allergies_box.setError("Please enter a value");
                check=false;
            }
        }



        return check;
    }
    public void click(View view)
    {
        m_box.setVisibility(View.VISIBLE);
    }

    public void click1(View view)
    {
        m_box.setVisibility(View.GONE);
    }

    public void click2(View view)
    {
        c_box.setVisibility(View.VISIBLE);
    }

    public void click3(View view)
    {
        c_box.setVisibility(View.GONE);
    }

    public void click4(View view)
    {
        p_box.setVisibility(View.VISIBLE);
    }

    public void click5(View view)
    {
        p_box.setVisibility(View.GONE);
    }

    public void click6(View view)
    {
        f_box.setVisibility(View.VISIBLE);
    }

    public void click7(View view)
    {
        f_box.setVisibility(View.GONE);
    }

    public void click8(View view)
    {
        addictive_box.setVisibility(View.VISIBLE);
    }

    public void click9(View view)
    {
        addictive_box.setVisibility(View.GONE);
    }

    public void click10(View view)
    {
        allergies_box.setVisibility(View.VISIBLE);
    }

    public void click11(View view)
    {
        allergies_box.setVisibility(View.GONE);
    }

    public void onDisplay() {
        try {
            JSONObject obj1 = (JSONObject) ob.get("history_table");
            JSONObject obj2 = (JSONObject) ob.get("personal_history");
            if (obj1.getString("C1").equals(new String("Regular"))) {
                m1.setChecked(true);
            } else {
                m_box.setVisibility(View.VISIBLE);
                m2.setChecked(true);
                m_box.setText(obj1.getString("C1"));
            }
            if (obj1.getString("C2").equals(new String("Insignificant"))) {
                c2.setChecked(true);
            } else {
                c_box.setVisibility(View.VISIBLE);
                c1.setChecked(true);
                c_box.setText(obj1.getString("C2"));
            }
            if (obj1.getString("C3").equals(new String("Insignificant"))) {
                p2.setChecked(true);
            } else {
                p_box.setVisibility(View.VISIBLE);
                p1.setChecked(true);
                p_box.setText(obj1.getString("C3"));
            }
            if (obj1.getString("C4").equals(new String("Insignificant"))) {
                f2.setChecked(true);
            } else {
                f_box.setVisibility(View.VISIBLE);
                f1.setChecked(true);
                f_box.setText(obj1.getString("C4"));
            }
            if(obj2.getString("C1").equals(new String("Veg")))
            {
                d1.setChecked(true);
            }
            else
            {
                d2.setChecked(true);
            }
            if(obj2.getString("C2").equals(new String("Normal")))
            {
                s1.setChecked(true);
            }
            else
            {
                s2.setChecked(true);
            }
            if(obj2.getString("C3").equals(new String("No")))
            {
                ad2.setChecked(true);
            }
            else
            {
                ad1.setChecked(true);
                addictive_box.setVisibility(View.VISIBLE);
                addictive_box.setText(obj2.getString("C3"));
            }
            if(obj2.getString("C4").equals(new String("No")))
            {
                al2.setChecked(true);
            }
            else
            {
                al1.setChecked(true);
                allergies_box.setVisibility(View.VISIBLE);
                allergies_box.setText(obj2.getString("C4"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onDisplay2()
    {
        if (str[1].equals(new String("Regular"))) {
            m1.setChecked(true);
        } else {
            m_box.setVisibility(View.VISIBLE);
            m2.setChecked(true);
            m_box.setText(str[1]);
        }
        if (str[2].equals(new String("Insignificant"))) {
            c2.setChecked(true);
        } else {
            c_box.setVisibility(View.VISIBLE);
            c1.setChecked(true);
            c_box.setText(str[2]);
        }
        if (str[3].equals(new String("Insignificant"))) {
            p2.setChecked(true);
        } else {
            p_box.setVisibility(View.VISIBLE);
            p1.setChecked(true);
            p_box.setText(str[3]);
        }
        if (str[4].equals(new String("Insignificant"))) {
            f2.setChecked(true);
        } else {
            f_box.setVisibility(View.VISIBLE);
            f1.setChecked(true);
            f_box.setText(str[4]);
        }
        if(str1[1].equals(new String("Veg")))
        {
            d1.setChecked(true);
        }
        else
        {
            d2.setChecked(true);
        }
        if(str1[2].equals(new String("Normal")))
        {
            s1.setChecked(true);
        }
        else
        {
            s2.setChecked(true);
        }
        if(str1[3].equals(new String("No")))
        {
            ad2.setChecked(true);
        }
        else
        {
            ad1.setChecked(true);
            addictive_box.setVisibility(View.VISIBLE);
            addictive_box.setText(str1[3]);
        }
        if(str1[4].equals(new String("No")))
        {
            al2.setChecked(true);
        }
        else
        {
            al1.setChecked(true);
            allergies_box.setVisibility(View.VISIBLE);
            allergies_box.setText(str1[4]);
        }
    }

}
