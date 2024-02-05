package com.bogoliubova.training_service.controller.page;
import com.bogoliubova.training_service.entity.Direction;
import com.bogoliubova.training_service.service.interf.DirectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/direction")
public class DirectionController {

    private final DirectionService directionService;

//    @GetMapping("/id_direction/{direction_id}")
//    public Direction getDirectionByDirectionId(@PathVariable("direction_id") String id) {
//        return directionService.getDirectionById(id);
//    }

    @PostMapping("/createDirection")
    public Direction createDirection(@RequestBody Direction direction) {
        return directionService.createNewDirection(direction);
    }
}