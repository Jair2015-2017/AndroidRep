package com.example.jair.fin.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.example.jair.fin.dto.Category;
import com.example.jair.fin.dto.Transaction;
import com.example.jair.fin.dto.User;
import com.example.jair.fin.olap.DateUtil;
import com.example.jair.fin.olap.Rapport;
import com.example.jair.fin.olap.TranOnMonth;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.example.jair.fin.schema.Schema.DATABASE_NAME;
import static com.example.jair.fin.schema.Schema.DATABASE_VERSION;

import static com.example.jair.fin.schema.Schema.SchemaCategory.BUDGET;
import static com.example.jair.fin.schema.Schema.SchemaCategory.BUDGET_NAME;
import static com.example.jair.fin.schema.Schema.SchemaCategory.CAT_DESCRIPTION;
import static com.example.jair.fin.schema.Schema.SchemaCategory.CAT_ID;
import static com.example.jair.fin.schema.Schema.SchemaCategory.CAT_NAME;
import static com.example.jair.fin.schema.Schema.SchemaCategory.CAT_TABLE;
import static com.example.jair.fin.schema.Schema.SchemaCategory.SQL_CAT_TABLE_DROP;
import static com.example.jair.fin.schema.Schema.SchemaCategory.SQL_CAT_TABLE_QUERY;
import static com.example.jair.fin.schema.Schema.SchemaRapport.DAY;
import static com.example.jair.fin.schema.Schema.SchemaRapport.MONTH;
import static com.example.jair.fin.schema.Schema.SchemaRapport.RAP_AMOUNT;
import static com.example.jair.fin.schema.Schema.SchemaRapport.RAP_CAT_NAME;
import static com.example.jair.fin.schema.Schema.SchemaRapport.RAP_ID;
import static com.example.jair.fin.schema.Schema.SchemaRapport.RAP_TABLE;
import static com.example.jair.fin.schema.Schema.SchemaRapport.SQL_RAP_TABLE_DROP;
import static com.example.jair.fin.schema.Schema.SchemaRapport.SQL_RAP_TABLE_QUERY;
import static com.example.jair.fin.schema.Schema.SchemaRapport.WEEK;
import static com.example.jair.fin.schema.Schema.SchemaRapport.YEAR;
import static com.example.jair.fin.schema.Schema.SchemaTranByDate.ASSETS;
import static com.example.jair.fin.schema.Schema.SchemaTranByDate.EXPENSES;
import static com.example.jair.fin.schema.Schema.SchemaTranByDate.REMAINING;
import static com.example.jair.fin.schema.Schema.SchemaTranByDate.SQL_TRANDATE_TABLE_DROP;
import static com.example.jair.fin.schema.Schema.SchemaTranByDate.SQL_TRANDATE_TABLE_QUERY;
import static com.example.jair.fin.schema.Schema.SchemaTranByDate.TRANDATE_ID;
import static com.example.jair.fin.schema.Schema.SchemaTranByDate.TRANDATE_TABLE;
import static com.example.jair.fin.schema.Schema.SchemaTransaction.CAT_FK;
import static com.example.jair.fin.schema.Schema.SchemaTransaction.SQL_TRAN_TABLE_DROP;
import static com.example.jair.fin.schema.Schema.SchemaTransaction.SQL_TRAN_TABLE_QUERY;
import static com.example.jair.fin.schema.Schema.SchemaTransaction.TRAN_AMOUNT;
import static com.example.jair.fin.schema.Schema.SchemaTransaction.TRAN_DATE;
import static com.example.jair.fin.schema.Schema.SchemaTransaction.TRAN_ID;
import static com.example.jair.fin.schema.Schema.SchemaTransaction.TRAN_NAME;
import static com.example.jair.fin.schema.Schema.SchemaTransaction.TRAN_TABLE;
import static com.example.jair.fin.schema.Schema.SchemaTransaction.TRAN_TYPE;
import static com.example.jair.fin.schema.Schema.SchemaTransaction.USER_FK;
import static com.example.jair.fin.schema.Schema.SchemaUser.CREATED;
import static com.example.jair.fin.schema.Schema.SchemaUser.DELETED;
import static com.example.jair.fin.schema.Schema.SchemaUser.EMAIL;
import static com.example.jair.fin.schema.Schema.SchemaUser.ID;
import static com.example.jair.fin.schema.Schema.SchemaUser.NAME_USER;
import static com.example.jair.fin.schema.Schema.SchemaUser.SQL_USER_TABLE_DROP;
import static com.example.jair.fin.schema.Schema.SchemaUser.SQL_USER_TABLE_QUERY;
import static com.example.jair.fin.schema.Schema.SchemaUser.SURNAME;
import static com.example.jair.fin.schema.Schema.SchemaUser.USER_NAME;
import static com.example.jair.fin.schema.Schema.SchemaUser.USER_PASSWORD;
import static com.example.jair.fin.schema.Schema.SchemaUser.USER_TABLE;

/**
 * Created by Jair on 2/4/2017.
 */

public class FinDao extends SQLiteOpenHelper {

    public FinDao(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        updateDatabase(0);
        //theCates();

        //defUser();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {





    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateDatabase(DATABASE_VERSION);

    }

    public void updateDatabase(int version){
        //deze method maakt de tabellen aan
        SQLiteDatabase db=getWritableDatabase();
        try{

          /*  db.execSQL("drop table if exists account_table");
            db.execSQL("drop table if exists month_rapport_table ");db.execSQL("drop table if exists spending_category_table");
            db.execSQL("drop table if exists spending_date_table");db.execSQL("drop table if exists spending_table");
            db.execSQL(SQL_USER_TABLE_DROP);
            db.execSQL(SQL_TRAN_TABLE_DROP);
            db.execSQL(SQL_RAP_TABLE_DROP);
            db.execSQL(SQL_CAT_TABLE_DROP);
            db.execSQL(SQL_TRANDATE_TABLE_DROP);
*/
            db.execSQL(SQL_USER_TABLE_QUERY);
            db.execSQL(SQL_TRAN_TABLE_QUERY);
            db.execSQL(SQL_RAP_TABLE_QUERY);
            db.execSQL(SQL_CAT_TABLE_QUERY);
            db.execSQL(SQL_TRANDATE_TABLE_QUERY);


        }catch (Exception e){


            String s="wrong";
        }
    }

    public void insertRecord(String table, ContentValues contentValues){
        //deze method nsert data in de aangegeven table
        SQLiteDatabase db = getWritableDatabase();
        db.insert(table, null, contentValues);
        db.close();
    }

    public boolean updateRecordByID(String table,String table_ID_column, ContentValues contentValues, int id){
        SQLiteDatabase db = getWritableDatabase();

        return db.update(table, contentValues, table_ID_column+ " = " + id, null)>0;
    }


    public boolean insertUser(ContentValues contentValues){
        //deze method nsert data in de aangegeven table
        SQLiteDatabase db = getWritableDatabase();
        boolean  succes = db.insert(USER_TABLE, null, contentValues)>0;
        db.close();

        return succes;
    }

    public User getUserByID( long  id ){

        User user = null;
        Cursor cursor = null;
        SQLiteDatabase db= getReadableDatabase();

        cursor = db.query(USER_TABLE, null,
                ID+" = ?", new String[] { "" + id },null,null,null);
        if (cursor.moveToFirst()) {

            long user_id = cursor.getLong(cursor.getColumnIndex(ID));
            String username = cursor.getString(cursor.getColumnIndex(USER_NAME));
            String password= cursor.getString(cursor.getColumnIndex(USER_PASSWORD));
            String email = cursor.getString(cursor.getColumnIndex(EMAIL));
            String name_user = cursor.getString(cursor.getColumnIndex(NAME_USER));
            String surname= cursor.getString(cursor.getColumnIndex(SURNAME));
            String created= cursor.getString(cursor.getColumnIndex(CREATED));
            String deleted= cursor.getString(cursor.getColumnIndex(DELETED));

            user = new User(user_id,username,password,email,name_user,surname,created,deleted);

        }
        db.close();
        return user;
    }

    public User getUserByName( String  name ){

        User user = null;
        Cursor cursor = null;
        SQLiteDatabase db= getReadableDatabase();

        cursor = db.query(USER_TABLE, null,
                USER_NAME+" = ?", new String[] { "" + name },null,null,null);
        if (cursor.moveToFirst()) {

            long user_id = cursor.getLong(cursor.getColumnIndex(ID));
            String username = cursor.getString(cursor.getColumnIndex(USER_NAME));
            String password= cursor.getString(cursor.getColumnIndex(USER_PASSWORD));
            String email = cursor.getString(cursor.getColumnIndex(EMAIL));
            String name_user = cursor.getString(cursor.getColumnIndex(NAME_USER));
            String surname= cursor.getString(cursor.getColumnIndex(SURNAME));
            String created= cursor.getString(cursor.getColumnIndex(CREATED));
            String deleted= cursor.getString(cursor.getColumnIndex(DELETED));

            user = new User(user_id,username,password,email,name_user,surname,created,deleted);

        }
        db.close();
        return user;
    }

    public boolean deleteUserByID( long id ){

        SQLiteDatabase db = getWritableDatabase();
        return db.delete(USER_TABLE, ID + " = ?", new String[] { String.valueOf(id) })>0;
    }

    public boolean updateUserByID( long id,ContentValues contentValues ){
        SQLiteDatabase db = getWritableDatabase();

        return db.update(USER_TABLE, contentValues, ID+ " = " + id, null)>0;
    }


    public void insertCategory(ContentValues contentValues){
        //deze method nsert data in de aangegeven table
        SQLiteDatabase db = getWritableDatabase();
        db.insert(CAT_TABLE, null, contentValues);
        db.close();
    }

    public Category getCategoryByID( long id){
        Category category = null;
        Cursor cursor = null;
        SQLiteDatabase db= getReadableDatabase();

        cursor = db.query(CAT_TABLE, null,
                CAT_ID+" = ?", new String[] { "" + id },null,null,null);
        if (cursor.moveToFirst()) {

            long cat_id = cursor.getLong(cursor.getColumnIndex(CAT_ID));
            String cat_name = cursor.getString(cursor.getColumnIndex(CAT_NAME));
            String cat_description = cursor.getString(cursor.getColumnIndex(CAT_DESCRIPTION));
            double budget = cursor.getDouble(cursor.getColumnIndex(BUDGET));
            String budget_name = cursor.getString(cursor.getColumnIndex(BUDGET_NAME));

            category = new Category(cat_id,cat_name,cat_description,budget_name,budget);

        }
        db.close();
        return category;

    }

    public Category getCategoryByName( String name){
        Category category = null;
        Cursor cursor = null;
        SQLiteDatabase db= getReadableDatabase();

        cursor = db.query(CAT_TABLE, null,
                CAT_NAME+" = ?", new String[] { "" + name },null,null,null);
        if (cursor.moveToFirst()) {

            long cat_id = cursor.getLong(cursor.getColumnIndex(CAT_ID));
            String cat_name = cursor.getString(cursor.getColumnIndex(CAT_NAME));
            String cat_description = cursor.getString(cursor.getColumnIndex(CAT_DESCRIPTION));
            double budget = cursor.getDouble(cursor.getColumnIndex(BUDGET));
            String budget_name = cursor.getString(cursor.getColumnIndex(BUDGET_NAME));

            category = new Category(cat_id,cat_name,cat_description,budget_name,budget);

        }
        db.close();
        return category;

    }

    public Category getCategoryByBudget( String name){
        Category category = null;
        Cursor cursor = null;
        SQLiteDatabase db= getReadableDatabase();

        cursor = db.query(CAT_TABLE, null,
                BUDGET_NAME+" = ?", new String[] { "" + name },null,null,null);
        if (cursor.moveToFirst()) {

            long cat_id = cursor.getLong(cursor.getColumnIndex(CAT_ID));
            String cat_name = cursor.getString(cursor.getColumnIndex(CAT_NAME));
            String cat_description = cursor.getString(cursor.getColumnIndex(CAT_DESCRIPTION));
            double budget = cursor.getDouble(cursor.getColumnIndex(BUDGET));
            String budget_name = cursor.getString(cursor.getColumnIndex(BUDGET_NAME));

            category = new Category(cat_id,cat_name,cat_description,budget_name,budget);

        }
        db.close();
        return category;

    }

    public boolean deleteCategoryByID( long id ){
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(CAT_TABLE, CAT_ID + " = ?", new String[] { String.valueOf(id) })>0;
    }

    public boolean updateCategoryByID( long id,ContentValues contentValues ){
        SQLiteDatabase db = getWritableDatabase();

        return db.update(CAT_TABLE, contentValues, CAT_ID+ " = " + id, null)>0;
    }

    public boolean addBudget(long categoryID ,String budgetName,double budget){

        ContentValues contentValues= new ContentValues();
        contentValues.put(BUDGET_NAME,budgetName);
        contentValues.put(BUDGET,budget);
        return updateCategoryByID(categoryID,contentValues);

    }

    public boolean deleteBudget(long categoryID){

        ContentValues contentValues= new ContentValues();
        contentValues.put(BUDGET_NAME,"no budget");
        contentValues.put(BUDGET,0);
        return updateCategoryByID(categoryID,contentValues);
    }

    public List<Category> getAllCategories(){
        SQLiteDatabase db = getReadableDatabase();
        Category category;
        List<Category> list = new ArrayList<>();

        Cursor  cursor = db.query(CAT_TABLE,null,null,null,null,null,null);

        if (cursor .moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex(CAT_NAME));
                category = getCategoryByName(name);

                cursor.moveToNext();

                list.add(category);

            }while (cursor.isAfterLast() == false);

        }

        return list;
    }


    public void insertTran(ContentValues contentValues,Transaction transaction){
        //deze method nsert data in de aangegeven table
        Calendar date= Calendar.getInstance();
        before_insert_Transaction_trigger(transaction,date);
        before_insert_Transaction_trigger(transaction);

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TRAN_TABLE, null, contentValues);

        db.close();
    }

    public Transaction getTranByID( long id){
        Transaction transaction = null;
        Cursor cursor = null;
        SQLiteDatabase db= getReadableDatabase();

        cursor = db.query(TRAN_TABLE, null,
                TRAN_ID+" = ?", new String[] { "" + id },null,null,null);
        if (cursor.moveToFirst()) {

            long tran_id = cursor.getLong(cursor.getColumnIndex(TRAN_ID));
            String tran_name = cursor.getString(cursor.getColumnIndex(TRAN_NAME));
            double tran_amount = cursor.getDouble(cursor.getColumnIndex(TRAN_AMOUNT));
            String tran_type = cursor.getString(cursor.getColumnIndex(TRAN_TYPE));
            String date = cursor.getString(cursor.getColumnIndex(TRAN_DATE));
            long user_id = cursor.getLong(cursor.getColumnIndex(USER_FK));
            long cat_id = cursor.getLong(cursor.getColumnIndex(CAT_FK));

            transaction = new Transaction(tran_id,tran_name,tran_amount,tran_type,date,getUserByID(user_id),getCategoryByID(cat_id));

        }
        db.close();
        return transaction;

    }

    public boolean deleteTranByID( long id ){
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TRAN_TABLE, TRAN_ID + " = ?", new String[] { String.valueOf(id) })>0;
    }

    public boolean updateTranByID( long id,ContentValues contentValues ){
        SQLiteDatabase db = getWritableDatabase();

        return db.update(TRAN_TABLE, contentValues, TRAN_ID+ " = " + id, null)>0;
    }

    public Transaction getLastTran(){
        Transaction transaction = null;
        Cursor cursor = null;
        SQLiteDatabase db= getReadableDatabase();

        String customquery = "SELECT * FROM "+TRAN_TABLE+
        " WHERE "+TRAN_ID+" = (SELECT MAX("+TRAN_ID+")"+TRAN_TABLE+ ")";
        cursor = db.rawQuery(customquery,null);
        if (cursor.moveToFirst()) {

            long tran_id = cursor.getLong(cursor.getColumnIndex(TRAN_ID));
            String tran_name = cursor.getString(cursor.getColumnIndex(TRAN_NAME));
            double tran_amount = cursor.getDouble(cursor.getColumnIndex(TRAN_AMOUNT));
            String tran_type = cursor.getString(cursor.getColumnIndex(TRAN_TYPE));
            String date = cursor.getString(cursor.getColumnIndex(TRAN_DATE));
            long user_id = cursor.getLong(cursor.getColumnIndex(USER_FK));
            long cat_id = cursor.getLong(cursor.getColumnIndex(CAT_FK));

            transaction = new Transaction(tran_id,tran_name,tran_amount,tran_type,date,getUserByID(user_id),getCategoryByID(cat_id));

        }
        db.close();
        return transaction;
    }


    public boolean insertRapport(ContentValues contentValues){
        //deze method nsert data in de aangegeven table
        SQLiteDatabase db = getWritableDatabase();
        long succes = db.insert(RAP_TABLE, null, contentValues);
        db.close();
        return succes>0;
    }

    public Rapport getRapByID(long id){
        Rapport rapport = null;
        Cursor cursor = null;
        SQLiteDatabase db= getReadableDatabase();

        cursor = db.query(RAP_TABLE, null,
                RAP_ID+" = ?", new String[] { "" + id },null,null,null);
        if (cursor.moveToFirst()) {

            long rap_id = cursor.getLong(cursor.getColumnIndex(RAP_ID));
            String cat_name = cursor.getString(cursor.getColumnIndex(RAP_CAT_NAME));
            int day = cursor.getInt(cursor.getColumnIndex(DAY));
            int week = cursor.getInt(cursor.getColumnIndex(WEEK));
            int month = cursor.getInt(cursor.getColumnIndex(MONTH));
            int year = cursor.getInt(cursor.getColumnIndex(YEAR));
            double amount = cursor.getDouble(cursor.getColumnIndex(RAP_AMOUNT));

            rapport = new Rapport(rap_id,cat_name,day,week,month,year,amount);
        }
        db.close();
        return rapport;

    }

    public boolean deleteRapByID( long id ){
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(RAP_TABLE, RAP_ID + " = ?", new String[] { String.valueOf(id) })>0;
    }

    public boolean updateRapByID( long id,ContentValues contentValues ){
        SQLiteDatabase db = getWritableDatabase();

        return db.update(RAP_TABLE, contentValues, RAP_ID+ " = " + id, null)>0;
    }


    public boolean insertTOM(ContentValues contentValues){
        //deze method nsert data in de aangegeven table
        SQLiteDatabase db = getWritableDatabase();
        boolean succes = db.insert(TRANDATE_TABLE, null, contentValues)>0;
        db.close();
        return succes;
    }

    public TranOnMonth getTOMByID(long id){
        TranOnMonth tranOnMonth = null;
        Cursor cursor = null;
        SQLiteDatabase db= getReadableDatabase();

        cursor = db.query(TRAN_DATE, null,
                TRANDATE_ID+" = ?", new String[] { "" + id },null,null,null);
        if (cursor.moveToFirst()) {

            long tom_id = cursor.getLong(cursor.getColumnIndex(TRANDATE_ID));
            double assets = cursor.getDouble(cursor.getColumnIndex(ASSETS));
            double expenses = cursor.getDouble(cursor.getColumnIndex(EXPENSES));
            double remaining = cursor.getDouble(cursor.getColumnIndex(REMAINING));

            tranOnMonth = new TranOnMonth(tom_id,assets,expenses,remaining);
        }
        db.close();
        return tranOnMonth;

    }

    public boolean deleteTOMByID( long id ){
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TRANDATE_TABLE, TRANDATE_ID + " = ?", new String[] { String.valueOf(id) })>0;
    }

    public boolean updateTOMByID( long id,ContentValues contentValues ){
        SQLiteDatabase db = getWritableDatabase();

        return db.update(TRANDATE_TABLE, contentValues, TRANDATE_ID+ " = " + id, null)>0;

    }

    public TranOnMonth getLastTOM(){
        TranOnMonth tranOnMonth  = null;
        Cursor cursor = null;
        SQLiteDatabase db= getReadableDatabase();

        String customquery = "SELECT * FROM "+TRANDATE_TABLE+
                " WHERE "+TRANDATE_ID+" = (SELECT MAX("+TRANDATE_ID+")  FROM "+TRANDATE_TABLE+")";
        cursor = db.rawQuery(customquery,null);
        if (cursor.moveToFirst()) {

            //heeeeeerrreee
            long tom_id = cursor.getLong(cursor.getColumnIndex(TRANDATE_ID));
            double assets = cursor.getDouble(cursor.getColumnIndex(ASSETS));
            double expenses = cursor.getDouble(cursor.getColumnIndex(EXPENSES));
            double remaining = cursor.getDouble(cursor.getColumnIndex(REMAINING));

            tranOnMonth = new TranOnMonth(tom_id,assets,expenses,remaining);
        }
        db.close();
        return tranOnMonth;

    }

    private boolean before_insert_Transaction_trigger(Transaction transaction, Calendar date) {

    ContentValues contentValues1 = new ContentValues();
    Date theDate = date.getTime();
    //dateArr = d,w,m,y
    int[] dateArr = DateUtil.convertDate(theDate);
    contentValues1.put(RAP_CAT_NAME, transaction.getCategory().getCat_name());
    //monday = 1 , sunday = 7
    contentValues1.put(DAY, dateArr[0]);
    contentValues1.put(WEEK, dateArr[1]);
    contentValues1.put(MONTH, dateArr[2]);
    contentValues1.put(YEAR, dateArr[3]);
    contentValues1.put(RAP_AMOUNT, transaction.getTran_amount());

 return insertRapport(contentValues1);

    }

    private boolean before_insert_Transaction_trigger(Transaction transaction){

        double expensesNew=0;
        double remainingNew=0;
        ContentValues contentValues=new ContentValues();
        TranOnMonth tranOnMonth = getLastTOM();

        if (transaction.getTran_type().equals("spending")){

            expensesNew = tranOnMonth.getExpenses()+transaction.getTran_amount();
            remainingNew = tranOnMonth.getAssets()-expensesNew;


        }else if (transaction.getTran_type().equals("earning")){

            expensesNew = tranOnMonth.getExpenses()- transaction.getTran_amount();
            remainingNew = tranOnMonth.getAssets()- expensesNew;

        }else{
            Log.i("TriggerError","transaction type does not equal : spending nor earning.\nclass:Findao, method = before_insert_Transaction_trigger");
        }

        contentValues.put(EXPENSES,expensesNew);
        contentValues.put(REMAINING,remainingNew);
        return updateTOMByID(tranOnMonth.getTom_id(),contentValues);

    }

    void defUser(){

        updateDatabase(0);
        Calendar date=Calendar.getInstance();String dateStr = String.valueOf(date.getTime());

        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_NAME,"admin");
        contentValues.put(USER_PASSWORD,"admin");
        contentValues.put(EMAIL,"admin@admin.com");
        contentValues.put(NAME_USER,"admin_name");
        contentValues.put(SURNAME,"admin_surname");
        contentValues.put(CREATED,dateStr);
        insertUser(contentValues);

        contentValues =new ContentValues();
        contentValues.put(ASSETS,1000.00);
        contentValues.put(EXPENSES,0);
        contentValues.put(REMAINING,1000);
        insertTOM(contentValues);


    }

    void theCates(){

        catMeth("food","spending on food","food budget",200);
        catMeth("entertainment","movies,going to the mall,going to a show","entert budget",300);
        catMeth("clothing & beauty","clothes you buy etc","clothes budget",100);
        catMeth("transportation","taking the bus, paying for benzine","car budget",50);

    }

    void catMeth(String name,String description,String budget_name,double budget) {

        ContentValues contentValues=new ContentValues();
        contentValues.put(CAT_NAME,name);
        contentValues.put(CAT_DESCRIPTION,description);
        contentValues.put(BUDGET_NAME,budget_name);
        contentValues.put(BUDGET,budget);

        insertCategory(contentValues);
    }
}
//public boolean before_insert_Transaction_trigger(Transaction transaction)= goed
// get last methods = goed;
