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

import android.improving.utils.views.cardsview.FlippableStackView;
import android.improving.utils.views.cardsview.StackPageTransformer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huolicai.android.R;
import com.huolicai.android.activity.messagenotice.MessageActivity;
import com.huolicai.android.adapters.MNAdapter;
import com.huolicai.android.base.BaseFragment;
import com.huolicai.android.common.Constants;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by Bartosz Lipinski
 * 28.01.15
 */
public class NoticeParentFragment extends BaseFragment {

    @Bind(R.id.to_new_notice)
    TextView toNew;

    @Bind(R.id.flippable_stack_view_notice)
    FlippableStackView flippableStackView;

    private MNAdapter mnAdapter;

    public static NoticeParentFragment newInstance() {
        NoticeParentFragment fragment = new NoticeParentFragment();
        return fragment;
    }


    @Override
    protected String currPageName() {
        return null;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notice_parent, container, false);
        ButterKnife.bind(this, view);
        initData();
        initView();
        return view;
    }


    private void initData() {
    }

    private void initView() {
        mnAdapter = new MNAdapter(getActivity().getSupportFragmentManager() , MessageActivity.TYPE_NOTICE);
        flippableStackView.initStack(3, StackPageTransformer.Orientation.VERTICAL);
        flippableStackView.setAdapter(mnAdapter);
        flippableStackView.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(final int position) {
                if (position < Constants.MessageNotice.MESSAGE_NOTICE_CPOUNT - 1) {
                    toNew.setVisibility(View.VISIBLE);
                } else {
                    toNew.setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        toNew.setVisibility(View.INVISIBLE);
        toNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flippableStackView.setCurrentItem(Constants.MessageNotice.MESSAGE_NOTICE_CPOUNT - 1, true);
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }



}
