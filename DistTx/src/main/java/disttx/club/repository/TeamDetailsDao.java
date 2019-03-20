package disttx.club.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import disttx.club.model.TeamDetails;
import disttx.generic.repository.GenericRepository;

@Repository
public class TeamDetailsDao extends GenericRepository<TeamDetails>{
	@Autowired
	public TeamDetailsDao(@Qualifier("secondaryEmf")EntityManager em){
		this.em = em;
	}
}
