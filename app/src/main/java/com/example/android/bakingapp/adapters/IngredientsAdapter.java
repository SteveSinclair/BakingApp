package com.example.android.bakingapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.entities.Ingredient;
import com.example.android.bakingapp.entities.Recipe;

import java.text.DecimalFormat;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.ViewHolder> {
    private static final String TAG = IngredientsAdapter.class.getSimpleName();
    private final Context mContext;
    private final Recipe mRecipe;

    public IngredientsAdapter(Context context, Recipe recipe) {
        mContext = context;
        mRecipe = recipe;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.list_item_ingredient, null);
        return new IngredientsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ingredient ingredient = mRecipe.ingerdients.get(position);
        DecimalFormat formatter = new DecimalFormat("0.###");
        holder.textViewIngredient.setText(formatter.format(ingredient.getQuantity()) + " " + ingredient.getMeasure() + " " + ingredient.getIngredient());
    }

    @Override
    public int getItemCount() {
        return mRecipe.ingerdients.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewIngredient;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewIngredient = itemView.findViewById(R.id.textViewIngredient);

        }
    }
}
