package com.academy.java.spring;

import com.academy.java.spring.model.Computer;
import com.academy.java.spring.repository.ComputerRepository;
import com.academy.java.spring.service.ComputerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static com.academy.java.spring.helper.ComputerHelper.defaultComputerAnswer;
import static com.academy.java.spring.helper.ComputerHelper.newComputer;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class ComputerServiceTest {

    @Mock
    private ComputerRepository repository;

    private ComputerService service;

    @Rule
    public ExpectedException exceptionExpect = ExpectedException.none();

    @Before
    public void setUp() {
        this.service = new ComputerService(repository);

    }


    @Test
    public void testComputerFindByIdReturningNull() {
        //setup
        given(repository.findById("123")).willReturn(Optional.empty());

        //exec
        Computer ret = service.findById("123");

        //assert
        verify(repository, atLeastOnce()).findById("123");
        Assert.assertNull(ret);
    }

    @Test
    public void testComputerFindByIdReturningObject() {
        //setup
        given(repository.findById("124")).willReturn(Optional.of(new Computer()));

        //exec
        Computer ret = service.findById("124");

        //assert
        verify(repository, atLeastOnce()).findById("124");
        Assert.assertNotNull(ret);
    }

    @Test
    public void testComputerSaveSuccess() {
        //setup
        given(repository.save(any(Computer.class))).willAnswer(defaultComputerAnswer("1234"));
        Computer computerToSave = newComputer("processor1", 1, 2);

        //exec
        Computer ret = service.save(computerToSave);

        //assert
        ArgumentCaptor<Computer> argument = ArgumentCaptor.forClass(Computer.class);

        verify(repository, atLeastOnce()).save(argument.capture());
        Assert.assertEquals("processor1", argument.getValue().getProcessor());

        Assert.assertEquals("1234", ret.getId());
        Assert.assertEquals("processor1", ret.getProcessor());
        Assert.assertEquals(Integer.valueOf(1), ret.getRam());
        Assert.assertEquals(Integer.valueOf(2), ret.getHd());

    }
}
