package Filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncoding implements Filter{
	private String encoding;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		req.setCharacterEncoding(encoding);
		chain.doFilter(req, resp);	
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		// web.xml의 설정 값을 인코딩을 설정하는데, 없다면 !! 기본으로 utf-8을 하겠다!
		encoding = config.getInitParameter("EncodingFilter");
		if(encoding == null) {
			encoding = "utf-8";
		}
	}

}
