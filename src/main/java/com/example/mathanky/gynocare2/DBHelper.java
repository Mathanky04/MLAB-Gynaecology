package com.example.mathanky.gynocare2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="Gynaecology";
    public static final String TABLE1="general_information";
    public static final String COL1="Name";
    public static final String COL2="DateOfExamination";
    public static final String COL3="RegistrationNo";
    public static final String COL4="Age";
    public static final String COL5="Wo";
    public static final String COL6="Address";
    public static final String COL7="Mobile";
    public static final String COL8="Landline";
    public static final String TABLE2="obstetric_information";
    public static final String COL9="HistoryOfAmenorrheaMonths";
    public static final String COL10="HistoryOfAmenorrheaDays";
    public static final String COL11="GestationalAgeWeeks";
    public static final String COL12="Complaints";
    public static final String TABLE3="trimester1";
    public static final String COL13="Conception";
    public static final String COL14="HistoryAilments";
    public static final String COL15="HistoryExposure";
    public static final String COL16="Scan";
    public static final String TABLE4="trimester2";
    public static final String COL17="AnomalyScan";
    public static final String COL18="OtherHistory2";
    public static final String TABLE5="trimester3";
    public static final String COL19="GrowthScan";
    public static final String COL20="OtherHistory3";
    public static final String TABLE6="prophylaxis";
    public static final String COL21="FolicAcid";
    public static final String COL22="Iron";
    public static final String COL23="TTFirstDose";
    public static final String COL24="TTSecondDose";
    public static final String COL25="OtherDrugs";
    public static final String TABLE7="obstetric_score";
    public static final String COL26="G";
    public static final String COL27="P";
    public static final String COL28="A";
    public static final String COL29="L";
    public static final String COL30="D";
    public static final String TABLE8="delivery_history";
    public static final String COL31="ChildNo";
    public static final String COL32="DeliveryMode";
    public static final String COL33="Complications";
    public static final String COL34="Outcome";
    public static final String TABLE9="menstrual_history";
    public static final String COL35="Regularity";
    public static final String COL36="LMP";
    public static final String COL37="EDD";
    public static final String TABLE10="other_history";
    public static final String COL38="ContraceptiveHistory";
    public static final String COL39="PastHistory";
    public static final String COL40="FamilyHistory";
    public static final String TABLE11="personal_history";
    public static final String COL41="Diet";
    public static final String COL42="Sleep";
    public static final String COL43="AddictiveHabits";
    public static final String COL44="AllergiesKnown";
    public static final String COL45="AllergiesUnknown";
    public static final String TABLE12="general_physical_examination";
    public static final String COL46="Height";
    public static final String COL47="Weight";
    public static final String COL48="BMI";
    public static final String COL49="PR";
    public static final String COL50="BP";
    public static final String COL51="RR";
    public static final String COL52="Temp";
    public static final String COL53="Breasts";
    public static final String COL54="Nipples";
    public static final String COL55="Thyroid";
    public static final String COL56="Spine";
    public static final String COL57="Pallor";
    public static final String COL58="Edema";
    public static final String COL59="Jaundice";
    public static final String COL60="Cyanosis";
    public static final String COL61="Clubbing";
    public static final String COL62="Lymphadenopathy";
    public static final String TABLE13="systemic_examination";
    public static final String COL63="CVS";
    public static final String COL64="PA";
    public static final String COL65="SFH";
    public static final String COL66="AG";
    public static final String COL67="EFW";
    public static final String COL68="FHS";
    public static final String COL69="RS";
    public static final String COL70="Uterus";
    public static final String COL71="Lie";
    public static final String COL72="Presentation";
    public static final String COL73="Liquor";
    public static final String COL74="PreviousScar";
    public static final String TABLE14="investigation";
    public static final String COL75="Hb";
    public static final String COL76="BloodGroupingTyping";
    public static final String COL77="UrineRoutine";
    public static final String COL78="VDRL";
    public static final String COL79="HIV";
    public static final String COL80="HbsAg";
    public static final String COL81="USG";
    public static final String COL82="UpdateStatus";

    public DBHelper(Context context) {
        super(context,DATABASE_NAME,null,1);
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+TABLE1+"(RegistrationNo TEXT primary key, Name TEXT, DateOfExamination TEXT, Age TEXT, Wo TEXT, Address TEXT,Mobile TEXT,Landline TEXT,UpdateStatus TEXT DEFAULT 'No')");
        sqLiteDatabase.execSQL("create table "+TABLE2+"(RegistrationNo TEXT primary key , HistoryOfAmenorrheaMonths TEXT, HistoryOfAmenorrheaDays TEXT,GestationalAgeWeeks TEXT, Complaints TEXT,UpdateStatus TEXT DEFAULT 'No', FOREIGN KEY(RegistrationNo) REFERENCES general_information(RegistrationNo))");
        sqLiteDatabase.execSQL("create table "+TABLE3+"(RegistrationNo TEXT primary key, Conception TEXT, HistoryAilments TEXT,HistoryExposure TEXT,Scan TEXT,UpdateStatus TEXT DEFAULT 'No', FOREIGN KEY(RegistrationNo) REFERENCES general_information(RegistrationNo))");
        sqLiteDatabase.execSQL("create table "+TABLE4+"(RegistrationNo TEXT primary key,AnomalyScan TEXT,OtherHistory2 TEXT,UpdateStatus TEXT DEFAULT 'No',FOREIGN KEY(RegistrationNo) REFERENCES general_information(RegistrationNo))");
        sqLiteDatabase.execSQL("create table "+TABLE5+"(RegistrationNo TEXT primary key,GrowthScan TEXT,OtherHistory3 TEXT,UpdateStatus TEXT DEFAULT 'No',FOREIGN KEY(RegistrationNo) REFERENCES general_information(RegistrationNo))");
        sqLiteDatabase.execSQL("create table "+TABLE6+"(RegistrationNo TEXT primary key,FolicAcid TEXT, Iron TEXT, TTFirstDose TEXT, TTSecondDose TEXT, OtherDrugs TEXT,UpdateStatus TEXT DEFAULT 'No', FOREIGN KEY(RegistrationNo) REFERENCES general_information(RegistrationNo))");
        sqLiteDatabase.execSQL("create table "+TABLE7+"(RegistrationNo TEXT primary key, G TEXT ,P TEXT,A TEXT, L TEXT,D TEXT,UpdateStatus TEXT DEFAULT 'No',FOREIGN KEY(RegistrationNo) REFERENCES general_information(RegistrationNo))");
        sqLiteDatabase.execSQL("create table "+TABLE8+"(RegistrationNo TEXT, ChildNo TEXT, DeliveryMode TEXT, Complications TEXT, Outcome TEXT,UpdateStatus TEXT DEFAULT 'No', primary key(RegistrationNo,ChildNo),FOREIGN KEY(RegistrationNo) REFERENCES general_information(RegistrationNo))");
        sqLiteDatabase.execSQL("create table "+TABLE9+"(RegistrationNo TEXT primary key, Regularity TEXT, LMP TEXT, EDD TEXT,UpdateStatus TEXT DEFAULT 'No',FOREIGN KEY(RegistrationNo) REFERENCES general_information(RegistrationNo))");
        sqLiteDatabase.execSQL("create table "+TABLE10+"(RegistrationNo TEXT primary key, ContraceptiveHistory TEXT, PastHistory TEXT, FamilyHistory TEXT,UpdateStatus TEXT DEFAULT 'No',FOREIGN KEY(RegistrationNo) REFERENCES Table1(RegistrationNo))");
        sqLiteDatabase.execSQL("create table "+TABLE11+"(RegistrationNo TEXT primary key, Diet TEXT, Sleep TEXT, AddictiveHabits TEXT,AllergiesKnown TEXT, AllergiesUnknown TEXT,UpdateStatus TEXT DEFAULT 'No',FOREIGN KEY(RegistrationNo) REFERENCES general_information(RegistrationNo))");
        sqLiteDatabase.execSQL("create table "+TABLE12+"(RegistrationNo TEXT primary key, Height TEXT, Weight TEXT, BMI TEXT, PR TEXT, BP TEXT, RR TEXT, Temp TEXT, Breasts TEXT, Nipples TEXT, Thyroid TEXT, Spine TEXT, Pallor TEXT, Edema TEXT, Jaundice TEXT, Cyanosis TEXT, Clubbing TEXT, Lymphadenopathy TEXT,UpdateStatus TEXT DEFAULT 'No',FOREIGN KEY(RegistrationNo) REFERENCES general_information(RegistrationNo))");
        sqLiteDatabase.execSQL("create table "+TABLE13+"(RegistrationNo TEXT primary key , CVS TEXT,PA TEXT,SFH TEXT,AG TEXT,EFW TEXT,FHS TEXT,RS TEXT,Uterus TEXT,Lie TEXT,Presentation TEXT,Liquor TEXT,PreviousScar TEXT,UpdateStatus TEXT DEFAULT 'No',FOREIGN KEY(RegistrationNo) REFERENCES general_information(RegistrationNo))");
        sqLiteDatabase.execSQL("create table "+TABLE14+"(RegistrationNo TEXT primary key , Hb TEXT,BloodGroupingTyping TEXT,UrineRoutine TEXT,VDRL TEXT,HIV TEXT,HbsAg TEXT,USG TEXT,UpdateStatus TEXT DEFAULT 'No',FOREIGN KEY(RegistrationNo) REFERENCES general_information(RegistrationNo))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE if EXISTS "+TABLE1);
        sqLiteDatabase.execSQL("DROP TABLE if EXISTS "+TABLE2);
        sqLiteDatabase.execSQL("DROP TABLE if EXISTS "+TABLE3);
        sqLiteDatabase.execSQL("DROP TABLE if EXISTS "+TABLE4);
        sqLiteDatabase.execSQL("DROP TABLE if EXISTS "+TABLE5);
        sqLiteDatabase.execSQL("DROP TABLE if EXISTS "+TABLE6);
        sqLiteDatabase.execSQL("DROP TABLE if EXISTS "+TABLE7);
        sqLiteDatabase.execSQL("DROP TABLE if EXISTS "+TABLE8);
        sqLiteDatabase.execSQL("DROP TABLE if EXISTS "+TABLE9);
        sqLiteDatabase.execSQL("DROP TABLE if EXISTS "+TABLE10);
        sqLiteDatabase.execSQL("DROP TABLE if EXISTS "+TABLE11);
        sqLiteDatabase.execSQL("DROP TABLE if EXISTS "+TABLE12);
        sqLiteDatabase.execSQL("DROP TABLE if EXISTS "+TABLE13);
        sqLiteDatabase.execSQL("DROP TABLE if EXISTS "+TABLE14);
        onCreate(sqLiteDatabase);

    }
    public boolean insertData1(String name, String date, String registrationNo, String age, String wifeOf, String address, String mobile, String landline)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL1,name);
        contentValues.put(COL2,date);
        contentValues.put(COL3,registrationNo);
        contentValues.put(COL4,age);
        contentValues.put(COL5,wifeOf);
        contentValues.put(COL6,address);
        contentValues.put(COL7,mobile);
        contentValues.put(COL8,landline);
        contentValues.put(COL82,"No");
        long result=sqLiteDatabase.insert(TABLE1,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }

    public boolean insertData2(String regNo,String month, String day, String weeks, String comments)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL3,regNo);
        contentValues.put(COL9,month);
        contentValues.put(COL10,day);
        contentValues.put(COL11,weeks);
        contentValues.put(COL12,comments);
        contentValues.put(COL82,"No");
        long result=sqLiteDatabase.insert(TABLE2,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }

    public boolean insertData3(String regNo,String conception, String ailments, String exposure, String scan)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL3,regNo);
        contentValues.put(COL13,conception);
        contentValues.put(COL14,ailments);
        contentValues.put(COL15,exposure);
        contentValues.put(COL16,scan);
        contentValues.put(COL82,"No");
        long result=sqLiteDatabase.insert(TABLE3,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;

    }

    public boolean insertData4(String regNo, String anomalyScan, String otherHistory)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL3,regNo);
        contentValues.put(COL17,anomalyScan);
        contentValues.put(COL18,otherHistory);
        contentValues.put(COL82,"No");
        long result=sqLiteDatabase.insert(TABLE4,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;

    }

    public boolean insertData5(String regNo, String growthScan, String otherHistory2)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL3,regNo);
        contentValues.put(COL19,growthScan);
        contentValues.put(COL20,otherHistory2);
        contentValues.put(COL82,"No");
        long result=sqLiteDatabase.insert(TABLE5,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;

    }
    public boolean insertData6( String regNo,String checkF, String checkI, String checktt1, String checktt2, String checkA)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL3,regNo);
        contentValues.put(COL21,checkF);
        contentValues.put(COL22,checkI);
        contentValues.put(COL23,checktt1);
        contentValues.put(COL24,checktt2);
        contentValues.put(COL25,checkA);
        contentValues.put(COL82,"No");
        long result=sqLiteDatabase.insert(TABLE6,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }

    public boolean insertData7(String regNo, String G, String P,String A, String L, String D)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL3,regNo);
        contentValues.put(COL26,G);
        contentValues.put(COL27,P);
        contentValues.put(COL28,A);
        contentValues.put(COL29,L);
        contentValues.put(COL30,D);
        contentValues.put(COL82,"No");
        long result=sqLiteDatabase.insert(TABLE7,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;

    }

    public boolean insertData8(String regNo, String Slno, String mode,String comp, String outcome)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL3,regNo);
        contentValues.put(COL31,Slno);
        contentValues.put(COL32,mode);
        contentValues.put(COL33,comp);
        contentValues.put(COL34,outcome);
        contentValues.put(COL82,"No");
        long result=sqLiteDatabase.insert(TABLE8,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;

    }
    public boolean insertData9(String regNo,String checkR, String EDD, String LMP)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL3,regNo);
        contentValues.put(COL35,checkR);
        contentValues.put(COL36,EDD);
        contentValues.put(COL37,LMP);
        contentValues.put(COL82,"No");
        long result=sqLiteDatabase.insert(TABLE9,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }

    public boolean insertData10(String regNo, String contra, String past, String family)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL3,regNo);
        contentValues.put(COL38,contra);
        contentValues.put(COL39,past);
        contentValues.put(COL40,family);
        contentValues.put(COL82,"No");
        long result=sqLiteDatabase.insert(TABLE10,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }

    public boolean insertData11(String regNo, String diet, String sleep, String addictive, String aknown, String aunknown)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL3,regNo);
        contentValues.put(COL41,diet);
        contentValues.put(COL42,sleep);
        contentValues.put(COL43,addictive);
        contentValues.put(COL44,aknown);
        contentValues.put(COL45,aunknown);
        contentValues.put(COL82,"No");
        long result=sqLiteDatabase.insert(TABLE11,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }

    public boolean insertData12( String regNo,String height, String weight, String BMI,
                               String PR, String BP, String RR, String temp,
                               String Breasts, String Nip, String Thyroid,
                               String Spine, String Pallor, String edema,
                               String jaundice, String cyanosis, String clubbing, String lymphadenopathy)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL3,regNo);
        contentValues.put(COL46,height);
        contentValues.put(COL47,weight);
        contentValues.put(COL48,BMI);
        contentValues.put(COL49,PR);
        contentValues.put(COL50,BP);
        contentValues.put(COL51,RR);
        contentValues.put(COL52,temp);
        contentValues.put(COL53,Breasts);
        contentValues.put(COL54,Nip);
        contentValues.put(COL55,Thyroid);
        contentValues.put(COL56,Spine);
        contentValues.put(COL57,Pallor);
        contentValues.put(COL58,edema);
        contentValues.put(COL59,jaundice);
        contentValues.put(COL60,cyanosis);
        contentValues.put(COL61,clubbing);
        contentValues.put(COL62,lymphadenopathy);
        contentValues.put(COL82,"No");
        long result=sqLiteDatabase.insert(TABLE12,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }

    public boolean insertData13(String regNo,String CVS, String P, String SFH,
                               String AG, String EFW, String FHS, String RS,
                               String Uterus, String Lie, String Presentation,
                               String Liqour, String previousScar)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL3,regNo);
        contentValues.put(COL63,CVS);
        contentValues.put(COL64,P);
        contentValues.put(COL65,SFH);
        contentValues.put(COL66,AG);
        contentValues.put(COL67,EFW);
        contentValues.put(COL68,FHS);
        contentValues.put(COL69,RS);
        contentValues.put(COL70,Uterus);
        contentValues.put(COL71,Lie);
        contentValues.put(COL72,Presentation);
        contentValues.put(COL73,Liqour);
        contentValues.put(COL74,previousScar);
        contentValues.put(COL82,"No");
        long result=sqLiteDatabase.insert(TABLE13,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }

    public boolean insertData14(String regNo,String Hb, String BloodGroup, String urineRoutine,
                               String VDRL, String HIV, String HbsAg, String USG)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL3,regNo);
        contentValues.put(COL75,Hb);
        contentValues.put(COL76,BloodGroup);
        contentValues.put(COL77,urineRoutine);
        contentValues.put(COL78,VDRL);
        contentValues.put(COL79,HIV);
        contentValues.put(COL80,HbsAg);
        contentValues.put(COL81,USG);
        contentValues.put(COL82,"No");
        long result=sqLiteDatabase.insert(TABLE14,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }


}


