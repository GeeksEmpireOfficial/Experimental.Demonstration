package com.geekstest.dynamicfeaturedemo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class InstantAppLink extends BaseConfigurations {

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        // ATTENTION: This was auto-generated to handle app links.
        Intent appLinkIntent = getIntent();
        String appLinkAction = appLinkIntent.getAction();
        Uri appLinkData = appLinkIntent.getData();
    }
}
