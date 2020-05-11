/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author manhpp
 */
public class DichVu {
    private int ID;
    private String ten;
    private int gia;

    public DichVu(){
        
    }
    
    public DichVu(int id, String ten, int gia) {
        this.ID = id;
        this.ten = ten;
        this.gia = gia;
    }

    public int getID() {
        return ID;
    }

    public void setID(int id) {
        this.ID = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

   
}
