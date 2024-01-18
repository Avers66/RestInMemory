package avers66.restinmemory.scopes;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

/**
 * RequestHolder
 *
 * @Author Tretyakov Alexandr
 */

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RequestIdHolder extends AbstractIdHolder{

    @Override
    String holderType() {
        return "Request";
    }
}
