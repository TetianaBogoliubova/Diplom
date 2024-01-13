package com.bogoliubova.training_service.controller.page;

import com.bogoliubova.training_service.entity.Direction;
import com.bogoliubova.training_service.service.interf.DirectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/direction")
public class DirectionController {

    private final DirectionService directionService;

//    @Autowired
//    public DirectionController(DirectionService directionService) {
//        this.directionService = directionService;
//    }

//    @GetMapping("/id_direction/{direction_id}")
//    public Direction getDirectionById(@PathVariable("direction_id") String id) {
//        return directionService.getDirectionById(id);
//    }
//    @GetMapping("id_direction/{direction_id}")
//    public ResponseEntity<Direction>getDirectionById(@PathVariable("direction_id") UUID id){
//       // Direction direction = directionService.getDirectionById(String.valueOf(id));
//        Direction direction = directionService.getDirectionById(UUID.fromString(String.valueOf(id)));
//        return ResponseEntity.ok(direction);
//    }

    @GetMapping("/id_direction/{direction_id}")
    public Direction getDirectionById(@PathVariable("direction_id") String direction_id) {
        return directionService.getDirectionById(direction_id);
    }
}
