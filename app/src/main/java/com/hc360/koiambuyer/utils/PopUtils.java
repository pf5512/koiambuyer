package com.hc360.koiambuyer.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.adapter.DatePopAdapter;
import com.hc360.koiambuyer.adapter.ViewPagerAdapter;
import com.hc360.koiambuyer.api.bean.OrderDetailInfo;
import com.hc360.koiambuyer.engine.RecycleViewDivider;
import com.hc360.koiambuyer.myinterface.DatePopChoosePosition;
import com.hc360.koiambuyer.myinterface.PicPopListener;
import com.hc360.koiambuyer.myinterface.PopListener;
import com.hc360.koiambuyer.view.MyApp;
import com.hc360.koiambuyer.widget.photoview.EasePhotoView;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

import static com.hc360.koiambuyer.R.id.pv;


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
            Glide.with(context).load(picUrls.get(i)).into(new SimpleTarget<Drawable>() {
                @Override
                public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                    View item = LayoutInflater.from(context).inflate(
                            R.layout.pop_viewpager_item, null);
                    PhotoView pv = (PhotoView) item.findViewById(R.id.pv);
                    pv.setImageDrawable(resource);
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
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(R.mipmap.good_default);
            requestOptions.error(R.mipmap.good_default);
            Glide.with(context).setDefaultRequestOptions(requestOptions).load(picUrls.get(i)).into(new SimpleTarget<Drawable>() {
                @Override
                public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                    View item = LayoutInflater.from(context).inflate(
                            R.layout.pop_viewpager_item, null);
                    PhotoView pv = (PhotoView) item.findViewById(R.id.pv);
                    pv.setImageDrawable(resource);
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
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(R.mipmap.good_default);
            requestOptions.error(R.mipmap.good_default);
            Glide.with(context).setDefaultRequestOptions(requestOptions).load(picUrls.get(i)).into(new SimpleTarget<Drawable>() {
                @Override
                public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                    View item = LayoutInflater.from(context).inflate(
                            R.layout.pop_viewpager_item, null);
                    PhotoView pv = (PhotoView) item.findViewById(R.id.pv);
                    pv.setImageDrawable(resource);
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
                        final PhotoView pvItem = (PhotoView) view.findViewById(pv);
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
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(R.mipmap.good_default);
            requestOptions.error(R.mipmap.good_default);
            Glide.with(context) .setDefaultRequestOptions(requestOptions).load(picUrls.get(i)).into(new SimpleTarget<Drawable>() {
                @Override
                public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                    View item = LayoutInflater.from(context).inflate(
                            R.layout.pop_viewpager_item_ease, null);
                    EasePhotoView pv = (EasePhotoView) item.findViewById(R.id.pv);
                    pv.setImageDrawable(resource);
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
            Glide.with(context).load(picUrls.get(i)).into(new SimpleTarget<Drawable>() {
                @Override
                public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                    View item = LayoutInflater.from(context).inflate(
                            R.layout.pop_viewpager_item, null);
                    PhotoView pv = (PhotoView) item.findViewById(R.id.pv);
                    pv.setBackgroundColor(context.getResources().getColor(R.color.tvNormalColor));
                    pv.setImageDrawable(resource);
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

        Glide.with(context).load(picUrl).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                pv.setImageDrawable(resource);
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
    public static void showOnlyPicSave(final Context context, View view, final String picUrl) {
        View contentView = LayoutInflater.from(context).inflate(R.layout.pop_only_pic, null);
        final PhotoView pv = (PhotoView) contentView.findViewById(R.id.pv);
        TextView tvSave = (TextView) contentView.findViewById(R.id.tv_save);
        tvSave.setVisibility(View.VISIBLE);
        Glide.with(context).load(picUrl).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                pv.setImageDrawable(resource);
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
        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(context).load(picUrl).into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                        BitmapDrawable bd = (BitmapDrawable) resource;
                        Bitmap bm = bd.getBitmap();
                        CameraUtil.saveBmp2Gallery(context, bm,System.currentTimeMillis()+".jpg");
                    }
                });
            }
        });
        mPopWindow.setClippingEnabled(false);
        mPopWindow.setContentView(contentView);
        mPopWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopWindow.setOutsideTouchable(true);
        mPopWindow.showAtLocation(view, Gravity.NO_GRAVITY,0,0);
    }

    public static void showLogistics(final Context context, final View view, final OrderDetailInfo.ContentBean.StProductOrderExpressBean bean) {
        View contentView = LayoutInflater.from(context).inflate(R.layout.pop_logistics, null);
        TextView tvLogisticsNum = (TextView) contentView.findViewById(R.id.tv_logistics_num);
        TextView tvLogisticsCompany = (TextView) contentView.findViewById(R.id.tv_logistics_company);
        tvLogisticsNum.setText(bean.expressNo);
        tvLogisticsCompany.setText(bean.expressComp);
        ImageView ivLogistics = (ImageView) contentView.findViewById(R.id.iv_logistics);
        TextView tvCancel = (TextView) contentView.findViewById(R.id.tv_cancel);
        View bg = contentView.findViewById(R.id.bg);
        GlideUtils.loadGood(context,bean.expressImg,ivLogistics);
        final PopupWindow mPopWindow = new PopupWindow(contentView,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopWindow.dismiss();
            }
        });
        bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopWindow.dismiss();
            }
        });
        ivLogistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOnlyPicSave(context,view, bean.expressImg);
            }
        });
        mPopWindow.setClippingEnabled(false);
        mPopWindow.setContentView(contentView);
        mPopWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopWindow.setOutsideTouchable(true);
        mPopWindow.showAtLocation(view, Gravity.NO_GRAVITY,0,0);
    }


    public static PopupWindow getPopType(final Context context, View view, final List<String> list, int position,final DatePopChoosePosition listener) {
        View contentView = LayoutInflater.from(context).inflate(R.layout.pop_date, null);
        RecyclerView rv = (RecyclerView) contentView.findViewById(R.id.rv);
        rv.addItemDecoration(new RecycleViewDivider(context, LinearLayoutManager.HORIZONTAL, 1,DensityUtil.dp2px(context,15), context.getResources().getColor(R.color.LineColor)));
        rv.setLayoutManager(new LinearLayoutManager(context));

        final PopupWindow popWindow = new PopupWindow(contentView,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        popWindow.setContentView(contentView);
        popWindow.setBackgroundDrawable(new BitmapDrawable());
        popWindow.setOutsideTouchable(true);
        popWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if (listener != null){
                    listener.onPopDismiss();
                }
            }
        });
        final DatePopAdapter adapter = new DatePopAdapter(R.layout.rv_date_item, list);
        adapter.prePosition = position;
        adapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                listener.getType(i,list.get(i));
                adapter.prePosition = i;
                adapter.notifyDataSetChanged();
                popWindow.dismiss();
            }
        });
        rv.setAdapter(adapter);
//        popWindow.showAtLocation(view, Gravity.NO_GRAVITY,0,0);
        popWindow.showAsDropDown(view);
        return popWindow;
    }

}
