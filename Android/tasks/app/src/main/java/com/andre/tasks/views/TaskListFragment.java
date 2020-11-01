package com.andre.tasks.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.andre.tasks.R;
import com.andre.tasks.adapter.TaskListAdapter;
import com.andre.tasks.constants.TaskConstants;
import com.andre.tasks.entities.TaskEntity;
import com.andre.tasks.entities.listener.TaskListInteractionListener;
import com.andre.tasks.infra.OperationListener;
import com.andre.tasks.manager.TaskManager;

import java.util.ArrayList;
import java.util.List;

public class TaskListFragment extends Fragment implements View.OnClickListener {

    private Context mContext;
    private boolean mMenuVisible = false;
    private TaskListInteractionListener mTaskListInteractionListener;
    private int mFilter;
    private List<TaskEntity> mTaskEntityList;
    private TaskListAdapter mTaskListAdapter;
    private ViewHolder mViewHolder = new ViewHolder();
    private TaskManager mTaskManager;

    public static TaskListFragment newInstance(int filter) {
        TaskListFragment fragment = new TaskListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(TaskConstants.TASK_FILTERS.KEY, filter);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            this.mFilter = getArguments().getInt(TaskConstants.TASK_FILTERS.KEY);
        }
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Infla o layout
        View rootView = inflater.inflate(R.layout.fragment_task_list, container, false);

        // Incializa as variáveis
        this.mContext = rootView.getContext();
        this.mTaskManager = new TaskManager(this.mContext);

        // Inicializa elementos
        this.mViewHolder.floatAddTask = rootView.findViewById(R.id.float_add_task);
        this.mViewHolder.floatMenuButton = rootView.findViewById(R.id.float_menu_button);

        this.mTaskListInteractionListener = new TaskListInteractionListener() {
            @Override
            public void onListClick(int id) {
                Bundle bundle = new Bundle();
                bundle.putInt(TaskConstants.BUNDLE.TASK_ID, id);

                Intent intent = new Intent(mContext, TaskFormActivity.class);
                intent.putExtras(bundle);

                startActivity(intent);
            }

            @Override
            public void onCompleteClick(int id) {
                mTaskManager.complete(id, true, taskUpdatedListener());
            }

            @Override
            public void onUncompleteClick(int id) {
                mTaskManager.complete(id, false, taskUpdatedListener());
            }

            @Override
            public void onDeleteClick(int id) {
                mTaskManager.delete(id, taskDeletedListener());
            }
        };

        // Inicializa eventos
        this.mViewHolder.floatAddTask.setOnClickListener(this);
        this.mViewHolder.floatMenuButton.setOnClickListener(this);

        // 1 - Obter a recyclerview
        this.mViewHolder.recylerTaskList = (RecyclerView) rootView.findViewById(R.id.recycler_task_list);

        // 2 - Definir adapter passando listagem de itens
        this.mTaskEntityList = new ArrayList<>();
        this.mTaskListAdapter = new TaskListAdapter(this.mTaskEntityList, this.mTaskListInteractionListener);
        this.mViewHolder.recylerTaskList.setAdapter(mTaskListAdapter);

        // 3 - Definir um layout
        this.mViewHolder.recylerTaskList.setLayoutManager(new LinearLayoutManager(this.mContext));

        return rootView;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.float_add_task:{
                startActivity(new Intent(this.mContext, TaskFormActivity.class));

            }
            break;
            case R.id.float_menu_button:{
                if(this.mMenuVisible){
                    Animation animationHide = AnimationUtils.loadAnimation(this.mContext,R.anim.float_menu_hide);
                    this.mViewHolder.floatAddTask.startAnimation(animationHide);

                    this.mViewHolder.floatAddTask.setVisibility(View.GONE);

                    this.mMenuVisible = false;

                }
                else {
                    Animation animationShow = AnimationUtils.loadAnimation(this.mContext,R.anim.float_menu_show);
                    this.mViewHolder.floatAddTask.startAnimation(animationShow);

                    this.mViewHolder.floatAddTask.setVisibility(View.VISIBLE);

                    this.mMenuVisible = true;
                }

            }
            break;
        }

    }

    private OperationListener taskUpdatedListener() {
        return new OperationListener<Boolean>() {
            @Override
            public void onSucess(Boolean result) {
                mTaskEntityList = new ArrayList<>();
                getTasks();

            }

            @Override
            public void onError(int errorCode, String errorMessage) {
                Toast.makeText(mContext, errorMessage, Toast.LENGTH_LONG).show();
            }
        };
    }

    private OperationListener taskDeletedListener() {
        return new OperationListener<Boolean>() {
            @Override
            public void onSucess(Boolean result) {
                mTaskEntityList = new ArrayList<>();
                Toast.makeText(mContext, R.string.tarefa_removida_com_sucesso, Toast.LENGTH_SHORT).show();
                getTasks();
            }

            @Override
            public void onError(int errorCode, String errorMessage) {
                Toast.makeText(mContext, errorMessage, Toast.LENGTH_SHORT).show();
            }
        };

    }

    @Override
    public void onResume() {
        super.onResume();
        this.mTaskEntityList = new ArrayList<>();
        this.getTasks();


    }


    public void getTasks() {
        this.mTaskManager.getList(mFilter, taskLoadedListener());
    }


    private OperationListener taskLoadedListener() {
        return new OperationListener<List<TaskEntity>>() {
            @Override
            public void onSucess(List<TaskEntity> result) {
                mTaskEntityList.addAll(result);
                mTaskListAdapter = new TaskListAdapter(mTaskEntityList, mTaskListInteractionListener);
                mViewHolder.recylerTaskList.setAdapter(mTaskListAdapter);
                mTaskListAdapter.notifyDataSetChanged();

                int completeCount = 0;

                for (TaskEntity entity : result) {
                    if (entity.Complete) {
                        completeCount++;
                    }
                }

                ((MainActivity) getActivity()).updateTasksCount(completeCount, result.size());

            }

            @Override
            public void onError(int errorCode, String errorMessage) {
                Toast.makeText(mContext, errorMessage, Toast.LENGTH_LONG).show();
            }
        };

    }

    /**
     * ViewHolder
     */
    private static class ViewHolder {
        private FloatingActionButton floatAddTask;
        private FloatingActionButton floatMenuButton;
        private RecyclerView recylerTaskList;
    }
}
