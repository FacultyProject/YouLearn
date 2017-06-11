package com.ourproject.learningapp.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

import com.ourproject.learningapp.R;
import com.ourproject.learningapp.tabs_fragments.Fragment1;
import com.ourproject.learningapp.tabs_fragments.Fragment2;
import com.ourproject.learningapp.tabs_fragments.Fragment3;

/**
 * Created by Moetaz on 2/24/2017.
 */

public class CustomPagerAdapter extends FragmentPagerAdapter {

    private Context context;

    private int[] icons = {R.drawable.book, R.drawable.exersiceicon, R.drawable.ic_flash};
    private String[] fragments = {"fragment 1", "fragment 2", "fragment 3"};

    public CustomPagerAdapter(FragmentManager fm, Context ApplicationContext) {
        super(fm);
        this.context = ApplicationContext;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        Drawable image = ContextCompat.getDrawable(context, icons[position]);
        image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
        SpannableString sb = new SpannableString(" ");
        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sb;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Fragment1();
            case 1:

                return new Fragment2();
            case 2:
                return new Fragment3();

            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return fragments.length;
    }
}