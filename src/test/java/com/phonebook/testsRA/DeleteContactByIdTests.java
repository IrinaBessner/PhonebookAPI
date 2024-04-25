package com.phonebook.testsRA;

import com.phonebook.dto.ContactDto;
import com.phonebook.dto.ErrorDto;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DeleteContactByIdTests extends TestBase {
    String id;

    @BeforeMethod
    public void precondition() {
        ContactDto contactDto = ContactDto.builder()
                .name("Hans")
                .lastName("Müller")
                .email("hans@gm.com")
                .phone("1239877899")
                .address("Hamburg")
                .description("keeper")
                .build();

        String message = given()
                .header(AUTH, TOKEN)
                .body(contactDto)
                .contentType(ContentType.JSON)
                .post("contacts")
                .then()
                .assertThat().statusCode(200)
                .extract().path("message");
        //System.out.println(message); Contact was added! ID: 9276d2e9-7757-4bd7-bf9e-11e10929bba1

        String[] split = message.split(": ");
        id=split[1];

    }
    @Test
    public void deleteContactByIdSuccessTest(){
         given()
                .header(AUTH, TOKEN)
                .delete("contacts/" + id)
                .then()
                .assertThat().statusCode(200)
                //.extract().path("message");
                        .assertThat().body("message",equalTo("Contact was deleted!"));
       // System.out.println(message);
    }
    @Test
    public void deleteContactByIdNegativeTestNotFound(){
        id ="f15c99c2-0b14";
        ErrorDto errorResponse = given()
                .header(AUTH, TOKEN)
                .delete("contacts/" + id)
                .then()
                .assertThat().statusCode(400)
                .extract()
                .as(ErrorDto.class);

        System.out.println(errorResponse.getMessage());
    }
    @Test
    public void deleteContactByIdNegativeTestUnauthorized(){

        ErrorDto errorResponse = given()
                .header(AUTH, "gklub")
                .delete("contacts/" + id)
                .then()
                .assertThat().statusCode(401)
                .extract()
                .as(ErrorDto.class);

        System.out.println(errorResponse.getMessage());
        System.out.println(errorResponse.getError());
    }
}
