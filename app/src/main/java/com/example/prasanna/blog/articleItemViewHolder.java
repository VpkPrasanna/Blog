package com.example.prasanna.blog;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by prasanna on 8/3/18.
 */

public class articleItemViewHolder extends RecyclerView.ViewHolder{
    TextView articleName;
    CardView cardView;

    public articleItemViewHolder(View itemView) {
        super(itemView);
        articleName = (TextView)itemView.findViewById(R.id.articleName);
        cardView = (CardView)itemView.findViewById(R.id.cardView);
    }
}
