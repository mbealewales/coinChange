package com.mbeale;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ChangeMachineTest {
    private static ChangeMachine changeMachine;

    @BeforeAll

    static void startup() {
        changeMachine = new ChangeMachine();
    }

    private List<Integer> toList(int[] iArr) {
        return Arrays.stream(iArr).boxed().collect(Collectors.toList());
    }

    @Test
    void test1() {
        List<Integer> change = changeMachine.change(Arrays.asList(200, 100, 50, 20, 10, 5, 2, 1) ,13);
        int[] array = {10,2,1};
        List<Integer> expectedResult = toList(array);
        Assertions.assertEquals(expectedResult, change);
    }

    @Test
    void test2() {
        List<Integer> change = changeMachine.change(Arrays.asList(5,5,2,2,2) ,11);
        int[] array = {5,2,2,2};
        List<Integer> expectedResult = toList(array);
        Assertions.assertEquals(expectedResult, change);
    }
    @Test
    void test3() {
        List<Integer> change = changeMachine.change(Arrays.asList(200, 100, 20, 200, 10, 5, 50, 2, 1, 1, 2), 574);
        int[] array = {200, 200, 100, 50, 20, 2, 2};
        List<Integer> expectedResult = toList(array);
        Assertions.assertEquals(expectedResult, change);
    }

    @Test
    void test4() {
        List<Integer> change = changeMachine.change(Arrays.asList(200, 100, 20, 200, 10, 5, 50, 2, 1, 1), 574);
        int[] array = {200, 200, 100, 50, 20, 2, 1, 1};
        List<Integer> expectedResult = toList(array);
        Assertions.assertEquals(expectedResult, change);
    }

    @Test
    void test6() { //close but ran out of coins
        try {
            List<Integer> change = changeMachine.change(Arrays.asList(200, 100, 20, 200, 10, 5, 50, 2, 1), 574);
            // MB: Assumed you'd want to handle as exception, not just return partial here!!

            //int[] array = {200, 200, 100, 50, 20, 2, 1};
            //List<Integer> expectedResult = toList(array);
            //Assertions.assertEquals(expectedResult, change);
        } catch (RuntimeException e) {
            int[] array = {200, 200, 100, 50, 20, 2, 1};
            List<Integer> expectedResult = toList(array);
            List<Integer> change = new ArrayList<Integer>();
            for (String item : e.getMessage().split(",")) {
                change.add(Integer.decode(item));
            }
            Assertions.assertEquals(expectedResult, change);
        }
    }
}
