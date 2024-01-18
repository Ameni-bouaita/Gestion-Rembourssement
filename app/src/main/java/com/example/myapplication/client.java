package com.example.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;


import java.lang.ref.WeakReference;
import java.util.List;
public class client extends AppCompatActivity {
    AppDatabase db;  // Declare db as a class member
    RecyclerView listRemboursements;  // Declare listRemboursements as a class member

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        Button demande = findViewById(R.id.demande);
        listRemboursements = findViewById(R.id.recyclerView);  // Initialize listRemboursements here
        db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "app-database")
                .build();

        // Fetch data in the background using AsyncTask
        new LoadRemboursementsTask(this).execute();

        demande.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(client.this, formulaire.class);
                startActivity(intent);
            }
        });
        listRemboursements.setLayoutManager(new LinearLayoutManager(this));

    }

    private static class LoadRemboursementsTask extends AsyncTask<Void, Void, List<remboursement>> {
        private WeakReference<client> activityReference;

        LoadRemboursementsTask(client activity) {
            activityReference = new WeakReference<>(activity);
        }

        @Override
        protected List<remboursement> doInBackground(Void... voids) {
            AppDatabase db = activityReference.get().db;  // Access db from the outer class
            return db.remboursementDAO().getAllRemboursements();
        }

        @Override
        protected void onPostExecute(List<remboursement> dataFromDatabase) {
            client activity = activityReference.get();
            if (activity != null) {
                if (dataFromDatabase != null && !dataFromDatabase.isEmpty()) {
                    ClientAdapter adapter = new ClientAdapter(dataFromDatabase);
                    activity.listRemboursements.setAdapter(adapter);  // Use listRemboursements instead of recyclerView
                    adapter.notifyDataSetChanged();
                    Log.d("LoadRemboursementsTask", "Data good : " + dataFromDatabase);

                } else {
                    Log.d("LoadRemboursementsTask", "Data is null or empty");
                }
            }
        }

    }
}
