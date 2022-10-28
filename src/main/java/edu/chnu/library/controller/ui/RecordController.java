package edu.chnu.library.controller.ui;

import edu.chnu.library.model.Record;
import edu.chnu.library.service.ReaderService;
import edu.chnu.library.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/ui/records")
public class RecordController {
    RecordService recordService;
    ReaderService readerService;

    @Autowired
    public RecordController(RecordService recordService, ReaderService readerService) {
        this.recordService = recordService;
        this.readerService = readerService;
    }

    @GetMapping("/show")
    public String showPage(HttpServletRequest request, Model model) {
        int page = 0;
        int size = 10;
        String lastName = "";

        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }
        if (request.getParameter("lastName") != null && !request.getParameter("lastName").isEmpty()) {
            lastName = request.getParameter("lastName");
        }
        model.addAttribute("records",
                recordService.getAllByReaderLastNameContainingPaginated(
                        lastName,
                        PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "createdAt"))));
        return "record/record-all";
    }

    @GetMapping("/show/{id}")
    public String showOne(@PathVariable String id, Model model) {
        model.addAttribute(recordService.get(id));
        return "record/record-show";
    }


    @GetMapping("/create/exemplar/{id}")
    public String showCreate(@PathVariable String id, Model model) {
        Record record = recordService.prepareRecordForExemplar(id);
        if (record == null) {
            return "in-use";
        }
        model.addAttribute("readers", readerService.getAll());
        model.addAttribute("record", record);
        return "record/record-create";
    }


    @PostMapping("/create/exemplar/{id}")
    public String performCreate(@PathVariable String id, Record record) {
        Record createdOne = recordService.createRecord(record, id);
        if (createdOne == null) return "redirect: /error";
        return "redirect:/ui/records/show/" + createdOne.getId();
    }

    @PutMapping("/return/{id}")
    public String performReturn(@PathVariable String id) {
        try {
            Record record = recordService.returnRecord(id);
            return "redirect:/ui/records/show/" + record.getId();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "error";
    }


    @DeleteMapping("/delete/{id}")
    public String performDelete(@PathVariable String id) {
        recordService.delete(id);
        return "redirect:/ui/records/show";
    }
}
