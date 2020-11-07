package com.samir.samirrolemanager;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Role_ListAdapter extends
        RecyclerView.Adapter<Role_ListAdapter.RoleViewHolder> {

    private List<Role> roleList;
    private final String TAG = "Role_ListAdapter";
    protected volatile static int childPosition = -1;
    static volatile int pos = 0;

    public Role_ListAdapter(List<Role> roleList) {
        this.roleList = roleList;
    }

    @NonNull
    @Override
    public Role_ListAdapter.RoleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.content_main, parent, false);
        return new RoleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoleViewHolder holder, int position) {

        Role role = roleList.get(position);
        String roleName = role.getRoleHeading();
        List<String> items = role.getRolePerms();

        holder.roleView.setText(roleName);

        ChildRecyclerAdapter childRecyclerAdapter = new ChildRecyclerAdapter(items);
        holder.childRecyclerView.setAdapter(childRecyclerAdapter);
    }

    public static void getPosition(int posit){
        pos = posit;
    }

    @Override
    public int getItemCount() {
        return roleList.size();
    }

    public class RoleViewHolder extends RecyclerView.ViewHolder {
        public final TextView roleView;
        RecyclerView childRecyclerView;

        public RoleViewHolder(View itemView) {
            super(itemView);
            roleView = itemView.findViewById(R.id.sectionNameTextView);
            childRecyclerView = itemView.findViewById(R.id.childRecyclerView);
        }
    }
}