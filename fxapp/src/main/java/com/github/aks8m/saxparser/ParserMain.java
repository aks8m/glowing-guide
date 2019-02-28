package com.github.aks8m.saxparser;

import com.github.aks8m.tree.ParserNode;

public class ParserMain {

    public static void main(String[] args) {
        try {

            SaxParser sparser = new SaxParser();


            ParserNode sourceNode = sparser.parse("C:\\Users\\kmaulden\\Documents\\C-CDA Comparison Files\\xmlComparisons\\CompleteMatch\\HSEP_IPOACKIES_QUENTIN_basictree.xml");
            ParserNode targetNode = sparser.parse("C:\\Users\\kmaulden\\Documents\\C-CDA Comparison Files\\xmlComparisons\\CompleteMatch\\HSEP_IPOACKIES_QUENTIN_basictree1.xml");

            double probability = probabilisticCompare(sourceNode, targetNode);
            System.out.println("Probablistic comparison match: " + probability);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static double probabilisticCompare(ParserNode sNode, ParserNode tNode) {
        int souceNodes = sNode.getChildren().size();
        double totalProb = 0;

        for (ParserNode sourceChild : sNode.getChildren()) {

            double maxProb = 0;
            double currentProb = 0;

            if (sourceChild.getChildren().size() == 0) {

                for (ParserNode targetChild : tNode.getChildren()) {
                    if (sourceChild.getValue() == targetChild.getValue()) {

                    }

                }

            } else {

                ParserNode removeNode = null;

                for (ParserNode targetChild : tNode.getChildren()) {
                    currentProb = probabilisticCompare(sourceChild,targetChild);
                    if (currentProb > maxProb) {
                        maxProb = currentProb;
                        removeNode = targetChild;
                    }
                }

                tNode.getChildren().remove(removeNode);

                totalProb += maxProb/souceNodes;
            }

        }
        return totalProb;
    }


}
