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
        private static final int SWIPE_DISTANCE_THRESHOLD = 10;
        private static final int SWIPE_VEL_THRESHOLD = 50;
        @Override
        public void onLongPress(MotionEvent e) {
            Log.d("GESTUREDEMO", "Long Tap Confirmed...");
            onLongClick();
            super.onLongPress(e);
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float distX = e2.getX() - e1.getX(); //x offset for the fling
            float distY = e2.getY() - e1.getY(); //Y offset for the fling

            Log.d("SWIPE", "Entered on fling: distX, DistY, VelX, Vel Y is..."
                + distX + distY + velocityX + velocityY);

            if(Math.abs(distX) > Math.abs(distY)
                && Math.abs(distX) > SWIPE_DISTANCE_THRESHOLD
                && Math.abs(velocityX) > SWIPE_VEL_THRESHOLD){
                //this means a horizontal swipe has ocurred

                if(distX > 0){
                    onSwipeRight();
                }else{
                    onSwipeLeft();
                }
                return true;
            }else if(Math.abs(distY) > Math.abs(distX)
                        && Math.abs(distY) > SWIPE_DISTANCE_THRESHOLD
                        && Math.abs(velocityY) > SWIPE_VEL_THRESHOLD){
                //a vertical swipe occurred
                if(distY >0){
                    onSwipeDown();
                }else{
                    onSwipeUp();
                }
                return true; //swipe has occurred

            }
            return false;
            //return super.onFling(e1, e2, velocityX, velocityY);
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

    public void onSwipeUp() {
        Log.d("GESTUREDEMO+SWIPE", "Swiped Up method inside custom touch listener");
    }

    public void onSwipeDown() {
        Log.d("GESTUREDEMO+SWIPE", "Swiped Down method inside custom touch listener");
    }

    //must be public!!!
    public void onSwipeLeft() {
        Log.d("GESTUREDEMO+SWIPE", "Swiped Left method inside custom touch listener");
    }

    public void onSwipeRight() {
        Log.d("GESTUREDEMO+SWIPE", "Swiped Right method inside custom touch listener");
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
