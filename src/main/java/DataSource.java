import oracle.toplink.essentials.config.TopLinkProperties;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Startup;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.inject.Singleton;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;



@Singleton
@Startup
@DataSourceDefinition(
        className = "com.mysql.jdbc.jdbc2.optional.MysqlDataSource",
        name = "java:global/jdbc/swebok",
        user = "root",
        password = "1234",
        databaseName = "swebok",
        url="jdbc:mysql://localhost:3306"
)
public class DataSource implements Serializable {



}
