package com.example.android.bakingapp.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

import com.example.android.bakingapp.R;

public class WidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new WidgetDataProvider(this, intent);
    }

}
