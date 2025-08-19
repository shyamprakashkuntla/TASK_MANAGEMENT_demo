package com.task.TaskManagement.controllers;

import com.task.TaskManagement.Entity.AuditLogsEntity;
import com.task.TaskManagement.dto.AuditLogsdto;
import com.task.TaskManagement.dto.ResponseWrapper;
import com.task.TaskManagement.services.AuditLogsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/audits")
public class AuditLogsController {

    @Autowired
    private AuditLogsService auditLogs;

    @PostMapping
    public ResponseEntity<ResponseWrapper<AuditLogsEntity>> createAudits(@Valid @RequestBody AuditLogsdto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(auditLogs.createAuditLog(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseWrapper<AuditLogsEntity>> updateAudits(@PathVariable Integer id, @RequestBody AuditLogsdto dto) {
        return ResponseEntity.ok(auditLogs.updateAuditLog(id, dto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWrapper<String>> deleteAudits(@PathVariable Integer id) {
        return ResponseEntity.ok(auditLogs.deleteAuditLog(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseWrapper<AuditLogsEntity>> getAuditById(@PathVariable Integer id) {
        return ResponseEntity.ok(auditLogs.getAuditLogById(id));
    }
    @GetMapping
    public ResponseEntity<ResponseWrapper<List<AuditLogsEntity>>> getAllAudits () {

        return ResponseEntity.ok(auditLogs.getAllAuditLogs());
    }
    @GetMapping("/filter")
    public List<AuditLogsEntity> filterAuditLogs(
            @RequestParam(required = false) String userName,
            @RequestParam(required = false) String operation,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate
    ) {
        return auditLogs.filterAuditLogs(userName, operation, startDate, endDate);
    }
    @GetMapping("/paged")
    public ResponseEntity<ResponseWrapper<List<AuditLogsEntity>>> geAuditsByPage(
            @RequestParam(defaultValue = "1") int pageNo) {

        int pageSize = 5;
        List<AuditLogsEntity> audits =auditLogs.getAuditsPageOnly(pageNo - 1, pageSize); // Page index is 0-based

        return ResponseEntity.ok(new ResponseWrapper<>("AuditLogs fetched successfully", audits));
    }

}
