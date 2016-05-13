package com.firebox80.myapplication6;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {
    Integer[] arrAvatarId = { R.drawable.ava,R.drawable.avaa,R.drawable.avab,R.drawable.avac,
            R.drawable.avad,R.drawable.avae,R.drawable.avb,R.drawable.avc,R.drawable.avd,
            R.drawable.ave,R.drawable.avf,R.drawable.avg,R.drawable.avh,R.drawable.avi,
            R.drawable.avj,R.drawable.avk,R.drawable.avl,R.drawable.avm,R.drawable.avn,
            R.drawable.avo,R.drawable.avp,R.drawable.avq,R.drawable.avr,R.drawable.avs,
            R.drawable.avt,R.drawable.avu,R.drawable.avv,R.drawable.avx,R.drawable.avy,
            R.drawable.avz};
    Integer[] arrFlagId = { R.drawable.ad,R.drawable.ae,R.drawable.af,R.drawable.ag,
            R.drawable.ai,R.drawable.al,R.drawable.am,R.drawable.an,R.drawable.ao,
            R.drawable.aq,R.drawable.ar,R.drawable.as,R.drawable.at,R.drawable.au,
            R.drawable.aw,R.drawable.ax,R.drawable.az,R.drawable.ba,R.drawable.bb,
            R.drawable.bd,R.drawable.be,R.drawable.bf,R.drawable.bg,R.drawable.bh,
            R.drawable.bi,R.drawable.bj,R.drawable.bl,R.drawable.bm,R.drawable.bn,
            R.drawable.bo};
    CharSequence[] arrCountry = {"Андорра", "ОАЭ", "Афганистан","Антигуа и Барбуда","Ангилья",
            "Албания","Армения","Нидерландские Антильские острова","Ангола","Антарктида","Аргентина",
            "Американское Самоа","Австрия","Австралия","Аруба","Аландские острова","Азербайджан",
            "Босния и Герцеговина","Барбадос","Бангладеш","Бельгия","Буркина-Фасо","Болгария",
            "Бахрейн","Бурунди","Бенин","Сен-Бартельми","Бермуды","Бруней","Боливия"};
    CharSequence[] arrName = {"Строганова Варвара","Ковтунова Анна","Родзянко Вячеслав",
            "Умаметев Евгений","Янкин Леонид","Куклачёва Жанна","Перехваткина Ольга",
            "Шаломенцева Алина","Кулагин Харитон","Якименко Якуб","Шишмарёва Анфиса",
            "Левковича Изольда","Краевич Евгений","Анреп Алексей","Морякова Татьяна",
            "Сюсин Герман","Тянникова Юлия","Катериночкина Ольга","Гуськова Полина",
            "Смешной Глеб","Ивазова Антонина","Муравьева Дарья","Коротаева Вероника",
            "Задкова Диана","Нарыков Юрий","Рыкова Наталья","Хохорина Варвара","Судленков Кирилл",
            "Черников Мирон","Кочубей Леонид"
    };
    float valueRating;
    int sequenceNumber;
    int quantityPersons;
    ScrollView svRoot;
    LinearLayout llLayout;
    CustomUserInfoView cuif;
    ArrayList<CustomUserInfoView> listUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        svRoot = new ScrollView(getApplicationContext());
        llLayout = new LinearLayout(getApplicationContext());
        llLayout.setOrientation(LinearLayout.VERTICAL);
        listUser = new ArrayList<CustomUserInfoView>();
        update();

        svRoot.addView(llLayout);
        setContentView(svRoot);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    public void update (){

        quantityPersons = (int)(Math.random() * 30)+ 1;

        listUser.clear();
        for(int i=0; i<quantityPersons; i++){
            cuif = new CustomUserInfoView(getApplicationContext(), null);
            cuif.tvName.setText(arrName[i]);
            cuif.tvName.setTextColor(Color.DKGRAY);
            cuif.ivFlag.setImageResource(arrFlagId[i]);
            cuif.tvCountry.setText(arrCountry[i]);
            cuif.tvCountry.setTextColor(Color.DKGRAY);
            valueRating = (float) ( Math.random() * 5);
            cuif.rbRating.setRating(valueRating);
            cuif.ivFlag.setImageResource(arrFlagId[i]);
            cuif.ivPhoto.setImageResource(arrAvatarId[i]);
            cuif.setId(i);
            listUser.add(cuif);
            cuif.setOnClickListener(this);
            cuif.setOnLongClickListener(this);
            llLayout.addView(cuif);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id){
        //noinspection SimplifiableIfStatement
            case R.id.action_settings:
                llLayout.removeAllViews();
                update();
            break;
            case R.id.action_settings2:
                updateSurname();
                displayInfo();
            break;
            case R.id.action_settings3:
                updateRating();
                displayInfo();
            break;
            case R.id.action_settings4:
                for(CustomUserInfoView element : listUser){
                    element.setAlpha(1);
                }
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void displayInfo() {
        llLayout.removeAllViews();

        for(CustomUserInfoView element : listUser){
            llLayout.addView(element);
        }
    }

    public void updateRating() {

        for(int i = quantityPersons; i>0; i--){
            for(int j = 0, k = 1; j<i-1; j++, k++) {
                if (listUser.get(j).rbRating.getRating() > listUser.get(k).rbRating.getRating()) {
                    CustomUserInfoView temp = listUser.get(j);
                    listUser.set(j, listUser.get(k));
                    listUser.set(k, temp);
                }
            }
        }
    }

    public void updateSurname() {
        for(int i=0; i<quantityPersons; i++){
            for(int j=i+1; j<quantityPersons; j++){
                String fullName1 = listUser.get(i).tvName.getText().toString();
                String fullName2 = listUser.get(j).tvName.getText().toString();
                String Surname1 = fullName1.substring(0, fullName1.indexOf(32, 0));
                String Surname2 = fullName2.substring(0, fullName2.indexOf(32, 0));
                Surname1.toUpperCase();
                Surname2.toUpperCase();

                if(Surname1.compareTo(Surname2)>0){
                    CustomUserInfoView temp = listUser.get(i);
                    listUser.set(i, listUser.get(j));
                    listUser.set(j, temp);
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        CustomUserInfoView temp = (CustomUserInfoView)findViewById(v.getId());
        for(CustomUserInfoView element : listUser){
            element.setAlpha(0.5f);
        }
        temp.setAlpha(1);
        StringBuilder info = new StringBuilder(temp.tvName.getText() + "\n" + temp.tvCountry.getText());
        Toast.makeText(this, info, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onLongClick(View v) {

        for(CustomUserInfoView temp : listUser){
            if(temp.getId()== v.getId()) {
                quantityPersons--;
                llLayout.removeView(temp);
                listUser.remove(temp);
                break;
            }
        }
        return true;
    }
}
