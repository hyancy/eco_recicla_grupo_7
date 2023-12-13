package com.hyancy.eco_recicla_reto_1_grupo_7.data.repository;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FirebaseRepo {
    //Instancia de la base de datos
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private final FirebaseUser currentUser = mAuth.getCurrentUser();
    ArrayList<QueryDocumentSnapshot> wasteList = new ArrayList<>();

    public void createUser(String name, Integer age, String email, String password, Context context) {
        final FirebaseAuth mAuth = FirebaseAuth.getInstance();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in userModel's information
                            Toast.makeText(context, "Cuenta creada con exito!.",
                                    Toast.LENGTH_SHORT).show();

                            String uIdUser = mAuth.getCurrentUser().getUid();

                            setUserData(uIdUser, name, age, email, password);
                        } else {
                            // If sign in fails, display a message to the userModel.
                            Toast.makeText(context, "Cuenta ya existe!!!.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public String getUidUser() {
        String uIdUser = mAuth.getCurrentUser().getUid();
        return uIdUser;
    }

    public void setUserData(String uIdUser, String name, Integer age, String email, String password) {

        //Crear un nuevo usuario
        Map<String, Object> userHashMap = new HashMap<>();
        userHashMap.put("name", name);
        userHashMap.put("age", age);
        userHashMap.put("email", email);
        userHashMap.put("password", password);

        //Agregar una nueva colección a la base de datos con un nuevo documento que tenga el objeto de usuario
        db.collection("users").document(uIdUser.toString())
                .set(userHashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("VEF", "Usuario registrado con exito con ID: " + uIdUser);
                            FirebaseAuth.getInstance().signOut();
                        } else {
                            Log.d("VEF2", "Error al registrar usuario. Contactar al administrador");
                        }
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
        wasteHashMap.put("idUser", getUidUser());

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

    public FirebaseFirestore getDb() {
        return db;
    }

    public FirebaseAuth getmAuth() {
        return mAuth;
    }

    public FirebaseUser getCurrentUser() {
        return currentUser;
    }

    public void getUserData(String idUser) {
        db.collection("users").document(currentUser.getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot document) {
                if (document.exists()) {
                    String idUser = document.getId();
                    String nombre = document.getString("name");
                    int edad = Long.valueOf(document.getLong("edad")).intValue();
                    String email = document.getString("email");

                    Log.i("OUT", "LOS DATOS DE USUARIO SON: \n" + idUser + "\n" + nombre + "\n" + edad + "\n" + email);
                } else {
                    Log.d(TAG, "Usuario no encontrado, verificar los datos");
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "Usuario no encontrado, verificar los datos");
            }
        });
    }

    public void  getWasteByUserId(String idCurrentUser, ArrayList<QueryDocumentSnapshot> wasteList, TextView tvAccumulatedAmount, TextView tvAccumulatedPoints, String category) {
        db.collection("wastes").whereEqualTo("idUser", idCurrentUser).whereEqualTo("category", category).get().addOnSuccessListener(queryResponse -> {
            if (!queryResponse.isEmpty()) {
                int points = 0;
                double quantity = 0.0;
                for (QueryDocumentSnapshot queryWaste : queryResponse) {
                    points += Integer.parseInt(queryWaste.get("points").toString());
                    quantity += Double.parseDouble(queryWaste.get("quantity").toString());
                    wasteList.add(queryWaste);
                }
                tvAccumulatedAmount.setText(String.valueOf(quantity));
                tvAccumulatedPoints.setText(String.valueOf(points));
                Log.i("ResponseQuery", wasteList.toArray().toString());

            } else {
                Log.i("ResponseQuery", "No hay datos de esta categoría");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    public ArrayList<QueryDocumentSnapshot> getDataWaste(ArrayList<QueryDocumentSnapshot> wasteList) {
        this.wasteList = wasteList;
        return this.wasteList;
    }

    public void logoutSesion() {
        FirebaseAuth.getInstance().signOut();
    }
}
