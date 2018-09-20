package com.example.sajib.sqlitecreatedbs;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText name, age, gender,userid;
    private Button savebutton,displaybutton,updatebutton,deletedata;
    DtabaseHelper dt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dt = new DtabaseHelper(this);
        SQLiteDatabase sqLiteDatabase = dt.getWritableDatabase();
        name = findViewById(R.id.textviewnameid);
        age = findViewById(R.id.textviewnageid);
        gender = findViewById(R.id.textviewgenderid);
        userid=findViewById(R.id.ideditid);
        savebutton = findViewById(R.id.buttonid);
        displaybutton = findViewById(R.id.dispplaybuttonid);
        updatebutton=findViewById(R.id.updatebuttonid);
        deletedata=findViewById(R.id.deletebuttonid);
        savebutton.setOnClickListener(this);
        displaybutton.setOnClickListener(this);
        updatebutton.setOnClickListener(this);
        deletedata.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        String namee = name.getText().toString();
        String agee = age.getText().toString();
        String gendere = gender.getText().toString();
        String id = userid.getText().toString();
        if (v.getId() == R.id.buttonid) {
            long rowId = dt.InsertData(namee, agee, gendere);
            if (rowId == -1) {
                Toast.makeText(this, "UNsuccessfull", Toast.LENGTH_SHORT).show();
            } else {

                Toast.makeText(this, "row" + rowId + "is successfully saved", Toast.LENGTH_SHORT).show();
            }
        }
        if(v.getId()==R.id.dispplaybuttonid){
            Cursor cursor = dt.displayAllData();
            if(cursor.getCount()==0){
                showData("error","nodata");
                return;
            }
            StringBuffer stringBuffer=new StringBuffer();
            while (cursor.moveToNext()){
                stringBuffer.append("Id"+cursor.getString(0)+"\n");
                stringBuffer.append("Name"+cursor.getString(1)+"\n");
                stringBuffer.append("Age"+cursor.getString(2)+"\n");
                stringBuffer.append("Gender"+cursor.getString(3)+"\n\n\n");
            }
            showData("resultset",stringBuffer.toString());
        }
        if(v.getId()==R.id.updatebuttonid){
          boolean istrue=dt.upDateData(id,namee,agee,gendere);
          if(istrue==true){
              Toast.makeText(this, "Data is Updated", Toast.LENGTH_SHORT).show();
          }else {
              Toast.makeText(this, "Not Updated", Toast.LENGTH_SHORT).show();
          }
        }
        if(v.getId()==R.id.deletebuttonid){
          int value = dt.deleteData(id);
          if(value>0){
              Toast.makeText(this, "data is deleted", Toast.LENGTH_SHORT).show();
          }else {
              Toast.makeText(this, "data is not deleted", Toast.LENGTH_SHORT).show();
          }
        }
    }
    public void showData(String title,String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(true);
        builder.show();
    }
}
