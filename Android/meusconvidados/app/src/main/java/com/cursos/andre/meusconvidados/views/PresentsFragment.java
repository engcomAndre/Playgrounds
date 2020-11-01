package com.cursos.andre.meusconvidados.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cursos.andre.meusconvidados.R;
import com.cursos.andre.meusconvidados.adapter.GuestListAdapter;
import com.cursos.andre.meusconvidados.business.GuestBusiness;
import com.cursos.andre.meusconvidados.constants.GuestConstants;
import com.cursos.andre.meusconvidados.entities.GuestEntity;
import com.cursos.andre.meusconvidados.listener.OnGuestInteractionListener;

import java.util.List;

public class PresentsFragment extends Fragment {

    private ViewHolder mViewHolder = new ViewHolder();
    private GuestBusiness mGuestBusiness;
    private OnGuestInteractionListener mOnGuestInteractionListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_presents, container, false);
        final Context context = view.getContext();

        this.mViewHolder.mRecyclerAllPresents= view.findViewById(R.id.recycler_all_presents);

        this.mGuestBusiness = new GuestBusiness(context);

        this.mOnGuestInteractionListener = new OnGuestInteractionListener() {
            @Override
            public void onListClick(int id) {
                Bundle bundle = new Bundle();
                bundle.putInt(GuestConstants.BundleConstants.GUEST_ID,id);

                Intent intent = new Intent(context,GuestFormActivity.class);

                intent.putExtras(bundle);

                startActivity(intent);
            }

            @Override
            public void onDeleteClick(int id) {
                mGuestBusiness.remove(id);

                Toast.makeText(getContext(),getString(R.string.removido_sucesso),Toast.LENGTH_LONG).show();
                loadGuests();

            }
        };

        //definir layout
        this.mViewHolder.mRecyclerAllPresents.setLayoutManager(new LinearLayoutManager(context));

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        this.loadGuests();
    }

    private void loadGuests() {
        List<GuestEntity> guestEntityList = this.mGuestBusiness.getPresent();
        GuestListAdapter guestListAdapter = new GuestListAdapter(guestEntityList,this.mOnGuestInteractionListener);
        this.mViewHolder.mRecyclerAllPresents.setAdapter(guestListAdapter);
        guestListAdapter.notifyDataSetChanged();
    }

    private static class ViewHolder{
        RecyclerView mRecyclerAllPresents;
    }
}
