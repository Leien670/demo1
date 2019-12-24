package com.demo.web.servlet;

import com.demo.domin.CheckIn;
import com.demo.service.CheckinService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/checkin")
public class CheckinServlet extends HttpServlet {

    private CheckinService checkinsService = new CheckinService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        PrintWriter out = resp.getWriter();
        if("show".equals(action)) {
            List<CheckIn> list = checkinsService.findAll();
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

            out.print(gson.toJson(list));
        }else if("update".equals(action)){
            int cid = Integer.parseInt(req.getParameter("cid"));
            int totalprice = Integer.parseInt(req.getParameter("totalprice"));
            int roomid = Integer.parseInt(req.getParameter("roomid"));
            checkinsService.update(cid,totalprice,roomid);
            out.print("1");
        }
            out .close();

        }
    }

