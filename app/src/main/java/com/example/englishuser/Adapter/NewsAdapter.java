package com.example.englishuser.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andview.refreshview.recyclerview.BaseRecyclerAdapter;
import com.example.englishuser.Bean.NewsModel;
import com.example.englishuser.R;
import com.example.englishuser.activity.DetailActivity;
import com.example.englishuser.activity.NewsActivity;
import com.example.englishuser.util.DensityUtil;

import java.util.List;

public class NewsAdapter extends BaseRecyclerAdapter<NewsAdapter.QuestionAdapterViewHolder> {

    private List<NewsModel.Results> list;
    private int largeCardHeight, smallCardHeight;
    private Context context;

    public NewsAdapter(List<NewsModel.Results> list, Context context) {
        this.list = list;
        this.context = context;
        largeCardHeight = DensityUtil.dip2px(context, 150);
        smallCardHeight = DensityUtil.dip2px(context, 200);
    }
    @Override
    public void onBindViewHolder(final QuestionAdapterViewHolder holder, final int position, boolean isItem) {
        holder.questionTV.setText(list.get(position) == null ? "" : list.get(position).getWebTitle());
        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, NewsActivity.class);
                intent.putExtra("url", list.get(position).getWebUrl());
//                intent.putExtra("title", holder.questionTV.getText());
                context.startActivity(intent);
            }
        });
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        if (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
            holder.rootView.getLayoutParams().height = position % 2 != 0 ? largeCardHeight : smallCardHeight;
        }
    }

    @Override
    public int getAdapterItemViewType(int position) {
        return 0;
    }

    @Override
    public int getAdapterItemCount() {
        return list.size();
    }

    @Override
    public QuestionAdapterViewHolder getViewHolder(View view) {
        return new QuestionAdapterViewHolder(view, false);
    }

    public void setData(List<NewsModel.Results> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public QuestionAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType, boolean isItem) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.question_item_recylerview, parent, false);
        QuestionAdapterViewHolder vh = new QuestionAdapterViewHolder(v, true);
        return vh;
    }


    public class QuestionAdapterViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView questionTV;

        public QuestionAdapterViewHolder(View itemView, boolean isItem) {
            super(itemView);
            if (isItem) {
                rootView = itemView.findViewById(R.id.question_card_view);
                questionTV = itemView.findViewById(R.id.recycler_view_question_tv);
            }
        }
    }

    public NewsModel.Results getItem(int position) {
        if (position < list.size())
            return list.get(position);
        else
            return null;
    }

}