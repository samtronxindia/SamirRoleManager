package com.samir.samirrolemanager;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.samir.samirrolemanager.MainActivity.roles;
import static java.sql.Types.NULL;

@Database(entities = {RolePermission.class, RoleApp.class, RoleActiveApp.class}, version = 12, exportSchema = false)
public abstract class RoleManagerDB extends RoomDatabase {
    public abstract RoleManagerDAO roleManagerDAO();

    private static volatile RoleManagerDB INSTANCE;
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

            });
        }
    };



}
