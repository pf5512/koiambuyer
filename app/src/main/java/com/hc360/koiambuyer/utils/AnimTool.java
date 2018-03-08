package com.hc360.koiambuyer.utils;

import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;

/**
 * Project: Ruhefu
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/4/27
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class AnimTool {

    public static Animation shakeAnimation(int counts) {
        Animation translateAnimation = new TranslateAnimation(0, 10, 0, 0);
        //设置一个循环加速器，使用传入的次数就会出现摆动的效果。
        translateAnimation.setInterpolator(new CycleInterpolator(counts));
        translateAnimation.setDuration(500);
        return translateAnimation;
    }
}
