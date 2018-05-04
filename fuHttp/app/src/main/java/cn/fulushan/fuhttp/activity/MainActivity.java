package cn.fulushan.fuhttp.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import cn.fulushan.fuhttp.R;
import cn.fulushan.fuhttp.fragment.FileFragment;
import cn.fulushan.fuhttp.fragment.HomeFragment;
import cn.fulushan.fuhttp.fragment.MineFragment;

public class MainActivity extends AppCompatActivity {

    private FrameLayout body_frame;

    private FragmentManager fragmentManager;
    private HomeFragment homeFragment;
    private FileFragment fileFragment;
    private MineFragment mineFragment;
    private int checkId = R.id.navigation_home;
    FragmentTransaction transaction;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    onCheckChange(item.getItemId());
                    return true;
                case R.id.navigation_dashboard:
                    onCheckChange(item.getItemId());
                    return true;
                case R.id.navigation_notifications:
                    onCheckChange(item.getItemId());
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();

        body_frame = findViewById(R.id.body_frame);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        onCheckChange(R.id.navigation_home);

    }



    protected void onCheckChange(int checkedI) {
        this.checkId = checkedI;
        // 开启一个Fragment事务
        transaction = fragmentManager.beginTransaction();
        hideFragments(transaction);
        switch (checkedI) {
            case R.id.navigation_home:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    transaction.add(R.id.body_frame, homeFragment);
                } else {
                    transaction.show(homeFragment);
                }

                break;
            case R.id.navigation_dashboard:
                if (fileFragment == null) {
                    fileFragment = new FileFragment();
                    transaction.add(R.id.body_frame, fileFragment);
                } else {
                    transaction.show(fileFragment);
                }
                break;
            case R.id.navigation_notifications:
                if (mineFragment == null) {
                    mineFragment = new MineFragment();
                    transaction.add(R.id.body_frame, mineFragment);
                } else {
                    transaction.show(mineFragment);
                }
                break;
            default:
                break;
        }
        transaction.commitAllowingStateLoss();
    }


    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction 用于对Fragment执行操作的事务
     */
    private void hideFragments(FragmentTransaction transaction) {

        if (mineFragment != null) {
            transaction.hide(mineFragment);
        }
        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (fileFragment != null) {
            transaction.hide(fileFragment);
        }

    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        // TODO Auto-generated method stub
        super.onAttachFragment(fragment);
        if (homeFragment == null && fragment instanceof HomeFragment) {
            homeFragment = (HomeFragment) fragment;
        } else if (fileFragment == null && fragment instanceof FileFragment) {
            fileFragment = (FileFragment) fragment;
        } else if (mineFragment == null && fragment instanceof MineFragment) {
            mineFragment = (MineFragment) fragment;
        }
    }

}
