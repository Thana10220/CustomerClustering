package com.thp.customerbehavior.Action;

import com.thp.customerbehavior.Domain.ReportDomain;
import com.thp.customerbehavior.Service.AdminService;
import com.thp.customerbehavior.Service.GetReportService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ProcessAction {

    GetReportService SERVICE = new GetReportService();

    @RequestMapping(value = "/Report" , method = RequestMethod.GET)
    public ModelAndView ReportAction(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Report/report");
        return modelAndView;
    }

    @RequestMapping(value = "/api/getReport" , produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    public List<ReportDomain> getinitaldata() throws SQLException, ClassNotFoundException {
        List<ReportDomain> REPORT_DOMAIN = new ArrayList<ReportDomain>();
        REPORT_DOMAIN = SERVICE.GET_ALL_REPORT();

        return REPORT_DOMAIN;
    }

    @RequestMapping(value = "/api/getDashboard" , produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    public List<ReportDomain> getDashboard() throws SQLException, ClassNotFoundException {
        List<ReportDomain> REPORT_DOMAIN = new ArrayList<ReportDomain>();
        REPORT_DOMAIN = SERVICE.GET_DASH_BOARD();

        return REPORT_DOMAIN;
    }


}
