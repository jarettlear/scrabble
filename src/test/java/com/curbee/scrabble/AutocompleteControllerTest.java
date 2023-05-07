package com.curbee.scrabble;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AutocompleteControllerTest {

    @Value(value="${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void autoComplete() {
       assertThat(this.restTemplate.getForEntity("http://localhost:" + port + "/autocomplete/get", List.class)
               .getBody()).asList().contains(
                "GET", "GETA", "GETABLE", "GETAS", "GETATABLE", "GETAWAY", "GETAWAYS", "GETOUT", "GETOUTS", "GETS");
        System.out.println();
    }
}
