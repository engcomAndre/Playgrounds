package com.cursos.andre.meusconvidados.viewholder;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cursos.andre.meusconvidados.R;
import com.cursos.andre.meusconvidados.entities.GuestEntity;
import com.cursos.andre.meusconvidados.listener.OnGuestInteractionListener;

public class GuestViewHolder extends RecyclerView.ViewHolder {

    private TextView mTextname;
    private Context mContext;
    private ImageView mImageRemove;

    public GuestViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        this.mTextname = itemView.findViewById(R.id.text_name);
        this.mContext = context;
        this.mImageRemove = itemView.findViewById(R.id.image_remove);
    }

    public void bindData(final GuestEntity guestEntity, final OnGuestInteractionListener listener) {
        this.mTextname.setText(guestEntity.getName());

        this.mTextname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onListClick(guestEntity.getId());
            }
        });

        this.mTextname.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                new AlertDialog.Builder(mContext)
                        .setTitle(R.string.remocao_convidado)
                        .setMessage(R.string.deseja_remover)
                        .setIcon(R.drawable.remove )
                        .setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                listener.onDeleteClick(guestEntity.getId());
                            }
                        })
                        .setNeutralButton(R.string.nao, null)
                        .show();
                return true;
            }
        });


        this.mImageRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(mContext)
                        .setTitle(R.string.remocao_convidado)
                        .setMessage(R.string.deseja_remover)
                        .setIcon(R.drawable.remove)
                        .setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                listener.onDeleteClick(guestEntity.getId());
                            }
                        })
                        .setNeutralButton(R.string.nao, null)
                        .show();

            }
        });
    }
}
