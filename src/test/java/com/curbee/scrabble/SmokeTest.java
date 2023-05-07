package com.curbee.scrabble;

import com.curbee.scrabble.controller.AutocompleteController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class SmokeTest {

    @Autowired
    private AutocompleteController autocompleteController;

    @Test
    public void contextLoads() throws Exception {
        assertThat(autocompleteController).isNotNull();
    }
}
