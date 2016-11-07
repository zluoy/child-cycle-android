package com.example.eunji.childcycle;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Eunji on 2016. 9. 25..
 */

public class RidingMainActivity extends AppCompatActivity {

    private Button button_stop, button_pause;
    private TextView riding_time, today_wether, weather_temp, riding_distanceh, riding_speed;
    private ImageView handle_aram, speed_aram, distance_aram;

    private DrawerLayout drawer;
    private NavigationView navigationView;

    public static int num = 0;

    private void _InitUi() {

        button_stop = (Button) findViewById(R.id.button_stop);
        button_pause = (Button) findViewById(R.id.button_pause);

        riding_time = (TextView) findViewById(R.id.riding_time);
        today_wether = (TextView) findViewById(R.id.today_weather);
        weather_temp = (TextView) findViewById(R.id.weather_temp);
        riding_distanceh = (TextView) findViewById(R.id.riding_distance);
        riding_speed = (TextView) findViewById(R.id.riding_speed);

        handle_aram = (ImageView) findViewById(R.id.handle_aram);
        speed_aram = (ImageView) findViewById(R.id.speed_aram);
        distance_aram = (ImageView) findViewById(R.id.distance_aram);

    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.riding_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.app_toolbar);
//        setSupportActionBar(toolbar);

        _InitUi();

        ActionBar actionBar = getSupportActionBar();


        actionBar.setBackgroundDrawable(new ColorDrawable(0xFFFFFFFF));
        actionBar.setTitle(Html.fromHtml("<font color='#000000'> ChildCycle </font>"));
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);


//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
//        toggle.syncState();
//
//        navigationView.setNavigationItemSelectedListener(this);

    }

    public void pauseClick(View v) {
        ++num;

        if (num % 2 == 1) {
            button_pause.setText("시작");
        } else
            button_pause.setText("일시정지");

    }

    public void stopClick(View v) {
        new AlertDialog.Builder(this)
                .setTitle("")
                .setMessage("정말로 운행을 종료할까요?")
                .setPositiveButton("예", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(getApplicationContext(), FinishRidingActivity.class);
                        startActivity(intent);

                    }
                })
                .setNegativeButton("아니오", null)
                .show();

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        boolean bFinish = intent.getBooleanExtra("FinishSelf", false);
        if (bFinish)
            finish();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        switch (keyCode) {
            //하드웨어 뒤로가기 버튼에 따른 이벤트 설정
            case KeyEvent.KEYCODE_BACK:

                new AlertDialog.Builder(this)
                        .setTitle("프로그램 종료")
                        .setMessage("프로그램을 종료 하시겠습니까?")
                        .setPositiveButton("예", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 앱 종료.
                                Intent clearTop = new Intent(getApplicationContext(), MainActivity.class);
                                clearTop.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                clearTop.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                clearTop.putExtra("FinishSelf", true);
                                startActivity(clearTop);
                                finish();

                            }
                        })

                        .setNegativeButton("아니오", null)
                        .show();
                break;

            default:
                break;
        }

        return super.onKeyDown(keyCode, event);
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//    @SuppressWarnings("StatementWithEmptyBody")
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//        if (id == R.id.drawer_main) {
//            Intent intent1 = new Intent(getApplicationContext(),RidingMainActivity.class);
//            startActivity(intent1);
//
//        } else if (id == R.id.drawer_history) {
//
//        } else if (id == R.id.drawer_setting) {
//
//        }
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }

}
