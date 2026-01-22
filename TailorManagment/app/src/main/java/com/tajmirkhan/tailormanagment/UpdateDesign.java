package com.tajmirkhan.tailormanagment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ActionMode;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoView;

import static android.R.attr.bitmap;
import static android.R.attr.checked;
import static android.R.attr.id;
import static android.R.id.list;

public class UpdateDesign extends AppCompatActivity {
    private  static final int GALLERY_PICK=0;
    private  static final int CAMRA_PICK=1;


    Toolbar toolbar;
    List selectNumbderOfData_List=new ArrayList();
    //List<Design_Data> selectNumbderOfData_Listt=new ArrayList();

    int count=0;
    DataBaseHelper db;
    Design_Data obj;
    GridView gridview;
    ArrayList<Design_Data> list=new ArrayList<>();
    CustomeListViewOfDesign adapte;

    float x1,x2;  // dialog all view
    float y1, y2;
    byte[] arry;
int a=0;
     PhotoView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_design_activity);

 Log.e("oncreat","oncreate is calles");

        db=new DataBaseHelper(this);

          gridview=(GridView)findViewById(R.id.gridview_id);

        toolbar=(Toolbar)findViewById(R.id.toolbar_id);
        toolbar.setNavigationIcon(R.drawable.ic_white_back);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Design");// it use for OptionMenu without this its not suport


//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.kamee_elbow_imagey);
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG,0, stream);
//        byte[] bytearry=stream.toByteArray();
//        db.insertIntoImageTable("elbow",bytearry);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

     //  dataRetriveFromDatabase(); //this is user define mthod it retrive and set data

         adapte=new CustomeListViewOfDesign(getApplicationContext(),list);
        gridview.setAdapter(adapte);
  gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

          Dialog dialog = new Dialog(UpdateDesign.this, android.R.style.Theme_DeviceDefault_Dialog_MinWidth);
          dialog.setContentView(R.layout.fullscreen_imgae_show);
         dialog.getWindow().setBackgroundDrawable(null);  //its show the dialog with is full

          //TextView name=(TextView)dialog.findViewById(R.id.textviewimage_id);
          image=(PhotoView)dialog.findViewById(R.id.imageview);
          LinearLayout linear=(LinearLayout)dialog.findViewById(R.id.linearlayout_id);
          Display display = getWindowManager().getDefaultDisplay();
          int width = display.getWidth();
          int height = display.getHeight();
           Log.e("minimum height", String.valueOf(height));

         // image.setMinimumWidth(width);
          image.setMinimumHeight(500);
         // image.setMaxWidth(width);
        // image.setMaxHeight(height);
          //name.setText(" Name :"+list.get(i).getName().toString());
          arry =list.get(i).getImage();
          try {
              Bitmap bitmap = BitmapFactory.decodeByteArray(arry, 0, arry.length);
              image.setImageBitmap(bitmap);
          }catch (Exception ex){
          }


image.setOnTouchListener(new View.OnTouchListener() {
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
            {
                x1 = motionEvent.getX();
                y1 = motionEvent.getY();
                break;
            }
            case MotionEvent.ACTION_UP:
            {
                x2 = motionEvent.getX();
                y2 = motionEvent.getY();

                //if left to right sweep event on screen
                if (x1 < x2)
                {
                    a--;
                    if(a<0){
                        a=list.size();

                    }else {
                    arry =list.get(a).getImage();
                    try {
                        Bitmap bitmap = BitmapFactory.decodeByteArray(arry, 0, arry.length);
                        image.setImageBitmap(bitmap);
                    }catch (Exception ex){

                    }
                }}

                // if right to left sweep event on screen
                if (x1 > x2)
                {
                    a++;
                    if(a>list.size()-1){
                        a=0;
                    }else {
                    arry =list.get(a).getImage();
                    try {
                        Bitmap bitmap = BitmapFactory.decodeByteArray(arry, 0, arry.length);
                        image.setImageBitmap(bitmap);
                    }catch (Exception ex){

                    }}
                }
                break;
            }
        }
            return true;
    }
});

          image.setMaximumScale(10); //image zooming
          dialog.show();  //its show the dialog
      }
  });




     gridview.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE_MODAL);
        gridview.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode actionMode, int position, long l, boolean checked) {
                if(checked)
                {
                    selectNumbderOfData_List.add(list.get(position).getId().toString());
                    count++;
                    actionMode.setTitle(count+" selected");
                }
                else {
                    selectNumbderOfData_List.remove(list.get(position).getId().toString());
                    count--;
                    actionMode.setTitle(count+" selected");
                }
            }

            @Override
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                MenuInflater menuInflater=getMenuInflater();
                menuInflater.inflate(R.menu.covert_menu,menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {

                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {

                if(menuItem.getItemId()==R.id.delete_one_id)
            {
                for(Object item:selectNumbderOfData_List)
                {
                    Log.e( "onActionItemClicked: ", ""+list.size());
                    db.deletTheSpacificImageOfMale(item.toString());
                    // list.remove(selectNumbderOfData_Listt.get(a));
                    Log.e( "onActionItemClicked:11 ", ""+list.size());
                }
                list.clear();
                dataRetriveFromDatabase();
                adapte.notifyDataSetChanged();
                actionMode.finish();
            }
                return true;
        }

            @Override
            public void onDestroyActionMode(ActionMode actionMode) {
                count=0;
                selectNumbderOfData_List.clear();

   // selectNumbderOfData_Listt.clear();
            }
        });
    }

public void dataRetriveFromDatabase()// this is user define mthod its retrive the data from database
{

    Cursor cr=db.imageDataShow();

    if(cr.getCount()>0) {
        cr.moveToFirst();
        do {
            obj = new Design_Data();

            obj.setId(cr.getString(0));
            obj.setName(cr.getString(1));
            obj.setImage(cr.getBlob(2));
            obj.setFlage(cr.getString(3));
            list.add(obj);
        } while (cr.moveToNext());
    }
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.add:

                AlertDialog.Builder builder = new AlertDialog.Builder(UpdateDesign.this);
                builder.setTitle("Add photo");
                final CharSequence[] option = {"Camra","Gallery","Cancel"};
                builder.setItems(option, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (option[i].equals("Camra")) {
                            cameraMethod();
                        } else if (option[i].equals("Gallery")) {
                            galleryMethod();
                        } else if (option[i].equals("Cancel")) {

                            dialogInterface.dismiss();
                        }
                    }
                });
                builder.show();

                return true;
            case R.id.exit:

                return true;
            case R.id.delet:
                 final AlertDialog.Builder build=new AlertDialog.Builder(this);
                build.setCancelable(false);
                build.setTitle("Delete All Desgin");
                build.setMessage("Are you sure you want to Delete All Designs");
                build.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        db.deletImageDesign();
                        list.clear();
                        adapte.notifyDataSetChanged();
                        Toast.makeText(getApplicationContext(), "All the Designs is Deleted", Toast.LENGTH_SHORT).show();
                    }
                });
                build.setNegativeButton("NO ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                build.create().show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void cameraMethod()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Log.e("in","inside the camra intent");
        startActivityForResult(intent,CAMRA_PICK);

    }

    public void galleryMethod()
    {
        Intent intent = new   Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        Log.e("in","inside the Gallery intent");
        startActivityForResult(intent,GALLERY_PICK);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        byte[] bytearry;
        if(resultCode==RESULT_OK)
        { Log.e("rc","inside the result code");
            if(requestCode==CAMRA_PICK)
            {
                Log.e("rc","request code Camra pick");
                Bundle bndl=data.getExtras();
                Bitmap btm=(Bitmap) bndl.get("data");

               // imageview.setImageBitmap(btm);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                btm.compress(Bitmap.CompressFormat.PNG,0, stream);
                bytearry=stream.toByteArray();
                Intent intent=new Intent(UpdateDesign.this,AddPhotoOfMale.class);
                intent.putExtra("imagedata",bytearry);
                startActivity(intent);

            }
            else if(requestCode==GALLERY_PICK)
            { Log.e("rc","requst code gallery pick");
                Uri uri=data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),uri); // convert uri to bitmap
                   // imageview.setImageBitmap(bitmap);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG,0, stream);
                    bytearry=stream.toByteArray(); // convert stream value to bytarray
                    Intent intent=new Intent(UpdateDesign.this,AddPhotoOfMale.class);
                    intent.putExtra("imagedata",bytearry);
                    startActivity(intent);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }
    }



    @Override
    protected void onResume() {
        Log.e("oncreat","onResume is calle is calles");
        super.onResume();
        list.clear();
        dataRetriveFromDatabase();
        adapte.notifyDataSetChanged();
    }
}
