package ScreenClasses;

import OtherClasses.GlobalVar;
import javax.swing.JColorChooser;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;


/**s
 *
 * @author arlin
 */
public class Configurazione extends javax.swing.JDialog {

    private boolean FileConfig = false;
    private int FileCol;
    private int FileRow;
    private int FileTotA;
    private int FileTotB;        
    private String PathFileC;

    /**
     * Creates new form Configurazione
     */
// Configurazione guidata (Interfaccia video)
    public Configurazione(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    // AC  - Center form
        this.setLocationRelativeTo(null); 
        this.FileConfig = false;
    }

//Configurazione da file
    public Configurazione(String ParamFilePath){
        this.FileConfig = true;
        this.FileCol = 0;
        this.FileRow = 0;
        this.FileTotA = 0;
        this.FileTotB = 0;    
        this.PathFileC = ParamFilePath;
    }

// My methods ********************************************************************
    public ArrayList LeggiConfig(String PathFile) throws FileNotFoundException, IOException{
        String ReadedLine;
        int StrLen = 0;
        BufferedReader br = new BufferedReader(new FileReader(PathFile));
        // La prima condizione per valutare il file come buono
        if (!(br.readLine().equals("Configurazione Gioco della vita"))){
            MsgGenerale();
            return null;
        }
        int i = 0;
        char a_char;  
        ArrayList bck = new ArrayList();
        int TotArray = 0;
        while (true){
            ReadedLine = br.readLine();   
            if (ReadedLine == null)
                break;
            this.FileRow++;
            StrLen = ReadedLine.length();
            if (this.FileCol < StrLen - 1) { // Vuol dire che ho una riga piu' lunga                
                if (this.FileRow > 1){
                    a_char = '*';                    
                    int ii = TotArray;
                    for (int j = this.FileRow - 1; j > 0; j --){
                        for (int idx = 0; idx < StrLen - 1 - this.FileCol; idx++){
                            bck.add(ii + idx, a_char);
                            TotArray++;
                        }
                        ii = ii - this.FileCol - 1;
                    }
                }
                this.FileCol = StrLen - 1;
            }
            for (i = 0; i <= this.FileCol; i++){
                if (i < StrLen){
                    a_char = ReadedLine.charAt(i);
                } else {
                    a_char = ' ';
                }
                switch (a_char){
                    case 'a':
                        a_char = Character.toUpperCase(a_char);
                    case 'A':
                        this.FileTotA++;
                        break;
                    case 'b':
                        a_char = Character.toUpperCase(a_char);
                    case 'B':
                        this.FileTotB++;
                        break;
                    default :
                        a_char = '*';
                        break;
                }  
                bck.add((TotArray + i), a_char);
            }
            TotArray = bck.size();
        }    
        this.FileCol++;
        boolean procedi = this.CheckParam(this.FileCol, this.FileRow, false, this.FileTotA, this.FileTotB);
        if (procedi == false)
            return null;
        bck = this.addCibo(bck);
        if (bck == null)
            MsgGenerale();
        return bck;
    }
    private ArrayList addCibo (ArrayList lstConfig){
        int percAnimali = ((TotAnimaliA() + TotAnimaliB()) * 100) / (Colonne() * Righe());
        Random rand = new Random();
        int percCibo;
        while (true){
            percCibo = rand.nextInt(50);
            if (percCibo + percAnimali < 100)
                break;
        }
        int TotCibo = (percCibo * Colonne() * Righe()) / 100; 
        int randomnum = 0;
        char a_char;
        for (int i = 0; i < TotCibo; i++){
            randomnum = rand.nextInt(Colonne() * Righe());
            a_char = (char) lstConfig.get(randomnum);
            if (a_char == '*')
                lstConfig.set(randomnum, 'C');
            else
                i--;
        }
        return lstConfig;
    }
    private boolean CheckParam(int Col, int Row, boolean randomck, int TotA, int TotB){
        if (TotA <= 0 && TotB <= 0){
            MsgGenerale();    
            return false;
        }
        if ((Col * Row * 90 / 100) < (TotA + TotB)){
            MsgAnimali();
            return false;
        }
        return true;    
    }     
    private void MsgGenerale(){
        JOptionPane.showMessageDialog(null,"Il file selezionato('" + this.PathFileC + "') non è un file di configurazione.");                
    }
    private void MsgAnimali(){
        JOptionPane.showMessageDialog(null, "La densità degli animali nella griglia non è adatta!" + 
                                            "\nRicorda che densità animale A + densità animale B <= 90%(Colonne * Righe)" +
                                            "\nAnimale A + Animale B = " + (TotAnimaliA() + TotAnimaliB()) +
                                            "\nColonne * Righe = " + (Colonne() * Righe()));
    }
    public int Colonne(){
        int colonne = 0;
        if (this.FileConfig == false)
            colonne = Integer.parseInt(this.Col.getText());  
        else
            colonne = this.FileCol; 
        return colonne;
    }
    public int Righe(){
        int righe = 0;
        if (this.FileConfig == false)
            righe = Integer.parseInt(this.Row.getText()); 
        else
            righe = this.FileRow;
        return righe;
    }
    public int TotAnimaliA(){
        int TotAnA = 0;
        if (this.FileConfig == false)        
            TotAnA = Integer.parseInt(this.TotA.getText());  
        else
            TotAnA = this.FileTotA;
        return TotAnA;
    }
    public int TotAnimaliB(){
        int TotAnB = 0;
        if (this.FileConfig == false)        
            TotAnB = Integer.parseInt(this.TotB.getText());  
        else
            TotAnB = this.FileTotB;
        return TotAnB;
    }
    public boolean RandomCk(){
        if (this.FileConfig == false)        
            return this.CkRandom.isSelected();
        return false;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        LbCol = new javax.swing.JLabel();
        LbRow = new javax.swing.JLabel();
        Col = new javax.swing.JTextField();
        Row = new javax.swing.JTextField();
        PbOk = new javax.swing.JButton();
        LbA = new javax.swing.JLabel();
        LbB = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        LbRgbA = new javax.swing.JLabel();
        LbRgbB = new javax.swing.JLabel();
        PbRgbA = new javax.swing.JButton();
        PbRgbB = new javax.swing.JButton();
        CkRandom = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        TotA = new javax.swing.JTextField();
        TotB = new javax.swing.JTextField();
        LbB1 = new javax.swing.JLabel();
        LbRgbB1 = new javax.swing.JLabel();

        jLabel3.setText("jLabel3");

        jLabel8.setText("jLabel8");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Configurazione guidata");

        LbCol.setText("Colonne");

        LbRow.setText("Righe");
        LbRow.setMaximumSize(new java.awt.Dimension(39, 14));
        LbRow.setMinimumSize(new java.awt.Dimension(39, 14));
        LbRow.setPreferredSize(new java.awt.Dimension(39, 14));

        Col.setText("0");
        Col.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ColKeyTyped(evt);
            }
        });

        Row.setText("0");
        Row.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                RowFocusLost(evt);
            }
        });
        Row.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                RowKeyTyped(evt);
            }
        });

        PbOk.setText("OK");
        PbOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PbOkActionPerformed(evt);
            }
        });

        LbA.setText("Animale A");

        LbB.setText("Animale B");
        LbB.setMaximumSize(new java.awt.Dimension(47, 14));
        LbB.setMinimumSize(new java.awt.Dimension(47, 14));
        LbB.setPreferredSize(new java.awt.Dimension(47, 14));

        jLabel1.setText(" ");

        jLabel2.setText(" ");

        LbRgbA.setBackground(new java.awt.Color(255, 0, 0));
        LbRgbA.setForeground(new java.awt.Color(255, 0, 0));
        LbRgbA.setText("aaa");
        LbRgbA.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        LbRgbA.setOpaque(true);

        LbRgbB.setBackground(new java.awt.Color(0, 0, 255));
        LbRgbB.setForeground(new java.awt.Color(0, 0, 255));
        LbRgbB.setText("aaa");
        LbRgbB.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        LbRgbB.setMaximumSize(new java.awt.Dimension(20, 16));
        LbRgbB.setMinimumSize(new java.awt.Dimension(20, 16));
        LbRgbB.setOpaque(true);
        LbRgbB.setPreferredSize(new java.awt.Dimension(20, 16));

        PbRgbA.setText("...");
        PbRgbA.setMargin(new java.awt.Insets(0, 0, 0, 0));
        PbRgbA.setOpaque(false);
        PbRgbA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PbRgbAActionPerformed(evt);
            }
        });

        PbRgbB.setText("...");
        PbRgbB.setMargin(new java.awt.Insets(0, 0, 0, 0));
        PbRgbB.setOpaque(false);
        PbRgbB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PbRgbBActionPerformed(evt);
            }
        });

        CkRandom.setText("Posizionamento random degli animali e del cibo");
        CkRandom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CkRandomActionPerformed(evt);
            }
        });

        jLabel4.setText("Tot. animali:");

        jLabel5.setText("A:");

        jLabel6.setText("B:");

        TotA.setText("0");
        TotA.setEnabled(false);
        TotA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TotAKeyTyped(evt);
            }
        });

        TotB.setText("0");
        TotB.setEnabled(false);
        TotB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TotBKeyTyped(evt);
            }
        });

        LbB1.setText("Cibo");
        LbB1.setMaximumSize(new java.awt.Dimension(47, 14));
        LbB1.setMinimumSize(new java.awt.Dimension(47, 14));
        LbB1.setPreferredSize(new java.awt.Dimension(47, 14));

        LbRgbB1.setBackground(new java.awt.Color(204, 255, 204));
        LbRgbB1.setForeground(new java.awt.Color(204, 255, 204));
        LbRgbB1.setText("aaa");
        LbRgbB1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        LbRgbB1.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(CkRandom))
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(LbRow, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(Row, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(LbCol, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(Col, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(LbB1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(LbRgbB1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(LbB, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(LbRgbB, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PbRgbB, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(PbOk)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(LbA, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(LbRgbA, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(PbRgbA, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(7, 7, 7)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TotB, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TotA, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(LbCol))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Col, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LbA))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(PbRgbA, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LbRgbA)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel1)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(LbRow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Row, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LbB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LbRgbB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PbRgbB, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LbB1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LbRgbB1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(CkRandom)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TotA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TotB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PbOk)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void PbOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PbOkActionPerformed
        int colonne = Integer.parseInt(this.Col.getText());        
        int righe = Integer.parseInt(this.Row.getText());  
        int TotAnA = Integer.parseInt(this.TotA.getText());  
        int TotAnB = Integer.parseInt(this.Row.getText());   
        boolean procedi = this.CheckParam(colonne, righe, this.CkRandom.isSelected(), TotAnA, TotAnB);
        if (procedi == true)
            this.dispose();
        else
            JOptionPane.showMessageDialog(null,"Dati mancanti o errati!");
    }//GEN-LAST:event_PbOkActionPerformed

    private void PbRgbAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PbRgbAActionPerformed
        Color newColorA = JColorChooser.showDialog(null, "Choose a color", GlobalVar.RgbA);
        if (newColorA != null){
            GlobalVar.RgbA = newColorA;
            this.LbRgbA.setBackground(newColorA);
            this.LbRgbA.setForeground(newColorA);
        }
    }//GEN-LAST:event_PbRgbAActionPerformed

    private void PbRgbBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PbRgbBActionPerformed
        Color newColorB = JColorChooser.showDialog(null, "Choose a color", GlobalVar.RgbB);
        if (newColorB != null) {
            GlobalVar.RgbB = newColorB;
            this.LbRgbB.setBackground(newColorB);
            this.LbRgbB.setForeground(newColorB);
        }
    }//GEN-LAST:event_PbRgbBActionPerformed

    private void CkRandomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CkRandomActionPerformed
        if (CkRandom.isSelected() == true){
            TotA.setEnabled(true);
            TotB.setEnabled(true);
        } else {
            TotA.setEnabled(false);
            TotB.setEnabled(false);
        }
    }//GEN-LAST:event_CkRandomActionPerformed
// Accetto solo numerici
    private void ColKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ColKeyTyped
        char ch = evt.getKeyChar();
        if (!(Character.isDigit(ch)))
            evt.consume();
    }//GEN-LAST:event_ColKeyTyped

    private void RowKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RowKeyTyped
        char ch = evt.getKeyChar();
        if (!(Character.isDigit(ch)))
            evt.consume();
    }//GEN-LAST:event_RowKeyTyped

    private void TotAKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TotAKeyTyped
        char ch = evt.getKeyChar();
        if (!(Character.isDigit(ch)))
            evt.consume();
    }//GEN-LAST:event_TotAKeyTyped

    private void TotBKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TotBKeyTyped
        char ch = evt.getKeyChar();
        if (!(Character.isDigit(ch)))
            evt.consume();
    }//GEN-LAST:event_TotBKeyTyped

    private void RowFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_RowFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_RowFocusLost

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(Configurazione.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Configurazione.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Configurazione.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Configurazione.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Configurazione dialog = new Configurazione(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox CkRandom;
    private javax.swing.JTextField Col;
    private javax.swing.JLabel LbA;
    private javax.swing.JLabel LbB;
    private javax.swing.JLabel LbB1;
    private javax.swing.JLabel LbCol;
    private javax.swing.JLabel LbRgbA;
    private javax.swing.JLabel LbRgbB;
    private javax.swing.JLabel LbRgbB1;
    private javax.swing.JLabel LbRow;
    private javax.swing.JButton PbOk;
    private javax.swing.JButton PbRgbA;
    private javax.swing.JButton PbRgbB;
    private javax.swing.JTextField Row;
    private javax.swing.JTextField TotA;
    private javax.swing.JTextField TotB;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    // End of variables declaration//GEN-END:variables
}
