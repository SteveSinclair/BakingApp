package com.example.android.bakingapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.entities.Recipe;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {
    private static final String TAG = RecipeAdapter.class.getSimpleName();

    private final Context mContext;
    private final List<Recipe> mRecipeList;
    private final RecipeAdapterOnClickHandler mClickHandler;

    public RecipeAdapter(Context mContext, List<Recipe> mRecipeList, RecipeAdapterOnClickHandler mClickHandler) {
        this.mContext = mContext;
        this.mRecipeList = mRecipeList;
        this.mClickHandler = mClickHandler;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.list_item_recipe, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Recipe recipe = mRecipeList.get(position);
        holder.textViewName.setText(recipe.getName().toString());
        holder.textViewServings.setText("serves " + recipe.getServings() + " people");
    }

    @Override
    public int getItemCount() {
        return mRecipeList.size();
    }

    public interface RecipeAdapterOnClickHandler {
        void OnClick(Recipe recipe);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final TextView textViewName = itemView.findViewById(R.id.textViewName);
        final TextView textViewServings = itemView.findViewById(R.id.textViewServings);

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int pos = getAdapterPosition();
            Recipe recipe = mRecipeList.get(pos);
            mClickHandler.OnClick(recipe);

        }
    }
}
