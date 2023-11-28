package nextstep.courses.domain;

import java.util.Optional;

public interface StudentRepository {
    int save(Student student);

    Students findBySessionId(long sessionId);

    Optional<Student> findByIdAndSessionId(long studentId,
                                           long sessionId);

    void updateSelection(Student student);
}
