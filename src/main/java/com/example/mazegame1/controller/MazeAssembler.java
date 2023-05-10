package com.example.mazegame1.controller;

import com.example.mazegame1.dto.Path;
import com.example.mazegame1.dto.RoomData;
import com.example.mazegame1.dto.Token;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class MazeAssembler {
    private static final String urlToken = "https://mazegame.plingot.com/Game/start";
    private static final String urlRoom = "https://mazegame.plingot.com/Room/current";
    private static final String urlPlayerMove = "https://mazegame.plingot.com/Player/move";
    private static ObjectMapper objectMapper = new ObjectMapper();
    private HttpHeaders headers;
    private RestTemplate restTemplate;

    public MazeAssembler(){
        headers = new HttpHeaders();
        restTemplate = new RestTemplate();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    public void getToken() throws JsonProcessingException {
        RequestEntity<Void> requestEntity = new RequestEntity<>(headers, HttpMethod.POST, URI.create(urlToken));
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);
        String responseBody = responseEntity.getBody();
        Token token = objectMapper.readValue(responseBody, Token.class);
        headers.set("Authorization", token.getToken());
    }
    public RoomData getRoom() throws JsonProcessingException {

        RequestEntity<Void> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, URI.create(urlRoom));
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);
        String responseBody = responseEntity.getBody();
        RoomData roomData = objectMapper.readValue(responseBody, RoomData.class);

        return roomData;
    }

    public void movePlayer(String moveDirection) throws URISyntaxException {

        RequestEntity<Void> requestEntity = new RequestEntity<>(headers, HttpMethod.PUT, new URI(urlPlayerMove + "?direction=" + moveDirection));
        restTemplate.exchange(requestEntity, String.class);


    }

}
