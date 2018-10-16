package edu.njit.surplus.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.facebook.drawee.backends.pipeline.Fresco;

import edu.njit.surplus.R;
import edu.njit.surplus.adapter.FoodListAdapter;
import edu.njit.surplus.adapter.RequestListAdapter;
import edu.njit.surplus.fragment.FoodListFragment;
import edu.njit.surplus.fragment.PeopleListFragment;
import edu.njit.surplus.network.GPSTracker;
import edu.njit.surplus.utilities.Constants;

/**
 * Created by srini on 11/11/17.
 */

public class HomeActivity extends AppCompatActivity implements FoodListAdapter.FoodItemListener, RequestListAdapter.RequestItemListener {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private HomeFragmentPagerAdapter homeFragmentPagerAdapter;
    private ImageView ivAdd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_home);

        viewPager = (ViewPager) findViewById(R.id.vp_home);
        tabLayout = (TabLayout) findViewById(R.id.tl_home);
        ivAdd = (ImageView) findViewById(R.id.iv_add);

        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, AddFoodActivity.class);
                startActivity(intent);
            }
        });

        homeFragmentPagerAdapter = new HomeFragmentPagerAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(homeFragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        
    }

    @Override
    public void onFoodItemClicked(int postId) {
        Intent intent = new Intent(HomeActivity.this, DetailsActivity.class);
        intent.putExtra(Constants.INTENT_KEY_POST_ID, postId);
        startActivity(intent);
    }

    @Override
    public void onRequestItemClicked(int postId) {
        Intent intent = new Intent(HomeActivity.this, RequestDetailsActivity.class);
        intent.putExtra(Constants.INTENT_KEY_POST_ID, postId);
        startActivity(intent);
    }

    public class HomeFragmentPagerAdapter extends FragmentPagerAdapter {

        private Context context;

        public HomeFragmentPagerAdapter(Context context, FragmentManager fragmentManager) {
            super(fragmentManager);
            this.context = context;
        }

        // This determines the fragment for each tab
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 1:
                    return new PeopleListFragment();
                default:
                    return new FoodListFragment();
            }
        }

        // This determines the number of tabs
        @Override
        public int getCount() {
            return 2;
        }

        // This determines the title for each tab
        @Override
        public CharSequence getPageTitle(int position) {
            // Generate title based on item position
            switch (position) {
                case 1:
                    return context.getString(R.string.people);
                default:
                    return context.getString(R.string.food);
            }
        }
    }


}
