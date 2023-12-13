package com.hyancy.eco_recicla_reto_1_grupo_7;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

public class Consejo1 extends AppCompatActivity {

    public void onClick(View consejo1) {

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AppCompatActivity.this);
// Configura el titulo.
            alertDialogBuilder.setTitle("¿Como recilcar el aceite?");
// Configura el mensaje.
            alertDialogBuilder
                    .setMessage("Dispon de una botella donde puedas llenarla del aceite ya usado; nunca lo arrojes por el lavado.  Cuando ésta ya se encuentre llena, acude a puntos de recolección donde se encargaran de elaborar otros productos tales como jabón.")
                    .setCancelable(false)
                    .setPositiveButton("Cerrar",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                        }

                    }).create().show();
        }
    }
