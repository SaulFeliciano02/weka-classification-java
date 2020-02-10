package logical;
import java.io.*;
import java.util.Scanner;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;


public class Main {

    public static void main(String[] args) {

        BufferedReader breader = null;
        BufferedReader testBreader = null;
        String instancia = "";
        try {
            DeleteLastLineOfFile();
            breader = new BufferedReader(new FileReader("src/Data/flags-71acurracy.arff"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Instances train = new Instances(breader);
            train.setClassIndex(3);
            breader.close();
            NaiveBayes nb = new NaiveBayes();
            nb.buildClassifier(train);

            System.out.println("Select landmass value (number): \n1 = N.America\n2 = S.America\n3 = Europe\n4 = Africa\n5 = Asia\n6 = Oceania");
            Scanner sc1 = new Scanner(System.in);
            instancia += sc1.next() + ",";

            System.out.println("\nSelect zone value (Geographic quadrant, based on Greenwich and the Equator) (number): \n1 = NE\n2 = SE\n3 = SW\n4 = NW");
            Scanner sc2 = new Scanner(System.in);
            instancia += sc2.next() + ",";

            System.out.println("\nSelect languaje value (number): \n1 = English\n2 = Spanish\n3 = French\n4 = German\n5 = Slavic\n6 = Other Indo-European\n7 = Chinese\n8 = Arabic\n9 = Japanese/Turkish/Finnish/Magyar\n10 = Others");
            Scanner sc3 = new Scanner(System.in);
            instancia += sc3.next() + ",";

            //This "?" represents the class value
            instancia += "?,";

            System.out.println("\nIs the red color present in the flag? (number): \n0 = No\n1 = Yes");
            Scanner sc4 = new Scanner(System.in);
            instancia += sc4.next() + ",";

            System.out.println("\nNumber of crosses present in the flag (0, 1, or 2): ");
            Scanner sc5 = new Scanner(System.in);
            instancia += sc5.next() + ",";

            System.out.println("\nNumber of sunstars present in the flag (0, 1, 2, 3, 4, 5, 6, 7, 9, 10, 14, 15, 22, or 50): ");
            Scanner sc6 = new Scanner(System.in);
            instancia += sc6.next();

            WriteDataToFile(instancia);
            testBreader = new BufferedReader(new FileReader("src/Data/flags-test.arff"));
            Instances test = new Instances(testBreader);
            test.setClassIndex(3);
            testBreader.close();

            Evaluation eval = new Evaluation(train);
            eval.evaluateModel(nb, test);
            System.out.println(eval.toSummaryString("\nResults\n=========\n", true));

            // Get the confusion matrix
//            String cMatrix = eval.toMatrixString();
//            System.out.println(cMatrix);

            System.out.println("Predicted value: \n");

            switch (eval.predictions().toString().charAt(10))
            {
                case '0':
                    System.out.println("Catholic");
                    break;
                case '1':
                    System.out.println("Other Christian");
                    break;
                case '2':
                    System.out.println("Muslim");
                    break;
                case '3':
                    System.out.println("Buddhist");
                    break;
                case '4':
                    System.out.println("Hindu");
                    break;
                case '5':
                    System.out.println("Ethnic");
                    break;
                case '6':
                    System.out.println("Marxist");
                    break;
                case '7':
                    System.out.println("Others");
                    break;
                default:
                    System.out.println("NaN");
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void DeleteLastLineOfFile() throws IOException {
        RandomAccessFile f = new RandomAccessFile("src/Data/flags-test.arff", "rw");
        long length = f.length() - 1;
        byte b;
        do {
            length -= 1;
            f.seek(length);
            b = f.readByte();
        } while (b != 10);
        f.setLength(length + 1);
        f.close();
    }

    public static void WriteDataToFile(String data) throws IOException {
        File file = new File("src/Data/flags-test.arff");
        FileWriter fr = new FileWriter(file, true);
        fr.write(data);
        fr.close();

        BufferedReader input = new BufferedReader(new FileReader("src/Data/flags-test.arff"));
        String last = null, line;

        while ((line = input.readLine()) != null) {
            last = line;
        }

        System.out.println("Ultima linea " + last);
    }

}