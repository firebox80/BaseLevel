package com.firebox80.myapplication11;

import java.util.ArrayList;

/**
 * Created by user on 19.10.2015.
 */
public class ListData {

    static ArrayList<Integer> listUnusedAvatar;

    static String[] arrName = { "Шуршалин Вениамин","Лихачев Богдан","Кораблёва Надежда",
            "Волынкина Ольга","Терегулов Мстислав","Яковалева Лариса","Багрова Изольда",
            "Афонин Иван","Белоглазов Виталий","Квартиров Кир"};

    static int[] arrFrendAvatar = { R.drawable.aang, R.drawable.azula, R.drawable.dolled,
            R.drawable.dragon, R.drawable.earth, R.drawable.earthbender,
            R.drawable.fire, R.drawable.firenation, R.drawable.firenationtoph,
            R.drawable.general, R.drawable.guru, R.drawable.haru, R.drawable.jeong,
            R.drawable.jet, R.drawable.june, R.drawable.katara, R.drawable.kingbumi,
            R.drawable.kingdom, R.drawable.mai, R.drawable.meng, R.drawable.redeemed,
            R.drawable.sokka, R.drawable.space, R.drawable.spirit, R.drawable.suki,
            R.drawable.toph, R.drawable.ty, R.drawable.uncle, R.drawable.ursa,
            R.drawable.warrior, R.drawable.yue, R.drawable.zhao, R.drawable.zuko };

    static ArrayList<Frend> initListFrends(){
        int quantityFriends, numberFrend;
        ArrayList<Frend> listFrend = new ArrayList<Frend>();
        ArrayList<String> listFrendsFrend = new ArrayList<String>();
        ArrayList<String> listNofrendsFrend = new ArrayList<String>();

        for(int i = 0; i < arrName.length; i++) {
            quantityFriends = (int)(Math.random() * 10);

            for(int j = 0; j < quantityFriends;) {
                numberFrend = (int)(Math.random()*10);
                if(!listFrendsFrend.contains(arrName[numberFrend])&& i != numberFrend){
                    listFrendsFrend.add(arrName[numberFrend]);
                    j++;
                }
            }
            for(int j = 0; j < arrName.length; j++) {
                if(!listFrendsFrend.contains(arrName[j])&& i != j){
                    listNofrendsFrend.add(arrName[j]);
                }
            }
            listFrend.add(new Frend(arrName[i], listFrendsFrend, listNofrendsFrend, arrFrendAvatar[i]));
            listFrendsFrend.clear();
            listNofrendsFrend.clear();
        }
        return listFrend;
    }

    static void delFrend (int position, ArrayList<Frend> valueListFrends){
        String nameDelFrend = valueListFrends.get(position).name;
        valueListFrends.remove(position);

        for(Frend value: valueListFrends){
            if(!(value.listFrends.remove(nameDelFrend))) {
                value.listNoFrends.remove(nameDelFrend);
            }
        }
    }
    static ArrayList<Integer> createUnusedAvatar(ArrayList<Frend> valueListFrends) {
        listUnusedAvatar =  new ArrayList<Integer>();

        for (int i = 0; i < arrFrendAvatar.length; i++) {
            listUnusedAvatar.add(Integer.valueOf(arrFrendAvatar[i]));
        }
        for(Frend valueFrend : valueListFrends){
            listUnusedAvatar.remove(Integer.valueOf(valueFrend.avatar));
        }
        return listUnusedAvatar;
    }
}