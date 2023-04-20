package idusw.springboot.boardjhm.domain;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@ToString
@EqualsAndHashCode
public class Member {
    // DTO : Data Transfer Object, 데이터 전송 객체 Controller -> Service -> Controller -> View
    // Domain(실제 업무 영역) Object
    private Long seq;
    private String email;
    private String name;
    private String pw;
}
