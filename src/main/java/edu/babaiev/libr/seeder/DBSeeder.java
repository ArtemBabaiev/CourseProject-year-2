package edu.babaiev.libr.seeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 20.08.2022 04:16
 * @class DBSeeder
 */
//@Component
public class DBSeeder implements CommandLineRunner {
    ArticleSeeder articleSeeder;
    AuthorSeeder authorSeeder;
    BookCaseSeeder bookCaseSeeder;
    BookTypeSeeder bookTypeSeeder;
    CollectionTypeSeeder collectionTypeSeeder;
    EmployeeSeeder employeeSeeder;
    GenreSeeder genreSeeder;
    KeySeeder keySeeder;
    LibrarySeeder librarySeeder;
    LiteratureSeeder literatureSeeder;
    MonographTypeSeeder monographTypeSeeder;
    PeriodicalTypeSeeder periodicalTypeSeeder;
    PublisherSeeder publisherSeeder;
    ReaderSeeder readerSeeder;
    ReadingRoomSeeder readingRoomSeeder;
    RecordSeeder recordSeeder;
    RoleSeeder roleSeeder;
    ShelfSeeder shelfSeeder;
    SubjectSeeder subjectSeeder;
    WritingSeeder writingSeeder;
    WrittenOffSeeder writtenOffSeeder;

    @Autowired
    public DBSeeder(ArticleSeeder articleSeeder, AuthorSeeder authorSeeder, BookCaseSeeder bookCaseSeeder, BookTypeSeeder bookTypeSeeder, CollectionTypeSeeder collectionTypeSeeder, EmployeeSeeder employeeSeeder, GenreSeeder genreSeeder, KeySeeder keySeeder, LibrarySeeder librarySeeder, LiteratureSeeder literatureSeeder, MonographTypeSeeder monographTypeSeeder, PeriodicalTypeSeeder periodicalTypeSeeder, PublisherSeeder publisherSeeder, ReaderSeeder readerSeeder, ReadingRoomSeeder readingRoomSeeder, RecordSeeder recordSeeder, RoleSeeder roleSeeder, ShelfSeeder shelfSeeder, SubjectSeeder subjectSeeder, WritingSeeder writingSeeder, WrittenOffSeeder writtenOffSeeder) {
        this.articleSeeder = articleSeeder;
        this.authorSeeder = authorSeeder;
        this.bookCaseSeeder = bookCaseSeeder;
        this.bookTypeSeeder = bookTypeSeeder;
        this.collectionTypeSeeder = collectionTypeSeeder;
        this.employeeSeeder = employeeSeeder;
        this.genreSeeder = genreSeeder;
        this.keySeeder = keySeeder;
        this.librarySeeder = librarySeeder;
        this.literatureSeeder = literatureSeeder;
        this.monographTypeSeeder = monographTypeSeeder;
        this.periodicalTypeSeeder = periodicalTypeSeeder;
        this.publisherSeeder = publisherSeeder;
        this.readerSeeder = readerSeeder;
        this.readingRoomSeeder = readingRoomSeeder;
        this.recordSeeder = recordSeeder;
        this.roleSeeder = roleSeeder;
        this.shelfSeeder = shelfSeeder;
        this.subjectSeeder = subjectSeeder;
        this.writingSeeder = writingSeeder;
        this.writtenOffSeeder = writtenOffSeeder;
    }

    public void Seed(){
        bookTypeSeeder.Seed();
        collectionTypeSeeder.Seed();
        monographTypeSeeder.Seed();
        periodicalTypeSeeder.Seed();

        genreSeeder.Seed();
        subjectSeeder.Seed();

        librarySeeder.Seed();
        readingRoomSeeder.Seed();
        bookCaseSeeder.Seed();
        shelfSeeder.Seed();

        roleSeeder.Seed();
        keySeeder.Seed();
        employeeSeeder.Seed();
        readerSeeder.Seed();

        authorSeeder.Seed();
        publisherSeeder.Seed();

        writtenOffSeeder.Seed();

        writingSeeder.Seed();
        articleSeeder.Seed();

        literatureSeeder.Seed();
        recordSeeder.Seed();
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("!!!SEEDING STARTED!!!");
        Seed();
        System.out.println("!!!SEEDING ENDED!!!");
    }
}
