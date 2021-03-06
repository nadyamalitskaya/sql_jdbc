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

public class TestSystemUserDAOUpdate extends DBTestCase {

    public TestSystemUserDAOUpdate(String testName) throws Exception {
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
        return new FlatXmlDataSet(new File("./src/test/java/expectedTestData/"
                + "SystemUserUpdateExpectedDataSet.xml"));
    }

    public void testUpdateSystemUser() throws Exception {

        DAOInterface<SystemUser> systemUserDAOInterface = DAOFactory
                .getInstance().getSystemUserDAO();
        SystemUser systemUser = systemUserDAOInterface.getById(1);

        systemUser.setFirstName("Katya");
        systemUserDAOInterface.update(systemUser);

        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("system_user");
        IDataSet expectedDataSet = new FlatXmlDataSet(new File(
                "./src/test/java/expectedTestData/SystemUserUpdateExpectedDataSet.xml"));

        ITable expectedTable = expectedDataSet.getTable("system_user");
        ITable filteredActualTable = DefaultColumnFilter
                .includedColumnsTable(actualTable,
                        expectedTable.getTableMetaData().getColumns());

        Assertion.assertEquals(expectedTable, filteredActualTable);
    }
}
