/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GiaoDien;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import model.BacSiChuyenKhoa;
import DAO.ConnectionDB;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.GroupLayout;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import model.BenhNhan;

public class BacSiChuyenKhoaForm extends javax.swing.JFrame {

    public BacSiChuyenKhoaForm() {
        initComponents();
        setResizable(false);
        connect = new ConnectionDB();
        connect.connect();
        kqXetNghiem = new ArrayList<String>();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        idct = new javax.swing.JTextField();
        ngayKham = new javax.swing.JLabel();
        themXN = new javax.swing.JButton();
        scroll = new javax.swing.JScrollPane();
        taoXetNghiem = new javax.swing.JButton();

        jLabel1.setBackground(new java.awt.Color(69, 155, 186));
        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PHÒNG KHÁM ĐA KHOA QUANG TRUNG");
        jLabel1.setOpaque(true);

        jLabel2.setText("Mã chuẩn đoán:");

        ngayKham.setText("Ngày khám:");

        themXN.setBackground(new java.awt.Color(69, 155, 186));
        themXN.setForeground(new java.awt.Color(255, 255, 255));
        themXN.setText("Thêm XN/In phiếu");
        themXN.setBorder(null);
        themXN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themXNActionPerformed(evt);
            }
        });

        taoXetNghiem.setBackground(new java.awt.Color(69, 155, 186));
        taoXetNghiem.setForeground(new java.awt.Color(255, 255, 255));
        taoXetNghiem.setText("Tạo xét nghiệm");
        taoXetNghiem.setBorder(null);
        taoXetNghiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                taoXetNghiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(themXN, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 1098, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(23, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(idct, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(109, 109, 109)
                        .addComponent(ngayKham, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(taoXetNghiem, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57))))
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idct, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ngayKham, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(taoXetNghiem, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(themXN, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void taoXetNghiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_taoXetNghiemActionPerformed
        // TODO add your handling code here:
        SimpleDateFormat fm = new SimpleDateFormat("dd/MM/yyyy");
        long time = System.currentTimeMillis();
        ngayKham.setText("Ngày khám: " + fm.format(time));
        List list;
        if (idct.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Không được để trống mã chi tiết khám!!!", "Thông báo", 0);
            return;
        } else {
            if (!idct.getText().matches("\\d*")) {
                JOptionPane.showMessageDialog(null, "Mã chi tiết phải là số nguyên", "Thông báo", 0);
                return;
            }
            id = Integer.parseInt(idct.getText());
            list = connect.getXetNghiemCanLam(id);
        }
        listIDDV = new ArrayList<Integer>();
        tenDV = new ArrayList<String>();
        if ((boolean) list.get(0) == false) {
            JOptionPane.showMessageDialog(null, "Xét nghiệm đã chứa thông tin!!!", "Thông báo", 0);
        } else if (((List) list.get(1)).size() == 0) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy xét nghiệm cho id này!!!", "Thông báo", 0);
        } else {
            tenDV = (List<String>) list.get(2);
            listIDDV = (List<Integer>) list.get(1);
        }
        JPanel panel = new JPanel();
        JLabel lb;
        GroupLayout gr = new GroupLayout(panel);
        panel.setLayout(gr);
        GroupLayout.Group groupH, groupV;
        groupH = gr.createParallelGroup(GroupLayout.Alignment.LEADING);
        groupV = gr.createSequentialGroup();

        listTextAreas = new ArrayList<JTextArea>();
        for (int i = 0; i < tenDV.size(); i++) {
            JLabel label = new JLabel(tenDV.get(i));
            JTextArea ta = new JTextArea();
            ta.setLineWrap(true);
            ta.setWrapStyleWord(true);
            JScrollPane sc = new JScrollPane(ta);
            listTextAreas.add(ta);
            groupH = groupH
                    .addComponent(label, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                    .addGroup(gr.createSequentialGroup()
                            .addContainerGap(0, Integer.MAX_VALUE)
                            .addComponent(sc, GroupLayout.PREFERRED_SIZE, 1000, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(0, Integer.MAX_VALUE)
                    );
            groupV = groupV
                    .addGap(0)
                    .addComponent(label, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addGap(0)
                    .addComponent(sc, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                    .addGap(20);
        }
        gr.setHorizontalGroup(groupH);
        gr.setVerticalGroup(groupV);
        scroll.setViewportView(panel);
    }//GEN-LAST:event_taoXetNghiemActionPerformed

    private void themXNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themXNActionPerformed
        // TODO add your handling code here:
        kqXetNghiem = new ArrayList<String>();
        if (listIDDV.size() == 0) {
            JOptionPane.showMessageDialog(null, "Hãy nhập id để thêm xét nghiệm!!!", "Thông báo", 0);
            return;
        }
        for (int i = 0; i < listIDDV.size(); i++) {
            if (listTextAreas.get(i).getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Không được để trống kết quả xét nghiệm!!!", "Thông báo", 0);
                return;
            } else {
                kqXetNghiem.add(listTextAreas.get(i).getText());
            }
        }
        boolean bool = themXetNghiem(id, listIDDV, kqXetNghiem);
        scroll.setViewportView(new JPanel());
        if (bool) {
            JOptionPane.showMessageDialog(null, "Thêm thành công!!!", "Thông báo", 1);
        } else {
            JOptionPane.showMessageDialog(null, "Thêm thất bại!!!", "Thông báo", 0);
        }
    }//GEN-LAST:event_themXNActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BacSiChuyenKhoaForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BacSiChuyenKhoaForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BacSiChuyenKhoaForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BacSiChuyenKhoaForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BacSiChuyenKhoaForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField idct;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel ngayKham;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JButton taoXetNghiem;
    private javax.swing.JButton themXN;
    // End of variables declaration//GEN-END:variables
    private List<Integer> listIDDV;
    private List<String> tenDV;
    private List<String> kqXetNghiem;
    private List<JTextArea> listTextAreas;
    private BacSiChuyenKhoa bsck;
    private int id;
    private ConnectionDB connect;

    //FUNCTION BO SUNG
    public boolean themXetNghiem(int idct, List<Integer> listIDDV, List<String> kqXetNghiem) {
        if (connect.themXN(idct, listIDDV, kqXetNghiem)) {
            try {
                inPhieu(idct, listIDDV, kqXetNghiem);
            } catch (DocumentException | IOException | SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }

    //hàm để xuất file pdf
    public void inPhieu(int idct, List<Integer> listIDDV, List<String> kqXetNghiem)
            throws DocumentException, IOException, SQLException {

        // lay thong tin benh nhan, dich vu
        List list = connect.getTTInPhieu(idct, listIDDV, kqXetNghiem);
        BenhNhan bn = (BenhNhan) list.get(0);
        ResultSet rs = (ResultSet) list.get(1);

        // font viet tieng viet
        // font arial dam
        String path = "Unicode\\arialbd.ttf";
        FontFactory.register(path);
        Font arialb = new Font(BaseFont.createFont(path, BaseFont.IDENTITY_H, BaseFont.EMBEDDED));
        // font arial binh thuong
        path = "Unicode\\arial.ttf";
        FontFactory.register(path);
        Font arial = new Font(BaseFont.createFont(path, BaseFont.IDENTITY_H, BaseFont.EMBEDDED));
        // font arial nghieng
        path = "Unicode\\ariali.ttf";
        FontFactory.register(path);
        Font ariali = new Font(BaseFont.createFont(path, BaseFont.IDENTITY_H, BaseFont.EMBEDDED));

        String locate="";
        
        JFileChooser j= new JFileChooser();
        j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        Integer opt = j.showSaveDialog(this);
        if(opt==JFileChooser.APPROVE_OPTION){
            locate=j.getSelectedFile().getPath();
        }
        
        // tao file pdf
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document,
                new FileOutputStream(locate+"\\Phieu-xet-nghiem.pdf"));
        writer.setPdfVersion(PdfWriter.PDF_VERSION_1_7);
        document.open();
        // phan dau
        Paragraph p = new Paragraph();
        Chunk c = new Chunk("PHÒNG KHÁM ĐA KHOA QUANG TRUNG");
        arialb.setSize(12);
        c.setFont(arialb);
        p.add(c);
        document.add(p);

        c = new Chunk("Địa chỉ: Số 1, Đại Cồ Việt, Hai Bà Trưng, Hà Nội");
        arialb.setSize(12);
        c.setFont(arialb);
        p = new Paragraph();
        p.add(c);
        document.add(p);

        long time = System.currentTimeMillis();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String ngay = format.format(time);
        c = new Chunk("Ngày: " + ngay);
        c.setFont(arialb);
        p = new Paragraph();
        p.add(c);
        document.add(p);

        // tittle
        c = new Chunk("PHIẾU XÉT NGHIỆM");
        arialb.setSize(18);
        c.setFont(arialb);
        p = new Paragraph();
        p.add(c);
        p.setAlignment(Element.ALIGN_CENTER);
        p.setSpacingAfter(20);
        p.setSpacingBefore(30);
        document.add(p);

        // thong tin benh nhan
        c = new Chunk("Mã chuẩn đoán: " + idct);
        arial.setSize(10);
        c.setFont(arial);
        p = new Paragraph();
        p.add(c);
        document.add(p);

        c = new Chunk("Họ tên: " + bn.getTen());
        arial.setSize(10);
        c.setFont(arial);
        p = new Paragraph();
        p.add(c);
        document.add(p);

        c = new Chunk("Giới tính: " + bn.getGioiTinh());
        c.setFont(arial);
        p = new Paragraph();
        p.add(c);
        document.add(p);

        c = new Chunk("Ngày sinh: " + bn.getNgaySinh());
        c.setFont(arial);
        p = new Paragraph();
        p.add(c);
        document.add(p);

        c = new Chunk("Địa chỉ: " + bn.getDiaChi());
        c.setFont(arial);
        p = new Paragraph();
        p.add(c);
        document.add(p);

        c = new Chunk("Số điện thoại: " + bn.getSDT());
        c.setFont(arial);
        p = new Paragraph();
        p.add(c);
        document.add(p);

        c = new Chunk("Chứng minh thư: " + bn.getCMT());
        c.setFont(arial);
        p = new Paragraph();
        p.add(c);
        p.setSpacingAfter(20);
        document.add(p);
        while (rs.next()) {
            // ket qua xet nghiem
            c = new Chunk(rs.getString(1) + ":");
            arialb.setSize(10);
            c.setFont(arialb);
            p = new Paragraph();
            p.add(c);
            p.setSpacingAfter(10);
            document.add(p);

            PdfPTable tb = new PdfPTable(1);
            PdfPCell cell;
            c = new Chunk("         " + rs.getString(2));
            c.setFont(arial);
            p = new Paragraph(c);
            cell = new PdfPCell(p);
            cell.setPaddingBottom(15);
            cell.setPaddingTop(10);
            tb.addCell(cell);
            tb.setSpacingAfter(20);
            tb.setWidthPercentage(100);
            document.add(tb);
        }

        //chu ky
        c = new Chunk("Bác sĩ xét nghiệm:...........................................");
        c.setFont(arial);
        p = new Paragraph();
        p.add(c);
        p.setAlignment(Element.ALIGN_RIGHT);
        document.add(p);

        // ket thuc ghi
        document.close();
        writer.close();
    }

}
