package nl.alirezaa;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.db.DataSourceFactory;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;

// Hier wordt config.yml gepakt en wordt 'database' gevonden.
public class webshopBackendConfiguration extends Configuration {
    private DataSourceFactory database = new DataSourceFactory();

    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() { return this.database; }

    @JsonProperty("database")
    public void setDataSourceFactory(DataSourceFactory dataSourceFactory){
        this.database = dataSourceFactory;
    }
}
