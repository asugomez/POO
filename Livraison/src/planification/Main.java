package planification;

import reseau.Client;
import reseau.Route;

import static planification.Routage.test;
import static planification.Routage.testMin;

public class Main {
    public static void main(String [] args){
        Routage r1= new Routage();
        //r1.testLecture("test");

        test();
        testMin();

    }
}
