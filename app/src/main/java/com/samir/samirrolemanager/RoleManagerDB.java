package com.samir.samirrolemanager;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {RolePermission.class, RoleApp.class, RoleActiveApp.class, RunningApps.class}, version = 13, exportSchema = false)
public abstract class RoleManagerDB extends RoomDatabase {
    public abstract RoleManagerDAO roleManagerDAO();

    protected static volatile RoleManagerDB INSTANCE;
    private static boolean runComplete = false;

    Context context;

    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(10);

    static RoleManagerDB getDatabase(final Context context) {
        if(INSTANCE == null) {
            synchronized (RoleManagerDB.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context,RoleManagerDB.class, "word_database").addCallback(sRoomDatabaseCallback).fallbackToDestructiveMigration().allowMainThreadQueries().build();
                }
            }
        }
        return INSTANCE;
    }

    private static final Callback sRoomDatabaseCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                //RoleManagerDB roleManagerDB = RoleManagerDB.getDatabase(this);
                RoleManagerDAO dao = INSTANCE.roleManagerDAO();
                dao.deleteAllRolePerms();
                dao.deleteAllRoleApps();
                dao.deleteAllRoleActive();
                dao.deleteAllRunningApps();

            });
        }
    };



}
