package com.yujindong.play.user.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.yujindong.play.user.dto.UserDto;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public abstract class LoginFilter implements Filter {
    private static Cache<String, UserDto> cache = CacheBuilder.newBuilder()
            .maximumSize(10000)
            .expireAfterWrite(3, TimeUnit.MINUTES)
            .build();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String token = request.getParameter("token");
        UserDto userDto = null;
        if(StringUtils.isBlank(token)) {
            Cookie[] cookies = request.getCookies();
            if(cookies != null) {
                for(Cookie cookie: cookies) {
                    if("token".equals(cookie.getName())) {
                        token = cookie.getValue();
                    }
                }
            }

            if(StringUtils.isNotBlank(token)) {
                userDto = cache.getIfPresent(token);
                if(userDto == null) {
                    userDto = requestUserInfo(token);
                    if(userDto != null) {
                        cache.put(token, userDto);
                    }
                }
            }

            if(userDto == null) {
                response.sendRedirect("http://127.0.0.1:50002/user/login");
                return;
            }
        }

        loginHandler(request, response, userDto);
        filterChain.doFilter(request, response);
    }

    protected abstract void loginHandler(HttpServletRequest request, HttpServletResponse response, UserDto userDto);

    private UserDto requestUserInfo(String token) {
        String url = "http://localhost:50002/user/authentication";
        HttpPost post = new HttpPost(url);
        post.addHeader("token", token);

        HttpClient client = HttpClients.createDefault();
        HttpResponse httpResponse = null;
        try {
            httpResponse = client.execute(post);
            HttpEntity httpEntity = httpResponse.getEntity();
            String response = EntityUtils.toString(httpEntity, "utf-8");

            if(StringUtils.isNotBlank(response)) {
                ObjectMapper mapper = new ObjectMapper();
                UserDto userInfo = mapper.readValue(response, UserDto.class);
                return userInfo;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void destroy() {

    }

    public static void main(String[] args) {
        String url = "http://localhost:50002/user/authentication";
        HttpPost post = new HttpPost(url);
        post.addHeader("token", "9e08744353ff440fa5fba542b34e3f47");

        HttpClient client = HttpClients.createDefault();
        HttpResponse httpResponse = null;
        try {
            httpResponse = client.execute(post);
            HttpEntity httpEntity = httpResponse.getEntity();
            String response = EntityUtils.toString(httpEntity, "utf-8");
            System.out.println(response);
            if(StringUtils.isNotBlank(response)) {
                ObjectMapper mapper = new ObjectMapper();
                UserDto userInfo = mapper.readValue(response, UserDto.class);
                System.out.println(userInfo);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
