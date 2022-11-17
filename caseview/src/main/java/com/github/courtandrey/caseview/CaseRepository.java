package com.github.courtandrey.caseview;

import org.springframework.data.repository.CrudRepository;

public interface CaseRepository extends CrudRepository<Decision, String> { }
