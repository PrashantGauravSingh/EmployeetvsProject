package com.andtechno.singh.psheet;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class TableData {

    @SerializedName("data")
    @Expose
    public List<List<String>> dataList = new ArrayList<>();

}