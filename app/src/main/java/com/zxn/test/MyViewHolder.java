package com.zxn.test;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;

import butterknife.BindView;
import butterknife.ButterKnife;

class MyViewHolder extends RecyclerView.ViewHolder {

    private String TAG = "MyViewHolder";
    @BindView(R.id.hs_root)
    HorizontalScrollView hsRoot;

    public MyViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        hsRoot.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                Log.i(TAG, "onTouch: HorizontalScrollView--->".concat("横向滑动的view的onTouch触发了!!!"));
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        Log.i(TAG, "onTouch: HorizontalScrollView--->".concat("手指按下了"));
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.i(TAG, "onTouch: HorizontalScrollView--->".concat("手指抬起了"));
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.i(TAG, "onTouch: HorizontalScrollView--->".concat("手指移动了"));
                        break;
                }

                return false;
            }
        });
    }
}
