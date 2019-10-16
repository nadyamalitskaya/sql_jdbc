package com.nixsolutions.sql_jdbc.TestAdd.SystemUser;

import com.nixsolutions.sql_jdbc.applications.CreateBD;
import com.nixsolutions.sql_jdbc.applications.DropDB;
import com.nixsolutions.sql_jdbc.dao.implementations.DAOFactory;
import com.nixsolutions.sql_jdbc.dao.interfaces.DAOInterface;
import com.nixsolutions.sql_jdbc.entity.SystemUser;
import org.apache.log4j.BasicConfigurator;
import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;

import java.io.File;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class TestSystemUserDAOAdd extends DBTestCase {

    public TestSystemUserDAOAdd(String testName) throws Exception {
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

    protected DatabaseOperation getSetUpOperation() throws Exception {
        return DatabaseOperation.REFRESH;
    }

    protected DatabaseOperation getTearDownOperation() throws Exception {
        return DatabaseOperation.NONE;
    }

    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSet(new File(
                "./src/test/java/testData/SystemUserTestAddDataSet.xml"));
    }

    public void testAddSystemUser() throws Exception {
        DAOInterface<SystemUser> systemUserDAOInterface = DAOFactory
                .getInstance().getSystemUserDAO();
        SystemUser systemUser = new SystemUser("Nadya", "Malitskaya", (long) 1,
                "BT818343", "380960887931", "Lenin str., 58",
                new GregorianCalendar(1999, Calendar.OCTOBER, 9).getTime(),
                (long) 1);
        SystemUser systemUser2 = new SystemUser("Dima", "Vasuk", (long) 2,
                "BC578456", "380667655903", "1561 Duis Rd.",
                new GregorianCalendar(1999, Calendar.NOVEMBER, 20).getTime(),
                (long) 1);
        SystemUser systemUser3 = new SystemUser("Oleg", "Karpenko", (long) 3,
                "BT954423", "380961418703", "7326 Elementum Rd.",
                new GregorianCalendar(1997, Calendar.JULY, 19).getTime(),
                (long) 2);

        systemUserDAOInterface.add(systemUser);
        systemUserDAOInterface.add(systemUser2);
        systemUserDAOInterface.add(systemUser3);

        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("system_user");
        IDataSet expectedDataSet = new FlatXmlDataSet(new File(
                "./src/test/java/expectedTestData/SystemUserAddExpectedDataSet.xml"));

        ITable expectedTable = expectedDataSet.getTable("system_user");
        ITable filteredActualTable = DefaultColumnFilter
                .includedColumnsTable(actualTable,
                        expectedTable.getTableMetaData().getColumns());

        Assertion.assertEquals(expectedTable, filteredActualTable);

    }
}
