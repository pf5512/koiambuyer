package com.hc360.koiambuyer.view.base;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.widget.Toast;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.model.Constant;
import com.hc360.koiambuyer.myinterface.PhotoDialogClickListener;
import com.hc360.koiambuyer.utils.CameraUtil;
import com.hc360.koiambuyer.utils.ImageUtil;
import com.hc360.koiambuyer.utils.PhotoUtils;
import com.hc360.koiambuyer.utils.ToastUtil;
import com.hc360.koiambuyer.widget.PhotoDialog;

import java.io.File;
import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

import static com.hc360.koiambuyer.model.Constant.RC_CAMERA_AND_WIFI;


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

    @Override
    protected void initView() {
        mDialog = new PhotoDialog(this, this);
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
                        PhotoUtils.cropImageUri(this, imageUri, cropImageUri, Constant.CROP_PICTURE);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case Constant.OPEN_PHOTO:
                    if (hasSdcard()) {
                        cropImageUri = Uri.fromFile(fileCropUri);
                        Uri newUri = Uri.parse(PhotoUtils.getPath(this, data.getData()));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                            newUri = FileProvider.getUriForFile(this, "com.hc360.iambuyer.fileprovider", new File(newUri.getPath()));
                        }
                        PhotoUtils.cropImageUri(this, newUri, cropImageUri, Constant.CROP_PICTURE);
                    } else {
                        Toast.makeText(this, "设备没有SD卡!", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case Constant.CROP_PICTURE:
                    //裁剪后
                    try {
                        Bitmap bitmap = PhotoUtils.getBitmapFromUri(cropImageUri, this);
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
            imageUri = Uri.fromFile(fileUri);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                //通过FileProvider创建一个content类型的Uri
                imageUri = FileProvider.getUriForFile(this, "com.hc360.iambuyer.fileprovider", fileUri);
            PhotoUtils.takePicture(this, imageUri, Constant.OPEN_CAMERA);
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
            PhotoUtils.openPic(this, Constant.OPEN_PHOTO);
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
