package com.tajmirkhan.tailormanagment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import static android.R.attr.id;
import static android.R.attr.name;
import static android.R.attr.version;
import static android.R.attr.width;
import static com.tajmirkhan.tailormanagment.DataBaseHelper.DATABASE_NAME;
import static com.tajmirkhan.tailormanagment.DataBaseHelper.ORDER_TABLE_NAME;
import static com.tajmirkhan.tailormanagment.DataBaseHelper.PANTSHIRT_TABLE_NAME;
import static com.tajmirkhan.tailormanagment.DataBaseHelper.SHALWARkMEEZ_TABLE_NAME;
import static com.tajmirkhan.tailormanagment.R.drawable.backhalflength;
import static com.tajmirkhan.tailormanagment.R.drawable.bicep;
import static com.tajmirkhan.tailormanagment.R.drawable.description;
import static com.tajmirkhan.tailormanagment.R.drawable.sleeve;

/**
 * Created by Tajmir khan on 12/11/2017.
 */

public class DatabaseFemale extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="F_TAILERMANAGMENT.db";
    public static final String SHALWARkMEEZ_TABLE_NAME="F_SHALWAR_KAMEEZ_DB";
    public static final String PANTSHIRT_TABLE_NAME="PANT_SHIRT_DB";
    public static final String SHALWAR_USERID="SHK_USERID";
    public static final String PANTSHIRT_USERID="PANT_USERID";
    public static final String SHALWAR_USER_NAME="NAME";
    public static final String SHALWAR_PHONE="PHONE";
    public static final String SHALWAR_SHIRT_LENGTH="LENGTH";
    public static final String SHALWAR_NECK="NECK";
    public static final String SHALWAR_SHOULDER="SHOULDER";
    public static final String SHALWAR_BUST="BUST";
    public static final String SHALWAR_KAMEEZ_WAIST="KAMEEZ_WAIST";
    public static final String SHALWAR_SHIRT_HIP="HIP";
    public static final String SHALWAR_SHIRT_BORDER="BORDER";
    public static final String SHALWAR_SLEEVE="SLEEVE";
    public static final String SHALWAR_UNDERARM="UNDERARM";
    public static final String SHALWAR_BICEP="BICEP";
    public static final String SHALWAR_LENGTH_OF_WRIST="WRIST";

    public static final String SHALWAR_WAIST="SHALWAR_WAIST";
    public static final String SHALWAR_LENGTHh="SHALWAR_LENGTH";
    public static final String SHALWAR_BOTTOM="BOTTOM";
    public static final String SHALWAR_DESCRIPTION="SHALWAR_DESCRIPTION";

    public static  final String SHIRT_JOCKET_WAIST="SHIRT_WAIST";
    public static  final String SHIRT_HIP="SHIRTHIP";


    public static  final String PANT_CALF="CALF";
    public static  final String PANT_ANKLE="ANKLE";
    public static  final String PANT_INSEAM="INSEAM";
    public static  final String CROTCH_WAIST="CROTCH_WAIST";
    public static  final String PANT_LENGTH="PANT_LENGTH";


    public static  final String PANT_CROTCH="CROTCH";
    public static  final String PANT_THIGH="PANTTHIGH";
    public static  final String PANT_KNEE="PANTKNEE";
    public static final String TOTAL_PRICE="TOTAL_COST";


    SQLiteDatabase db;
    Context context=null;

    public static final String IMAGE_TABLE_NAME="IMAGE_TABLE";
    public static final String IMAGE_ID="ID";
    public static final String IMAGE_NAME="NAME";
    public static final String IMAGES="IMAGE";
    public static final String FLAGE="FLAGE";


    public static final String ORDER_TABLE_NAME="ORDER_TABLE";
    public static final String ORDER_ID="ORDER_ID";
    public static final String QUANTITY="QUNATITY";
    public static final String PRICE="PRICE";
    public static final String CURRENT_TIME="CURRENT_TIME";
    public static final String DELIVERY_TIME="DELIVERY_TIME";
    public static final String DELIVERY_DATE="DELIVERY_DATE";

    public static final String MESSAGE_SEND="MESSAGE";


    public DatabaseFemale(Context context) {
        super(context,DATABASE_NAME,null,1);
        this.context=context;
        db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="create table "+SHALWARkMEEZ_TABLE_NAME+" (SHK_USERID INTEGER PRIMARY KEY, NAME TEXT, PHONE TEXT, LENGTH TEXT, NECK TEXT, SHOULDER TEXT, BUST TEXT, KAMEEZ_WAIST TEXT, HIP TEXT, BORDER TEXT, SLEEVE TEXT, UNDERARM TEXT, BICEP TEXT, WRIST TEXT, SHALWAR_WAIST TEXT, SHALWAR_LENGTH TEXT, BOTTOM TEXT, SHALWAR_DESCRIPTION TEXT)";
        String query2="create table "+PANTSHIRT_TABLE_NAME+" (PANT_USERID INTEGER PRIMARY KEY, NAME TEXT, PHONE TEXT, BUST TEXT, SHIRT_WAIST TEXT, HIP TEXT, SHOULDER TEXT, LENGTH TEXT, SLEEVE TEXT, BICEP TEXT, WRIST TEXT, SHALWAR_WAIST TEXT, PANTTHIGH TEXT, PANTKNEE TEXT, CALF TEXT, ANKLE TEXT, INSEAM TEXT, CROTCH_WAIST TEXT, PANT_LENGTH TEXT, SHALWAR_DESCRIPTION TEXT)";
        String query3="create table "+IMAGE_TABLE_NAME+"(DESIGNID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT,IMAGE BLOB,FLAGE TEXT)";
        String query4="create table "+ORDER_TABLE_NAME+" (ORDER_ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, QUANTITY TEXT, PRICE TEXT, CURRENT_TIME TEXT, DELIVERY_TIME TEXT, DELIVERY_DATE, SHK_USERID INTEGER, PANT_USERID INTEGER, ID INTEGER, PHONE TEXT ,MESSAGE TEXT ,TOTAL_COST TEXT, FOREIGN KEY (SHK_USERID) REFERENCES F_SHALWAR_KAMEEZ_DB (SHK_USERID),  FOREIGN KEY (PANT_USERID) REFERENCES PANT_SHIRT_DB (PANT_USERID), FOREIGN KEY (ID) REFERENCES IMAGE_TABLE (ID))";
        db.execSQL(query);
        db.execSQL(query2);
        db.execSQL(query3);
        db.execSQL(query4);
        int[] images={R.drawable.shalwar_function_4,R.drawable.shalwar_function_5};
        for(int i=0;i<images.length;i++){
            Bitmap bitmap= BitmapFactory.decodeResource(context.getResources(),images[i]);
            ByteArrayOutputStream stream=new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG,0,stream);
            byte[] byt=stream.toByteArray();
            ContentValues values = new ContentValues();
            values.put("NAME","elbowe");
            values.put("IMAGE",byt);
            values.put("FLAGE","0");
            try {
                db.insert(IMAGE_TABLE_NAME,"NAME",values);
            }
            catch (Exception x){
            }

        }
        }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+SHALWARkMEEZ_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+PANTSHIRT_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+IMAGE_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ORDER_TABLE_NAME);

        Log.e("dd","table is created now");
    }

    public boolean inserteFemaleShalwarKameez(String id,String name,String phone,String length,String neck,String shoulder,String bust,String kameez_waist,String hip,String border,String sleeve,String underarm,String bicep,String wrist,String shalwarwaist,String shalwarlength,String bottom,String shalwarDescription){
        String query="insert into "+SHALWARkMEEZ_TABLE_NAME+" (SHK_USERID,NAME,PHONE,LENGTH,NECK,SHOULDER,BUST,KAMEEZ_WAIST,HIP,BORDER,SLEEVE,UNDERARM,BICEP,WRIST,SHALWAR_WAIST,SHALWAR_LENGTH,BOTTOM,SHALWAR_DESCRIPTION) values ('"+id+"','"+name+"','"+phone+"','"+length+"','"+neck+"','"+shoulder+"','"+bust+"','"+kameez_waist+"','"+hip+"','"+border+"','"+sleeve+"','"+underarm+"','"+bicep+"','"+wrist+"','"+shalwarwaist+"','"+shalwarlength+"','"+bottom+"','"+shalwarDescription+"')";
        boolean str=false;

        try {
            str=true;
            db.execSQL(query);
        }
        catch (Exception x){
            Log.e("query",x.toString());
            str=false;
        }
        return str;

    }

    public boolean inserteFemalePantshirt(String id,String name,String phone,String bust,String shirt_waist,String hip,String shoulder,String shirtlength,String sleeve,String bicep,String wrist,String shalwarwaist,String pantthigh,String pantknee,String calf,String ankle,String inseam,String crotchwaist,String pantlength,String pantdiscription){
        String query2="insert into "+PANTSHIRT_TABLE_NAME+" (PANT_USERID,NAME,PHONE,BUST,SHIRT_WAIST,HIP,SHOULDER,LENGTH,SLEEVE,BICEP,WRIST,SHALWAR_WAIST,PANTTHIGH,PANTKNEE,CALF,ANKLE,INSEAM,CROTCH_WAIST,PANT_LENGTH,SHALWAR_DESCRIPTION) values ('"+id+"','"+name+"','"+phone+"','"+bust+"','"+shirt_waist+"','"+hip+"','"+shoulder+"','"+shirtlength+"','"+sleeve+"','"+bicep+"','"+wrist+"','"+shalwarwaist+"','"+pantthigh+"','"+pantknee+"','"+calf+"','"+ankle+"','"+inseam+"','"+crotchwaist+"','"+pantlength+"','"+pantdiscription+"')";
        boolean str=false;

        try {
            str=true;
            db.execSQL(query2);
        }
        catch (Exception x){
            Log.e("query",x.toString());
            str=false;
        }
        return str;
    }

    public Cursor femaleDataShowOfShalwarKameez()
    {
        String query="select * from "+SHALWARkMEEZ_TABLE_NAME;
        Cursor cr=db.rawQuery(query,null);

        return  cr;
    }

    public Cursor femaleDataShowOfPantshrit()
    {
        String query="select * from "+PANTSHIRT_TABLE_NAME;
        Cursor cr=db.rawQuery(query,null);

        return  cr;
    }

    public  boolean deletRecord(int check,String id)
    {
        String query="hell";
        boolean str=true;
        if(check==0) {
            query = "DELETE FROM F_SHALWAR_KAMEEZ_DB WHERE SHK_USERID='" + id + "'";
        }
        else if(check==1)
        {
            query = "DELETE FROM PANT_SHIRT_DB WHERE PANT_USERID='" + id + "'";

        }

        try {
            db.execSQL(query);
            str=true;
        }
        catch (Exception ex){
            str=false;
        }

        return str;
    }

    public Cursor spasificData(int flage,String id)
    {  Cursor cr;
        String query=null;
        if(flage==0) {
            query = "select * from " + SHALWARkMEEZ_TABLE_NAME + " where SHK_USERID= '" + id + "'";
        }
        else if(flage==1)
        {    query="select * from "+PANTSHIRT_TABLE_NAME+ " where PANT_USERID= '"+id+"'";
        }

        cr= db.rawQuery(query,null);

        return cr;
    }

    public boolean updataFemaleShalwarKameez(String id,String name,String phone,String length,String neck,String shoulder,String bust,String kameez_waist,String hip,String border,String sleeve,String underarm,String bicep,String wrist,String shalwarwaist,String shalwarlength,String bottom,String shalwarDescription)
    { boolean str=true;
        String query="update "+ SHALWARkMEEZ_TABLE_NAME +" set NAME='"+name+"',PHONE='"+phone+"',LENGTH='"+length+"',NECK='"+neck+"',SHOULDER='"+shoulder+"',BUST='"+bust+"',KAMEEZ_WAIST='"+kameez_waist+"',HIP='"+hip+"',BORDER='"+border+"',SLEEVE='"+sleeve+"',UNDERARM='"+underarm+"',BICEP='"+bicep+"',WRIST='"+wrist+"',SHALWAR_WAIST='"+shalwarwaist+"',SHALWAR_LENGTH='"+shalwarlength+"',BOTTOM='"+bottom+"',SHALWAR_DESCRIPTION='"+shalwarDescription+"'  where SHK_USERID="+id+"";
        try {
            db.execSQL(query);
            str=true;
        }
        catch (Exception ex)
        {
            str=false;
        }
        return str;
    }

    public boolean updataFemalePantshirt(String id,String name,String phone,String bust,String shirt_waist,String hip,String shoulder,String shirtlength,String sleeve,String bicep,String wrist,String shalwarwaist,String pantthigh,String pantknee,String calf,String ankle,String inseam,String crotchwaist,String pantlength,String pantdiscription)
    { boolean str=true;
        String query="update "+PANTSHIRT_TABLE_NAME+" set NAME='"+name+"',PHONE='"+phone+"',BUST='"+bust+"',SHIRT_WAIST='"+shirt_waist+"',HIP='"+hip+"',SHOULDER='"+shoulder+"',LENGTH='"+shirtlength+"',SLEEVE='"+sleeve+"',BICEP='"+bicep+"',WRIST='"+wrist+"',SHALWAR_WAIST='"+shalwarwaist+"',pantthigh='"+pantthigh+"',pantknee='"+pantknee+"',CALF='"+calf+"',ANKLE='"+ankle+"',INSEAM='"+inseam+"',CROTCH_WAIST='"+crotchwaist+"',PANT_LENGTH='"+pantlength+"',SHALWAR_DESCRIPTION='"+pantdiscription+"' where PANT_USERID="+id+"";
        try {
            db.execSQL(query);
            str=true;
        }
        catch (Exception ex)
        {
            str=false;
        }
        return str;
    }

    public Cursor imageDataShow()
    { Cursor cr=null;
        String query="select * from "+IMAGE_TABLE_NAME;
        try {
            cr = db.rawQuery(query, null);
        }
        catch (Exception ex)
        {
            Toast.makeText(context, "error is occur during ", Toast.LENGTH_SHORT).show();
        }
        return cr;
    }


    public void deletImageDesign()    // its remove it a time all the designs from the table
    {
        String query="Delete from "+IMAGE_TABLE_NAME;
        db.execSQL(query);
    }

    public boolean insertIntoImageTable(String name,byte[] image,String flage){

        boolean str=false;
        ContentValues values = new ContentValues();
        //values.put("ID",id);
        values.put("NAME",name);
        values.put("IMAGE",image);
        values.put("FLAGE",flage);
        try {
            db.insert(IMAGE_TABLE_NAME,"NAME",values);
            str=true;
        }
        catch (Exception x){
            str=false;
        }
        return str;
    }
    public void deletTheSpacificImageOfMale(String id)  //its remove the design one by one
    {
        String query="Delete from "+IMAGE_TABLE_NAME+" where DESIGNID ='"+id+"'";
        Log.e("ddd","deleted of item "+id);
        db.execSQL(query);

    }

    public boolean insertInToFemaleOrdertable(String name,String quantity,String price,String current_time,String delivery_time,String delivery_date,String shk_userid,String pant_userid,String imageid,String phone_number,String message_send,String total_cost)
    {
        String query="insert into "+ORDER_TABLE_NAME+ " (NAME,QUANTITY,PRICE,CURRENT_TIME,DELIVERY_TIME,DELIVERY_DATE,SHK_USERID,PANT_USERID,ID,PHONE,MESSAGE, TOTAL_COST) VALUES ('"+name+"','"+quantity+"','"+price+"','"+current_time+"','"+delivery_time+"','"+delivery_date+"','"+shk_userid+"','"+pant_userid+"','"+imageid+"','"+phone_number+"','"+message_send+"','"+total_cost+"')";
        boolean str=false;


        try {
            str=true;
            db.execSQL(query);
        }
        catch (Exception x){
            str=false;
        }
        return str;
    }


    public Cursor showOnOrderDataFemale(){
        Cursor cr;
        String query="select * from "+ORDER_TABLE_NAME+" where MESSAGE='ON';";
        cr= db.rawQuery(query,null);
        return cr;
    }


    public Cursor showOneSpacificImage(String id){
        String query="select IMAGE from "+IMAGE_TABLE_NAME+" where DESIGNID="+id;

        Cursor cr=db.rawQuery(query,null);
        return cr;
    }


    public void deletTheSpacificOrderTable(String id)  //its remove the design one by one
    {
        String query="Delete from "+ORDER_TABLE_NAME+" where ORDER_ID ='"+id+"'";
        Log.e("ddd","deleted of item "+id);
        db.execSQL(query);

    }

    public Cursor showDatandTimeDB(){
        Cursor cr;
        String query="select DELIVERY_TIME,DELIVERY_DATE,PHONE,ORDER_ID,MESSAGE from "+ORDER_TABLE_NAME+" where MESSAGE='ON'";
        cr=db.rawQuery(query,null);
        return cr;
    }
    public boolean replaceON_OFF(String id){
        boolean str=false;
        String query="UPDATE "+ORDER_TABLE_NAME+" SET MESSAGE = REPLACE(MESSAGE,'ON','OFF') WHERE ORDER_ID ='"+id+"';";
        try {
            db.execSQL(query);
           str=true;
        }catch (Exception e){
            str=false;
        }
        return str;
    }
    public Cursor showOffOrderDataFemale(){
        Cursor cr;
        String query="select * from "+ORDER_TABLE_NAME+" where MESSAGE='OFF';";
        cr= db.rawQuery(query,null);
        return cr;
    }
    public Cursor selectPhoneNumberOfFemale(String id){
        String query="select PHONE from "+ORDER_TABLE_NAME+" where ORDER_ID='"+id+"';";
        Cursor cr;
        cr=db.rawQuery(query,null);
        return cr;
    }

    public Cursor showOneSpacificOrderOfFemale(String id){
        String query="select * from "+ORDER_TABLE_NAME+" where ORDER_ID="+id;

        Cursor cr=db.rawQuery(query,null);
        return cr;
    }

    public Cursor showOneSpacificImageOfFemale(String id){
        String query="select IMAGE from "+IMAGE_TABLE_NAME+" where DESIGNID="+id;

        Cursor cr=db.rawQuery(query,null);
        return cr;
    }

    public boolean deletOneSpacificOrderOfFemale(String id){
        boolean str=false;
        String query="DELETE FROM "+ORDER_TABLE_NAME+" WHERE ORDER_ID = '"+id+"'";

        try {
            db.execSQL(query);
            str=true;
        }catch (Exception e){
            str=false;
        }
        return str;
    }

    public boolean updateTimeDate_OFF_ON_Female(String time,String date,String id){
        String query="UPDATE "+ORDER_TABLE_NAME+" SET DELIVERY_TIME = '"+time+"', DELIVERY_DATE= '"+date+"', MESSAGE='ON' WHERE ORDER_ID ='"+id+"';";
        boolean str=false;
        try {
            db.execSQL(query);
            str=true;
        }catch (Exception ex)
        {
            str=false;
        }
        return str;
    }
}
