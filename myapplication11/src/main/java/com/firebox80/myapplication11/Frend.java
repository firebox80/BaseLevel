package com.firebox80.myapplication11;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by user on 19.10.2015.
 */
public class Frend implements Serializable{

    String name;
    ArrayList<String> listFrends;
    ArrayList<String> listNoFrends;
    int avatar;

    public Frend(String name, int avatar, ArrayList<Frend> listNoFrends) {
        this.name = name;
        this.listFrends = new ArrayList<String>();
        this.listNoFrends = new ArrayList<String>();
        this.avatar = avatar;

        for (int i=0;i<listNoFrends.size();i++ ) {
            this.listNoFrends.add(listNoFrends.get(i).name);
        }
    }

    public Frend(String name, ArrayList<String> listFrends, ArrayList<String> listNoFrends, int avatar) {
        this.name = name;
        this.listFrends = new ArrayList<String>(listFrends);
        this.listNoFrends = new ArrayList<String>(listNoFrends);
        this.avatar = avatar;
    }

    String getFrendsName (){
        StringBuilder str = new StringBuilder();

        for(String name: listFrends){
            str.append(name+", ");
        }
        if(str.length() != 0) {
            int lastIndex = str.lastIndexOf(",");
            str.deleteCharAt(lastIndex);
        } else {
            str.append("Нет данных");
        }
        return str.toString();
    }
}
