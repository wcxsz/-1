package com.example.geek.view;

import com.example.geek.base.BaseMvpView;
import com.example.geek.bean.WeChatBean;

public interface WeChatView extends BaseMvpView {
    void setData(WeChatBean weChatBean);

    void setShiBai(String string);

    void initData(WeChatBean weChatBean);

    void shuo(String string);
}
