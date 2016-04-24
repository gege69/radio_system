package br.com.radio.security.recaptcha;


public class ReCaptchaAuthenticationFilter 
//extends UsernamePasswordAuthenticationFilter 
{

//	@Autowired
//	private ReCaptchaImpl reCaptcha;
//	
//	@Autowired
//	private Environment env;
//
//	@Override
//	public Authentication attemptAuthentication( HttpServletRequest request, HttpServletResponse response ) throws AuthenticationException
//	{
//		
//		
//		return super.attemptAuthentication( request, response );
//	}
//	
//	
//	private void reCaptchaError(HttpServletRequest request, HttpServletResponse response, String errorMsg)
//    {
//        System.out.println("ReCaptcha failed : " + errorMsg);
//        try
//        {
//            RequestDispatcher dispatcher = request.getRequestDispatcher("/login?error=2");
// 
//            dispatcher.forward(request, response);
//        }
//        catch (ServletException e)
//        {
//            throw new AuthenticationServiceException("ReCaptcha failed : " + errorMsg);
//        }
//        catch (IOException e)
//        {
//            throw new AuthenticationServiceException("Recaptcha failed : " + errorMsg);
//        }
//    }
// 
//    public String getPrivateKey()
//    {
//    	return env.getRequiredProperty( "google.private.key" );
//    }
// 
//    public void afterPropertiesSet()
//    {
//        if (StringUtils.isEmpty(this.getPrivateKey()))
//        {
//            throw new IllegalArgumentException("The 'privateKey' should be set for the bean type 'ReCaptchaAuthenticationFilter'");
//        }
//        else
//        {
//            reCaptcha.setPrivateKey(this.getPrivateKey());
//        }
//    }


}
