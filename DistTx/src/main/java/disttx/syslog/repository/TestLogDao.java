package disttx.syslog.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import disttx.generic.repository.GenericRepository;
import disttx.syslog.model.TestLog;

@Repository

public class TestLogDao extends GenericRepository<TestLog>{
	@Autowired
	public TestLogDao(@Qualifier("secondaryEmf")EntityManager em) {
		this.em = em;
	}
}