package com.haiduk.servlets;


import com.haiduk.domain.PriceList;
import com.haiduk.domain.Product;
import com.haiduk.entities.User;
import com.haiduk.service.DataService;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;


@WebServlet("/priceList")
public class PriceListServlet extends HttpServlet {
    private final static ArrayList<Product> listProduct = PriceList.getListProduct();
    private final static ArrayList<Product> clickList = new ArrayList<>();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/jsp/menu.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute("userName", req.getParameter("userName"));
        req.setAttribute("products", listProduct);
        DataService service = new DataService();
        clickList.addAll(service.getSelect("mapKey",req));
        session.setAttribute("clickList", clickList);

        doGet(req,resp);


    }
}
