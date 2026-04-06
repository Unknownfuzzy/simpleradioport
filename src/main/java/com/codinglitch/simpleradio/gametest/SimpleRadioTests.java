package com.codinglitch.simpleradio.gametest;

import com.codinglitch.simpleradio.gametest.tests.SystemTest;
import net.minecraft.gametest.framework.*;

import java.util.*;
import java.util.stream.Stream;

public class SimpleRadioTests {
    @GameTestGenerator
    public static Collection<TestFunction> generateTests() {
        return Stream.of(SystemTest.class)
                .map(SimpleRadioTestFunction::from)
                .flatMap(Collection::stream)
                .toList();
    }
}
