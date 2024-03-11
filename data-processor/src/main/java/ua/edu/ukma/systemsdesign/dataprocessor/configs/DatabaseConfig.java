package ua.edu.ukma.systemsdesign.dataprocessor.configs;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.conf.*;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class DatabaseConfig {

    @Bean
    public DefaultConfiguration jooqConfigurations(DataSource dataSource) throws SQLException {

        try (var connection = dataSource.getConnection()) {

            var mappedSchema =
                    new MappedSchema().withInput("public").withOutput(connection.getCatalog());

            var settings =
                    new Settings()
                            .withRenderSchema(false)
                            .withRenderQuotedNames(RenderQuotedNames.NEVER)
                            .withRenderNameCase(RenderNameCase.AS_IS)
                            .withRenderMapping(new RenderMapping().withSchemata(mappedSchema));

            var configuration = new DefaultConfiguration();
            configuration.set(settings);
            configuration.setDataSource(dataSource);
            configuration.setSQLDialect(SQLDialect.MYSQL);

            return configuration;
        }
    }

    @Bean
    public DSLContext mySqlContext(DefaultConfiguration configuration) {

        return new DefaultDSLContext(configuration);
    }
}
