package com.tomdoischer.phrasedemo.controller;

import com.tomdoischer.phrasedemo.dto.PhraseAccountConfigurationDto;
import com.tomdoischer.phrasedemo.entity.PhraseAccountConfiguration;
import com.tomdoischer.phrasedemo.service.PhraseAccountConfigurationService;
import org.modelmapper.ModelMapper;
import org.springframework.expression.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * The controller for operation with Phrase account configuration.
 */
@RestController
@RequestMapping("/account-configurations")
@CrossOrigin(origins = "*")
public class PhraseAccountConfigurationController {

    private final ModelMapper modelMapper;
    private final PhraseAccountConfigurationService accountConfigurationService;

    public PhraseAccountConfigurationController(ModelMapper modelMapper,
                                                PhraseAccountConfigurationService accountConfigurationService) {
        this.modelMapper = modelMapper;
        this.accountConfigurationService = accountConfigurationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public PhraseAccountConfigurationDto createAccountConfiguration(@RequestBody PhraseAccountConfigurationDto configurationDto) {
        PhraseAccountConfiguration configuration = convertToEntity(configurationDto);
        PhraseAccountConfiguration configurationCreated = accountConfigurationService.saveAccountConfig(configuration);
        return convertToDto(configurationCreated);
    }

    @GetMapping(value = "/{id}")
    public PhraseAccountConfigurationDto getAccountConfigurationById(@PathVariable Long id) {
        return convertToDto(accountConfigurationService.getById(id));
    }

    @GetMapping
    public PhraseAccountConfigurationDto getAccountConfigurationByUsername(@RequestParam(value = "username") String username) {
        return convertToDto(accountConfigurationService.getByUsername(username));
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public PhraseAccountConfigurationDto updateAccountConfiguration(@PathVariable Long id, @RequestBody PhraseAccountConfigurationDto configurationDto) {
        return convertToDto(accountConfigurationService.update(id, convertToEntity(configurationDto)));
    }

    private PhraseAccountConfigurationDto convertToDto(PhraseAccountConfiguration configuration) {
        return modelMapper.map(configuration, PhraseAccountConfigurationDto.class);
    }

    private PhraseAccountConfiguration convertToEntity(PhraseAccountConfigurationDto postDto) throws ParseException {
        return modelMapper.map(postDto, PhraseAccountConfiguration.class);
    }
}
