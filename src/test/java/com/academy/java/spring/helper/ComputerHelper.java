package com.academy.java.spring.helper;

import com.academy.java.spring.model.Computer;
import org.mockito.stubbing.Answer;

public class ComputerHelper {

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

}
