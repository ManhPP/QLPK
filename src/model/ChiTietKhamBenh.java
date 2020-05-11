/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author manhpp
 */
public class ChiTietKhamBenh {
    private int IDCT;
    private int IDBN;
    private String chuanDoanBanDau, chuanDoanSauXetNghiem;
    private Date ngayKham;

    public ChiTietKhamBenh(){
        
    }
    
    public ChiTietKhamBenh(int id, int idBN, String chanDoanBanDau, String chanDoanSauXetNghiem, Date ngayKham) {
        this.IDCT = id;
        this.IDBN = idBN;
        this.chuanDoanBanDau = chanDoanBanDau;
        this.chuanDoanSauXetNghiem = chanDoanSauXetNghiem;
        this.ngayKham = ngayKham;
    }
    
    public int getIDCT() {
        return IDCT;
    }

    public void setIDCT(int id) {
        this.IDCT = id;
    }

    public int getIDBN() {
        return IDBN;
    }

    public void setIDBN(int idBN) {
        this.IDBN = idBN;
    }

    public String getChuanDoanBanDau() {
        return chuanDoanBanDau;
    }

    public void setChuanDoanBanDau(String chanDoanBanDau) {
        this.chuanDoanBanDau = chanDoanBanDau;
    }

    public String getChuanDoanSauXetNghiem() {
        return chuanDoanSauXetNghiem;
    }

    public void setChuanDoanSauXetNghiem(String chanDoanSauXetNghiem) {
        this.chuanDoanSauXetNghiem = chanDoanSauXetNghiem;
    }

    public Date getNgayKham() {
        return ngayKham;
    }

    public void setNgayKham(Date ngayKham) {
        this.ngayKham = ngayKham;
    }
    
    
}
