/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opencv;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.lazy.IBk;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.RandomForest;
import weka.classifiers.trees.RandomTree;
import weka.core.Debug.Random;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

/**
 *
 * @author welli
 */
public class Testes {
    
    public static void main(String args[]) throws Exception {
    
        DataSource fonte = new DataSource("opencv\\caracteristicas.arff");
        
        int foldes = 10;
        int vezes = 30;
        
        Classifier classificador = new RandomForest();
        Instances dados = fonte.getDataSet();
        dados.setClassIndex(dados.numAttributes() - 1);
        
        for(int i=1; i<=vezes;i++) {
            Evaluation avaliador = new Evaluation(dados);
            avaliador.crossValidateModel(classificador, dados, foldes, new Random(i));
            System.out.println(String.valueOf(avaliador.pctCorrect()).replace(".", ","));
        }
                
    }
}
