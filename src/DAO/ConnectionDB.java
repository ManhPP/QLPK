/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import com.itextpdf.text.DocumentException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.*;

/**
 *
 * @author Administrator
 */
public class ConnectionDB {

    Connection conn;

    public void connect() {
        conn = null;
        String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=OOP20181;user=sa;password=1234";
        try {
            conn = DriverManager.getConnection(dbURL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //check dang nhap
    public boolean checkAccount(String User, String Pass, String chucVu) {
        try {
            String sql = "select * from NhanVien where Username=? and Passsword=? and ChucVu=?";
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setString(1, User);
            pr.setString(2, Pass);
            pr.setString(3, chucVu);
            ResultSet r = pr.executeQuery();
            while (r.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    //cho giao dien bac si chuyen khoa
    //lay cac xet nghiem phai lam
    public List<String> getXetNghiemCanLam(int idct) {
        List list = new ArrayList<>();
        List<Integer> iddv;
        List<String> ten, kq;
        iddv = new ArrayList<>();
        kq = new ArrayList<>();
        ten = new ArrayList<>();
        try {
            PreparedStatement pr = conn.prepareStatement("select ct_dv.iddv, dv.ten, ct_dv.KetQua from ChiTietKham_DichVu ct_dv, DichVu dv where ct_dv.iddv = dv.id and ct_dv.idct=?");
            pr.setInt(1, idct);
            ResultSet rs = pr.executeQuery();

            while (rs.next()) {
                int id = Integer.parseInt(rs.getString(1));
                String t = rs.getString(2);
                String k = rs.getString(3);
                if (k != null) {
                    list.add(false);
                    return list;
                }
                iddv.add(id);
                ten.add(t);
                kq.add(k);
            }
        } catch (SQLException ex) {
        }
        list.add(true);
        list.add(iddv);
        list.add(ten);
        list.add(kq);
        return list;
    }

    //tt in phieu
    public List getTTInPhieu(int idct, List<Integer> listIDDV, List<String> kqXetNghiem) {
        List list = new ArrayList();
        ResultSet rs = null;
        BenhNhan bn = new BenhNhan();

        String ten = "", gioiTinh = "", ngaySinh = "", diaChi = "", SDT = "", CMT = "";
        String sql = "select * from BenhNhan where BenhNhan.ID in ( select IDBN from ChiTietKhamBenh where IDCT = ?)";
        String sql2 = "select DichVu.Ten, ChiTietKham_DichVu.KetQua from DichVu, ChiTietKham_DichVu where DichVu.ID = ChiTietKham_DichVu.IDDV and IDCT = ?";
        try {
            // thong tin benh nhan
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setInt(1, idct);
            rs = pr.executeQuery();
            while (rs.next()) {
                bn.setTen(rs.getString(2));
                bn.setGioiTinh(rs.getString(3));
                bn.setNgaySinh(rs.getDate(4));
                bn.setDiaChi(rs.getString(5));
                bn.setSDT(rs.getString(6));
                bn.setCMT(rs.getString(7));
            }
            // lay thon tin dich vu
            pr = conn.prepareStatement(sql2);
            pr.setInt(1, idct);
            list.add(bn);
            rs = pr.executeQuery();
            list.add(rs);
        } catch (SQLException e) {
            return list;
        }
        return list;
    }

    //them xet nghiem
    public boolean themXN(int idct, List<Integer> listIDDV, List<String> kqXetNghiem) {
        String sql = "update ChiTietKham_DichVu set KetQua=? where IDCT=? and IDDV=?";
        try {
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setInt(2, idct);
            for (int i = 0; i < listIDDV.size(); i++) {
                pr.setInt(3, listIDDV.get(i));
                pr.setString(1, kqXetNghiem.get(i));
                pr.execute();
            }
        } catch (SQLException e) {
            return false;
        }

        return true;
    }
    //=======================================================
    //phan cua manh

    public Thuoc timThuoc(int id) {
        String sql = "select * from Thuoc where ID = ?";
        PreparedStatement pst;
        ResultSet rs;
        Thuoc thuoc = null;

        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                thuoc = new Thuoc(rs.getInt("ID"), rs.getString("Ten"), 0, rs.getInt("Gia"), rs.getInt("SoLuongConLai"));
            }
        } catch (Exception ex) {

        }

        return thuoc;
    }

    //tim chi tiet kham benh
    public ChiTietKhamBenh timCT(int id) {
        String sql = "select * from ChiTietKhamBenh where IDCT = ?";
        PreparedStatement pst;
        ResultSet rs;
        ChiTietKhamBenh ct = null;

        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();

            while (rs.next()) {

                ct = new ChiTietKhamBenh(rs.getInt("IDCT"), rs.getInt("IDBN"), rs.getString("ChuanDoanBanDau"), rs.getString("ChuanDoanSauXetNghiem"), rs.getDate("NgayKham"));
            }

        } catch (Exception ex) {
            return null;
        }

        return ct;
    }

    //tim chi tiet kham benh vua them
    
    public int ChiTietKhamVuaThem(int idbn) throws SQLException{
        int idct;
        String sql="Select MAX(IDCT) from ChiTietKhamBenh where IDBN=?";
        PreparedStatement ps=conn.prepareStatement(sql);
        ps.setInt(1, idbn);
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
            idct=rs.getInt(1);
            return idct;
        }
        return 0;
    }
    
    //tim dich vu
    public DichVu timDichVu(int id) {
        String sql = "select * from DichVu where ID = ?";
        PreparedStatement pst;
        ResultSet rs;
        DichVu dv = null;

        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                dv = new DichVu(rs.getInt("ID"), rs.getString("Ten"), rs.getInt("Gia"));
                //System.out.println(rs.getString("Ten"));
            }

        } catch (Exception ex) {

        }

        return dv;
    }

    public ArrayList<DichVu> dsDichVu(int id) {
        ArrayList<DichVu> dsdv = new ArrayList<>();
        String sql = "select * from ChiTietKham_DichVu where IDCT = ?";

        ResultSet rs;

        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);

            rs = pst.executeQuery();

            while (rs.next()) {
                dsdv.add(this.timDichVu(rs.getInt("IDDV")));
            }
        } catch (Exception ex) {
            return null;
        }
        return dsdv;
    }

    public ArrayList<Thuoc> dsThuoc(int id) {
        ArrayList<Thuoc> dsThuoc = new ArrayList<>();
        String sql = "select * from ChiTietKhamBenh_Thuoc where IDCT = ?";
        PreparedStatement pst;
        ResultSet rs;

        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();

            while (rs.next()) {
                Thuoc t = this.timThuoc(rs.getInt("IDThuoc"));
                t.setSoLuong(rs.getInt("SoLuongVienThuoc"));
                dsThuoc.add(t);
            }
        } catch (Exception ex) {

        }
        return dsThuoc;
    }

    public void updateThuoc(int id, int soLuongConLai) {
        String sql = "update Thuoc set SoLuongConLai = ? where ID = ?";
        PreparedStatement pst;
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, soLuongConLai);
            pst.setInt(2, id);
            pst.execute();
        } catch (Exception ex) {

        }
    }

    public String timTen(int id) {
        String ten = null;
        ChiTietKhamBenh ct = timCT(id);
        String sql = "select Ten from BenhNhan where ID = ?";
        PreparedStatement pst;
        ResultSet rs;

        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, ct.getIDBN());
            rs = pst.executeQuery();

            while (rs.next()) {
                ten = rs.getString("Ten");
            }
        } catch (Exception ex) {

        }

        return ten;
    }
    //==========================================================
    //cua kien

    public List<ChiTietKhamBenh> timKiemHoSo(int idbn) {
        String sql = "select * from ChiTietKhamBenh where IDBN= ?";
        List<ChiTietKhamBenh> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idbn);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietKhamBenh chitiet = new ChiTietKhamBenh();
                chitiet.setIDBN(rs.getInt("IDBN"));
                chitiet.setIDCT(rs.getInt("IDCT"));
                chitiet.setChuanDoanBanDau(rs.getString("ChuanDoanBanDau"));
                chitiet.setChuanDoanSauXetNghiem(rs.getString("ChuanDoanSauXetNghiem"));
                chitiet.setNgayKham(rs.getDate("NgayKham"));

                list.add(chitiet);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public BenhNhan timBenhNhan(int idbn) {
        String sql = "select * from BenhNhan where ID=?";
        BenhNhan bn = null;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idbn);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                bn = new BenhNhan();
                bn.setID(rs.getInt(1));
                bn.setTen(rs.getString("Ten"));
                bn.setGioiTinh(rs.getString("GioiTinh"));
                bn.setNgaySinh(rs.getDate("NgaySinh"));
                bn.setDiaChi(rs.getString("DiaChi"));
                bn.setSDT(rs.getString("SDT"));
                bn.setCMT(rs.getString("CMT"));
                return bn;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bn;
    }

    public List<DichVu> danhSachDV() {
        String sql = "select * from DichVu";
        List<DichVu> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DichVu dv = new DichVu();
                dv.setID(rs.getInt(1));
                dv.setTen(rs.getString(2));
                dv.setGia(rs.getInt(3));

                list.add(dv);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean themChuanDoan(ChiTietKhamBenh chitiet, List<String> xetnghiem,int idbn) {
        try {
            String sql, sql2, sql3;
            List<Integer> IDDVArr = new ArrayList<>();
            sql = "select * from ChiTietKham_DichVu where IDCT = ?";
            PreparedStatement ps;
            ResultSet rs;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, chitiet.getIDCT());
            rs = ps.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Đã có trong cơ sở dữ liệu! Không thêm được...", "Warning", JOptionPane.WARNING_MESSAGE);
                return false;
            } else {
                sql = "INSERT INTO ChiTietKhamBenh(IDBN,ChuanDoanBanDau,NgayKham) VALUES(?,?,?)";
                ps = conn.prepareStatement(sql);

                ps.setInt(1, chitiet.getIDBN());
                ps.setString(2, chitiet.getChuanDoanBanDau());
                ps.setDate(3, chitiet.getNgayKham());
                //bien done chứa trạng thái việc thêm dữ liệu
                int done = 0;
                int a = ps.executeUpdate();
                if (a > 0) {
                    sql = "Select ID from DichVu where Ten =?";
                    sql2 = "INSERT INTO ChiTietKham_DichVu(IDCT,IDDV) VALUES(?,?)";
                    sql3 = "Select MAX(IDCT) From ChitietKhamBenh where IDBN=?";
                    PreparedStatement ps3 = conn.prepareStatement(sql3);
                    ps3.setInt(1, chitiet.getIDBN());
                    rs = ps3.executeQuery();
                    //tim idct ban ghi cuoi duoc them vao cho benh nhan
                    int maxIDCT = 0;
                    if (rs.next()) {
                        maxIDCT = rs.getInt(1);
                    }
                    ps = conn.prepareStatement(sql);
                    PreparedStatement ps2 = conn.prepareStatement(sql2);
                    for (String string : xetnghiem) {
                        ps.setString(1, string);
                        rs = ps.executeQuery();
                        if (rs.next()) {
                            //IDDVArr.add(rs.getInt(1));
                            //them truc tiep vao bang nối ChiTietKham_DichVu
                            ps2.setInt(1, maxIDCT);
                            ps2.setInt(2, rs.getInt(1));
                            int b = ps2.executeUpdate();
                            if (b > 0) {
                                done = 1;
                            } else {
                                JOptionPane.showMessageDialog(null, "Không thành công!", "Thông báo", JOptionPane.WARNING_MESSAGE);

                                return false;
                            }
                        }
                    }
                    if (done == 1) {
                        int maHoSo=ChiTietKhamVuaThem(idbn);
                        JOptionPane.showMessageDialog(null, "Thành công! Mã hồ sơ khám: "+maHoSo, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        
                        return true;
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Không thành công!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    //===================================================
    //cua tai
    public BenhNhan themBenhNhan(String ten, String gioitinh, Date ngaysinh, String diachi, String sdt, String cmt) {
        try {
            String sql = "select * from BenhNhan where CMT = ?";
            PreparedStatement ps;
            ResultSet rs;
            ps = conn.prepareStatement(sql);
            ps.setString(1, cmt);
            rs = ps.executeQuery();

            if (rs.next()) {
                BenhNhan benhnhan = new BenhNhan();
                benhnhan.setID(rs.getInt(1));
                benhnhan.setTen(rs.getString(2));
                benhnhan.setGioiTinh(rs.getString(3));
                benhnhan.setNgaySinh(rs.getDate(4));
                benhnhan.setDiaChi(rs.getString(5));
                benhnhan.setSDT(rs.getString(6));
                benhnhan.setCMT(rs.getString(7));
                return benhnhan;
            } else {
                sql = "INSERT INTO BenhNhan(Ten,GioiTinh,NgaySinh,DiaChi,SDT,CMT) VALUES(?,?,?,?,?,?)";
                ps = conn.prepareStatement(sql);

                ps.setString(1, ten);
                ps.setString(2, gioitinh);
                ps.setDate(3, ngaysinh);
                ps.setString(4, diachi);
                ps.setString(5, sdt);
                ps.setString(6, cmt);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public int getLastID() {
        int id = 0;
        try {
            String sql = "select Max(ID) from BenhNhan";
            PreparedStatement ps;
            ResultSet rs;
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
                return id;
            }
        } catch (Exception e) {

        }
        return id;
    }

    public boolean checkUser(String username, String pass, String type) {
        try {
            String sql = "select * from NhanVien where Username=? and Password=? and ChucVu=?";
            PreparedStatement ps;
            ResultSet rs;
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, pass);
            ps.setString(3, type);
            rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    //======================================================
    //cua a
    public List<Thuoc> danhSachThuoc() {
        String sql = "Select * from Thuoc";
        List<Thuoc> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Thuoc(rs.getInt("ID"), rs.getString("Ten"), 0, rs.getInt("Gia"), rs.getInt("SoLuongConLai")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean timChiTietKham(int idct) {
        String sql = "select IDCT from ChiTietKhamBenh where IDCT=?";
        List<Integer> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idct);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt(1));
            }
            if (!list.isEmpty()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean themChuanDoanSauXN(String chuandoansauXN, int idct) {
        String sql = "UPDATE ChiTietKhamBenh SET ChuanDoanSauXetNghiem = ? where IDCT = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, chuandoansauXN);
            ps.setInt(2, idct);
            int a = ps.executeUpdate();
            if (a > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean themChiTietThuoc(int idct, List<Thuoc> list) {

        String sql = "insert into ChiTietKhamBenh_Thuoc values (?, ?, ?)";
        try {
            for (int i = 0; i < list.size(); i++) {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, list.get(i).getId());
                ps.setInt(2, idct);
                ps.setInt(3, list.get(i).getSoLuong());
                ps.execute();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }
}
