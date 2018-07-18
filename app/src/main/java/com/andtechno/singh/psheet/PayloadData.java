package com.andtechno.singh.psheet;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PayloadData {

    @SerializedName("TABLE_DATA")
    @Expose
    public TableData tableData;

}