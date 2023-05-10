package com.example.mazegame1;

import com.example.mazegame1.controller.MazeAssembler;
import com.example.mazegame1.dto.Path;
import com.example.mazegame1.dto.RoomData;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.net.URISyntaxException;
import java.util.*;

public class MazeSolver {
    private static HashSet<String> visited = new HashSet<>();
    private static MazeAssembler mazeAssembler = new MazeAssembler();

    /**
    * Provide a recursive method to go through all neighbors from a root node,
     * including performing backward operation if the player can't go further
     * @param root A room object
     * @param dir The direction that the player is going to go forward
     * @return A boolean value, it will always be false util it can find the exit
    * */
    private static boolean go(RoomData root, String dir) throws JsonProcessingException, URISyntaxException {
        if(!dir.isEmpty()) mazeAssembler.movePlayer(dir);

        RoomData currentRoom = mazeAssembler.getRoom();

        //Once find the exit
        if (currentRoom.getEffect() != null || currentRoom.getContents() != null){
            return true;
        }

        visited.add(currentRoom.getId());
        List<Path> pathList = currentRoom.getPaths();
        for(Path path: pathList){
            //Check if the neighbor has been visited be it goes forward
            if(!visited.contains(path.getDestination())){
                String nextDir = path.getDirection();
                if(go(currentRoom, nextDir)){
                    return true;
                }
            }
        }

        if(!dir.isEmpty()) mazeAssembler.movePlayer(getOpposite(dir));
        return false;
    }

    /**
     * This is a utility method used to turn player around
     *  @param oldDirection It's a previous direction.
     *  @return Give an opposite.
    */
    private static String getOpposite(String oldDirection){
        switch (oldDirection){
           case "North" : return "South";
           case "South" : return "North";
           case "West"  : return "East";
           case "East"  : return "West";
        }
        return "";
    }

    public static void main(String[] args) throws URISyntaxException, JsonProcessingException {

        //Start a game with a new token
        mazeAssembler.getToken();

        //kick off
        if(go(null, "")){
            System.out.println("YOU WIN THE GAME!!!");
        } else {
            System.out.println("Embracing the adventure that comes with being stuck in a maze!");
        }


        Queue<RoomData> queue = new LinkedList<>();
        HashMap<String, String> traverseResult = new HashMap<>();

        //Implement BFS
        while(!queue.isEmpty()){
            RoomData currentRoomData = queue.poll();
            traverseResult.put(currentRoomData.getId(), currentRoomData.getContents() + currentRoomData.getEffect());

            List<Path> pathList = currentRoomData.getPaths();
            System.out.println(pathList);

            for(Path path: pathList){
                String neighbor = path.getDestination();
                if(!visited.contains(neighbor)){
                    String next = path.getDirection();
                    mazeAssembler.movePlayer(next);
                    visited.add(neighbor);

                    RoomData roomDataTemp = mazeAssembler.getRoom();
                    queue.add(roomDataTemp);
                }
            }

            RoomData first = queue.peek();

        }
    }

}
