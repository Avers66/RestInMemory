package avers66.restinmemory.web.filter;

import avers66.restinmemory.scopes.IdHolder;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

/**
 * IdLoggingFilter
 *
 * @Author Tretyakov Alexandr
 */


@Order(1)
@Component
@RequiredArgsConstructor
public class IdLoggingFilter extends OncePerRequestFilter {

    private final List<IdHolder> idHolders;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        idHolders.forEach(IdHolder::logId);
        filterChain.doFilter(request, response);
    }
}
