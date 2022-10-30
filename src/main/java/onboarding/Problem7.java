package onboarding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class Problem7 {
    static HashMap<String, HashSet<String>> graph = new HashMap<>();
    static HashMap<String, Integer> points = new HashMap<>();

    public static List<String> solution(String user, List<List<String>> friends, List<String> visitors) {
        List<String> answer = new ArrayList<>(Collections.emptyList());

        initFriendRelationship(friends);
        calculatePoint(user, visitors);

        return answer;
    }

    private static void calculatePoint(String user, List<String> visitors) {
        for (String visitor : visitors) {
            if (NotFoundFriendOnPoints(visitor)) {
                points.put(visitor, 0);
            }
            addPoint(visitor, 1);
            if (isVisitorNotInGraph(visitor)) {
                graph.put(visitor, new HashSet<>());
            }
            for (String target : graph.get(visitor)) {
                if (!Objects.equals(target, user)) {
                    addPoint(target, 10);
                }
            }
        }
    }

    private static void addPoint(String name, Integer point) {
        points.put(name, points.get(name) + point);
    }

    private static boolean isVisitorNotInGraph(String visitor) {
        return graph.get(visitor) == null;
    }

    private static void initFriendRelationship(List<List<String>> friends) {
        for (List<String> friend : friends) {
            if (NotFoundFriendOnPoints(friend.get(0))) {
                points.put(friend.get(0), 0);
            }
            if (NotFoundFriendOnPoints(friend.get(1))) {
                points.put(friend.get(1), 0);
            }
            if (NotFoundFriendOnGraph(friend.get(0))) {
                graph.put(friend.get(0), new HashSet<>());
            }
            if (NotFoundFriendOnGraph(friend.get(1))) {
                graph.put(friend.get(1), new HashSet<>());
            }
            InsertFriend(friend);
        }
    }

    private static boolean NotFoundFriendOnPoints(String name) {
        return !points.containsKey(name);
    }

    private static boolean NotFoundFriendOnGraph(String name) {
        return !graph.containsKey(name);
    }

    private static void InsertFriend(List<String> friend) {
        graph.get(friend.get(0)).add(friend.get(1));
        graph.get(friend.get(1)).add(friend.get(0));
    }

}
