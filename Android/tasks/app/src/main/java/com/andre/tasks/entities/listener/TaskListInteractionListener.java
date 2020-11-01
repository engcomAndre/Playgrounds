package com.andre.tasks.entities.listener;

public interface TaskListInteractionListener {

    void onListClick(int id);

    void onCompleteClick(int id);

    void onUncompleteClick(int id);

    void onDeleteClick(int id);
}
