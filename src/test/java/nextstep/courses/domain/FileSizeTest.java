package nextstep.courses.domain;

import nextstep.courses.exception.VolumeOverException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FileSizeTest {

    @Test
    @DisplayName("크기가 1MB를 초과하면 예외 처리 한다")
    void newVolume() {
        Assertions.assertThrows(VolumeOverException.class, () -> new FileSize(1024L * 1024L + 1L),
                "파일 크기는 1MB 이하여야 합니다.");
    }
}
