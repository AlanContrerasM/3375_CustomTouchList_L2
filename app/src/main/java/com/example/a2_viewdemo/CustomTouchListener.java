package com.example.a2_viewdemo;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.core.view.GestureDetectorCompat;

public class CustomTouchListener implements View.OnTouchListener{
    Context context;
    GestureDetectorCompat gestureDetectorCompat;

    public CustomTouchListener(Context context){
        this.context = context;
        //need to create new GestureDetectorCompat object
        //needs two things: context, CustomOnGestureListener
        gestureDetectorCompat = new GestureDetectorCompat(context, new CustomOnGestureListener());
    }

    public class CustomOnGestureListener extends GestureDetector.SimpleOnGestureListener{
        @Override
        public void onLongPress(MotionEvent e) {
            Log.d("GESTUREDEMO", "Long Tap Confirmed...");
            onLongClick();
            super.onLongPress(e);
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return super.onFling(e1, e2, velocityX, velocityY);
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return true;//so that other gestures may be detected
            //return super.onDown(e);

        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Log.d("GESTUREDEMO", "Double Tap Confirmed...");
            onDoubleClick();
            return super.onDoubleTap(e);
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            Log.d("GESTUREDEMO", "Single Tap Confirmed...");
            onSingleClick();
            return super.onSingleTapConfirmed(e);
        }
    }

    public void onLongClick(){
        Log.d("GESTUREDEMO", "Long click method inside custom touch listener");
    }

    public void onDoubleClick() {
        Log.d("GESTUREDEMO", "Double click method inside custom touch listener");
    }

    public void onSingleClick(){
        Log.d("GESTUREDEMO", "Single click method inside custom touch listener");
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        //return false;
        return gestureDetectorCompat.onTouchEvent(motionEvent);
    }
}
