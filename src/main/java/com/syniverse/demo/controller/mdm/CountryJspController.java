package com.syniverse.demo.controller.mdm;

import java.sql.ResultSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.syniverse.demo.model.Country;

@Controller
public class CountryJspController {
	@Autowired
	private JdbcTemplate jdbcTpl;

	private List<Country> cachedCountries;

	public List<Country> getCountries() {
		String sql = "SELECT mdm_country_id, name FROM mdm_countrytbl";
		return jdbcTpl.query(sql, (ResultSet rs, int rowNum) -> new Country(rs.getString(1), rs.getString(2)));
	}

	@GetMapping("/countriesJsp")
	public String getCountriesJsp(Model model) {
		if (cachedCountries == null)
			cachedCountries = getCountries();
		model.addAttribute("countries", cachedCountries);
		return "countries"; // JSP file
	}
}
