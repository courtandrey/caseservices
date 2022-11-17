package com.github.courtandrey.caseview;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Decision {
    @Id
    private String id;
    @Column(columnDefinition = "CLOB(10k)")
    private String text;
}
