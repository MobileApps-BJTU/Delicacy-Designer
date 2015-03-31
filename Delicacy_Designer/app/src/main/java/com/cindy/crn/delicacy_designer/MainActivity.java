package com.cindy.crn.delicacy_designer;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cindy.crn.delicacy_designer.createRecipe.CreateRecipeActivity;
import com.cindy.crn.delicacy_designer.sortlistview.ContactActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements
        ViewPager.OnPageChangeListener, View.OnClickListener, ForumFragment.OnFragmentInteractionListener {


    //private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private SlideMenu slideMenu;
    private TextView userName, createRecipe, recipeCollection, myGroup, myOrder, follower, myshare;
    private ImageView slideSwicher;
    private List<ImageView> mTabIndicator = new ArrayList<ImageView>();
    private List<Fragment> mTabs = new ArrayList<Fragment>();
    private ImageView qrbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //   initDatas();
        HomeFragment homeFragment = new HomeFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.viewpager, homeFragment);
        transaction.addToBackStack(null);
        transaction.commit();

        initTabIndicator();

        slideMenu = (SlideMenu) findViewById(R.id.slideMenu);
        slideSwicher = (ImageView) findViewById(R.id.title_bar_menu_btn);
        slideSwicher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (slideMenu.isMainScreenShowing()) {
                    slideMenu.openMenu();
                    ;
                } else {
                    slideMenu.closeMenu();
                }
            }
        });

        //TODO:给侧边栏每一栏加点击事件跳转到对应界面
        createRecipe = (TextView) findViewById(R.id.createRecipe);
        createRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateRecipeActivity.class);
                startActivity(intent);
            }
        });
        myshare = (TextView) findViewById(R.id.myshare);
        myshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ShareActivity.class);
                startActivity(i);
            }
        });

        myOrder = (TextView) findViewById(R.id.myOrder);
        myOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, ShopCarActivity.class);
                startActivity(it);
            }
        });
        follower = (TextView) findViewById(R.id.following);
        follower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, ContactActivity.class);
                startActivity(it);
            }
        });
        qrbutton = (ImageView) findViewById(R.id.qr_button);
        qrbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, MipcaActivityCapture.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                //startActivityForResult(intent, SCANNIN_GREQUEST_CODE);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void initTabIndicator() {
        ImageView one = (ImageView) findViewById(R.id.menu_home);
        ImageView two = (ImageView) findViewById(R.id.menu_forum);
        ImageView three = (ImageView) findViewById(R.id.menu_market);
        ImageView four = (ImageView) findViewById(R.id.menu_setting);

        mTabIndicator.add(one);
        mTabIndicator.add(two);
        mTabIndicator.add(three);
        mTabIndicator.add(four);

        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        resetOtherTabs();
//TODO:为每一次选中的图标加深颜色
        switch (v.getId()) {
            case R.id.menu_home:

                // Toast.makeText(MainActivity.this, "aa", Toast.LENGTH_SHORT).show();
                HomeFragment homeFragment = new HomeFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.viewpager, homeFragment);
                transaction.addToBackStack(null);
                transaction.commit();
                break;
            case R.id.menu_forum:
                //  Toast.makeText(MainActivity.this, "bb", Toast.LENGTH_SHORT).show();
                ForumFragment forumFragment = new ForumFragment();
                transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.viewpager, forumFragment);
                transaction.addToBackStack(null);
                transaction.commit();
                break;
            case R.id.menu_market:

                MarketFragment marketFragment = new MarketFragment();
                transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.viewpager, marketFragment);
                transaction.addToBackStack(null);
                transaction.commit();
                break;
            case R.id.menu_setting:

                SettingFragment settingFragment = new SettingFragment();
                transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.viewpager, settingFragment);
                transaction.addToBackStack(null);
                transaction.commit();
                break;

        }


    }

    /**
     * set other Tab
     */
    // TODO:更改未选中图标颜色
    private void resetOtherTabs() {
        for (int i = 0; i < mTabIndicator.size(); i++) {

        }
    }

    @Override
    public void onPageScrolled(int i, float v, int i2) {

    }

    @Override
    public void onPageSelected(int i) {

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void showDetail() {
        Intent intent = new Intent(MainActivity.this, MenuDetailActivity.class);
        startActivity(intent);
    }
}
