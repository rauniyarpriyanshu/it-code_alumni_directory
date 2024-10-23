package com.pr.alumni_directory.fragment;

import android.app.Activity;
import android.content.Context;

public interface ActivityInterface {
    void screenInitialize();
    void appComponentLoad();

    Activity $activity();
    Context $context();

}
