package com.library.steps;

import com.library.utility.DB_Util;
import io.cucumber.java.en.*;
import org.junit.Assert;

import java.util.List;

public class us01 {
    List<String> actualColumnNames;

    @Given("Establish the database connection")
    public void establish_the_database_connection() {
        DB_Util.createConnection();

    }

    @When("Execute query to get all IDs from users")
    public void execute_query_to_get_all_i_ds_from_users() {
      DB_Util.runQuery("select id from users ");

    }

    @Then("verify all users has unique ID")
    public void verify_all_users_has_unique_id() {
        int actualUserCount = DB_Util.getRowCount();
        System.out.println("actualUserCount = " + actualUserCount);

        DB_Util.runQuery("select distinct id from users");
        int expectedUserCount = DB_Util.getRowCount();
        System.out.println("expectedUserCount = " + expectedUserCount);


        Assert.assertEquals(actualUserCount, expectedUserCount);


    }





    @When("Execute query to get all columns")
    public void execute_query_to_get_all_columns() {
        DB_Util.runQuery("select * from users");

       actualColumnNames = DB_Util.getAllColumnNamesAsList();
        System.out.println("actualColumnNames = " + this.actualColumnNames);
    }

    @Then("verify the below columns are listed in result")
    public void verify_the_below_columns_are_listed_in_result(io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.

        List<String> expectedColumnNames = dataTable.asList();
        System.out.println("expectedColumnNames = " + expectedColumnNames);

        Assert.assertEquals(expectedColumnNames,actualColumnNames);


    }


}
