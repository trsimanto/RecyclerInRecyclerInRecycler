package com.towhid.recyclerinrecycler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.ViewHolder> {

    Context mcontext;
    List<ChildModel> mData;
    HashMap<Integer, Boolean> checkHash;


    ChildAdapter(Context mcontext, List<ChildModel> mData) {
        this.mcontext = mcontext;
        this.mData = mData;
        checkHash = new HashMap<Integer, Boolean>();


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.child_recycler, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.child_textView.setText(mData.get(position).getTitle());
        holder.rv_baby.setLayoutManager(new LinearLayoutManager(holder.rv_baby.getContext()));
        holder.rv_baby.setAdapter(new BabyAdapter(holder.rv_baby.getContext(), mData.get(position).getBabyModels()));


        if (checkHash.containsKey(position)) {
            if (checkHash.get(position)) {
                holder.expandable_layout_child.setVisibility(View.VISIBLE);
            } else {
                holder.expandable_layout_child.setVisibility(View.GONE);

            }
        } else {
            holder.expandable_layout_child.setVisibility(View.GONE);

        }


        holder.child_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (holder.expandable_layout_child.getVisibility() == View.VISIBLE) {
                    holder.expandable_layout_child.setVisibility(View.GONE);
                    checkHash.put(position, false);
                } else {
                    holder.expandable_layout_child.setVisibility(View.VISIBLE);

                    checkHash.put(position, true);
                }


            }
        });


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView child_textView;
        private RecyclerView rv_baby;
        LinearLayout expandable_layout_child;

        ViewHolder(View itemView) {
            super(itemView);
            child_textView = (TextView) itemView.findViewById(R.id.child_textView);
            rv_baby = (RecyclerView) itemView.findViewById(R.id.rv_baby);
            expandable_layout_child = (LinearLayout) itemView.findViewById(R.id.expandable_layout_child);

        }
    }


}
