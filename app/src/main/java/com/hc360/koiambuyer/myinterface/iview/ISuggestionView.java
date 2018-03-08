package com.hc360.koiambuyer.myinterface.iview;

import android.graphics.Bitmap;

import com.hc360.koiambuyer.api.bean.PostPicInfo;


/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/10/13
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public interface ISuggestionView {
    void postPic(PostPicInfo info, int position, Bitmap bitmap);
    void submitSuccess();
    void deleteImg(int position);
    void showPics(int position);
}
