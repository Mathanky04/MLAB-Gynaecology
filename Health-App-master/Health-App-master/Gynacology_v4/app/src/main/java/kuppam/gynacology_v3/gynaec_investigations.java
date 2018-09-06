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

import static kuppam.gynacology_v3.R.id.hiv;
import static kuppam.gynacology_v3.generalinfo.id;
import static kuppam.gynacology_v3.updateChoice.retrieve;
import static kuppam.gynacology_v3.updateChoice.selected;

public class gynaec_investigations extends AppCompatActivity {
    //String id="123";
    Button btnNext2;
    JSONObject ob;
    String[] str;
    String pid = "";
    EditText e1, e2, e3, e4;
    RadioGroup rg1, rg2, rg3, rg4, rg5, rg6, rg7, rg8, rg9, rg10, rg11, rg12, rg13;
    RadioButton selectedRadioButton, tc,tc1, pc,pc1, ps,ps1, b1, b2, b3, b4, b5, b6, b7, b8, a,a1, s,s1, m,m1, cs,cs1, ghiv,ghiv1, fbs,fbs1, ppbs,ppbs1, u,u1, pap,pap1;
    SQLiteDatabase database;
    String table_query = "patient_id TEXT ," +
            "gynaec_hb TEXT ," +
            "gynaec_tc TEXT ," +
            "gynaec_platelet_count TEXT ," +
            "gynaec_peripheral_smear TEXT ," +
            "gynaec_blood_grouping_rh_typing TEXT ," +
            "gynaec_albumin TEXT ," +
            "gynaec_sugar TEXT ," +
            "gynaec_microscopy TEXT ," +
            "gynaec_cs TEXT ," +
            "gynaec_hiv TEXT ," +
            "gynaec_hbsag TEXT ," +
            "gynaec_vdrl TEXT ," +
            "gynaec_rbs TEXT ," +
            "gynaec_fbs TEXT ," +
            "gynaec_ppbs TEXT ," +
            "gynaec_ultrasound TEXT ," +
            "gynaec_pap_smear TEXT ," +
            "update_status TEXT DEFAULT \"No\"," +
            "timestamp TEXT ," +
            "primary key(patient_id)," +
            "foreign key(patient_id) references general_information(patient_id)";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gynaec_investigations);
        e1 = (EditText) findViewById(R.id.g_Hb_inv_box);
        e2 = (EditText) findViewById(R.id.g_HbsAg);
        e3 = (EditText) findViewById(R.id.g_VDRL);
        e4 = (EditText) findViewById(R.id.g_RBS);
        rg1 = (RadioGroup) findViewById(R.id.g_TC);
        rg2 = (RadioGroup) findViewById(R.id.g_platelet_count);
        rg3 = (RadioGroup) findViewById(R.id.g_Peripheral_Smear);
        rg4 = (RadioGroup) findViewById(R.id.g_blood_grouping);
        rg5 = (RadioGroup) findViewById(R.id.g_albumin);
        rg6 = (RadioGroup) findViewById(R.id.g_Sugar);
        rg7 = (RadioGroup) findViewById(R.id.g_Microscopy);
        rg8 = (RadioGroup) findViewById(R.id.g_CS);
        rg9 = (RadioGroup) findViewById(R.id.g_HIV);
        rg10 = (RadioGroup) findViewById(R.id.g_FBS);
        rg11 = (RadioGroup) findViewById(R.id.g_PPBS);
        rg12 = (RadioGroup) findViewById(R.id.g_Ultrasound);
        rg13 = (RadioGroup) findViewById(R.id.g_Pap_Smear);
        tc = (RadioButton) findViewById(R.id.g_TC_normal);
        tc1 = (RadioButton) findViewById(R.id.g_TC_abnormal);
        pc = (RadioButton) findViewById(R.id.g_platelet_count_normal);
        pc1 = (RadioButton) findViewById(R.id.g_platelet_count_abnormal);
        ps = (RadioButton) findViewById(R.id.g_Peripheral_Smear_normal);
        ps1 = (RadioButton) findViewById(R.id.g_Peripheral_Smear_abnormal);
        b1 = (RadioButton) findViewById(R.id.g_Aplus);
        b2 = (RadioButton) findViewById(R.id.g_Aminus);
        b3 = (RadioButton) findViewById(R.id.g_Bplus);
        b4 = (RadioButton) findViewById(R.id.g_Bminus);
        b5 = (RadioButton) findViewById(R.id.g_Oplus);
        b6 = (RadioButton) findViewById(R.id.g_Ominus);
        b7 = (RadioButton) findViewById(R.id.g_ABplus);
        b8 = (RadioButton) findViewById(R.id.g_ABminus);
        a = (RadioButton) findViewById(R.id.g_albumin_normal);
        a1 = (RadioButton) findViewById(R.id.g_albumin_abnormal);
        s = (RadioButton) findViewById(R.id.g_Sugar_normal);
        s1 = (RadioButton) findViewById(R.id.g_Sugar_abnormal);
        m = (RadioButton) findViewById(R.id.g_Microscopy_normal);
        m1 = (RadioButton) findViewById(R.id.g_Microscopy_abnormal);
        cs = (RadioButton) findViewById(R.id.g_CS_normal);
        cs1 = (RadioButton) findViewById(R.id.g_CS_abnormal);
        ghiv = (RadioButton) findViewById(R.id.g_HIV_positive);
        ghiv1 = (RadioButton) findViewById(R.id.g_HIV_negative);
        fbs = (RadioButton) findViewById(R.id.g_FBS_positive);
        fbs1 = (RadioButton) findViewById(R.id.g_FBS_negative);
        ppbs = (RadioButton) findViewById(R.id.g_PPBS_positive);
        ppbs1 = (RadioButton) findViewById(R.id.g_PPBS_negative);
        u = (RadioButton) findViewById(R.id.g_Ultrasound_positive);
        u1 = (RadioButton) findViewById(R.id.g_Ultrasound_negative);
        pap = (RadioButton) findViewById(R.id.g_Pap_Smear_positive);
        pap1 = (RadioButton) findViewById(R.id.g_Pap_Smear_negative);
        //opening db
        database = openOrCreateDatabase("gynaecology", Context.MODE_PRIVATE, null);

        //creating table if doesn't exist
        database.execSQL("CREATE TABLE IF NOT EXISTS gynaec_investigations (" + table_query + ")");
        if(retrieve) {
            if (selected == "server") {
                try {
                    Bundle b = getIntent().getExtras();
                    System.out.println("Entered");
                    System.out.println(b.getString("JObj7"));
                    //JSONArray arr = new JSONArray(new String(responseBody));
                    ob = new JSONObject(b.getString("JObj7"));
                    System.out.println(ob);
                    System.out.println("Retreived::" + "Here");
                    JSONObject obj = (JSONObject) ob.get("gynaec_investigations");
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
                String selectQuery = "SELECT * FROM gynaec_investigations WHERE patient_id ='" + pid + "';";
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
                        String deleteQuery = "DELETE FROM gynaec_investigations where patient_id = " + "'" + pid + "'";
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
            String tc1 ="Abnormal",pc1="Abnormal",psr = "Abnormal",bg="Not Specified" ,
                    alb ="Abnormal",sg="Abnormal",mic="Abnormal",cs1="Abnormal",hv="Negative",fs="Abnormal",
                    ppb="Abnormal",ult="Abnormal",pps="Abnormal";
            if(tc.isChecked())
                tc1="Normal";
            if(pc.isChecked())
                pc1="Normal";
            if(ps.isChecked())
                psr="Normal";
            if(b1.isChecked())
                bg="A+";
            if(b2.isChecked())
                bg="A-";
            if(b3.isChecked())
                bg="B+";
            if(b4.isChecked())
                bg="B-";
            if(b5.isChecked())
                bg="O+";
            if(b6.isChecked())
                bg="O-";
            if(b7.isChecked())
                bg="AB+";
            if(b8.isChecked())
                bg="AB-";
            if(a.isChecked())
                alb="Normal";
            if(s.isChecked())
                sg="Normal";
            if(m.isChecked())
                mic="Normal";
            if(cs.isChecked())
                cs1="Normal";
            if(ghiv.isChecked())
                hv="Positive";
            if(fbs.isChecked())
                fs="Normal";
            if(ppbs.isChecked())
                ppb="Normal";
            if(u.isChecked())
                ult="Normal";
            if(ppbs.isChecked())
                pps="Normal";
            String insert_query = "'" + id.toString().trim() + "'," +
                    "'" + e1.getText().toString().trim() + "'," +
                    "'" + tc1 + "'," +
                    "'" + pc1 + "'," +
                    "'" + psr + "'," +
                    "'" + bg + "'," +
                    "'" + alb + "'," +
                    "'" + sg + "'," +
                    "'" + mic + "'," +
                    "'" + cs1 + "'," +
                    "'" + hv + "'," +
                    "'" + e2.getText().toString().trim() + "'," +
                    "'" + e3.getText().toString().trim() + "'," +
                    "'" + e4.getText().toString().trim() + "'," +
                    "'" + fs + "'," +
                    "'" + ppb + "'," +
                    "'" + ult + "'," +
                    "'" + pps + "'," +
                    "'" + "No" + "'," +
                    "'" + format.toString().trim() + "'";
            System.out.println("InsertQuery:" + insert_query);
            //inserting into database
            database.execSQL("INSERT INTO gynaec_investigations VALUES (" + insert_query + ")");
            Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_LONG).show();
            if(selected == "server")
            {
                Intent intent=new Intent(getApplicationContext(),planofcare.class);
                intent.putExtra("type","obs");
                intent.putExtra("JObj8",ob.toString());
                startActivity(intent);
            }
            else {
                Intent intent = new Intent(getApplicationContext(), planofcare.class);
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
        if (e1.getText().toString().equalsIgnoreCase("")) {
            e1.setError("Please enter a value");
            check = false;
        }

        if (Integer.parseInt(e1.getText().toString()) > 100) {
            e1.setError("Exceeds limit! Please enter a valid percentage");
            return false;
        }

        if (e2.getText().toString().equalsIgnoreCase("")) {
            e2.setError("Please enter a value");
            check = false;
        }

        if (e3.getText().toString().equalsIgnoreCase("")) {
            e3.setError("Please enter a value");
            check = false;
        }

        if (e4.getText().toString().equalsIgnoreCase("")) {
            e4.setError("Please enter a value");
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

        if (rg9.getCheckedRadioButtonId() == -1) {
            errMsg.append("Please select on option\n");
            check = false;
        }
        if (rg10.getCheckedRadioButtonId() == -1) {
            errMsg.append("Please select on option\n");
            check = false;
        }

        if (rg11.getCheckedRadioButtonId() == -1) {
            errMsg.append("Please select on option\n");
            check = false;
        }

        if (rg12.getCheckedRadioButtonId() == -1) {
            errMsg.append("Please select on option\n");
            check = false;
        }
        if (rg13.getCheckedRadioButtonId() == -1) {
            errMsg.append("Please select on option\n");
            check = false;
        }
        return check;
    }

    public void onDisplay()
    {
        try {
            JSONObject obj1 = (JSONObject) ob.get("gynaec_investigations");
            e1.setText(obj1.getString("C1"));
            if(obj1.getString("C2").equals(new String("Normal")))
            {
                tc.setChecked(true);
            }
            else
            {
                tc1.setChecked(true);
            }
            if(obj1.getString("C3").equals(new String("Normal")))
            {
                pc.setChecked(true);
            }
            else
            {
                pc1.setChecked(true);
            }
            if(obj1.getString("C4").equals(new String("Normal")))
            {
                ps.setChecked(true);
            }
            else
            {
                ps1.setChecked(true);
            }
            switch(obj1.getString("C5"))
            {
                case "A+":
                    b1.setChecked(true);
                    break;
                case "A-":
                    b2.setChecked(true);
                    break;
                case "B+":
                    b3.setChecked(true);
                    break;
                case "B-":
                    b4.setChecked(true);
                    break;
                case "O+":
                    b5.setChecked(true);
                    break;
                case "O-":
                    b6.setChecked(true);
                    break;
                case "AB+":
                    b7.setChecked(true);
                    break;
                case "AB-":
                    b8.setChecked(true);
                    break;
            }
            if(obj1.getString("C6").equals(new String("Normal")))
            {
                a.setChecked(true);
            }
            else
            {
                a1.setChecked(true);
            }
            if(obj1.getString("C7").equals(new String("Normal")))
            {
                s.setChecked(true);
            }
            else
            {
                s1.setChecked(true);
            }
            if(obj1.getString("C8").equals(new String("Normal")))
            {
                m.setChecked(true);
            }
            else
            {
                m1.setChecked(true);
            }
            if(obj1.getString("C8").equals(new String("Normal")))
            {
                cs.setChecked(true);
            }
            else
            {
                cs1.setChecked(true);
            }
            if(obj1.getString("C9").equals(new String("Normal")))
            {
                cs.setChecked(true);
            }
            else
            {
                cs1.setChecked(true);
            }
            if(obj1.getString("C10").equals(new String("Positive")))
            {
                ghiv.setChecked(true);
            }
            else
            {
                ghiv1.setChecked(true);
            }
            e2.setText(obj1.getString("C11"));
            e3.setText(obj1.getString("C12"));
            e4.setText(obj1.getString("C13"));
            if(obj1.getString("C14").equals(new String("Normal")))
            {
                fbs.setChecked(true);
            }
            else
            {
                fbs1.setChecked(true);
            }
            if(obj1.getString("C15").equals(new String("Normal")))
            {
                ppbs.setChecked(true);
            }
            else
            {
                ppbs1.setChecked(true);
            }
            if(obj1.getString("C16").equals(new String("Normal")))
            {
                u.setChecked(true);
            }
            else
            {
                u1.setChecked(true);
            }
            if(obj1.getString("C16").equals(new String("Normal")))
            {
                pap.setChecked(true);
            }
            else
            {
                pap1.setChecked(true);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onDisplay2()
    {
        e1.setText(str[1]);
        if(str[2].equals(new String("Normal")))
        {
            tc.setChecked(true);
        }
        else
        {
            tc1.setChecked(true);
        }
        if(str[3].equals(new String("Normal")))
        {
            pc.setChecked(true);
        }
        else
        {
            pc1.setChecked(true);
        }
        if(str[4].equals(new String("Normal")))
        {
            ps.setChecked(true);
        }
        else
        {
            ps1.setChecked(true);
        }
        switch(str[5])
        {
            case "A+":
                b1.setChecked(true);
                break;
            case "A-":
                b2.setChecked(true);
                break;
            case "B+":
                b3.setChecked(true);
                break;
            case "B-":
                b4.setChecked(true);
                break;
            case "O+":
                b5.setChecked(true);
                break;
            case "O-":
                b6.setChecked(true);
                break;
            case "AB+":
                b7.setChecked(true);
                break;
            case "AB-":
                b8.setChecked(true);
                break;
        }
        if(str[6].equals(new String("Normal")))
        {
            a.setChecked(true);
        }
        else
        {
            a1.setChecked(true);
        }
        if(str[7].equals(new String("Normal")))
        {
            s.setChecked(true);
        }
        else
        {
            s1.setChecked(true);
        }
        if(str[8].equals(new String("Normal")))
        {
            m.setChecked(true);
        }
        else
        {
            m1.setChecked(true);
        }
        if(str[9].equals(new String("Normal")))
        {
            cs.setChecked(true);
        }
        else
        {
            cs1.setChecked(true);
        }
        if(str[10].equals(new String("Normal")))
        {
            cs.setChecked(true);
        }
        else
        {
            cs1.setChecked(true);
        }
        if(str[11].equals(new String("Positive")))
        {
            ghiv.setChecked(true);
        }
        else
        {
            ghiv1.setChecked(true);
        }
        e2.setText(str[12]);
        e3.setText(str[13]);
        e4.setText(str[14]);
        if(str[15].equals(new String("Normal")))
        {
            fbs.setChecked(true);
        }
        else
        {
            fbs1.setChecked(true);
        }
        if(str[16].equals(new String("Normal")))
        {
            ppbs.setChecked(true);
        }
        else
        {
            ppbs1.setChecked(true);
        }
        if(str[17].equals(new String("Normal")))
        {
            u.setChecked(true);
        }
        else
        {
            u1.setChecked(true);
        }
        if(str[18].equals(new String("Normal")))
        {
            pap.setChecked(true);
        }
        else
        {
            pap1.setChecked(true);
        }
    }

}