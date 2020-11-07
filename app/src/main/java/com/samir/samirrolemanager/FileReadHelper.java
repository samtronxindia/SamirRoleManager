package com.samir.samirrolemanager;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.samir.samirrolemanager.MainActivity.roles;

public class FileReadHelper extends AsyncTask<Context, Void, Void> {

    private WeakReference<Context> contextWeakReference;
    public FileReadHelper(Context context) {
        contextWeakReference = new WeakReference<>(context);
    }

    @Override
    protected Void doInBackground(Context... contexts) {
        Context context = contextWeakReference.get();
        try {
            String[] list;
            list = context.getAssets().list("");
            int fileNum = 0;
            for (String f1 : list) {
                Pattern p = Pattern.compile("^role");
                Matcher m = p.matcher(f1);
                if (m.find()) {
                    Log.v("Filenames: from FAB", f1);
                    String[] roleNum = f1.split("_");
                    InputStreamReader is = new InputStreamReader(context.getAssets().open(f1));
                    BufferedReader reader = new BufferedReader(is);
                    reader.readLine();
                    String line;
                    List<String> lines = new ArrayList<String>();
                    while ((line = reader.readLine()) != null) {
                        lines.add(line);
                    }
                    MainActivity.roles.put(Integer.parseInt(roleNum[1]),lines);
                    is.close();
                    reader.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "The specified file was not found", Toast.LENGTH_SHORT).show();
        }
        return null;
    }



    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        Context context = contextWeakReference.get();

        RoleManagerDB.databaseWriteExecutor.execute(() -> {
            RoleManagerDB roleManagerDB = RoleManagerDB.getDatabase(context);

            RoleManagerDAO dao = roleManagerDB.roleManagerDAO();
            dao.deleteAllRolePerms();
            //RolePermission rp = new RolePermission();
            //Creating several rows using permId
            RolePermission rp_new = new RolePermission();
            for (int i = 1; i < 20; i++) {
                rp_new.setPermId(i);
                dao.insertPermRole1(rp_new);
            }

            //create several rows for the role_app table
            RoleApp ra_new = new RoleApp();
            for (int i = 1; i < 20; i++) {
                ra_new.setRoleId(i);
                dao.insertRoleApp1(ra_new);
            }

            //create several rows for the role_active table
            RoleActiveApp ractive_new = new RoleActiveApp();
            for (int i = 1; i < 20; i++) {
                ractive_new.setRoleActiveId(i);
                dao.insertRoleActive1(ractive_new);
            }

            //create several rows for the running_apps table
            RunningApps runningapps = new RunningApps();
            for (int i = 1; i < 20; i++) {
                runningapps.setRunningAppId(i);
                runningapps.setAppRunning(false);
                dao.insertRunningApp(runningapps);
            }

            //updating roles indexed by permId
            Log.v("SamirRoleManager","Outputting roles:");
            for (Map.Entry<Integer, List<String>> entry : roles.entrySet()) {
                int tempPermId = 1;
                switch(entry.getKey()){
                    case 1:
                        tempPermId = 1;
                        for (String rolePerm : entry.getValue()){
                            dao.updatePermRole1(tempPermId,rolePerm);
                            tempPermId++;
                        }
                        break;
                    case 2:
                        tempPermId = 1;
                        for (String rolePerm : entry.getValue()){
                            dao.updatePermRole2(tempPermId,rolePerm);
                            tempPermId++;
                        }
                        break;
                    case 3:
                        tempPermId = 1;
                        for (String rolePerm : entry.getValue()){
                            dao.updatePermRole3(tempPermId,rolePerm);
                            tempPermId++;
                        }
                        break;
                    case 4:
                        tempPermId = 1;
                        for (String rolePerm : entry.getValue()){
                            dao.updatePermRole4(tempPermId,rolePerm);
                            tempPermId++;
                        }
                        break;
                    case 5:
                        tempPermId = 1;
                        for (String rolePerm : entry.getValue()){
                            dao.updatePermRole5(tempPermId,rolePerm);
                            tempPermId++;
                        }
                        break;
                    case 6:
                        tempPermId = 1;
                        for (String rolePerm : entry.getValue()){
                            dao.updatePermRole6(tempPermId,rolePerm);
                            tempPermId++;
                        }
                        break;
                    case 7:
                        tempPermId = 1;
                        for (String rolePerm : entry.getValue()){
                            dao.updatePermRole7(tempPermId,rolePerm);
                            tempPermId++;
                        }
                        break;
                    case 8:
                        tempPermId = 1;
                        for (String rolePerm : entry.getValue()){
                            dao.updatePermRole8(tempPermId,rolePerm);
                            tempPermId++;
                        }
                        break;
                    case 9:
                        tempPermId = 1;
                        for (String rolePerm : entry.getValue()){
                            dao.updatePermRole9(tempPermId,rolePerm);
                            tempPermId++;
                        }
                        break;
                    case 10:
                        tempPermId = 1;
                        for (String rolePerm : entry.getValue()){
                            dao.updatePermRole10(tempPermId,rolePerm);
                            tempPermId++;
                        }
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + entry.getKey());
                }

                for (String rolePerm : entry.getValue()) {
                    Log.v("Role:", entry.getKey().toString() + ":" + rolePerm);
                }
            }
        });

    }
}
