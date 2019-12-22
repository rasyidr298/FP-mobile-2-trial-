package com.coswick.travelinktrial.dao;

import android.content.Context;

import com.coswick.travelinktrial.model.TicketModel;


/**
 * Created by Lucas Albuquerque on 09/03/2018.
 */

public class ClienteDAO extends GenericDAO<TicketModel> {

    public ClienteDAO(Context context) {
        super(context, TicketModel.class);
    }
}
