package com.example.labdesenvolvimento.loteriaapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener
{

    private EditText txtEmail;
    private EditText txtPassword;
    private FirebaseAuth firebaseAuth;
    private Button registerBtn;
    private Button cancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firebaseAuth = FirebaseAuth.getInstance();
        txtEmail = (EditText) findViewById(R.id.editEmail);
        txtPassword = (EditText) findViewById(R.id.editPasswordRegister);
        registerBtn = (Button) findViewById(R.id.RegisterConfirm);
        cancelBtn = (Button) findViewById(R.id.CancelRegister);
        registerBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);
    }

    private void OnRegisterConfirm(){
        String email = txtEmail.getText().toString().trim();
        String pass = txtPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "O campo E-mail é obrigatório", Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(pass)){
            Toast.makeText(this, "O campo Senha é obrigatório", Toast.LENGTH_LONG).show();
            return;
        }

        firebaseAuth.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this, "Usuario registrado!", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(RegisterActivity.this, "Falha ao registrar o usuário, tente novamente.", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        if(v == registerBtn){
            OnRegisterConfirm();
        }
        if(v == cancelBtn){

        }
    }
}
