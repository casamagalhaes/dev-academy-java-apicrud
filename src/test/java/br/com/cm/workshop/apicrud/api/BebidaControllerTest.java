package br.com.cm.workshop.apicrud.api;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.cm.workshop.apicrud.enums.TipoBebida;
import br.com.cm.workshop.apicrud.model.Bebida;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

@SpringBootTest
public class BebidaControllerTest {

    @Value("${server.port}")
    private int porta;
   
    private RequestSpecification requisicao;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    private void preparaRequisicao() {
        requisicao = new RequestSpecBuilder()
        .setBasePath("/bebidas")
        .setPort(porta)
        .setAccept(ContentType.JSON)
        .setContentType(ContentType.JSON)
        .log(LogDetail.ALL)
        .build();
    }

    @Test
    public void deveriaBuscarBebidasComSucesso() {
        given()
            .spec(requisicao)
        .expect()
            .statusCode(HttpStatus.SC_OK)
        .when()
            .get();
    }
    
    @Test
    public void deveriaCriarUmaBebidaComSucesso() throws JsonProcessingException {
        
        Bebida bebidaCriada = 
        given()
            .spec(requisicao)
            .body(objectMapper.writeValueAsString(dadoUmaBebidaPadrao()))
        .when()
            .post()
        .then()
            .statusCode(HttpStatus.SC_CREATED)
        .extract()
            .as(Bebida.class);

        assertNotNull(bebidaCriada);
        assertNotNull(bebidaCriada.getId());
        assertEquals("café", bebidaCriada.getNome());

    }

    @Test
    public void deveriaAlterarUmaBebidaComSucesso() throws JsonProcessingException {
        Bebida bebidaCriada = 
        given()
            .spec(requisicao)
            .body(objectMapper.writeValueAsString(dadoUmaBebidaPadrao()))
        .when()
            .post()
        .then()
            .statusCode(HttpStatus.SC_CREATED)
        .extract()
            .as(Bebida.class);

        bebidaCriada.setNome("suco");

        Bebida bebidaAlterada = 
        given()
            .spec(requisicao)
            .body(objectMapper.writeValueAsString(bebidaCriada))
        .when()
            .put("/{id}", bebidaCriada.getId())
        .then()
            .statusCode(HttpStatus.SC_OK)
        .extract()
            .as(Bebida.class);

        assertEquals(bebidaAlterada.getNome(), bebidaCriada.getNome(), "nomes alterados");
    }

    @Test
    public void deveriaExcluirBebidaComSucesso() throws JsonProcessingException {
        // cria bebida
        Bebida bebidaCriada = 
        given()
            .spec(requisicao)
            .body(objectMapper.writeValueAsString(dadoUmaBebidaPadrao()))
        .when()
            .post()
        .then()
            .statusCode(HttpStatus.SC_CREATED)
        .extract()
            .as(Bebida.class);

        // remove bebida
        given()
            .spec(requisicao)
        .when()
            .delete("/{id}", bebidaCriada.getId())    
        .then()
            .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    public void deveriaValidarBebidaSemNome() throws JsonProcessingException {
        given()
            .spec(requisicao)
            .body(objectMapper.writeValueAsString(dadoUmaBebidaSemNome()))
        .when()
            .post()
        .then()
            .statusCode(HttpStatus.SC_BAD_REQUEST);
    }


    private Bebida dadoUmaBebidaPadrao(){
        Bebida bebida = new Bebida();
        bebida.setNome("café");
        bebida.setAtivo(true);
        bebida.setMarca("Marca 01");
        bebida.setTipoBebida(TipoBebida.MINERAL);
        return bebida;
    }

    private Bebida dadoUmaBebidaSemNome(){
        Bebida bebida = new Bebida();
        bebida.setAtivo(true);
        bebida.setMarca("Marca 02");
        bebida.setTipoBebida(TipoBebida.MINERAL);
        return bebida;
    }

}
