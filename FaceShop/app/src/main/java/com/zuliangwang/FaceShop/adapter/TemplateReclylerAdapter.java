package com.zuliangwang.FaceShop.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zuliangwang.FaceShop.R;
import com.zuliangwang.FaceShop.listener.TemplateAdapterLisener;
//import com.zuliangwang.FaceShop.listener.TemplateAdapterLisener;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by zuliangwang on 15/11/29.
 */
public class TemplateReclylerAdapter extends RecyclerView.Adapter<TemplateReclylerAdapter.TemplateViewHolder> implements View.OnClickListener{

    private Context mContext;
    //datas是image的id
    private List<Integer> datas;
    private LayoutInflater layoutInflater;


    private TemplateAdapterLisener lisener;


    public TemplateReclylerAdapter(Context context,List<Integer> idList) {
        mContext = context;
        layoutInflater = LayoutInflater.from(context);
        datas = idList;
    }

    @Override
    public TemplateViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.template_item,parent,false);
        TemplateViewHolder holder= new TemplateViewHolder(view);

        holder.template = (ImageView) view.findViewById(R.id.template_item_image);
//        holder.template.setOnClickListener(this);

        return holder;
    }

    @Override
    public void onBindViewHolder(TemplateViewHolder holder, int position) {
//        holder.template.setImageResource(datas.get(position));
        Picasso.with(mContext).load(datas.get(position)).resize(100,100).into(holder.template);
        holder.template.setTag(position);

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public void onClick(View v) {
        lisener.onItemClick(v,(int)v.getTag());
    }


    public class TemplateViewHolder extends RecyclerView.ViewHolder{
        ImageView template;

        public TemplateViewHolder(View itemView) {
            super(itemView);
        }
    }




    public void setOnClickItemListener(TemplateAdapterLisener listener){
        this.lisener = listener;
    }

}
