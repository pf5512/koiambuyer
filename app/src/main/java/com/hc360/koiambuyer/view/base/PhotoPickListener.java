package com.hc360.koiambuyer.view.base;

import android.graphics.Bitmap;

import java.io.File;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/10/24
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public interface PhotoPickListener {
    void onPhotoPick(File file, Bitmap bitmap);
}
