package com.example.myworks.interceptor;

import com.example.myworks.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class AuthenticationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token=request.getHeader("Authorization");
        if("GET".equals(request.getMethod())){
            return true;
        }
        if(token==null|| !JwtUtils.checkToken(token)){
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("你还未登录，请先登录");
            return false;
        }
        Integer id=JwtUtils.getIdFromJwt(token);
        String userName=JwtUtils.getNameFromJwt(token);
        return true;
    }
}
