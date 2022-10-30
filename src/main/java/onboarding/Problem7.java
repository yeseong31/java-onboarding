package onboarding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;

public class Problem7 {
    static HashMap<String, HashSet<String>> graph = new HashMap<>();
    static HashMap<String, Integer> points = new HashMap<>();

    public static List<String> solution(String user, List<List<String>> friends, List<String> visitors) {
        List<String> answer = new ArrayList<>(Collections.emptyList());

        initFriendRelationship(friends);
        calculatePoint(user, visitors);

        for (Entry<String, Integer> target : sortPoints()) {
            if (notValidateTarget(user, target.getKey())) {
                continue;
            }
            if (notContainUser(target.getKey(), user)) {
                answer.add(target.getKey());
            }
        }

        return answer;
    }

    private static List<Entry<String, Integer>> sortPoints() {
        List<Entry<String, Integer>> candidates = new ArrayList<>(points.entrySet());
        candidates.sort((obj1, obj2) ->
            obj2.getValue().compareTo(obj1.getValue())
        );
        return candidates;
    }

    private static boolean notContainUser(String key, String user) {
        return !graph.get(key).contains(user);
    }

    private static boolean notValidateTarget(String user, String target) {
        if (Objects.equals(target, user)) {
            return true;
        }
        if (points.get(target) <= 0) {
            return true;
        }
        return isVisitorNotInGraph(target);
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
