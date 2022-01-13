package gr.hua.dit.ds.restdemo.repositiry;

import java.util.List;

import gr.hua.dit.ds.restdemo.entity.Subjects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import gr.hua.dit.ds.restdemo.entity.Subjects;
import gr.hua.dit.ds.restdemo.entity.User;

public interface SubjectsRepository extends JpaRepository<Subjects, Integer> {
	
	

	@Query("SELECT d FROM Subjects d where d.id= :id")
    List<Subjects> findByid(@Param("id") String id);
	

	@Query("Update Subjects d SET d.state=':state' where d.id=:id")
	void UpdateState(@Param("state") String state,@Param("id") int id);
	
	@Query("SELECT d FROM Subjects d where d.user=:user")
	List<Subjects> findByUsername(@Param("user")User user);

}
