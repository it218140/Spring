package gr.hua.dit.ds.restdemo.repositiry;

import org.springframework.data.jpa.repository.JpaRepository;

import gr.hua.dit.ds.restdemo.entity.UserDetails;

public interface UserDetailsRepository extends JpaRepository<UserDetails, String> {

}
