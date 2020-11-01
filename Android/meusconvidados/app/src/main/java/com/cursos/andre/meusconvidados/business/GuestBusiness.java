package com.cursos.andre.meusconvidados.business;

import android.content.Context;

import com.cursos.andre.meusconvidados.constants.DatabaseContants;
import com.cursos.andre.meusconvidados.constants.GuestConstants;
import com.cursos.andre.meusconvidados.entities.GuestCount;
import com.cursos.andre.meusconvidados.entities.GuestEntity;
import com.cursos.andre.meusconvidados.repository.GuestRepository;

import java.util.List;

public class GuestBusiness {
    private GuestRepository mGuestRepository;


    public GuestBusiness(Context context) {
        this.mGuestRepository = GuestRepository.getInstance(context);
    }


    public GuestEntity load(int mGuestID) {
        return this.mGuestRepository.load(mGuestID);
    }

    public Boolean insert(GuestEntity guestEntity) {
        return this.mGuestRepository.insert(guestEntity);
    }

    public boolean update(GuestEntity guestEntity) {
        return this.mGuestRepository.update(guestEntity);

    }
    public Boolean remove(int id) {
        return this.mGuestRepository.remove(id);

    }

    public List<GuestEntity> getInvited() {
        return this.mGuestRepository.getGuestsByQuery("select * from " + DatabaseContants.GUEST.TABLE_NAME);
    }


    public List<GuestEntity> getAbsent() {
        return this.mGuestRepository.getGuestsByQuery("select * from " +
                DatabaseContants.GUEST.TABLE_NAME +
                " where " +
                DatabaseContants.GUEST.COLUMNS.PRESENCE + " = " + GuestConstants.CONFIRMATION.ABSENT);
    }

    public List<GuestEntity> getPresent() {
        return this.mGuestRepository.getGuestsByQuery("select * from " +
                DatabaseContants.GUEST.TABLE_NAME +
                " where " +
                DatabaseContants.GUEST.COLUMNS.PRESENCE + " = " + GuestConstants.CONFIRMATION.PRESENT);
    }

    public GuestCount loadDashBoard() {
        return this.mGuestRepository.loadDashBoard();

    }
}
