package com.github.aks8m.compare;

import com.github.aks8m.compare.tree.Node;
import com.github.aks8m.report.ComparisonLocation;
import com.github.aks8m.report.result.Result;
import com.github.aks8m.report.result.ResultType;
import com.github.aks8m.traversal.MethodType.NodeValueType;
import javafx.concurrent.Task;
import java.util.*;

public class MDHTComparisonService extends ComparisonService {

    private Node rootNode;
    private List<Result> resultList;
    private ComparisonLocation location;
    private List<Node> skipList = new ArrayList<>();

    public MDHTComparisonService() {
        resultList = new ArrayList<>();
        rootNode = null;
        location = new ComparisonLocation();
    }

    public void setRootNode(Node rootNode) {
        this.rootNode = rootNode;
    }

    @Override
    protected Task<List<Result>> createTask() {

        return new Task<List<Result>>() {

            @Override
            protected List<Result> call() throws Exception {

                resultList = recursiveComparison(rootNode);

                return resultList;
            }

        };
    }

    private List<Result> recursiveComparison(Node topNode) {
        location.enter(topNode.getLocationType().toString());
        List<Result> totalRetList = new ArrayList<>();
        List<Result> ret = new ArrayList<>();

        for (Node childNode : topNode.getChildren()) {
            if (!skipList.contains(childNode)) {
                if (childNode.getSiblings().size() > 0) {


                    //if it is value nodeList
                    if (childNode.getLocationType().getMethodType() == NodeValueType.MethodType.ValueNodeList) {
                        Result result = childNode.getComparison().compare();

                        if (result.getResultType() == ResultType.MISMATCH) {
                            Boolean matched = false;
                            for (Node siblingNode : childNode.getSiblings()) {
                                if (siblingNode.getComparison().compare().getResultType() == ResultType.MATCH) {
                                    matched = true;
                                    break;
                                }
                            }
                            if (!matched) {
//                            result.setComparison(childNode.getComparison().setComparisonLocation(new ComparisonLocation(location).enter(childNode.getLocationType().toString())));
                                totalRetList.add(result);
                            }
                        }

                    } else { //(complex location List)

                        List<Result> complexReturnList = recursiveComparison(childNode);

                        if (complexReturnList.size() != 0) {
                            //check siblings
                            Boolean matched = false;
                            for (Node siblingNode : childNode.getSiblings()) {
                                ret = recursiveComparison(siblingNode);
                                if (ret.size() == 0) {
                                    matched = true;
                                    break;
                                }
                            }
                            if (!matched) {
                                //add siblings to skip list
                                skipList.addAll(childNode.getSiblings());

                                //add not found result for source
                                Result res = new Result("Source section \"" + new ComparisonLocation(location).enter(childNode.getLocationType().toString()).formattedLocation() + "\" did not have an exact match in the target");
                                res.setResultType(ResultType.SECTIONMATCHNOTFOUND);
                                totalRetList.add(res);

//                                totalRetList.addAll(complexReturnList);
                            }
                        }

                    }

                } else {
                    //if it is valuenode or valuenodelist without siblings
                    if (childNode.getLocationType().getMethodType() == NodeValueType.MethodType.ValueNode
                            || childNode.getLocationType().getMethodType() == NodeValueType.MethodType.ValueNodeList) {
                        Result result = childNode.getComparison().compare();
                        if (result.getResultType() == ResultType.MISMATCH) {
//                        result.setComparison(childNode.getComparison().setComparisonLocation(new ComparisonLocation(location).enter(childNode.getLocationType().toString())));
                            totalRetList.add(result);
                        }


                    } else { //(complexlocation or complexlocationlist without siblings)
                        totalRetList.addAll(recursiveComparison(childNode));
                    }

                }
            }


        }
        location.exit();
        return totalRetList;
    }
}
