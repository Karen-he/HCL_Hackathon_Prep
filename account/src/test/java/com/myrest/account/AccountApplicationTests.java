package com.myrest.account;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;


@RunWith(SpringRunner.class)
@WebMvcTest
public class AccountApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountRepository accountRepository;


	@Test
	public void contextLoads() throws Exception{
	    Mockito.when(accountRepository.findAll()).thenReturn(
                Collections.emptyList()
        );

	    MvcResult mvcResult = mockMvc.perform(
	            MockMvcRequestBuilders.get("/mysql/accounts")
                .accept(MediaType.APPLICATION_JSON)
        ).andReturn();

	    System.out.println(mvcResult.getResponse());

	    Mockito.verify(accountRepository).findAll();
	}

}
