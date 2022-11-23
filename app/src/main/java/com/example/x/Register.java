package com.example.x;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import DataBase.DBusuario;

public class Register extends Fragment {

    EditText username,correo, password, confirmPassword;
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

    public Register() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        username = view.findViewById(R.id.txtNameUser);
        correo = view.findViewById(R.id.txtEmail);
        password =  view.findViewById(R.id.txtPassword);
        confirmPassword =  view.findViewById(R.id.txtConfirmPassword);
        register =  view.findViewById(R.id.btnRegister);
        DB = new DBusuario(getContext());

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String email = correo.getText().toString();
                String pass = password.getText().toString();
                String repass = confirmPassword.getText().toString();

                if (user.equals("")  || email.equals("")|| pass.equals("") || repass.equals(""))
                    Toast.makeText(getContext(), "ingresar todo los campos", Toast.LENGTH_SHORT).show();
                else {
                    if (pass.equals(repass)) {
                        Boolean checkuser = DB.checkusername(email);
                        if (checkuser == false) {
                            Boolean insert = DB.insertData(email, pass);
                            if (insert == true) {
                                Toast.makeText(getContext(), "Registro exitoso", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getContext(), MainActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(getContext(), "Registro fallido", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getContext(), "usuario ya existe! ingrese nuevo", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getContext(), "contraseña no coincide", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}