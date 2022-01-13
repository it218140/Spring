package gr.hua.dit.ds.restdemo.repositiry;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gr.hua.dit.ds.restdemo.entity.Authority;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
	
	//Delete Authorities from user where username found
	@Transactional
	@Modifying
	@Query("Delete from Authority Where username=:username")
	void DeleteByUsername(@Param("username") String username);
}
