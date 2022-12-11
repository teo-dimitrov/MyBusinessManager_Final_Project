package com.example.mybusinessmanager_final_project.config;

import com.example.mybusinessmanager_final_project.service.OvertimeService;
import com.example.mybusinessmanager_final_project.service.ReportService;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;

public class MBMMethodSecurityExpressionHandler extends DefaultMethodSecurityExpressionHandler {

    private final ReportService reportService;
    private final OvertimeService overtimeService;

    public MBMMethodSecurityExpressionHandler(ReportService reportService, OvertimeService overtimeService) {
        this.reportService = reportService;
        this.overtimeService = overtimeService;
    }


    @Override
    protected MethodSecurityExpressionOperations createSecurityExpressionRoot(
            Authentication authentication, MethodInvocation invocation) {
        OwnerSecurityExpressionRoot root = new OwnerSecurityExpressionRoot(authentication);

        root.setReportService(reportService);
        root.setOvertimeService(overtimeService);
        root.setPermissionEvaluator(getPermissionEvaluator());
        root.setTrustResolver(new AuthenticationTrustResolverImpl());
        root.setRoleHierarchy(getRoleHierarchy());

        return root;

    }
}
