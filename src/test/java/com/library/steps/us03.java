package com.library.steps;

import com.library.pages.BookPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import com.library.utility.Driver;
import io.cucumber.java.en.*;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class us03 {

    BookPage bookPage = new BookPage();
    List<String> actualBookCat;

    @When("the user navigates to {string} page")
    public void the_user_navigates_to_page(String book) {
        bookPage.navigateModule(book);

    }
    @When("the user clicks book categories")
    public void the_user_clicks_book_categories() {
        bookPage.mainCategoryElement.click();
        actualBookCat = BrowserUtil.getAllSelectOptions(bookPage.mainCategoryElement);
        actualBookCat.remove(0);
        System.out.println("actualBookCat = " + actualBookCat);

    }

    @Then("verify book categories must match book_categories table from db")
    public void verify_book_categories_must_match_book_categories_table_from_db() {
        DB_Util.runQuery("select name from book_categories");
        List<String> expectedBookCat = DB_Util.getColumnDataAsList(1);
        System.out.println("expectedBookCat = " + expectedBookCat);

        Assert.assertEquals(expectedBookCat, actualBookCat);


    }




}
