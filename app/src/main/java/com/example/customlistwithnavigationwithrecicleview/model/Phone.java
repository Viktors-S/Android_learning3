package com.example.customlistwithnavigationwithrecicleview.model;

import androidx.annotation.NonNull;

public class Phone {
    @NonNull
    String mobile, home, office;

    public Phone(@NonNull String mobile, @NonNull String home, @NonNull String office){

        this.mobile = mobile;
        this.home = home;
        this.office = office;

    }

    @NonNull
    public String getMobile() {
        return mobile;
    }

    @NonNull
    public String getHome() {
        return home;
    }

    @NonNull
    public String getOffice() {
        return office;
    }
}
