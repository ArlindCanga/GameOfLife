/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ScreenClasses;

import OtherClasses.Animali;
import OtherClasses.GlobalVar;
import static OtherClasses.GlobalVar.RgbA;
import static OtherClasses.GlobalVar.RgbB;
import static OtherClasses.GlobalVar.RgbC;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author arlin
 */
public class ScreenMain extends javax.swing.JDialog {

    /**
     * Creates new form ScreenMain
     */
    private Animali[][] GridData; 
    private PlayThread Gioca = new PlayThread();
    private boolean InPausa = false;
    private int MaxMov = -1;
    private BufferedImage master;
    
    // --
    public ScreenMain(java.awt.Frame parent, boolean modal) throws InterruptedException{
        super(parent, modal);
        while (true){
            GridData = Configura();
            //Configura();
            if (GridData != null)
                break;
        }
        // Generated code from ide
        initComponents();
        //-- My code
        ResizeScreen();
        Thread.sleep(200); // Aspetto il thread "AWT-EventQueue-0" (generato dell'IDE)
        InitGrid(GridData.length, GridData[0].length);
        Thread.sleep(200);
        PopolaGriglia(GridData);
        this.setLocationRelativeTo(null);        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PbPlay = new javax.swing.JButton();
        PbPausa = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        GrGrid = new javax.swing.JTable(){
            public Component prepareRenderer(TableCellRenderer r, int row, int col){
                Component c=super.prepareRenderer(r, row, col);
                Object cellobj = getModel().getValueAt(row,col);
                Animali animal = (Animali) cellobj;
                String value = animal.Tipo;
                JComponent jc = (JComponent)c;
                if (value == "A"){
                    c.setBackground(RgbA);
                    c.setForeground(RgbA);
                    jc.setBorder(new MatteBorder(animal.Vita / 4, animal.Vita / 4, animal.Vita / 4, animal.Vita / 4, Color.GREEN));
                } else {
                    if (value == "B"){
                        c.setBackground(RgbB);
                        c.setForeground(RgbB);
                        jc.setBorder(new MatteBorder(animal.Vita / 4 / 2, animal.Vita / 4 / 2, animal.Vita / 4 / 2, animal.Vita / 4 / 2, Color.GREEN));
                    } else {
                        if (value == "C"){
                            c.setBackground(RgbC);
                            c.setForeground(RgbC);
                        } else {
                            c.setBackground(Color.GRAY);
                            c.setForeground(Color.GRAY);
                        }
                    }
                }
                return c;
            }

        };
        PbImp = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Progetto Unicam - Il gioco della vita");
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        PbPlay.setText("Play");
        PbPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PbPlayActionPerformed(evt);
            }
        });

        PbPausa.setText("Pausa");
        PbPausa.setEnabled(false);
        PbPausa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PbPausaActionPerformed(evt);
            }
        });

        GrGrid.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        GrGrid.setEnabled(false);
        GrGrid.setTableHeader(null);
        GrGrid.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                GrGridMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(GrGrid);

        PbImp.setText("...");
        PbImp.setEnabled(false);
        PbImp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PbImpMouseClicked(evt);
            }
        });
        PbImp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PbImpActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(PbImp)
                        .addGap(545, 545, 545)
                        .addComponent(PbPausa)
                        .addGap(10, 10, 10)
                        .addComponent(PbPlay)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(PbPausa)
                        .addComponent(PbImp))
                    .addComponent(PbPlay)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
// THREAD **************************************************************************
    private class PlayThread extends Thread {
        @Override
        public void run(){
            int contatore = 0;
            Animali workobj = new Animali('*');
            boolean esegui = true;
            while (esegui == true){
                try {
                    contatore ++;
                    if (!(contatore > MaxMov) || (MaxMov == -1)){
                        workobj.Sposta(GridData);
                        PopolaGriglia(GridData);
                        Thread.sleep(200 / GlobalVar.VelThread);
                        workobj.Riproduci(GridData);
                        PopolaGriglia(GridData);
                        Thread.sleep(800 / GlobalVar.VelThread);
                    } else {
                        contatore = 0;
                        PbPausaActionPerformed(null);
                        while (InPausa == true){
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException ex) {
                                continue;
                            }
                        }
                    }
                } catch (InterruptedException e) {
                    while (InPausa == true){
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException ex) {
                            continue;
                        }
                    }
                }
            }
        }
    }
// Fine Thread *******************************************************************
    
    // Personal methods
    // Configurazione Iniziale
    private Animali[][] Configura()  {
        JFileChooser fileChooser  = new JFileChooser();
        File file = new File(GlobalVar.UserPath);
        fileChooser.setCurrentDirectory(file);
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            Configurazione FileConfig = new Configurazione(fileChooser.getSelectedFile().getAbsolutePath());
            try {
                ArrayList lstConfig = FileConfig.LeggiConfig(fileChooser.getSelectedFile().getAbsolutePath());
                if (lstConfig != null){
                    Animali[][] Ret = CreateGridObj(lstConfig,FileConfig.Colonne(),FileConfig.Righe());
                    return Ret;
                }
            } catch (IOException ex){
                JOptionPane.showMessageDialog(null,ex);   
            }
        } else {
            System.exit(0);
        }    
        return null;
    }
    //Crea Griglia[][]
    private Animali[][] CreateGridObj(ArrayList lstConfig,int TableCol, int TableRow){
        int lstsize = lstConfig.size();
        int wkCol = 0;
        int wkRow = 0;
        Animali[][] matr = new Animali[TableRow][TableCol];
        for (int i = 0; i < lstsize; i++){
            if (wkCol == TableCol){
                wkRow++;
                wkCol = 0;
            }
            matr[wkRow][wkCol] = new Animali((char) lstConfig.get(i));
            wkCol++;
        }
        return matr;
    }    
    // Resize Screen (video)
    private void ResizeScreen(){
        // Dimensioni schermo utente
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        // mi prendo un 75% 
        width = width * 75 / 100;
        height = height * 75 / 100;
        int workvar;
        int workdim;
        if (width >= height){
            workvar = (int) height;
        } else {
            workvar = (int) width;
        }
        // Screen size
        this.setLayout(null);
        this.setResizable(false); 
        Dimension PbSize = this.PbPausa.getPreferredSize();
        int PbWidth = (int) PbSize.getWidth() * 2;
        int PbHeight = (int) PbSize.getHeight();
        int ScreenWidth = (int) (workvar - 3 - PbHeight);
        int ScreenHeight = workvar;
        Insets tmp = this.getInsets();
        this.setPreferredSize(new Dimension(ScreenWidth + tmp.left + tmp.right,ScreenHeight + tmp.top + tmp.bottom));
        pack();        
        //Grid size and position
        int GridWidth = ScreenWidth - 4;
        int GridHeight  = ScreenHeight - 22 - PbHeight;
        this.jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        this.jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.jScrollPane1.setLocation(2,2);
        this.jScrollPane1.setSize(GridWidth,GridHeight);
        this.GrGrid.setPreferredSize(new Dimension(GridWidth,GridHeight));        
        // Button: Pausa & Play
        int SavePbX = (int) (ScreenWidth / 2 - PbWidth / 2);
        int SavePbY = (int) (ScreenHeight - PbHeight - 2);
        this.PbPlay.setLayout(null);
        this.PbPlay.setEnabled(true);
        this.PbPlay.setText(null);
        this.PbPlay.setBounds(SavePbX, SavePbY, PbWidth, PbHeight);
        Path imgfile = Paths.get(GlobalVar.UserPath + "//img//play.jpg");
        Image scaled = CreateImagPb(this.PbPausa, imgfile.toString());
        this.PbPlay.setIcon(new ImageIcon(scaled));
        //Pausa
        this.PbPausa.setLayout(null);
        this.PbPausa.setBounds(SavePbX, SavePbY, PbWidth, PbHeight);
        this.PbPausa.setText(null);
        imgfile = Paths.get(GlobalVar.UserPath + "//img//pausa.jpg");
        scaled = CreateImagPb(this.PbPausa, imgfile.toString());
        this.PbPausa.setIcon((new ImageIcon(scaled)));
        this.PbPausa.setVisible(false);
        // button impostazioni
        this.PbImp.setLayout(null);
        this.PbImp.setEnabled(true);
        SavePbX = ScreenWidth - (PbWidth / 2);
        this.PbImp.setBounds(SavePbX -2, SavePbY, PbWidth / 2, PbHeight);
        imgfile = Paths.get(GlobalVar.UserPath + "//img//impostazioni.jpg");
        scaled = CreateImagPb(this.PbPausa, imgfile.toString());
        this.PbImp.setText(null);
        this.PbImp.setIcon((new ImageIcon(scaled)));
    }
    // Impostazioni griglia
    private void InitGrid(int TableRow,int TableCol){
        DefaultTableModel model = (DefaultTableModel) this.GrGrid.getModel();     
        for (int i = 0; i < TableCol; i++){
            model.addColumn(new Object[]{" "});
        }
        for (int i = 0; i < TableRow; i++){
            model.addRow(new Object[]{" "});
            this.GrGrid.setRowHeight(i, this.jScrollPane1.getHeight() / TableRow);
        }
    }
    // Popola griglia in base l'array[][]
    private void PopolaGriglia(Animali[][] GridData){
        DefaultTableModel model = (DefaultTableModel) this.GrGrid.getModel();     
        for (int i = 0; i < GridData.length; i++){
            for (int ii = 0; ii < GridData[0].length; ii++){
                //model.setValueAt(GridData[i][ii].RitornaTipo(GridData[i][ii].Tipo), i, ii);
                model.setValueAt(GridData[i][ii], i, ii);
            }
        }
    }
    private Image CreateImagPb(JButton Push, String pathimg){
        Insets insets = Push.getInsets();
        Dimension size = Push.getSize();
        size.width -= insets.left + insets.right;
        size.height -= insets.top + insets.bottom;
        if (size.width > size.height) {
            size.width = -1;
        } else {
            size.height = -1;
        }
        try {
            master = ImageIO.read(new File(pathimg));
        } catch (IOException ex) {
            master = null;
        }
        if (master == null)
            return null;
        Image scaled = master.getScaledInstance(size.width, size.height, java.awt.Image.SCALE_SMOOTH);        
        return scaled;        
    }    
    
   // Chiusura
    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
    }//GEN-LAST:event_formWindowClosed

    // Play ****************************************************************************************
    private void PbPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PbPlayActionPerformed
        this.PbPlay.setVisible(false);
        this.PbPlay.setEnabled(false); 
        this.PbPausa.setEnabled(true);
        this.PbPausa.setVisible(true);
        if (this.InPausa == false)
            Gioca.start();
        else{
            this.InPausa = false;
        }
    }//GEN-LAST:event_PbPlayActionPerformed

    // Pausa ****************************************************************************************
    private void PbPausaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PbPausaActionPerformed
        this.InPausa = true; 
        this.PbPlay.setEnabled(true);
        this.PbPausa.setEnabled(false);
        this.PbPausa.setVisible(false);
        this.PbPlay.setVisible(true);
        Gioca.interrupt();
    }//GEN-LAST:event_PbPausaActionPerformed
    // PopUp menù velocità
    private void GrGridMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GrGridMouseClicked
        if (SwingUtilities.isRightMouseButton(evt)) {
            PopUpImp(evt);
        }
    }//GEN-LAST:event_GrGridMouseClicked

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        if (SwingUtilities.isRightMouseButton(evt)) {
            PopUpImp(evt);
        }
    }//GEN-LAST:event_formMouseClicked

    private void PbImpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PbImpActionPerformed
    }//GEN-LAST:event_PbImpActionPerformed

    private void PbImpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PbImpMouseClicked
        PopUpImp(evt);
    }//GEN-LAST:event_PbImpMouseClicked
    private void PopUpImp(java.awt.event.MouseEvent evt){
     // Parent menu
        JPopupMenu MainPopup = new JPopupMenu();
    //Sub menu
        JMenu VelMenu = new JMenu("Velocita'");
        JRadioButtonMenuItem velx1 = new JRadioButtonMenuItem("x1");
        if (GlobalVar.VelThread == 1)
            velx1.setSelected(true);
        velx1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                GlobalVar.VelThread = 1;
            }                    
        });
        VelMenu.add(velx1);
        VelMenu.setSelected(false);
        JRadioButtonMenuItem velx2 = new JRadioButtonMenuItem("x2");
        if (GlobalVar.VelThread == 2)
            velx2.setSelected(true);
        velx2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                GlobalVar.VelThread = 2;
           }                    
        });
        VelMenu.add(velx2);
        JRadioButtonMenuItem velx3 = new JRadioButtonMenuItem("x4");
        if (GlobalVar.VelThread == 4)
            velx3.setSelected(true);                        
        velx3.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                GlobalVar.VelThread = 4;
            }                    
        });
        VelMenu.add(velx3);                    
        JRadioButtonMenuItem velx4 = new JRadioButtonMenuItem("x6");
        if (GlobalVar.VelThread == 6)
            velx4.setSelected(true);
        velx4.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                GlobalVar.VelThread = 6;
            }                    
        });
        JRadioButtonMenuItem velx5 = new JRadioButtonMenuItem("x10");
        if (GlobalVar.VelThread == 10)
            velx5.setSelected(true);
        velx5.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                GlobalVar.VelThread = 10;
        }                    
        });
        VelMenu.add(velx5);
        MainPopup.add(VelMenu);
        JMenu ImpostaLimite = new JMenu("Imposta limite max. spostamenti");
        MainPopup.addSeparator();
        JTextField prova = new JTextField();
        prova.setText(Integer.toString(MaxMov));
        prova.setPreferredSize(new Dimension(40,20));
        prova.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {    
                MaxMov = Integer.parseInt(prova.getText());
                JOptionPane.showMessageDialog(null, "Limite imposto: " + prova.getText());
            }                      
        });
        prova.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyTyped(java.awt.event.KeyEvent evt) {                             
                char ch = evt.getKeyChar();
                if (!(Character.isDigit(ch)))
                    evt.consume();
            }                            
            
        });
        ImpostaLimite.add(prova);
        MainPopup.add(ImpostaLimite);
        MainPopup.show(evt.getComponent(), evt.getX(), evt.getY());    
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable GrGrid;
    private javax.swing.JButton PbImp;
    private javax.swing.JButton PbPausa;
    private javax.swing.JButton PbPlay;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
