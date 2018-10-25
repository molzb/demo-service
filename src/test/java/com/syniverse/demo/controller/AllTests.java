package com.syniverse.demo.controller;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.syniverse.demo.controller.remote.RemoteWebserviceCallTest;

@RunWith(Suite.class)
@SuiteClasses({HelloControllerTest.class, RemoteWebserviceCallTest.class})
public class AllTests {

}
