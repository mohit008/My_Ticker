package com.ticker;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Random;

/**
 * Author @ Mohit Soni on 16-05-2018 03:41 PM.
 */

public class Main extends Activity{

    Button but,add;
    Ticker hor;

    ArrayList<String> arrayList = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        but = (Button) findViewById(R.id.but);
        add = (Button) findViewById(R.id.add);
        hor = (Ticker) findViewById(R.id.hor);

        // just random
        for(int i = 0 ; i < 7 ; i ++) {
            arrayList.add(getNumber()+"");
        }
        hor.setArrayList(arrayList);

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hor.start();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hor.add();
            }
        });
    }

    public int getNumber(){
        return 1000 + (new Random().nextInt(10000-1000)+1);
    }
}
