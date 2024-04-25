package com.phonebook.testsRA;

import com.phonebook.dto.AllContactsDto;
import com.phonebook.dto.AuthResponseDto;
import com.phonebook.dto.ContactDto;
import com.phonebook.dto.ErrorDto;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetAllContactsTests extends TestBase {

    @Test
    public void getAllContactsSuccessTest() {
        AllContactsDto contactsDto = given()
                .header(AUTH, TOKEN)
                .when()
                .get("contacts")
                .then()
                .assertThat().statusCode(200)
                .extract().body().as(AllContactsDto.class);

        for (ContactDto contact : contactsDto.getContacts()) {
            System.out.println(contact.getId() + " *** " + contact.getName());
            System.out.println("================================================");
        }
    }

    @Test
    public void getAllContactsOhneTOKENTest() {
        ErrorDto response = given()
                .header(AUTH, "asztre")
                .when()
                .get("contacts")
                .then()
                .assertThat().statusCode(401)
                .extract().as(ErrorDto.class);
        System.out.println(response.getMessage());
    }
}