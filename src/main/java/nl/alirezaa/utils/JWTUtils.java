package nl.alirezaa.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import nl.alirezaa.model.AccountModel;
import org.joda.time.DateTime;

public class JWTUtils {

    private static byte[] key;
    private static JWTUtils instance;

    public static synchronized JWTUtils getInstance() {
        if(instance ==  null){
            instance = new JWTUtils();
        }
        return instance;
    }

    public synchronized  void setKey(byte[] secret) {key = secret;}

    public synchronized byte[] getKey() {return this.key;}

    public String createToken(AccountModel account) {
        String token = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(key);
            token = JWT.create()
                    .withExpiresAt(new DateTime().plusMinutes(30).toDate())
                    .withIssuer("auth0")
                    .withClaim("accountId", account.getUser_id())
                    .withClaim("accoutType", account.getAccountType())
                    .sign(algorithm);
        } catch (
                JWTCreationException exception){
            //Invalid Signing configuration / Couldn't convert Claims.
        }

        return token;
    }

    public boolean verifyJwtToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(key);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (
                JWTVerificationException exception){
            //Invalid signature/claims
            return false;
        }
    }

    public boolean decodedJWT(String token) {
        try {
            Jws<Claims> result = Jwts.parser()
                    .setSigningKey(Keys.hmacShaKeyFor(getInstance().getKey()))
                    .parseClaimsJws(token);
        } catch (JWTDecodeException exception){
            exception.printStackTrace();
            System.out.println("The JWT has an incorrect secret key. The request to the API is restricted.");
        }
        return false;
    }

    public String retrieveUsernameFromJWToken(String token) {
        String username = "";

        Jws<Claims> result = Jwts.parser()
                .setSigningKey(Keys.hmacShaKeyFor(getInstance().getKey()))
                .parseClaimsJws(token);

        username = result.getBody().get("accountId").toString();

        return username.toString();
    }



}
