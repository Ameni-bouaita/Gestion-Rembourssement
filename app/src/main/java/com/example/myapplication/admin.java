package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.lang.ref.WeakReference;
import java.util.List;

public class admin extends AppCompatActivity {

    AppDatabase db;  // Declare db as a class member
    RecyclerView listRemboursements;  // Declare listRemboursements as a class member
    private List<remboursement> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        listRemboursements = findViewById(R.id.recyclerViewAdmin);  // Initialize listRemboursements here
        db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "app-database")
                .build();

        // Fetch data in the background using AsyncTask
        new LoadRemboursementsAdminTask(this).execute();

        listRemboursements.setLayoutManager(new LinearLayoutManager(this));
    }

    private static class LoadRemboursementsAdminTask extends AsyncTask<Void, Void, List<remboursement>> {
        private WeakReference<admin> activityReference;

        LoadRemboursementsAdminTask(admin activity) {
            activityReference = new WeakReference<>(activity);
        }

        @Override
        protected List<remboursement> doInBackground(Void... voids) {
            admin activity = activityReference.get();
            if (activity != null) {
                AppDatabase db = activity.db;  // Access db from the outer class
                return db.remboursementDAO().getAllRemboursements();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<remboursement> dataFromDatabase) {
            admin activity = activityReference.get();
            if (activity != null) {
                if (dataFromDatabase != null && !dataFromDatabase.isEmpty()) {
                    AdminAdapter adapter = new AdminAdapter(dataFromDatabase);
                    activity.listRemboursements.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    Log.d("LoadRemboursementsTask", "Data good : " + dataFromDatabase);
                } else {
                    Log.d("LoadRemboursementsTask", "Data is null or empty");
                }
            }
        }
    }

    public static class UpdateRemboursementEtatTask extends AsyncTask<Void, Void, Void> {
        private remboursement remboursementToUpdate;
        private WeakReference<admin> activityReference;

        UpdateRemboursementEtatTask(admin activity, remboursement remboursement) {
            this.activityReference = new WeakReference<>(activity);
            this.remboursementToUpdate = remboursement;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            admin activity = activityReference.get();
            if (activity != null) {
                AppDatabase db = activity.db; // Use the existing instance
                // Update the 'etat' in the database
                db.remboursementDAO().updateRemboursementEtat(remboursementToUpdate);
            }
            return null;
        }
    }

    private void updateRemboursementEtat(int position, String newEtat ,View itemView) {
        remboursement currentItem = itemList.get(position);
        currentItem.etat = newEtat;

        // Get the reference to the outer class
        admin activity = (admin) itemView.getContext();

        // Update the 'etat' in the database
        new admin.UpdateRemboursementEtatTask(activity, currentItem).execute();
    }
  private void onAcceptButtonClick(int position, View itemView) {
      // Implement the logic to update the 'etat' to 'accepted' in the database
      updateRemboursementEtat(itemView, position, "accepted");
  }

    private void onDeclineButtonClick(int position, View itemView) {
        // Implement the logic to update the 'etat' to 'declined' in the database
        updateRemboursementEtat(itemView, position, "declined");
    }
    private void updateRemboursementEtat(View itemView, int position, String newEtat) {
        remboursement currentItem = itemList.get(position);
        currentItem.etat = newEtat;

        // Get the reference to the outer class
        admin activity = (admin) itemView.getContext();

        // Update the 'etat' in the database
        new admin.UpdateRemboursementEtatTask(activity, currentItem).execute();
    }


}
