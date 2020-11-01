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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Role_ListAdapter extends
        RecyclerView.Adapter<Role_ListAdapter.RoleViewHolder> {

    private final LinkedList<String> mRoleList;
    private LayoutInflater mInflater;

    static Context ctx;

    private int position;

    private static volatile RoleManagerDB INSTANCE;

    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(6);

    public Role_ListAdapter(Context context,
                           LinkedList<String> roleList) {
        mInflater = LayoutInflater.from(context);
        this.mRoleList = roleList;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @NonNull
    @Override
    public Role_ListAdapter.RoleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.list_item,
                parent, false);
        return new RoleViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull Role_ListAdapter.RoleViewHolder holder, int position) {
        String mCurrent = mRoleList.get(position);
        holder.roleItemView.setText(mCurrent);
        /*if(position == 0){
            holder.roleItemView.setTextColor(ColorStateList.valueOf(0xff0000ff ));
        }*/

      /*  holder.itemView.setOnCreateContextMenuListener(new View.OnClickListener());{
            @Override
                    public boolean onLongClick(View v){
                setPosition(holder.getAdapterPosition());
                return false;
            }
        };*/
    }

    @Override
    public int getItemCount() {
        return mRoleList.size();
    }

    class RoleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView roleItemView;
        final Role_ListAdapter mAdapter;

        public void onClick(View v) {
            // Get the position of the item that was clicked.
            int mPosition = getLayoutPosition();
            // Use that to access the affected item in mWordList.
            String element = mRoleList.get(mPosition);
            Toast.makeText(ctx,"Clicked on:" + element,Toast.LENGTH_SHORT).show();
            // Change the word in the mWordList.
            mRoleList.set(mPosition, "Clicked! " + element);
            // Notify the adapter, that the data has changed so it can
            // update the RecyclerView to display the data.
            mAdapter.notifyDataSetChanged();
        }

        public RoleViewHolder(View itemView, Role_ListAdapter adapter) {
            super(itemView);
            roleItemView = itemView.findViewById(R.id.word);
            this.mAdapter = adapter;
        }
    }
}