package edu.babaiev.libr.controller;

import edu.babaiev.libr.form.CategoryChooseForm;
import edu.babaiev.libr.model.*;
import edu.babaiev.libr.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;


/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 22.08.2022 20:26
 * @class LiteratureController
 */
@Controller
@RequestMapping("/literature")
public class LiteratureController {
    LiteratureService literatureService;
    PublisherService publisherService;
    ArticleService articleService;
    AuthorService authorService;
    WritingService writingService;
    GenreService genreService;
    SubjectService subjectService;
    LibraryService libraryService;
    ReadingRoomService readingRoomService;
    BookCaseService bookCaseService;
    ShelfService shelfService;
    BookTypeService bookTypeService;
    CollectionTypeService collectionTypeService;
    MonographTypeService monographTypeService;
    PeriodicalTypeService periodicalTypeService;

    @Autowired
    public LiteratureController(LiteratureService literatureService, PublisherService publisherService,
                                ArticleService articleService, AuthorService authorService, WritingService writingService,
                                GenreService genreService, SubjectService subjectService, LibraryService libraryService,
                                ReadingRoomService readingRoomService, BookCaseService bookCaseService,
                                ShelfService shelfService, BookTypeService bookTypeService,
                                CollectionTypeService collectionTypeService, MonographTypeService monographTypeService,
                                PeriodicalTypeService periodicalTypeService) {
        this.literatureService = literatureService;
        this.publisherService = publisherService;
        this.articleService = articleService;
        this.authorService = authorService;
        this.writingService = writingService;
        this.genreService = genreService;
        this.subjectService = subjectService;
        this.libraryService = libraryService;
        this.readingRoomService = readingRoomService;
        this.bookCaseService = bookCaseService;
        this.shelfService = shelfService;
        this.bookTypeService = bookTypeService;
        this.collectionTypeService = collectionTypeService;
        this.monographTypeService = monographTypeService;
        this.periodicalTypeService = periodicalTypeService;
    }


    @GetMapping("/show")
    public String showAll(Model model) {
        model.addAttribute("literature", literatureService.getAll());
        return "literature/literature-all";
    }

    @GetMapping("/show/{id}")
    public String showOne(@PathVariable String id, Model model) {
        Literature literature = literatureService.get(id);
        if (Book.class.equals(literature.getClass())) {
            model.addAttribute("book", (Book) literature);
            return "literature/book/book-show";
        }
        if (Collection.class.equals(literature.getClass())) {
            model.addAttribute("collection", (Collection) literature);
            return "literature/collection/collection-show";
        }
        if (Monograph.class.equals(literature.getClass())) {
            model.addAttribute("monograph", (Monograph) literature);
            return "literature/monograph/monograph-show";
        }
        if (Periodical.class.equals(literature.getClass())) {
            model.addAttribute("periodical", (Periodical) literature);
            return "literature/periodical/periodical-show";
        }
        return "redirect:/literature/show/";
    }

    @GetMapping("/create")
    public String showChooseCategory(Model model) {
        model.addAttribute("categoryChooseForm", new CategoryChooseForm());
        return "literature/category-choose";
    }

    @PostMapping("/create")
    public RedirectView redirectCategory(CategoryChooseForm categoryChooseForm) {
        switch (categoryChooseForm.getCategory()) {
            case "Book":
                return new RedirectView("/literature/create/book");
            case "Collection":
                return new RedirectView("/literature/create/collection");
            case "Monograph":
                return new RedirectView("/literature/create/monograph");
            case "Periodical":
                return new RedirectView("/literature/create/periodical");
        }
        return new RedirectView("/literature/category");
    }

    @GetMapping("/create/book")
    public String showCreateBook(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("genres", genreService.getAll());
        model.addAttribute("publishers", publisherService.getAll());
        model.addAttribute("authors", authorService.getAll());
        model.addAttribute("writings", writingService.getAll());
        model.addAttribute("libraries", libraryService.getAll());
        model.addAttribute("readingRooms", readingRoomService.getAll());
        model.addAttribute("bookCases", bookCaseService.getAll());
        model.addAttribute("shelves", shelfService.getAll());
        model.addAttribute("bookTypes", bookTypeService.getAll());
        return "literature/book/book-create";
    }

    @PostMapping("/create/book")
    public RedirectView performCreateBook(Book book) {
        Literature newOne = literatureService.create(book);
        return new RedirectView("/literature/show/" + newOne.getId());
    }

    @GetMapping("/create/collection")
    public String showCreateCollection(Model model) {
        model.addAttribute("collection", new Collection());
        model.addAttribute("genres", genreService.getAll());
        model.addAttribute("publishers", publisherService.getAll());
        model.addAttribute("authors", authorService.getAll());
        model.addAttribute("writings", writingService.getAll());
        model.addAttribute("libraries", libraryService.getAll());
        model.addAttribute("readingRooms", readingRoomService.getAll());
        model.addAttribute("bookCases", bookCaseService.getAll());
        model.addAttribute("shelves", shelfService.getAll());
        model.addAttribute("collectionTypes", collectionTypeService.getAll());
        return "literature/collection/collection-create";
    }

    @PostMapping("/create/collection")
    public RedirectView performCreateCollection(Collection collection) {
        Literature newOne = literatureService.create(collection);
        return new RedirectView("/literature/show/" + newOne.getId());
    }

    @GetMapping("/create/monograph")
    public String showCreateMonograph(Model model) {
        model.addAttribute("monograph", new Monograph());
        model.addAttribute("publishers", publisherService.getAll());
        model.addAttribute("authors", authorService.getAll());
        model.addAttribute("libraries", libraryService.getAll());
        model.addAttribute("readingRooms", readingRoomService.getAll());
        model.addAttribute("bookCases", bookCaseService.getAll());
        model.addAttribute("shelves", shelfService.getAll());
        model.addAttribute("monographTypes", monographTypeService.getAll());
        return "literature/monograph/monograph-create";
    }

    @PostMapping("/create/monograph")
    public RedirectView performCreateMonograph(Monograph monograph) {
        Literature newOne = literatureService.create(monograph);
        return new RedirectView("/literature/show/" + newOne.getId());
    }

    @GetMapping("/create/periodical")
    public String showCreatePeriodical(Model model) {
        model.addAttribute("periodical", new Periodical());
        model.addAttribute("subjects", subjectService.getAll());
        model.addAttribute("publishers", publisherService.getAll());
        model.addAttribute("authors", authorService.getAll());
        model.addAttribute("articles", articleService.getAll());
        model.addAttribute("libraries", libraryService.getAll());
        model.addAttribute("readingRooms", readingRoomService.getAll());
        model.addAttribute("bookCases", bookCaseService.getAll());
        model.addAttribute("shelves", shelfService.getAll());
        model.addAttribute("periodicalTypes", periodicalTypeService.getAll());
        return "literature/periodical/periodical-create";
    }

    @PostMapping("/create/periodical")
    public RedirectView performCreatePeriodical(Periodical periodical) {
        Literature newOne = literatureService.create(periodical);
        return new RedirectView("/literature/show/" + newOne.getId());
    }


    @GetMapping("/edit/{id}")
    public String showEdit(@PathVariable String id, Model model) {
        Literature literature = literatureService.get(id);
        model.addAttribute("publishers", publisherService.getAll());
        model.addAttribute("authors", authorService.getAll());
        model.addAttribute("libraries", libraryService.getAll());
        model.addAttribute("readingRooms", readingRoomService.getAll());
        model.addAttribute("bookCases", bookCaseService.getAll());
        model.addAttribute("shelves", shelfService.getAll());
        if (Book.class.equals(literature.getClass())) {
            model.addAttribute("book", (Book) literature);
            model.addAttribute("writings", writingService.getAll());
            model.addAttribute("genres", genreService.getAll());
            model.addAttribute("bookTypes", bookTypeService.getAll());
            return "literature/book/book-edit";
        }
        if (Collection.class.equals(literature.getClass())) {
            model.addAttribute("collection", (Collection) literature);
            model.addAttribute("writings", writingService.getAll());
            model.addAttribute("genres", genreService.getAll());
            model.addAttribute("collectionTypes", collectionTypeService.getAll());
            return "literature/collection/collection-edit";
        }
        if (Monograph.class.equals(literature.getClass())) {
            model.addAttribute("monograph", (Monograph) literature);
            model.addAttribute("monographTypes", monographTypeService.getAll());
            return "literature/monograph/monograph-edit";
        }
        // (Periodical.class.equals(literature.getClass())) {
        model.addAttribute("periodical", (Periodical) literature);
        model.addAttribute("subjects", subjectService.getAll());
        model.addAttribute("articles", articleService.getAll());
        model.addAttribute("periodicalTypes", periodicalTypeService.getAll());
        return "literature/periodical/periodical-edit";
        //}
        //return "";
    }

    @PutMapping("/edit/{id}/book")
    public RedirectView performEditBook(@PathVariable String id, Book book) {
        book.setId(id);
        Literature updated = literatureService.update(book);
        return new RedirectView("/literature/show/" + updated.getId());
    }

    @PutMapping("/edit/{id}/collection")
    public RedirectView performEditCollection(@PathVariable String id, Collection collection) {
        collection.setId(id);
        Literature updated = literatureService.update(collection);
        return new RedirectView("/literature/show/" + updated.getId());
    }

    @PutMapping("/edit/{id}/monograph")
    public RedirectView performEditMonograph(@PathVariable String id, Monograph monograph) {
        monograph.setId(id);
        Literature updated = literatureService.update(monograph);
        return new RedirectView("/literature/show/" + updated.getId());
    }

    @PutMapping("/edit/{id}/periodical")
    public RedirectView performEditPeriodical(@PathVariable String id, Periodical periodical) {
        periodical.setId(id);
        Literature updated = literatureService.update(periodical);
        return new RedirectView("/literature/show/" + updated.getId());
    }

    @DeleteMapping("/delete/{id}")
    public RedirectView performDelete(@PathVariable String id) {
        literatureService.delete(id);
        return new RedirectView("/literature/show");

    }
}
