package graph;

import java.util.*;

public class CourseSchedule {
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        // Build adjacency list and indegree array
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        int[] indegree = new int[numCourses];

        for (int[] pre : prerequisites) {
            int course = pre[0];
            int prereq = pre[1];
            graph.get(prereq).add(course);
            indegree[course]++;
        }

        // Queue for courses with no prerequisites
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        int finished = 0;
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            finished++;
            for (int next : graph.get(curr)) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        // If we finished all courses, return true
        return finished == numCourses;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Read number of courses
        int numCourses = sc.nextInt();
        int m = sc.nextInt(); // number of prerequisite pairs
        int[][] prerequisites = new int[m][2];
        for (int i = 0; i < m; i++) {
            prerequisites[i][0] = sc.nextInt();
            prerequisites[i][1] = sc.nextInt();
        }

        System.out.println(canFinish(numCourses, prerequisites) ? "True" : "False");
        sc.close();
    }
}