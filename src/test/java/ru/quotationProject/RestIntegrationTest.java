package ru.quotationProject;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.quotationProject.entity.Quote;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RestIntegrationTest extends AbstractIntegrationTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void createQuote() throws Exception {
        String uri = "http://localhost:8080/api/quotes";
        Quote quote = new Quote();
        quote.setIsin("RU000A0JX0S2");
        quote.setBid(100.2);
        quote.setAsk(101.9);

        String inputJson = super.mapToJson(quote);

        MvcResult mvcResult= mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        HashMap<String,Double> result =
                new ObjectMapper().readValue(content, HashMap.class);
        assertEquals(100.2, result.get("elvl"));
    }

    @Test
    public void getElvlById() throws Exception {

        String uri = "http://localhost:8080/api/quotes";
        Quote quote = new Quote();
        quote.setIsin("RU000A0JX0S2");
        quote.setBid(100.5);
        quote.setAsk(101.9 );

        String inputJson = super.mapToJson(quote);

        MvcResult mvcResult= mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String uriGet = "http://localhost:8080/api/elvls/RU000A0JX0S2";
        MvcResult mvcResultGet = mvc.perform(MockMvcRequestBuilders.get(uriGet)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int statusGet = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String contentGet = mvcResultGet.getResponse().getContentAsString();
        HashMap<String,Double> result =
                new ObjectMapper().readValue(contentGet, HashMap.class);
        assertEquals(100.5, result.get("RU000A0JX0S2"));


    }

}
