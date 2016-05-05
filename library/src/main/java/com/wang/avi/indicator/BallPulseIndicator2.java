package com.wang.avi.indicator;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ValueAnimator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Jack on 2015/10/16.
 */
public class BallPulseIndicator2 extends BaseIndicatorController {

    public static final float[] SCALES = {1.0f, 0.3f, 1.0f};


    private static final float[] INTERVAL = {0.0f, 0.7f, 1.4f};
    //set start SCALES;
    private float[] scaleFloats = new float[]{SCALES[0], SCALES[1], SCALES[2]};
    public static final long DURATION = 720L;

    public static final long DIFFER_START_TIME = 120L;
    public static final float DIFFER_SCALE = INTERVAL[2] / (float) (DURATION / DIFFER_START_TIME);

    private static final float DIFFER_AFTER_MIDDLE = INTERVAL[2] - SCALES[2];
    private static final float DIFFER_BEFORE_MIDDLE = SCALES[0] - INTERVAL[1];

    private static final float STEP1 = DIFFER_SCALE;
    private static final float STEP2 = DIFFER_SCALE * 2;
    private static final float STEP3 = DIFFER_SCALE * 3;

    @Override
    public void draw(Canvas canvas, Paint paint) {
        float circleSpacing = 4;
        float radius = (Math.min(getWidth(), getHeight()) - circleSpacing * 2) / 6;
        float x = getWidth() / 2 - (radius * 2 + circleSpacing);
        float y = getHeight() / 2;
        for (int i = 0; i < 3; i++) {
            int saveCount = canvas.save();
            float translateX = x + ((radius * 2)+ circleSpacing) * i;
            canvas.translate(translateX, y);
            canvas.scale(scaleFloats[i], scaleFloats[i]);
            canvas.drawCircle(0, 0, radius, paint);
            canvas.restoreToCount(saveCount);
        }
    }

    private static float checkBoardsInterval(float t){
        if(t < INTERVAL[0]){
            return INTERVAL[0] - t;
        }
        return t;
    }

    private static float convertToScale(float t){
        if(t > INTERVAL[1]){
            return t - DIFFER_AFTER_MIDDLE;
        }else{
            return  INTERVAL[1] - t + DIFFER_BEFORE_MIDDLE;
        }
    }

    @Override
    public List<Animator> createAnimation() {
        ValueAnimator animator = ValueAnimator.ofFloat(INTERVAL);

        animator.setDuration(DURATION);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float t = (float) animation.getAnimatedValue();
                scaleFloats[0] = checkBoardsInterval(t - STEP1);
                scaleFloats[1] = checkBoardsInterval(t - STEP2);
                scaleFloats[2] = checkBoardsInterval(t - STEP3);

                scaleFloats[0] = convertToScale(scaleFloats[0]);
                scaleFloats[1] = convertToScale(scaleFloats[1]);
                scaleFloats[2] = convertToScale(scaleFloats[2]);

                postInvalidate();

            }

        });
        animator.start();
        return Collections.singletonList((Animator) animator);
    }

}
