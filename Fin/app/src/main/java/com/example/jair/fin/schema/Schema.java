package com.example.jair.fin.schema;

import static com.example.jair.fin.schema.Schema.SchemaCategory.CAT_ID;
import static com.example.jair.fin.schema.Schema.SchemaCategory.CAT_TABLE;
import static com.example.jair.fin.schema.Schema.SchemaUser.ID;
import static com.example.jair.fin.schema.Schema.SchemaUser.USER_TABLE;

/**
 * Created by Jair on 2/4/2017.
 */

public class Schema {

    public static final String DATABASE_NAME = "fin_database.db";
    public static final int DATABASE_VERSION = 1;

    public class SchemaUser {
        public static final String USER_TABLE = "user_table";
        public static final String ID = "id";
        public static final String USER_NAME = "username";
        public static final String USER_PASSWORD = "password";
        public static final String EMAIL = "email";
        public static final String NAME_USER = "name_user";
        public static final String SURNAME = "surname";
        public static final String CREATED = "created";
        public static final String DELETED = "deleted";

        public static final String SQL_USER_TABLE_QUERY =
                "create table "+USER_TABLE+"("+ID+" INTEGER PRIMARY KEY, "
                        +USER_NAME+" STRING NOT NULL UNIQUE, "
                        +USER_PASSWORD+" STRING NOT NULL, "
                        +EMAIL+" STRING, "
                        +NAME_USER+" STRING, "
                        +SURNAME+" STRING, "
                        +CREATED+" STRING, "
                        +DELETED+" STRING )";
        public static final String SQL_USER_TABLE_DROP = "DROP TABLE IF EXISTS "+USER_TABLE;
    }

    public class SchemaTransaction{
        public static final String TRAN_ID="tran_id";
        public static final String TRAN_TABLE= "transaction_table";
        public static final String TRAN_NAME="tran_name";
        public static final String TRAN_AMOUNT="amount";
        public static final String TRAN_TYPE="type";
        public static final String TRAN_DATE= "date";
        public static final String USER_FK="user_fk";
        public static final String CAT_FK="category_fk";

        public static final String SQL_TRAN_TABLE_QUERY =
                "create table "+TRAN_TABLE+
                        "("+TRAN_ID+" INTEGER PRIMARY KEY, "
                        +TRAN_NAME+" STRING NOT NULL, "
                        +TRAN_AMOUNT+" STRING NOT NULL, "
                        +TRAN_TYPE+" STRING, "
                        +TRAN_DATE+" DOUBLE, "
                        +USER_FK+" INTEGER, "
                        +CAT_FK+" INTEGER, "+
                        "FOREIGN KEY ("+CAT_FK+") REFERENCES "+CAT_TABLE+"("+CAT_ID+") "+
                        "FOREIGN KEY ("+USER_FK+") REFERENCES "+USER_TABLE+"("+ID+"));";


        public static final String SQL_TRAN_TABLE_DROP = "DROP TABLE IF EXISTS "+TRAN_TABLE;
    }

    public class SchemaCategory{

        public static final String CAT_TABLE = "category_table";
        public static final String CAT_ID= "cat_id";
        public static final String CAT_NAME= "cat_name";
        public static final String CAT_DESCRIPTION= "description";
        public static final String BUDGET_NAME= "budget_name";
        public static final String BUDGET= "budget";


        public static final String SQL_CAT_TABLE_QUERY =
                "create table "+CAT_TABLE+"("+CAT_ID+" INTEGER PRIMARY KEY, "
                        +CAT_NAME+" STRING NOT NULL UNIQUE, "
                        +BUDGET_NAME+" STRING, "
                        +BUDGET+" DOUBLE, "
                        +CAT_DESCRIPTION+" STRING) ";

        public static final String SQL_CAT_TABLE_DROP = "DROP TABLE IF EXISTS "+CAT_TABLE;


    }

    public class SchemaRapport{
        public static final String RAP_TABLE = "rapport_table";
        public static final String RAP_ID = "rapport_id";
        public static final String RAP_CAT_NAME = "rap_cat_name";
        public static final String DAY = "day";
        public static final String WEEK = "week";
        public static final String MONTH = "month";
        public static final String YEAR = "year";
        public static final String RAP_AMOUNT = "rap_amount";


        public static final String SQL_RAP_TABLE_QUERY =
                "create table "+RAP_TABLE+"("+RAP_ID+" INTEGER PRIMARY KEY, "
                        +RAP_CAT_NAME+" STRING, "
                        +DAY+" INTEGER, "
                        +WEEK+" INTEGER, "
                        +MONTH+" INTEGER, "
                        +YEAR+" INTEGER, "
                        +RAP_AMOUNT+" DOUBLE )";
        public static final String SQL_RAP_TABLE_DROP = "DROP TABLE IF EXISTS "+RAP_TABLE;

    }

    public class SchemaTranByDate{
        public static final String TRANDATE_TABLE = "transaction_byDate_table";
        public static final String TRANDATE_ID = "transaction_id";
        public static final String ASSETS = "assets";
        public static final String EXPENSES = "expenses";
        public static final String REMAINING = "remaining";

        public static final String SQL_TRANDATE_TABLE_QUERY =
                "create table "+TRANDATE_TABLE+"("+TRANDATE_ID+" INTEGER PRIMARY KEY, "
                        +ASSETS+" DOUBLE, "
                        +EXPENSES+" DOUBLE, "
                        +REMAINING+" DOUBLE )";
        public static final String SQL_TRANDATE_TABLE_DROP = "DROP TABLE IF EXISTS "+TRANDATE_TABLE;

    }

    public class Triggers{

            public final static String AFTER_INSERT_SPENDING_TRIGGER = "after_insert_spending_trigger";

        public final static String AFTER_INSERT_SPENDING_TRIGGER_QUERY = "CREATE TRIGGER "+ AFTER_INSERT_SPENDING_TRIGGER+
                " AFTER INSERT "+
                "ON "+"TABLE"+
                " BEGIN "+
                //Condition
                " SELECT RAISE(ABORT,'error'); END;";

    }




}
