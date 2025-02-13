package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.NotFoundException;
import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.Objects;

public class Answer {
    private Long id;

    private NsUser writer;

    private Question question;

    private String contents;

    private boolean deleted = false;

    private LocalDateTime createdDate = LocalDateTime.now();

    private LocalDateTime updatedDate;

    public Answer() {
    }

    public Answer(NsUser writer,
                  Question question,
                  String contents) {
        this(0L, writer, question, contents);
    }

    public Answer(NsUser writer,
                  Question question,
                  String contents,
                  boolean deleted) {
        this(0L, writer, question, contents);
        this.deleted = deleted;
    }

    public Answer(NsUser writer,
                  Question question,
                  String contents,
                  LocalDateTime localDateTime) {
        this(0L, writer, question, contents);
        this.createdDate = localDateTime;
    }

    public Answer(NsUser writer,
                  Question question,
                  String contents,
                  boolean deleted,
                  LocalDateTime localDateTime) {
        this(writer, question, contents, deleted);
        this.createdDate = localDateTime;
    }

    public Answer(Long id,
                  NsUser writer,
                  Question question,
                  String contents) {
        this.id = id;
        if (writer == null) {
            throw new UnAuthorizedException();
        }

        if (question == null) {
            throw new NotFoundException();
        }

        this.writer = writer;
        this.question = question;
        this.contents = contents;
    }

    public Long getId() {
        return id;
    }

    public Answer setDeleted(boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public DeleteHistory delete(NsUser loginUser,
                                LocalDataTimeHolder localDataTimeHolder) {
        validateOwner(loginUser);
        this.deleted = true;
        return new DeleteHistory(ContentType.ANSWER, id, writer, localDataTimeHolder.now());
    }

    private void validateOwner(NsUser loginUser) {
        if (!isOwner(loginUser)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }

    public boolean isOwner(NsUser writer) {
        return this.writer.equals(writer);
    }

    public NsUser getWriter() {
        return writer;
    }

    public String getContents() {
        return contents;
    }

    public void toQuestion(Question question) {
        this.question = question;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return deleted == answer.deleted && Objects.equals(id, answer.id) && Objects.equals(writer, answer.writer) && Objects.equals(question, answer.question) && Objects.equals(contents, answer.contents) && Objects.equals(createdDate, answer.createdDate) && Objects.equals(updatedDate, answer.updatedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, writer, question, contents, deleted, createdDate, updatedDate);
    }

    @Override
    public String toString() {
        return "Answer [id=" + getId() + ", writer=" + writer + ", contents=" + contents + "]";
    }
}
