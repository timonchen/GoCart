package comp3350.GoCart.tests.business;

import org.junit.After;
import org.junit.Before;

import java.io.File;
import java.io.IOException;

import comp3350.GoCart.business.AccessUsers;
import comp3350.GoCart.persistence.UserPersistence;
import comp3350.GoCart.persistence.hsqldb.UserPersistenceHSQLDB;
import comp3350.GoCart.tests.utils.TestUtils;

public class AccessUsersIT
{
    private AccessUsers accessUsers;
    private File tempDB;

    @Before
    public void setUp() throws IOException {
        this.tempDB = TestUtils.copyDB();
        final UserPersistence persistence = new UserPersistenceHSQLDB(this.tempDB.getAbsolutePath().replace(".script", ""));
        this.accessUsers = new AccessUsers(persistence);
    }
    
    @After
    public void tearDown() {
        // reset DB
        this.tempDB.delete();
    }
}
