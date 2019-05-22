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
import com.example.englishuser.util.DensityUtil;
import com.example.englishuser.activity.DetailActivity;
import com.example.englishuser.R;

import java.util.List;

public class SimpleAdapter extends BaseRecyclerAdapter<SimpleAdapter.QuestionAdapterViewHolder> {
//    private List<Person> list;
    private List<String> list;
    private int largeCardHeight, smallCardHeight;
    private Context context;

//    public SimpleAdapter(List<Person> list, Context context) {
//        this.list = list;
//        largeCardHeight = DensityUtil.dip2px(context, 150);
//        smallCardHeight = DensityUtil.dip2px(context, 100);
//    }

    public SimpleAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
        largeCardHeight = DensityUtil.dip2px(context, 150);
        smallCardHeight = DensityUtil.dip2px(context, 200);
    }
    @Override
    public void onBindViewHolder(final QuestionAdapterViewHolder holder, final int position, boolean isItem) {
        holder.questionTV.setText(list.get(position) == null ? "" : list.get(position));
        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("Id", position);
                intent.putExtra("title", holder.questionTV.getText());
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

    public void setData(List<String> list) {
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

    public void insert(String item, int position) {
        insert(list, item, position);
    }

    public void remove(int position) {
        remove(list, position);
    }

    public void clear() {
        clear(list);
    }

//    public class SimpleAdapterViewHolder extends RecyclerView.ViewHolder {
//
//        public View rootView;
//        public TextView nameTv;
//        public TextView ageTv;
//        public int position;
//
//        public SimpleAdapterViewHolder(View itemView, boolean isItem) {
//            super(itemView);
//            if (isItem) {
//                nameTv = (TextView) itemView
//                        .findViewById(R.id.recycler_view_test_item_person_name_tv);
//                ageTv = (TextView) itemView
//                        .findViewById(R.id.recycler_view_test_item_person_age_tv);
//                rootView = itemView
//                        .findViewById(R.id.card_view);
//            }
//
//        }
//    }

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

    public String getItem(int position) {
        if (position < list.size())
            return list.get(position);
        else
            return null;
    }

}