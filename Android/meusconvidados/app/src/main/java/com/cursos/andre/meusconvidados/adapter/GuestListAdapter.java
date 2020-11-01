package com.cursos.andre.meusconvidados.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cursos.andre.meusconvidados.R;
import com.cursos.andre.meusconvidados.entities.GuestEntity;
import com.cursos.andre.meusconvidados.listener.OnGuestInteractionListener;
import com.cursos.andre.meusconvidados.viewholder.GuestViewHolder;

import java.util.List;

public class GuestListAdapter extends RecyclerView.Adapter<GuestViewHolder> {

    List<GuestEntity> mGuestEntitiesList;
    OnGuestInteractionListener mOnGuestInteractionListener;

    public GuestListAdapter(List<GuestEntity> guestEntities,OnGuestInteractionListener listener) {
        this.mGuestEntitiesList = guestEntities;
        this.mOnGuestInteractionListener= listener;
    }

    @NonNull
    @Override
    public GuestViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType) {
        Context context  = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.row_guest_list,parent,false);

        return new GuestViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull GuestViewHolder holder,int position) {
        GuestEntity guestEntity = this.mGuestEntitiesList.get(position);
        holder.bindData(guestEntity,mOnGuestInteractionListener);

    }

    @Override
    public int getItemCount() {
        return this.mGuestEntitiesList.size();
    }
}
