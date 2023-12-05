package com.hyancy.eco_recicla_reto_1_grupo_7.data.repository;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class FirebaseRepo {
    //Instancia de la base de datos
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser currentUser = mAuth.getCurrentUser();


    public void setUserData(String name, Integer age, String email, String password) {

        //Crear un nuevo usuario
        Map<String, Object> userHashMap = new HashMap<>();
        userHashMap.put("name", name);
        userHashMap.put("edad", age);
        userHashMap.put("email", email);
        userHashMap.put("password", password);


        //Agregar una nueva colección a la base de datos con un nuevo documento que tenga el objeto de usuario
        db.collection("users")
                .add(userHashMap)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "UserModel creado con exito, ID: " + documentReference.getId());
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "Error al registrar usuario. Contactarse con el administrador");
                    }
                });
    }
    public void setWasteData(String description, String photoUrl, String registerDate, String location, String category, double quantity, int points) {

        //Crear un nuevo residuo
        Map<String, Object> wasteHashMap = new HashMap<>();
        wasteHashMap.put("description", description);
        wasteHashMap.put("photo_path", photoUrl);
        wasteHashMap.put("date", registerDate);
        wasteHashMap.put("location", location);
        wasteHashMap.put("category", category);
        wasteHashMap.put("quantity", quantity);
        wasteHashMap.put("points", points);

        //Agregar una nueva colección a la base de datos con un nuevo documento que tenga el objeto de usuario
        db.collection("wastes")
                .add(wasteHashMap)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "Residuo registrado con exito,  ID: " + documentReference.getId());
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "Error al registrar residuo. Contactarse con el administrador");
                    }
                });
    }

    public FirebaseFirestore getDb(){
        return db;
    }

    public FirebaseAuth getmAuth(){
        return mAuth;
    }

    public FirebaseUser getCurrentUser(){
        return currentUser;
    }
}
