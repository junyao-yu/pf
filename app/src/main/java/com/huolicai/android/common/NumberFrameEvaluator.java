package com.huolicai.android.common;


import android.improving.utils.views.yummytext.FrameEvaluator;

/**
 * Author UFreedom
 * Date : 2016 三月 02
 */
public class NumberFrameEvaluator implements FrameEvaluator {

    private int mStart;
    private int mEnd;
    private String[] middles;

    public NumberFrameEvaluator(int start, int end) {
        this.mStart = start;
        this.mEnd = end;
        middles = new String[2];
        
    }
    
    @Override
    public String getStartFrame(int input) {
        if (input == 0) {
            return "" + mStart;
        } else if (input == 1) {
            return "" + (mStart + 1);
        } else {
            return "" + (mStart + 2);
        }
    }

    @Override
    public String getMiddleFrame(float input) {


        if (input <= 0.3f) {
            return mEnd / 9 +"";
        } else if (input <= 0.5f) {
            return mEnd / 7 +"";
        } else if (input <= 0.7f) {
            return mEnd / 5 +"";
        }  else if (input <= 0.9f) {
            return mEnd / 3 +"";
        }   else {
            return mEnd / 2 +"";
        }
    }

    @Override
    public String getEndFrame(int input) {

        if (input == 0) {
            return "" + (mEnd - 2);
        } else if (input == 1) {
            return "" + (mEnd - 1);
        } else {
            return "" + mEnd;
        }
    }
}
