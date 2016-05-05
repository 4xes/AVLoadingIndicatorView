package com.wang.avi;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.IntDef;
import android.util.AttributeSet;
import android.view.View;

import com.wang.avi.indicator.BallBeatIndicator;
import com.wang.avi.indicator.BallClipRotateIndicator;
import com.wang.avi.indicator.BallClipRotateMultipleIndicator;
import com.wang.avi.indicator.BallClipRotatePulseIndicator;
import com.wang.avi.indicator.BallGridBeatIndicator;
import com.wang.avi.indicator.BallGridPulseIndicator;
import com.wang.avi.indicator.BallPulseBeatIndicator;
import com.wang.avi.indicator.BallPulseIndicator;
import com.wang.avi.indicator.BallPulseRiseIndicator;
import com.wang.avi.indicator.BallPulseSyncIndicator;
import com.wang.avi.indicator.BallRotateIndicator;
import com.wang.avi.indicator.BallScaleIndicator;
import com.wang.avi.indicator.BallScaleMultipleIndicator;
import com.wang.avi.indicator.BallScaleRippleIndicator;
import com.wang.avi.indicator.BallScaleRippleMultipleIndicator;
import com.wang.avi.indicator.BallSpinFadeLoaderIndicator;
import com.wang.avi.indicator.BallTrianglePathIndicator;
import com.wang.avi.indicator.BallZigZagDeflectIndicator;
import com.wang.avi.indicator.BallZigZagIndicator;
import com.wang.avi.indicator.BaseIndicatorController;
import com.wang.avi.indicator.CubeTransitionIndicator;
import com.wang.avi.indicator.LineScaleIndicator;
import com.wang.avi.indicator.LineScalePartyIndicator;
import com.wang.avi.indicator.LineScalePulseOutIndicator;
import com.wang.avi.indicator.LineScalePulseOutRapidIndicator;
import com.wang.avi.indicator.LineSpinFadeLoaderIndicator;
import com.wang.avi.indicator.PacmanIndicator;
import com.wang.avi.indicator.SemiCircleSpinIndicator;
import com.wang.avi.indicator.SquareSpinIndicator;
import com.wang.avi.indicator.TriangleSkewSpinIndicator;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * Created by Jack on 2015/10/15
 * <p/>
 * .BALL_PULSE,
 * .BALL_GRID_PULSE,
 * .BALL_CLIP_ROTATE,
 * .BALL_CLIP_ROTATE_PULSE,
 * .SQUARE_SPIN,
 * .BALL_CLIP_ROTATE_MULTIPLE,
 * .BALL_PULSE_RISE,
 * .BALL_ROTATE,
 * .CUBE_TRANSITION,
 * .BALL_ZIG_ZAG,
 * .BALL_ZIG_ZAG_DEFLECT,
 * .BALL_TRIANGLE_PATH,
 * .BALL_SCALE,
 * .LINE_SCALE,
 * .LINE_SCALE_PARTY,
 * .BALL_SCALE_MULTIPLE,
 * .BALL_PULSE_SYNC,
 * .BALL_BEAT,
 * .LINE_SCALE_PULSE_OUT,
 * .LINE_SCALE_PULSE_OUT_RAPID,
 * .BALL_SCALE_RIPPLE,
 * .BALL_SCALE_RIPPLE_MULTIPLE,
 * .BALL_SPIN_FADE_LOADER,
 * .LINE_SPIN_FADE_LOADER,
 * .TRIANGLE_SKEW_SPIN,
 * .PACMAN,
 * .BALL_GRID_BEAT,
 * .SEMI_CIRCLE_SPIN,
 * .BALL_PULSE_BEAT_INDICATOR
 */
public class AVLoadingIndicatorView extends View {

    @IntDef(flag = true,
            value = {
                    BALL_PULSE,
                    BALL_GRID_PULSE,
                    BALL_CLIP_ROTATE,
                    BALL_CLIP_ROTATE_PULSE,
                    SQUARE_SPIN,
                    BALL_CLIP_ROTATE_MULTIPLE,
                    BALL_PULSE_RISE,
                    BALL_ROTATE,
                    CUBE_TRANSITION,
                    BALL_ZIG_ZAG,
                    BALL_ZIG_ZAG_DEFLECT,
                    BALL_TRIANGLE_PATH,
                    BALL_SCALE,
                    LINE_SCALE,
                    LINE_SCALE_PARTY,
                    BALL_SCALE_MULTIPLE,
                    BALL_PULSE_SYNC,
                    BALL_BEAT,
                    LINE_SCALE_PULSE_OUT,
                    LINE_SCALE_PULSE_OUT_RAPID,
                    BALL_SCALE_RIPPLE,
                    BALL_SCALE_RIPPLE_MULTIPLE,
                    BALL_SPIN_FADE_LOADER,
                    LINE_SPIN_FADE_LOADER,
                    TRIANGLE_SKEW_SPIN,
                    PACMAN,
                    BALL_GRID_BEAT,
                    SEMI_CIRCLE_SPIN,
                    BALL_PULSE_BEAT_INDICATOR
            })
    @Retention(RetentionPolicy.SOURCE)
    public @interface Indicator {
    }

    public static final int BALL_PULSE = 0;
    public static final int BALL_GRID_PULSE = 1;
    public static final int BALL_CLIP_ROTATE = 2;
    public static final int BALL_CLIP_ROTATE_PULSE = 3;
    public static final int SQUARE_SPIN = 4;
    public static final int BALL_CLIP_ROTATE_MULTIPLE = 5;
    public static final int BALL_PULSE_RISE = 6;
    public static final int BALL_ROTATE = 7;
    public static final int CUBE_TRANSITION = 8;
    public static final int BALL_ZIG_ZAG = 9;
    public static final int BALL_ZIG_ZAG_DEFLECT = 10;
    public static final int BALL_TRIANGLE_PATH = 11;
    public static final int BALL_SCALE = 12;
    public static final int LINE_SCALE = 13;
    public static final int LINE_SCALE_PARTY = 14;
    public static final int BALL_SCALE_MULTIPLE = 15;
    public static final int BALL_PULSE_SYNC = 16;
    public static final int BALL_BEAT = 17;
    public static final int LINE_SCALE_PULSE_OUT = 18;
    public static final int LINE_SCALE_PULSE_OUT_RAPID = 19;
    public static final int BALL_SCALE_RIPPLE = 20;
    public static final int BALL_SCALE_RIPPLE_MULTIPLE = 21;
    public static final int BALL_SPIN_FADE_LOADER = 22;
    public static final int LINE_SPIN_FADE_LOADER = 23;
    public static final int TRIANGLE_SKEW_SPIN = 24;
    public static final int PACMAN = 25;
    public static final int BALL_GRID_BEAT = 26;
    public static final int SEMI_CIRCLE_SPIN = 27;
    public static final int BALL_PULSE_BEAT_INDICATOR = 28;

    /*
     * Array of indicator flags for mapping attribute "indicator" to correct
     * flag value.
     */
    private static final int[] INDICATOR_FLAGS = {
            BALL_PULSE,
            BALL_GRID_PULSE,
            BALL_CLIP_ROTATE,
            BALL_CLIP_ROTATE_PULSE,
            SQUARE_SPIN,
            BALL_CLIP_ROTATE_MULTIPLE,
            BALL_PULSE_RISE,
            BALL_ROTATE,
            CUBE_TRANSITION,
            BALL_ZIG_ZAG,
            BALL_ZIG_ZAG_DEFLECT,
            BALL_TRIANGLE_PATH,
            BALL_SCALE,
            LINE_SCALE,
            LINE_SCALE_PARTY,
            BALL_SCALE_MULTIPLE,
            BALL_PULSE_SYNC,
            BALL_BEAT,
            LINE_SCALE_PULSE_OUT,
            LINE_SCALE_PULSE_OUT_RAPID,
            BALL_SCALE_RIPPLE,
            BALL_SCALE_RIPPLE_MULTIPLE,
            BALL_SPIN_FADE_LOADER,
            LINE_SPIN_FADE_LOADER,
            TRIANGLE_SKEW_SPIN,
            PACMAN,
            BALL_GRID_BEAT,
            SEMI_CIRCLE_SPIN,
            BALL_PULSE_BEAT_INDICATOR
    };
    //Sizes (with defaults in DP)
    public static final int DEFAULT_SIZE = 45;

    //attrs
    @Indicator
    int mIndicatorId;
    int mIndicatorColor;

    Paint mPaint;

    BaseIndicatorController mIndicatorController;

    private boolean mHasAnimation;


    public AVLoadingIndicatorView(Context context) {
        super(context);
        init(null, 0);
    }

    public AVLoadingIndicatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public AVLoadingIndicatorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AVLoadingIndicatorView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyle) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.AVLoadingIndicatorView);
        mIndicatorId = INDICATOR_FLAGS[a.getInt(R.styleable.AVLoadingIndicatorView_indicator, BALL_PULSE)];
        mIndicatorColor = a.getColor(R.styleable.AVLoadingIndicatorView_indicator_color, Color.WHITE);
        a.recycle();
        mPaint = new Paint();
        mPaint.setColor(mIndicatorColor);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        applyIndicator();
    }

    private void applyIndicator() {
        switch (mIndicatorId) {
            case BALL_PULSE:
                mIndicatorController = new BallPulseIndicator();
                break;
            case BALL_GRID_PULSE:
                mIndicatorController = new BallGridPulseIndicator();
                break;
            case BALL_CLIP_ROTATE:
                mIndicatorController = new BallClipRotateIndicator();
                break;
            case BALL_CLIP_ROTATE_PULSE:
                mIndicatorController = new BallClipRotatePulseIndicator();
                break;
            case SQUARE_SPIN:
                mIndicatorController = new SquareSpinIndicator();
                break;
            case BALL_CLIP_ROTATE_MULTIPLE:
                mIndicatorController = new BallClipRotateMultipleIndicator();
                break;
            case BALL_PULSE_RISE:
                mIndicatorController = new BallPulseRiseIndicator();
                break;
            case BALL_ROTATE:
                mIndicatorController = new BallRotateIndicator();
                break;
            case CUBE_TRANSITION:
                mIndicatorController = new CubeTransitionIndicator();
                break;
            case BALL_ZIG_ZAG:
                mIndicatorController = new BallZigZagIndicator();
                break;
            case BALL_ZIG_ZAG_DEFLECT:
                mIndicatorController = new BallZigZagDeflectIndicator();
                break;
            case BALL_TRIANGLE_PATH:
                mIndicatorController = new BallTrianglePathIndicator();
                break;
            case BALL_SCALE:
                mIndicatorController = new BallScaleIndicator();
                break;
            case LINE_SCALE:
                mIndicatorController = new LineScaleIndicator();
                break;
            case LINE_SCALE_PARTY:
                mIndicatorController = new LineScalePartyIndicator();
                break;
            case BALL_SCALE_MULTIPLE:
                mIndicatorController = new BallScaleMultipleIndicator();
                break;
            case BALL_PULSE_SYNC:
                mIndicatorController = new BallPulseSyncIndicator();
                break;
            case BALL_BEAT:
                mIndicatorController = new BallBeatIndicator();
                break;
            case LINE_SCALE_PULSE_OUT:
                mIndicatorController = new LineScalePulseOutIndicator();
                break;
            case LINE_SCALE_PULSE_OUT_RAPID:
                mIndicatorController = new LineScalePulseOutRapidIndicator();
                break;
            case BALL_SCALE_RIPPLE:
                mIndicatorController = new BallScaleRippleIndicator();
                break;
            case BALL_SCALE_RIPPLE_MULTIPLE:
                mIndicatorController = new BallScaleRippleMultipleIndicator();
                break;
            case BALL_SPIN_FADE_LOADER:
                mIndicatorController = new BallSpinFadeLoaderIndicator();
                break;
            case LINE_SPIN_FADE_LOADER:
                mIndicatorController = new LineSpinFadeLoaderIndicator();
                break;
            case TRIANGLE_SKEW_SPIN:
                mIndicatorController = new TriangleSkewSpinIndicator();
                break;
            case PACMAN:
                mIndicatorController = new PacmanIndicator();
                break;
            case BALL_GRID_BEAT:
                mIndicatorController = new BallGridBeatIndicator();
                break;
            case SEMI_CIRCLE_SPIN:
                mIndicatorController = new SemiCircleSpinIndicator();
                break;
            case BALL_PULSE_BEAT_INDICATOR:
                mIndicatorController = new BallPulseBeatIndicator();
                break;
        }
        mIndicatorController.setTarget(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = measureDimension(dp2px(DEFAULT_SIZE), widthMeasureSpec);
        int height = measureDimension(dp2px(DEFAULT_SIZE), heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    private int measureDimension(int defaultSize, int measureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else if (specMode == MeasureSpec.AT_MOST) {
            result = Math.min(defaultSize, specSize);
        } else {
            result = defaultSize;
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawIndicator(canvas);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (!mHasAnimation) {
            mHasAnimation = true;
            applyAnimation();
        }
    }

    @Override
    public void setVisibility(int v) {
        if (getVisibility() != v) {
            super.setVisibility(v);
            if (v == GONE || v == INVISIBLE) {
                mIndicatorController.setAnimationStatus(BaseIndicatorController.AnimStatus.END);
            } else {
                mIndicatorController.setAnimationStatus(BaseIndicatorController.AnimStatus.START);
            }
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (mHasAnimation) {
            mIndicatorController.setAnimationStatus(BaseIndicatorController.AnimStatus.START);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mIndicatorController.setAnimationStatus(BaseIndicatorController.AnimStatus.CANCEL);
    }

    void drawIndicator(Canvas canvas) {
        mIndicatorController.draw(canvas, mPaint);
    }

    void applyAnimation() {
        mIndicatorController.initAnimation();
    }

    private int dp2px(int dpValue) {
        return (int) getContext().getResources().getDisplayMetrics().density * dpValue;
    }
}
