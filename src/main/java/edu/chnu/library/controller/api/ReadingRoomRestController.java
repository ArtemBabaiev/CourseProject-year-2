package edu.chnu.library.controller.api;

import edu.chnu.library.model.ReadingRoom;
import edu.chnu.library.model.ReadingRoom;
import edu.chnu.library.service.ReadingRoomService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

/**
 * @author artem
 * @version: 1.0.0
 * @project microJava_01
 * @date 05.09.2022 23:36
 * @class ReadingRoomRestController
 */
@RestController
@RequestMapping("/api/readingRooms")
public class ReadingRoomRestController {
    ReadingRoomService service;

    @Autowired
    public ReadingRoomRestController(ReadingRoomService readingRoomService) {
        this.service = readingRoomService;
    }

    @ApiOperation(value = "Get all readingRooms", notes = "Returns all readingRooms from DB", httpMethod = "GET", response = List.class, code = 200)
    @GetMapping("/")
    List<ReadingRoom> showAll() {
        return service.getAll();
    }

    @ApiOperation(value = "Get readingRoom by specified id", notes = "Returns readingRooms by ID from DB", httpMethod = "GET", response = ReadingRoom.class, code = 200)
    @GetMapping("/{id}")
    ReadingRoom showOne(@PathVariable String id) {
        return service.get(id);
    }

    @ApiOperation(value = "Delete readingRoom by specified id", notes = "Deletes readingRooms by ID from DB", httpMethod = "DELETE", response = void.class, code = 200)
    @DeleteMapping("/{id}")
    void deleteOne(@PathVariable String id) {
        service.delete(id);
    }

    @ApiOperation(value = "Create new readingRoom", notes = "Creates readingRooms in DB and returns new ReadingRoom object", httpMethod = "POST", response = ReadingRoom.class, code = 200)
    @PostMapping("")
    ReadingRoom createOne(@RequestBody ReadingRoom readingRoom) {
        return service.create(readingRoom);
    }

    @ApiOperation(value = "Update existing readingRoom", notes = "Updates readingRooms in DB and returns updated ReadingRoom object", httpMethod = "PUT", response = ReadingRoom.class, code = 200)
    @PutMapping("")
    ReadingRoom UpdateOne(@RequestBody ReadingRoom readingRoom) {
        return service.update(readingRoom);
    }

    @ApiOperation(value = "Search by name containing and sort by specified field and order", notes = "to specify order put sort_by=+field_name or -fieldName", httpMethod = "PUT", response = ReadingRoom.class, code = 200)
    @GetMapping("/search")
    List<ReadingRoom> search(HttpServletRequest request) {
        String number = "";
        Sort sortBy = Sort.by(Sort.Direction.ASC, "id");
        String between1 = "";
        String between2 = "";
        if (request.getParameter("number") != null && !request.getParameter("number").isEmpty()) {
            number = request.getParameter("number");
        }
        if (request.getParameter("sort_by") != null && !request.getParameter("sort_by").isEmpty()) {
            StringBuilder requestParameter = new StringBuilder(request.getParameter("sort_by"));
            if (requestParameter.charAt(0) == '+' || requestParameter.charAt(0) == '-') {
                sortBy = Sort.by(requestParameter.charAt(0) == '+' ? Sort.Direction.ASC : Sort.Direction.DESC, requestParameter.substring(1));
            }
        }
        if (request.getParameter("range") != null && !request.getParameter("range").isEmpty()) {
            String[] ranges = request.getParameter("range").split("-");
            try {
                between1 = ranges[0];
                between2 = ranges[1];
            }catch (Exception e){}
        }
        List<ReadingRoom> result = !Objects.equals(between1, "") && !Objects.equals(between2, "") ? service.getAllByNumberContainingAndBetween(number, between1, between2, sortBy) : service.getAllByNumberContaining(number, sortBy);
        return result;
    }

    @GetMapping("/paging")
    List<ReadingRoom> paging(HttpServletRequest request){
        int page = 0;
        int size = 10;
        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }
        return service.getAllPaginated(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "number"))).getContent();
    }
}
