package com.hankkin.WeiXinSelectImgsDemo.popwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import com.hankkin.WeiXinSelectImgsDemo.R;

/**
 * Created by Hankkin on 15/10/10.
 */
public class SelectPicPopupWindow extends PopupWindow {

    private Button item_popupwindows_camera,    //弹窗拍照按钮
            item_popupwindows_Photo,            //弹窗从相册选择按钮
            item_popupwindows_cancel;           //弹窗取消按钮
    private View menuview;

    /**
     * 上传图片*************************
     * @param context
     * @param itemsOnclick
     */
    public SelectPicPopupWindow(Activity context, View.OnClickListener itemsOnclick){
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        menuview = inflater.inflate(R.layout.item_popupwindows,null);
        item_popupwindows_camera = (Button) menuview.findViewById(R.id.item_popupwindows_camera);   //拍照按钮
        item_popupwindows_cancel = (Button) menuview.findViewById(R.id.item_popupwindows_cancel);   //取消按钮
        item_popupwindows_Photo = (Button) menuview.findViewById(R.id.item_popupwindows_Photo);     //图库按钮

        /**
         * 取消按钮销毁事件
         * by黄海杰@2015-1-7
         */
        item_popupwindows_cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                dismiss();
            }
        });
        item_popupwindows_camera.setOnClickListener(itemsOnclick);
        item_popupwindows_Photo.setOnClickListener(itemsOnclick);
        //设置SelectPicPopupWindow的View
        this.setContentView(menuview);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.FILL_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        //修改高度显示，解决被手机底部虚拟键挡住的问题  by黄海杰 at:2015-4-30
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        //this.setAnimationStyle(R.style);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        //menuview添加ontouchlistener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        menuview.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int height = menuview.findViewById(R.id.ll_popup).getTop();
                int y = (int) motionEvent.getY();
                if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    if (y<height){
                        dismiss();
                    }
                }
                return true;
            }
        });


    }


}
