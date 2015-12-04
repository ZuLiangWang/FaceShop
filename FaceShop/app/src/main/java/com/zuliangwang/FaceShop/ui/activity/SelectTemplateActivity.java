package com.zuliangwang.FaceShop.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mob.tools.MobUIShell;
import com.squareup.picasso.Picasso;
import com.zuliangwang.FaceShop.R;
import com.zuliangwang.FaceShop.adapter.TemplateReclylerAdapter;
import com.zuliangwang.FaceShop.listener.TemplateAdapterLisener;
import com.zuliangwang.FaceShop.widget.DragImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by zuliangwang on 15/11/28.
 */
public class SelectTemplateActivity extends BaseActivity {


    String photoPath;
    int openType;
    List<Integer> list = new ArrayList<>();

    @InjectView(R.id.template_recycler)
    RecyclerView templateRecycler;

    @InjectView(R.id.select_drag_template)
    DragImageView dragTemplate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_template_activity);
        ButterKnife.inject(this);

        Intent cameraIntent = getIntent();
        Bundle bundle= cameraIntent.getExtras();
        photoPath = bundle.getString("photoPath");
        openType = bundle.getInt("openType");

        initTemplatesIdList();
        initTemplateReycler();

    }



    private void initTemplatesIdList(){
        list.add(R.drawable.mo1);
        list.add(R.drawable.mo2);
        list.add(R.drawable.mo3);
        list.add(R.drawable.mo4);
        list.add(R.drawable.mo5);
        list.add(R.drawable.mo6);
        list.add(R.drawable.mo7);
    }

    private void initTemplateReycler(){
        TemplateReclylerAdapter adapter = new TemplateReclylerAdapter(this,list);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        templateRecycler.setLayoutManager(manager);
        adapter.setOnClickItemListener(new TemplateAdapterLisener() {
            @Override
            public void onItemClick(View view, int posiotion) {
                Picasso.with(SelectTemplateActivity.this).load(list.get(posiotion)).into(dragTemplate);
            }
        });
        templateRecycler.setAdapter(adapter);



    }







}
