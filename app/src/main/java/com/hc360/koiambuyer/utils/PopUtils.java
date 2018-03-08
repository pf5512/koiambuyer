package com.hc360.koiambuyer.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.adapter.ViewPagerAdapter;
import com.hc360.koiambuyer.myinterface.PicPopListener;
import com.hc360.koiambuyer.myinterface.PopListener;
import com.hc360.koiambuyer.view.MyApp;
import com.hc360.koiambuyer.widget.photoview.EasePhotoView;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;


/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/11/2
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class PopUtils {
    public static void showPopAsDown(Context context) {
        View contentView = LayoutInflater.from(context).inflate(R.layout.pop_choice_seller, null);
        final PopupWindow mPopWindow = new PopupWindow(contentView,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        mPopWindow.setContentView(contentView);
        mPopWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopWindow.setOutsideTouchable(true);


        //显示PopupWindow
//        mPopWindow.setAnimationStyle(R.style.contextMenuAnim);
        View rootview = LayoutInflater.from(context).inflate(R.layout.fragment_choice_seller, null);
        TextView viewById = (TextView) rootview.findViewById(R.id.tv_address);
        ToastUtil.showShort(MyApp.getAppContext(), viewById.getMeasuredHeight());
        mPopWindow.showAsDropDown(viewById, 0, viewById.getMeasuredHeight());
    }


    public static ViewPager showMultiPics(final Context context, View view, @Nullable String title, final List<String> picUrls, final int currPosition, final PopListener listener) {

        View contentView = LayoutInflater.from(context).inflate(R.layout.pop_multi_pics, null);
        ImageView ivBack = (ImageView) contentView.findViewById(R.id.iv_back);
        TextView tvTitle = (TextView) contentView.findViewById(R.id.tv_title);
        final ViewPager viewPager = (ViewPager) contentView.findViewById(R.id.view_pager);
        final TextView tvLeft = (TextView) contentView.findViewById(R.id.tv_left);
        TextView tvRight = (TextView) contentView.findViewById(R.id.tv_right);

        if (TextUtils.isEmpty(title)) {
            tvTitle.setText(context.getResources().getString(R.string.pic_picker));
        } else {
            tvTitle.setText(title);
        }
        final List<View> mPageView = new ArrayList<>();
        for (String picUrl : picUrls) {
            mPageView.add(new View(context));
        }
        final ViewPagerAdapter adapter = new ViewPagerAdapter(mPageView);
        viewPager.setAdapter(adapter);
        for (int i = 0; i < picUrls.size(); i++) {
            final int finalI = i;
            Glide.with(context).load(picUrls.get(i)).asBitmap().into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    View item = LayoutInflater.from(context).inflate(
                            R.layout.pop_viewpager_item, null);
                    PhotoView pv = (PhotoView) item.findViewById(R.id.pv);
                    pv.setImageBitmap(resource);
                    mPageView.set(finalI, item);
                    if (mPageView.size() == picUrls.size()) {
                        adapter.setData(mPageView);
                        if (mPageView.size() > currPosition) {
                            viewPager.setCurrentItem(currPosition);
                        }
                    }
                }
            });

        }
        final PopupWindow mPopWindow = new PopupWindow(contentView,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        mPopWindow.setContentView(contentView);
        mPopWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopWindow.setOutsideTouchable(true);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopWindow.dismiss();
            }
        });
        tvLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPageView.size() > 0) {
                    int currentItem = viewPager.getCurrentItem();
                    mPageView.remove(currentItem);
                    adapter.setData(mPageView);
                    if (mPageView.size() == 0) {
                        mPopWindow.dismiss();
                    }
                    listener.deletePic(currentItem);
                }
            }
        });
        tvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.updatePic(viewPager.getCurrentItem());
            }
        });

        mPopWindow.showAsDropDown(view);
        return viewPager;
    }


    public static void showSecret(final Context context, View view, boolean secret) {
        View contentView = LayoutInflater.from(context).inflate(secret?R.layout.pop_secret:R.layout.pop_account, null);
        ImageView ivBack = (ImageView) contentView.findViewById(R.id.iv_back);
        final PopupWindow mPopWindow = new PopupWindow(contentView,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopWindow.dismiss();
            }
        });
        mPopWindow.setContentView(contentView);
        mPopWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopWindow.setOutsideTouchable(false);
//        mPopWindow.showAsDropDown(view);
        mPopWindow.showAtLocation(view, Gravity.NO_GRAVITY,0,0);
    }

    public static ViewPager showMultiNoTitlePics(final Context context, View view, final List<String> picUrls, final int currPosition, final PicPopListener listener) {
        View contentView = LayoutInflater.from(context).inflate(R.layout.pop_multi_no_title_pics, null);
        final ViewPager viewPager = (ViewPager) contentView.findViewById(R.id.view_pager);
        final TextView tvLeft = (TextView) contentView.findViewById(R.id.tv_delete);
        TextView tvRight = (TextView) contentView.findViewById(R.id.tv_change);
        final List<View> mPageView = new ArrayList<>();
        for (String picUrl : picUrls) {
            mPageView.add(new View(context));
        }
        final PopupWindow mPopWindow = new PopupWindow(contentView,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);

        final ViewPagerAdapter adapter = new ViewPagerAdapter(mPageView);
        viewPager.setAdapter(adapter);
        for (int i = 0; i < picUrls.size(); i++) {
            final int finalI = i;
            Glide.with(context).load(picUrls.get(i)).asBitmap().placeholder(R.mipmap.good_default).error(R.mipmap.good_default).into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    View item = LayoutInflater.from(context).inflate(
                            R.layout.pop_viewpager_item, null);
                    PhotoView pv = (PhotoView) item.findViewById(R.id.pv);
                    pv.setImageBitmap(resource);
                    pv.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
                        @Override
                        public void onPhotoTap(View view, float v, float v1) {
                            mPopWindow.dismiss();
                        }
                        @Override
                        public void onOutsidePhotoTap() {
                            mPopWindow.dismiss();
                        }
                    });
                    mPageView.set(finalI, item);
                    if (mPageView.size() == picUrls.size()) {
                        adapter.setData(mPageView);
                        if (mPageView.size() > currPosition) {
                            viewPager.setCurrentItem(currPosition);
                        }
                    }
                }
            });

        }
        tvLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPageView.size() > 0) {
                    int currentItem = viewPager.getCurrentItem();
                    mPageView.remove(currentItem);
                    adapter.setData(mPageView);
                    if (mPageView.size() == 0) {
                        mPopWindow.dismiss();
                    }
                    listener.deletePic(currentItem);
                }
            }
        });
        tvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.updatePic(viewPager.getCurrentItem());
            }
        });
        mPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                listener.onPopDismiss();
            }
        });
        mPopWindow.setClippingEnabled(false);
        mPopWindow.setContentView(contentView);
        mPopWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopWindow.setOutsideTouchable(true);
        mPopWindow.showAtLocation(view, Gravity.NO_GRAVITY,0,0);
        return viewPager;
    }

    public static ViewPager showMultiNoTitlePicsNew(final Context context, View view, final List<String> picUrls, final int currPosition, final PicPopListener listener) {
        View contentView = LayoutInflater.from(context).inflate(R.layout.pop_multi_no_title_pics, null);
        final ViewPager viewPager = (ViewPager) contentView.findViewById(R.id.view_pager);
        final TextView tvLeft = (TextView) contentView.findViewById(R.id.tv_delete);
        TextView tvRight = (TextView) contentView.findViewById(R.id.tv_change);
        final List<View> mPageView = new ArrayList<>();
        for (String picUrl : picUrls) {
            mPageView.add(new View(context));
        }
        final PopupWindow mPopWindow = new PopupWindow(contentView,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);

        final ViewPagerAdapter adapter = new ViewPagerAdapter(mPageView);
        viewPager.setAdapter(adapter);
        for (int i = 0; i < picUrls.size(); i++) {
            final int finalI = i;
            Glide.with(context).load(picUrls.get(i)).asBitmap().placeholder(R.mipmap.good_default).error(R.mipmap.good_default).into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    View item = LayoutInflater.from(context).inflate(
                            R.layout.pop_viewpager_item, null);
                    PhotoView pv = (PhotoView) item.findViewById(R.id.pv);
                    pv.setImageBitmap(resource);
                    pv.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
                        @Override
                        public void onPhotoTap(View view, float v, float v1) {
                            mPopWindow.dismiss();
                        }
                        @Override
                        public void onOutsidePhotoTap() {
                            mPopWindow.dismiss();
                        }
                    });
                    mPageView.set(finalI, item);
                    if (mPageView.size() == picUrls.size()) {
                        adapter.setData(mPageView);
                        if (mPageView.size() > currPosition) {
                            viewPager.setCurrentItem(currPosition);
                        }
                    }
                }
            });
        }
        tvLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPageView.size() > 0) {
                    int currentItem = viewPager.getCurrentItem();
                    mPageView.remove(currentItem);
                    for (int i = 0; i < mPageView.size(); i++) {
                        View view = mPageView.get(i);
                        final PhotoView pvItem = (PhotoView) view.findViewById(R.id.pv);
                        pvItem.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
                            @Override
                            public void onPhotoTap(View view, float v, float v1) {
                                Logger.e(pvItem.toString());
                                mPopWindow.dismiss();
                            }

                            @Override
                            public void onOutsidePhotoTap() {
                                Logger.e(pvItem.toString());
                                mPopWindow.dismiss();
                            }
                        });
                    }
                    adapter.setData(mPageView);
                    if (mPageView.size() == 0) {
                        mPopWindow.dismiss();
                    }
                    listener.deletePic(currentItem);
                }
            }
        });
        tvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.updatePic(viewPager.getCurrentItem());
            }
        });
        mPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                listener.onPopDismiss();
            }
        });
        mPopWindow.setClippingEnabled(false);
        mPopWindow.setContentView(contentView);
        mPopWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopWindow.setOutsideTouchable(true);
        mPopWindow.showAtLocation(view, Gravity.NO_GRAVITY,0,0);
        return viewPager;
    }

    public static List<Object> showMultiNoTitlePicsEase(final Context context, View view, final List<String> picUrls, final int currPosition, final PicPopListener listener) {
        View contentView = LayoutInflater.from(context).inflate(R.layout.activity_pic_picker, null);
        final ViewPager viewPager = (ViewPager) contentView.findViewById(R.id.view_pager);
        final TextView tvLeft = (TextView) contentView.findViewById(R.id.tv_delete);
        TextView tvRight = (TextView) contentView.findViewById(R.id.tv_change);
        final List<View> mPageView = new ArrayList<>();
        for (String picUrl : picUrls) {
            mPageView.add(new View(context));
        }
        List<Object> list = new ArrayList<>();
        final PopupWindow mPopWindow = new PopupWindow(contentView,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);

        final ViewPagerAdapter adapter = new ViewPagerAdapter(mPageView);
        viewPager.setAdapter(adapter);
        for (int i = 0; i < picUrls.size(); i++) {
            final int finalI = i;
            Glide.with(context).load(picUrls.get(i)).asBitmap().placeholder(R.mipmap.good_default).error(R.mipmap.good_default).into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    View item = LayoutInflater.from(context).inflate(
                            R.layout.pop_viewpager_item_ease, null);
                    EasePhotoView pv = (EasePhotoView) item.findViewById(R.id.pv);
                    pv.setImageBitmap(resource);
                    pv.setOnPhotoTapListener(new com.hc360.koiambuyer.widget.photoview.PhotoViewAttacher.OnPhotoTapListener() {
                        @Override
                        public void onPhotoTap(View view, float x, float y) {
                            mPopWindow.dismiss();
                        }
                    });
                    pv.setOnViewTapListener(new com.hc360.koiambuyer.widget.photoview.PhotoViewAttacher.OnViewTapListener() {
                        @Override
                        public void onViewTap(View view, float x, float y) {
                            mPopWindow.dismiss();
                        }
                    });
                    mPageView.set(finalI, item);
                    if (mPageView.size() == picUrls.size()) {
                        adapter.setData(mPageView);
                        if (mPageView.size() > currPosition) {
                            viewPager.setCurrentItem(currPosition);
                        }
                    }
                }
            });
        }
        tvLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPageView.size() > 0) {
                    int currentItem = viewPager.getCurrentItem();
                    mPageView.remove(currentItem);
//                    for (int i = 0; i < mPageView.size(); i++) {
//                        View view = mPageView.get(i);
//                        final EasePhotoView pvItem = (EasePhotoView) view.findViewById(pv);
//                        pvItem.setOnPhotoTapListener(new com.hyphenate.easeui.widget.photoview.PhotoViewAttacher.OnPhotoTapListener() {
//                            @Override
//                            public void onPhotoTap(View view, float x, float y) {
//                                mPopWindow.dismiss();
//                            }
//                        });
//                        pvItem.setOnViewTapListener(new com.hyphenate.easeui.widget.photoview.PhotoViewAttacher.OnViewTapListener() {
//                            @Override
//                            public void onViewTap(View view, float x, float y) {
//                                mPopWindow.dismiss();
//                            }
//                        });
//                    }
                    adapter.setData(mPageView);
                    if (mPageView.size() == 0) {
                        mPopWindow.dismiss();
                    }
                    listener.deletePic(currentItem);
                }
            }
        });
        tvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.updatePic(viewPager.getCurrentItem());
            }
        });
        mPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                listener.onPopDismiss();
            }
        });
        mPopWindow.setClippingEnabled(false);
        mPopWindow.setContentView(contentView);
        mPopWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopWindow.setOutsideTouchable(true);
        mPopWindow.showAtLocation(view, Gravity.NO_GRAVITY,0,0);
        list.add(viewPager);
        list.add(mPopWindow);
        return list;
    }


    public static void showOnlyPics(final Context context, View view, final List<String> picUrls, final int currPosition) {
        View contentView = LayoutInflater.from(context).inflate(R.layout.pop_only_pics, null);
        final ViewPager viewPager = (ViewPager) contentView.findViewById(R.id.view_pager);
        final TextView tvNum = (TextView) contentView.findViewById(R.id.tv_num);
        tvNum.setText((currPosition+1)+"/"+picUrls.size());
        final List<View> mPageView = new ArrayList<>();
        final PopupWindow mPopWindow = new PopupWindow(contentView,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        mPopWindow.setClippingEnabled(false);
        mPopWindow.setContentView(contentView);
        mPopWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopWindow.setOutsideTouchable(true);
        for (String picUrl : picUrls) {
            mPageView.add(new View(context));
        }
        final ViewPagerAdapter adapter = new ViewPagerAdapter(mPageView);
        viewPager.setAdapter(adapter);
        for (int i = 0; i < picUrls.size(); i++) {
            final int finalI = i;
            Glide.with(context).load(picUrls.get(i)).asBitmap().into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    View item = LayoutInflater.from(context).inflate(
                            R.layout.pop_viewpager_item, null);
                    PhotoView pv = (PhotoView) item.findViewById(R.id.pv);
                    pv.setBackgroundColor(context.getResources().getColor(R.color.tvNormalColor));
                    pv.setImageBitmap(resource);
                    pv.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
                        @Override
                        public void onPhotoTap(View view, float v, float v1) {
                            mPopWindow.dismiss();
                        }

                        @Override
                        public void onOutsidePhotoTap() {
                            mPopWindow.dismiss();
                        }
                    });
                    mPageView.set(finalI, item);
                    if (mPageView.size() == picUrls.size()) {
                        adapter.setData(mPageView);
                        if (mPageView.size() > currPosition) {
                            viewPager.setCurrentItem(currPosition);
                        }
                    }
                }
            });

        }
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tvNum.setText((position+1)+"/"+picUrls.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mPopWindow.showAtLocation(view, Gravity.NO_GRAVITY,0,0);
    }

    public static void showOnlyPic(final Context context, View view, String picUrl) {
        View contentView = LayoutInflater.from(context).inflate(R.layout.pop_only_pic, null);
        final PhotoView pv = (PhotoView) contentView.findViewById(R.id.pv);

        Glide.with(context).load(picUrl).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                pv.setImageBitmap(resource);
            }
        });
        final PopupWindow mPopWindow = new PopupWindow(contentView,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        pv.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float v, float v1) {
                mPopWindow.dismiss();
            }
            @Override
            public void onOutsidePhotoTap() {
                mPopWindow.dismiss();
            }
        });
        mPopWindow.setClippingEnabled(false);
        mPopWindow.setContentView(contentView);
        mPopWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopWindow.setOutsideTouchable(true);
        mPopWindow.showAtLocation(view, Gravity.NO_GRAVITY,0,0);
    }

}
