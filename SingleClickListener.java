package com.stellium.music.live.managers.listeners;

import android.view.View;

public abstract class SingleClickListener implements View.OnClickListener {

    boolean clicked;

    public SingleClickListener() {
        clicked = false;
    }


    @Override
    public void onClick(View v) {
        if(clicked)
            return;
        clicked = true;
        performClick(v);
    }

    public abstract void performClick(View v);
}
