package com.samir.samirrolemanager;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UA_Adapter extends RecyclerView.Adapter<UA_Adapter.AppViewHolder> {

    HashMap<String, List<String>> appList = new HashMap<>();
    private static final String[] mRoleNumArray = {"role1_heading", "role2_heading", "role3_heading",
            "role4_heading", "role5_heading", "role6_heading",
            "role7_heading", "role8_heading", "role9_heading", "role10_heading"};
    private ArrayList<String> roleApp = new ArrayList<>();
    private final String TAG="UA_Adapter";

    public UA_Adapter(HashMap<String, List<String>> appList) {
        this.appList = appList;
    }

    @NonNull
    @Override
    public UA_Adapter.AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.content_main2, parent, false);
        return new AppViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull UA_Adapter.AppViewHolder holder, int position) {

        for (Map.Entry<String, List<String>> entry : appList.entrySet()){
            roleApp.addAll(Collections.singleton(entry.getKey()));
        }

        String tempRoleApp = roleApp.get(position);
        List<String> items = loadAppRoles(tempRoleApp);

        holder.appView.setText(tempRoleApp);

        ChildRecyclerAdapter2 childRecyclerAdapter2 = new ChildRecyclerAdapter2(items);
        holder.childRecyclerView2.setAdapter(childRecyclerAdapter2);

    }

    private List<String> loadAppRoles(String tempAppRole) {
        List<String> tempRoleApps = new ArrayList<>();
        tempRoleApps.clear();
        Log.v(TAG, "loadAppRoles called!");
        for (Map.Entry<String, List<String>> entry : appList.entrySet()) {
            if (entry.getKey().equals(tempAppRole)) {
                Log.v(TAG, "Inside if " + entry.getValue() + "key: " + entry.getKey() + " temp variable : " + tempAppRole);
                for(String rolePerm : entry.getValue()){
                    Log.v(TAG, "individual variable rolePerm : " + rolePerm);
                    tempRoleApps.add(rolePerm);
                }
                break;
            }
        }
        return tempRoleApps;
    }

    @Override
    public int getItemCount() {
        return appList.size();
    }

    public class AppViewHolder extends RecyclerView.ViewHolder{

        public final TextView appView;
        RecyclerView childRecyclerView2;

        public AppViewHolder(@NonNull View itemView) {
            super(itemView);
            appView = itemView.findViewById(R.id.sectionNameTextView2);
            childRecyclerView2 = itemView.findViewById(R.id.childRecyclerView2);
        }
    }
}
