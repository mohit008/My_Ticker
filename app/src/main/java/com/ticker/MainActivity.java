package com.ticker;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements GetClick{

    RecyclerView recycler_view;
    Timer timer;
    Button start, stop;
    LinearLayoutManager manager;

    TickeAdapter adapter;
    ArrayList<String> arrayList = new ArrayList<>();

    int array_size = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = (Button) findViewById(R.id.start);
        stop = (Button) findViewById(R.id.stop);
        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);

        // just random
        for(int i = 0 ; i < 7 ; i ++) {
            arrayList.add(getNumber()+"");
        }

        manager = new LinearLayoutManager(MainActivity.this,
                LinearLayoutManager.HORIZONTAL, false);
        recycler_view.setLayoutManager(manager);

        recycler_view.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItemCount = manager.getChildCount();
                int totalItemCount = manager.getItemCount();
                int pastVisiblesItems = manager.findFirstVisibleItemPosition();
                Log.i("index", visibleItemCount + ":" + totalItemCount + ":"
                        + pastVisiblesItems + ":" + manager.findLastVisibleItemPosition());
                update(manager.findLastVisibleItemPosition());
            }
        });

        adapter = new TickeAdapter(this, arrayList, this);
        recycler_view.setAdapter(adapter);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start();
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer.cancel();
                timer.purge();
            }
        });
//        start();
    }

    /**
     * start timer to move it
     */
    public void start() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    public void run() {
                        recycler_view.scrollBy(10, 0);
                    }
                });
            }
        }, 0, 100);
    }

    /**
     * get object here
     * @param name
     * @param position
     */
    @Override
    public void clickResutl(String name, int position) {
        Toast.makeText(this, name + ":" + position, Toast.LENGTH_SHORT).show();
    }

    /**
     * add value to recycle value
     * @param position
     */
    public void update(int position) {
        if (position == arrayList.size() - 1) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    arrayList.add(arrayList.get(array_size));
                    array_size++;
                    if (array_size == 9) {
                        array_size = 0;
                    }
                    adapter.notifyDataSetChanged();
                }
            }, 50);
        }
    }

    public int getNumber(){
        return 1000 + (new Random().nextInt(10000-1000)+1);
    }

}
