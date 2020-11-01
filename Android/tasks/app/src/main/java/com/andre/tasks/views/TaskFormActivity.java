package com.andre.tasks.views;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.andre.tasks.R;
import com.andre.tasks.constants.TaskConstants;
import com.andre.tasks.entities.PriorityEntity;
import com.andre.tasks.entities.TaskEntity;
import com.andre.tasks.infra.OperationListener;
import com.andre.tasks.manager.PriorityManager;
import com.andre.tasks.manager.TaskManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TaskFormActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private Context mContext;
    private List<Integer> mListPriorityId;
    private PriorityManager mPriorityManager;
    private TaskManager mTaskManager;
    private List<PriorityEntity> mListPriorityEntity;
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    private int mTaskId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_form);

        this.mPriorityManager = new PriorityManager(this);
        this.mTaskManager = new TaskManager(this);
        this.mContext = this;
        this.mListPriorityId = new ArrayList<>();


        // Inicializa variáveis
        this.mViewHolder.editDescription = (EditText) this.findViewById(R.id.edit_description);
        this.mViewHolder.checkComplete = (CheckBox) this.findViewById(R.id.check_complete);
        this.mViewHolder.spinnerPriority = (Spinner) this.findViewById(R.id.spinner_priority);
        this.mViewHolder.buttonDate = (Button) this.findViewById(R.id.button_date);
        this.mViewHolder.buttonSave = (Button) this.findViewById(R.id.button_save);
        this.mViewHolder.imageBack = this.findViewById(R.id.image_toolbar_back);
        this.mViewHolder.textToolbar = this.findViewById(R.id.text_toobar);
        this.mViewHolder.progressDialog = new ProgressDialog(this);

        // Atribui eventos
        this.mViewHolder.buttonSave.setOnClickListener(this);
        this.mViewHolder.buttonDate.setOnClickListener(this);
        this.mViewHolder.imageBack.setOnClickListener(this);


        this.loadPriorities();
        this.loadDataFromActivity();

    }

    private void loadDataFromActivity() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            this.mTaskId = bundle.getInt(TaskConstants.BUNDLE.TASK_ID, 0);
            if (this.mTaskId != 0) {
                this.mViewHolder.textToolbar.setText(R.string.atualizar_tarefa);
                this.mViewHolder.buttonDate.setText(R.string.atualizar_tarefa_button);
                this.mTaskManager.get(this.mTaskId, taskLoadedListener());
            }
        }

    }

    private void loadPriorities() {
        this.mListPriorityEntity = this.mPriorityManager.getListLocal();

        List<String> lstDescription = new ArrayList<>();

        for (PriorityEntity entity : this.mListPriorityEntity) {
            lstDescription.add(entity.Description);
            this.mListPriorityId.add(entity.Id);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, lstDescription);
        this.mViewHolder.spinnerPriority.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.button_save: {
                this.handleSave();
            }
            break;
            case R.id.button_date: {
                this.showDatePicker();
            }
            break;
            case R.id.image_toolbar_back: {
                super.onBackPressed();
            }
            break;
        }

    }

    private void handleSave() {

        showLoading(true, getString(R.string.salvando), getString(R.string.salvando_tarefa));
        try {
            TaskEntity entity = new TaskEntity();

            entity.Id = this.mTaskId;
            entity.Description = this.mViewHolder.editDescription.getText().toString();
            entity.Complete = this.mViewHolder.checkComplete.isChecked();
            entity.PriorityId = this.mListPriorityId.get(this.mViewHolder.spinnerPriority.getSelectedItemPosition());

            if (!"".equals(this.mViewHolder.buttonDate.getText().toString())) {
                entity.DueDate = SIMPLE_DATE_FORMAT.parse(this.mViewHolder.buttonDate.getText().toString());
            }

            if (this.mTaskId == 0) {
                this.mTaskManager.insert(entity, taskSavedListener());
            } else {
                this.mTaskManager.update(entity, taskSavedListener());

            }
        } catch (Exception e) {
            Toast.makeText(this, R.string.UNEXPECTED_ERROR, Toast.LENGTH_LONG).show();
            showLoading(false, "", "");

        }
    }

    private void showLoading(Boolean show, String title, String message) {
        if (show) {
            this.mViewHolder.progressDialog.setTitle(title);
            this.mViewHolder.progressDialog.setMessage(message);
            this.mViewHolder.progressDialog.show();
        } else {
            this.mViewHolder.progressDialog.hide();
            this.mViewHolder.progressDialog.dismiss();
        }
    }

    private int getIndex(int priorityId) {
        int index = 0;
        for (int i = 0; i < this.mListPriorityEntity.size(); i++) {
            if (this.mListPriorityEntity.get(i).Id == priorityId) {
                index = i;
                break;
            }
        }
        return index;
    }

    private OperationListener taskLoadedListener() {
        return new OperationListener<TaskEntity>() {
            @Override
            public void onSucess(TaskEntity result) {
                mViewHolder.editDescription.setText(result.Description);
                mViewHolder.buttonDate.setText(SIMPLE_DATE_FORMAT.format(result.DueDate));
                mViewHolder.checkComplete.setChecked(result.Complete);
                mViewHolder.spinnerPriority.setSelection(getIndex(result.PriorityId));
            }

            @Override
            public void onError(int errorCode, String errorMessage) {
                Toast.makeText(mContext, errorMessage, Toast.LENGTH_LONG).show();
            }
        };

    }

    private OperationListener taskSavedListener() {
        return new OperationListener<Boolean>() {
            @Override
            public void onSucess(Boolean result) {
                if (mTaskId == 0) {
                    Toast.makeText(mContext, R.string.tarefa_incluida_com_sucesso, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(mContext, R.string.tarefa_atualizada_com_sucesso, Toast.LENGTH_LONG).show();
                }
                showLoading(true, getString(R.string.salvando), getString(R.string.salvando_tarefa));
                finish();
            }

            @Override
            public void onError(int errorCode, String errorMessage) {
                Toast.makeText(mContext, errorMessage, Toast.LENGTH_LONG).show();
                showLoading(true, getString(R.string.salvando), getString(R.string.salvando_tarefa));
            }
        };

    }


    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        new DatePickerDialog(this, this, year, month, dayOfMonth).show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        this.mViewHolder.buttonDate.setText(SIMPLE_DATE_FORMAT.format(calendar.getTime()));
    }

    /**
     * ViewHolder
     */
    private static class ViewHolder {
        private EditText editDescription;
        private CheckBox checkComplete;
        private Spinner spinnerPriority;
        private Button buttonDate;
        private Button buttonSave;
        private ProgressDialog progressDialog;
        private ImageView imageBack;
        private TextView textToolbar;
    }
}
