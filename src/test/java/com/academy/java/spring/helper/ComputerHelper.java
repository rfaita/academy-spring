package com.academy.java.spring.helper;

import com.academy.java.spring.model.Computer;
import org.mockito.stubbing.Answer;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class ComputerHelper {

    private ComputerHelper() {
    }

    public static Computer newComputer(String processor, Integer ram, Integer hd) {
        Computer ret = new Computer();
        ret.setHd(hd);
        ret.setRam(ram);
        ret.setProcessor(processor);

        return ret;
    }

    public static Answer<Computer> defaultComputerAnswer(final String id) {
        return (Answer<Computer>) invocationOnMock -> {
            Computer arg = invocationOnMock.getArgument(0);

            Computer ret = newComputer(arg.getProcessor(),
                    arg.getRam(),
                    arg.getHd());
            ret.setId(id);
            return ret;
        };
    }

    public static List<Computer> newComputerList(long seed, Integer size) {

        Random random = new Random(seed);

        return IntStream.range(1, size + 1)
                .mapToObj(i -> newComputer("processor" + i, random.nextInt(100), random.nextInt(100)))
                .collect(Collectors.toList());

    }

}
