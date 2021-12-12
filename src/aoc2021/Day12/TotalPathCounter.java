package aoc2021.Day12;

import aoc2021.utils.FileUtils;

import java.util.*;

public class TotalPathCounter {

    protected static String inputPath1 = FileUtils.BASE_PATH + "Day12\\input1";
    protected static String inputPath2 = FileUtils.BASE_PATH + "Day12\\input2";

    public static void main(String[] args) {
        List<String> input1 = FileUtils.readInputFile(inputPath1);
        List<String> input2 = FileUtils.readInputFile(inputPath2);

        System.out.println(getTotalPaths(input1));
        System.out.println(getTotalPaths(input2));

        System.out.println(getTotalPathsWithTwiceAllowed(input1));
        System.out.println(getTotalPathsWithTwiceAllowed(input2));
    }

    private static int getTotalPaths(List<String> input) {
        Map<String, List<String>> graph = buildGraph(input);
        printGraph(graph);

        int pathCount = 0;
        Queue<Node> queue = new LinkedList<>();
        queue.add(createStartNode());

        while (!queue.isEmpty()) {
            Node curNode = queue.remove();
            Path curPath = curNode.getPath();
            List<String> destinationList = graph.get(curNode.getName());

            for (String destination : destinationList) {
                if (destination.equals("end")) {
                    pathCount++;
                } else {
                    boolean isLowerCave = isStringLowerCase(destination);
                    if (!curPath.getVisitedSmallCaves().contains(destination)) {
                        List<String> newPath = addCurrentDestinationToPath(curPath, destination);
                        Set<String> visited = updatedVisitedList(curPath, destination, isLowerCave);
                        queue.add(new Node(destination, new Path(newPath, visited)));
                    }
                }
            }
        }
        return pathCount;
    }

    private static int getTotalPathsWithTwiceAllowed(List<String> input) {
        Map<String, List<String>> graph = buildGraph(input);
        printGraph(graph);

        int pathCount = 0;
        Queue<Node> queue = new LinkedList<>();
        queue.add(createStartNode());

        while (!queue.isEmpty()) {
            Node curNode = queue.remove();
            Path curPath = curNode.getPath();
            List<String> destinationList = graph.get(curNode.getName());

            for (String destination : destinationList) {
                if (destination.equals("end")) {
                    pathCount++;
                } else {
                    boolean isLowerCave = isStringLowerCase(destination);
                    if (curPath.getTwiceVisitedNode() == null || !curPath.getVisitedSmallCaves().contains(destination)) {
                        List<String> newPath = addCurrentDestinationToPath(curPath, destination);
                        Set<String> visited = updatedVisitedList(curPath, destination, isLowerCave);
                        Path updatedPath = new Path(newPath, visited);
                        updatedPath.setTwiceVisitedNode(curPath.getTwiceVisitedNode());
                        if (curPath.getTwiceVisitedNode() == null && curPath.getVisitedSmallCaves().contains(destination)) {
                            updatedPath.setTwiceVisitedNode(destination);
                        }
                        queue.add(new Node(destination, updatedPath));
                    }
                }
            }
        }
        return pathCount;
    }

    protected static Map<String, List<String>> buildGraph(List<String> input) {
        Map<String, List<String>> graph = new HashMap<>();
        // don't add start to destination lists and don't create a list for end
        for (String in : input) {
            String[] edge = in.split("-");

            if (!edge[0].equals("end") && !edge[1].equals("start")) {
                List<String> adjacentList = graph.getOrDefault(edge[0], new ArrayList<>());
                adjacentList.add(edge[1]);
                graph.put(edge[0], adjacentList);
            }

            if (!edge[0].equals("start") && !edge[1].equals("end")) {
                List<String> adjacentList = graph.getOrDefault(edge[1], new ArrayList<>());
                adjacentList.add(edge[0]);
                graph.put(edge[1], adjacentList);
            }
        }
        return graph;
    }

    protected static void printGraph(Map<String, List<String>> graph) {
        graph.entrySet().stream().forEach(e -> System.out.println(e.getKey() + "->" + e.getValue()));
    }

    protected static Node createStartNode() {
        List<String> startPathMap = new ArrayList<>();
        startPathMap.add("start");
        return new Node("start", new Path(startPathMap));
    }

    protected static boolean isStringLowerCase(String str) {
        return str.equals(str.toLowerCase());
    }

    protected static List<String> addCurrentDestinationToPath(Path path, String destination) {
        List<String> newPathList = new ArrayList<>();
        newPathList.addAll(path.getPath());
        newPathList.add(destination);
        return newPathList;
    }

    protected static Set<String> updatedVisitedList(Path path, String destination, boolean isLowerCave) {
        Set<String> visited = new HashSet<>();
        visited.addAll(path.getVisitedSmallCaves());
        if (isLowerCave) {
            visited.add(destination);
        }
        return visited;
    }
}
