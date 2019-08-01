package com.example.android.bakingapp.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.adapters.IngredientsAdapter;
import com.example.android.bakingapp.entities.Recipe;

import static com.example.android.bakingapp.Constants.EXTRA_RECIPE;


public class IngredientsListFragment extends Fragment {

    private static final String TAG = IngredientsListFragment.class.getSimpleName();

    private Recipe mRecipe;
    private IngredientsAdapter mAdapter;
    private RecyclerView mRecyclerView;

    public IngredientsListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mRecipe = (Recipe) getActivity().getIntent().getSerializableExtra(EXTRA_RECIPE);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ingredients_list, container, false);
        IngredientsAdapter mAdapter = new IngredientsAdapter(view.getContext(), mRecipe);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        mRecyclerView = view.findViewById(R.id.recyclerViewIngredients);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        return view;

    }

}
