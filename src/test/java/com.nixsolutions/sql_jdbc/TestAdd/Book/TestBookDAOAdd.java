package com.nixsolutions.sql_jdbc.TestAdd.Book;

import com.nixsolutions.sql_jdbc.applications.CreateBD;
import com.nixsolutions.sql_jdbc.applications.DropDB;
import com.nixsolutions.sql_jdbc.dao.implementations.DAOFactory;
import com.nixsolutions.sql_jdbc.dao.interfaces.DAOInterface;
import com.nixsolutions.sql_jdbc.entity.Book;
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

public class TestBookDAOAdd extends DBTestCase {


    public TestBookDAOAdd(String testName) throws Exception {
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
                "./src/test/java/testData/BookAddDataSet.xml"));

    }

    protected DatabaseOperation getSetUpOperation() throws Exception {
        return DatabaseOperation.REFRESH;
    }

    protected DatabaseOperation getTearDownOperation() throws Exception {
        return DatabaseOperation.NONE;
    }

    public void testAddBook() throws Exception {
        DAOInterface<Book> bookDAOInterface = DAOFactory.getInstance()
                .getBookDAO();
        Book book1 = new Book("14524", "The Iliad", "english", 1);
        Book book2 = new Book("40233", "Atlas Shrugged", "english", 2);
        Book book3 = new Book("45623", "Wuthering Heights", "deutsch", 1);

        bookDAOInterface.add(book1);
        bookDAOInterface.add(book2);
        bookDAOInterface.add(book3);

        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("book");
        IDataSet expectedDataSet = new FlatXmlDataSet(
                new File("./src/test/java/expectedTestData/BookAddExpected.xml"));

        ITable expectedTable = expectedDataSet.getTable("book");
        ITable filteredActualTable = DefaultColumnFilter
                .includedColumnsTable(actualTable,
                        expectedTable.getTableMetaData().getColumns());

        Assertion.assertEquals(expectedTable, filteredActualTable);
    }
}
