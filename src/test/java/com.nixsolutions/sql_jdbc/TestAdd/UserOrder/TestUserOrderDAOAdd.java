package com.nixsolutions.sql_jdbc.TestAdd.UserOrder;

import com.nixsolutions.sql_jdbc.applications.CreateBD;
import com.nixsolutions.sql_jdbc.applications.DropDB;
import com.nixsolutions.sql_jdbc.dao.implementations.DAOFactory;
import com.nixsolutions.sql_jdbc.dao.interfaces.DAOInterface;
import com.nixsolutions.sql_jdbc.entity.UserOrder;
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

public class TestUserOrderDAOAdd extends DBTestCase {
    public TestUserOrderDAOAdd(String testName) throws Exception {
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
        return new FlatXmlDataSet(
                new File("./src/test/java/testData/UserOrderAdd.xml"));
    }

    public void testUpdateUserOrder() throws Exception {
        DAOInterface<UserOrder> userOrderDAOInterface = DAOFactory.getInstance()
                .getUserOrderDAO();
        UserOrder userOrder1 = new UserOrder((long) 1, (long) 1, "Lenin. 96",
                new GregorianCalendar(2017, Calendar.FEBRUARY, 14).getTime(),
                new GregorianCalendar(2017, Calendar.MARCH, 20).getTime(),
                (long) 1);

        userOrderDAOInterface.add(userOrder1);

        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("user_order");
        IDataSet expectedDataSet = new FlatXmlDataSet(new File(
                "./src/test/java/expectedTestData/UserOrderAddExpected.xml"));

        ITable expectedTable = expectedDataSet.getTable("user_order");
        ITable filteredActualTable = DefaultColumnFilter
                .includedColumnsTable(actualTable,
                        expectedTable.getTableMetaData().getColumns());

        Assertion.assertEquals(expectedTable, filteredActualTable);
    }
}
