package com.library.steps;
import com.library.pages.BookPage;
import com.library.pages.DashBoardPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.*;
import org.junit.Assert;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class us04 {

    BookPage bookPage = new BookPage();
    String title;
    List<String> actualBookDetails = new ArrayList<>();

    @When("the user searches for {string} book")
    public void the_user_searches_for_book(String bookTitle) {
        title = bookTitle;
        bookPage.search.sendKeys(bookTitle);


    }

    @When("the user clicks edit book button")
    public void the_user_clicks_edit_book_button() {
        bookPage.editBook(title).click();
        actualBookDetails.add(bookPage.bookName.getAttribute("value"));
        actualBookDetails.add(bookPage.isbn.getAttribute("value"));
        actualBookDetails.add(bookPage.year.getAttribute("value"));
        actualBookDetails.add(bookPage.author.getAttribute("value"));
        actualBookDetails.add(bookPage.categoryDropdown.getAttribute("value"));
        actualBookDetails.add(bookPage.description.getAttribute("value"));

        System.out.println("actualBookDetails = " + actualBookDetails);
    }

    @Then("book information must match the Database")
    public void book_information_must_match_the_database() {
        DB_Util.runQuery("select * from books where name like 'Mountain Calls'");
        List<String> expectedBookDetails = DB_Util.getRowDataAsList(1);

        Assert.assertEquals(expectedBookDetails, actualBookDetails);

    }


}