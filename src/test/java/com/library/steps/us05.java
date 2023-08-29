package com.library.steps;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.library.pages.DashBoardPage;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class us05 {
    String actualPopGenre = "";

    @When("I execute query to find most popular book genre")
    public void i_execute_query_to_find_most_popular_book_genre() {
        DB_Util.runQuery("select BC.name, count(*) from books B\n" +
                "join book_categories BC on B.book_category_id = BC.id\n" +
                "join book_borrow BB on B.id = BB.book_id\n" +
                "group by BC.name\n" +
                "order by 2 desc");

       actualPopGenre = DB_Util.getFirstRowFirstColumn();
        System.out.println("actualPopGenre = " + actualPopGenre);

    }

    @Then("verify {string} is the most popular book genre.")
    public void verify_is_the_most_popular_book_genre(String expectedPopGenre) {
        Assert.assertEquals(expectedPopGenre, actualPopGenre);
    }



}
