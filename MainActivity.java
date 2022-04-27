package com.example.databaseq12_practical;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DBHelper db;
    EditText roll, name, email, phone;
    Button insert, delete, update, view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        roll = findViewById(R.id.rollNo);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        insert = findViewById(R.id.insertBtn);
        delete = findViewById(R.id.deleteBtn);
        update = findViewById(R.id.updateBtn);
        view = findViewById(R.id.viewBtn);
        db = new DBHelper(this,"userDB.db",null,1);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String rolltxt = roll.getText().toString();
                String nametxt = name.getText().toString();
                String emailtxt = email.getText().toString();
                String phonetxt = phone.getText().toString();

                boolean checkData = db.insertUserData(rolltxt,nametxt,emailtxt,phonetxt);
                if(checkData){
                    Toast.makeText(MainActivity.this, "Data inserted sucecssfully", Toast.LENGTH_SHORT).show();
                    roll.setText("");
                    name.setText("");
                    email.setText("");
                    phone.setText("");
                }
                else {
                    Toast.makeText(MainActivity.this, "Data insertion failed!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String rolltxt = roll.getText().toString();
                String nametxt = name.getText().toString();
                String emailtxt = email.getText().toString();
                String phonetxt = phone.getText().toString();

                boolean checkData = db.updateUserData(rolltxt,nametxt,emailtxt,phonetxt);
                if(checkData){
                    Toast.makeText(MainActivity.this, "Data updated sucecssfully", Toast.LENGTH_SHORT).show();
                    roll.setText("");
                    name.setText("");
                    email.setText("");
                    phone.setText("");
                }
                else {
                    Toast.makeText(MainActivity.this, "Data updation failed!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String rolltxt = roll.getText().toString();
                String nametxt = name.getText().toString();
                String emailtxt = email.getText().toString();
                String phonetxt = phone.getText().toString();

                boolean checkData = db.deleteUserData(rolltxt);
                if(checkData){
                    Toast.makeText(MainActivity.this, "Data deleted sucecssfully", Toast.LENGTH_SHORT).show();
                    roll.setText("");
                    name.setText("");
                    email.setText("");
                    phone.setText("");
                }
                else {
                    Toast.makeText(MainActivity.this, "Data deletion failed!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor result = db.getData();
                if (result.getCount()==0){
                    Toast.makeText(MainActivity.this, "No record found!!", Toast.LENGTH_SHORT).show();
                    return;
                }

                StringBuffer sbuffer = new StringBuffer();
                while (result.moveToNext()){
                    sbuffer.append("Roll No.: "+result.getString(0)+"\n");
                    sbuffer.append("Name: "+result.getString(1)+"\n");
                    sbuffer.append("Email: "+result.getString(2)+"\n");
                    sbuffer.append("Phone: "+result.getString(3)+"\n");
                }

                AlertDialog.Builder dataTable = new AlertDialog.Builder(MainActivity.this);
                dataTable.setCancelable(true);
                dataTable.setTitle("Data Table");
                dataTable.setMessage(sbuffer.toString());
                dataTable.show();
            }
        });
    }
}