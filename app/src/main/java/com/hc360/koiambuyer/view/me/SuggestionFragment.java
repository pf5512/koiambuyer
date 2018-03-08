package com.hc360.koiambuyer.view.me;

import android.Manifest;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.FileProvider;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.adapter.LLAdapter;
import com.hc360.koiambuyer.adapter.ViewPagerAdapter;
import com.hc360.koiambuyer.api.bean.PostPicInfo;
import com.hc360.koiambuyer.engine.CustomToast;
import com.hc360.koiambuyer.model.Constant;
import com.hc360.koiambuyer.myinterface.DialogPositiveClickListener;
import com.hc360.koiambuyer.myinterface.PhotoDialogClickListener;
import com.hc360.koiambuyer.myinterface.PicPopListener;
import com.hc360.koiambuyer.myinterface.ipresenter.ISuggestionPresenter;
import com.hc360.koiambuyer.myinterface.iview.ISuggestionView;
import com.hc360.koiambuyer.presenter.SuggestionPresenter;
import com.hc360.koiambuyer.utils.DialogHelper;
import com.hc360.koiambuyer.utils.EtHelper;
import com.hc360.koiambuyer.utils.PhotoUtils;
import com.hc360.koiambuyer.utils.PicNumUtils;
import com.hc360.koiambuyer.utils.PopUtils;
import com.hc360.koiambuyer.utils.ToastUtil;
import com.hc360.koiambuyer.view.ContainerFooterActivity;
import com.hc360.koiambuyer.view.MyApp;
import com.hc360.koiambuyer.view.base.BaseFragment;
import com.hc360.koiambuyer.widget.PhotoDialog;
import com.hc360.koiambuyer.widget.SingleTextView;
import com.hc360.koiambuyer.widget.photoview.EasePhotoView;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.EasyPermissions;

import static com.hc360.koiambuyer.model.Constant.RC_CAMERA_AND_WIFI;


/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/9/21
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class SuggestionFragment extends BaseFragment<ISuggestionPresenter> implements PhotoDialogClickListener, ISuggestionView {
    //最大图片数
    private static final int PIC_MAX_NUM = 5;
    //最大字数
    private static final int WORD_MAX_NUM = 200;
    @BindView(R.id.et)
    EditText mEt;
    @BindView(R.id.tv_et)
    TextView mTvEt;
    @BindView(R.id.rv)
    RecyclerView mRv;
    @BindView(R.id.tv_rv)
    TextView mTvRv;
    @BindView(R.id.stv)
    SingleTextView mStv;
    @BindView(R.id.iv_add_pic)
    ImageView mIvAddPic;
    @BindView(R.id.fl)
    FrameLayout mFl;
    private List<Bitmap> pics;
    private LLAdapter llAdapter;
    private PhotoDialog mDialog;

    String[] perms = {Manifest.permission.CAMERA, Manifest.permission.CHANGE_WIFI_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
    private List<String> mImgs;
    private ArrayList<String> mHttpImgs;
    private int changePosition = -1;
    private ViewPager mPopViewPager;
    private boolean isPopShow = false;
    private PopupWindow mPopupWindow;
    private CustomToast mCustomToast;


    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_suggestion;
    }

    @Override
    protected void initInjector() {
        mPresenter = new SuggestionPresenter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRv.setLayoutManager(linearLayoutManager);
        pics = new ArrayList();
        llAdapter = new LLAdapter(R.layout.rv_ll_item, pics, this);
        mRv.setAdapter(llAdapter);
        mImgs = new ArrayList<>();
        mHttpImgs = new ArrayList<>();
        mCustomToast = new CustomToast(mContext);
    }

    @Override
    protected void initViews() {
        EtHelper.etLimitColor(mEt, mTvEt, WORD_MAX_NUM);
        mStv.setText(getStr(R.string.submit));
        mDialog = new PhotoDialog(mContext, this);
        PicNumUtils.setPicNum(mTvRv, 0, PIC_MAX_NUM);
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @OnClick({R.id.iv_add_pic, R.id.stv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_add_pic:
                mDialog.show();
                break;
            case R.id.stv:
                //提交
                String trim = mEt.getText().toString().trim();
                if (TextUtils.isEmpty(trim)) {
                    ToastUtil.showShort(mContext, getStr(R.string.have_no_suggestion));
                    return;
                }
                mPresenter.submit(MyApp.sUserId, trim, mImgs);
                break;
        }
    }



    @Override
    public void cancel() {
        mDialog.dismiss();
    }

    public void getPic(Bitmap bitmap, File file) {
        //添加图片
        if (llAdapter.getData().size() >= PIC_MAX_NUM) {
            ToastUtil.showShort(mContext, getStr(R.string.sorry_only_add) + PIC_MAX_NUM + getStr(R.string.pic_num));
            return;
        }
        mPresenter.postPic(file, changePosition,bitmap);
        if (mCustomToast != null) {
            mCustomToast.alwaysShow(getStr(R.string.updating));
        }
    }

    @Override
    public void postPic(PostPicInfo info, int position, Bitmap bitmap) {
        if (position != -1 && mImgs.size() > position) {
            //更换的操作
            mImgs.set(position, info.name);
            mHttpImgs.set(position, info.httpUrl);
        } else {
            mImgs.add(info.name);
            mHttpImgs.add(info.httpUrl);
        }
        if (mCustomToast != null) {
            mCustomToast.hide();
        }
        if (changePosition != -1 && llAdapter.getData().size() > changePosition) {
            //这是更换的操作
            llAdapter.getData().set(changePosition, bitmap);

            if (mPopViewPager != null) {
                ViewPagerAdapter adapter = (ViewPagerAdapter) mPopViewPager.getAdapter();
                View item = LayoutInflater.from(mContext).inflate(
                        R.layout.pop_viewpager_item_ease, null);
                EasePhotoView pv = (EasePhotoView) item.findViewById(R.id.pv);
                pv.setImageBitmap(bitmap);
                pv.setOnPhotoTapListener(new com.hc360.koiambuyer.widget.photoview.PhotoViewAttacher.OnPhotoTapListener() {
                    @Override
                    public void onPhotoTap(View view, float x, float y) {
                        mPopupWindow.dismiss();
                    }
                });
                pv.setOnViewTapListener(new com.hc360.koiambuyer.widget.photoview.PhotoViewAttacher.OnViewTapListener() {
                    @Override
                    public void onViewTap(View view, float x, float y) {
                        mPopupWindow.dismiss();
                    }
                });
                List<View> data = adapter.getData();
                data.set(changePosition, item);
                adapter.setData(data);
            }

        } else {
            //这是添加的操作
            llAdapter.getData().add(bitmap);
        }
        llAdapter.notifyDataSetChanged();
        PicNumUtils.setPicNum(mTvRv, llAdapter.getData().size(), PIC_MAX_NUM);
        changePosition = -1;
    }

    @Override
    public void submitSuccess() {
        DialogHelper.showCustomNoCancelDialog(mContext, null, getStr(R.string.suggestion_have_submit), null, new DialogPositiveClickListener() {
            @Override
            public void positiveClick() {
                getActivity().finish();
            }
        });
    }

    @Override
    public void deleteImg(int position) {
        mImgs.remove(position);
        mHttpImgs.remove(position);
        PicNumUtils.setPicNum(mTvRv, mImgs.size(), PIC_MAX_NUM);
    }

    @Override
    public void showPics(int position) {
        isPopShow = true;
        ContainerFooterActivity activity = (ContainerFooterActivity) getActivity();
        if (llAdapter.getData().size() == mHttpImgs.size()){
            List<Object> list = PopUtils.showMultiNoTitlePicsEase(mContext, activity.mHead, mHttpImgs, position, new PicPopListener() {
                @Override
                public void deletePic(int currPosition) {
                    deleteImg(currPosition);
                    llAdapter.getData().remove(currPosition);
                    llAdapter.notifyDataSetChanged();
                }

                @Override
                public void updatePic(int currPosition) {
                    changePosition = currPosition;
                    mDialog.show();
                }

                @Override
                public void onPopDismiss() {
                    isPopShow = false;
                    mFl.setBackgroundColor(getResources().getColor(R.color.BgColor));
                }
            });
            mPopViewPager = (ViewPager) list.get(0);
            mPopupWindow = (PopupWindow) list.get(1);
            mFl.setBackgroundColor(Color.parseColor("#000000"));

        }else{
            ToastUtil.showShort(mContext,getStr(R.string.pic_updating));
            Logger.e(llAdapter.getData().size()+"==="+mHttpImgs.size());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void openCamera() {
        if (EasyPermissions.hasPermissions(mContext, perms)) {
            canOpenCamera();
        } else {
            EasyPermissions.requestPermissions(this, getStr(R.string.camera_permission),
                    RC_CAMERA_AND_WIFI, perms);
        }
    }

    @Override
    public void openPhoto() {
        if (EasyPermissions.hasPermissions(mContext, perms)) {
            ContainerFooterActivity activity = (ContainerFooterActivity) getActivity();
            PhotoUtils.openPic(activity, Constant.OPEN_PHOTO);
            mDialog.dismiss();
        } else {
            EasyPermissions.requestPermissions(this, getStr(R.string.camera_permission),
                    RC_CAMERA_AND_WIFI, perms);
        }
    }

    private void canOpenCamera() {
        if (hasSdcard()) {
            ContainerFooterActivity activity = (ContainerFooterActivity) getActivity();
            activity.imageUri = Uri.fromFile(activity.fileUri);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                //通过FileProvider创建一个content类型的Uri
                activity.imageUri = FileProvider.getUriForFile(mContext, "com.hc360.iambuyer.fileprovider", activity.fileUri);
            PhotoUtils.takePicture(activity, activity.imageUri, Constant.OPEN_CAMERA);
        } else {
            Toast.makeText(mContext, getStr(R.string.have_no_sd), Toast.LENGTH_SHORT).show();
        }
        mDialog.dismiss();
    }
}
