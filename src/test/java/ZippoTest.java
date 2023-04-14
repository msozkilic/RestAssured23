import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ZippoTest {

    @Test
    public void test(){

        given()//todo hazirlik islemleri token ,send , body , parametreler

                .when() // todo lin ve metod verilecek

                .then();// todo assertion ve verileri ele alma ,extract
    }

    @Test
    public void statusCodeTest(){

                given()//todo hazirlik islemleri token ,send , body , parametreler

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then() // todo dogrulama yeri ,statüs
                .log().body() //todo log.All bütün responsu sonucu listeyi verir
                .statusCode(200)

        ;
    }
    @Test
    public void contentTypeTest(){

        given()//todo hazirlik islemleri token ,send , body , parametreler

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then() // todo dogrulama yeri ,statüs
                .log().body() //todo log.All bütün responsu sonucu listeyi verir
                .statusCode(200)
                .contentType(ContentType.JSON)

        ;
    }
    @Test
    public void checkStateInResponseBody(){

                given()//todo hazirlik islemleri token ,send , body , parametreler

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then() // todo dogrulama yeri ,statüs
                .log().body() //todo log.All bütün responsu sonucu listeyi verir
                .body("country",equalTo("United States"))//todo body.country==United States ?
                .statusCode(200)

                ;
    }
    // todo body.places[0].'place name' -> body("body.places[0].'place name'"   seklinde yazilir hamcrest i

    @Test
    public void bodyJsonPathTest2(){

        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .log().body()
                .body("places[0].state",equalTo("California"))
                .statusCode(200)

        ;
    }
    @Test
    public void bodyJsonPathTest3(){

        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .log().body()
                .body("places.state",equalTo("California"))
                .statusCode(200)

        ;
    }
    @Test
    public void bodyJsonPathTest4(){

        given()

                .when()
                .get("http://api.zippopotam.us/tr/01000")

                .then()
                .log().body()
                .body("places.'place name'",hasItem("Çaputçu Köyü"))
                .statusCode(200)

        ;
    }
    @Test
    public void bodyArrayHasSize(){

        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .log().body()
                .body("places",hasSize(1)) //todo verilen path deki listin size kontrolu
                .statusCode(200)

        ;
    }
    @Test
    public void pathParamTest(){

        given()
                .pathParam("Country","us")
                .pathParam("ZipKod","90210")
                .log().uri() //todo url yi göster

                .when()
                .get("http://api.zippopotam.us/{Country}/{ZipKod}")

                .then()
                .log().body()

                .statusCode(200)

        ;
    }
    @Test
    public void pathParamTest2(){
        //todo 90210 dan 90250 ye kadar test sonuclarinda places size nin hepsinde 1 geldigini test ediniz

        for (int i = 90210; i < 90213; i++) {
            given()
                    .pathParam("Country", "us")
                    .pathParam("ZipKod", i)
                    .log().uri() //todo url yi göster

                    .when()
                    .get("http://api.zippopotam.us/{Country}/{ZipKod}")

                    .then()
                    .log().body()
                    .body("places",hasSize(1))
                    .statusCode(200)

            ;
        }

    }
    @Test
    public void queryParamTest(){
        //  https://gorest.co.in/public/v2/users?page=1

        given()
                .param("page",1)
                .log().uri() //todo url yi göster

                .when()
                .get("https://gorest.co.in/public/v2/users")

                .then()
                .log().body()

                .statusCode(200)

        ;
    }
    @Test
    public void queryParamTest2(){
        //  https://gorest.co.in/public/v1/users?page=1

        given()
                .param("page",1)
                .log().uri() //todo url yi göster

                .when()
                .get("https://gorest.co.in/public/v1/users")

                .then()
                .log().body()
                .body("meta.pagination.page",equalTo(1))
                .statusCode(200)

        ;
    }
    @Test
    public void queryParamTest3() {
        //  https://gorest.co.in/public/v1/users?page=1
        //todo 1 den 10 a kadar page leri kontrol ediniz yani url de page 1 di,simdi 10 a kadar page i kontrol et.

        for (int pageNo = 1; pageNo <= 10; pageNo++) {


            given()
                    .param("page", pageNo)
                    .log().uri() //todo url yi göster

                    .when()
                    .get("https://gorest.co.in/public/v1/users")

                    .then()
                    .log().body()
                    .body("meta.pagination.page", equalTo(pageNo))
                    .statusCode(200)
            ;
        }
    }



}
