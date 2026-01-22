package com.tajmirkhan.tailormanagment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoView;

import static android.R.attr.checked;

public class FemaleUpdateDesign extends AppCompatActivity {
    private  static final int GALLERY_PICK=0;
    private  static final int CAMRA_PICK=1;

    List selectNumbderOfData_List=new ArrayList();
DatabaseFemale db;
    Toolbar toolbar;
    GridView gridview;
    CustomListViewOfFemaleDesign customlistview;
    ArrayList<Female_Design_Data> list=new ArrayList<>();

 int count;


    float x1,x2;  // dialog all view
    float y1, y2;
    byte[] arry;
    int a=0;
    PhotoView image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.female_update_design_activity);

        gridview=(GridView)findViewById(R.id.gridview_id);
        db=new DatabaseFemale(this);
        getImageDataFromDB();

        toolbar=(Toolbar)findViewById(R.id.toolbar_id);
        toolbar.setNavigationIcon(R.drawable.ic_white_back);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        getSupportActionBar().setTitle("Design");// it use for OptionMenu without this its not suport
        customlistview=new CustomListViewOfFemaleDesign(getApplicationContext(),list);
        gridview.setAdapter(customlistview);
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
                return true;            }

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
                    getImageDataFromDB();
                    customlistview.notifyDataSetChanged();
                    actionMode.finish();
                }
                return true;            }

            @Override
            public void onDestroyActionMode(ActionMode actionMode) {
                count=0;
                selectNumbderOfData_List.clear();

            }
        });

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Dialog dialog = new Dialog(FemaleUpdateDesign.this, android.R.style.Theme_DeviceDefault_Dialog_MinWidth);
                dialog.setContentView(R.layout.fullscreen_imgae_show);
                dialog.getWindow().setBackgroundDrawable(null);  //its show the dialog with is full

                //TextView name=(TextView)dialog.findViewById(R.id.textviewimage_id);
                image=(PhotoView)dialog.findViewById(R.id.imageview);
                LinearLayout linear=(LinearLayout)dialog.findViewById(R.id.linearlayout_id);
                Display display = getWindowManager().getDefaultDisplay();
                int width = display.getWidth();
                // int height = display.getHeight();


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
    }

    public void getImageDataFromDB()
    {
        Cursor cr=db.imageDataShow();

        if(cr.getCount()>0) {
            cr.moveToFirst();
            Female_Design_Data obj;
            do {
                obj = new Female_Design_Data();

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
        getMenuInflater().inflate(R.menu.add_deleteall_exit,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_id:
                AlertDialog.Builder dialog=new AlertDialog.Builder(this);
                dialog.setTitle("Add photo");
                final String[] option={"Camera","Gallery","cancel"};
                dialog.setItems(option, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                       if(option[i].equals("Camera")){
                           cameraMethod();
                       }else if(option[i].equals("Gallery")){
                                galleryMethod();
                       }else if(option[i].equals("cancel")){
                           Toast.makeText(FemaleUpdateDesign.this, "cancel select", Toast.LENGTH_SHORT).show();
                       }
                    }
                });
                dialog.show();
                return true;
            case R.id.deleteall_id:
                 AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setCancelable(false);
                builder.setTitle("Delete all Designs");
                builder.setMessage("are you sure you want to Delete all design images");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        db.deletImageDesign();
                        list.clear();
                        customlistview.notifyDataSetChanged();
                        Toast.makeText(getApplicationContext(), "All the Designs is Deleted", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.show();
                return true;
            case R.id.exist_id:

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void cameraMethod(){
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,CAMRA_PICK);
    }

    public void galleryMethod(){
        Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,GALLERY_PICK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        byte[] bytearry;

        if(resultCode==RESULT_OK){
            if(CAMRA_PICK==requestCode){
                Bundle bndl=data.getExtras();
                Bitmap btm=(Bitmap) bndl.get("data");

                // imageview.setImageBitmap(btm);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                btm.compress(Bitmap.CompressFormat.PNG,0, stream);
                bytearry=stream.toByteArray();
                Intent intent=new Intent(FemaleUpdateDesign.this,AddPhotoOfFemale.class);
                intent.putExtra("imagedata",bytearry);
                startActivity(intent);

            } else if(requestCode==GALLERY_PICK)
            { Log.e("rc","requst code gallery pick");
                Uri uri=data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),uri); // convert uri to bitmap
                    // imageview.setImageBitmap(bitmap);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG,0, stream);
                    bytearry=stream.toByteArray(); // convert stream value to bytarray
                    Intent intent=new Intent(FemaleUpdateDesign.this,AddPhotoOfFemale.class);
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
        super.onResume();
        list.clear();
        getImageDataFromDB();
        customlistview.notifyDataSetChanged();

    }
}
