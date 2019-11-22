package com.towhid.recyclerinrecycler;

import android.app.LauncherActivity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.HashMap;
import java.util.List;

public class ParentAdapter extends RecyclerView.Adapter<ParentAdapter.ViewHolder> {
    HashMap<Integer, Boolean> checkHash;
    Context mcontext;
    List<ParentModel> mData;
    View view;
    RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();

    ParentAdapter(Context mcontext, List<ParentModel> mData) {
        this.mcontext = mcontext;
        this.mData = mData;
        checkHash = new HashMap<Integer, Boolean>();


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(mcontext).inflate(R.layout.parent_recycler, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.textView.setText(mData.get(position).getTitle());
        holder.rv_child.setLayoutManager(new LinearLayoutManager(holder.rv_child.getContext()));
        holder.rv_child.setAdapter(new ChildAdapter(holder.rv_child.getContext(), mData.get(position).getChildModels()));


        if (checkHash.containsKey(position)) {
            if (checkHash.get(position)) {
                holder.expandable_layout.setVisibility(View.VISIBLE);
            } else {
                holder.expandable_layout.setVisibility(View.GONE);

            }
        } else {
            holder.expandable_layout.setVisibility(View.GONE);

        }


        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (holder.expandable_layout.getVisibility() == View.VISIBLE) {
                    holder.expandable_layout.setVisibility(View.GONE);
                    checkHash.put(position, false);
                } else {
                    holder.expandable_layout.setVisibility(View.VISIBLE);

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

        private TextView textView;
        private RecyclerView rv_child;
        LinearLayout expandable_layout;


        ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);
            rv_child = (RecyclerView) itemView.findViewById(R.id.rv_child);
            expandable_layout = (LinearLayout) itemView.findViewById(R.id.expandable_layout);
        }
    }


}
