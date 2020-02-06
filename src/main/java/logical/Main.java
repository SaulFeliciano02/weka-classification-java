package logical;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.j48.BinC45ModelSelection;
import weka.core.Instance;
import weka.core.Instances;


public class Main {

    public static void main(String[] args) {

        BufferedReader breader = null;
        BufferedReader testBreader = null;
        try {
            breader = new BufferedReader(new FileReader("/home/saulfeliciano/Downloads/weka-3-9-4/data/flags.arff"));
            testBreader = new BufferedReader(new FileReader("/home/saulfeliciano/Downloads/weka-3-9-4/data/flags-test.arff"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Instances train = new Instances(breader);
            Instances test = new Instances(testBreader);
            test.setClassIndex(5);
            train.setClassIndex(5);
            breader.close();
            testBreader.close();
            NaiveBayes nb = new NaiveBayes();
            nb.buildClassifier(train);
            Evaluation eval = new Evaluation(test);
            //eval.evaluateModel()
            //eval.crossValidateModel(nb, test, 10, new Random(1));
            System.out.println(eval.toSummaryString("\nResults\n=========\n", true));
            System.out.println(eval.fMeasure(5) + " " + eval.precision(5) + " " + eval.recall(5));

//            J48 tree = new J48();
//            tree.buildClassifier(train);
//            Evaluation eval2 = new Evaluation(train);
//            eval2.crossValidateModel(tree, train, 10, new Random(1));
//            System.out.println(eval2.toSummaryString("\nResults\n=========\n", true));
//            System.out.println(eval2.fMeasure(5) + " " + eval2.precision(5) + " " + eval2.recall(5));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
