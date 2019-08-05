package com.example.android.bakingapp.widget;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.entities.Ingredient;
import com.example.android.bakingapp.entities.Recipe;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.example.android.bakingapp.Constants.KEY_RECIPE;
import static com.example.android.bakingapp.Constants.SHARED_PREF;

public class WidgetDataProvider implements RemoteViewsService.RemoteViewsFactory {
    // this class is like the adapter in the main activity

    private static final String TAG = WidgetDataProvider.class.getSimpleName();

    List<String> collection = new ArrayList<>();
    Recipe mRecipe;
    Context context;
    Intent intent;

    public WidgetDataProvider(Context context, Intent intent) {
        this.context = context;
        this.intent = intent;
        Log.d(TAG, "WidgetDataProvider: ");
    }

    private void initData() {
        Log.d(TAG, "initData: ");
        loadData();
        collection.clear();
        collection.add("*** " + mRecipe.getName().toUpperCase() + " ***");
        for (Ingredient ingredient : mRecipe.ingerdients) {
            collection.add(ingredient.getIngredient());
        }
    }

    private void loadData() {
        Log.d(TAG, "loadData: ");
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(KEY_RECIPE, null);
        //Log.d(TAG, "loadData: sharedPreferencs json := "+ json);
        Type type = new TypeToken<Recipe>() {
        }.getType();
        mRecipe = gson.fromJson(json, type);

        if (mRecipe == null) {
            mRecipe = new Recipe(-1, "none", null, null, 0, "");
        }
    }

    @Override
    public void onCreate() {
        initData();
    }

    @Override
    public void onDataSetChanged() {
        initData();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return collection.size();
    }

    @Override
    public RemoteViews getViewAt(int i) {
        RemoteViews view = new RemoteViews(context.getPackageName(),
                android.R.layout.simple_list_item_1);
        view.setTextViewText(android.R.id.text1, collection.get(i));
        return view;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
