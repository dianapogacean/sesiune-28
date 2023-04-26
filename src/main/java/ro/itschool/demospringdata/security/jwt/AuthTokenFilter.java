package ro.itschool.demospringdata.security.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import ro.itschool.demospringdata.security.service.CustomUserDetailsService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    /*
    ....
    Authorization Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkaWFuYUBtYWlsLmNvbSIsImlhdCI6MTY4MjQzMjE0NiwiZXhwIjoxNjgyNTE4NTQ2fQ.jGCu30-I0xYbgmWc5zjMxxdWrEGV7VPkXbt7Yu3taUd4sx1zorwxhMptR8QCwJhZSOB2mlSMAkm314D5Xugw6g
    ....
     */

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        try {
            String jwt = parseJwt(request);
            //extract username

            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                String username = jwtUtils.extractUsernameFromJwt(jwt);

                UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                //set the authetication object on the security context
                SecurityContextHolder.getContext().setAuthentication(authentication);

            }
        } catch (Exception e){
            logger.error("Cannot set user authenticated {}", e);
        }

        filterChain.doFilter(request, response);

    }

    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (!headerAuth.isEmpty() && headerAuth.startsWith("Bearer")) {
            return headerAuth.substring(7, headerAuth.length());
        }
        return null;
    }


}
