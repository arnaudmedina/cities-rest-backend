package co.simplon.PoleEmploi.components;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EntityManagerProducer {

//	private static String DEFAULT_JDBC_URL = "jdbc:h2:file:~/db/referentiel";
//	private static String DEFAULT_JDBC_URL = "jdbc:h2:tcp://localhost/~/h2/h2-Simplon";
	private static String DEFAULT_JDBC_URL = "jdbc:h2:tcp://localhost/C:/Users/Utilisateur-SJD/h2/referentiel";

	private static final Logger LOGGER = LoggerFactory.getLogger(EntityManagerProducer.class);
	
	private static EntityManagerFactory EMF = null;

	private static synchronized void initEntityManagerFactory() {
		if (EMF == null) {
			final Map<String, String> properties = new HashMap<>();
			String jdbcUrl = System.getProperty("referentiel.db.jdbc.url");
			if (jdbcUrl == null) {
				LOGGER.debug("Fallback to default JDBC URL {}", DEFAULT_JDBC_URL);
				jdbcUrl = DEFAULT_JDBC_URL;
			}
			properties.put("javax.persistence.jdbc.url", jdbcUrl);
			properties.put("javax.persistence.jdbc.user", "sa");
			properties.put("javax.persistence.jdbc.password", "");
			properties.put("javax.persistence.jdbc.driver", "org.h2.Driver");
			properties.put("hibernate.show_sql", "true");
			EMF = Persistence.createEntityManagerFactory(
					"BasePatrimoineLocale", properties);
			LOGGER.info("Successfully created a JPA Entity Manager Factory for DB located at {}", jdbcUrl);
		}
	}

	@Produces
	@RequestScoped
	public EntityManager em() {
		initEntityManagerFactory();
		return EMF.createEntityManager();
	}

	public void dispose(@Disposes EntityManager em) {
		em.close();
	}

}