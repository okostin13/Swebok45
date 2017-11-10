import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Startup;
import javax.inject.Singleton;

@Singleton
@Startup
@DataSourceDefinition(
        className = "com.mysql.jdbc.jdbc2.optional.MysqlDataSource",
        name = "java:global/Swebok/swebok",
        user = "root",
        password = "root",
        portNumber=3306,
        serverName="localhost",
        databaseName = "swebok",
        properties = ("create = true")
)
public class DataSourceConfig {

}
