package com.library.steps;

import com.github.javafaker.Faker;
import com.library.pages.BookPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.sql.Date;
import java.util.List;

public class us06 {
    BookPage bookPage = new BookPage();
    String actualBookTitle;


    @When("the librarian click to add book")
    public void the_librarian_click_to_add_book() {
        bookPage.addBook.click();

    }

    @When("the librarian enter book name {string}")
    public void the_librarian_enter_book_name(String title) {

      bookPage.bookName.sendKeys(title);
        actualBookTitle = title;
    }

    @When("the librarian enter ISBN {string}")
    public void the_librarian_enter_isbn(String isbn) {

        bookPage.isbn.sendKeys(isbn);

    }

    @When("the librarian enter year {string}")
    public void the_librarian_enter_year(String year) {
        bookPage.year.sendKeys(year);

    }

    @When("the librarian enter author {string}")
    public void the_librarian_enter_author(String author   ) {
        bookPage.author.sendKeys(author);

    }

    @When("the librarian choose the book category {string}")
    public void the_librarian_choose_the_book_category(String bookCat) {
        bookPage.categoryDropdown.sendKeys(bookCat);
        BrowserUtil.waitFor(2);

    }

    @When("the librarian click to save changes")
    public void the_librarian_click_to_save_changes() {
        bookPage.saveChanges.click();

    }

    @Then("verify {string} message is displayed")
    public void verify_message_is_displayed(String toastMessage) {
        bookPage.toastMessage.isDisplayed();


    }

    @Then("verify {string} information must match with DB")
    public void verify_information_must_match_with_db(String string) {

        DB_Util.runQuery("select name from books;");
        List<String> allTitles = DB_Util.getColumnDataAsList(1);

         Assert.assertTrue(allTitles.contains(actualBookTitle));

    }

}
