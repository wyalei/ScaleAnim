package com.richtech.scaleanim;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Rect;
import android.os.Bundle;
import android.print.PageRange;
import android.view.View;
import android.view.animation.Animation;

public class MainActivity extends AppCompatActivity {

    private View mA;
    private View mB;
    private int mAnimTime = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mA = findViewById(R.id.a);
        mB = findViewById(R.id.b);

        findViewById(R.id.a2b).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a2b();
            }
        });

        findViewById(R.id.b2a).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b2a();
            }
        });
    }

    private void a2b(){
        Rect soureRect = getViewPosRect(mA);
        Rect targetRect = getViewPosRect(mB);

        Animation animation = TranslateAnimHelper.tanslateScaleAnim(true, soureRect, targetRect);
        animation.setDuration(mAnimTime);
        mA.startAnimation(animation);
    }

    private void b2a(){
        Rect soureRect = getViewPosRect(mB);
        Rect targetRect = getViewPosRect(mA);

        Animation animation = TranslateAnimHelper.tanslateScaleAnim(false, soureRect, targetRect);
        animation.setDuration(mAnimTime);
        mA.startAnimation(animation);
    }

    public Rect getViewPosRect(View view) {
        int[] arr = new int[2];
        view.getLocationInWindow(arr);

        Rect rect = new Rect(arr[0], arr[1], arr[0] + view.getWidth(), arr[1] + view.getHeight());
        return rect;
    }

}
