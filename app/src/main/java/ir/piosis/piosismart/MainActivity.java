package ir.piosis.piosismart;


import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;

import java.util.ArrayList;

import ir.piosis.piosismart.fragment.BlankFragment;
import ir.piosis.piosismart.fragment.BlankFragment2;
import ir.piosis.piosismart.fragment.BlankFragment3;


public class MainActivity extends AppCompatActivity implements BlankFragment.OnFragmentInteractionListener,
        BlankFragment2.OnFragmentInteractionListener,BlankFragment3.OnFragmentInteractionListener{

    static boolean activeMainActivityOrNot = false;
    public static Context context;
    static Button button;


    private ViewPagerAdapter adapter;
    private ArrayList<AHBottomNavigationItem> bottomNavigationItems = new ArrayList<>();
    private AHBottomNavigationViewPager viewPager;
    private AHBottomNavigation bottomNavigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.btn_stop_service);
        context = getApplicationContext();
        final Intent i = new Intent(context, UDPListenerService.class);


        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);
        viewPager = (AHBottomNavigationViewPager) findViewById(R.id.viewpagernb);

        AHBottomNavigationItem item1 = new AHBottomNavigationItem("خانه", ContextCompat.getDrawable(this,R.drawable.ic_launcher), Color.parseColor("#6d98ff"));
        AHBottomNavigationItem item2 = new AHBottomNavigationItem("نقشه", ContextCompat.getDrawable(this,R.drawable.ic_launcher)  , Color.parseColor("#f2ff50"));
        AHBottomNavigationItem item3 = new AHBottomNavigationItem("پروفایل" , ContextCompat.getDrawable(this, R.drawable.notification_background) , Color.parseColor("#ff586d"));

        bottomNavigationItems.add(item1);
        bottomNavigationItems.add(item2);
        bottomNavigationItems.add(item3);

        bottomNavigation.addItems(bottomNavigationItems);
        bottomNavigation.setAccentColor(Color.parseColor("#F63D2B"));
        bottomNavigation.setInactiveColor(Color.parseColor("#747474"));

        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                viewPager.setCurrentItem(position, true);
                return true;
            }
        });

        bottomNavigation.setColored(true);

        viewPager.setOffscreenPageLimit(4);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);






// potentially add data to the intent
//        i.putExtra("KEY1", "Value to be used by the service");
        context.startService(i);
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////context.stopService(i);
//                UDP_Client.send(new UDP_Client(), "<Play>1<!$&P!>0,17<Play>");
//            }
//        });

//
        button.setOnTouchListener(new MyTouchListener(context,true,500, new MyTouchListener.MyTouch() {
            @Override
            public void onClick() {
                Toast.makeText(MainActivity.this, button.getText() + " test", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClickDown() {
                button.setScaleX(1.2f);
                button.setScaleY(1.2f);
                button.setAlpha(0.3f);
            }

            @Override
            public void onLongClickUp() {
                button.setScaleX(1f);
                button.setScaleY(1f);
                button.setAlpha(1);
                Toast.makeText(MainActivity.this, button.getText() + " test Long", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClickMove() {

            }

            @Override
            public void onDrag() {
                Toast.makeText(MainActivity.this, "drage", Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        }));
//
//
//

//        button.setOnTouchListener(new MdragListener(context, new MdragListener.InterfaceMdrag() {
//            @Override
//            public void onDr() {
//                Toast.makeText(MainActivity.this, "tesssssssssssst drag", Toast.LENGTH_SHORT).show();
//            }
//        }));


    }


    @Override
    public void onStart() {
        super.onStart();
        activeMainActivityOrNot = true;
    }

    @Override
    public void onStop() {
        super.onStop();
        activeMainActivityOrNot = false;
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
