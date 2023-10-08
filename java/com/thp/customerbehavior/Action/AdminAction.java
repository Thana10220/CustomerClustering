package com.thp.customerbehavior.Action;

import com.thp.customerbehavior.Domain.AdminDomain;
import com.thp.customerbehavior.Domain.ReportDomain;
import com.thp.customerbehavior.Service.AdminService;
import com.thp.customerbehavior.Service.GetReportService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AdminAction {

    AdminService SERVICE = new AdminService();

    @RequestMapping(value = "/Authen" , method = RequestMethod.GET)
    public ModelAndView AuthenAction(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Authen/Authen");
        return modelAndView;
    }

    @RequestMapping(value = "/Admin" , method = RequestMethod.GET)
    public ModelAndView ReportAction(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Admin/Admin");
        return modelAndView;
    }

    @RequestMapping(value = "/api/process" , produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    public boolean process() throws SQLException, ClassNotFoundException {
        SERVICE.ML_MODULE();
        return true;
    }


    @RequestMapping(value = "/api/getjob" , produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    public List<AdminDomain> getjob() throws SQLException, ClassNotFoundException {
        List<AdminDomain> ADMIN_DOMAIN = new ArrayList<AdminDomain>();
        ADMIN_DOMAIN = SERVICE.GET_JOB();
        return ADMIN_DOMAIN;
    }

    @RequestMapping(value = "/api/getinitpara" , produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    public AdminDomain getInitPara() throws SQLException, ClassNotFoundException {
        AdminDomain ADMIN_DOMAIN = new AdminDomain();
        ADMIN_DOMAIN = SERVICE.GET_INIT_PARAMETER();
        return ADMIN_DOMAIN;
    }

    @RequestMapping(value = "/api/updatepara" , produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    public boolean updatePara(@RequestParam int KMEAN,
                                  @RequestParam float DB_SCAN,
                                  @RequestParam int PAM,
                                  @RequestParam int MEANSHIFT) throws SQLException, ClassNotFoundException {
        AdminDomain ADMIN_DOMAIN = new AdminDomain();
        ADMIN_DOMAIN.setK_mean_cluster(KMEAN);
        ADMIN_DOMAIN.setDbscan_eps(DB_SCAN);
        ADMIN_DOMAIN.setKmedoid_cluster(PAM);
        ADMIN_DOMAIN.setMeanshift_bndwidth(MEANSHIFT);
        boolean result = SERVICE.UPDATE_PARAMETER(ADMIN_DOMAIN);
        return result;
    }

}
