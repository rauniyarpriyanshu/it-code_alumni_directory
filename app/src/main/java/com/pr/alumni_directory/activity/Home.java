package com.pr.alumni_directory.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.navigation.NavigationBarView;
import com.pr.alumni_directory.R;
import com.pr.alumni_directory.data.AlumniData;
import com.pr.alumni_directory.db.CsvLoader;
import com.pr.alumni_directory.db.LoginInfo;
import com.pr.alumni_directory.fragment.ActivityInterface;
import com.pr.alumni_directory.ui.MyRecyclerView;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Home extends AppCompatActivity implements ActivityInterface {
    SearchView searchView;
    RecyclerView myRecyclerView;
    CountDownLatch countDownLatch;
    LoginInfo info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        screenInitialize();
        appComponentLoad();
    }

    public void filterList(String search, ArrayList<AlumniData> data, MyRecyclerView recyclerView) {
        List<AlumniData> filter = new ArrayList<>();

        Pattern regex=Pattern.compile(search,Pattern.CASE_INSENSITIVE);
        for (AlumniData value : data) {
            Matcher matcher=regex.matcher(value.getName());
            Matcher matcher2=regex.matcher(value.getJobTitle());
            Matcher matcher3=regex.matcher(value.getSubData().getNameOfBusiness());

            if (matcher.find()){
                filter.add(value);
            } else if (matcher3.find()) {
                filter.add(value);
            } else if (matcher2.find()) {
                filter.add(value);
            }
        }
        if (!filter.isEmpty()) {
            recyclerView.setFilteredList(filter);
        }

    }

    public void copyDataBase() {
        File folder = new File(this.getExternalCacheDir() + "/databaseFile/");
        if (!folder.exists()) {
            folder.mkdirs();
        }

        if (!folder.exists()) {
            folder.mkdir();
        }

        if (!folder.exists()) {
            Toast.makeText(this, "Failed to create database folder", Toast.LENGTH_SHORT).show();
        }


        AssetManager manager = this.getAssets();
        try {
            InputStream stream = manager.open("database.csv");

            try (BufferedInputStream inputStream = new BufferedInputStream(stream)) {
                int data;
                BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(new File(folder, "database.csv")));
                byte[] buffer = new byte[1024];
                while ((data = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, data);
                    outputStream.flush();
                }


                outputStream.close();
            }

        } catch (IOException e) {
            Toast.makeText(this, "Failed to load database from Asset file", Toast.LENGTH_SHORT).show();
        }


    }

    public void initializeNavBar(boolean isAdmin) {
        BottomNavigationView navBar = findViewById(R.id.home_navBar);

        if (!isAdmin) {
            Menu menu = navBar.getMenu();
            MenuItem item = menu.findItem(R.id.navbar_edit);
            item.setVisible(false);
        }
        navBar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                if (menuItem.getItemId() == R.id.navbar_home) {

                } else if (menuItem.getItemId() == R.id.navbar_edit) {
                    Intent intent = new Intent($activity(), EditDetails.class);
                    $activity().startActivity(intent);
                    $activity().finish();

                } else if (menuItem.getItemId() == R.id.navbar_logout) {
                    showDialog();
                }

                return true;
            }
        });

    }

    public void showDialog() {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder($context())
                .setTitle("")
                .setMessage("Are you sure want to logout?")
                .setCancelable(true)
                .setIcon(R.drawable.ic_warning)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent($activity(), Login.class);
                        $activity().startActivity(intent);
                        $activity().finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        builder.show();

    }

    public boolean databaseExist() {
        File file = new File(this.getExternalCacheDir() + "/databaseFile/" + "database.csv");
        return file.exists();
    }


    @Override
    public void screenInitialize() {
        myRecyclerView = findViewById(R.id.home_recyclerView);
    }

    @Override
    public void appComponentLoad() {
        final String FILE_PATH = "/databaseFile/database.csv";
        CsvLoader loader = new CsvLoader(this.getExternalCacheDir() + FILE_PATH);
        countDownLatch = new CountDownLatch(1);

        info = (LoginInfo) getIntent().getSerializableExtra("user_d");

        boolean isAdmin = false;
        if (info != null) {
            isAdmin = info.isAdmin();
        }
        initializeNavBar(isAdmin);

        if (!databaseExist()) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    copyDataBase();
                    countDownLatch.countDown();
                }
            }).start();
        } else {
            countDownLatch.countDown();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        ArrayList<AlumniData> data;
        try {
            loader.readCSV();
            data = new ArrayList<>(loader.getList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        MyRecyclerView adapter = new MyRecyclerView(this, data);

        if (info != null) {
            adapter.setIsAdmin(info.isAdmin());
        }

        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myRecyclerView.setAdapter(adapter);

        searchView = findViewById(R.id.home_searchView);

        this.getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (searchView.isFocused()) {
                    searchView.clearFocus();
                } else {
                    setEnabled(false);
                    getOnBackPressedDispatcher().onBackPressed();
                }
            }
        });


        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                searchView.clearFocus();
                return true;
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()) {
                    searchView.clearFocus();
                }
                filterList(newText, data, adapter);
                return true;
            }
        });
    }

    @Override
    public Activity $activity() {
        return this;
    }

    @Override
    public Context $context() {
        return this;
    }
}