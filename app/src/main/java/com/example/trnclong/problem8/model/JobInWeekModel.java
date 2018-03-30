package com.example.trnclong.problem8.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by TRẦN ĐỨC LONG on 3/3/2018.
 */

public class JobInWeekModel {
    private String tenCongViec;
    private String theLoaiCongViec;
    private String noiDungCongViec;
    private Date ngayHT;
    private Date gioHT;

    public JobInWeekModel() {
    }


    public JobInWeekModel(String tenCongViec, String theLoaiCongViec, String noiDungCongViec, Date ngayHT, Date gioHT) {
        this.tenCongViec = tenCongViec;
        this.theLoaiCongViec = theLoaiCongViec;

        this.noiDungCongViec = noiDungCongViec;
        this.ngayHT = ngayHT;
        this.gioHT = gioHT;
    }

    public String getTenCongViec() {
        return tenCongViec;
    }

    public void setTenCongViec(String tenCongViec) {
        this.tenCongViec = tenCongViec;
    }

    public String getTheLoaiCongViec() {
        return theLoaiCongViec;
    }

    public void setTheLoaiCongViec(String theLoaiCongViec) {
        this.theLoaiCongViec = theLoaiCongViec;
    }
    public String getNoiDungCongViec() {
        return noiDungCongViec;
    }

    public void setNoiDungCongViec(String noiDungCongViec) {
        this.noiDungCongViec = noiDungCongViec;
    }

    public Date getNgayHT() {
        return ngayHT;
    }

    public void setNgayHT(Date ngayHT) {
        this.ngayHT = ngayHT;
    }

    public Date getGioHT() {
        return gioHT;
    }

    public void setGioHT(Date gioHT) {
        this.gioHT = gioHT;
    }

    public String getDateFormat(Date d)
    {
        SimpleDateFormat dft=new SimpleDateFormat("dd/MM/yyyy");
        return dft.format(d);
    }

    public String getHourFormat(Date d)
    {
        SimpleDateFormat dft=new SimpleDateFormat("hh:mm a");
        return dft.format(d);
    }
    @Override
    public String toString() {
        return this.tenCongViec+" - "+  this.theLoaiCongViec+" - "+ getDateFormat(this.ngayHT)+" - "+ getHourFormat(this.gioHT);
    }

}


