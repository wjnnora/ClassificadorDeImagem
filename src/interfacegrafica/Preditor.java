/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacegrafica;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import opencv.ExtratorImagem;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.bayes.NaiveBayesUpdateable;
import weka.classifiers.functions.LibSVM;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.lazy.IBk;
import weka.classifiers.rules.JRip;
import weka.classifiers.rules.OneR;
import weka.classifiers.trees.J48;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SelectedTag;
import weka.core.converters.ConverterUtils.DataSource;

/**
 *
 * @author welli
 */
public class Preditor extends javax.swing.JFrame {

    private Instances instancias;
    
    public Preditor() {
        initComponents();
    }
    
    public void classifica() throws Exception
    {
        //Naive Bayes
        NaiveBayes nb = new NaiveBayes();
        nb.buildClassifier(instancias);
            
        //Arvore de decisão
        J48 arvore = new J48();        
        arvore.buildClassifier(instancias);
        
        //Regras
        OneR oner = new OneR();
        JRip jrip = new JRip();
        
        //Criação das tabelas de probabilidade
        oner.buildClassifier(instancias);
        jrip.buildClassifier(instancias);
        
        //Aprendizado por Instancias
        IBk ibk = new IBk(3);
        ibk.buildClassifier(instancias);
        
        //Máquinas de vetores de suporte
        LibSVM svm = new LibSVM();
        svm.setKernelType(new SelectedTag(LibSVM.KERNELTYPE_LINEAR, LibSVM.TAGS_KERNELTYPE));
        svm.buildClassifier(instancias);
        
        //Redes Neurais - Multilayer
        MultilayerPerceptron multi = new MultilayerPerceptron();
        multi.buildClassifier(instancias);
        
        Instance novo = new DenseInstance(instancias.numAttributes());
        novo.setDataset(instancias);
        novo.setValue(0, Float.parseFloat(lblLaranjaBart.getText()));
        novo.setValue(1, Float.parseFloat(lblAzulCalcaoBart.getText()));
        novo.setValue(2, Float.parseFloat(lblAzulSapatoBart.getText()));
        novo.setValue(3, Float.parseFloat(lblMarromHomer.getText()));
        novo.setValue(4, Float.parseFloat(lblAzulHomer.getText()));
        novo.setValue(5, Float.parseFloat(lblSapatoHomer.getText()));
        
        DecimalFormat df = new DecimalFormat("#.##%");
        
        // Fazendo as previsões
        double resultadosNaiveBayes[] = nb.distributionForInstance(novo);        
        lblNaiveBart.setText("Bart: " + df.format(resultadosNaiveBayes[0]/(resultadosNaiveBayes[0]+resultadosNaiveBayes[1])));
        lblNaiveHomer.setText("Homer: " + df.format(resultadosNaiveBayes[1]/(resultadosNaiveBayes[0]+resultadosNaiveBayes[1])));    
        
        double resultadosJ48[] = arvore.distributionForInstance(novo);
        lblJ48bart.setText("Bart: " + df.format(resultadosJ48[0]/(resultadosJ48[0]+resultadosJ48[1])));
        lblJ48homer.setText("Homer: " + df.format(resultadosJ48[1]/(resultadosJ48[0]+resultadosJ48[1])));    
        
        double resultadosOneR[] = oner.distributionForInstance(novo);          
        lblOneRBart.setText("Bart: " + df.format(resultadosOneR[0]/(resultadosOneR[0]+resultadosOneR[1])));
        lblOneRHomer.setText("Homer: " + df.format(resultadosOneR[1]/(resultadosOneR[0]+resultadosOneR[1])));   
        
        double resultadosJRip[] = jrip.distributionForInstance(novo);      
        lblJRipBart.setText("Bart: " + df.format(resultadosJRip[0]/(resultadosJRip[0]+resultadosJRip[1])));
        lblJRipHomer.setText("Homer: " + df.format(resultadosJRip[1]/(resultadosJRip[0]+resultadosJRip[1])));    
        
        double resultadosIbk[] = ibk.distributionForInstance(novo);      
        lblIbkBart.setText("Bart: " + df.format(resultadosIbk[0]/(resultadosIbk[0]+resultadosIbk[1])));
        lblIbkHomer.setText("Homer: " + df.format(resultadosIbk[1]/(resultadosIbk[0]+resultadosIbk[1])));
        
        double resultadosSVM[] = svm.distributionForInstance(novo);
        lblLibSVMbart.setText("Bart: " + df.format(resultadosSVM[0]/(resultadosSVM[0]+resultadosSVM[1])));
        lblLibSVMhomer.setText("Homer: " + df.format(resultadosSVM[1]/(resultadosSVM[0]+resultadosSVM[1])));
        
        double resultadosMultilayer[] = multi.distributionForInstance(novo);
        lblMultiLayerBart.setText("Bart: " + df.format(resultadosMultilayer[0]/(resultadosMultilayer[0]+resultadosMultilayer[1])));
        lblMultiLayerHomer.setText("Homer: " + df.format(resultadosMultilayer[1]/(resultadosMultilayer[0]+resultadosMultilayer[1])));
    }
    
    public void carregaBaseWeka() throws Exception {
        DataSource ds = new DataSource("src\\opencv\\caracteristicas.arff");
        instancias = ds.getDataSet();
        instancias.setClassIndex(instancias.numAttributes()-1);
        //System.out.println(instancias.toString());
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSelecionarImagem = new javax.swing.JButton();
        txtCaminhoImagem = new javax.swing.JTextField();
        lblImagem = new javax.swing.JLabel();
        btnExtrairCaracteristicas = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblLaranjaBart = new javax.swing.JLabel();
        lblAzulCalcaoBart = new javax.swing.JLabel();
        lblAzulSapatoBart = new javax.swing.JLabel();
        lblAzulHomer = new javax.swing.JLabel();
        lblMarromHomer = new javax.swing.JLabel();
        lblSapatoHomer = new javax.swing.JLabel();
        btnClassificar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        lblNaiveBart = new javax.swing.JLabel();
        lblNaiveHomer = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblJ48bart = new javax.swing.JLabel();
        lblJ48homer = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblOneRBart = new javax.swing.JLabel();
        lblOneRHomer = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblJRipBart = new javax.swing.JLabel();
        lblJRipHomer = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblIbkBart = new javax.swing.JLabel();
        lblIbkHomer = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblLibSVMbart = new javax.swing.JLabel();
        lblLibSVMhomer = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblMultiLayerBart = new javax.swing.JLabel();
        lblMultiLayerHomer = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnSelecionarImagem.setLabel("Selecionar Imagem");
        btnSelecionarImagem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelecionarImagemActionPerformed(evt);
            }
        });

        lblImagem.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnExtrairCaracteristicas.setText("Extrair Características");
        btnExtrairCaracteristicas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExtrairCaracteristicasActionPerformed(evt);
            }
        });

        jLabel1.setText("Características do Bart");

        jLabel2.setText("Características do Homer");

        lblLaranjaBart.setText("-");

        lblAzulCalcaoBart.setText("-");

        lblAzulSapatoBart.setText("-");

        lblAzulHomer.setText("-");

        lblMarromHomer.setText("-");

        lblSapatoHomer.setText("-");

        btnClassificar.setText("Classificar");
        btnClassificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClassificarActionPerformed(evt);
            }
        });

        jLabel3.setText("Naive Bayes");

        lblNaiveBart.setText("-");

        lblNaiveHomer.setText("-");

        jLabel4.setText("J48");

        lblJ48bart.setText("-");

        lblJ48homer.setText("-");

        jLabel5.setText("OneR");

        lblOneRBart.setText("-");

        lblOneRHomer.setText("-");

        jLabel6.setText("JRip");

        lblJRipBart.setText("-");

        lblJRipHomer.setText("-");

        jLabel7.setText("IBk");

        lblIbkBart.setText("-");

        lblIbkHomer.setText("-");

        jLabel8.setText("LibSVM");

        lblLibSVMbart.setText("-");

        lblLibSVMhomer.setText("-");

        jLabel9.setText("Multilayer");

        lblMultiLayerBart.setText("-");

        lblMultiLayerHomer.setText("-");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 825, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnExtrairCaracteristicas)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(74, 74, 74)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblJ48bart)
                                            .addComponent(jLabel4)
                                            .addComponent(lblJ48homer)
                                            .addComponent(jLabel8)
                                            .addComponent(lblLibSVMbart)
                                            .addComponent(lblLibSVMhomer))))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(btnClassificar, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(118, 118, 118)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(lblOneRBart)
                                                    .addComponent(jLabel5)
                                                    .addComponent(lblOneRHomer))
                                                .addGap(100, 100, 100)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(lblJRipHomer)
                                                    .addComponent(jLabel6)
                                                    .addComponent(lblJRipBart)))
                                            .addComponent(jLabel9)
                                            .addComponent(lblMultiLayerBart)
                                            .addComponent(lblMultiLayerHomer)))))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lblNaiveHomer)
                                    .addGap(227, 227, 227))
                                .addComponent(lblNaiveBart, javax.swing.GroupLayout.Alignment.LEADING))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(lblLaranjaBart)
                                    .addComponent(lblAzulCalcaoBart)
                                    .addComponent(lblAzulSapatoBart))
                                .addGap(120, 120, 120)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblSapatoHomer)
                                    .addComponent(lblMarromHomer)
                                    .addComponent(lblAzulHomer)
                                    .addComponent(jLabel2)))
                            .addComponent(jLabel7)
                            .addComponent(lblIbkBart)
                            .addComponent(lblIbkHomer)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSelecionarImagem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCaminhoImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 956, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(169, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSelecionarImagem)
                    .addComponent(txtCaminhoImagem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblImagem, javax.swing.GroupLayout.DEFAULT_SIZE, 932, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnExtrairCaracteristicas)
                            .addComponent(btnClassificar))
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(56, 56, 56)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblLaranjaBart)
                            .addComponent(lblAzulHomer))
                        .addGap(72, 72, 72)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblAzulCalcaoBart)
                            .addComponent(lblMarromHomer))
                        .addGap(77, 77, 77)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblAzulSapatoBart)
                            .addComponent(lblSapatoHomer))
                        .addGap(56, 56, 56)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNaiveBart)
                            .addComponent(lblJ48bart)
                            .addComponent(lblOneRBart)
                            .addComponent(lblJRipBart))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblJ48homer)
                                .addComponent(lblOneRHomer)
                                .addComponent(lblJRipHomer))
                            .addComponent(lblNaiveHomer))
                        .addGap(63, 63, 63)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblIbkBart)
                            .addComponent(lblLibSVMbart)
                            .addComponent(lblMultiLayerBart))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblIbkHomer)
                            .addComponent(lblLibSVMhomer)
                            .addComponent(lblMultiLayerHomer))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSelecionarImagemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelecionarImagemActionPerformed
        JFileChooser fc = new JFileChooser();
        int retorno = fc.showDialog(this, "Selecione a Imagem");
        if (retorno == fc.APPROVE_OPTION) {
            File arquivo = fc.getSelectedFile();
            txtCaminhoImagem.setText(arquivo.getAbsolutePath());
            
            BufferedImage imagemBmp = null;
            try {
                imagemBmp = ImageIO.read(arquivo);
                ImageIcon imagemLabel = new ImageIcon(imagemBmp);
                lblImagem.setIcon(new ImageIcon(imagemLabel.getImage().getScaledInstance(lblImagem.getWidth(), lblImagem.getHeight(), Image.SCALE_DEFAULT)));
            } catch (IOException ex) {
                Logger.getLogger(Preditor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnSelecionarImagemActionPerformed

    private void btnExtrairCaracteristicasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExtrairCaracteristicasActionPerformed
        ExtratorImagem extrator = new ExtratorImagem();
        float []caracteristicas = extrator.extrairCaracteristicaImagem(txtCaminhoImagem.getText());
        lblLaranjaBart.setText(String.valueOf(caracteristicas[0]));
        lblAzulCalcaoBart.setText(String.valueOf(caracteristicas[1]));
        lblAzulSapatoBart.setText(String.valueOf(caracteristicas[2]));
        lblAzulHomer.setText(String.valueOf(caracteristicas[3]));
        lblMarromHomer.setText(String.valueOf(caracteristicas[4]));
        lblSapatoHomer.setText(String.valueOf(caracteristicas[5]));
    }//GEN-LAST:event_btnExtrairCaracteristicasActionPerformed

    private void btnClassificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClassificarActionPerformed
        try {
            carregaBaseWeka();
            classifica();
        } catch (Exception ex) {
            Logger.getLogger(Preditor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnClassificarActionPerformed

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
            java.util.logging.Logger.getLogger(Preditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Preditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Preditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Preditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Preditor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClassificar;
    private javax.swing.JButton btnExtrairCaracteristicas;
    private javax.swing.JButton btnSelecionarImagem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lblAzulCalcaoBart;
    private javax.swing.JLabel lblAzulHomer;
    private javax.swing.JLabel lblAzulSapatoBart;
    private javax.swing.JLabel lblIbkBart;
    private javax.swing.JLabel lblIbkHomer;
    private javax.swing.JLabel lblImagem;
    private javax.swing.JLabel lblJ48bart;
    private javax.swing.JLabel lblJ48homer;
    private javax.swing.JLabel lblJRipBart;
    private javax.swing.JLabel lblJRipHomer;
    private javax.swing.JLabel lblLaranjaBart;
    private javax.swing.JLabel lblLibSVMbart;
    private javax.swing.JLabel lblLibSVMhomer;
    private javax.swing.JLabel lblMarromHomer;
    private javax.swing.JLabel lblMultiLayerBart;
    private javax.swing.JLabel lblMultiLayerHomer;
    private javax.swing.JLabel lblNaiveBart;
    private javax.swing.JLabel lblNaiveHomer;
    private javax.swing.JLabel lblOneRBart;
    private javax.swing.JLabel lblOneRHomer;
    private javax.swing.JLabel lblSapatoHomer;
    private javax.swing.JTextField txtCaminhoImagem;
    // End of variables declaration//GEN-END:variables
}
