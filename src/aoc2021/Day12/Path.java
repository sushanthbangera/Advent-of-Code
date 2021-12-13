package aoc2021.Day12;

import java.util.*;

public class Path {

    private List<String> path;

    //Added just for tracing the path
    private Set<String> visitedSmallCaves;

    private String twiceVisitedNode;

    public Path(List<String> paths) {
        this.path = paths;
        this.visitedSmallCaves = new HashSet<>();
    }

    public Path(List<String> paths, Set<String> visitedSmallCaves) {
        this.path = paths;
        this.visitedSmallCaves = visitedSmallCaves;
    }

    public List<String> getPath() {
        return path;
    }

    public void setPath(List<String> path) {
        this.path = path;
    }

    public Set<String> getVisitedSmallCaves() {
        return visitedSmallCaves;
    }

    public void setVisitedSmallCaves(Set<String> visitedSmallCaves) {
        this.visitedSmallCaves = visitedSmallCaves;
    }

    public String toString() {
        return this.getPath().toString();
    }

    public String getTwiceVisitedNode() {
        return twiceVisitedNode;
    }

    public void setTwiceVisitedNode(String twiceVisitedNode) {
        this.twiceVisitedNode = twiceVisitedNode;
    }
}
