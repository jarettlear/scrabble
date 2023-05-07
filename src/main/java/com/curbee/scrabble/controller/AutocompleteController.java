package com.curbee.scrabble.controller;

import com.curbee.scrabble.service.AutocompleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("autocomplete")
@RequiredArgsConstructor
public class AutocompleteController {

    private final AutocompleteService autocompleteService;

    @GetMapping("{prefix}")
    public ResponseEntity<List<String>> getAutocomplete(@PathVariable("prefix") String prefix) {
        var suggestions = autocompleteService.getAutocomplete(prefix);

        if (CollectionUtils.isEmpty(suggestions))
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();

        return ResponseEntity.ok(suggestions);
    }

}
