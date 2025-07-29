package com.example.service;


import com.example.entity.CountOption;
import com.example.entity.JobOption;

import java.util.List;
import java.util.Map;

public interface ReportService {
    JobOption getEmpJobData();

    List<Map<String, Object>> getEmpGenderData();

    CountOption getStudentCountData();

    List<Map<String, Object>> getStudentDegreeData();
}
