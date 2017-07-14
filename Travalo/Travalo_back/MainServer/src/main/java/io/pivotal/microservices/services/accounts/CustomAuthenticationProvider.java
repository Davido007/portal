package io.pivotal.microservices.services.accounts;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * Created by DPLICHTA on 4/14/2017.
 */
public class CustomAuthenticationProvider extends DaoAuthenticationProvider {

/*    @Autowired
    private UserRepository userRepository1;*/

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        System.out.println("kk"+auth.getDetails());
        System.out.println("kk"+auth.getName());
        System.out.println("lllllllllllllllllllllllllllllllllllll");
        final String verificationCode = ((CustomWebAuthenticationDetails) auth.getDetails()).getVerificationCode();
        //final userService.models.User user = userRepository1.findByEmail(auth.getName());
        System.out.println(auth.getName());
//        System.out.println("zzzzzzzzzzzzz"+user);
///*        if ((user == null)) {
//            throw new BadCredentialsException("Invalid username or password");
//        }*/
        // to verify verification code
/*        if (user.isUsing2FA()) {
            final Totp totp = new Totp(user.getSecret());
            if (!isValidLong(verificationCode) || !totp.verify(verificationCode)) {
                throw new BadCredentialsException("Invalid verfication code");
            }

        }*/
       // final Authentication result = super.authenticate(auth);
//        return new UsernamePasswordAuthenticationToken(user, result.getCredentials(), result.getAuthorities());
        return null;
    }

    private boolean isValidLong(String code) {
        try {
            Long.parseLong(code);
        } catch (final NumberFormatException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}

