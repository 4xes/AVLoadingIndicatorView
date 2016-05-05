package com.wang.avi.indicator;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ValueAnimator;

import java.util.Collections;
import java.util.List;

/**
 * Created by Jack on 2015/10/19.
 * optimized by Alex Petrushin
 */
public class BallBeatIndicator extends BaseIndicatorController {


    public static final float[] SCALES = {1.0f, 0.75f, 1.0f};
    public static final int[] ALPHAS = {255, 55, 255};

    public static final int[] INTERVALS = {200, 0, 200};

    //INTERVALS[0] / (SCALES[0] - SCALES[1]);
    public static final float DIFFER_VALUE = 800.0f;

    public static final float INTERVAL_SCALE = SCALES[0] + SCALES[1];
    public static final int INTERVAL_ALPHA = ALPHAS[0] + ALPHAS[1];

    private float[] scaleFloats = new float[]{SCALES[0], SCALES[1], SCALES[2]};
    private int[] alphas = {ALPHAS[0], ALPHAS[1], ALPHAS[2]};

    @Override
    public void draw(Canvas canvas, Paint paint) {
        float circleSpacing = 4;
        float radius = (getWidth() - circleSpacing * 2) / 6;
        float x = getWidth() / 2 - (radius * 2 + circleSpacing);
        float y = getHeight() / 2;
        for (int i = 0; i < 3; i++) {
            int saveCount = canvas.save();
            float translateX = x + (radius * 2 + circleSpacing) * i;
            canvas.translate(translateX, y);
            canvas.scale(scaleFloats[i], scaleFloats[i]);
            paint.setAlpha(alphas[i]);
            canvas.drawCircle(0, 0, radius, paint);
            canvas.restoreToCount(saveCount);
        }
    }

    @Override
    public List<Animator> createAnimation() {
        ValueAnimator animator = ValueAnimator.ofInt(INTERVALS);
        animator.setDuration(700);
        animator.setRepeatCount(-1);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                //invert from [200:0] to [1.0f, 0.75f]
                scaleFloats[0] = (float)(value) / DIFFER_VALUE + SCALES[1];
                //invert value by interval
                scaleFloats[1] = INTERVAL_SCALE - scaleFloats[0];
                scaleFloats[2] = scaleFloats[0];

                //invert from [200:0] to [255:55]
                alphas[0] = value + ALPHAS[1];
                alphas[1] = INTERVAL_ALPHA - alphas[0];
                alphas[2] = alphas[0];
                postInvalidate();
            }
        });

        animator.start();


        return Collections.singletonList((Animator)animator);
    }

}
