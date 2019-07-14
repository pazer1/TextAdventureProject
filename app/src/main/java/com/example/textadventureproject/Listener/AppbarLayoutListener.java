package com.example.textadventureproject.Listener;

import android.util.Log;

import com.google.android.material.appbar.AppBarLayout;

public abstract class AppbarLayoutListener implements AppBarLayout.OnOffsetChangedListener {

    public enum State{
        EXPANDED,
        COLLAPSED,
        IDEL
    }

    private State mCurrentState = State.IDEL;

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

        if(verticalOffset==0){
            if(mCurrentState != State.EXPANDED){
                onStateChanged(appBarLayout,State.EXPANDED);
            }
            mCurrentState = State.EXPANDED;
        }else if(Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()){
            if(mCurrentState != State.COLLAPSED){
                onStateChanged(appBarLayout,State.COLLAPSED);
            }
            mCurrentState = State.COLLAPSED;
        }else{
            if(mCurrentState != State.IDEL){
                onStateChanged(appBarLayout,State.IDEL);
            }
            mCurrentState = State.IDEL;
        }
    }
    public abstract void onStateChanged(AppBarLayout appBarLayout, State state);
}
