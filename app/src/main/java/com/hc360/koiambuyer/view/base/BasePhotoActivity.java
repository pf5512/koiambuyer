package com.hc360.koiambuyer.view.base;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.widget.Toast;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.model.Constant;
import com.hc360.koiambuyer.myinterface.PhotoDialogClickListener;
import com.hc360.koiambuyer.utils.CameraUtil;
import com.hc360.koiambuyer.utils.ImageUtil;
import com.hc360.koiambuyer.utils.PhotoUtils;
import com.hc360.koiambuyer.utils.ToastUtil;
import com.hc360.koiambuyer.widget.PhotoDialog;
import com.luck.picture.lib.PictureSelectionModel;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

import static com.hc360.koiambuyer.model.Constant.RC_CAMERA_AND_WIFI;
import static com.hc360.koiambuyer.utils.PhotoUtils.getBitmapFromUri;


/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/9/30
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public abstract class BasePhotoActivity<T extends IBasePhotoPresenter> extends BaseActivity<T> implements PhotoDialogClickListener, EasyPermissions.PermissionCallbacks,PhotoPickListener{

    String[] perms = {Manifest.permission.CAMERA, Manifest.permission.CHANGE_WIFI_STATE,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE};
    public PhotoDialog mDialog;
    private File fileUri = new File(Environment.getExternalStorageDirectory().getPath() + "/photo.jpg");
    public File fileCropUri = new File(Environment.getExternalStorageDirectory().getPath() + "/"+System.currentTimeMillis()+"crop_photo.jpg");
    private Uri imageUri;
    private Uri cropImageUri;
    private int mCanSelect = 1;
    private String cropRatio = "";

    @Override
    protected void initView() {
        mDialog = new PhotoDialog(this, this);
    }

    public void setCropRatio(String ratio){
        this.cropRatio = ratio;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case Constant.OPEN_CAMERA:
                    //拍照
                    try {
                        cropImageUri = Uri.fromFile(fileCropUri);
                        try {
                            Bitmap bitmap = getBitmapFromUri(cropImageUri, this);
                            if (bitmap != null) {
                                Bitmap crop_bitmap = ImageUtil.compressBitmapByQuality( bitmap, 200);
                                File file = CameraUtil.saveImageToGallery(this, crop_bitmap, Constant.HEAD_PIC);
                                //上传图片
                                onPhotoPick(file,crop_bitmap);
                            }else {

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
//                        PhotoUtils.cropImageUri(this, imageUri, cropImageUri, Constant.CROP_PICTURE);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case Constant.OPEN_PHOTO:
                    if (hasSdcard()) {
                        cropImageUri = Uri.fromFile(fileCropUri);
                        Uri newUri = Uri.parse(PhotoUtils.getPath(this, data.getData()));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                            newUri = FileProvider.getUriForFile(this, "com.hc360.koiambuyer.fileprovider", new File(newUri.getPath()));
                        }
                        try {
                            Bitmap bitmap = getBitmapFromUri(newUri, this);
                            if (bitmap != null) {
//                                Bitmap crop_bitmap = ImageUtil.compressBitmapByQuality( bitmap, 200);
                                File file = CameraUtil.saveImageToGallery(this, bitmap, Constant.HEAD_PIC);
                                //上传图片
                                Logger.e(file.getAbsolutePath());
                                onPhotoPick(file,bitmap);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
//                        PhotoUtils.cropImageUri(this, newUri, cropImageUri, Constant.CROP_PICTURE);
                    } else {
                        Toast.makeText(this, getStr(R.string.have_no_sd), Toast.LENGTH_SHORT).show();
                    }
                    break;
                case Constant.CROP_PICTURE:
                    //裁剪后
                    try {
                        Bitmap bitmap = getBitmapFromUri(cropImageUri, this);
                        if (bitmap != null) {
                            Bitmap crop_bitmap = ImageUtil.compressBitmapByQuality( bitmap, 200);
                            File file = CameraUtil.saveImageToGallery(this, crop_bitmap, Constant.HEAD_PIC);
                            //上传图片
                            onPhotoPick(file,crop_bitmap);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片、视频、音频选择结果回调
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true  注意：音视频除外
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true  注意：音视频除外
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                    for (LocalMedia media : selectList) {
                        if (media.isCompressed()){
                            onPhotoPick(new File(media.getCompressPath()), BitmapFactory.decodeFile(media.getCompressPath()));
                        }
                    }
                    break;
            }
        }
    }

    @Override
    public void openCamera() {
        if (EasyPermissions.hasPermissions(this, perms)) {
            canOpenCamera();
        } else {
            EasyPermissions.requestPermissions(this, getStr(R.string.camera_permission),
                    RC_CAMERA_AND_WIFI, perms);
        }
    }

    private void canOpenCamera() {
        if (hasSdcard()) {
            PictureSelectionModel model = PictureSelector.create(this)
                    .openCamera(PictureMimeType.ofImage())
                    .compress(true);
            if (!TextUtils.isEmpty(cropRatio)){
                try {
                    if (cropRatio.equals("1:1")){
                        model.circleDimmedLayer(true);
                    }else {
                        String[] split = cropRatio.split(":");
                        model.enableCrop(true)// 是否裁剪 true or false
                                .withAspectRatio(Integer.parseInt(split[0]),Integer.parseInt(split[1]));// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                    }
                }catch (Exception e){

                }
            }
            model.forResult(PictureConfig.CHOOSE_REQUEST);
        } else {
            Toast.makeText(this, getStr(R.string.have_no_sd), Toast.LENGTH_SHORT).show();
        }
        mDialog.dismiss();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        if (requestCode == RC_CAMERA_AND_WIFI) {
            canOpenCamera();
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (requestCode == RC_CAMERA_AND_WIFI) {
            ToastUtil.showLong(this, getStr(R.string.open_camera));
        }
    }

    @Override
    public void openPhoto() {
        if (EasyPermissions.hasPermissions(this, perms)) {
            PictureSelectionModel mode = PictureSelector.create(this)
                    .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                    .imageSpanCount(4)// 每行显示个数 int
                    .selectionMode(PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                    .isCamera(true)// 是否显示拍照按钮 true or false
                    .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                    .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                    .enableCrop(false)// 是否裁剪 true or false
                    .compress(true);// 是否压缩 true or false
            if (!TextUtils.isEmpty(cropRatio)){
                try {
                    if (cropRatio.equals("1:1")){
                        mode.circleDimmedLayer(true);
                    }else {
                        String[] split = cropRatio.split(":");
                        mode.enableCrop(true)// 是否裁剪 true or false
                                .withAspectRatio(Integer.parseInt(split[0]),Integer.parseInt(split[1]));// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                    }
                }catch (Exception e){

                }
            }
            mode.forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
            mDialog.dismiss();
        } else {
            EasyPermissions.requestPermissions(this, getStr(R.string.pics_permission),
                    RC_CAMERA_AND_WIFI, perms);
        }

    }

    @Override
    public void cancel() {
        mDialog.dismiss();
    }

    /**
     * 检查设备是否存在SDCard的工具方法
     */
    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }

}
