package com.example.android.bakingapp.fragments;


import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.activities.RecipeDetailActivity;
import com.example.android.bakingapp.adapters.RecipeAdapter;
import com.example.android.bakingapp.entities.Recipe;
import com.example.android.bakingapp.network.Api;
import com.example.android.bakingapp.network.RetrofitClientInstance;
import com.example.android.bakingapp.widget.WidgetDataProvider;
import com.example.android.bakingapp.widget.WidgetService;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.android.bakingapp.Constants.EXTRA_RECIPE;
import static com.example.android.bakingapp.Constants.KEY_RECIPE;
import static com.example.android.bakingapp.Constants.SHARED_PREF;

public class RecipesListFragment extends Fragment implements RecipeAdapter.RecipeAdapterOnClickHandler {
    private static final String TAG = RecipesListFragment.class.getSimpleName();
    private final Api mApi = RetrofitClientInstance.getRetrofitInstance().create(Api.class);
    private RecyclerView mRecyclerView;
    private List<Recipe> mRecipes;


    public RecipesListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recipes_list, container, false);
        mRecyclerView = view.findViewById(R.id.recylerView_recipes);
        if (view.findViewById(R.id.layoutActivityMainIsOnTablet) == null)
            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 1));
        else
            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 3));


        //mRecyclerView.setAdapter(new RecipeAdapter(mRecipes, getActivity(), isShoppingList))
        loadRecipes();
        return view;
    }

    private void loadRecipes() {
        Log.d(TAG, "loadRecipes: enter");
        Call<List<Recipe>> call;
        call = mApi.getRecipes();
        call.enqueue(new Callback<List<Recipe>>() {
                         @Override
                         public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                             if (response.isSuccessful()) {
                                 showRecipes(response.body());
                             }
                         }

                         @Override
                         public void onFailure(Call<List<Recipe>> call, Throwable t) {
                             Log.e(TAG, t.getMessage());
                             Toast.makeText(getActivity().getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                         }
                     }

        );
    }

    private void showRecipes(List<Recipe> recipes) {
        RecipeAdapter recipeAdapter = new RecipeAdapter(getActivity().getApplicationContext(), recipes, this);
        mRecyclerView.setAdapter(recipeAdapter);
    }

    @Override
    public void OnClick(Recipe recipe) {
        saveRecipe(recipe);
        Intent intent = new Intent(getActivity().getApplicationContext(), RecipeDetailActivity.class);
        intent.putExtra(EXTRA_RECIPE, recipe);
        startActivity(intent);

    }

    private void saveRecipe(Recipe recipe) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(recipe);
        editor.putString(KEY_RECIPE, json);
        editor.apply();
        Log.d(TAG, "saveRecipe: notifyWidgetToUpdate: " + recipe.getName());
        notifyWidgetToUpdate();
    }

    private void notifyWidgetToUpdate() {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getActivity().getApplicationContext());
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(getActivity().getApplicationContext(), WidgetDataProvider.class));
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widget_list);
    }


}
