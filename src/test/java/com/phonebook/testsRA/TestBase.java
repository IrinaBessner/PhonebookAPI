package com.phonebook.testsRA;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;

public class TestBase {
    public static final String TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoiYWt1bmFAbWEuZGUiLCJpc3MiOiJSZWd1bGFpdCIsImV4cCI6MTcxNDYzOTU3MiwiaWF0IjoxNzE0MDM5NTcyfQ.VaMvtHdGGEwTkNKQh8cnBRWe5x6cAYdiC3pQ5NG6awE";
    public static final String AUTH = "Authorization";

    @BeforeMethod
    public void init() {
        RestAssured.baseURI = "https://contactapp-telran-backend.herokuapp.com";
        RestAssured.basePath = "v1";
    }
}
