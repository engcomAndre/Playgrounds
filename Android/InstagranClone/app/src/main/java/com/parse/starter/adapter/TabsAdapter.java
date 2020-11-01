package com.parse.starter.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.ViewGroup;

import com.parse.starter.R;
import com.parse.starter.fragments.HomeFragment;
import com.parse.starter.fragments.UsuariosFragment;

import java.util.HashMap;

public class TabsAdapter extends FragmentStatePagerAdapter {

    private String[] abas = new String[]{"HOME","USUARIOS"};
    private int[] icones = new int[]{R.drawable.ic_action_home,R.drawable.ic_people};
    private int tamanhoIcone;
    private Context mContext;
    private HashMap<Integer,Fragment> fragmentHashMap;

    public TabsAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.mContext = context;
        double escala = this.mContext.getResources().getDisplayMetrics().density;
        this.tamanhoIcone = (int)(36 * escala);
        this.fragmentHashMap = new HashMap<>();
   }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:{
                fragment = new HomeFragment();
                this.fragmentHashMap.put(position,fragment);
                break;
            }
            case 1:{
                fragment = new UsuariosFragment();
                break;
            }
        }
        return fragment;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
        this.fragmentHashMap.remove(position);
    }

    public Fragment getFragmentHashMap(Integer indice) {
        return fragmentHashMap.get(indice);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        Drawable drawable = ContextCompat.getDrawable(this.mContext,this.icones[position]);
        drawable.setBounds(0,0,tamanhoIcone ,tamanhoIcone);
        ImageSpan span = new ImageSpan(drawable);
        SpannableString spannableString = new SpannableString(" ");
        spannableString.setSpan(span,0,spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }



    @Override
    public int getCount() {
        return this.icones.length;
    }
}
