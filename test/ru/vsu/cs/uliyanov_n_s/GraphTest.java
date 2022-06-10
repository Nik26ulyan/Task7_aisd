package ru.vsu.cs.uliyanov_n_s;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.vsu.cs.uliyanov_n_s.Graph;
import ru.vsu.cs.uliyanov_n_s.utils.GraphUtils;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    @Test
    void wayFindingTest1() {
        String inputGraphStr = "a c 9\n" +
                "a b 7\n" +
                "a f 14\n" +
                "b c 10\n" +
                "b d 15\n" +
                "c d 11\n" +
                "c f 2\n" +
                "d e 6\n" +
                "e f 9\n" +
                "f e 9";
        String badCities = "f b";
        Graph inputGraph = GraphUtils.fromString(inputGraphStr);
        String actualResult = inputGraph.findWayWithDijkstraWithBadVertexes("a", "e", badCities);
        String expectedResult = "a -> c(9km) -> d(20km) -> e(26km)";

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void wayFindingTest2() {
        String inputGraphStr = "a c 20\n" +
                "a b 7\n" +
                "a f 14\n" +
                "b c 10\n" +
                "f c 4\n" +
                "b f 3\n" +
                "d a 5\n" +
                "c d 12";
        String badCities = "f";
        Graph inputGraph = GraphUtils.fromString(inputGraphStr);
        String actualResult = inputGraph.findWayWithDijkstraWithBadVertexes("a", "c", badCities);
        String expectedResult = "a -> b(7km) -> c(17km)";

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void wayFindingTest3() {
        String inputGraphStr = "a c 20\n" +
                "a b 7\n" +
                "a f 14\n" +
                "b c 10\n" +
                "f c 4";
        String badCities = "b";
        Graph inputGraph = GraphUtils.fromString(inputGraphStr);
        String actualResult = inputGraph.findWayWithDijkstraWithBadVertexes("a", "c", badCities);
        String expectedResult = "a -> f(14km) -> c(18km)";

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void wayFindingTest4() {
        String inputGraphStr = "a b 10\n" +
                "b c 30\n" +
                "b f 20\n" +
                "a f 40\n" +
                "d e 15\n" +
                "c d 25\n" +
                "a c 25\n" +
                "e f 5\n" +
                "e b 35\n" +
                "f d 40\n" +
                "f c 25\n" +
                "d a 10\n" +
                "g e 40\n" +
                "b g 10";
        String badCities = "c g b";
        Graph inputGraph = GraphUtils.fromString(inputGraphStr);
        String actualResult = inputGraph.findWayWithDijkstraWithBadVertexes("a", "e", badCities);
        String expectedResult = "a -> f(40km) -> d(80km) -> e(95km)";

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void wayFindingTest5() {
        String inputGraphStr = "a b 10\n" +
                "b c 30\n" +
                "b f 20\n" +
                "d a 10\n" +
                "g e 40\n" +
                "b g 10\n" +
                "a e 15\n" +
                "c a 5\n" +
                "d b 10";
        String badCities = "a f c";
        Graph inputGraph = GraphUtils.fromString(inputGraphStr);
        String actualResult = inputGraph.findWayWithDijkstraWithBadVertexes("d", "e", badCities);
        String expectedResult = "d -> b(10km) -> g(20km) -> e(60km)";

        assertEquals(expectedResult, actualResult);
    }
}