package com.steadyoung.app.commonlibs.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.WindowManager;

import com.steadyoung.app.commonlibs.R;
import com.wang.avi.AVLoadingIndicatorView;

/**
 * Created by Administrator on 2018/3/14.
 * Email : steadyoung@foxmail.com
 * Auth  : wayne
 * Desc  : LoadDialog
 */

public class LoadDialog extends Dialog {

    private AVLoadingIndicatorView av_loading;

    public LoadDialog(@NonNull Context context) {
        super(context, R.style.DialogBgStyle);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        init(getContext());
    }

    private void init(Context context)
    {
        //设置不可取消，点击其他区域不能取消，实际中可以抽出去封装供外包设置
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        setContentView(R.layout.dialog_load);

        av_loading = findViewById(R.id.av_loading);

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(params);
    }


    /**
     * 修改load图标样式
     * @param indicator
     */
    public void setIndicator(String indicator){
        if(av_loading != null){
            av_loading.setIndicator(indicator);
        }
    }

    /**
     * 样式
     */
    private static final String[] INDICATORS=new String[]{
            "BallPulseIndicator",
            "BallGridPulseIndicator",
            "BallClipRotateIndicator",
            "BallClipRotatePulseIndicator",
            "SquareSpinIndicator",
            "BallClipRotateMultipleIndicator",
            "BallPulseRiseIndicator",
            "BallRotateIndicator",
            "CubeTransitionIndicator",
            "BallZigZagIndicator",
            "BallZigZagDeflectIndicator",
            "BallTrianglePathIndicator",
            "BallScaleIndicator",
            "LineScaleIndicator",
            "LineScalePartyIndicator",
            "BallScaleMultipleIndicator",
            "BallPulseSyncIndicator",
            "BallBeatIndicator",
            "LineScalePulseOutIndicator",
            "LineScalePulseOutRapidIndicator",
            "BallScaleRippleIndicator",
            "BallScaleRippleMultipleIndicator",
            "BallSpinFadeLoaderIndicator",
            "LineSpinFadeLoaderIndicator",
            "TriangleSkewSpinIndicator",
            "PacmanIndicator",
            "BallGridBeatIndicator",
            "SemiCircleSpinIndicator",
            "com.wang.avi.sample.MyCustomIndicator"
    };

}
