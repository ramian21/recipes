package com.example.ramon.recipes;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ramon.recipes.data.RecipeContract;

/**
 * Created by Ramon on 3/22/2018.
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.TitleViewHolder> {
    private final ListItemClickListener mOnClickListener;
    private Cursor mCursor;
    private Context mContext;


    public RecipeAdapter(ListItemClickListener listener, Context context) {
        mOnClickListener = listener;
        mContext = context;
    }

    @Override
    public TitleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.recipe_list_item, parent, false);

        TitleViewHolder viewHolder = new TitleViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TitleViewHolder holder, int position) {
        int idIndex = mCursor.getColumnIndex(RecipeContract.RecipeEntry._ID);
        int titleIndex = mCursor.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_TITLE);

        mCursor.moveToPosition(position);

        final int id = mCursor.getInt(idIndex);
        String title = mCursor.getString(titleIndex);

        holder.itemView.setTag(id);
        holder.titleItemView.setText(title);
    }

    @Override
    public int getItemCount() {
        if (mCursor == null) return 0;
        return mCursor.getCount();
    }

    public void swapCursor(Cursor c) {
        mCursor = c;
        notifyDataSetChanged();
    }

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    class TitleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //TextView that displays the title of the Recipe
        TextView titleItemView;

        public TitleViewHolder(View itemView) {
            super(itemView);
            titleItemView = (TextView) itemView.findViewById(R.id.tv_recipe_title);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }
    }
}
