package com.example.truss;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StrutsAdapter extends RecyclerView.Adapter<StrutsAdapter.StrutsHolder> {
    List<Struts> strutsList;
    Context context;
    ViewGroup parentGroup;

    public StrutsAdapter(List<Struts> strutsList) {
        this.strutsList = strutsList;
    }

    @NonNull
    @Override
    public StrutsAdapter.StrutsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        parentGroup = parent;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_struts, null);
        return new StrutsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final StrutsAdapter.StrutsHolder holder, final int position) {
        holder.feetEdit.setText(String.valueOf(strutsList.get(position).feet));
        holder.feetEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                strutsList.get(position).feet = Float.parseFloat(charSequence.toString());
                strutsList.get(position).calculateAndStoreCost();
                float newCost = totalCost();
                TextView textView = parentGroup.getRootView().findViewById(R.id.total_cost);
                textView.setText(String.valueOf(newCost));
                holder.costText.setText(String.valueOf(strutsList.get(position).cost));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        holder.quantityEdit.setText(String.valueOf(strutsList.get(position).quantity));
        holder.quantityEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                strutsList.get(position).quantity = Integer.parseInt(charSequence.toString());
                strutsList.get(position).calculateAndStoreCost();
                float newCost = totalCost();
                TextView textView = parentGroup.getRootView().findViewById(R.id.total_cost);
                textView.setText(String.valueOf(newCost));
                holder.costText.setText(String.valueOf(strutsList.get(position).cost));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        holder.sideEdit.setText(String.valueOf(strutsList.get(position).side));
        holder.sideEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                strutsList.get(position).side = Integer.parseInt(charSequence.toString());
                strutsList.get(position).calculateAndStoreCost();
                float newCost = totalCost();
                TextView textView = parentGroup.getRootView().findViewById(R.id.total_cost);
                textView.setText(String.valueOf(newCost));
                holder.costText.setText(String.valueOf(strutsList.get(position).cost));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        strutsList.get(position).calculateAndStoreCost();
        holder.costText.setText(String.valueOf(strutsList.get(position).cost));
        if (strutsList.size() == 1) {
            TextView textView = parentGroup.getRootView().findViewById(R.id.total_cost);
            textView.setText(String.valueOf(strutsList.get(position).cost));
        }
    }

    public void addItem(Struts struts) {
        strutsList.add(struts);
        notifyDataSetChanged();
    }

    public float totalCost() {
        float cost = 0;
        for (Struts struts : strutsList) {
            cost += struts.cost;
        }
        return cost;
    }

    @Override
    public int getItemCount() {
        return strutsList.size();
    }

    public class StrutsHolder extends RecyclerView.ViewHolder {
        private EditText feetEdit, quantityEdit, sideEdit;
        private TextView costText;

        public StrutsHolder(@NonNull View itemView) {
            super(itemView);
            feetEdit = itemView.findViewById(R.id.feet_edit);
            quantityEdit = itemView.findViewById(R.id.quantity_edit);
            sideEdit = itemView.findViewById(R.id.side_edit);
            costText = itemView.findViewById(R.id.cost_text);
        }
    }
}
