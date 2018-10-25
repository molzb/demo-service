package com.syniverse.demo.controller.mdm;

import java.sql.ResultSet;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.syniverse.demo.model.Country;

@RestController
public class CountryController {
	private Logger logger = LoggerFactory.getLogger(CountryController.class.getSimpleName());
	@Autowired
	private JdbcTemplate jdbcTpl;
	
	@Autowired
	private HttpServletResponse response;
	
	@GetMapping("/countries")
	public List<Country> getCountries() {
		String sql = "SELECT mdm_country_id, name FROM mdm_countrytbl";
		return jdbcTpl.query(sql, (ResultSet rs, int rowNum) -> new Country(rs.getString(1), rs.getString(2)));
	}
	
	@GetMapping("/countryNames")
	public List<String> getCountryNames() {
		response.addHeader("Access-Control-Allow-Origin", "*");
		String sql = "SELECT name FROM mdm_countrytbl ORDER BY name";
		return jdbcTpl.query(sql, (ResultSet rs, int rowNum) -> rs.getString(1));
	}

	// @GetMapping(value = "/countriesXml", produces = MediaType.APPLICATION_XML_VALUE)
	// public List<Country> getCountriesXml() {
	// String sql = "SELECT mdm_country_id, name FROM mdm_countrytbl";
	// return jdbcTpl.query(sql, (ResultSet rs, int rowNum) -> new Country(rs.getString(1), rs.getString(2)));
	// }

	@GetMapping(value = "/country/{countryId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Country getCountry(@PathVariable String countryId) {
		String sql = "SELECT mdm_country_id, name FROM mdm_countrytbl WHERE mdm_country_id = ?";
		try {
			SqlRowSet country = jdbcTpl.queryForRowSet(sql, countryId);
			country.first();
			return new Country(country.getString(1), country.getString(2));
		} catch (DataAccessException | ArrayIndexOutOfBoundsException dae) {
			logger.warn(dae.getMessage());
			return null;
		}
	}

	@GetMapping(value = "/country2/{countryId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Country getCountry2(@PathVariable String countryId) {
		String sql = "SELECT mdm_country_id, name FROM mdm_countrytbl WHERE mdm_country_id = ?";
		Object[] sqlParams = new Object[] { countryId };
		return jdbcTpl.queryForObject(sql, sqlParams, (ResultSet rs, int rowNum) -> new Country(rs.getString(1), rs.getString(2)));
	}

	@PostMapping("/country")
	public boolean insertCountry(@RequestBody Country country) {
		String sql = "INSERT INTO mdm_countrytbl (mdm_country_id, name, is_iso_country, is_active) VALUES (?,?,'Y','Y')";
		int rowsUpdated = jdbcTpl.update(sql, country.getId(), country.getName());
		return rowsUpdated > 0;
	}

	@PutMapping("/country")
	public boolean updateCountry(@RequestBody Country c) {
		String sql = "UPDATE mdm_countrytbl SET name=? WHERE mdm_country_id=?";
		int rowsUpdated = jdbcTpl.update(sql, c.getName(), c.getId());
		return rowsUpdated > 0;
	}

	@DeleteMapping("/country/{id}")
	public boolean deleteCountry(@PathVariable String id) {
		String sql = "DELETE FROM mdm_countrytbl WHERE mdm_country_id=?";
		int rowsUpdated = jdbcTpl.update(sql, id);
		return rowsUpdated > 0;
	}
}

