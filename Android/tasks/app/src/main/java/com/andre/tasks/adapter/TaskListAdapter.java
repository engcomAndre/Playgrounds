package com.andre.tasks.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andre.tasks.R;
import com.andre.tasks.entities.TaskEntity;
import com.andre.tasks.entities.listener.TaskListInteractionListener;
import com.andre.tasks.viewholder.TaskViewHolder;

import java.util.List;

public class TaskListAdapter extends RecyclerView.Adapter<TaskViewHolder> {

    private List<TaskEntity> mListTaskEntities;
    private TaskListInteractionListener mTaskListInteractionListener;

    /**
     * Construtor
     */
    public TaskListAdapter(List<TaskEntity> taskList, TaskListInteractionListener listener) {
        this.mListTaskEntities = taskList;
        this.mTaskListInteractionListener = listener;
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();

        // Infla o layout da linha e faz uso na listagem
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_task_list, parent, false);

        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        TaskEntity entity = this.mListTaskEntities.get(position);
        holder.bindData(entity,this.mTaskListInteractionListener);
    }

    @Override
    public int getItemCount() {
        return mListTaskEntities.size();
    }

}
