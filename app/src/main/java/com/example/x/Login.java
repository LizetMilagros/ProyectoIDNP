package com.example.x;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import DataBase.DBusuario;

public class Login extends Fragment {

    EditText password, correo;
    Button login, register;
    DBusuario DB;
    CallbackFragment callbackFragment;
    //String userName, pass;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    public void onAttach(@NonNull Context context) {
        sharedPreferences = context.getSharedPreferences("userFile",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        super.onAttach(context);
    }

    public Login() {
        // constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        //username = view.findViewById(R.id.txtUser);
        correo = view.findViewById(R.id.txtEmail1);
        password =  view.findViewById(R.id.txtPassword);
        login =  view.findViewById(R.id.btnLogin);
        register =  view.findViewById(R.id.btnRegister);
        DB = new DBusuario(getContext());

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (callbackFragment!=null){
                    callbackFragment.changeFragment();
                }

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = correo.getText().toString();
                String pass = password.getText().toString();

                if(email.equals("")||pass.equals(""))
                    Toast.makeText(getContext(), "Por favor ingrese todos los campos", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = DB.checkusernamepassword(email, pass);
                    if(checkuserpass==true){
                        Toast.makeText(getContext(), "Iniciar sesión con éxito", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(getContext(), MenuActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(getContext(), "Credenciales no válidas", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    public void setCallbackFragment(CallbackFragment callbackFragment){
        this.callbackFragment = callbackFragment;
    }

}