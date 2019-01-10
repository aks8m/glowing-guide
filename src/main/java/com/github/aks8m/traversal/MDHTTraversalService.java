package com.github.aks8m.traversal;

import com.github.aks8m.compare.comparisonobject.Comparison;
import com.github.aks8m.compare.engine.ComparisonUtility;
import com.github.aks8m.compare.tree.Node;
import com.github.aks8m.report.result.ResultTreeItem;
import com.github.aks8m.traversal.MethodType.InitializeEnums;
import com.github.aks8m.traversal.MethodType.NodeValueType;
import com.github.aks8m.traversal.MethodType.Pair;
import javafx.concurrent.Task;
import javafx.scene.control.TreeItem;
import org.eclipse.emf.common.util.EList;
import org.openhealthtools.mdht.uml.cda.ClinicalDocument;
import org.openhealthtools.mdht.uml.cda.Component3;

import java.lang.reflect.*;
import java.util.*;

public class MDHTTraversalService extends TraversalService {

    private ClinicalDocument sourceClinicalDocument;
    private ClinicalDocument targetClinicalDocument;
    private final ResultTreeItem sourceRoot;
    private final ResultTreeItem targetRoot;

    public MDHTTraversalService(ClinicalDocument sourceClinicalDocument, ClinicalDocument targetClinicalDocument,
                                ResultTreeItem sourceRoot, ResultTreeItem targetRoot) {
        this.sourceClinicalDocument = sourceClinicalDocument;
        this.targetClinicalDocument = targetClinicalDocument;
        this.sourceRoot = sourceRoot;
        this.targetRoot = targetRoot;
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
            Node childNode = null;
            if (target == null) {
                //set comparisonobject Object as source is not null but target is Null
//                rootNode.setComparison(new Comparison(null, ComparisonUtility.StringComparison()));
                rootNode.setComparison(new Comparison(null, ComparisonUtility.StringComparison(),"target is null", ""));
            } else {
                Object childSource = null;
                Object childTarget = null;
                List<Node> siblingNodes = null;

                for (Pair<String, NodeValueType> methodName : rootNode.getLocationType().getCallableMethods()) {
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
                                if (sourcelist != null) { //this null check is needed for weird edge case where IDs returns a list of length 1 but with all null contents
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
                                    if (methodName.getR() == NodeValueType.Component3s) {
                                        if (((Component3) sourcelist).getSection().getCode().getCode().equals(((Component3) targetlist).getSection().getCode().getCode())) {
                                            childNode = new Node(methodName.getR(), rootNode);
                                            siblingNodes.add(childNode);
                                            rootNode.addChild(childNode);
                                            reflectiveRecursiveTraversal(sourcelist, targetlist, childNode);
                                        }
                                    } else {
                                        childNode = new Node(methodName.getR(), rootNode);
                                        siblingNodes.add(childNode);
                                        rootNode.addChild(childNode);
                                        reflectiveRecursiveTraversal(sourcelist, targetlist, childNode);
                                    }

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
                    if (sourceAdd != null && targetAdd != null) {
                        sourceAdd = sourceAdd.getClass().getMethod(method).invoke(sourceAdd);
                        targetAdd = targetAdd.getClass().getMethod(method).invoke(targetAdd);
                    }
                }
                sources.add(sourceAdd);
                targets.add(targetAdd);
            }
            childNode.setComparison(new Comparison(null, ComparisonUtility.ObjectsListComparison(),sources,targets));
        }
    }




}


