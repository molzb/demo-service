package com.syniverse.demo.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.syniverse.demo.controller.mdm.CountryController;
import com.syniverse.demo.model.Country;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CountryControllerIT {

	@Autowired
	CountryController ctrl;
	@Autowired
	WebApplicationContext ctx;

	@Before
	public void setUp() throws Exception {
		MockMvcBuilders.webAppContextSetup(ctx).build();
	}

	@Test
	public void testGetCountries() {
		Integer countriesSize = ctrl.getCountries().size();
		assertTrue("The countries table should not be empty.", countriesSize > 0);
	}

	@Test
	public void testGetCountryNames() {
		Integer countryNamesSize = ctrl.getCountryNames().size();
		assertTrue("The country names should not be empty.", countryNamesSize > 0);
	}

	@Test
	public void testGetCountry() {
		Country country = ctrl.getCountry("UNK");
		assertEquals("UNK should map to Unknown.", "Unknown", country.getName());
	}

	@Test
	public void t1_testInsertCountry() {
		boolean rowsInserted = ctrl.insertCountry(new Country("SES", "San Escobar"));
		assertEquals("There should be 1 new row.", true, rowsInserted);
		assertEquals("SES should map to 'San Escobar'", "San Escobar", ctrl.getCountry("SES").getName());
	}

	@Test
	public void t2_testUpdateCountry() {
		boolean updateCountry = ctrl.updateCountry(new Country("SES", "San Escabor"));
		assertEquals("There should be 1 new row.", true, updateCountry);
		assertEquals("SES should NOW map to 'San Escabor'", "San Escabor", ctrl.getCountry("SES").getName());
	}

	@Test
	public void t3_testDeleteCountry() {
		boolean rowsDeleted = ctrl.deleteCountry("SES");
		assertEquals("There should be 1 deleted row.", true, rowsDeleted);
		assertEquals("SES should NOW return null", null, ctrl.getCountry("SES"));
	}

}
