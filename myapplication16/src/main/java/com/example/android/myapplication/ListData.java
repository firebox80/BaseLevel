package com.example.android.myapplication;

import java.util.ArrayList;

/**
 * Created by Maxim on 12.02.2016.
 */
public class ListData {

    private static ArrayList<Integer> listUnusedAvatar;

    private static String[] arrFrend = { "Шуршалин Вениамин","Лихачев Богдан","Кораблёва Надежда",
            "Волынкина Ольга","Терегулов Мстислав","Яковалева Лариса","Багрова Изольда",
            "Афонин Иван","Белоглазов Виталий","Квартиров Кир"};

    private static String[] arrBirthday = { "30/09/1980","06/04/1982","04/08/1982","16/08/1984",
            "02/12/1985","17/04/1986","11/03/1987","23/07/1987","07/06/1988","29/03/1990"};

    private static int[] arrFrendAvatar = { R.drawable.aang, R.drawable.azula, R.drawable.dolled,
            R.drawable.dragon, R.drawable.earth, R.drawable.earthbender,
            R.drawable.fire, R.drawable.firenation, R.drawable.firenationtoph,
            R.drawable.general, R.drawable.guru, R.drawable.haru, R.drawable.jeong,
            R.drawable.jet, R.drawable.june, R.drawable.katara, R.drawable.kingbumi,
            R.drawable.kingdom, R.drawable.mai, R.drawable.meng, R.drawable.redeemed,
            R.drawable.sokka, R.drawable.space, R.drawable.spirit, R.drawable.suki,
            R.drawable.toph, R.drawable.ty, R.drawable.uncle, R.drawable.ursa,
            R.drawable.warrior, R.drawable.yue, R.drawable.zhao, R.drawable.zuko };

    static ArrayList<Frend> initListFrends(){
        ArrayList<Frend> listFrend = new ArrayList<Frend>();

        for(int i = 0; i < arrFrend.length; i++) {
            listFrend.add(new Frend(arrFrend[i], arrBirthday[i], arrFrendAvatar[i]));
        }
        return listFrend;
    }

    static ArrayList<Integer> initUnusedAvatar() {
        listUnusedAvatar =  new ArrayList<Integer>();

        for (int i = arrFrend.length; i < arrFrendAvatar.length; i++) {
            listUnusedAvatar.add(Integer.valueOf(arrFrendAvatar[i]));
        }
        return listUnusedAvatar;
    }

}
