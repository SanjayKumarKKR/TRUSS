package com.example.truss;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    StrutsAdapter adapter;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new StrutsAdapter(new ArrayList<Struts>()));
        adapter = (StrutsAdapter) recyclerView.getAdapter();
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDialog();
            }
        });
    }

    private void createDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        ViewGroup viewGroup = findViewById(R.id.content);
        final View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_struts, viewGroup, false);
        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();
        dialogView.findViewById(R.id.create_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float feet = Float.parseFloat(((EditText) dialogView.findViewById(R.id.feet_edit)).getText().toString().trim());
                int quantity = Integer.parseInt(((EditText) dialogView.findViewById(R.id.quantity_edit)).getText().toString().trim()), side = Integer.parseInt(((EditText) dialogView.findViewById(R.id.side_edit)).getText().toString().trim());
                Struts struts = new Struts(feet, quantity, side);
                struts.calculateAndStoreCost();
                adapter.addItem(struts);
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }
}