package com.coswick.travelinktrial.db_crud_ormite;

import android.content.Context;

import com.coswick.travelinktrial.model.TicketModel;

public class ClienteDAO extends GenericDAO<TicketModel> {

    public ClienteDAO(Context context) {
        super(context, TicketModel.class);
    }
}
