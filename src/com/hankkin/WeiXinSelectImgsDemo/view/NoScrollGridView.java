package com.hankkin.WeiXinSelectImgsDemo.view;

/**
 * Created by Hankkin on 15/10/10.
 */

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 屏蔽掉GridView的滚动，防止嵌入ScrollView时出现冲突
 * by:Hankkin at：2015-08-05 11:31:06
 *
 */
public class NoScrollGridView extends GridView {

    public NoScrollGridView(Context context) {
        super(context);
    }

    public NoScrollGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoScrollGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    //该自定义控件只是重写了GridView的onMeasure方法，使其不会出现滚动条，ScrollView嵌套ListView也是同样的道理，不再赘述。
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

}
