package com.example.android.bakingapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.entities.Recipe;
import com.example.android.bakingapp.fragments.IngredientsListFragment;
import com.example.android.bakingapp.fragments.StepsListFragment;

import static com.example.android.bakingapp.Constants.EXTRA_RECIPE;

public class RecipeDetailActivity extends AppCompatActivity {
    private static final String TAG = RecipeDetailActivity.class.getSimpleName();
    private Recipe mRecipe;
    private boolean mIsTwoPane = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        Intent intent = getIntent();
        if ((intent != null) && (intent.hasExtra(EXTRA_RECIPE))) {
            mRecipe = (Recipe) intent.getSerializableExtra(EXTRA_RECIPE);

            setTitle(mRecipe.getName());

            if (mIsTwoPane) {

            } else {
                createFragmentIngredients();
                createFragmentSteps();
            }

        }
    }

    private void createFragmentIngredients() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        IngredientsListFragment ingredientsListFragment = new IngredientsListFragment();
        fragmentManager.beginTransaction()
                .replace(R.id.ingredients_container, ingredientsListFragment)
                .commit();

    }

    private void createFragmentSteps() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        StepsListFragment stepsListFragment = new StepsListFragment();
        fragmentManager.beginTransaction()
                .replace(R.id.steps_container, stepsListFragment)
                .commit();

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onRestoreInstanceState: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
        Log.d(TAG, "onRestoreInstanceState: ");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState: ");
    }
}
