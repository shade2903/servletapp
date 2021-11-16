package com.haiduk.servlets;

import com.haiduk.entities.User;
import com.haiduk.service.DataService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/totalPrice")
public class ThirdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/shopList.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DataService dataService = new DataService();
        req.setAttribute("data",dataService.getSelectPriceList("mapKey",req));
        User user = User.getInstance();
        System.out.println(user.getName());



        doGet(req,resp);

    }
}
