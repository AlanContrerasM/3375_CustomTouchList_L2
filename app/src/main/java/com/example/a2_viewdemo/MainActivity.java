package com.example.a2_viewdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView txtViewWelcomeMsg;
    ImageView imgViewSample;
    Button btnShowTextOrImgae;
    Button btnShowBoth;
    boolean bigger = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtViewWelcomeMsg = findViewById(R.id.txtViewWelcomeMsg);
        imgViewSample = findViewById(R.id.imgViewSample);

        Drawable img = ResourcesCompat.getDrawable(getResources(), R.drawable.border,getTheme());
        if(img != null){
            img.setBounds(0,0,img.getIntrinsicWidth(), img.getIntrinsicHeight());
        }

        txtViewWelcomeMsg.setCompoundDrawables(img, null, img, null);
        txtViewWelcomeMsg.setCompoundDrawablePadding(8);
        txtViewWelcomeMsg.setAlpha(0.8f);

        btnShowTextOrImgae = findViewById(R.id.btnShowText);
        btnShowBoth = findViewById(R.id.btnShowBoth);

        btnShowTextOrImgae.setOnClickListener(v->{
            if(btnShowTextOrImgae.getText().equals("Show Text")){
                txtViewWelcomeMsg.setVisibility(View.VISIBLE);
                imgViewSample.setVisibility(View.INVISIBLE);
                btnShowTextOrImgae.setText("Show Image");
            }else{
                txtViewWelcomeMsg.setVisibility(View.INVISIBLE);
                //can also be View.GONE, that one takes it out of the DOM
                imgViewSample.setVisibility(View.VISIBLE);
                btnShowTextOrImgae.setText("Show Text");
            }
        });

        btnShowBoth.setOnClickListener(v->{
            txtViewWelcomeMsg.setVisibility(View.VISIBLE);
            imgViewSample.setVisibility(View.VISIBLE);
        });

        txtViewWelcomeMsg.setOnTouchListener(new CustomTouchListener(this){
            @Override
            public void onSwipeUp() {
                super.onSwipeUp();
                //I need to change vertical gravity to TOP while zeeping horz gravity as is
                int horzGravity = txtViewWelcomeMsg.getGravity() & Gravity.HORIZONTAL_GRAVITY_MASK; //getting horz gravity
                txtViewWelcomeMsg.setGravity(Gravity.TOP | horzGravity);
            }

            @Override
            public void onSwipeDown() {
                super.onSwipeDown();
                //I need to change vertical gravity to Bottom while zeeping horz gravity as is
                int horzGravity = txtViewWelcomeMsg.getGravity() & Gravity.HORIZONTAL_GRAVITY_MASK; //getting horz gravity
                txtViewWelcomeMsg.setGravity(Gravity.BOTTOM | horzGravity);
            }

            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                //I need to change horizontal gravity to LEFT while zeeping horz gravity as is
                int vertGravity = txtViewWelcomeMsg.getGravity() & Gravity.VERTICAL_GRAVITY_MASK; //getting horz gravity
                txtViewWelcomeMsg.setGravity(Gravity.LEFT | vertGravity);
            }

            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                //I need to change horizontal gravity to Right while zeeping horz gravity as is
                int vertGravity = txtViewWelcomeMsg.getGravity() & Gravity.VERTICAL_GRAVITY_MASK; //getting horz gravity
                txtViewWelcomeMsg.setGravity(Gravity.RIGHT | vertGravity);
            }

            @Override
            public void onLongClick() {

                super.onLongClick();
                Log.d("GESTUREDEMO", "Detected loooong click on the TextView");
                Toast.makeText(context, "Hi people", Toast.LENGTH_LONG).show();
                if (txtViewWelcomeMsg.getPaint().isStrikeThruText()){
                    //bitwise anding the current paint flags with the complement of strike thru flag
                    //removes the strike thru
                    //while keeping all other flas as is.
                    txtViewWelcomeMsg.setPaintFlags(txtViewWelcomeMsg.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
                }else{
                    //bitwise or of current paint flafs with strike thru flags
                    //adds the strike thru while keeping all other flas as is.
                    txtViewWelcomeMsg.setPaintFlags(txtViewWelcomeMsg.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
            }

            @Override
            public void onDoubleClick() {

                super.onDoubleClick();
                Log.d("GESTUREDEMO", "Detected double click on the TextView");
                if(!bigger){
                    txtViewWelcomeMsg.setTextSize(txtViewWelcomeMsg.getTextSize()/getResources().getDisplayMetrics().density + 10); //we basically get dp and then add 10
                    bigger = true;
                }else{
                    txtViewWelcomeMsg.setTextSize(txtViewWelcomeMsg.getTextSize()/getResources().getDisplayMetrics().density - 10);
                    bigger = false;
                }
            }

            @Override
            public void onSingleClick() {
                super.onSingleClick();
                Log.d("GESTUREDEMO", "Detected single click on TextView");
                if(txtViewWelcomeMsg.getCurrentTextColor() !=
                        ResourcesCompat.getColor(getResources(), R.color.teal_200, getTheme())){
                    txtViewWelcomeMsg.setTextColor(ResourcesCompat.getColor(getResources(), R.color.teal_200, getTheme()));
                }else{
                    txtViewWelcomeMsg.setTextColor(Color.rgb(255,255,255));
                }
            }

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.d("GESTUREDEMO", "Detected touch on textView");
                return super.onTouch(view, motionEvent);
            }
        });

        imgViewSample.setOnTouchListener(new CustomTouchListener(this){
            //if this - the current object does not refer to the activity object
            //you must scope this with DataType - MainActivity.this - means the current object that is
            //on instance of MainActivity class


            @Override
            public void onDoubleClick() {
                super.onDoubleClick();
                if(imgViewSample.getScaleType() != ImageView.ScaleType.FIT_XY){
                    imgViewSample.setScaleType(ImageView.ScaleType.FIT_XY);
                }else{
                    imgViewSample.setScaleType(ImageView.ScaleType.FIT_CENTER);
                }
            }

            @Override
            public void onSingleClick() {
                super.onSingleClick();
                //to compare images you have to use ConstantState
                if(imgViewSample.getDrawable().getConstantState()
                        != ResourcesCompat.getDrawable(getResources(), R.drawable.bird, getTheme()).getConstantState()){
                    imgViewSample.setImageResource(R.drawable.bird);
                }else{
                    imgViewSample.setImageResource(R.drawable.fire);
                }
            }

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.d("GESTUREDEMO", "Detected on Touch on ImageView");
                return super.onTouch(view, motionEvent);
            }
        });
    }


}