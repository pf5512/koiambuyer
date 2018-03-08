package com.hc360.koiambuyer.adapter;

import android.graphics.Bitmap;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.myinterface.iview.ISuggestionView;

import java.util.List;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/9/21
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class LLAdapter extends BaseQuickAdapter<Bitmap> {
    ISuggestionView mView;
    public LLAdapter(int layoutResId, List data, ISuggestionView view) {
        super(layoutResId, data);
        this.mView = view;
    }

    @Override
    protected void convert(final BaseViewHolder holder, Bitmap bitmap) {
        holder.setImageBitmap(R.id.iv_rv_item,bitmap);
        final int position = holder.getAdapterPosition();
        holder.itemView.findViewById(R.id.iv_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Bitmap> data = getData();
                data.remove(position);
                notifyDataSetChanged();
                mView.deleteImg(position);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mView.showPics(position);
            }
        });
    }
}
