package com.hc360.koiambuyer.myinterface.ipresenter;

import android.graphics.Bitmap;

import com.hc360.koiambuyer.view.base.IBasePresenter;

import java.io.File;
import java.util.List;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/10/13
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public interface ISuggestionPresenter extends IBasePresenter {
    void postPic(File file, int position, Bitmap bitmap);
    void submit(String userId, String context,String phone,String feedType, List<String> imgs);
}
