package me.littlecheesecake.slidingtablayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import me.littlecheesecake.slidingtablayout.library.OnTabSelectListener;
import me.littlecheesecake.slidingtablayout.library.SlidingTabLayout;

public class MainActivity extends AppCompatActivity implements OnTabSelectListener {
    private Context context = this;
    private ArrayList<Fragment> fragments = new ArrayList<>();

    private final String[] titles = {
            "beauty", "happy", "lovely", "sunshine", "freedom"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (String title : titles) {
            fragments.add(SimpleFragment.getInstance(title));
        }

        ViewPager vp = (ViewPager) findViewById(R.id.vp);
        vp.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        SlidingTabLayout slidingTabLayout = (SlidingTabLayout) findViewById(R.id.tab);

        slidingTabLayout.setViewPager(vp);
        vp.setCurrentItem(1);
    }


    @Override
    public void onTabSelect(int position) {

    }

    @Override
    public void onTabReselect(int position) {

    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }

    @SuppressLint("ValidFragment")
    private static class SimpleFragment extends Fragment {
        private String title;

        public static SimpleFragment getInstance(String title) {
            SimpleFragment sf = new SimpleFragment();
            sf.title = title;
            return sf;
        }

        @Override
        public void onCreate(Bundle saveInstanceState) {
            super.onCreate(saveInstanceState);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
            View v = inflater.inflate(R.layout.fragment_view, null);
            TextView cardTitleTextView = (TextView) v.findViewById(R.id.title_text);
            cardTitleTextView.setText(title);

            return v;
        }
    }
}
