package com.andre.tasks.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.andre.tasks.R;
import com.andre.tasks.constants.TaskConstants;
import com.andre.tasks.infra.OperationListener;
import com.andre.tasks.infra.SecurityPreferences;
import com.andre.tasks.manager.PriorityManager;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private SecurityPreferences mSecurityPreferences;

    private Context mContext;
    private PriorityManager mPriorityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mContext = this;
        this.mPriorityManager = new PriorityManager(this.mContext);

        this.mViewHolder.textHello = findViewById(R.id.text_hello);
        this.mViewHolder.textDateDescription = findViewById(R.id.text_date_description);
        this.mViewHolder.textTaskCount = findViewById(R.id.text_task_count);
        this.mViewHolder.textTaskComplete = findViewById(R.id.text_task_complete_count);

        this.mSecurityPreferences = new SecurityPreferences(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        this.initialLoad();
        
        this.formatUserName();
        this.formatDateDescription();

        // Incia a fragment padrão
        this.startDefaultFragment();
    }




    private void initialLoad() {
        this.mPriorityManager.getList(priorityListener());
    }
    private OperationListener priorityListener(){
        return new OperationListener<Boolean>(){
            @Override
            public void onSucess(Boolean result){

            }
            @Override
            public void onError(int errorCode,String errorMessage){
             }
        };

    }

    private void formatUserName() {
        String userName  = this.mSecurityPreferences.getStoredString(TaskConstants.USER.NAME);
        String userEmail  = this.mSecurityPreferences.getStoredString(TaskConstants.USER.EMAIL);
        this.mViewHolder.textHello.setText("Olá , " + userName + '!');

        NavigationView nav = this.findViewById(R.id.nav_view);

        View header  = nav.getHeaderView(0);

        TextView name = header.findViewById(R.id.text_name);
        TextView email = header.findViewById(R.id.text_email);

        name.setText(userName);
        email.setText(userEmail);
    }

    private void formatDateDescription() {
        Calendar calendar = Calendar.getInstance();

        String[] days = {"Domingo","Segunda-Feira","Terça-Feira","Quarta-Feira","Quinta-Feira","Sexta-Feira","Sábado"};
        String[] months = {"Janeiro","Fevereiro","Março","Abril","Maio","Junho","Julho","Agosto","Setembro","Outubro","Novembro","Dezembro"};

        String str = (days[calendar.get(Calendar.DAY_OF_WEEK ) -1 ])+ ", "
                +    (calendar.get(Calendar.DAY_OF_MONTH)) + " de "
                +    (months[calendar.get(Calendar.MONTH )]) ;

        this.mViewHolder.textDateDescription.setText(str);
    }

    public void updateTasksCount(int complete,int  total){

        String strAll;
        if(total == 0){
            strAll = getString(R.string.nenhuma_tarefa);
        }
        else if(total == 1){
            strAll = getString(R.string.uma_tarefa);
        }
        else {
            strAll = String.valueOf(total) + " " + getString(R.string.tarefas);
        }

        String strComplete;
        if(complete == 0){
            strComplete = getString(R.string.nenhuma_completa);
        }
        else if(complete == 1){
            strComplete = getString(R.string.uma_completa);
        }
        else {
            strComplete = String.valueOf(complete) + " " + getString(R.string.completas);
        }

        this.mViewHolder.textTaskCount.setText(strAll);
        this.mViewHolder.textTaskComplete.setText(strComplete);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        Fragment fragment = null;
        int id = item.getItemId();

        try {
            if (id == R.id.nav_all_tasks) {
                fragment = TaskListFragment.newInstance(TaskConstants.TASK_FILTERS.NO_FILTER);
            } else if (id == R.id.nav_next_seven_days) {
                fragment = TaskListFragment.newInstance(TaskConstants.TASK_FILTERS.NEXT_7_DAYS);
            } else if (id == R.id.nav_overdue) {
                fragment = TaskListFragment.newInstance(TaskConstants.TASK_FILTERS.OVERDUE);
            } else if (id == R.id.nav_logout) {
                this.handleLogout();
                return true;
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insere fragment substituindo qualquer existente
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_content, fragment).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void handleLogout() {
        SecurityPreferences securityPreferences = new SecurityPreferences(this.mContext);
        securityPreferences.removeStoredString(TaskConstants.HEADER.PERSON_KEY);
        securityPreferences.removeStoredString(TaskConstants.HEADER.TOKEN_KEY);
        securityPreferences.removeStoredString(TaskConstants.USER.NAME);
        securityPreferences.removeStoredString(TaskConstants.USER.EMAIL);

        startActivity(new Intent(this.mContext,LoginActivity.class));
    }

    /**
     * Incia a fragment padrão
     */
    private void startDefaultFragment() {

        Fragment fragment = null;
        try {
            fragment = TaskListFragment.newInstance(TaskConstants.TASK_FILTERS.NO_FILTER);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insere fragment substituindo qualquer existente
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_content, fragment).commit();
    }

    /**
     * ViewHolder
     */
    private static class ViewHolder {
        private TextView textHello;
        private TextView textDateDescription;
        private TextView textTaskComplete;
        private TextView textTaskCount;

    }
}
