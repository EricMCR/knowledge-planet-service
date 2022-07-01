package mcr.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.JsonNode;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Map;

public class JWTUtils {

    private static final String SIGN = "user-center";

    /**
     * Generate token
     *
     * @param map User information
     * @return token
     */
    public static String getToken(Map<String,String> map){
        JWTCreator.Builder builder = JWT.create();
        map.forEach(builder::withClaim);

        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE,1);
        builder.withExpiresAt(instance.getTime());
        return builder.sign(Algorithm.HMAC256(SIGN));
    }

    /**
     * toke certification
     *
     * @param token
     * @return User information
     */
    public static DecodedJWT verify(String token){
        JWTVerifier build = JWT.require(Algorithm.HMAC256(SIGN)).build();
        return build.verify(token);
    }

    /**
     * Get user id
     *
     * @param request
     * @return user id
     */
    public static Long getUserId(HttpServletRequest request){
        String token = request.getHeader("token");
        DecodedJWT verify = JWTUtils.verify(token);
        Claim idClaim = verify.getClaim("id");
        JsonNode id = idClaim.as(JsonNode.class);
        return id.asLong();
    }

    /**
     * Get the user's identity
     *
     * @param request
     * @return User identification code
     */
    public static int getUserRole(HttpServletRequest request){
        String token = request.getHeader("token");
        DecodedJWT verify = JWTUtils.verify(token);
        return Integer.parseInt(verify.getClaim("userRole").asString());
    }
}