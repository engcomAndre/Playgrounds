package com.andre.tasks.viewholder;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.andre.tasks.R;
import com.andre.tasks.constants.PriorityCacheConstants;
import com.andre.tasks.entities.TaskEntity;
import com.andre.tasks.entities.listener.TaskListInteractionListener;

import java.text.SimpleDateFormat;

public class TaskViewHolder extends RecyclerView.ViewHolder {


    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    private TextView mTextDescription;
    private TextView mTextPriorityId;
    private TextView mTextPriority;
    private TextView mTextDueDate;
    private ImageView mImageTask;
    private Context mContext;

    public TaskViewHolder(View itemView) {
        super(itemView);
        this.mTextDescription = itemView.findViewById(R.id.text_description);
        this.mTextPriorityId = itemView.findViewById(R.id.text_priority_id);
        this.mTextPriority = itemView.findViewById(R.id.text_priority);
        this.mTextDueDate = itemView.findViewById(R.id.text_due_date);
        this.mImageTask = itemView.findViewById(R.id.image_task);
        this.mContext = itemView.getContext();
    }

    public void bindData(final TaskEntity entity, final TaskListInteractionListener listener) {
        this.mTextDescription.setText(entity.Description);
        this.mTextPriorityId.setText(String.valueOf(entity.PriorityId));
        this.mTextDueDate.setText(SIMPLE_DATE_FORMAT.format(entity.DueDate));
        this.mTextPriority.setText(PriorityCacheConstants.get(entity.PriorityId));

        if (entity.Complete) {
            this.mImageTask.setImageResource(R.drawable.ic_done);
            this.mTextDescription.setTextColor(Color.GRAY);
        }

        this.mTextDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onListClick(entity.Id);

            }
        });

        this.mTextDescription.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showConfirmationDialog(listener, entity);
                return true;
            }
        });

        this.mImageTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (entity.Complete) {
                    listener.onUncompleteClick(entity.Id);
                } else {
                    listener.onCompleteClick(entity.Id);
                }
            }
        });
    }

    private void showConfirmationDialog(final TaskListInteractionListener listener, final TaskEntity entity) {
        new AlertDialog.Builder(this.mContext)
                .setTitle(R.string.remocao_de_tarefa)
                .setMessage("Deseja realmente remover " + entity.Description + " ?")
                .setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        listener.onDeleteClick(entity.Id);
                    }

                })
                .setNegativeButton(R.string.cancelar, null).show();
    }
}
