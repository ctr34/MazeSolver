package com.example.mazegame1.dto;

import java.util.List;
import lombok.Data;

@Data
public class RoomData {
    private String id;
    private List<Path> paths;
    private String contents;
    private String effect;
}

