package disttx.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class BaseRepository {
	@PersistenceContext
	private EntityManager em;
	
}
