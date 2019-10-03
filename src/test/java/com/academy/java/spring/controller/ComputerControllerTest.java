package com.academy.java.spring.controller;

import com.academy.java.spring.helper.ComputerHelper;
import com.academy.java.spring.model.Computer;
import com.academy.java.spring.service.ComputerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static com.academy.java.spring.helper.ComputerHelper.defaultComputerAnswer;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static com.academy.java.spring.helper.ComputerHelper.newComputer;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@WebMvcTest(ComputerController.class)
public class ComputerControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private ComputerService service;

    @Test
    public void testFindByIdSuccess() throws Exception {
        //setup
        given(service.findById("124")).willReturn(newComputer("124", "proc1", 5, 7));

        //exec
        ResultActions ret = mvc.perform(
                get("/computer/124")
                        .accept(MediaType.APPLICATION_JSON)
        );

        //assert
        ret
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is("124")))
                .andExpect(jsonPath("$.processor", is("proc1")))
                .andExpect(jsonPath("$.ram", is(5)))
                .andExpect(jsonPath("$.hd", is(7)))

        ;

        verify(service, atLeastOnce()).findById("124");

    }

    @Test
    public void testSaveSuccess() throws Exception {
        //setup
        Computer computerToSave = newComputer("proc2", 7, 11);

        given(service.save(any(Computer.class))).willAnswer(defaultComputerAnswer("1235"));

        String data = mapper.writeValueAsString(computerToSave);

        //exec
        ResultActions request = mvc.perform(
                post("/computer")
                        .content(data)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        );

        //assert - reponse
        request
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is("1235")))
                .andExpect(jsonPath("$.processor", is("proc2")))
                .andExpect(jsonPath("$.ram", is(7)))
                .andExpect(jsonPath("$.hd", is(11)))

        ;


        //assert - behavior
        ArgumentCaptor<Computer> argument = ArgumentCaptor.forClass(Computer.class);

        verify(service, atLeastOnce()).save(argument.capture());

        Computer ret = argument.getValue();

        Assert.assertNull(ret.getId());
        Assert.assertEquals("proc2", ret.getProcessor());
        Assert.assertEquals(Integer.valueOf(7), ret.getRam());
        Assert.assertEquals(Integer.valueOf(11), ret.getHd());

    }


}
