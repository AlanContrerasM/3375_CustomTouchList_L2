package com.example.a2_viewdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView txtViewWelcomeMsg;
    ImageView imgViewSample;
    Button btnShowTextOrImgae;
    Button btnShowBoth;
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
            public void onLongClick() {
                super.onLongClick();
            }

            @Override
            public void onDoubleClick() {
                super.onDoubleClick();
            }

            @Override
            public void onSingleClick() {
                super.onSingleClick();
            }

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.d("GESTUREDEMO", "Detected touch on textView");
                return super.onTouch(view, motionEvent);
            }
        });
    }


}