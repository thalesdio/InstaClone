package com.parse.starter.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.ViewGroup;

import com.parse.starter.Fragments.HomeFragment;
import com.parse.starter.Fragments.UsuariosFragment;
import com.parse.starter.R;

import java.util.HashMap;

public class TabsAdapter extends FragmentStatePagerAdapter {

    private Context context;
    //private String[] abas = new String[]{"HOME", "USUARIOS"};
    private int[] icones = new int[]{R.drawable.ic_action_home, R.drawable.ic_people};
    private int tamanhoIcone;
    private HashMap<Integer, Fragment> fragmentosUtilizados = new HashMap<>();

    public TabsAdapter(FragmentManager fm, Context c) {
        super(fm);
        this.context = c;
        double escala = this.context.getResources().getDisplayMetrics().density;
        tamanhoIcone = (int) (36*escala);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        switch (position){
            case 0: fragment = new HomeFragment();
                fragmentosUtilizados.put(position, fragment);
                break;
            case 1: fragment = new UsuariosFragment();
                break;
        }
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
        fragmentosUtilizados.remove(position);
    }

    public Fragment getFragment(Integer indice){
        return fragmentosUtilizados.get(indice);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Drawable drawable = ContextCompat.getDrawable(this.context, icones[position]); //Icone de acordo com posição do switch
        drawable.setBounds(0, 0, tamanhoIcone, tamanhoIcone);
        ImageSpan imageSpan = new ImageSpan(drawable); //Colocar imagem dentro de um texto

        //Classe para retornar um CharSequence
        SpannableString spannableString = new SpannableString(" ");
        spannableString.setSpan(imageSpan, 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return spannableString;

    }

    @Override
    public int getCount() {
        return icones.length;
    }
}
