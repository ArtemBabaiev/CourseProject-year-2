package edu.babaiev.libr.controller;

import edu.babaiev.libr.model.Exemplar;
import edu.babaiev.libr.model.Record;
import edu.babaiev.libr.service.EmployeeService;
import edu.babaiev.libr.service.ExemplarService;
import edu.babaiev.libr.service.ReaderService;
import edu.babaiev.libr.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/records")
public class RecordController {
    RecordService recordService;
    ExemplarService exemplarService;
    ReaderService readerService;
    EmployeeService employeeService;

    @Autowired
    public RecordController(RecordService recordService, ExemplarService exemplarService, ReaderService readerService, EmployeeService employeeService) {
        this.recordService = recordService;
        this.exemplarService = exemplarService;
        this.readerService = readerService;
        this.employeeService = employeeService;
    }

    @GetMapping("/show")
    public String showPage(HttpServletRequest request, Model model){
        int page = 0;
        int size = 10;
        String lastName = "";

        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }
        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
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

    @GetMapping("/create/exemplar/{id}")
    public String showCreateRecord(@PathVariable String id, Model model){
        //get exemplar
        Exemplar exemplar = exemplarService.get(id);
        //check if possible
        if (exemplar.isLend()){
            return "in-use";
        }
        model.addAttribute("exemplar", exemplar);
        model.addAttribute("readers", readerService.getAll());
        //створити рекорд
        model.addAttribute("record", new Record());
        return "record/record-create";
    }


    @PostMapping("/create/exemplar/{id}")
    public RedirectView performCreateRecord(){
        //get exemplar
        //видати екземпляр
        //створити рекорд

        return new RedirectView("/records/show/" + "id");
    }

    @PostMapping("/return/{id}")
    public RedirectView performReturnRecord(@PathVariable String id){
        //отримати рекорд
        //Рекорд сервіс повернути
        //Екземпляр сервіс повернути

        return new RedirectView("/records/show");
    }


    @DeleteMapping("/delete/{id}")
    public RedirectView performDeleteArticle(@PathVariable String id) {
        recordService.delete(id);
        return new RedirectView("/records/show");
    }
}
