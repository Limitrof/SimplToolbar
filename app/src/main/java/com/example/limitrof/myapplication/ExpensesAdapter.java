package com.example.limitrof.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Impression on 25.05.2016.
 */
public class ExpensesAdapter extends RecyclerView.Adapter<ExpensesAdapter.ExpensesHolder>  {

    private List<Expense> expenseList;

    public ExpensesAdapter(List<Expense> listForSetting){
        this.expenseList=listForSetting;
    }

    @Override
    public ExpensesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View itemView = LayoutInflater.from(parent.getContext())
               .inflate(R.layout.expense_item ,parent,false);
        return new ExpensesHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ExpensesHolder holder, int position) {
        Expense expense = expenseList.get(position);
        holder.description.setText(expense.description);
        holder.price.setText(expense.price );
    }

    @Override
    public int getItemCount() {
        return expenseList.size();
    }

    class ExpensesHolder extends RecyclerView.ViewHolder{

        public TextView description;
        public TextView price;

        public ExpensesHolder(View itemView) {
            super(itemView);
            description = (TextView)itemView.findViewById(R.id.description);
            price = (TextView)itemView.findViewById(R.id.price);
        }
    }
}
