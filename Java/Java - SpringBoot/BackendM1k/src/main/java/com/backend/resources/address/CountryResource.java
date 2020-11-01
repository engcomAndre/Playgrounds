package com.backend.resources.address;

import com.backend.entity.address.Country;
import com.backend.entity.dto.CountryDTO;
import com.backend.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("country")
public class CountryResource {

    @Autowired
    private CountryService countryService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Country>> getCountry(@PathVariable Integer id) {
        return ResponseEntity.ok().body(countryService.getCountries(id));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Country>> getCountries() {
        return ResponseEntity.ok().body(countryService.getCountries());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insertCountry(@RequestBody CountryDTO countryDTO) {
        Country country = countryService.insert(countryDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(country.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Country> updateCountry(@PathVariable Integer id, @RequestBody CountryDTO countryDTO) {
        return ResponseEntity.ok().body(countryService.updtCountry(id, countryDTO));
    }

}
