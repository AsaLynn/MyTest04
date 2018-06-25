package com.zxn.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventViewActivity extends AppCompatActivity {

    @BindView(R.id.rv)
    MyRecyclerView rv;
    private String TAG = "EventViewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_view);
        ButterKnife.bind(this);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        DividerItemDecoration decor = new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);

        rv.addItemDecoration(decor);
        rv.setAdapter(new MyRvAdapter());
        rv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i(TAG, "onTouch: MyRecyclerView--->".concat("纵向滑动的view的onTouch触发了!!!"));
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.i(TAG, "onTouch: MyRecyclerView--->".concat("手指按下了"));
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.i(TAG, "onTouch: MyRecyclerView--->".concat("手指抬起了"));
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.i(TAG, "onTouch: MyRecyclerView--->".concat("手指移动了"));
                        break;
                }

                return false;
            }
        });

    }
}
