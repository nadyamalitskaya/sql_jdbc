package com.nixsolutions.sql_jdbc.TestAdd.BookGenre;

import com.nixsolutions.sql_jdbc.applications.CreateBD;
import com.nixsolutions.sql_jdbc.applications.DropDB;
import com.nixsolutions.sql_jdbc.dao.implementations.DAOFactory;
import com.nixsolutions.sql_jdbc.dao.interfaces.DAOInterface;
import com.nixsolutions.sql_jdbc.entity.BookGenre;
import org.apache.log4j.BasicConfigurator;
import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;

import java.io.File;
import java.io.FileInputStream;

public class TestBookGenreDAOAdd extends DBTestCase {


    public TestBookGenreDAOAdd(String testName) throws Exception {
        super(testName);

        System.setProperty(
                PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS,
                "org.postgresql.Driver");
        System.setProperty(
                PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL,
                "jdbc:postgresql://localhost:5432/postgres");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME,
                "postgres");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD,
                "");

        String[] argv = new String[0];
        DropDB.main(argv);
        CreateBD.main(argv);
        BasicConfigurator.configure();
    }

    @Override protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(new FileInputStream(
                "./src/test/java/testData/BookGenreAdd.xml"));
    }

    protected DatabaseOperation getSetUpOperation() throws Exception {
        return DatabaseOperation.REFRESH;
    }

    protected DatabaseOperation getTearDownOperation() throws Exception {
        return DatabaseOperation.NONE;
    }

    public void testAddBookGenre() throws Exception {
        DAOInterface<BookGenre> bookGenreDAO = DAOFactory.getInstance()
                .getBookGenreDAO();
        BookGenre bookGenre1 = new BookGenre((long) 1, (long) 1);
        BookGenre bookGenre2 = new BookGenre((long) 2, (long) 3);
        BookGenre bookGenre3 = new BookGenre((long) 3, (long) 2);

        bookGenreDAO.add(bookGenre1);
        bookGenreDAO.add(bookGenre2);
        bookGenreDAO.add(bookGenre3);

        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("book_genre");
        IDataSet expectedDataSet = new FlatXmlDataSet(new File(
                "./src/test/java/expectedTestData/BookGenreAddExpected.xml"));

        ITable expectedTable = expectedDataSet.getTable("book_genre");
        ITable filteredActualTable = DefaultColumnFilter
                .includedColumnsTable(actualTable,
                        expectedTable.getTableMetaData().getColumns());

        Assertion.assertEquals(expectedTable, filteredActualTable);
    }
}
