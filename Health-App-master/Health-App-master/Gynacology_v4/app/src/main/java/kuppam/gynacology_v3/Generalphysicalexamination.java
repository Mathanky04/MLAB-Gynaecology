package kuppam.gynacology_v3;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class Generalphysicalexamination extends AppCompatActivity {

    private EditText e1,e2,e3,e4,e5,e6, e7, e8, e9,h,w,bmi,pr,bp;
    RadioGroup rg1,rg2,rg3,rg4,rg5,rg6,rg7,rg8,rg9,rg10,rg11,rg12;
    RadioButton selectedRadioButton,selectedRadioButton1, sr2, sr3, sr4, sr5, sr6, sr7, sr8, sr9, sr10,sr12,t1,t2,p1,p2,p3,sr13,
            sr21,sr31,sr41,sr51,sr61,sr71,sr81,sr91,sr11;
    String t="Afebrile",b="Normal",np="Normal",th="Normal",sp="Normal",ic="NOT SPECIFIED",pl="Absent",
            ed="Absent",cy="Absent",clb="Absent",lym="Absent";
    JSONObject ob;
    String[] str;
    String pid = "";
    SQLiteDatabase database;
    //String id="1234";
    String table_query = "patient_id TEXT ," +
            " height TEXT ," +
            " weight TEXT ," +
            " bmi_pre_pregnancy TEXT ," +
            " pr TEXT ," +
            " bp TEXT , " +
            "temp TEXT ," +
            " breasts TEXT ," +
            "nipples TEXT ," +
            "thyroid TEXT ," +
            "spine TEXT ,i" +
            "cterus TEXT ," +
            "pallor TEXT ," +
            "edema TEXT , " +
            "cyanosis TEXT ," +
            "clubbing TEXT ," +
            "lymphadenopathy TEXT ," +
            "update_status TEXT DEFAULT \"No\"," +
            "timestamp TEXT , " +
            "primary key(patient_id), " +
            "foreign key(patient_id) references general_information(patient_id)";
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generalphysicalexamination);
        h = (EditText) findViewById(R.id.height);
        w = (EditText) findViewById(R.id.weight);
        bmi = (EditText) findViewById(R.id.bmi);
        pr = (EditText) findViewById(R.id.pr);
        bp = (EditText) findViewById(R.id.bp);
        e1 = (EditText) findViewById(R.id.breasts_box);
        e2 = (EditText) findViewById(R.id.nipples_box);
        e3 = (EditText) findViewById(R.id.thyroid_box);
        e4 = (EditText) findViewById(R.id.spine_box);
        e5 = (EditText) findViewById(R.id.icterus_box);
        rg11 = (RadioGroup)findViewById(R.id.pallor_positive);
        e6 = (EditText) findViewById(R.id.edema_box);
        e7 = (EditText) findViewById(R.id.cyanosis_box);
        e8 = (EditText) findViewById(R.id.clubbing_box);
        e9 = (EditText) findViewById(R.id.lymphadenopathy_box);
        p1 =(RadioButton)findViewById(R.id.pallor_single) ;
        p2 =(RadioButton)findViewById(R.id.pallor_double) ;
        p3 =(RadioButton)findViewById(R.id.pallor_triple) ;
        rg1 = (RadioGroup) findViewById(R.id.breasts);
        selectedRadioButton1 = (RadioButton) findViewById(R.id.breasts_normal);
        selectedRadioButton = (RadioButton) findViewById(R.id.breasts_abnormal);
        rg2 = (RadioGroup) findViewById(R.id.nipples);
        sr2 = (RadioButton) findViewById(R.id.nipples_retracted);
        sr21 = (RadioButton) findViewById(R.id.nipples_normal);
        rg3 = (RadioGroup) findViewById(R.id.thyroid);
        sr3 = (RadioButton) findViewById(R.id.thyroid_abnormal);
        sr31 = (RadioButton) findViewById(R.id.thyroid_normal);
        rg4 = (RadioGroup) findViewById(R.id.spine);
        sr4 = (RadioButton) findViewById(R.id.spine_abnormal);
        sr41 = (RadioButton) findViewById(R.id.spine_normal);
        rg5 = (RadioGroup) findViewById(R.id.icterus);
        sr5 = (RadioButton) findViewById(R.id.icterus_present);
        sr51 = (RadioButton) findViewById(R.id.icterus_absent);
        rg6 = (RadioGroup) findViewById(R.id.pallor);
        sr6 = (RadioButton) findViewById(R.id.pallor_present);
        sr61 = (RadioButton) findViewById(R.id.pallor_absent);
        rg7 = (RadioGroup) findViewById(R.id.edema);
        sr7 = (RadioButton) findViewById(R.id.edema_present);
        sr71 = (RadioButton) findViewById(R.id.edema_absent);
        rg8 = (RadioGroup) findViewById(R.id.cyanosis);
        sr8 = (RadioButton) findViewById(R.id.cyanosis_present);
        sr81 = (RadioButton) findViewById(R.id.cyanosis_absent);
        rg9 = (RadioGroup) findViewById(R.id.clubbing);
        sr9 = (RadioButton) findViewById(R.id.clubbing_present);
        sr91 = (RadioButton) findViewById(R.id.clubbing_absent);
        rg10 = (RadioGroup) findViewById(R.id.lymphadenopathy);
        sr10 = (RadioButton) findViewById(R.id.lymphadenopathy_present);
        sr11 = (RadioButton) findViewById(R.id.lymphadenopathy_absent);
        rg12 = (RadioGroup) findViewById(R.id.temp);
        sr12 = (RadioButton)findViewById(R.id.febrile);
        sr13 = (RadioButton)findViewById(R.id.afebrile);
        e1.setVisibility(View.GONE);
        e2.setVisibility(View.GONE);
        e3.setVisibility(View.GONE);
        e4.setVisibility(View.GONE);
        e5.setVisibility(View.GONE);
        e6.setVisibility(View.GONE);
        e7.setVisibility(View.GONE);
        e8.setVisibility(View.GONE);
        e9.setVisibility(View.GONE);
        p1.setVisibility(View.GONE);
        p2.setVisibility(View.GONE);
        p3.setVisibility(View.GONE);
        //rg11.setVisibility(View.GONE);
        //opening db
        database = openOrCreateDatabase("gynaecology", Context.MODE_PRIVATE, null);

        //creating table if doesn't exist
        database.execSQL("CREATE TABLE IF NOT EXISTS general_physical_examination (" + table_query + ")");
        if(retrieve) {
            if (selected == "server") {
                try {
                    Bundle b = getIntent().getExtras();
                    System.out.println("Entered");
                    //JSONArray arr = new JSONArray(new String(responseBody));
                    ob = new JSONObject(b.getString("JObj5"));
                    System.out.println(ob);
                    System.out.println("Retreived::" + "Here");
                    JSONObject obj = (JSONObject) ob.get("general_physical_examination");
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
                String selectQuery = "SELECT * FROM general_physical_examination WHERE patient_id ='" + pid + "';";
                Cursor cursor = database.rawQuery(selectQuery, null);
                if (cursor.moveToFirst()) {//cursor.moveToNext();
                    int totalColumn = cursor.getColumnCount();
                    do {
                        str = new String[totalColumn];
                        for (int i = 0; i < totalColumn; i++) {
                            str[i] = cursor.getString(i);
                        }
                        onDisplay2();
                        //System.out.println(cursor.getString(0));
                        String deleteQuery = "DELETE FROM general_physical_examination where patient_id = " + "'" + pid + "'";
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
            if(sr12.isChecked())
                t="Febrile";
            if(selectedRadioButton.isChecked())
                b=e1.getText().toString().trim();
            if(sr2.isChecked())
                np=e2.getText().toString().trim();
            if(sr3.isChecked())
                th=e3.getText().toString().trim();
            if(sr4.isChecked())
                sp=e4.getText().toString().trim();
            if(sr5.isChecked())
                ic=e5.getText().toString().trim();
            if(sr6.isChecked())
            {
                if(p1.isChecked())
                    pl="+";
                else if(p2.isChecked())
                    pl="++";
                else if(p3.isChecked())
                    pl="+++";
            }
            if(sr7.isChecked())
                ed=e6.getText().toString().trim();
            if(sr8.isChecked())
                cy=e7.getText().toString().trim();
            if(sr9.isChecked())
                clb=e8.getText().toString().trim();
            if(sr10.isChecked())
                lym=e9.getText().toString().trim();



            String insert_query ="'" + id.toString().trim() + "'," +
                    "'" + h.getText().toString().trim() + "'," +
                    "'" + w.getText().toString().trim() + "'," +
                    "'" + bmi.getText().toString().trim() + "'," +
                    "'" + pr.getText().toString().trim() + "'," +
                    "'" + bp.getText().toString().trim() + "'," +
                    "'" + t + "'," +
                    "'" + b + "'," +
                    "'" + np + "'," +
                    "'" + th + "'," +
                    "'" + sp + "'," +
                    "'" + ic + "'," +
                    "'" + pl + "'," +
                    "'" + ed + "'," +
                    "'" + cy + "'," +
                    "'" + clb + "'," +
                    "'" + lym + "'," +
                    "'" + "No" + "'," +
                    "'" + format.toString().trim() + "'";
            System.out.println("InsertQuery:" + insert_query);
            //inserting into database
            database.execSQL("INSERT INTO general_physical_examination VALUES (" + insert_query + ")");

            Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_LONG).show();
            if(selected == "server")
            {
                Intent intent=new Intent(getApplicationContext(),obs_systemic_examination.class);
                intent.putExtra("type","obs");
                intent.putExtra("JObj6",ob.toString());
                startActivity(intent);
            }
            else {
                Intent intent = new Intent(getApplicationContext(), obs_systemic_examination.class);
                intent.putExtra("pid",pid);
                startActivity(intent);
            }

        } else {
            Toast.makeText(getApplicationContext(), "Please check the details", Toast.LENGTH_LONG).show();
        }
        database.close();


    }

    private boolean ValidationSuccess() {

        boolean check = true;
        StringBuilder errMsg = new StringBuilder("");
        e1 = (EditText) findViewById(R.id.height);
        if (e1.getText().toString().equalsIgnoreCase("")) {
            e1.setError("Please enter a value");
            check = false;
        }

        e1 = (EditText) findViewById(R.id.weight);
        if (e1.getText().toString().equalsIgnoreCase("")) {
            e1.setError("Please enter a value");
            check = false;
        }

        e1 = (EditText) findViewById(R.id.pr);
        if (e1.getText().toString().equalsIgnoreCase("")) {
            e1.setError("Please enter a value");
            check = false;
        }

        e1 = (EditText) findViewById(R.id.bp);
        if (e1.getText().toString().equalsIgnoreCase("")) {
            e1.setError("Please enter a value");
            check = false;
        }

        if (rg1.getCheckedRadioButtonId() == -1) {
            errMsg.append("Please select on option\n");
            check = false;
        }

        if (rg12.getCheckedRadioButtonId() == -1) {
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

        if (selectedRadioButton.isChecked()) {
            e1 = (EditText) findViewById(R.id.breasts_box);
            if (e1.getText().toString().equalsIgnoreCase("")) {
                e1.setError("Please enter a value");
                check = false;
            }
        }

        if (sr2.isChecked()) {
            e2 = (EditText) findViewById(R.id.nipples_box);
            if (e2.getText().toString().equalsIgnoreCase("")) {
                e2.setError("Please enter a value");
                check = false;
            }
        }

        if (sr3.isChecked()) {
            e3 = (EditText) findViewById(R.id.thyroid_box);
            if (e3.getText().toString().equalsIgnoreCase("")) {
                e3.setError("Please enter a value");
                check = false;
            }
        }

        if (sr4.isChecked()) {
            e4 = (EditText) findViewById(R.id.spine_box);
            if (e4.getText().toString().equalsIgnoreCase("")) {
                e4.setError("Please enter a value");
                check = false;
            }
        }

        if (sr5.isChecked()) {
            e5 = (EditText) findViewById(R.id.icterus_box);
            if (e5.getText().toString().equalsIgnoreCase("")) {
                e5.setError("Please enter a value");
                check = false;
            }
        }

        if (sr6.isChecked()) {
            rg11 = (RadioGroup) findViewById(R.id.pallor_positive);
            if (rg11.getCheckedRadioButtonId() == -1) {
                errMsg.append("Please select on option\n");
                check = false;
            }
        }

        if (sr7.isChecked()) {
            e5 = (EditText) findViewById(R.id.edema_box);
            if (e5.getText().toString().equalsIgnoreCase("")) {
                e5.setError("Please enter a value");
                check = false;
            }
        }

        if (sr8.isChecked()) {
            e5 = (EditText) findViewById(R.id.cyanosis_box);
            if (e5.getText().toString().equalsIgnoreCase("")) {
                e5.setError("Please enter a value");
                check = false;
            }
        }

        if (sr9.isChecked()) {
            e5 = (EditText) findViewById(R.id.clubbing_box);
            if (e5.getText().toString().equalsIgnoreCase("")) {
                e5.setError("Please enter a value");
                check = false;
            }
        }

        if (sr10.isChecked()) {
            e5 = (EditText) findViewById(R.id.lymphadenopathy_box);
            if (e5.getText().toString().equalsIgnoreCase("")) {
                e5.setError("Please enter a value");
                check = false;
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
        p1.setVisibility(View.VISIBLE);
        p2.setVisibility(View.VISIBLE);
        p3.setVisibility(View.VISIBLE);
    }
    public void click11(View view)
    {
        p1.setVisibility(View.GONE);
        p2.setVisibility(View.GONE);
        p3.setVisibility(View.GONE);
    }

    public void click12(View view)
    {
        e6.setVisibility(View.VISIBLE);
    }
    public void click13(View view)
    {
        e6.setVisibility(View.GONE);
    }

    public void click14(View view)
    {
        e7.setVisibility(View.VISIBLE);
    }
    public void click15(View view)
    {
        e7.setVisibility(View.GONE);
    }

    public void click16(View view)
    {
        e8.setVisibility(View.VISIBLE);
    }
    public void click17(View view)
    {
        e8.setVisibility(View.GONE);
    }

    public void click18(View view)
    {
        e9.setVisibility(View.VISIBLE);
    }
    public void click19(View view)
    {
        e9.setVisibility(View.GONE);
    }
    public void onDisplay()
    {
        try {
            JSONObject obj1 = (JSONObject) ob.get("general_physical_examination");
            h.setText(obj1.getString("C1"));
            w.setText(obj1.getString("C2"));
            bmi.setText(obj1.getString("C3"));
            pr.setText(obj1.getString("C4"));
            bp.setText(obj1.getString("C5"));
            if(obj1.getString("C6").equals(new String("Febrile")))
            {
                sr12.setChecked(true);
            }
            else
            {
                sr13.setChecked(true);
            }
            if(obj1.getString("C7").equals(new String("Normal")))
            {
                selectedRadioButton1.setChecked(true);
            }
            else
            {
                selectedRadioButton.setChecked(true);
                e1.setVisibility(View.VISIBLE);
                e1.setText(obj1.getString("C7"));
            }
            if(obj1.getString("C8").equals(new String("Normal")))
            {
                sr21.setChecked(true);
            }
            else
            {
                sr2.setChecked(true);
                e2.setText(obj1.getString("C8"));
                e2.setVisibility(View.VISIBLE);
            }
            if(obj1.getString("C9").equals(new String("Normal")))
            {
                sr31.setChecked(true);
            }
            else
            {
                sr3.setChecked(true);
                e3.setText(obj1.getString("C9"));
                e3.setVisibility(View.VISIBLE);
            }
            if(obj1.getString("C10").equals(new String("Normal")))
            {
                sr41.setChecked(true);
            }
            else
            {
                sr4.setChecked(true);
                e4.setText(obj1.getString("C10"));
                e4.setVisibility(View.VISIBLE);
            }
            if(obj1.getString("C11").equals(new String("Normal")))
            {
                sr51.setChecked(true);
            }
            else
            {
                sr5.setChecked(true);
                e5.setText(obj1.getString("C11"));
                e5.setVisibility(View.VISIBLE);
            }
            if(obj1.getString("C12").equals(new String("Absent")))
            {
                sr61.setChecked(true);
            }
            else if(obj1.getString("C12").equals(new String("+")))
            {
                sr6.setChecked(true);
                p1.setChecked(true);
                p1.setVisibility(View.VISIBLE);
            }
            else if(obj1.getString("C12").equals(new String("+")))
            {
                sr6.setChecked(true);
                p2.setChecked(true);
                p2.setVisibility(View.VISIBLE);
            }
            else
            {
                sr6.setChecked(true);
                p3.setChecked(true);
                p3.setVisibility(View.VISIBLE);
            }
            if(obj1.getString("C13").equals(new String("Absent")))
            {
                sr71.setChecked(true);
            }
            else
            {
                sr7.setChecked(true);
                e6.setText(obj1.getString("C13"));
                e6.setVisibility(View.VISIBLE);
            }
            if(obj1.getString("C14").equals(new String("Absent")))
            {
                sr81.setChecked(true);
            }
            else
            {
                sr8.setChecked(true);
                e7.setText(obj1.getString("C14"));
                e7.setVisibility(View.VISIBLE);
            }
            if(obj1.getString("C15").equals(new String("Absent")))
            {
                sr91.setChecked(true);
            }
            else
            {
                sr9.setChecked(true);
                e8.setText(obj1.getString("C15"));
                e8.setVisibility(View.VISIBLE);
            }
            if(obj1.getString("C16").equals(new String("Absent")))
            {
                sr11.setChecked(true);
            }
            else
            {
                sr10.setChecked(true);
                e9.setText(obj1.getString("C16"));
                e9.setVisibility(View.VISIBLE);
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onDisplay2()
    {
        h.setText(str[1]);
        w.setText(str[2]);
        bmi.setText(str[3]);
        pr.setText(str[4]);
        bp.setText(str[5]);
        if(str[6].equals(new String("Febrile")))
        {
            sr12.setChecked(true);
        }
        else
        {
            sr13.setChecked(true);
        }
        if(str[7].equals(new String("Normal")))
        {
            selectedRadioButton1.setChecked(true);
        }
        else
        {
            selectedRadioButton.setChecked(true);
            e1.setVisibility(View.VISIBLE);
            e1.setText(str[7]);
        }
        if(str[8].equals(new String("Normal")))
        {
            sr21.setChecked(true);
        }
        else
        {
            sr2.setChecked(true);
            e2.setText(str[8]);
            e2.setVisibility(View.VISIBLE);
        }
        if(str[9].equals(new String("Normal")))
        {
            sr31.setChecked(true);
        }
        else
        {
            sr3.setChecked(true);
            e3.setText(str[9]);
            e3.setVisibility(View.VISIBLE);
        }
        if(str[10].equals(new String("Normal")))
        {
            sr41.setChecked(true);
        }
        else
        {
            sr4.setChecked(true);
            e4.setText(str[10]);
            e4.setVisibility(View.VISIBLE);
        }
        if(str[11].equals(new String("Normal")))
        {
            sr51.setChecked(true);
        }
        else
        {
            sr5.setChecked(true);
            e5.setText(str[11]);
            e5.setVisibility(View.VISIBLE);
        }
        if(str[12].equals(new String("Absent")))
        {
            sr61.setChecked(true);
        }
        else if(str[12].equals(new String("+")))
        {
            sr6.setChecked(true);
            p1.setChecked(true);
            p1.setVisibility(View.VISIBLE);
        }
        else if(str[12].equals(new String("+")))
        {
            sr6.setChecked(true);
            p2.setChecked(true);
            p2.setVisibility(View.VISIBLE);
        }
        else
        {
            sr6.setChecked(true);
            p3.setChecked(true);
            p3.setVisibility(View.VISIBLE);
        }
        if(str[13].equals(new String("Absent")))
        {
            sr71.setChecked(true);
        }
        else
        {
            sr7.setChecked(true);
            e6.setText(str[13]);
            e6.setVisibility(View.VISIBLE);
        }
        if(str[14].equals(new String("Absent")))
        {
            sr81.setChecked(true);
        }
        else
        {
            sr8.setChecked(true);
            e7.setText(str[14]);
            e7.setVisibility(View.VISIBLE);
        }
        if(str[15].equals(new String("Absent")))
        {
            sr91.setChecked(true);
        }
        else
        {
            sr9.setChecked(true);
            e8.setText(str[15]);
            e8.setVisibility(View.VISIBLE);
        }
        if(str[16].equals(new String("Absent")))
        {
            sr11.setChecked(true);
        }
        else
        {
            sr10.setChecked(true);
            e9.setText(str[16]);
            e9.setVisibility(View.VISIBLE);
        }

    }
}
