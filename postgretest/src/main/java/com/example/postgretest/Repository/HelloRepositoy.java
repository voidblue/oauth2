package com.example.postgretest.Repository;

        import com.example.postgretest.Model.Hello;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.stereotype.Repository;

        import java.util.Optional;

@Repository
public interface HelloRepositoy extends JpaRepository<Hello, Integer> {
    Optional<Hello> findByName(String name);
}
