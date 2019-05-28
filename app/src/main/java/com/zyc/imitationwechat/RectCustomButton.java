package com.zyc.imitationwechat;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class RectCustomButton extends AppCompatButton {

    private int mCurrentState  = NORMAL;
    public static final int NORMAL = 1;
    public static final int SPEAKING = 2;
    public static final int WANT_TO_CANCEL = 3;

    private Context mContext;

    private FloatDialog mDialog;

    public RectCustomButton(Context context) {
        this(context , null);
    }

    public RectCustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        setBackgroundResource(R.drawable.button_shape_rect);
        mDialog = new FloatDialog(context, R.style.ThemeFloatDialog);
        changeButtonState (NORMAL);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                setBackgroundResource(R.drawable.button_shape_rect_press);
                changeButtonState(SPEAKING);
                break;
            case MotionEvent.ACTION_MOVE:
                if (isWantToCancel(event.getRawX(), event.getRawY())) {
                    changeButtonState(WANT_TO_CANCEL);
                } else {
                    changeButtonState(SPEAKING);
                }
                break;
            case MotionEvent.ACTION_UP:
                setBackgroundResource(R.drawable.button_shape_rect);
                changeButtonState(NORMAL);
                break;
        }
        return true;
    }

    /**
     * change button state
     * @param currentState change to state
     */
    private void changeButtonState(int currentState) {
        mCurrentState = currentState;
        switch (currentState) {
            case NORMAL:
                setText(mContext.getString(R.string.press_speak));
                mDialog.dismissDialog();
                break;
            case SPEAKING:
                setText(mContext.getString(R.string.speaking));
                mDialog.showSpeakingDialog();
                break;
            case WANT_TO_CANCEL:
                setText(mContext.getString(R.string.want_to_cancel));
                mDialog.showWantToCancel();
                break;
        }
    }

    /**
     * @param x point x
     * @param y  current point y
     * @return  this is condition judge
     */
    private boolean isWantToCancel(float x, float y) {
        System.out.println("x : " + x + "   getX: " + getX() + "  getWidth: " + getWidth()  + "  getMeasuredWidth : " + getMeasuredWidth());
        if (x < getX() || x > getX() + getMeasuredWidth()) {
            return true;
        }
        System.out.println("y : " + y + "   getY : " + getY());
        if (y < getY()) {
            System.out.println("isWantToCancel : " + y + "   getY : " + getY());
            return true;
        }
        return false;
    }
}
