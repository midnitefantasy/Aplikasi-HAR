package com.example.har.Adapter;

import android.content.Context;

import com.example.har.Model.DataModel;

import java.util.List;

public class AdapterData {
    private Context ctx;
    private List<DataModel> listUser;
    public AdapterData(Context ctx, List<DataModel> listUser){
        this.ctx = ctx;
        this.listUser = listUser;

    }
}
