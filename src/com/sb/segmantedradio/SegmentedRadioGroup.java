package com.sb.segmantedradio;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SegmentedRadioGroup extends RadioGroup {
    private Context context;

    public SegmentedRadioGroup(Context context) {
        super(context);
        this.context = context;
    }

    public SegmentedRadioGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public void onCheckedChangeListener(RadioGroup group, int checkedId) {
        int count = group.getChildCount();
        for (int i = 0; i < count; i++) {
            View view = group.getChildAt(i);
            if (view instanceof RadioButton) {
                ((RadioButton) view).setTextAppearance(context, R.style.segmented_radio_normal);
            }
        }
        RadioButton radioButton = (RadioButton) group.findViewById(checkedId);
        radioButton.setTextAppearance(context, R.style.segmented_radio_bold);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        changeButtonsImages();
        this.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                onCheckedChangeListener(group, checkedId);
            }
        });
    }

    private void changeButtonsImages() {
        int count = super.getChildCount();

        if (count > 1) {
            if (super.getChildAt(0) instanceof RadioButton) {
                super.getChildAt(0).setBackgroundResource(R.drawable.segmented_radio_left_bg_selector);
            }
            for (int i = 1; i < count - 1; i++) {
                if (super.getChildAt(i) instanceof RadioButton) {
                    super.getChildAt(i).setBackgroundResource(R.drawable.segmented_radio_middle_bg_selector);
                }
            }
            if (super.getChildAt(count - 1) instanceof RadioButton) {
                super.getChildAt(count - 1).setBackgroundResource(R.drawable.segmented_radio_right_bg_selector);
            }
        }
        else if (count == 1) {
            if (super.getChildAt(0) instanceof RadioButton) {
                super.getChildAt(0).setBackgroundResource(R.drawable.segmented_radio_bg_selector);
            }
        }
    }
}