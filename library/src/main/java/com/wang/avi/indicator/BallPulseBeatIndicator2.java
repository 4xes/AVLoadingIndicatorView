package com.wang.avi.indicator;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ValueAnimator;

import java.util.Collections;
import java.util.List;

/**
 * Created by Jack on 2015/10/16.
 */
public class BallPulseBeatIndicator2 extends BaseIndicatorController {

    public static final long DURATION = 720L;

    public static final float[] SCALES = {1.0f, 0.3f, 1.0f};
    private static final float[] INTERVAL = {0.0f, 0.7f, 1.4f};


    private float[] scaleFloats = SCALES.clone();

    public static final long DIFFER_START_TIME = 120L;
    public static final float DIFFER_SCALE = INTERVAL[2] / (float) (DURATION / DIFFER_START_TIME);

    private static final float DIFFER_AFTER_MIDDLE = INTERVAL[2] - SCALES[2];
    private static final float DIFFER_BEFORE_MIDDLE = SCALES[0] - INTERVAL[1];

    private static final float STEP1 = DIFFER_SCALE;
    private static final float STEP2 = DIFFER_SCALE * 2.f;
    private static final float STEP3 = DIFFER_SCALE * 3.f;

    private static final int[] ALPHAS = {255, 55, 255};
    private int[] alphas = ALPHAS.clone();
    public static final float DIFFER_SCALE_TO_ALPHA = (ALPHAS[0] - ALPHAS[1]) / (SCALES[0] - SCALES[1]);

    @Override
    public void draw(Canvas canvas, Paint paint) {
        float circleSpacing = 4.f;
        float radius = (Math.min(getWidth(), getHeight()) - circleSpacing * 2.f) / 6.f;
        float x = getWidth() / 2.f - (radius * 2.f + circleSpacing);
        float y = getHeight() / 2;
        for (int i = 0; i < 3; i++) {
            int saveCount = canvas.save();
            float translateX = x + ((radius * 2)+ circleSpacing) * (float) i;
            canvas.translate(translateX, y);
            canvas.scale(scaleFloats[i], scaleFloats[i]);
            paint.setAlpha(alphas[i]);
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

    //convert [1.0, 0.3] to [255, 55]
    // 0.7 0;
    private static int convertToAlpha(float t){
        int alpha =  (int)((t - SCALES[1]) * DIFFER_SCALE_TO_ALPHA) + ALPHAS[1];
        Log.d("ALPHA", "" + alpha);
        if(alpha > 255) alpha = 255;
        if(alpha < 0) alpha = 0;

        return alpha;
    }

    @Override
    public List<Animator> createAnimation() {
        ValueAnimator animator = ValueAnimator.ofFloat(INTERVAL);
        Log.d("BALLPulse", "" + DIFFER_SCALE_TO_ALPHA);
        animator.setDuration(DURATION);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float t = (float) animation.getAnimatedValue();
                float t1 = checkBoardsInterval(t - STEP1);
                float t2 = checkBoardsInterval(t - STEP2);
                float t3 = checkBoardsInterval(t - STEP3);

                scaleFloats[0] = convertToScale(t1);
                scaleFloats[1] = convertToScale(t2);
                scaleFloats[2] = convertToScale(t3);

                alphas[0] = convertToAlpha(scaleFloats[0]);
                alphas[1] = convertToAlpha(scaleFloats[1]);
                alphas[2] = convertToAlpha(scaleFloats[2]);

                postInvalidate();

            }

        });
        animator.start();
        return Collections.singletonList((Animator) animator);
    }

}
