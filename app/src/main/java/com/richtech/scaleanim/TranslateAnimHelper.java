package com.richtech.scaleanim;

import android.graphics.Rect;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

/**
 * author       : wangyalei
 * time         : 19-10-9
 * description  : 从本身位置开始, targetRight - sourceLeft = pivotX - (pivotX - sourceWidth) * toX；那么 pivotX = (targetRight - sourceLeft - sourceWidth * toX) / (1 - toX)
 *                从其它位置开始 :sourceLeft - targetLeft = pivotX * (1 - fromX), 那么pivotX = (sourceLeft - targetLeft) / (1 - fromX);
 *                https://bbs.csdn.net/topics/391822179
 *                https://www.jianshu.com/p/292803d2dfda
 * history      :
 */
public class TranslateAnimHelper {
    public static Animation tanslateScaleAnim(boolean fromOrigin, Rect sourceRect, Rect targetRect){
        Animation anim = null;

        float sx = targetRect.width() * 1.0f / sourceRect.width();
        float sy = targetRect.height() * 1.0f / sourceRect.height();

        boolean isScale = sx != 1 || sy != 1;

        if(isScale){
            anim = scaleAnim(fromOrigin, sourceRect, targetRect);
        }else{
            if(fromOrigin){
                int fromDeltaX = 0;
                int toDeltaX = targetRect.left - sourceRect.left;
                int fromDeltaY = 0;
                int toDeltaY = targetRect.top - sourceRect.top;

                anim = new TranslateAnimation(fromDeltaX, toDeltaX, fromDeltaY, toDeltaY);
            }else {
                int fromDeltaX = -(targetRect.left - sourceRect.left);
                int toDeltaX = 0;
                int fromDeltaY = -(targetRect.top - sourceRect.top);
                int toDeltaY = 0;
                anim = new TranslateAnimation(fromDeltaX, toDeltaX, fromDeltaY, toDeltaY);
            }
        }

        return anim;
    }


    public static Animation scaleAnim(boolean fromOrigin, Rect sourceRect, Rect targetRect){
        float sx = targetRect.width() * 1.0f / sourceRect.width();
        float sy = targetRect.height() * 1.0f / sourceRect.height();

        Animation animation = null;
        if(fromOrigin){
            float fromX = 1;
            float toX = sx;
            float fromY = 1;
            float toY = sy;

            float px = (targetRect.right - sourceRect.left - sourceRect.width() * toX) / (1 - toX);
            float py = (targetRect.bottom - sourceRect.top - sourceRect.height() * toY) / (1 - toY);

            animation = new ScaleAnimation(fromX, toX, fromY, toY, px, py);
        }else{

            float fromX =  1 / sx;
            float toX = 1;
            float fromY = 1 / sy;
            float toY = 1;

            float px = (sourceRect.left - targetRect.left) / (1 - fromX);
            float py = (sourceRect.top - targetRect.top) / (1 - fromY);

            animation = new ScaleAnimation(fromX, toX, fromY, toY, px, py);
        }

        return animation;
    }
}
