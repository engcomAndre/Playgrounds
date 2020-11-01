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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cursos.andre.meusconvidados.R;
import com.cursos.andre.meusconvidados.adapter.GuestListAdapter;
import com.cursos.andre.meusconvidados.business.GuestBusiness;
import com.cursos.andre.meusconvidados.constants.GuestConstants;
import com.cursos.andre.meusconvidados.entities.GuestCount;
import com.cursos.andre.meusconvidados.entities.GuestEntity;
import com.cursos.andre.meusconvidados.listener.OnGuestInteractionListener;

import java.util.List;

public class AllInvitedFragment extends Fragment {

    private ViewHolder mViewHolder = new ViewHolder();
    private GuestBusiness mGuestBusiness;
    private OnGuestInteractionListener mOnGuestInteractionListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_invited, container, false);
        final Context context = view.getContext();

        //definir recycler_view
        this.mViewHolder.mRecyclerAllInvited = view.findViewById(R.id.recycler_all_invited);
        this.mViewHolder.mTextPresentCount = view.findViewById(R.id.text_present_count);
        this.mViewHolder.mTextAbsentCount = view.findViewById(R.id.text_absent_count);
        this.mViewHolder.mTextAllInvitedCount = view.findViewById(R.id.text_all_invited_count);

        this.mGuestBusiness = new GuestBusiness(context);
        List<GuestEntity> guestEntityList = this.mGuestBusiness.getInvited();

        this.mOnGuestInteractionListener = new OnGuestInteractionListener() {
            @Override
            public void onListClick(int id) {
                Bundle bundle = new Bundle();
                bundle.putInt(GuestConstants.BundleConstants.GUEST_ID, id);

                Intent intent = new Intent(context, GuestFormActivity.class);

                intent.putExtras(bundle);

                startActivity(intent);
            }

            @Override
            public void onDeleteClick(int id) {
                mGuestBusiness.remove(id);

                Toast.makeText(getContext(),getString(R.string.removido_sucesso),Toast.LENGTH_LONG).show();
                loadDashBoard();
                loadGuests();
            }
        };



        //definir layout
        this.mViewHolder.mRecyclerAllInvited.setLayoutManager(new LinearLayoutManager(context));

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        this.loadDashBoard();

        this.loadGuests();
    }

    private void loadGuests() {
        List<GuestEntity> guestEntityList = this.mGuestBusiness.getInvited();
        GuestListAdapter guestListAdapter = new GuestListAdapter(guestEntityList, this.mOnGuestInteractionListener);
        this.mViewHolder.mRecyclerAllInvited.setAdapter(guestListAdapter);
        guestListAdapter.notifyDataSetChanged();
    }

    private void loadDashBoard() {
        GuestCount guestCount = this.mGuestBusiness.loadDashBoard();

        this.mViewHolder.mTextPresentCount.setText(String.valueOf(guestCount.getPresentCount()));
        this.mViewHolder.mTextAbsentCount.setText(String.valueOf(guestCount.getAbsentCount()));
        this.mViewHolder.mTextAllInvitedCount.setText(String.valueOf(guestCount.getAllInvitedCount()));
    }

    private static class ViewHolder {
        RecyclerView mRecyclerAllInvited;
        TextView mTextPresentCount;
        TextView mTextAbsentCount;
        TextView mTextAllInvitedCount;
    }
}


