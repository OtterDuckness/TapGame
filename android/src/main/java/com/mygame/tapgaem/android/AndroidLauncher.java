package com.mygame.tapgaem.android;

import android.os.Bundle;
import android.content.pm.ActivityInfo;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.mygame.tapgaem.TapGame;

/** Launches the Android application. */
public class AndroidLauncher extends AndroidApplication {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // FORCE PORTRAIT
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        AndroidApplicationConfiguration configuration =
            new AndroidApplicationConfiguration();

        configuration.useImmersiveMode = true;

        initialize(new TapGame(), configuration);
    }
}
