package com.tajmirkhan.tailormanagment;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import static android.R.attr.bitmap;
import static android.R.attr.id;
import static android.R.attr.name;
import static android.R.attr.width;
import static android.R.id.message;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static com.tajmirkhan.tailormanagment.R.drawable.backhalflength;
import static com.tajmirkhan.tailormanagment.R.drawable.bicep;
import static com.tajmirkhan.tailormanagment.R.drawable.sleeve;
import static com.tajmirkhan.tailormanagment.R.id.bottom;
import static com.tajmirkhan.tailormanagment.R.id.image;
import static java.sql.Types.VARCHAR;

/**
 * Created by Tajmir khan on 10/5/2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="TAILERMANAGMENT.db";
    public static final String SHALWARkMEEZ_TABLE_NAME="SHALWAR_KAMEEZ_DB";
    public static final String PANTSHIRT_TABLE_NAME="PANT_SHIRT_DB";
    public static final String SHALWAR_USERID="SHK_USERID";
    public static final String PANTSHIRT_USERID="PANT_USERID";
    public static final String SHALWAR_USER_NAME="NAME";
    public static final String SHALWAR_PHONE="PHONE";
    public static final String SHALWAR_SHIRT_LENGTH="LENGTH";
    public static final String SHALWAR_SLEEVE="SLEEVE";
    public static final String SHALWAR_SHOULDER="SHOULDER";
    public static final String SHALWAR_CHEST="CHEST";
    public static final String SHALWAR_UNDERARM="UNDERARM";
    public static final String SHALWAR_NECK="NECK";
    public static final String SHALWAR_SHIRT_WAIST="WAIST";
    public static final String SHALWAR_WIDTH="WIDTH";
    public static final String SHALWAR_ABDOMEN="ABDOMEN";
    public static final String SHALWAR_BICEP="BICEP";
    public static final String SHALWAR_LENGTH_OF_WRIST="WRIST";
    public static final String SHALWAR_LENGTHh="SHALWAR_LENGTH";
    public static final String SHALWAR_WAIST="SHALWAR_WAIST";
    public static final String SHALWAR_PANCHA="PANCHA";
    public static final String SHALWAR_BIN_COLLAR="BIN_COLLOR";
    public static final String SHALWAR_FRONT_PACKET="FRONT_PACKET";
    public static final String SHALWAR_PACKET="SS_PACKET";
    public static final String SHALWAR_SIDE_PACKET="SIDE_PACKET";
    public static final String SHALWAR_DESCRIPTION="SHALWAR_DESCRIPTION";


    public static  final String SHIRT_JOCKET_WAIST="JOCKET_WAIST";
    public static  final String SHIRT_HIP="SHIRTHIP";
    public static  final String HALF_SHOULDER="HALFSHOULDER";
    public static  final String SHIRTBACKFULL_LENGTH="BACKFULL_LENGTH";
    public static  final String SHIRTBACKFALF_LENGTH="BACKHALF_LENGTH";
    public static  final String PANTINSIDE_LENGTH="INSIDE_LENGTH";
    public static  final String PANT_CROTCH="CROTCH";
    public static  final String PANT_THIGH="PANTTHIGH";
    public static  final String PANT_KNEE="PANTKNEE";


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
    public static final String TOTAL_PRICE="TOTAL_COST";

    SQLiteDatabase db;
    Context context=null;


    public DataBaseHelper(Context context) {

        super(context, DATABASE_NAME,null,1);
        this.context=context;
        db=this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String query="create table "+SHALWARkMEEZ_TABLE_NAME+" (SHK_USERID INTEGER PRIMARY KEY, NAME TEXT, PHONE TEXT, LENGTH TEXT, SLEEVE TEXT, SHOULDER TEXT, CHEST TEXT, UNDERARM TEXT, NECK TEXT, WAIST TEXT, WIDTH TEXT, ABDOMEN TEXT, BICEP TEXT, WRIST TEXT, SHALWAR_LENGTH TEXT, SHALWAR_WAIST TEXT, PANCHA TEXT, BIN_COLLOR TEXT, FRONT_PACKET TEXT, SS_PACKET TEXT, SIDE_PACKET TEXT, SHALWAR_DESCRIPTION TEXT)";
        String query2="create table "+PANTSHIRT_TABLE_NAME+"(PANT_USERID INTEGER PRIMARY KEY, NAME TEXT, PHONE TEXT, NECK TEXT, CHEST TEXT, SHOULDER TEXT, SLEEVE TEXT, BICEP TEXT, WRIST TEXT, JOCKET_WAIST TEXT, SHIRTHIP TEXT, LENGTH TEXT, WAIST TEXT, HALFSHOULDER TEXT, BACKFULL_LENGTH TEXT, BACKHALF_LENGTH TEXT, SHALWAR_WAIST TEXT, SHALWAR_LENGTH TEXT, INSIDE_LENGTH TEXT, CROTCH TEXT, PANTTHIGH TEXT, PANTKNEE TEXT, BIN_COLLOR TEXT, FRONT_PACKET TEXT, SHALWAR_DESCRIPTION TEXT)";
        String query3="create table "+IMAGE_TABLE_NAME+"(DESIGNID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT,IMAGE BLOB,FLAGE TEXT)";
        String query4="create table "+ORDER_TABLE_NAME+" (ORDER_ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, QUANTITY TEXT, PRICE TEXT, CURRENT_TIME TEXT, DELIVERY_TIME TEXT, DELIVERY_DATE, SHK_USERID INTEGER, PANT_USERID INTEGER, ID INTEGER, PHONE TEXT, MESSAGE TEXT, TOTAL_COST TEXT, FOREIGN KEY (SHK_USERID) REFERENCES SHALWAR_KAMEEZ_DB (SHK_USERID),  FOREIGN KEY (PANT_USERID) REFERENCES PANT_SHIRT_DB (PANT_USERID), FOREIGN KEY (ID) REFERENCES IMAGE_TABLE (ID))";

        db.execSQL(query);
        db.execSQL(query2);
        Log.e("dd","table is creating");
        db.execSQL(query3);
        db.execSQL(query4);
       Log.e("db","database is created");
         int[] images={R.drawable.shalwarkameez_fuctional_2,R.drawable.shalwarkameez_fuctional_1,R.drawable.shalwar_function_3,R.drawable.shalwar_function_4,
         R.drawable.shalwar_function_5,R.drawable.shalwar_function_6,R.drawable.shalwar_function_7,R.drawable.shalwar_function_8,R.drawable.shalwar_function_10,R.drawable.shalwar_kameez11,R.drawable.shalwar_kameez12};
        for(int i=0;i<images.length;i++) {
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),images[i]);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
            byte[] bytearry = stream.toByteArray();
            ContentValues values = new ContentValues();
            values.put("NAME","elbowe");
            values.put("IMAGE",bytearry);
            values.put("FLAGE","0");
            try {
                db.insert(IMAGE_TABLE_NAME,"NAME",values);
            }
            catch (Exception x){
            }

        }

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+SHALWARkMEEZ_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+PANTSHIRT_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+IMAGE_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ORDER_TABLE_NAME);
        Log.e("db","database is created");


    }


    public Cursor dataShowOfShalwarKameez()
    {
        String query="select * from "+SHALWARkMEEZ_TABLE_NAME;
        Cursor cr=db.rawQuery(query,null);

        Log.e("db","bellow the show metho");
        return  cr;
    }

    public Cursor datashowOfPantShirt(){
        String query="select * from "+PANTSHIRT_TABLE_NAME;
        Cursor cr=db.rawQuery(query,null);
        return cr;
    }

public  boolean insertDataOfPantShirt(String id,String name,String phone, String neck,String chest,String shoulder,String sleeve,String bicep,String wrist,String jocket_waist,String shirthip,String length, String waist,String halfshoulder,String backfull,String backhalflength,String shalwarwaist,String shalwarlength,String insidelength,String crotch,String pantthigh,String knee,String bin_collar,String frontPacket,String shalwarDescription)
{
  //String query="insert into "+PANTSHIRT_TABLE_NAME+"(USERID,NAME,PHONE,NECK,CHEST,SHOULDER,SLEEVE,BICEP,WRIST,JOCKET_WAIST,SHIRTHIP,LENGTH,WAIST,HALFSHOULDER,BACKFULL_LENGTH,BACKHALF_LENGTH,SHALWAR_WAIST,SHALWAR_LENGTH,INSIDE_LENGTH,CROTCH,PANTTHIGH,PANTKNEE,BIN_COLLOR,FRONT_PACKET,SHALWAR_DESCRIPTION) Values ('"+id+"','"+name+"','"+phone+"','"+neck+"','"+chest+"','"+shoulder+"','"+sleeve+"','"+bicep+"','"+wrist+"','"+jocket_waist+"','"+shirthip+"','"+length+"','"+waist+"','"+halfshoulder+"','"+backfull+"','"+backhalflength+"','"+shalwarwaist+"','"+shalwarlength+"','"+insidelength+"'','"+crotch+"','"+pantthigh+"','"+knee+"','"+bin_collar+"','"+frontPacket+"','"+shalwarDescription+"');";

    String query="insert into "+PANTSHIRT_TABLE_NAME+"(PANT_USERID,NAME,PHONE,NECK,CHEST,SHOULDER,SLEEVE,BICEP,WRIST,JOCKET_WAIST,SHIRTHIP,LENGTH,WAIST,HALFSHOULDER,BACKFULL_LENGTH,BACKHALF_LENGTH,SHALWAR_WAIST,SHALWAR_LENGTH,INSIDE_LENGTH,CROTCH,PANTTHIGH,PANTKNEE,BIN_COLLOR,FRONT_PACKET,SHALWAR_DESCRIPTION) values ('"+id+"','"+name+"','"+phone+"','"+neck+"','"+chest+"','"+shoulder+"','"+sleeve+"','"+bicep+"','"+wrist+"','"+jocket_waist+"','"+shirthip+"','"+length+"','"+waist+"','"+halfshoulder+"','"+backfull+"','"+backhalflength+"','"+shalwarwaist+"','"+shalwarlength+"','"+insidelength+"','"+crotch+"','"+pantthigh+"','"+knee+"','"+bin_collar+"','"+frontPacket+"','"+shalwarDescription+"')";
    boolean str=false;
    try {


       db.execSQL(query);
       str = true;
    }
    catch (Exception ex){
        Log.e("co","iside the databse classs ");
        Toast.makeText(context, "pleas change the id", Toast.LENGTH_SHORT).show();
        str=false;
    }

return  str;
}


public  boolean deletRecord(int check,String id)
{
    String query="hell";
    boolean str=true;
    if(check==0) {
        query = "DELETE FROM SHALWAR_KAMEEZ_DB WHERE SHK_USERID='" + id + "'";
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


public boolean updataShalwarKameez(String id,String name, String phone, String length, String sleeve, String shoulder, String chest, String underarm, String neck, String waist, String width, String abdomen, String bicep, String wrist, String shalwar_length, String shalwar_waist, String shalwar_pancha, String bin_collar, String front_packet, String shalwar_packet, String side_packet, String description)
    {
        boolean str=true;


    String query="update "+ SHALWARkMEEZ_TABLE_NAME +" set NAME='"+name+"',PHONE='"+phone+"',LENGTH='"+length+"',SLEEVE='"+sleeve+"',SHOULDER='"+shoulder+"',CHEST='"+chest+"',UNDERARM='"+underarm+"',NECK='"+neck+"',WAIST='"+waist+"',WIDTH='"+width+"',ABDOMEN='"+abdomen+"',BICEP='"+bicep+"',WRIST='"+wrist+"',SHALWAR_LENGTH='"+shalwar_length+"',WAIST='"+shalwar_waist+"',PANCHA='"+shalwar_pancha+"',BIN_COLLOR='"+bin_collar+"',FRONT_PACKET='"+front_packet+"',SS_PACKET='"+shalwar_packet+"',SIDE_PACKET='"+side_packet+"',SHALWAR_DESCRIPTION='"+description+"'  where SHK_USERID="+id+"";
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


public void updatePantShirt(String id,String name,String phone, String neck,String chest,String shoulder,String sleeve,String bicep,String wrist,String jocket_waist,String shirthip,String length, String waist,String halfshoulder,String backfull,String backhalflength,String shalwarwaist,String shalwarlength,String insidelength,String crotch,String pantthigh,String knee,String bin_collar,String frontPacket,String shalwarDescription)
{
    String query="update "+PANTSHIRT_TABLE_NAME+" set NAME='"+name+"',PHONE='"+phone+"',NECK='"+neck+"',CHEST='"+chest+"',SHOULDER='"+shoulder+"',SLEEVE='"+sleeve+"',BICEP='"+bicep+"',WRIST='"+wrist+"',JOCKET_WAIST='"+jocket_waist+"',SHIRTHIP='"+shirthip+"',LENGTH='"+length+"',WAIST='"+waist+"',HALFSHOULDER='"+halfshoulder+"',BACKFULL_LENGTH='"+backfull+"',BACKHALF_LENGTH='"+backhalflength+"',SHALWAR_WAIST='"+shalwarwaist+"',SHALWAR_LENGTH='"+shalwarlength+"',INSIDE_LENGTH='"+insidelength+"',CROTCH='"+crotch+"',PANTTHIGH='"+pantthigh+"',PANTKNEE='"+knee+"',BIN_COLLOR='"+bin_collar+"',FRONT_PACKET='"+frontPacket+"',SHALWAR_DESCRIPTION='"+shalwarDescription+"' where PANT_USERID="+id+"";

    try {
        db.execSQL(query);
        Toast.makeText(context, "Data is updated", Toast.LENGTH_SHORT).show();

    }catch (Exception ex)
    {
        Toast.makeText(context, "updating is not Successful", Toast.LENGTH_SHORT).show();
    }

}


    public boolean insertintoOrdertable(String name,String quantity,String price,String current_time,String delivery_time,String delivery_date,String shk_userid,String pant_userid,String imageid,String phone_number,String message_send,String total_cost)
    {
        String query="insert into "+ORDER_TABLE_NAME+ " (NAME,QUANTITY,PRICE,CURRENT_TIME,DELIVERY_TIME,DELIVERY_DATE,SHK_USERID,PANT_USERID,ID,PHONE,MESSAGE,TOTAL_COST) VALUES ('"+name+"','"+quantity+"','"+price+"','"+current_time+"','"+delivery_time+"','"+delivery_date+"','"+shk_userid+"','"+pant_userid+"','"+imageid+"','"+phone_number+"','"+message_send+"','"+total_cost+"')";
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



    public  boolean insertData(String id,String name, String phone, String length, String sleeve, String shoulder, String chest, String underarm, String neck, String waist, String width, String abdomen, String bicep, String wrist, String shalwar_length, String shalwar_waist, String shalwar_pancha, String bin_collar, String front_packet, String shalwar_packet, String side_packet, String description)
    {
        String query="insert into "+ SHALWARkMEEZ_TABLE_NAME +" ( SHK_USERID,NAME,PHONE,LENGTH,SLEEVE,SHOULDER,CHEST,UNDERARM,NECK,WAIST,WIDTH,ABDOMEN,BICEP,WRIST,SHALWAR_LENGTH,SHALWAR_WAIST,PANCHA,BIN_COLLOR,FRONT_PACKET,SS_PACKET,SIDE_PACKET,SHALWAR_DESCRIPTION) Values ('"+id+"','"+name+"','"+phone+"','"+length+"','"+sleeve+"','"+shoulder+"','"+chest+"','"+underarm+"','"+neck+"','"+waist+"','"+width+"','"+abdomen+"','"+bicep+"','"+wrist+"','"+shalwar_length+"','"+shalwar_waist+"','"+shalwar_pancha+"','"+bin_collar+"','"+front_packet+"','"+shalwar_packet+"','"+side_packet+"','"+description+"')";

      /*ContentValues values = new ContentValues();
        values.put("USERID",id);
        values.put("NAME",name);
        values.put("PHONE",phone);
      values.put("LENGTH",length);
        values.put("SLEEVE",sleeve);
        values.put("SHOULDER",shoulder);
        values.put("CHEST",chest);
        values.put("UNDERARM",underarm);
        values.put("NECK",neck);
        values.put("WAIST",waist);
        values.put("WIDTH",width);
        values.put("ABDOMEN",abdomen);
        values.put("BICEP",bicep);
        values.put("WRIST",wrist);
        values.put("SHALWAR_LENGTH",shalwar_length);
        values.put("SHALWAR_WAIST",shalwar_waist);
        values.put("PANCHA",shalwar_pancha);
        values.put("BIN_COLLOR",bin_collar);
        values.put("FRONT_PACKET",front_packet);

        values.put("SS_PACKET",shalwar_packet);
        values.put("SIDE_PACKET",side_packet);
        values.put("SHALWAR_DESCRIPTION",description);*/
        //      Long l= db.insert(SHALWAR_TABLE_NAME,null,values);


         boolean str=false;

  try {


      db.execSQL(query);
      str = true;
  }
  catch (Exception ex){
      Log.e("co","iside the databse classs ");
      Toast.makeText(context, "pleas change the id", Toast.LENGTH_SHORT).show();
      str=false;
  }
  return str;
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



public void deletImageDesign()    // its remove it a time all the designs from the table
{
    String query="Delete from "+IMAGE_TABLE_NAME;
    db.execSQL(query);
}


public void deletTheSpacificImageOfMale(String id)  //its remove the design one by one
{
    String query="Delete from "+IMAGE_TABLE_NAME+" where DESIGNID ='"+id+"'";
    Log.e("ddd","deleted of item "+id);
    db.execSQL(query);

}



    public void deletTheSpacificOrderTable(String id)  //its remove the design one by one
    {
        String query="Delete from "+ORDER_TABLE_NAME+" where ORDER_ID ='"+id+"'";
        Log.e("ddd","deleted of item "+id);
        db.execSQL(query);

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

    public Cursor showSpacificImage(String choice)  //at a time it show the only one categray design ,pantshirt or shalwarekameez
    {
        Cursor cr;
        String query=null;
        if(choice.equals("0")) {
            query="select * from "+IMAGE_TABLE_NAME+" where FLAGE=0";
        }
        else if(choice.equals("1"))
        {    query="select * from "+IMAGE_TABLE_NAME+" where FLAGE=1";
        }

        cr= db.rawQuery(query,null);
        return cr;
    }


    public Cursor showOneSpacificImage(String id){
        String query="select IMAGE from "+IMAGE_TABLE_NAME+" where DESIGNID="+id;

        Cursor cr=db.rawQuery(query,null);
        return cr;
    }


    public Cursor showOnOrderData(){
        Cursor cr;
        String query="select * from "+ORDER_TABLE_NAME+" where MESSAGE='ON';";
        cr= db.rawQuery(query,null);
        return cr;
    }
    public Cursor showOffOrderData(){
        Cursor cr;
        String query="select * from "+ORDER_TABLE_NAME+" where MESSAGE='OFF';";
        cr= db.rawQuery(query,null);
        return cr;
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

    public boolean updateTimeDate_OFF_ON(String time,String date,String id){
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

    public boolean deletOneSpacificOrder(String id){
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

    public Cursor selectPhoneNumberOfMale(String id){
        String query="select PHONE from "+ORDER_TABLE_NAME+" where ORDER_ID="+id+";";
        Cursor cr;
        cr=db.rawQuery(query,null);
        return cr;
    }


    public Cursor showOneSpacificOrder(String id){
        String query="select * from "+ORDER_TABLE_NAME+" where ORDER_ID="+id;

        Cursor cr=db.rawQuery(query,null);
        return cr;
    }


}
