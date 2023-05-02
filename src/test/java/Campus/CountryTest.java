package Campus;

import Campus.Model.Country;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
public class CountryTest {
    Cookies cookies;
    @BeforeClass
    public void loginCampus(){

        baseURI = "https://test.mersys.io/";

        //username: turkeyts
        //password: TechnoStudy123

        Map<String,String> credential=new HashMap<>();
        credential.put("username","turkeyts");
        credential.put("password","TechnoStudy123");
        credential.put("rememberMe","true");  //todo burasi kullaniciyi hatirla kismidir

        cookies=
        given()
                .contentType(ContentType.JSON)
                .body(credential)

                .when()
                .post("auth/login") //todo url nin devami

                .then()
              //  .log().all()  //todo
               // .log().cookies() //todo bu sekilde de yapilirsa cookies e direk ulasir ,görürüz
                .statusCode(200)
                .extract().response().getDetailedCookies();//todo burada su var.sayfanin tooken i cookies altinda gidiyordu
                                                            //todo bununla gönderiyoruz.

                ;
    }

    String countryID;
    @Test
    public void createCountry(){

        Country country=new Country();
        country.setName();//todo generateCountryName
        country.setCode();//todo generateCountryCode

        countryID=
        given()
                .cookies(cookies)
                .log().all()

                .when()


                .then()

                ;

    }
    public String getRandomName(){  //todo sürekli bir isim alacagimiz icin bunu metoda cevirdik.
        return RandomStringUtils.randomAlphabetic(8);
    }

    public String getRandomEmail(){
        return RandomStringUtils.randomAlphabetic(8).toLowerCase()+"@gmail.com";
    }


}
