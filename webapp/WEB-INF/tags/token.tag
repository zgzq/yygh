<%@tag description="Synchronizer Token" import="my.web.TokenManager" %>

<input
    type="hidden"
    name="<%=TokenManager.getTokenKey()%>"
    value="<%=session.getAttribute(TokenManager.getTokenKey())%>"
/>