package disttx.player.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import disttx.generic.repository.GenericRepository;
import disttx.player.model.UserDetails;

@Repository

public class UserDetailsDao extends GenericRepository<UserDetails>{
	@Autowired
	public UserDetailsDao(@Qualifier("primaryEmf")EntityManager em){
		this.em = em;
	}
}