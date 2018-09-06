package kuppam.gynacology_v3;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import cz.msebera.android.httpclient.Header;

import static kuppam.gynacology_v3.choice.checker1;
import static kuppam.gynacology_v3.choice.checker2;
import static kuppam.gynacology_v3.trimester_choice.trimester;

//import android.app.ProgressDialog;

public class updateServer extends AppCompatActivity {

    // private ProgressDialog prDialog;
    ProgressDialog prgDialog;
    SQLiteDatabase database;
    Button btnHome,btnSync;
    int count;
    public static final String[] tables = {"","general_information","doctor_table","obstetric_information",
            "investigations","obstetric_score","past_obstetric_history","history_table",
            "personal_history","general_physical_examination","systemic_examination","first_trimester","second_trimester","third_trimester",
            "gynaec_complaints","gynaec_obstetric_history", "gynaec_past_history","gynaec_family_history","gynaec_personal_history",
            "gynaec_physical_examination","gynaec_systemic_examination", "gynaec_investigations","gynaec_care"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_server);
        btnHome=(Button)findViewById(R.id.button2);
        btnSync = (Button)findViewById(R.id.sync);
        prgDialog = new ProgressDialog(this);
        prgDialog.setMessage("Syncing SQLite Data with Remote MySQL DB. Please wait...");
        prgDialog.setCancelable(false);
        //opening db
        database = openOrCreateDatabase("gynaecology", Context.MODE_PRIVATE, null);
        Toast.makeText(getApplicationContext(), getSyncStatus(), Toast.LENGTH_LONG).show();
        //onBtnHome();
    }

    @Override
    public void onBackPressed() {

    }

    public void onBtnHome(View view)
    {
        checker1 = false;
        checker2 = false;
        trimester = 0;
        //btnHome.setOnClickListener(new View.OnClickListener() {
        //@Override
        //public void onClick(View view) {
        Intent intent=new Intent(getApplicationContext(),doctor_login.class);
        startActivity(intent);
    }

    public void onSync(View view)
    {
        syncSQLiteMySQLDB();
    }

    public void syncSQLiteMySQLDB(){
        //Create AsyncHttpClient object
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        ArrayList<HashMap<String, String>> patientList = getAllPatients();
        if(patientList.size()!=0){
            System.out.println(dbSyncCount());
            if(dbSyncCount() != 0){
                prgDialog.show();
                params.put("tablesJSON"+1,composeJSONfromSQLite(tables[1]));
                params.put("tablesJSON"+2,composeJSONfromSQLite(tables[2]));
                if(checker1)
                {
                    params.put("start",3);
                    params.put("stop",10);
                    for(int i =3;i<=10;i++){
                        params.put("tablesJSON"+i,composeJSONfromSQLite(tables[i]));
                    }
                    if(trimester == 1)
                    {
                        params.put("trimester",11);
                        params.put("tablesJSON"+11,composeJSONfromSQLite(tables[11]));
                    }
                    else if(trimester == 2)
                    {
                        params.put("trimester",12);
                        params.put("tablesJSON"+12,composeJSONfromSQLite(tables[12]));
                    }
                    else
                    {
                        params.put("trimester",13);
                        params.put("tablesJSON"+13,composeJSONfromSQLite(tables[13]));
                    }

                }
                else
                {
                    params.put("start",14);
                    params.put("stop",22);
                    for(int i =14;i<=22;i++){
                        params.put("tablesJSON"+i,composeJSONfromSQLite(tables[i]));
                    }
                    //params.put("tablesJSON"+23,"Hello");
                }
                Log.d("** JSON SENT **", params.toString());
                client.post("http://192.168.43.13/gyno/updateUser.php",params ,new AsyncHttpResponseHandler() {
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        // System.out.println("hello"+response);
                        prgDialog.hide();
                        System.out.print("Here: ");
                        System.out.println(new String(responseBody));
                        if (responseBody != null && responseBody.length > 0) {
                            try {
                                System.out.println("Entered");
                                JSONArray arr = new JSONArray(new String(responseBody));
                                System.out.println(arr.length());
                                for (int i = 0; i < arr.length(); i++) {
                                    JSONObject obj = (JSONObject) arr.get(i);
                                    System.out.println(obj.get("RegistrationNo"));
                                    System.out.println(obj.get("updateStatus"));
                                    System.out.println(obj.get("tableName"));
                                    updateSyncStatus(obj.get("RegistrationNo").toString(), obj.get("updateStatus").toString(),obj.get("tableName").toString());
                                    System.out.println("Insync " + obj.get("RegistrationNo").toString() + " : " + obj.get("updateStatus").toString());
                                }
                                Toast.makeText(getApplicationContext(), "DB Sync completed!", Toast.LENGTH_LONG).show();
                            } catch (JSONException e) {
                                Toast.makeText(getApplicationContext(), "Error Occurred [Server's JSON response might be invalid]!", Toast.LENGTH_LONG).show();
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        // TODO Auto-generated method stub
                        prgDialog.hide();
                        if(statusCode == 404){
                            Toast.makeText(getApplicationContext(), "Requested resource not found", Toast.LENGTH_LONG).show();
                        }else if(statusCode == 500){
                            Toast.makeText(getApplicationContext(), "Something went wrong at server end", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "Unexpected Error occurred! [Most common Error: Device might not be connected to Internet]", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }else{
                Toast.makeText(getApplicationContext(), "SQLite and Remote MySQL DBs are in Sync!", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(getApplicationContext(), "No data in SQLite DB, please do enter User name to perform Sync action", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Get list of Users from SQLite DB as Array List
     *
     * @return
     */
    public ArrayList<HashMap<String, String>> getAllPatients() {
        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " +tables[1]+ ";";
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put("pid", cursor.getString(0));
                map.put("name", cursor.getString(1));
                wordList.add(map);
            } while (cursor.moveToNext());
        }
        cursor.close();
        //database.close();
        return wordList;
    }

    /**
     * Compose JSON out of SQLite records
     *
     * @return
     */
    public String composeJSONfromSQLite(String table_name) {
        ArrayList<HashMap<String, String>> patientList;
        patientList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM "+ table_name+" where update_status = 'No';";
        Cursor cursor = database.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        do {
            int totalColumn = cursor.getColumnCount();
            LinkedHashMap<String, String> map = new LinkedHashMap<>();
            for (int i = 0; i < totalColumn; i++) {
                map.put(cursor.getColumnName(i), cursor.getString(i));
                //System.out.println("'"+cursor.getColumnName(i) +":" + cursor.getString(i)+"'");
            }
            patientList.add(map);
        } while (cursor.moveToNext());
        cursor.close();
        Gson gson = new GsonBuilder().create();
        //System.out.println("LULZ"+js);
        //database.close();
        return gson.toJson(patientList);

    }

    /**
     * Get Sync status of SQLite
     *
     * @return
     */
    public String getSyncStatus() {
        String msg;
        if (this.dbSyncCount() == 0) {
            msg = "SQLite and Remote MySQL DBs are in Sync!";
        } else {
            msg = "DB Sync needed";
        }
        return msg;
    }

    /**
     * Get SQLite records that are yet to be Synced
     *
     * @return
     */
    public int dbSyncCount() {
        String selectQuery = "SELECT  * FROM " +tables[1]+ " where update_status = 'No';";
        Cursor a = database.rawQuery(selectQuery, null);
        count = a.getCount();
        a.close();
        //database.close();
        return count;
    }

    /**
     * Update Sync status against each User ID
     *
     * @param pid
     * @param updateStatus
     */
    public void updateSyncStatus(String pid, String updateStatus, String table_name) {
        String updateQuery = "Update "+ table_name +" set update_status = '" + updateStatus + "' where patient_id = " + "'" + pid + "'";
        Log.d("query", updateQuery);
        database.execSQL(updateQuery);
        //database.close();
    }


}
