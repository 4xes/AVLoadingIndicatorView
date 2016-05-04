package com.wang.avi.indicator;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ValueAnimator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jack on 2015/10/19.
 * optimized by Alex Petrushin
 */
public class BallBeatIndicator extends BaseIndicatorController {


    public static final float[] SCALES = {1.0f, 0.75f, 1.0f};
    public static final int[] ALPHAS = {255, 55, 255};

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
            canvas.save();
            float translateX = x + (radius * 2) * i + circleSpacing * i;
            canvas.translate(translateX, y);
            canvas.scale(scaleFloats[i], scaleFloats[i]);
            paint.setAlpha(alphas[i]);
            canvas.drawCircle(0, 0, radius, paint);
            canvas.restore();
        }
    }

    @Override
    public List<Animator> createAnimation() {
        List<Animator> animators = new ArrayList<>();
        ValueAnimator scaleAnim = ValueAnimator.ofFloat(SCALES);
        scaleAnim.setDuration(700);
        scaleAnim.setRepeatCount(-1);
        scaleAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                scaleFloats[0] = (float) animation.getAnimatedValue();
                //invert value by interval
                scaleFloats[1] = INTERVAL_SCALE - scaleFloats[0];
                scaleFloats[2] = scaleFloats[0];
                postInvalidate();
            }
        });

        ValueAnimator alphaAnim = ValueAnimator.ofInt(ALPHAS);
        alphaAnim.setDuration(700);
        alphaAnim.setRepeatCount(-1);
        alphaAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                alphas[0] = (int) animation.getAnimatedValue();
                //invert value by interval
                alphas[1] = INTERVAL_ALPHA - alphas[0];
                alphas[2] = alphas[0];
                postInvalidate();
            }
        });

        scaleAnim.start();
        alphaAnim.start();

        animators.add(scaleAnim);
        animators.add(alphaAnim);
        return animators;
    }

}
