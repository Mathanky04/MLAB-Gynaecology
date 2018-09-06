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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import static kuppam.gynacology_v3.generalinfo.id;
import static kuppam.gynacology_v3.updateChoice.retrieve;
import static kuppam.gynacology_v3.updateChoice.selected;

public class obstetric_score_past_history extends AppCompatActivity {

    SQLiteDatabase database;
    String table_obstetric_score="patient_id TEXT , g TEXT ,p TEXT ,a TEXT ,l TEXT ,d TEXT , update_status TEXT DEFAULT \"Not Specified\",timestamp TEXT , primary key(patient_id), foreign key(patient_id) references general_information(patient_id)";
    String table_past_obstetric_history = "patient_id TEXT ,pregnancy_order TEXT , term_preterm TEXT , mode_of_delivery TEXT , antenatal TEXT ,intrapartum TEXT ,postpartum TEXT , alive_dead TEXT , gender TEXT , baby_weight TEXT , present_age_months TEXT , present_age_days TEXT ,update_status TEXT DEFAULT \"No\", timestamp TEXT ,primary key(patient_id,pregnancy_order), foreign key(patient_id) references general_information(patient_id)";
    EditText g,p,a,l,d,preg_order,term,baby_wt,age_months,age_days;
    Button btnNext;
    RadioButton m1,m2,m3,c1,c2,s1,s2,g1,g2;
    CheckBox antenatal,intrapartum,postpartum;
    RadioGroup rg1,rg2,rg3,rg4;
    int count=1;
    JSONObject ob;
    String[] str;
    String str1[];
    String pid = "";
    String mode_a="Not Specified";
    String mode_i="Not Specified";
    String mode_p="Not Specified";
    String status="",gender="";
    //TextView t;
    String po=""+count+"";
    Button btnAdd;
    ScrollView sv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obstetric_score_past_history);
        database = openOrCreateDatabase("gynaecology", Context.MODE_PRIVATE, null);
        database.execSQL("CREATE TABLE IF NOT EXISTS obstetric_score(" + table_obstetric_score + ")");
        database.execSQL("CREATE TABLE IF NOT EXISTS past_obstetric_history(" + table_past_obstetric_history + ")");
        rg1=(RadioGroup)findViewById(R.id.complications);
        rg2 = (RadioGroup)findViewById(R.id.mode_of_delivery);
        rg3 = (RadioGroup)findViewById(R.id.status);
        rg4 = (RadioGroup)findViewById(R.id.gender);
        g=(EditText)findViewById(R.id.G_box);
        p=(EditText)findViewById(R.id.P_box);
        a=(EditText)findViewById(R.id.A_box);
        l=(EditText)findViewById(R.id.L_box);
        d=(EditText)findViewById(R.id.D_box);
        preg_order=(EditText)findViewById(R.id.pregnancy_order);
        preg_order.setText(po);
        term=(EditText)findViewById(R.id.term);
        baby_wt=(EditText)findViewById(R.id.baby_weight);
        age_months=(EditText)findViewById(R.id.baby_age_months);
        age_days=(EditText)findViewById(R.id.baby_age_days);
        m1=(RadioButton)findViewById(R.id.vaginal);
        m2=(RadioButton)findViewById(R.id.operative_vaginal);
        m3=(RadioButton)findViewById(R.id.cesarean);
        c1=(RadioButton)findViewById(R.id.complications_yes);
        c2=(RadioButton)findViewById(R.id.complications_no);
        antenatal=(CheckBox)findViewById(R.id.antenatal);
        intrapartum=(CheckBox)findViewById(R.id.intrapartum);
        postpartum=(CheckBox)findViewById(R.id.postpartum);
        s1=(RadioButton)findViewById(R.id.alive);
        s2=(RadioButton)findViewById(R.id.dead);
        g1=(RadioButton)findViewById(R.id.male);
        g2=(RadioButton)findViewById(R.id.female);
        btnNext=(Button)findViewById(R.id.next_page5);
        btnAdd=(Button)findViewById(R.id.add_child);
        sv=(ScrollView)findViewById(R.id.scrollView1);
        //t=(TextView)findViewById(R.id.past_obs_hist_title);
        antenatal.setVisibility(View.GONE);
        intrapartum.setVisibility(View.GONE);
        postpartum.setVisibility(View.GONE);
        //onButton();
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
                    JSONObject obj = (JSONObject) ob.get("obstetric_score");
                    JSONObject objx = (JSONObject) ob.get("past_obstetric_history");
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
                String selectQuery = "SELECT * FROM obstetric_score WHERE patient_id ='" + pid + "';";
                String selectQuery1 = "SELECT * FROM past_obstetric_history WHERE patient_id ='" + pid + "';";
                Cursor cursor = database.rawQuery(selectQuery, null);
                Cursor c = database.rawQuery(selectQuery1, null);
                if (cursor.moveToFirst()) {//cursor.moveToNext();
                    int totalColumn = cursor.getColumnCount();
                    do {
                        str = new String[totalColumn];
                        for (int i = 0; i < totalColumn; i++) {
                            str[i] = cursor.getString(i);
                            //System.out.println(str[i]);
                        }
                        //System.out.println(cursor.getString(0));
                    } while (cursor.moveToNext());
                }
                if (c.moveToLast()) {//cursor.moveToNext();
                    int totalColumn = c.getColumnCount();
                    do {
                        str1 = new String[totalColumn];
                        for (int i = 0; i < totalColumn; i++) {
                            str1[i] = c.getString(i);
                            //System.out.println(str[i]);
                        }
                        onDisplay2();
                        String deleteQuery1 = "DELETE FROM obstetric_score where patient_id = " + "'" + pid + "'";
                        Log.d("query", deleteQuery1);
                        database.execSQL(deleteQuery1);
                        //System.out.println(cursor.getString(0));
                        String deleteQuery = "DELETE FROM past_obstetric_history where patient_id = " + "'" + pid + "'";
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
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(ValidationSuccess())
                {
                    String mod="";
                    po=""+count+"";
                    preg_order.setText(po);
                    if(m1.isChecked())
                        mod="Vaginal";
                    else if(m2.isChecked())
                        mod="Operative Vaginal";
                    else if(m3.isChecked())
                        mod="Cesarean";
                    if(c1.isChecked())
                    {
                        if(antenatal.isChecked())
                            mode_a="Yes";
                        if(intrapartum.isChecked())
                            mode_i="Yes";
                        if(postpartum.isChecked())
                            mode_p="Yes";
                    }
                    if(s1.isChecked())
                        status="Alive";
                    else if(s2.isChecked())
                        status="Dead";
                    if(g1.isChecked())
                        gender="Male";
                    else if(g2.isChecked())
                        gender="Female";
                    String insert_obstetric_score="'" + id.toString().trim() + "'," + "'" + g.getText().toString().trim() + "'," + "'" + p.getText().toString().trim() + "'," + "'" + a.getText().toString().trim() + "'," + "'" + l.getText().toString().trim() + "'," + "'" + d.getText().toString().trim() +"','"+"No"+"','"+format.toString().trim()+"'";
                    String insert_past_obstetric_history="'" + id.toString().trim() + "'," + "'" + preg_order.getText().toString().trim() + "'," + "'" + term.getText().toString().trim() + "'," + "'" + mod.trim() + "'," + "'" + mode_a.trim() + "'," + "'" + mode_i.trim() + "'," + "'" + mode_p.trim() + "'," + "'" + status.trim() + "'," + "'" + gender.trim()+"','"+baby_wt.getText().toString().trim()+"','"+age_months.getText().toString()+"','"+ age_days.getText().toString().trim()+"','"+"No"+"','" + format.toString().trim() + "'";

                    System.out.println("InsertQuery:" + insert_obstetric_score);
                    System.out.println("InsertQuery:" + insert_past_obstetric_history);
                    //inserting into database
                    database.execSQL("INSERT INTO obstetric_score VALUES (" + insert_obstetric_score + ")");
                    database.execSQL("INSERT INTO past_obstetric_history VALUES (" +insert_past_obstetric_history+" )");
                    Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_LONG).show();
                    if(selected == "server")
                    {
                        Intent intent=new Intent(getApplicationContext(),histories.class);
                        intent.putExtra("type","obs");
                        intent.putExtra("JObj4",ob.toString());
                        startActivity(intent);
                    }
                    else {
                        Intent intent = new Intent(getApplicationContext(), histories.class);
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
    public void onAddChild(View view)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
        final String format = simpleDateFormat.format(new Date());
        if(ValidationSuccess()) {
            String mod = "";
            String mode_a = "Not Specified";
            String mode_i = "Not Specified";
            String mode_p = "Not Specified";
            String status = "", gender = "";
            if (m1.isChecked())
                mod = "Vaginal";
            else if (m2.isChecked())
                mod = "Operative Vaginal";
            else if (m3.isChecked())
                mod = "Cesarean";
            if (c1.isChecked()) {
                if (antenatal.isChecked())
                    mode_a = "Yes";
                else
                    mode_a = "No";
                if (intrapartum.isChecked())
                    mode_i = "Yes";
                else
                    mode_i = "No";
                if (postpartum.isChecked())
                    mode_p = "Yes";
                else
                    mode_p = "No";
            }
            if (s1.isChecked())
                status = "Alive";
            else if (s2.isChecked())
                status = "Dead";
            if (g1.isChecked())
                gender = "Male";
            else if (g2.isChecked())
                gender = "Female";
            String insert_past_obstetric_history = "'" + id.toString().trim() + "'," + "'" + preg_order.getText().toString().trim() + "'," + "'" + term.getText().toString().trim() + "'," + "'" + mod.trim() + "'," + "'" + mode_a.trim() + "'," + "'" + mode_i.trim() + "'," + "'" + mode_p.trim() + "'," + "'" + status.trim() + "'," + "'" + gender.trim() + "','" + baby_wt.getText().toString().trim() + "','" + age_months.getText().toString() + "','" + age_days.getText().toString().trim() + "','" + "No" + "','" + format.toString().trim() + "'";
            System.out.println("InsertQuery:" + insert_past_obstetric_history);
            database.execSQL("INSERT INTO past_obstetric_history VALUES (" + insert_past_obstetric_history + " )");
            count=count+1;
            po=""+count+"";
            preg_order.setText(po);
            term.setText("");
            baby_wt.setText("");
            age_months.setText("");
            age_days.setText("");
            rg1.clearCheck();
            rg2.clearCheck();
            rg3.clearCheck();
            rg4.clearCheck();
            if(antenatal.isChecked())
                antenatal.setChecked(false);
            if(intrapartum.isChecked())
                intrapartum.setChecked(false);
            if(postpartum.isChecked())
                postpartum.setChecked(false);
            focusOnView();
        }
    }

    private final void focusOnView(){
        sv.post(new Runnable() {
            @Override
            public void run() {
                sv.scrollTo(0,d.getBottom());
            }
        });
    }

    private boolean ValidationSuccess(){

        boolean check=true;
        StringBuilder errMsg = new StringBuilder("");
        if (g.getText().toString().equalsIgnoreCase("")){
            g.setError("Please enter a value");
            check=false;
        }

        if (p.getText().toString().equalsIgnoreCase("")){
            p.setError("Please enter a value");
            check=false;
        }

        if (a.getText().toString().equalsIgnoreCase("")){
            a.setError("Please enter a value");
            check=false;
        }

        if (l.getText().toString().equalsIgnoreCase("")){
            l.setError("Please enter a value");
            check=false;
        }

        if (d.getText().toString().equalsIgnoreCase("")){
            d.setError("Please enter a value");
            check=false;
        }

        if (term.getText().toString().equalsIgnoreCase("")){
            term.setError("Please enter a value");
            check=false;
        }

        if (baby_wt.getText().toString().equalsIgnoreCase("")){
            baby_wt.setError("Please enter a value");
            check=false;
        }

        if (age_days.getText().toString().equalsIgnoreCase("")){
            age_days.setError("Please enter a value");
            check=false;
        }

        if (Integer.parseInt(age_days.getText().toString())>31){
            age_days.setError("Exceeds limit! Please enter a valid number of days less than 31");
            return false;
        }


        if (age_months.getText().toString().equalsIgnoreCase("")){
            age_months.setError("Please enter a value");
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

        if(c1.isChecked())
        {
            if (!(antenatal.isChecked() || intrapartum.isChecked() || postpartum.isChecked())){
                antenatal.setError("Please select an option");
                check=false;
            }

        }
        return check;
    }

    public void click(View view)
    {
        antenatal.setVisibility(View.VISIBLE);
        intrapartum.setVisibility(View.VISIBLE);
        postpartum.setVisibility(View.VISIBLE);
    }

    public void click1(View view)
    {
        antenatal.setVisibility(View.GONE);
        intrapartum.setVisibility(View.GONE);
        postpartum.setVisibility(View.GONE);
    }

    public void onDisplay()
    {
        try {
            JSONObject obj1 = (JSONObject) ob.get("obstetric_score");
            JSONObject obj2 = (JSONObject) ob.get("past_obstetric_history");
            g.setText(obj1.getString("C1"));
            p.setText(obj1.getString("C2"));
            a.setText(obj1.getString("C3"));
            l.setText(obj1.getString("C4"));
            d.setText(obj1.getString("C5"));
            if(obj2.length() == 14)
            {
                preg_order.setText(obj2.getString("C1"));
                term.setText(obj2.getString("C2"));
                if (obj2.getString("C3").equals(new String("Vaginal"))) {
                    m1.setChecked(true);
                } else if (obj2.getString("C3").equals(new String("Operative Vaginal"))) {
                    m2.setChecked(true);
                } else {
                    m3.setChecked(true);
                }
                if (obj2.getString("C4").equals(new String("Not Specified")) && obj2.getString("C5").equals(new String("Not Specified"))
                        && obj2.getString("C6").equals(new String("Not Specified"))) {
                    c2.setChecked(true);
                } else {
                    c1.setChecked(true);
                    antenatal.setVisibility(View.VISIBLE);
                    intrapartum.setVisibility(View.VISIBLE);
                    postpartum.setVisibility(View.VISIBLE);
                    if (!(obj2.getString("C4").equals(new String("Not Specified")))) {
                        antenatal.setChecked(true);
                    }
                    if (!(obj2.getString("C5").equals(new String("Not Specified")))) {
                        intrapartum.setChecked(true);
                    }
                    if (!(obj2.getString("C6").equals(new String("Not Specified")))) {
                        postpartum.setChecked(true);
                    }
                }
                if (obj2.getString("C7").equals(new String("Alive"))) {
                    s1.setChecked(true);
                } else {
                    s2.setChecked(true);
                }
                if (obj2.getString("C8").equals(new String("Male"))) {
                    g1.setChecked(true);
                } else {
                    g2.setChecked(true);
                }
                baby_wt.setText(obj2.getString("C9"));
                age_months.setText(obj2.getString("C10"));
                age_days.setText(obj2.getString("C11"));
            }
            else{
                JSONObject obj21 = (JSONObject) obj2.get("1");
                preg_order.setText(obj21.getString("C1"));
                term.setText(obj21.getString("C2"));
                if (obj21.getString("C3").equals(new String("Vaginal"))) {
                    m1.setChecked(true);
                } else if (obj21.getString("C3").equals(new String("Operative Vaginal"))) {
                    m2.setChecked(true);
                } else {
                    m3.setChecked(true);
                }
                if (obj21.getString("C4").equals(new String("Not Specified")) && obj21.getString("C5").equals(new String("Not Specified"))
                        && obj21.getString("C6").equals(new String("Not Specified"))) {
                    c2.setChecked(true);
                } else {
                    c1.setChecked(true);
                    antenatal.setVisibility(View.VISIBLE);
                    intrapartum.setVisibility(View.VISIBLE);
                    postpartum.setVisibility(View.VISIBLE);
                    if (!(obj21.getString("C4").equals(new String("Not Specified")))) {
                        antenatal.setChecked(true);
                    }
                    if (!(obj21.getString("C5").equals(new String("Not Specified")))) {
                        intrapartum.setChecked(true);
                    }
                    if (!(obj21.getString("C6").equals(new String("Not Specified")))) {
                        postpartum.setChecked(true);
                    }
                }
                if (obj21.getString("C7").equals(new String("Alive"))) {
                    s1.setChecked(true);
                } else {
                    s2.setChecked(true);
                }
                if (obj21.getString("C8").equals(new String("Male"))) {
                    g1.setChecked(true);
                } else {
                    g2.setChecked(true);
                }
                baby_wt.setText(obj21.getString("C9"));
                age_months.setText(obj21.getString("C10"));
                age_days.setText(obj21.getString("C11"));
            }

        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void onDisplay2()
    {
            g.setText(str[1]);
            p.setText(str[2]);
            a.setText(str[3]);
            l.setText(str[4]);
            d.setText(str[5]);
            preg_order.setText(str1[1]);
            term.setText(str1[2]);
            if(str1[3].equals(new String("Vaginal")))
            {
                m1.setChecked(true);
            }
            else if(str1[3].equals(new String("Operative Vaginal")))
            {
                m2.setChecked(true);
            }
            else
            {
                m3.setChecked(true);
            }
            if(str1[4].equals(new String("Not Specified")) && str1[5].equals(new String("Not Specified"))
                    && str1[6].equals(new String("Not Specified")))
            {
                c2.setChecked(true);
            }
            else
            {
                c1.setChecked(true);
                antenatal.setVisibility(View.VISIBLE);
                intrapartum.setVisibility(View.VISIBLE);
                postpartum.setVisibility(View.VISIBLE);
                if(!(str1[4].equals(new String("Not Specified"))))
                {
                    antenatal.setChecked(true);
                }
                if(!(str1[5].equals(new String("Not Specified"))))
                {
                    intrapartum.setChecked(true);
                }
                if(!(str1[6].equals(new String("Not Specified"))))
                {
                    postpartum.setChecked(true);
                }
            }
            if(str1[7].equals(new String("Alive")))
            {
                s1.setChecked(true);
            }
            else
            {
                s2.setChecked(true);
            }
            if(str1[8].equals(new String("Male")))
            {
                g1.setChecked(true);
            }
            else
            {
                g2.setChecked(true);
            }
            baby_wt.setText(str1[9]);
            age_months.setText(str1[10]);
            age_days.setText(str1[11]);
        }

}
