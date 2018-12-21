package com.github.aks8m.traversal;

import com.github.aks8m.compare.comparisonobject.Comparison;
import com.github.aks8m.compare.engine.ComparisonUtility;
import com.github.aks8m.compare.tree.Node;
import com.github.aks8m.report.ComparisonLocation;
import com.github.aks8m.traversal.MethodType.InitializeEnums;
import com.github.aks8m.traversal.MethodType.NodeValueType;
import com.github.aks8m.traversal.MethodType.Pair;
import javafx.concurrent.Task;
import org.eclipse.emf.common.util.EList;
import org.openhealthtools.mdht.uml.cda.ClinicalDocument;

import java.lang.reflect.*;
import java.util.*;

public class MDHTTraversalService extends TraversalService {

    private ClinicalDocument sourceClinicalDocument;
    private ClinicalDocument targetClinicalDocument;
    private List<Comparison> comparisons = new ArrayList<>();
    private ComparisonLocation currentLocation = new ComparisonLocation();

    public MDHTTraversalService(ClinicalDocument sourceClinicalDocument, ClinicalDocument targetClinicalDocument) {
        this.sourceClinicalDocument = sourceClinicalDocument;
        this.targetClinicalDocument = targetClinicalDocument;
    }


    @Override
    protected Task<Node> createTask() {

        return new Task<Node>() {

            @Override
            protected Node call() throws Exception {
                Node rootNode = new Node(NodeValueType.ClinicalDocument);

                InitializeEnums.initializeEnums();

                //recursive call to enter
                reflectiveRecursiveTraversal(sourceClinicalDocument,targetClinicalDocument,rootNode);

                return rootNode;
            }
        };
    }




    private void reflectiveRecursiveTraversal(Object source, Object target, Node rootNode) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        if (source != null) {
            if (target == null) {
                //set comparisonobject Object as source is not null but target is Null
            } else {
                Object childSource = null;
                Object childTarget = null;
                List<Node> siblingNodes = null;
                Node childNode = null;

                for (Pair<String, NodeValueType> methodName : rootNode.getLocation().getCallableMethods()) {
                    switch (methodName.getR().getMethodType()) {
                        case ValueNode:
                            childSource = source.getClass().getMethod(methodName.getL()).invoke(source);
                            childTarget = target.getClass().getMethod(methodName.getL()).invoke(target);
                            if (childSource != null) {
                                childNode = new Node(methodName.getR(), rootNode);
                                rootNode.addChild(childNode);
                                setChildComparison(methodName,childSource,childTarget,childNode);
                            }
                            break;

                        case ValueNodeList:
                            for (Object sourcelist : (EList<Object>) source.getClass().getMethod(methodName.getL()).invoke(source)) {
                                siblingNodes = new ArrayList<>();
                                for (Object targetlist : (EList<Object>) target.getClass().getMethod(methodName.getL()).invoke(target)) {
                                    childNode = new Node(methodName.getR(),rootNode);
                                    siblingNodes.add(childNode);
                                    rootNode.addChild(childNode);

                                    //set comparisonobject for child node
                                    setChildComparison(methodName,sourcelist,targetlist,childNode);
                                }
                                for (int i = 0; i < siblingNodes.size(); i++) {
                                    siblingNodes.get(i).addSiblings(siblingNodes.subList(0, i));
                                    siblingNodes.get(i).addSiblings(siblingNodes.subList(i + 1, siblingNodes.size()));

                                }
                            }
                            break;

                        case ComplexLocation:
                            childSource = source.getClass().getMethod(methodName.getL()).invoke(source);
                            childTarget = target.getClass().getMethod(methodName.getL()).invoke(target);
                            if (childSource != null) {
                                childNode = new Node(methodName.getR(), rootNode);
                                rootNode.addChild(childNode);
                                reflectiveRecursiveTraversal(childSource, childTarget, childNode);
                            }
                            break;


                        case ComplexLocationList:
                            for (Object sourcelist : (EList<Object>) source.getClass().getMethod(methodName.getL()).invoke(source)) {
                                siblingNodes = new ArrayList<>();
                                for (Object targetlist : (EList<Object>) target.getClass().getMethod(methodName.getL()).invoke(target)) {
                                    childNode = new Node(methodName.getR(), rootNode);
                                    siblingNodes.add(childNode);
                                    rootNode.addChild(childNode);
                                    reflectiveRecursiveTraversal(sourcelist, targetlist, childNode);
                                }
                                for (int i = 0; i < siblingNodes.size(); i++) {
                                    siblingNodes.get(i).addSiblings(siblingNodes.subList(0, i));
                                    siblingNodes.get(i).addSiblings(siblingNodes.subList(i + 1, siblingNodes.size()));
                                }
                            }
                            break;
                    }
                }

            }
        }
    }

    private void setChildComparison(Pair<String, NodeValueType> methodName, Object sourcelist, Object targetlist, Node childNode) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if (targetlist == null) {
            childNode.setComparison(new Comparison(null, ComparisonUtility.StringComparison(),"target is null", ""));
        } else {
            List<Object> sources = new ArrayList<>();
            List<Object> targets = new ArrayList<>();
            for (List<String> methodList : methodName.getR().getValueMethods()) {
                Object sourceAdd = sourcelist;
                Object targetAdd = targetlist;
                for (String method : methodList) {
                    sourceAdd = sourceAdd.getClass().getMethod(method).invoke(sourceAdd);
                    targetAdd = targetAdd.getClass().getMethod(method).invoke(targetAdd);
                }
                sources.add(sourceAdd);
                targets.add(targetAdd);
            }
            childNode.setComparison(new Comparison(null, ComparisonUtility.ObjectsListComparison(),sources,targets));
        }
    }




}


