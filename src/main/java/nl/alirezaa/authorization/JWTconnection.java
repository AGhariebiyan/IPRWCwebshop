package nl.alirezaa.authorization;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import nl.alirezaa.model.AccountModel;
import org.joda.time.DateTime;

public class JWTconnection {

    private static String key;
    private static JWTconnection instance;

    public static synchronized JWTconnection getInstance() {
        if(instance ==  null){
            instance = new JWTconnection();
        }
        return instance;
    }

    public synchronized  void setKey(String secret) {key = secret;}

    public String createToken(AccountModel account) {
        String token = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(key);
            token = JWT.create()
                    .withExpiresAt(new DateTime().plusMinutes(30).toDate())
                    .withIssuer("auth0")
                    .withClaim("accountId", account.getUser_id())
                    .sign(algorithm);
        } catch (
                JWTCreationException exception){
            //Invalid Signing configuration / Couldn't convert Claims.
        }

        return token;
    }

    public boolean verifyJwtToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(key);
            return true;
        } catch (
                JWTVerificationException exception){
            //Invalid signature/claims
            return false;
        }
    }

    public DecodedJWT decodedJWT(String token) {
        try {
            return JWT.decode(token);
        } catch (JWTDecodeException exception){
            exception.printStackTrace();
        }
        return null;
    }



}
