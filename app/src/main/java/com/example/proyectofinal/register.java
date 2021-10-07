package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class register extends AppCompatActivity {

    EditText username, password, repassword;
    Button signUp, signIn;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);

        signUp = (Button) findViewById(R.id.btnSignUp);
        signIn = (Button) findViewById(R.id.btnSignIn);

        DB = new DBHelper(this);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();


                if (user.equals("") || pass.equals("") || repass.equals("")){
                    Toast.makeText(register.this, "Por favor ingresa todos los campos", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (pass.equals(repass)){
                        Boolean checkUser = DB.checkUsername(user);
                        if (checkUser == false){
                            Boolean insert = DB.insertData(user, pass);
                            if (insert == true){
                                Toast.makeText(register.this,"Registro exitoso!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(register.this, "Hubo un error al registrarse.", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(register.this, "El usuario ya existe, elija otro usuario.", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(register.this, "Las contraseñas no coinciden, por favor verifique que las contraseñas sean las mismas.", Toast.LENGTH_SHORT).show();

                    }
                }

            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });


    }
}