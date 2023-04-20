package idusw.springboot.boardjhm.repository;

import idusw.springboot.boardjhm.entity.MemoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoRepository extends JpaRepository<MemoEntity, Long> {

}
