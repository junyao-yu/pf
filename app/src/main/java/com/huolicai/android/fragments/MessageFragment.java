/**
 * Copyright 2015 Bartosz Lipinski
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.huolicai.android.fragments;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.huolicai.android.R;
import com.huolicai.android.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by Bartosz Lipinski
 * 28.01.15
 */
public class MessageFragment extends BaseFragment {

    @Bind(R.id.main_layout)
    LinearLayout mainLayout;

    public static MessageFragment newInstance(int backgroundColor) {
        MessageFragment fragment = new MessageFragment();
//        Bundle bdl = new Bundle();
//        bdl.putInt(EXTRA_COLOR, backgroundColor);
//        fragment.setArguments(bdl);
        return fragment;
    }

    @Override
    protected String currPageName() {
        return null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }


    private void initView() {
        LayerDrawable bgDrawable = (LayerDrawable) mainLayout.getBackground();
        GradientDrawable shape = (GradientDrawable) bgDrawable.findDrawableByLayerId(R.id.background_shape);
        shape.setColor(Color.WHITE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
