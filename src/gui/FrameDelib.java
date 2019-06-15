/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import DAO.EtudiantExcelDAO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import DAO.DaoFactory;
import DAO.EtudiantDAO;
import DAO.MatiereDAO;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;
import models.Etudiant;
import models.Matiere;
import models.CreatExcelEtudiant;

/**
 *
 * @author digital
 * 
 */


public class FrameDelib extends javax.swing.JFrame {

    HSSFWorkbook workbook;
    HSSFCellStyle style;
    HSSFFont font;
    DaoFactory daoFactory;
    ArrayList<Etudiant> listEtudiant = null;
    ArrayList<Matiere> listMatiere = null;
    DefaultTableModel model;
    /**
     * Creates new form FrameDelib
     */
    public FrameDelib(DaoFactory dao) {
        initComponents();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.daoFactory = dao;
        addRowToJtableDelib();
    }
    
    //generation de pv
    
     public void generatePV() throws IOException {
         Row rowInfo;
        workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Liste des Etudiants");
        Cell cell;
        Row row;
        int num = 0;
        row = sheet.createRow(num);
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("UNIVERSITE OUAGA 1 Pr JOSEPH KI-ZERBO");
        cell.setCellStyle(style);
        row = sheet.createRow(++num);
        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("-------------------------------");
        cell.setCellStyle(style);
        
        row = sheet.createRow(++num);
        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("  UNITE DE FORMATION ET DE RECHERCHE EN \n" +
        "SCIENCES EXACTES ET APPLIQUEES (UFR/SEA)");
        
        cell.setCellStyle(style);
        row = sheet.createRow(++num);
        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("-------------------------------");
        cell.setCellStyle(style);
        
        row = sheet.createRow(++num);
        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("SERVICE DE SCOLARITE");
        cell.setCellStyle(style);
        
        row = sheet.createRow(++num);
        cell = row.createCell(7, CellType.STRING);
        cell.setCellValue("Procès Verbal de délibération");
        cell.setCellStyle(style);
        
        row = sheet.createRow(++num);
        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("Licence III   Semestre 5   Informatique - Option : Systèmes d'Informations et Réseaux (SIR)");
        cell.setCellStyle(style);
        
        Row rowMat = sheet.createRow(10);
        Row rowCoef = sheet.createRow(11);
        int i =6;//colonne
         for(Matiere m : listMatiere)
         {
             cell = rowMat.createCell(i, CellType.STRING);
             cell.setCellValue(m.getIntitule());
             cell.setCellStyle(style);
             
             cell = rowCoef.createCell(i, CellType.NUMERIC);
             cell.setCellValue(m.getCoef());
             cell.setCellStyle(style);
             i++;
         }
         //ecrire Coefficient sur la ligne des coefficients
         cell = rowCoef.createCell(5, CellType.NUMERIC);
         cell.setCellValue("Coefficient");
         cell.setCellStyle(style);
        int rownum = 12;
        rowInfo = sheet.createRow(rownum);
        cell = rowInfo.createCell(0, CellType.STRING);
        cell.setCellValue("No");
        cell.setCellStyle(style);
        // nom
        cell = rowInfo.createCell(1, CellType.STRING);
        cell.setCellValue("Nom");
        cell.setCellStyle(style);
        // prenom
        cell = rowInfo.createCell(2, CellType.STRING);
        cell.setCellValue("Prenom");
        cell.setCellStyle(style);
        // date de naissance
        cell = rowInfo.createCell(3, CellType.STRING);
        cell.setCellValue("date Naissance");
        cell.setCellStyle(style);
        // niveau
        cell = rowInfo.createCell(4, CellType.STRING);
        cell.setCellValue("Lieu de Naissance");
        cell.setCellStyle(style);
        //matricule
        cell = rowInfo.createCell(5, CellType.STRING);
        cell.setCellValue("Matricule");
        cell.setCellStyle(style);
 
        // Data
        int j = 1;
        for (Etudiant etd : listEtudiant) {
            rownum++;
            row = sheet.createRow(rownum);
 
            // numero
            cell = row.createCell(0, CellType.NUMERIC);
            cell.setCellValue(j);
            // nom
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue(etd.getNom());
            // prennom
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue(etd.getPrenom());
            // date de naissance
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue(etd.getDate());
            // lieu de naissance
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue(etd.getLieu());
            // matricule
            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue(etd.getMatricule());
            //recuperation de la liste des note de l'etudiant
            HashMap listNote = etd.getListNote();
            int k = 6;
            for(Matiere m : listMatiere)
            {
                cell = row.createCell(k, CellType.NUMERIC);
                cell.setCellValue((float)listNote.get(m.getIntitule()));
                k++;
            }
            cell = rowInfo.createCell(++k, CellType.STRING);
            //cell.setCellValue(""
            
            // Bonus (E)
        j++;
        }
        File file = new File("C:/demo/etudiant44.xls");
        file.getParentFile().mkdirs();
        FileOutputStream outFile = new FileOutputStream(file);
        this.workbook.write(outFile);
        System.out.println("Created file: " + file.getAbsolutePath());
 
    }
    //on calcul les moyennes et on delibere
    
    public void addRowToJtableDelib()
    {
        EtudiantDAO etudiantDao = daoFactory.getEtudiantDAO();
        MatiereDAO matiereDao = daoFactory.getMatiereDAO();
        listEtudiant = new ArrayList();
        listEtudiant = etudiantDao.getAll();
        listMatiere = matiereDao.getAll();
        HashMap listNote = new HashMap();
        float moy;
        int Tcoef;
        float note;
        float total;
        model = (DefaultTableModel) tblDelib.getModel();
        Object rowData[] = new Object[8];
        int j = 1;
        for(int i = 0; i < listEtudiant.size(); i++)
        {
            Tcoef = 0;
            total = 0;
            rowData[0] = j;
            rowData[1] = listEtudiant.get(i).getNom();
            rowData[2] = listEtudiant.get(i).getPrenom();
            rowData[3] = listEtudiant.get(i).getDate();
            rowData[4] = listEtudiant.get(i).getLieu();
            rowData[5] = listEtudiant.get(i).getMatricule();
            listNote = listEtudiant.get(i).getListNote();
            for(Matiere mat : listMatiere)
            {
                Tcoef += mat.getCoef();
                note = (float)listNote.get(mat.getIntitule());
                total += note*mat.getCoef();
            }
            moy = total/Tcoef;
            rowData[6] = moy;
            if(moy>=10)
               rowData[7] = "ADMIS(E)";
            else
               rowData[7] = "AJOURNE(E)";
            model.addRow(rowData);
            
            j++;
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblDelib = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblDelib.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Nom", "Prenom", "Date de Naissance", "Lieu de Naissance", "Matricule", "Moyenne", "Status"
            }
        ));
        jScrollPane1.setViewportView(tblDelib);

        jButton1.setText("Generer un PV");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 670, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(161, 161, 161))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        System.out.println("clicked");
        try
        {
            generatePV();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
       //CreatExcelEtudiant Pv = new CreatExcelEtudiant(listEtudiant, listMatiere);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(FrameDelib.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(FrameDelib.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(FrameDelib.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(FrameDelib.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new FrameDelib().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblDelib;
    // End of variables declaration//GEN-END:variables
}
