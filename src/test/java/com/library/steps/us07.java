package com.library.steps;

import com.library.pages.BookPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class us07 {

        BookPage bookPage = new BookPage();
        String book = "Self Confidence";
        String expectedBook;

    @When("the user clicks Borrow Book")
    public void the_user_clicks_borrow_book() {
    BrowserUtil.waitForClickablility(bookPage.borrowBook(book), 3);
        bookPage.borrowBook(book).click();



    }
    @Then("verify that book is shown in {string} page")
    public void verify_that_book_is_shown_in_page(String borrowingBook) {

            bookPage.navigateModule(borrowingBook);
        List<String> allBook  = new ArrayList<>();
        allBook= BrowserUtil.getElementsText(bookPage.allRows);
        for (String each : allBook) {
                    if(each.contains(book)){
                        expectedBook = each;
                    }
        }

        Assert.assertTrue(expectedBook.contains( book));

    }
    @Then("verify logged student has same book in database")
    public void verify_logged_student_has_same_book_in_database() {

        DB_Util.createConnection();
        DB_Util.runQuery("select U.full_name, BB.book_id, b.name, BB.is_returned from users U\n" +
                "join book_borrow BB on U.id = BB.user_id\n" +
                "join books b on BB.book_id = b.id\n" +
                "where BB.is_returned = 0 and U.full_name = 'Test Student 2'");

        String expectedBook = DB_Util.getCellValue(1, "name");

        Assert.assertEquals(expectedBook, book);
    }




}
