package spring.error;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.jta.JtaTransactionManager;


@Configuration
@EntityScan(basePackages = { "spring.error"})
@EnableJpaRepositories("spring.error")
public class EclipseLinkJpaConfig extends JpaBaseConfiguration {

	protected EclipseLinkJpaConfig(DataSource dataSource, JpaProperties properties,
			ObjectProvider<JtaTransactionManager> jtaTransactionManager) {
		super(dataSource, properties, jtaTransactionManager);
	}
	
	@Override
	protected AbstractJpaVendorAdapter createJpaVendorAdapter() {
		return new EclipseLinkJpaVendorAdapter();
	}
	
	@Override
	protected Map<String, Object> getVendorProperties() {
		HashMap<String, Object> map = new HashMap<>();
		map.put(PersistenceUnitProperties.LOGGING_LEVEL,"INFO"); 
		map.put(PersistenceUnitProperties.WEAVING,"FALSE");
		map.put(PersistenceUnitProperties.TARGET_DATABASE,"ORACLE");
		map.put(PersistenceUnitProperties.QUERY_CACHE,"TRUE");
		map.put(PersistenceUnitProperties.UPPERCASE_COLUMN_NAMES,"TRUE");
		map.put(PersistenceUnitProperties.CACHE_STATEMENTS,"TRUE");
		map.put(PersistenceUnitProperties.ID_VALIDATION,"NULL");
		return map;
	}
	
}