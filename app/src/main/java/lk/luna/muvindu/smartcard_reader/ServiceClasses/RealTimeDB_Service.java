package lk.luna.muvindu.smartcard_reader.ServiceClasses;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import lk.luna.muvindu.smartcard_reader.Models.Journey;

public class RealTimeDB_Service {
     public static final String NEXT_STOP="next";
    public static final String PREVIOUS_STOP="previous";
    public static final String CURRENT_STOP="current";

    public void addJourney(String card,Journey journey){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        myRef.child(card)

                .setValue(journey)

                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.e("fire","suc");
                        } else {
                            Log.e("fire","fail");
                        }
                    }


                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Log.e("fire",e.getMessage());
                    }
                });
    }


    public void changeStation(String card,String type,String Station){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        myRef.child(card)
                .child(type)

                .setValue(Station)

                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.e("fire","suc");
                        } else {
                            Log.e("fire","fail");
                        }
                    }


                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Log.e("fire",e.getMessage());
                    }
                });
    }
}
