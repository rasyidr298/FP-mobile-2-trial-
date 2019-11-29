package com.coswick.travelinktrial.dao;

import android.content.Context;

import com.coswick.travelinktrial.model.ClientModel;


/**
 * Created by Lucas Albuquerque on 09/03/2018.
 */

public class ClienteDAO extends GenericDAO<ClientModel> {

    public ClienteDAO(Context context) {
        super(context, ClientModel.class);
    }
}
