package com.samir.samirrolemanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.ref.WeakReference;
import java.util.List;

public class ChildRecyclerAdapter2 extends RecyclerView.Adapter<ChildRecyclerAdapter2.ViewHolder> {
    List<String> items2;
    private String TAG = "ChildRecyclerAdapter2";
    private static volatile String[] mRoleArray = {"role1","role2","role3","role4","role5","role6","role7","role8","role9","role10"};

    public ChildRecyclerAdapter2(List<String> items) {
        this.items2 = items;
    }

    @NonNull
    @Override
    public ChildRecyclerAdapter2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item2,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildRecyclerAdapter2.ViewHolder holder, int position) {
        holder.itemTextView2.setText(items2.get(position));
    }

    @Override
    public int getItemCount() {
        return items2.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView itemTextView2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemTextView2 = itemView.findViewById(R.id.itemTextView2);
        }
    }
}
