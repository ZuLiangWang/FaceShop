package com.zuliangwang.FaceShop.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zuliangwang.FaceShop.R;

import java.util.List;

/**
 * Created by zuliangwang on 15/11/29.
 */
public class TemplateReclylerAdapter extends RecyclerView.Adapter<TemplateReclylerAdapter.TemplateViewHolder> {

    private Context mContext;
    //datas是image的id
    private List<Integer> datas;
    private LayoutInflater layoutInflater;

    public TemplateReclylerAdapter(Context context) {
        mContext = context;
        layoutInflater = LayoutInflater.from(context);

    }

    @Override
    public TemplateViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.template_item,parent,false);
        TemplateViewHolder holder= new TemplateViewHolder(view);

        holder.template = (ImageView) view.findViewById(R.id.template_item_image);
        return holder;
    }

    @Override
    public void onBindViewHolder(TemplateViewHolder holder, int position) {
        holder.template.setImageResource(datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class TemplateViewHolder extends RecyclerView.ViewHolder{
        ImageView template;

        public TemplateViewHolder(View itemView) {
            super(itemView);
        }
    }
}
